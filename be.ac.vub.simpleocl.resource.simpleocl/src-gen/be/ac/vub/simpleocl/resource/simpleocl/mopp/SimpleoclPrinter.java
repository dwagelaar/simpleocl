/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

public class SimpleoclPrinter implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextPrinter {
	
	protected be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolverFactory tokenResolverFactory = new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTokenResolverFactory();
	
	protected java.io.OutputStream outputStream;
	
	/**
	 * Holds the resource that is associated with this printer. This may be null if
	 * the printer is used stand alone.
	 */
	private be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource resource;
	
	private java.util.Map<?, ?> options;
	
	public SimpleoclPrinter(java.io.OutputStream outputStream, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource resource) {
		super();
		this.outputStream = outputStream;
		this.resource = resource;
	}
	
	protected int matchCount(java.util.Map<String, Integer> featureCounter, java.util.Collection<String> needed) {
		int pos = 0;
		int neg = 0;
		
		for (String featureName : featureCounter.keySet()) {
			if (needed.contains(featureName)) {
				int value = featureCounter.get(featureName);
				if (value == 0) {
					neg += 1;
				} else {
					pos += 1;
				}
			}
		}
		return neg > 0 ? -neg : pos;
	}
	
	protected void doPrint(org.eclipse.emf.ecore.EObject element, java.io.PrintWriter out, String globaltab) {
		if (element == null) {
			throw new java.lang.IllegalArgumentException("Nothing to write.");
		}
		if (out == null) {
			throw new java.lang.IllegalArgumentException("Nothing to write on.");
		}
		
		if (element instanceof be.ac.vub.simpleocl.Module) {
			print_be_ac_vub_simpleocl_Module((be.ac.vub.simpleocl.Module) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.Import) {
			print_be_ac_vub_simpleocl_Import((be.ac.vub.simpleocl.Import) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.OclFeatureDefinition) {
			print_be_ac_vub_simpleocl_OclFeatureDefinition((be.ac.vub.simpleocl.OclFeatureDefinition) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.OclContextDefinition) {
			print_be_ac_vub_simpleocl_OclContextDefinition((be.ac.vub.simpleocl.OclContextDefinition) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.OclMetamodel) {
			print_be_ac_vub_simpleocl_OclMetamodel((be.ac.vub.simpleocl.OclMetamodel) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.OclInstanceModel) {
			print_be_ac_vub_simpleocl_OclInstanceModel((be.ac.vub.simpleocl.OclInstanceModel) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.Attribute) {
			print_be_ac_vub_simpleocl_Attribute((be.ac.vub.simpleocl.Attribute) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.Operation) {
			print_be_ac_vub_simpleocl_Operation((be.ac.vub.simpleocl.Operation) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.Parameter) {
			print_be_ac_vub_simpleocl_Parameter((be.ac.vub.simpleocl.Parameter) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.OclModelElementExp) {
			print_be_ac_vub_simpleocl_OclModelElementExp((be.ac.vub.simpleocl.OclModelElementExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.LambdaCallExp) {
			print_be_ac_vub_simpleocl_LambdaCallExp((be.ac.vub.simpleocl.LambdaCallExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.VariableExp) {
			print_be_ac_vub_simpleocl_VariableExp((be.ac.vub.simpleocl.VariableExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.SuperExp) {
			print_be_ac_vub_simpleocl_SuperExp((be.ac.vub.simpleocl.SuperExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.SelfExp) {
			print_be_ac_vub_simpleocl_SelfExp((be.ac.vub.simpleocl.SelfExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.EnvExp) {
			print_be_ac_vub_simpleocl_EnvExp((be.ac.vub.simpleocl.EnvExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.StringExp) {
			print_be_ac_vub_simpleocl_StringExp((be.ac.vub.simpleocl.StringExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.BooleanExp) {
			print_be_ac_vub_simpleocl_BooleanExp((be.ac.vub.simpleocl.BooleanExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.RealExp) {
			print_be_ac_vub_simpleocl_RealExp((be.ac.vub.simpleocl.RealExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.IntegerExp) {
			print_be_ac_vub_simpleocl_IntegerExp((be.ac.vub.simpleocl.IntegerExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.BagExp) {
			print_be_ac_vub_simpleocl_BagExp((be.ac.vub.simpleocl.BagExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.OrderedSetExp) {
			print_be_ac_vub_simpleocl_OrderedSetExp((be.ac.vub.simpleocl.OrderedSetExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.SequenceExp) {
			print_be_ac_vub_simpleocl_SequenceExp((be.ac.vub.simpleocl.SequenceExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.SetExp) {
			print_be_ac_vub_simpleocl_SetExp((be.ac.vub.simpleocl.SetExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.TupleExp) {
			print_be_ac_vub_simpleocl_TupleExp((be.ac.vub.simpleocl.TupleExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.TuplePart) {
			print_be_ac_vub_simpleocl_TuplePart((be.ac.vub.simpleocl.TuplePart) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.MapExp) {
			print_be_ac_vub_simpleocl_MapExp((be.ac.vub.simpleocl.MapExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.MapElement) {
			print_be_ac_vub_simpleocl_MapElement((be.ac.vub.simpleocl.MapElement) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.EnumLiteralExp) {
			print_be_ac_vub_simpleocl_EnumLiteralExp((be.ac.vub.simpleocl.EnumLiteralExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.OclUndefinedExp) {
			print_be_ac_vub_simpleocl_OclUndefinedExp((be.ac.vub.simpleocl.OclUndefinedExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.LetExp) {
			print_be_ac_vub_simpleocl_LetExp((be.ac.vub.simpleocl.LetExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.IfExp) {
			print_be_ac_vub_simpleocl_IfExp((be.ac.vub.simpleocl.IfExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.BraceExp) {
			print_be_ac_vub_simpleocl_BraceExp((be.ac.vub.simpleocl.BraceExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.EqOpCallExp) {
			print_be_ac_vub_simpleocl_EqOpCallExp((be.ac.vub.simpleocl.EqOpCallExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.RelOpCallExp) {
			print_be_ac_vub_simpleocl_RelOpCallExp((be.ac.vub.simpleocl.RelOpCallExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.AddOpCallExp) {
			print_be_ac_vub_simpleocl_AddOpCallExp((be.ac.vub.simpleocl.AddOpCallExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.IntOpCallExp) {
			print_be_ac_vub_simpleocl_IntOpCallExp((be.ac.vub.simpleocl.IntOpCallExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.MulOpCallExp) {
			print_be_ac_vub_simpleocl_MulOpCallExp((be.ac.vub.simpleocl.MulOpCallExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.NotOpCallExp) {
			print_be_ac_vub_simpleocl_NotOpCallExp((be.ac.vub.simpleocl.NotOpCallExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.StaticPropertyCallExp) {
			print_be_ac_vub_simpleocl_StaticPropertyCallExp((be.ac.vub.simpleocl.StaticPropertyCallExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.StaticOperationCall) {
			print_be_ac_vub_simpleocl_StaticOperationCall((be.ac.vub.simpleocl.StaticOperationCall) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.StaticNavigationOrAttributeCall) {
			print_be_ac_vub_simpleocl_StaticNavigationOrAttributeCall((be.ac.vub.simpleocl.StaticNavigationOrAttributeCall) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.NavigationOrAttributeCall) {
			print_be_ac_vub_simpleocl_NavigationOrAttributeCall((be.ac.vub.simpleocl.NavigationOrAttributeCall) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.IterateExp) {
			print_be_ac_vub_simpleocl_IterateExp((be.ac.vub.simpleocl.IterateExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.IteratorExp) {
			print_be_ac_vub_simpleocl_IteratorExp((be.ac.vub.simpleocl.IteratorExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.Iterator) {
			print_be_ac_vub_simpleocl_Iterator((be.ac.vub.simpleocl.Iterator) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.CollectionOperationCall) {
			print_be_ac_vub_simpleocl_CollectionOperationCall((be.ac.vub.simpleocl.CollectionOperationCall) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.StringType) {
			print_be_ac_vub_simpleocl_StringType((be.ac.vub.simpleocl.StringType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.BooleanType) {
			print_be_ac_vub_simpleocl_BooleanType((be.ac.vub.simpleocl.BooleanType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.IntegerType) {
			print_be_ac_vub_simpleocl_IntegerType((be.ac.vub.simpleocl.IntegerType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.RealType) {
			print_be_ac_vub_simpleocl_RealType((be.ac.vub.simpleocl.RealType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.BagType) {
			print_be_ac_vub_simpleocl_BagType((be.ac.vub.simpleocl.BagType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.OrderedSetType) {
			print_be_ac_vub_simpleocl_OrderedSetType((be.ac.vub.simpleocl.OrderedSetType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.SequenceType) {
			print_be_ac_vub_simpleocl_SequenceType((be.ac.vub.simpleocl.SequenceType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.SetType) {
			print_be_ac_vub_simpleocl_SetType((be.ac.vub.simpleocl.SetType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.OclAnyType) {
			print_be_ac_vub_simpleocl_OclAnyType((be.ac.vub.simpleocl.OclAnyType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.TupleType) {
			print_be_ac_vub_simpleocl_TupleType((be.ac.vub.simpleocl.TupleType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.TupleTypeAttribute) {
			print_be_ac_vub_simpleocl_TupleTypeAttribute((be.ac.vub.simpleocl.TupleTypeAttribute) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.OclModelElement) {
			print_be_ac_vub_simpleocl_OclModelElement((be.ac.vub.simpleocl.OclModelElement) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.MapType) {
			print_be_ac_vub_simpleocl_MapType((be.ac.vub.simpleocl.MapType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.LambdaType) {
			print_be_ac_vub_simpleocl_LambdaType((be.ac.vub.simpleocl.LambdaType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.EnvType) {
			print_be_ac_vub_simpleocl_EnvType((be.ac.vub.simpleocl.EnvType) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.LocalVariable) {
			print_be_ac_vub_simpleocl_LocalVariable((be.ac.vub.simpleocl.LocalVariable) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.OperatorCallExp) {
			print_be_ac_vub_simpleocl_OperatorCallExp((be.ac.vub.simpleocl.OperatorCallExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.PropertyCallExp) {
			print_be_ac_vub_simpleocl_PropertyCallExp((be.ac.vub.simpleocl.PropertyCallExp) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.OperationCall) {
			print_be_ac_vub_simpleocl_OperationCall((be.ac.vub.simpleocl.OperationCall) element, globaltab, out);
			return;
		}
		if (element instanceof be.ac.vub.simpleocl.CollectionType) {
			print_be_ac_vub_simpleocl_CollectionType((be.ac.vub.simpleocl.CollectionType) element, globaltab, out);
			return;
		}
		
		addWarningToResource("The printer can not handle " + element.eClass().getName() + " elements", element);
	}
	
	protected be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclReferenceResolverSwitch getReferenceResolverSwitch() {
		return (be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclReferenceResolverSwitch) new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation().getReferenceResolverSwitch();
	}
	
	protected void addWarningToResource(final String errorMessage, org.eclipse.emf.ecore.EObject cause) {
		be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource resource = getResource();
		if (resource == null) {
			// the resource can be null if the printer is used stand alone
			return;
		}
		resource.addProblem(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclProblem(errorMessage, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.PRINT_PROBLEM, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity.WARNING), cause);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		this.options = options;
	}
	
	public java.util.Map<?,?> getOptions() {
		return options;
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource getResource() {
		return resource;
	}
	
	/**
	 * Calls {@link #doPrint(EObject, PrintWriter, String)} and writes the result to
	 * the underlying output stream.
	 */
	public void print(org.eclipse.emf.ecore.EObject element) {
		java.io.PrintWriter out = new java.io.PrintWriter(new java.io.BufferedOutputStream(outputStream));
		doPrint(element, out, "");
		out.flush();
		out.close();
	}
	
	public void print_be_ac_vub_simpleocl_Module(be.ac.vub.simpleocl.Module element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(8);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MODULE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MODULE__METAMODELS));
		printCountingMap.put("metamodels", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MODULE__FEATURES));
		printCountingMap.put("features", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MODULE__IMPORTS));
		printCountingMap.put("imports", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("module");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_Module_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_Module_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_Module_2(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_Module_3(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_be_ac_vub_simpleocl_Module_0(be.ac.vub.simpleocl.Module element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MODULE__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MODULE__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MODULE__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MODULE__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_Module_1(be.ac.vub.simpleocl.Module element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("metamodels");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("metamodels");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MODULE__METAMODELS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("metamodels", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_Module_1_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_Module_1_0(be.ac.vub.simpleocl.Module element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("metamodels");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MODULE__METAMODELS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("metamodels", count - 1);
		}
	}
	
	public void print_be_ac_vub_simpleocl_Module_2(be.ac.vub.simpleocl.Module element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("imports");
		if (count > 0) {
			java.util.List<?> list = (java.util.List<?>)element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MODULE__IMPORTS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			java.util.ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("imports", 0);
		}
	}
	
	public void print_be_ac_vub_simpleocl_Module_3(be.ac.vub.simpleocl.Module element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("features");
		if (count > 0) {
			java.util.List<?> list = (java.util.List<?>)element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MODULE__FEATURES));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			java.util.ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("features", 0);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_Import(be.ac.vub.simpleocl.Import element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(6);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IMPORT__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IMPORT__MODULE));
		printCountingMap.put("module", temp == null ? 0 : 1);
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("import");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_Import_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_Import_0(be.ac.vub.simpleocl.Import element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IMPORT__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IMPORT__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IMPORT__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IMPORT__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_OclFeatureDefinition(be.ac.vub.simpleocl.OclFeatureDefinition element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__FEATURE));
		printCountingMap.put("feature", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__CONTEXT_));
		printCountingMap.put("context_", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__STATIC));
		printCountingMap.put("static", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("context_");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__CONTEXT_));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("context_", count - 1);
		}
		// DEFINITION PART BEGINS (BooleanTerminal)
		count = printCountingMap.get("static");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__STATIC));
			if (o != null) {
			}
			printCountingMap.put("static", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("def");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("feature");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__FEATURE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("feature", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_OclContextDefinition(be.ac.vub.simpleocl.OclContextDefinition element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(6);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_CONTEXT_DEFINITION__DEFINITION));
		printCountingMap.put("definition", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_CONTEXT_DEFINITION__CONTEXT_));
		printCountingMap.put("context_", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("context");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("context_");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_CONTEXT_DEFINITION__CONTEXT_));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("context_", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_OclMetamodel(be.ac.vub.simpleocl.OclMetamodel element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_METAMODEL__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_METAMODEL__ELEMENTS));
		printCountingMap.put("elements", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_METAMODEL__MODEL));
		printCountingMap.put("model", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_OclMetamodel_0(element, localtab, out, printCountingMap);
	}
	
	public void print_be_ac_vub_simpleocl_OclMetamodel_0(be.ac.vub.simpleocl.OclMetamodel element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_METAMODEL__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_METAMODEL__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_METAMODEL__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_METAMODEL__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_OclInstanceModel(be.ac.vub.simpleocl.OclInstanceModel element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__ELEMENTS));
		printCountingMap.put("elements", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL));
		printCountingMap.put("metamodel", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_OclInstanceModel_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("metamodel");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOclInstanceModelMetamodelReferenceResolver().deResolve((be.ac.vub.simpleocl.OclMetamodel) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL)), element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL), element));
				out.print(" ");
			}
			printCountingMap.put("metamodel", count - 1);
		}
	}
	
	public void print_be_ac_vub_simpleocl_OclInstanceModel_0(be.ac.vub.simpleocl.OclInstanceModel element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_LocalVariable(be.ac.vub.simpleocl.LocalVariable element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(11);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__VAR_NAME));
		printCountingMap.put("varName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__VARIABLE_EXP));
		printCountingMap.put("variableExp", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__INIT_EXPRESSION));
		printCountingMap.put("initExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__BASE_EXP));
		printCountingMap.put("baseExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__EQ));
		printCountingMap.put("eq", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("varName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__VAR_NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__VAR_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("varName", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_LocalVariable_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("eq");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__EQ));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("EQ");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__EQ), element));
				out.print(" ");
			}
			printCountingMap.put("eq", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("initExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__INIT_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("initExpression", count - 1);
		}
	}
	
	public void print_be_ac_vub_simpleocl_LocalVariable_0(be.ac.vub.simpleocl.LocalVariable element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("type");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("type", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_Attribute(be.ac.vub.simpleocl.Attribute element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(9);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__DEFINITION));
		printCountingMap.put("definition", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__EQ));
		printCountingMap.put("eq", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__INIT_EXPRESSION));
		printCountingMap.put("initExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_Attribute_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("type");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("type", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("eq");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__EQ));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("EQ");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__EQ), element));
				out.print(" ");
			}
			printCountingMap.put("eq", count - 1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("initExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__INIT_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("initExpression", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_Attribute_0(be.ac.vub.simpleocl.Attribute element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ATTRIBUTE__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_Operation(be.ac.vub.simpleocl.Operation element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(10);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__DEFINITION));
		printCountingMap.put("definition", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__EQ));
		printCountingMap.put("eq", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__PARAMETERS));
		printCountingMap.put("parameters", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__RETURN_TYPE));
		printCountingMap.put("returnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__BODY));
		printCountingMap.put("body", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_Operation_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_Operation_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("returnType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__RETURN_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("returnType", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("eq");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__EQ));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("EQ");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__EQ), element));
				out.print(" ");
			}
			printCountingMap.put("eq", count - 1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("body");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__BODY));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("body", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_Operation_0(be.ac.vub.simpleocl.Operation element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_Operation_1(be.ac.vub.simpleocl.Operation element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__PARAMETERS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_Operation_1_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_Operation_1_0(be.ac.vub.simpleocl.Operation element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION__PARAMETERS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_Parameter(be.ac.vub.simpleocl.Parameter element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(8);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PARAMETER__VAR_NAME));
		printCountingMap.put("varName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PARAMETER__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PARAMETER__VARIABLE_EXP));
		printCountingMap.put("variableExp", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PARAMETER__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("varName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PARAMETER__VAR_NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PARAMETER__VAR_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("varName", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("type");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PARAMETER__TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("type", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_OclModelElementExp(be.ac.vub.simpleocl.OclModelElementExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(18);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__MODEL));
		printCountingMap.put("model", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("model");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__MODEL));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOclModelElementExpModelReferenceResolver().deResolve((be.ac.vub.simpleocl.OclModel) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__MODEL)), element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__MODEL), element));
				out.print(" ");
			}
			printCountingMap.put("model", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("!");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_OclModelElementExp_0(element, localtab, out, printCountingMap);
	}
	
	public void print_be_ac_vub_simpleocl_OclModelElementExp_0(be.ac.vub.simpleocl.OclModelElementExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_LambdaCallExp(be.ac.vub.simpleocl.LambdaCallExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(18);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__REFERRED_VARIABLE));
		printCountingMap.put("referredVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__ARGUMENTS));
		printCountingMap.put("arguments", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("referredVariable");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__REFERRED_VARIABLE));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getVariableExpReferredVariableReferenceResolver().deResolve((be.ac.vub.simpleocl.VariableDeclaration) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__REFERRED_VARIABLE)), element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__REFERRED_VARIABLE), element));
				out.print(" ");
			}
			printCountingMap.put("referredVariable", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_LambdaCallExp_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_LambdaCallExp_0(be.ac.vub.simpleocl.LambdaCallExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_LambdaCallExp_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_LambdaCallExp_0_0(be.ac.vub.simpleocl.LambdaCallExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_VariableExp(be.ac.vub.simpleocl.VariableExp element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__REFERRED_VARIABLE));
		printCountingMap.put("referredVariable", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("referredVariable");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__REFERRED_VARIABLE));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getVariableExpReferredVariableReferenceResolver().deResolve((be.ac.vub.simpleocl.VariableDeclaration) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__REFERRED_VARIABLE)), element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.VARIABLE_EXP__REFERRED_VARIABLE), element));
				out.print(" ");
			}
			printCountingMap.put("referredVariable", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_SuperExp(be.ac.vub.simpleocl.SuperExp element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(16);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SUPER_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SUPER_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SUPER_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SUPER_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SUPER_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SUPER_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SUPER_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SUPER_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SUPER_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SUPER_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SUPER_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SUPER_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("super");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_SelfExp(be.ac.vub.simpleocl.SelfExp element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(16);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SELF_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SELF_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SELF_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SELF_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SELF_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SELF_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SELF_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SELF_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SELF_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SELF_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SELF_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SELF_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("self");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_EnvExp(be.ac.vub.simpleocl.EnvExp element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(16);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("env");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_StringExp(be.ac.vub.simpleocl.StringExp element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__STRING_SYMBOL));
		printCountingMap.put("stringSymbol", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderInQuotes)
		count = printCountingMap.get("stringSymbol");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__STRING_SYMBOL));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_39_39_92");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_EXP__STRING_SYMBOL), element));
				out.print(" ");
			}
			printCountingMap.put("stringSymbol", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_BooleanExp(be.ac.vub.simpleocl.BooleanExp element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__BOOLEAN_SYMBOL));
		printCountingMap.put("booleanSymbol", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (BooleanTerminal)
		count = printCountingMap.get("booleanSymbol");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_EXP__BOOLEAN_SYMBOL));
			if (o != null) {
			}
			printCountingMap.put("booleanSymbol", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_RealExp(be.ac.vub.simpleocl.RealExp element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__REAL_SYMBOL));
		printCountingMap.put("realSymbol", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("realSymbol");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__REAL_SYMBOL));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("FLOAT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_EXP__REAL_SYMBOL), element));
				out.print(" ");
			}
			printCountingMap.put("realSymbol", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_IntegerExp(be.ac.vub.simpleocl.IntegerExp element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__INTEGER_SYMBOL));
		printCountingMap.put("integerSymbol", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("integerSymbol");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__INTEGER_SYMBOL));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("INTEGER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_EXP__INTEGER_SYMBOL), element));
				out.print(" ");
			}
			printCountingMap.put("integerSymbol", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_BagExp(be.ac.vub.simpleocl.BagExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__ELEMENTS));
		printCountingMap.put("elements", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("Bag");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_BagExp_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_BagExp_0(be.ac.vub.simpleocl.BagExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__ELEMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elements", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_BagExp_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_BagExp_0_0(be.ac.vub.simpleocl.BagExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_EXP__ELEMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elements", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_OrderedSetExp(be.ac.vub.simpleocl.OrderedSetExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__ELEMENTS));
		printCountingMap.put("elements", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("OrderedSet");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_OrderedSetExp_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_OrderedSetExp_0(be.ac.vub.simpleocl.OrderedSetExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__ELEMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elements", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_OrderedSetExp_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_OrderedSetExp_0_0(be.ac.vub.simpleocl.OrderedSetExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__ELEMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elements", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_SequenceExp(be.ac.vub.simpleocl.SequenceExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__ELEMENTS));
		printCountingMap.put("elements", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("Sequence");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_SequenceExp_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_SequenceExp_0(be.ac.vub.simpleocl.SequenceExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__ELEMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elements", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_SequenceExp_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_SequenceExp_0_0(be.ac.vub.simpleocl.SequenceExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_EXP__ELEMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elements", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_SetExp(be.ac.vub.simpleocl.SetExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__ELEMENTS));
		printCountingMap.put("elements", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("Set");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_SetExp_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_SetExp_0(be.ac.vub.simpleocl.SetExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__ELEMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elements", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_SetExp_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_SetExp_0_0(be.ac.vub.simpleocl.SetExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_EXP__ELEMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elements", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_TupleExp(be.ac.vub.simpleocl.TupleExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__TUPLE_PART));
		printCountingMap.put("tuplePart", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("Tuple");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_TupleExp_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_TupleExp_0(be.ac.vub.simpleocl.TupleExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("tuplePart");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__TUPLE_PART));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("tuplePart", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_TupleExp_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_TupleExp_0_0(be.ac.vub.simpleocl.TupleExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("tuplePart");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_EXP__TUPLE_PART));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("tuplePart", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_TuplePart(be.ac.vub.simpleocl.TuplePart element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(12);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__VAR_NAME));
		printCountingMap.put("varName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__VARIABLE_EXP));
		printCountingMap.put("variableExp", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__INIT_EXPRESSION));
		printCountingMap.put("initExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__BASE_EXP));
		printCountingMap.put("baseExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__EQ));
		printCountingMap.put("eq", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__TUPLE));
		printCountingMap.put("tuple", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("varName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__VAR_NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__VAR_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("varName", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_TuplePart_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("eq");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__EQ));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("EQ");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__EQ), element));
				out.print(" ");
			}
			printCountingMap.put("eq", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("initExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__INIT_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("initExpression", count - 1);
		}
	}
	
	public void print_be_ac_vub_simpleocl_TuplePart_0(be.ac.vub.simpleocl.TuplePart element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("type");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_PART__TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("type", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_MapExp(be.ac.vub.simpleocl.MapExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__ELEMENTS));
		printCountingMap.put("elements", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("Map");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_MapExp_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_MapExp_0(be.ac.vub.simpleocl.MapExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__ELEMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elements", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_MapExp_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_MapExp_0_0(be.ac.vub.simpleocl.MapExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_EXP__ELEMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elements", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_MapElement(be.ac.vub.simpleocl.MapElement element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_ELEMENT__MAP));
		printCountingMap.put("map", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_ELEMENT__KEY));
		printCountingMap.put("key", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_ELEMENT__VALUE));
		printCountingMap.put("value", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("key");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_ELEMENT__KEY));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("key", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("value");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_ELEMENT__VALUE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("value", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_EnumLiteralExp(be.ac.vub.simpleocl.EnumLiteralExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("#");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_EnumLiteralExp_0(element, localtab, out, printCountingMap);
	}
	
	public void print_be_ac_vub_simpleocl_EnumLiteralExp_0(be.ac.vub.simpleocl.EnumLiteralExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_OclUndefinedExp(be.ac.vub.simpleocl.OclUndefinedExp element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(16);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_UNDEFINED_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_UNDEFINED_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_UNDEFINED_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_UNDEFINED_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_UNDEFINED_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_UNDEFINED_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_UNDEFINED_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_UNDEFINED_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_UNDEFINED_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_UNDEFINED_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_UNDEFINED_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_UNDEFINED_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("OclUndefined");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_LetExp(be.ac.vub.simpleocl.LetExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(18);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__VARIABLE));
		printCountingMap.put("variable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__IN_));
		printCountingMap.put("in_", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("let");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variable");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__VARIABLE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variable", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("in");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("in_");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LET_EXP__IN_));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("in_", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_IfExp(be.ac.vub.simpleocl.IfExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(19);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__THEN_EXPRESSION));
		printCountingMap.put("thenExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__CONDITION));
		printCountingMap.put("condition", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__ELSE_EXPRESSION));
		printCountingMap.put("elseExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("if");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("condition");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__CONDITION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("condition", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("then");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("thenExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__THEN_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("thenExpression", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("else");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elseExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.IF_EXP__ELSE_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elseExpression", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("endif");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_BraceExp(be.ac.vub.simpleocl.BraceExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__EXP));
		printCountingMap.put("exp", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("exp");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BRACE_EXP__EXP));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("exp", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_OperatorCallExp(be.ac.vub.simpleocl.OperatorCallExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(20);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__CALLS));
		printCountingMap.put("calls", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__ARGUMENT));
		printCountingMap.put("argument", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__OPERATION_NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("BOOLOP");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("argument");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__ARGUMENT));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("argument", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_EqOpCallExp(be.ac.vub.simpleocl.EqOpCallExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(20);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__CALLS));
		printCountingMap.put("calls", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__ARGUMENT));
		printCountingMap.put("argument", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_EqOpCallExp_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("argument");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__ARGUMENT));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("argument", count - 1);
		}
	}
	
	public void print_be_ac_vub_simpleocl_EqOpCallExp_0(be.ac.vub.simpleocl.EqOpCallExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__OPERATION_NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("NEQ");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
			count = printCountingMap.get("operationName");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__OPERATION_NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("EQ");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__OPERATION_NAME), element));
					out.print(" ");
				}
				printCountingMap.put("operationName", count - 1);
			}
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_RelOpCallExp(be.ac.vub.simpleocl.RelOpCallExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(20);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__CALLS));
		printCountingMap.put("calls", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__ARGUMENT));
		printCountingMap.put("argument", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__OPERATION_NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("RELOP");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("argument");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__ARGUMENT));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("argument", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_AddOpCallExp(be.ac.vub.simpleocl.AddOpCallExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(20);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__CALLS));
		printCountingMap.put("calls", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__ARGUMENT));
		printCountingMap.put("argument", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__OPERATION_NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("ADDOP");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("argument");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__ARGUMENT));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("argument", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_IntOpCallExp(be.ac.vub.simpleocl.IntOpCallExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(20);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__CALLS));
		printCountingMap.put("calls", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__ARGUMENT));
		printCountingMap.put("argument", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__OPERATION_NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("INTOP");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("argument");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__ARGUMENT));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("argument", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_MulOpCallExp(be.ac.vub.simpleocl.MulOpCallExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(20);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__CALLS));
		printCountingMap.put("calls", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__ARGUMENT));
		printCountingMap.put("argument", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__OPERATION_NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("MULOP");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("argument");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__ARGUMENT));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("argument", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_NotOpCallExp(be.ac.vub.simpleocl.NotOpCallExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(20);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__CALLS));
		printCountingMap.put("calls", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__ARGUMENT));
		printCountingMap.put("argument", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__OPERATION_NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("NOTOP");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_StaticPropertyCallExp(be.ac.vub.simpleocl.StaticPropertyCallExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(18);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL));
		printCountingMap.put("staticCall", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("staticCall");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("staticCall", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_StaticOperationCall(be.ac.vub.simpleocl.StaticOperationCall element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__STATIC_CALL_EXP));
		printCountingMap.put("staticCallExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__ARGUMENTS));
		printCountingMap.put("arguments", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("::");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_StaticOperationCall_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_StaticOperationCall_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_StaticOperationCall_0(be.ac.vub.simpleocl.StaticOperationCall element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__OPERATION_NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("operationName");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__OPERATION_NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__OPERATION_NAME), element));
					out.print(" ");
				}
				printCountingMap.put("operationName", count - 1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_StaticOperationCall_1(be.ac.vub.simpleocl.StaticOperationCall element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_StaticOperationCall_1_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_StaticOperationCall_1_0(be.ac.vub.simpleocl.StaticOperationCall element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_StaticNavigationOrAttributeCall(be.ac.vub.simpleocl.StaticNavigationOrAttributeCall element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(6);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_NAVIGATION_OR_ATTRIBUTE_CALL__STATIC_CALL_EXP));
		printCountingMap.put("staticCallExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_NAVIGATION_OR_ATTRIBUTE_CALL__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("::");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_StaticNavigationOrAttributeCall_0(element, localtab, out, printCountingMap);
	}
	
	public void print_be_ac_vub_simpleocl_StaticNavigationOrAttributeCall_0(be.ac.vub.simpleocl.StaticNavigationOrAttributeCall element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_NAVIGATION_OR_ATTRIBUTE_CALL__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_NAVIGATION_OR_ATTRIBUTE_CALL__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_NAVIGATION_OR_ATTRIBUTE_CALL__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STATIC_NAVIGATION_OR_ATTRIBUTE_CALL__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_PropertyCallExp(be.ac.vub.simpleocl.PropertyCallExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(18);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__IF_EXP3));
		printCountingMap.put("ifExp3", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__APPLIED_PROPERTY));
		printCountingMap.put("appliedProperty", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__LET_EXP));
		printCountingMap.put("letExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__LOOP_EXP));
		printCountingMap.put("loopExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__PARENT_OPERATION));
		printCountingMap.put("parentOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__INITIALIZED_VARIABLE));
		printCountingMap.put("initializedVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__IF_EXP2));
		printCountingMap.put("ifExp2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__OWNING_OPERATION));
		printCountingMap.put("owningOperation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__IF_EXP1));
		printCountingMap.put("ifExp1", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__OWNING_ATTRIBUTE));
		printCountingMap.put("owningAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__CALLS));
		printCountingMap.put("calls", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("calls");
		if (count > 0) {
			java.util.List<?> list = (java.util.List<?>)element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__CALLS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			java.util.ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("calls", 0);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_OperationCall(be.ac.vub.simpleocl.OperationCall element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION_CALL__CALL_EXP));
		printCountingMap.put("callExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION_CALL__ARGUMENTS));
		printCountingMap.put("arguments", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION_CALL__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_OperationCall_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_OperationCall_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_OperationCall_0(be.ac.vub.simpleocl.OperationCall element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION_CALL__OPERATION_NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION_CALL__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("operationName");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION_CALL__OPERATION_NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION_CALL__OPERATION_NAME), element));
					out.print(" ");
				}
				printCountingMap.put("operationName", count - 1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_OperationCall_1(be.ac.vub.simpleocl.OperationCall element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION_CALL__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_OperationCall_1_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_OperationCall_1_0(be.ac.vub.simpleocl.OperationCall element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OPERATION_CALL__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_NavigationOrAttributeCall(be.ac.vub.simpleocl.NavigationOrAttributeCall element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(6);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NAVIGATION_OR_ATTRIBUTE_CALL__CALL_EXP));
		printCountingMap.put("callExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NAVIGATION_OR_ATTRIBUTE_CALL__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_NavigationOrAttributeCall_0(element, localtab, out, printCountingMap);
	}
	
	public void print_be_ac_vub_simpleocl_NavigationOrAttributeCall_0(be.ac.vub.simpleocl.NavigationOrAttributeCall element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NAVIGATION_OR_ATTRIBUTE_CALL__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NAVIGATION_OR_ATTRIBUTE_CALL__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NAVIGATION_OR_ATTRIBUTE_CALL__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.NAVIGATION_OR_ATTRIBUTE_CALL__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_IterateExp(be.ac.vub.simpleocl.IterateExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(8);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATE_EXP__CALL_EXP));
		printCountingMap.put("callExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATE_EXP__BODY));
		printCountingMap.put("body", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATE_EXP__ITERATORS));
		printCountingMap.put("iterators", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATE_EXP__RESULT));
		printCountingMap.put("result", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("->");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("iterate");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("iterators");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATE_EXP__ITERATORS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("iterators", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_IterateExp_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("result");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATE_EXP__RESULT));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("result", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("|");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("body");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATE_EXP__BODY));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("body", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_IterateExp_0(be.ac.vub.simpleocl.IterateExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("iterators");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATE_EXP__ITERATORS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("iterators", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_IteratorExp(be.ac.vub.simpleocl.IteratorExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(8);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR_EXP__CALL_EXP));
		printCountingMap.put("callExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR_EXP__BODY));
		printCountingMap.put("body", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR_EXP__ITERATORS));
		printCountingMap.put("iterators", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR_EXP__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("->");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_IteratorExp_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("iterators");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR_EXP__ITERATORS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("iterators", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_IteratorExp_1(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("|");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("body");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR_EXP__BODY));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("body", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_IteratorExp_0(be.ac.vub.simpleocl.IteratorExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR_EXP__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR_EXP__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR_EXP__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR_EXP__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_IteratorExp_1(be.ac.vub.simpleocl.IteratorExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("iterators");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR_EXP__ITERATORS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("iterators", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_Iterator(be.ac.vub.simpleocl.Iterator element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(8);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR__VAR_NAME));
		printCountingMap.put("varName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR__VARIABLE_EXP));
		printCountingMap.put("variableExp", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR__LOOP_EXPR));
		printCountingMap.put("loopExpr", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("varName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR__VAR_NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR__VAR_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("varName", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_Iterator_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_be_ac_vub_simpleocl_Iterator_0(be.ac.vub.simpleocl.Iterator element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("type");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ITERATOR__TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("type", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_CollectionOperationCall(be.ac.vub.simpleocl.CollectionOperationCall element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__CALL_EXP));
		printCountingMap.put("callExp", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__ARGUMENTS));
		printCountingMap.put("arguments", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("->");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_CollectionOperationCall_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_CollectionOperationCall_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_CollectionOperationCall_0(be.ac.vub.simpleocl.CollectionOperationCall element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__OPERATION_NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("operationName");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__OPERATION_NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__OPERATION_NAME), element));
					out.print(" ");
				}
				printCountingMap.put("operationName", count - 1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_CollectionOperationCall_1(be.ac.vub.simpleocl.CollectionOperationCall element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_CollectionOperationCall_1_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_CollectionOperationCall_1_0(be.ac.vub.simpleocl.CollectionOperationCall element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_CollectionType(be.ac.vub.simpleocl.CollectionType element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(18);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__ELEMENT_TYPE));
		printCountingMap.put("elementType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("COLLECTIONTYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elementType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.COLLECTION_TYPE__ELEMENT_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elementType", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_StringType(be.ac.vub.simpleocl.StringType element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("STRINGTYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.STRING_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_BooleanType(be.ac.vub.simpleocl.BooleanType element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("BOOLEANTYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_IntegerType(be.ac.vub.simpleocl.IntegerType element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("INTEGERTYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.INTEGER_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_RealType(be.ac.vub.simpleocl.RealType element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("REALTYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.REAL_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_BagType(be.ac.vub.simpleocl.BagType element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(18);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__ELEMENT_TYPE));
		printCountingMap.put("elementType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("BAGTYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elementType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.BAG_TYPE__ELEMENT_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elementType", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_OrderedSetType(be.ac.vub.simpleocl.OrderedSetType element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(18);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__ELEMENT_TYPE));
		printCountingMap.put("elementType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("ORDEREDSETTYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elementType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__ELEMENT_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elementType", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_SequenceType(be.ac.vub.simpleocl.SequenceType element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(18);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__ELEMENT_TYPE));
		printCountingMap.put("elementType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SEQUENCETYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elementType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__ELEMENT_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elementType", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_SetType(be.ac.vub.simpleocl.SetType element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(18);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__ELEMENT_TYPE));
		printCountingMap.put("elementType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SETTYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elementType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.SET_TYPE__ELEMENT_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elementType", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_OclAnyType(be.ac.vub.simpleocl.OclAnyType element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("OCLANYTYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_TupleType(be.ac.vub.simpleocl.TupleType element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(18);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__ATTRIBUTES));
		printCountingMap.put("attributes", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TUPLETYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_TupleType_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_be_ac_vub_simpleocl_TupleType_0(be.ac.vub.simpleocl.TupleType element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("attributes");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__ATTRIBUTES));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("attributes", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_TupleType_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_TupleType_0_0(be.ac.vub.simpleocl.TupleType element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("attributes");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE__ATTRIBUTES));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("attributes", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_TupleTypeAttribute(be.ac.vub.simpleocl.TupleTypeAttribute element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE));
		printCountingMap.put("tupleType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_TupleTypeAttribute_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("type");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("type", count - 1);
		}
	}
	
	public void print_be_ac_vub_simpleocl_TupleTypeAttribute_0(be.ac.vub.simpleocl.TupleTypeAttribute element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_OclModelElement(be.ac.vub.simpleocl.OclModelElement element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(18);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__MODEL));
		printCountingMap.put("model", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("model");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__MODEL));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOclModelElementModelReferenceResolver().deResolve((be.ac.vub.simpleocl.OclModel) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__MODEL)), element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__MODEL), element));
				out.print(" ");
			}
			printCountingMap.put("model", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("!");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_be_ac_vub_simpleocl_OclModelElement_0(element, localtab, out, printCountingMap);
	}
	
	public void print_be_ac_vub_simpleocl_OclModelElement_0(be.ac.vub.simpleocl.OclModelElement element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"name"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderInQuotes)
				count = printCountingMap.get("name");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__NAME));
					if (o != null) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__NAME), element));
						out.print(" ");
					}
					printCountingMap.put("name", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
			count = printCountingMap.get("name");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__NAME));
				if (o != null) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("TEXT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__NAME), element));
					out.print(" ");
				}
				printCountingMap.put("name", count - 1);
			}
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_MapType(be.ac.vub.simpleocl.MapType element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(19);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__VALUE_TYPE));
		printCountingMap.put("valueType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__KEY_TYPE));
		printCountingMap.put("keyType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("MAPTYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("keyType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__KEY_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("keyType", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("valueType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.MAP_TYPE__VALUE_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("valueType", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_be_ac_vub_simpleocl_LambdaType(be.ac.vub.simpleocl.LambdaType element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(19);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__RETURN_TYPE));
		printCountingMap.put("returnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__ARGUMENT_TYPES));
		printCountingMap.put("argumentTypes", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("LAMBDATYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_be_ac_vub_simpleocl_LambdaType_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("returnType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__RETURN_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("returnType", count - 1);
		}
	}
	
	public void print_be_ac_vub_simpleocl_LambdaType_0(be.ac.vub.simpleocl.LambdaType element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("argumentTypes");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__ARGUMENT_TYPES));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("argumentTypes", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_be_ac_vub_simpleocl_LambdaType_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_be_ac_vub_simpleocl_LambdaType_0_0(be.ac.vub.simpleocl.LambdaType element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("argumentTypes");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.LAMBDA_TYPE__ARGUMENT_TYPES));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("argumentTypes", count - 1);
		}
	}
	
	
	public void print_be_ac_vub_simpleocl_EnvType(be.ac.vub.simpleocl.EnvType element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(17);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__DEFINITIONS));
		printCountingMap.put("definitions", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__MAP_TYPE2));
		printCountingMap.put("mapType2", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__MAP_TYPE));
		printCountingMap.put("mapType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__COLLECTION_TYPES));
		printCountingMap.put("collectionTypes", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__TUPLE_TYPE_ATTRIBUTE));
		printCountingMap.put("tupleTypeAttribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__LAMBDA_RETURN_TYPE));
		printCountingMap.put("lambdaReturnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__LAMBDA_ARG_TYPE));
		printCountingMap.put("lambdaArgType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__STATIC_PROPERTY_CALL));
		printCountingMap.put("staticPropertyCall", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__NAME));
			if (o != null) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver = tokenResolverFactory.createTokenResolver("ENVTYPE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(be.ac.vub.simpleocl.SimpleoclPackage.ENV_TYPE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
	}
	
	
}
