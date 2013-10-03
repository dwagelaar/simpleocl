/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.util;

/**
 * This class provides basic infrastructure to interpret models. To implement
 * concrete interpreters, subclass this abstract interpreter and override the
 * interprete_* methods. The interpretation can be customized by binding the two
 * type parameters (ResultType, ContextType). The former is returned by all
 * interprete_* methods, while the latter is passed from method to method while
 * traversing the model. The concrete traversal strategy can also be exchanged.
 * One can use a static traversal strategy by pushing all objects to interpret on
 * the interpretation stack (using addObjectToInterprete()) before calling
 * interprete(). Alternatively, the traversal strategy can be dynamic by pushing
 * objects on the interpretation stack during interpretation.
 */
public class AbstractSimpleoclInterpreter<ResultType, ContextType> {
	
	private java.util.Stack<org.eclipse.emf.ecore.EObject> interpretationStack = new java.util.Stack<org.eclipse.emf.ecore.EObject>();
	private java.util.List<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclInterpreterListener> listeners = new java.util.ArrayList<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclInterpreterListener>();
	private org.eclipse.emf.ecore.EObject nextObjectToInterprete;
	private Object currentContext;
	
	public ResultType interprete(ContextType context) {
		ResultType result = null;
		org.eclipse.emf.ecore.EObject next = null;
		currentContext = context;
		while (!interpretationStack.empty()) {
			try {
				next = interpretationStack.pop();
			} catch (java.util.EmptyStackException ese) {
				// this can happen when the interpreter was terminated between the call to empty()
				// and pop()
				break;
			}
			nextObjectToInterprete = next;
			notifyListeners(next);
			result = interprete(next, context);
			if (!continueInterpretation(context, result)) {
				break;
			}
		}
		currentContext = null;
		return result;
	}
	
	/**
	 * Override this method to stop the overall interpretation depending on the result
	 * of the interpretation of a single model elements.
	 */
	public boolean continueInterpretation(ContextType context, ResultType result) {
		return true;
	}
	
	public ResultType interprete(org.eclipse.emf.ecore.EObject object, ContextType context) {
		ResultType result = null;
		if (object instanceof org.eclipselabs.simpleocl.Module) {
			result = interprete_org_eclipselabs_simpleocl_Module((org.eclipselabs.simpleocl.Module) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.NamedElement) {
			result = interprete_org_eclipselabs_simpleocl_NamedElement((org.eclipselabs.simpleocl.NamedElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.ModuleElement) {
			result = interprete_org_eclipselabs_simpleocl_ModuleElement((org.eclipselabs.simpleocl.ModuleElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.Import) {
			result = interprete_org_eclipselabs_simpleocl_Import((org.eclipselabs.simpleocl.Import) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.VariableExp) {
			result = interprete_org_eclipselabs_simpleocl_VariableExp((org.eclipselabs.simpleocl.VariableExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.SuperExp) {
			result = interprete_org_eclipselabs_simpleocl_SuperExp((org.eclipselabs.simpleocl.SuperExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.SelfExp) {
			result = interprete_org_eclipselabs_simpleocl_SelfExp((org.eclipselabs.simpleocl.SelfExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.EnvExp) {
			result = interprete_org_eclipselabs_simpleocl_EnvExp((org.eclipselabs.simpleocl.EnvExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.StringExp) {
			result = interprete_org_eclipselabs_simpleocl_StringExp((org.eclipselabs.simpleocl.StringExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.BooleanExp) {
			result = interprete_org_eclipselabs_simpleocl_BooleanExp((org.eclipselabs.simpleocl.BooleanExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.RealExp) {
			result = interprete_org_eclipselabs_simpleocl_RealExp((org.eclipselabs.simpleocl.RealExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.IntegerExp) {
			result = interprete_org_eclipselabs_simpleocl_IntegerExp((org.eclipselabs.simpleocl.IntegerExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.NumericExp) {
			result = interprete_org_eclipselabs_simpleocl_NumericExp((org.eclipselabs.simpleocl.NumericExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.PrimitiveExp) {
			result = interprete_org_eclipselabs_simpleocl_PrimitiveExp((org.eclipselabs.simpleocl.PrimitiveExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.BagExp) {
			result = interprete_org_eclipselabs_simpleocl_BagExp((org.eclipselabs.simpleocl.BagExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OrderedSetExp) {
			result = interprete_org_eclipselabs_simpleocl_OrderedSetExp((org.eclipselabs.simpleocl.OrderedSetExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.CollectionExp) {
			result = interprete_org_eclipselabs_simpleocl_CollectionExp((org.eclipselabs.simpleocl.CollectionExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OclExpression) {
			result = interprete_org_eclipselabs_simpleocl_OclExpression((org.eclipselabs.simpleocl.OclExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.CollectionRange) {
			result = interprete_org_eclipselabs_simpleocl_CollectionRange((org.eclipselabs.simpleocl.CollectionRange) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.CollectionItem) {
			result = interprete_org_eclipselabs_simpleocl_CollectionItem((org.eclipselabs.simpleocl.CollectionItem) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.CollectionPart) {
			result = interprete_org_eclipselabs_simpleocl_CollectionPart((org.eclipselabs.simpleocl.CollectionPart) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.SequenceExp) {
			result = interprete_org_eclipselabs_simpleocl_SequenceExp((org.eclipselabs.simpleocl.SequenceExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.SetExp) {
			result = interprete_org_eclipselabs_simpleocl_SetExp((org.eclipselabs.simpleocl.SetExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.TupleExp) {
			result = interprete_org_eclipselabs_simpleocl_TupleExp((org.eclipselabs.simpleocl.TupleExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.TuplePart) {
			result = interprete_org_eclipselabs_simpleocl_TuplePart((org.eclipselabs.simpleocl.TuplePart) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.MapExp) {
			result = interprete_org_eclipselabs_simpleocl_MapExp((org.eclipselabs.simpleocl.MapExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.MapElement) {
			result = interprete_org_eclipselabs_simpleocl_MapElement((org.eclipselabs.simpleocl.MapElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.EnumLiteralExp) {
			result = interprete_org_eclipselabs_simpleocl_EnumLiteralExp((org.eclipselabs.simpleocl.EnumLiteralExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OclUndefinedExp) {
			result = interprete_org_eclipselabs_simpleocl_OclUndefinedExp((org.eclipselabs.simpleocl.OclUndefinedExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.StaticPropertyCallExp) {
			result = interprete_org_eclipselabs_simpleocl_StaticPropertyCallExp((org.eclipselabs.simpleocl.StaticPropertyCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.StaticNavigationOrAttributeCall) {
			result = interprete_org_eclipselabs_simpleocl_StaticNavigationOrAttributeCall((org.eclipselabs.simpleocl.StaticNavigationOrAttributeCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.StaticOperationCall) {
			result = interprete_org_eclipselabs_simpleocl_StaticOperationCall((org.eclipselabs.simpleocl.StaticOperationCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.StaticPropertyCall) {
			result = interprete_org_eclipselabs_simpleocl_StaticPropertyCall((org.eclipselabs.simpleocl.StaticPropertyCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.PropertyCallExp) {
			result = interprete_org_eclipselabs_simpleocl_PropertyCallExp((org.eclipselabs.simpleocl.PropertyCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.NavigationOrAttributeCall) {
			result = interprete_org_eclipselabs_simpleocl_NavigationOrAttributeCall((org.eclipselabs.simpleocl.NavigationOrAttributeCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OperationCall) {
			result = interprete_org_eclipselabs_simpleocl_OperationCall((org.eclipselabs.simpleocl.OperationCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.PropertyCall) {
			result = interprete_org_eclipselabs_simpleocl_PropertyCall((org.eclipselabs.simpleocl.PropertyCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.NotOpCallExp) {
			result = interprete_org_eclipselabs_simpleocl_NotOpCallExp((org.eclipselabs.simpleocl.NotOpCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.RelOpCallExp) {
			result = interprete_org_eclipselabs_simpleocl_RelOpCallExp((org.eclipselabs.simpleocl.RelOpCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.EqOpCallExp) {
			result = interprete_org_eclipselabs_simpleocl_EqOpCallExp((org.eclipselabs.simpleocl.EqOpCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.AddOpCallExp) {
			result = interprete_org_eclipselabs_simpleocl_AddOpCallExp((org.eclipselabs.simpleocl.AddOpCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.IntOpCallExp) {
			result = interprete_org_eclipselabs_simpleocl_IntOpCallExp((org.eclipselabs.simpleocl.IntOpCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.MulOpCallExp) {
			result = interprete_org_eclipselabs_simpleocl_MulOpCallExp((org.eclipselabs.simpleocl.MulOpCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OperatorCallExp) {
			result = interprete_org_eclipselabs_simpleocl_OperatorCallExp((org.eclipselabs.simpleocl.OperatorCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.LambdaCallExp) {
			result = interprete_org_eclipselabs_simpleocl_LambdaCallExp((org.eclipselabs.simpleocl.LambdaCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.BraceExp) {
			result = interprete_org_eclipselabs_simpleocl_BraceExp((org.eclipselabs.simpleocl.BraceExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.CollectionOperationCall) {
			result = interprete_org_eclipselabs_simpleocl_CollectionOperationCall((org.eclipselabs.simpleocl.CollectionOperationCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.IterateExp) {
			result = interprete_org_eclipselabs_simpleocl_IterateExp((org.eclipselabs.simpleocl.IterateExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.IteratorExp) {
			result = interprete_org_eclipselabs_simpleocl_IteratorExp((org.eclipselabs.simpleocl.IteratorExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.LoopExp) {
			result = interprete_org_eclipselabs_simpleocl_LoopExp((org.eclipselabs.simpleocl.LoopExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.LetExp) {
			result = interprete_org_eclipselabs_simpleocl_LetExp((org.eclipselabs.simpleocl.LetExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.IfExp) {
			result = interprete_org_eclipselabs_simpleocl_IfExp((org.eclipselabs.simpleocl.IfExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.LocalVariable) {
			result = interprete_org_eclipselabs_simpleocl_LocalVariable((org.eclipselabs.simpleocl.LocalVariable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.Iterator) {
			result = interprete_org_eclipselabs_simpleocl_Iterator((org.eclipselabs.simpleocl.Iterator) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.Parameter) {
			result = interprete_org_eclipselabs_simpleocl_Parameter((org.eclipselabs.simpleocl.Parameter) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.VariableDeclaration) {
			result = interprete_org_eclipselabs_simpleocl_VariableDeclaration((org.eclipselabs.simpleocl.VariableDeclaration) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.CollectionType) {
			result = interprete_org_eclipselabs_simpleocl_CollectionType((org.eclipselabs.simpleocl.CollectionType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.StringType) {
			result = interprete_org_eclipselabs_simpleocl_StringType((org.eclipselabs.simpleocl.StringType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.BooleanType) {
			result = interprete_org_eclipselabs_simpleocl_BooleanType((org.eclipselabs.simpleocl.BooleanType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.IntegerType) {
			result = interprete_org_eclipselabs_simpleocl_IntegerType((org.eclipselabs.simpleocl.IntegerType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.RealType) {
			result = interprete_org_eclipselabs_simpleocl_RealType((org.eclipselabs.simpleocl.RealType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.NumericType) {
			result = interprete_org_eclipselabs_simpleocl_NumericType((org.eclipselabs.simpleocl.NumericType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.Primitive) {
			result = interprete_org_eclipselabs_simpleocl_Primitive((org.eclipselabs.simpleocl.Primitive) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OclType) {
			result = interprete_org_eclipselabs_simpleocl_OclType((org.eclipselabs.simpleocl.OclType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OclModelElementExp) {
			result = interprete_org_eclipselabs_simpleocl_OclModelElementExp((org.eclipselabs.simpleocl.OclModelElementExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.BagType) {
			result = interprete_org_eclipselabs_simpleocl_BagType((org.eclipselabs.simpleocl.BagType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OrderedSetType) {
			result = interprete_org_eclipselabs_simpleocl_OrderedSetType((org.eclipselabs.simpleocl.OrderedSetType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.SequenceType) {
			result = interprete_org_eclipselabs_simpleocl_SequenceType((org.eclipselabs.simpleocl.SequenceType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.SetType) {
			result = interprete_org_eclipselabs_simpleocl_SetType((org.eclipselabs.simpleocl.SetType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OclAnyType) {
			result = interprete_org_eclipselabs_simpleocl_OclAnyType((org.eclipselabs.simpleocl.OclAnyType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.TupleType) {
			result = interprete_org_eclipselabs_simpleocl_TupleType((org.eclipselabs.simpleocl.TupleType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.TupleTypeAttribute) {
			result = interprete_org_eclipselabs_simpleocl_TupleTypeAttribute((org.eclipselabs.simpleocl.TupleTypeAttribute) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OclModelElement) {
			result = interprete_org_eclipselabs_simpleocl_OclModelElement((org.eclipselabs.simpleocl.OclModelElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.MapType) {
			result = interprete_org_eclipselabs_simpleocl_MapType((org.eclipselabs.simpleocl.MapType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.LambdaType) {
			result = interprete_org_eclipselabs_simpleocl_LambdaType((org.eclipselabs.simpleocl.LambdaType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.EnvType) {
			result = interprete_org_eclipselabs_simpleocl_EnvType((org.eclipselabs.simpleocl.EnvType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OclFeatureDefinition) {
			result = interprete_org_eclipselabs_simpleocl_OclFeatureDefinition((org.eclipselabs.simpleocl.OclFeatureDefinition) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OclContextDefinition) {
			result = interprete_org_eclipselabs_simpleocl_OclContextDefinition((org.eclipselabs.simpleocl.OclContextDefinition) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.Attribute) {
			result = interprete_org_eclipselabs_simpleocl_Attribute((org.eclipselabs.simpleocl.Attribute) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.Operation) {
			result = interprete_org_eclipselabs_simpleocl_Operation((org.eclipselabs.simpleocl.Operation) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OclFeature) {
			result = interprete_org_eclipselabs_simpleocl_OclFeature((org.eclipselabs.simpleocl.OclFeature) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OclMetamodel) {
			result = interprete_org_eclipselabs_simpleocl_OclMetamodel((org.eclipselabs.simpleocl.OclMetamodel) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OclInstanceModel) {
			result = interprete_org_eclipselabs_simpleocl_OclInstanceModel((org.eclipselabs.simpleocl.OclInstanceModel) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.OclModel) {
			result = interprete_org_eclipselabs_simpleocl_OclModel((org.eclipselabs.simpleocl.OclModel) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.eclipselabs.simpleocl.LocatedElement) {
			result = interprete_org_eclipselabs_simpleocl_LocatedElement((org.eclipselabs.simpleocl.LocatedElement) object, context);
		}
		if (result != null) {
			return result;
		}
		return result;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_LocatedElement(org.eclipselabs.simpleocl.LocatedElement locatedElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_NamedElement(org.eclipselabs.simpleocl.NamedElement namedElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_Module(org.eclipselabs.simpleocl.Module module, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_ModuleElement(org.eclipselabs.simpleocl.ModuleElement moduleElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_Import(org.eclipselabs.simpleocl.Import _import, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OclExpression(org.eclipselabs.simpleocl.OclExpression oclExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_VariableExp(org.eclipselabs.simpleocl.VariableExp variableExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_SuperExp(org.eclipselabs.simpleocl.SuperExp superExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_SelfExp(org.eclipselabs.simpleocl.SelfExp selfExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_EnvExp(org.eclipselabs.simpleocl.EnvExp envExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_PrimitiveExp(org.eclipselabs.simpleocl.PrimitiveExp primitiveExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_StringExp(org.eclipselabs.simpleocl.StringExp stringExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_BooleanExp(org.eclipselabs.simpleocl.BooleanExp booleanExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_NumericExp(org.eclipselabs.simpleocl.NumericExp numericExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_RealExp(org.eclipselabs.simpleocl.RealExp realExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_IntegerExp(org.eclipselabs.simpleocl.IntegerExp integerExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_CollectionExp(org.eclipselabs.simpleocl.CollectionExp collectionExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_CollectionPart(org.eclipselabs.simpleocl.CollectionPart collectionPart, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_CollectionRange(org.eclipselabs.simpleocl.CollectionRange collectionRange, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_CollectionItem(org.eclipselabs.simpleocl.CollectionItem collectionItem, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_BagExp(org.eclipselabs.simpleocl.BagExp bagExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OrderedSetExp(org.eclipselabs.simpleocl.OrderedSetExp orderedSetExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_SequenceExp(org.eclipselabs.simpleocl.SequenceExp sequenceExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_SetExp(org.eclipselabs.simpleocl.SetExp setExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_TupleExp(org.eclipselabs.simpleocl.TupleExp tupleExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_TuplePart(org.eclipselabs.simpleocl.TuplePart tuplePart, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_MapExp(org.eclipselabs.simpleocl.MapExp mapExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_MapElement(org.eclipselabs.simpleocl.MapElement mapElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_EnumLiteralExp(org.eclipselabs.simpleocl.EnumLiteralExp enumLiteralExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OclUndefinedExp(org.eclipselabs.simpleocl.OclUndefinedExp oclUndefinedExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_StaticPropertyCallExp(org.eclipselabs.simpleocl.StaticPropertyCallExp staticPropertyCallExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_StaticPropertyCall(org.eclipselabs.simpleocl.StaticPropertyCall staticPropertyCall, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_StaticNavigationOrAttributeCall(org.eclipselabs.simpleocl.StaticNavigationOrAttributeCall staticNavigationOrAttributeCall, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_StaticOperationCall(org.eclipselabs.simpleocl.StaticOperationCall staticOperationCall, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_PropertyCallExp(org.eclipselabs.simpleocl.PropertyCallExp propertyCallExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_PropertyCall(org.eclipselabs.simpleocl.PropertyCall propertyCall, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_NavigationOrAttributeCall(org.eclipselabs.simpleocl.NavigationOrAttributeCall navigationOrAttributeCall, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OperationCall(org.eclipselabs.simpleocl.OperationCall operationCall, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OperatorCallExp(org.eclipselabs.simpleocl.OperatorCallExp operatorCallExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_NotOpCallExp(org.eclipselabs.simpleocl.NotOpCallExp notOpCallExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_RelOpCallExp(org.eclipselabs.simpleocl.RelOpCallExp relOpCallExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_EqOpCallExp(org.eclipselabs.simpleocl.EqOpCallExp eqOpCallExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_AddOpCallExp(org.eclipselabs.simpleocl.AddOpCallExp addOpCallExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_IntOpCallExp(org.eclipselabs.simpleocl.IntOpCallExp intOpCallExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_MulOpCallExp(org.eclipselabs.simpleocl.MulOpCallExp mulOpCallExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_LambdaCallExp(org.eclipselabs.simpleocl.LambdaCallExp lambdaCallExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_BraceExp(org.eclipselabs.simpleocl.BraceExp braceExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_CollectionOperationCall(org.eclipselabs.simpleocl.CollectionOperationCall collectionOperationCall, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_LoopExp(org.eclipselabs.simpleocl.LoopExp loopExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_IterateExp(org.eclipselabs.simpleocl.IterateExp iterateExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_IteratorExp(org.eclipselabs.simpleocl.IteratorExp iteratorExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_LetExp(org.eclipselabs.simpleocl.LetExp letExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_IfExp(org.eclipselabs.simpleocl.IfExp ifExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_VariableDeclaration(org.eclipselabs.simpleocl.VariableDeclaration variableDeclaration, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_LocalVariable(org.eclipselabs.simpleocl.LocalVariable localVariable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_Iterator(org.eclipselabs.simpleocl.Iterator iterator, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_Parameter(org.eclipselabs.simpleocl.Parameter parameter, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_CollectionType(org.eclipselabs.simpleocl.CollectionType collectionType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OclType(org.eclipselabs.simpleocl.OclType oclType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OclModelElementExp(org.eclipselabs.simpleocl.OclModelElementExp oclModelElementExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_Primitive(org.eclipselabs.simpleocl.Primitive primitive, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_StringType(org.eclipselabs.simpleocl.StringType stringType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_BooleanType(org.eclipselabs.simpleocl.BooleanType booleanType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_NumericType(org.eclipselabs.simpleocl.NumericType numericType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_IntegerType(org.eclipselabs.simpleocl.IntegerType integerType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_RealType(org.eclipselabs.simpleocl.RealType realType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_BagType(org.eclipselabs.simpleocl.BagType bagType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OrderedSetType(org.eclipselabs.simpleocl.OrderedSetType orderedSetType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_SequenceType(org.eclipselabs.simpleocl.SequenceType sequenceType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_SetType(org.eclipselabs.simpleocl.SetType setType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OclAnyType(org.eclipselabs.simpleocl.OclAnyType oclAnyType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_TupleType(org.eclipselabs.simpleocl.TupleType tupleType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_TupleTypeAttribute(org.eclipselabs.simpleocl.TupleTypeAttribute tupleTypeAttribute, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OclModelElement(org.eclipselabs.simpleocl.OclModelElement oclModelElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_MapType(org.eclipselabs.simpleocl.MapType mapType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_LambdaType(org.eclipselabs.simpleocl.LambdaType lambdaType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_EnvType(org.eclipselabs.simpleocl.EnvType envType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OclFeatureDefinition(org.eclipselabs.simpleocl.OclFeatureDefinition oclFeatureDefinition, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OclContextDefinition(org.eclipselabs.simpleocl.OclContextDefinition oclContextDefinition, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OclFeature(org.eclipselabs.simpleocl.OclFeature oclFeature, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_Attribute(org.eclipselabs.simpleocl.Attribute attribute, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_Operation(org.eclipselabs.simpleocl.Operation operation, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OclModel(org.eclipselabs.simpleocl.OclModel oclModel, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OclMetamodel(org.eclipselabs.simpleocl.OclMetamodel oclMetamodel, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_eclipselabs_simpleocl_OclInstanceModel(org.eclipselabs.simpleocl.OclInstanceModel oclInstanceModel, ContextType context) {
		return null;
	}
	
	private void notifyListeners(org.eclipse.emf.ecore.EObject element) {
		for (org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclInterpreterListener listener : listeners) {
			listener.handleInterpreteObject(element);
		}
	}
	
	/**
	 * Adds the given object to the interpretation stack. Attention: Objects that are
	 * added first, are interpret last.
	 */
	public void addObjectToInterprete(org.eclipse.emf.ecore.EObject object) {
		interpretationStack.push(object);
	}
	
	/**
	 * Adds the given collection of objects to the interpretation stack. Attention:
	 * Collections that are added first, are interpret last.
	 */
	public void addObjectsToInterprete(java.util.Collection<? extends org.eclipse.emf.ecore.EObject> objects) {
		for (org.eclipse.emf.ecore.EObject object : objects) {
			addObjectToInterprete(object);
		}
	}
	
	/**
	 * Adds the given collection of objects in reverse order to the interpretation
	 * stack.
	 */
	public void addObjectsToInterpreteInReverseOrder(java.util.Collection<? extends org.eclipse.emf.ecore.EObject> objects) {
		java.util.List<org.eclipse.emf.ecore.EObject> reverse = new java.util.ArrayList<org.eclipse.emf.ecore.EObject>(objects.size());
		reverse.addAll(objects);
		java.util.Collections.reverse(reverse);
		addObjectsToInterprete(reverse);
	}
	
	/**
	 * Adds the given object and all its children to the interpretation stack such
	 * that they are interpret in top down order.
	 */
	public void addObjectTreeToInterpreteTopDown(org.eclipse.emf.ecore.EObject root) {
		java.util.List<org.eclipse.emf.ecore.EObject> objects = new java.util.ArrayList<org.eclipse.emf.ecore.EObject>();
		objects.add(root);
		java.util.Iterator<org.eclipse.emf.ecore.EObject> it = root.eAllContents();
		while (it.hasNext()) {
			org.eclipse.emf.ecore.EObject eObject = (org.eclipse.emf.ecore.EObject) it.next();
			objects.add(eObject);
		}
		addObjectsToInterpreteInReverseOrder(objects);
	}
	
	public void addListener(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclInterpreterListener newListener) {
		listeners.add(newListener);
	}
	
	public boolean removeListener(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclInterpreterListener listener) {
		return listeners.remove(listener);
	}
	
	public org.eclipse.emf.ecore.EObject getNextObjectToInterprete() {
		return nextObjectToInterprete;
	}
	
	public java.util.Stack<org.eclipse.emf.ecore.EObject> getInterpretationStack() {
		return interpretationStack;
	}
	
	public void terminate() {
		interpretationStack.clear();
	}
	
	public Object getCurrentContext() {
		return currentContext;
	}
	
}
