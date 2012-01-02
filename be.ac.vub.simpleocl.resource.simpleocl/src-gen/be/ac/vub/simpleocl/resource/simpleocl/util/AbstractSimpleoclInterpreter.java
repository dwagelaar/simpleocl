/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.util;

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
	private java.util.List<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclInterpreterListener> listeners = new java.util.ArrayList<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclInterpreterListener>();
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
		if (object instanceof be.ac.vub.simpleocl.OclInstanceModel) {
			result = interprete_be_ac_vub_simpleocl_OclInstanceModel((be.ac.vub.simpleocl.OclInstanceModel) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OclMetamodel) {
			result = interprete_be_ac_vub_simpleocl_OclMetamodel((be.ac.vub.simpleocl.OclMetamodel) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OclModel) {
			result = interprete_be_ac_vub_simpleocl_OclModel((be.ac.vub.simpleocl.OclModel) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.Operation) {
			result = interprete_be_ac_vub_simpleocl_Operation((be.ac.vub.simpleocl.Operation) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.Attribute) {
			result = interprete_be_ac_vub_simpleocl_Attribute((be.ac.vub.simpleocl.Attribute) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OclFeature) {
			result = interprete_be_ac_vub_simpleocl_OclFeature((be.ac.vub.simpleocl.OclFeature) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OclContextDefinition) {
			result = interprete_be_ac_vub_simpleocl_OclContextDefinition((be.ac.vub.simpleocl.OclContextDefinition) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OclFeatureDefinition) {
			result = interprete_be_ac_vub_simpleocl_OclFeatureDefinition((be.ac.vub.simpleocl.OclFeatureDefinition) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.EnvType) {
			result = interprete_be_ac_vub_simpleocl_EnvType((be.ac.vub.simpleocl.EnvType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.LambdaType) {
			result = interprete_be_ac_vub_simpleocl_LambdaType((be.ac.vub.simpleocl.LambdaType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.MapType) {
			result = interprete_be_ac_vub_simpleocl_MapType((be.ac.vub.simpleocl.MapType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OclModelElement) {
			result = interprete_be_ac_vub_simpleocl_OclModelElement((be.ac.vub.simpleocl.OclModelElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.TupleTypeAttribute) {
			result = interprete_be_ac_vub_simpleocl_TupleTypeAttribute((be.ac.vub.simpleocl.TupleTypeAttribute) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.TupleType) {
			result = interprete_be_ac_vub_simpleocl_TupleType((be.ac.vub.simpleocl.TupleType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OclAnyType) {
			result = interprete_be_ac_vub_simpleocl_OclAnyType((be.ac.vub.simpleocl.OclAnyType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.SetType) {
			result = interprete_be_ac_vub_simpleocl_SetType((be.ac.vub.simpleocl.SetType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.SequenceType) {
			result = interprete_be_ac_vub_simpleocl_SequenceType((be.ac.vub.simpleocl.SequenceType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OrderedSetType) {
			result = interprete_be_ac_vub_simpleocl_OrderedSetType((be.ac.vub.simpleocl.OrderedSetType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.BagType) {
			result = interprete_be_ac_vub_simpleocl_BagType((be.ac.vub.simpleocl.BagType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.RealType) {
			result = interprete_be_ac_vub_simpleocl_RealType((be.ac.vub.simpleocl.RealType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.IntegerType) {
			result = interprete_be_ac_vub_simpleocl_IntegerType((be.ac.vub.simpleocl.IntegerType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.NumericType) {
			result = interprete_be_ac_vub_simpleocl_NumericType((be.ac.vub.simpleocl.NumericType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.BooleanType) {
			result = interprete_be_ac_vub_simpleocl_BooleanType((be.ac.vub.simpleocl.BooleanType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.StringType) {
			result = interprete_be_ac_vub_simpleocl_StringType((be.ac.vub.simpleocl.StringType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.Primitive) {
			result = interprete_be_ac_vub_simpleocl_Primitive((be.ac.vub.simpleocl.Primitive) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OclModelElementExp) {
			result = interprete_be_ac_vub_simpleocl_OclModelElementExp((be.ac.vub.simpleocl.OclModelElementExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.CollectionType) {
			result = interprete_be_ac_vub_simpleocl_CollectionType((be.ac.vub.simpleocl.CollectionType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OclType) {
			result = interprete_be_ac_vub_simpleocl_OclType((be.ac.vub.simpleocl.OclType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.Parameter) {
			result = interprete_be_ac_vub_simpleocl_Parameter((be.ac.vub.simpleocl.Parameter) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.Iterator) {
			result = interprete_be_ac_vub_simpleocl_Iterator((be.ac.vub.simpleocl.Iterator) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.LocalVariable) {
			result = interprete_be_ac_vub_simpleocl_LocalVariable((be.ac.vub.simpleocl.LocalVariable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.VariableDeclaration) {
			result = interprete_be_ac_vub_simpleocl_VariableDeclaration((be.ac.vub.simpleocl.VariableDeclaration) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.IfExp) {
			result = interprete_be_ac_vub_simpleocl_IfExp((be.ac.vub.simpleocl.IfExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.LetExp) {
			result = interprete_be_ac_vub_simpleocl_LetExp((be.ac.vub.simpleocl.LetExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.IteratorExp) {
			result = interprete_be_ac_vub_simpleocl_IteratorExp((be.ac.vub.simpleocl.IteratorExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.IterateExp) {
			result = interprete_be_ac_vub_simpleocl_IterateExp((be.ac.vub.simpleocl.IterateExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.LoopExp) {
			result = interprete_be_ac_vub_simpleocl_LoopExp((be.ac.vub.simpleocl.LoopExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.CollectionOperationCall) {
			result = interprete_be_ac_vub_simpleocl_CollectionOperationCall((be.ac.vub.simpleocl.CollectionOperationCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.BraceExp) {
			result = interprete_be_ac_vub_simpleocl_BraceExp((be.ac.vub.simpleocl.BraceExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.LambdaCallExp) {
			result = interprete_be_ac_vub_simpleocl_LambdaCallExp((be.ac.vub.simpleocl.LambdaCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.MulOpCallExp) {
			result = interprete_be_ac_vub_simpleocl_MulOpCallExp((be.ac.vub.simpleocl.MulOpCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.IntOpCallExp) {
			result = interprete_be_ac_vub_simpleocl_IntOpCallExp((be.ac.vub.simpleocl.IntOpCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.AddOpCallExp) {
			result = interprete_be_ac_vub_simpleocl_AddOpCallExp((be.ac.vub.simpleocl.AddOpCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.EqOpCallExp) {
			result = interprete_be_ac_vub_simpleocl_EqOpCallExp((be.ac.vub.simpleocl.EqOpCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.RelOpCallExp) {
			result = interprete_be_ac_vub_simpleocl_RelOpCallExp((be.ac.vub.simpleocl.RelOpCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.NotOpCallExp) {
			result = interprete_be_ac_vub_simpleocl_NotOpCallExp((be.ac.vub.simpleocl.NotOpCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OperatorCallExp) {
			result = interprete_be_ac_vub_simpleocl_OperatorCallExp((be.ac.vub.simpleocl.OperatorCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OperationCall) {
			result = interprete_be_ac_vub_simpleocl_OperationCall((be.ac.vub.simpleocl.OperationCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.NavigationOrAttributeCall) {
			result = interprete_be_ac_vub_simpleocl_NavigationOrAttributeCall((be.ac.vub.simpleocl.NavigationOrAttributeCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.PropertyCall) {
			result = interprete_be_ac_vub_simpleocl_PropertyCall((be.ac.vub.simpleocl.PropertyCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.PropertyCallExp) {
			result = interprete_be_ac_vub_simpleocl_PropertyCallExp((be.ac.vub.simpleocl.PropertyCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.StaticOperationCall) {
			result = interprete_be_ac_vub_simpleocl_StaticOperationCall((be.ac.vub.simpleocl.StaticOperationCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.StaticNavigationOrAttributeCall) {
			result = interprete_be_ac_vub_simpleocl_StaticNavigationOrAttributeCall((be.ac.vub.simpleocl.StaticNavigationOrAttributeCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.StaticPropertyCall) {
			result = interprete_be_ac_vub_simpleocl_StaticPropertyCall((be.ac.vub.simpleocl.StaticPropertyCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.StaticPropertyCallExp) {
			result = interprete_be_ac_vub_simpleocl_StaticPropertyCallExp((be.ac.vub.simpleocl.StaticPropertyCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OclUndefinedExp) {
			result = interprete_be_ac_vub_simpleocl_OclUndefinedExp((be.ac.vub.simpleocl.OclUndefinedExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.EnumLiteralExp) {
			result = interprete_be_ac_vub_simpleocl_EnumLiteralExp((be.ac.vub.simpleocl.EnumLiteralExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.MapElement) {
			result = interprete_be_ac_vub_simpleocl_MapElement((be.ac.vub.simpleocl.MapElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.MapExp) {
			result = interprete_be_ac_vub_simpleocl_MapExp((be.ac.vub.simpleocl.MapExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.TuplePart) {
			result = interprete_be_ac_vub_simpleocl_TuplePart((be.ac.vub.simpleocl.TuplePart) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.TupleExp) {
			result = interprete_be_ac_vub_simpleocl_TupleExp((be.ac.vub.simpleocl.TupleExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.SetExp) {
			result = interprete_be_ac_vub_simpleocl_SetExp((be.ac.vub.simpleocl.SetExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.SequenceExp) {
			result = interprete_be_ac_vub_simpleocl_SequenceExp((be.ac.vub.simpleocl.SequenceExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OrderedSetExp) {
			result = interprete_be_ac_vub_simpleocl_OrderedSetExp((be.ac.vub.simpleocl.OrderedSetExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.BagExp) {
			result = interprete_be_ac_vub_simpleocl_BagExp((be.ac.vub.simpleocl.BagExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.CollectionExp) {
			result = interprete_be_ac_vub_simpleocl_CollectionExp((be.ac.vub.simpleocl.CollectionExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.IntegerExp) {
			result = interprete_be_ac_vub_simpleocl_IntegerExp((be.ac.vub.simpleocl.IntegerExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.RealExp) {
			result = interprete_be_ac_vub_simpleocl_RealExp((be.ac.vub.simpleocl.RealExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.NumericExp) {
			result = interprete_be_ac_vub_simpleocl_NumericExp((be.ac.vub.simpleocl.NumericExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.BooleanExp) {
			result = interprete_be_ac_vub_simpleocl_BooleanExp((be.ac.vub.simpleocl.BooleanExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.StringExp) {
			result = interprete_be_ac_vub_simpleocl_StringExp((be.ac.vub.simpleocl.StringExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.PrimitiveExp) {
			result = interprete_be_ac_vub_simpleocl_PrimitiveExp((be.ac.vub.simpleocl.PrimitiveExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.EnvExp) {
			result = interprete_be_ac_vub_simpleocl_EnvExp((be.ac.vub.simpleocl.EnvExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.SelfExp) {
			result = interprete_be_ac_vub_simpleocl_SelfExp((be.ac.vub.simpleocl.SelfExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.SuperExp) {
			result = interprete_be_ac_vub_simpleocl_SuperExp((be.ac.vub.simpleocl.SuperExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.VariableExp) {
			result = interprete_be_ac_vub_simpleocl_VariableExp((be.ac.vub.simpleocl.VariableExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.OclExpression) {
			result = interprete_be_ac_vub_simpleocl_OclExpression((be.ac.vub.simpleocl.OclExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.Import) {
			result = interprete_be_ac_vub_simpleocl_Import((be.ac.vub.simpleocl.Import) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.Module) {
			result = interprete_be_ac_vub_simpleocl_Module((be.ac.vub.simpleocl.Module) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.NamedElement) {
			result = interprete_be_ac_vub_simpleocl_NamedElement((be.ac.vub.simpleocl.NamedElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof be.ac.vub.simpleocl.LocatedElement) {
			result = interprete_be_ac_vub_simpleocl_LocatedElement((be.ac.vub.simpleocl.LocatedElement) object, context);
		}
		if (result != null) {
			return result;
		}
		return result;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_LocatedElement(be.ac.vub.simpleocl.LocatedElement object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_NamedElement(be.ac.vub.simpleocl.NamedElement object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_Module(be.ac.vub.simpleocl.Module object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_Import(be.ac.vub.simpleocl.Import object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OclExpression(be.ac.vub.simpleocl.OclExpression object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_VariableExp(be.ac.vub.simpleocl.VariableExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_SuperExp(be.ac.vub.simpleocl.SuperExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_SelfExp(be.ac.vub.simpleocl.SelfExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_EnvExp(be.ac.vub.simpleocl.EnvExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_PrimitiveExp(be.ac.vub.simpleocl.PrimitiveExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_StringExp(be.ac.vub.simpleocl.StringExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_BooleanExp(be.ac.vub.simpleocl.BooleanExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_NumericExp(be.ac.vub.simpleocl.NumericExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_RealExp(be.ac.vub.simpleocl.RealExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_IntegerExp(be.ac.vub.simpleocl.IntegerExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_CollectionExp(be.ac.vub.simpleocl.CollectionExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_BagExp(be.ac.vub.simpleocl.BagExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OrderedSetExp(be.ac.vub.simpleocl.OrderedSetExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_SequenceExp(be.ac.vub.simpleocl.SequenceExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_SetExp(be.ac.vub.simpleocl.SetExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_TupleExp(be.ac.vub.simpleocl.TupleExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_TuplePart(be.ac.vub.simpleocl.TuplePart object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_MapExp(be.ac.vub.simpleocl.MapExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_MapElement(be.ac.vub.simpleocl.MapElement object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_EnumLiteralExp(be.ac.vub.simpleocl.EnumLiteralExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OclUndefinedExp(be.ac.vub.simpleocl.OclUndefinedExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_StaticPropertyCallExp(be.ac.vub.simpleocl.StaticPropertyCallExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_StaticPropertyCall(be.ac.vub.simpleocl.StaticPropertyCall object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_StaticNavigationOrAttributeCall(be.ac.vub.simpleocl.StaticNavigationOrAttributeCall object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_StaticOperationCall(be.ac.vub.simpleocl.StaticOperationCall object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_PropertyCallExp(be.ac.vub.simpleocl.PropertyCallExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_PropertyCall(be.ac.vub.simpleocl.PropertyCall object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_NavigationOrAttributeCall(be.ac.vub.simpleocl.NavigationOrAttributeCall object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OperationCall(be.ac.vub.simpleocl.OperationCall object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OperatorCallExp(be.ac.vub.simpleocl.OperatorCallExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_NotOpCallExp(be.ac.vub.simpleocl.NotOpCallExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_RelOpCallExp(be.ac.vub.simpleocl.RelOpCallExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_EqOpCallExp(be.ac.vub.simpleocl.EqOpCallExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_AddOpCallExp(be.ac.vub.simpleocl.AddOpCallExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_IntOpCallExp(be.ac.vub.simpleocl.IntOpCallExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_MulOpCallExp(be.ac.vub.simpleocl.MulOpCallExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_LambdaCallExp(be.ac.vub.simpleocl.LambdaCallExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_BraceExp(be.ac.vub.simpleocl.BraceExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_CollectionOperationCall(be.ac.vub.simpleocl.CollectionOperationCall object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_LoopExp(be.ac.vub.simpleocl.LoopExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_IterateExp(be.ac.vub.simpleocl.IterateExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_IteratorExp(be.ac.vub.simpleocl.IteratorExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_LetExp(be.ac.vub.simpleocl.LetExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_IfExp(be.ac.vub.simpleocl.IfExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_VariableDeclaration(be.ac.vub.simpleocl.VariableDeclaration object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_LocalVariable(be.ac.vub.simpleocl.LocalVariable object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_Iterator(be.ac.vub.simpleocl.Iterator object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_Parameter(be.ac.vub.simpleocl.Parameter object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_CollectionType(be.ac.vub.simpleocl.CollectionType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OclType(be.ac.vub.simpleocl.OclType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OclModelElementExp(be.ac.vub.simpleocl.OclModelElementExp object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_Primitive(be.ac.vub.simpleocl.Primitive object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_StringType(be.ac.vub.simpleocl.StringType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_BooleanType(be.ac.vub.simpleocl.BooleanType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_NumericType(be.ac.vub.simpleocl.NumericType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_IntegerType(be.ac.vub.simpleocl.IntegerType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_RealType(be.ac.vub.simpleocl.RealType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_BagType(be.ac.vub.simpleocl.BagType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OrderedSetType(be.ac.vub.simpleocl.OrderedSetType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_SequenceType(be.ac.vub.simpleocl.SequenceType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_SetType(be.ac.vub.simpleocl.SetType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OclAnyType(be.ac.vub.simpleocl.OclAnyType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_TupleType(be.ac.vub.simpleocl.TupleType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_TupleTypeAttribute(be.ac.vub.simpleocl.TupleTypeAttribute object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OclModelElement(be.ac.vub.simpleocl.OclModelElement object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_MapType(be.ac.vub.simpleocl.MapType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_LambdaType(be.ac.vub.simpleocl.LambdaType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_EnvType(be.ac.vub.simpleocl.EnvType object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OclFeatureDefinition(be.ac.vub.simpleocl.OclFeatureDefinition object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OclContextDefinition(be.ac.vub.simpleocl.OclContextDefinition object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OclFeature(be.ac.vub.simpleocl.OclFeature object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_Attribute(be.ac.vub.simpleocl.Attribute object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_Operation(be.ac.vub.simpleocl.Operation object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OclModel(be.ac.vub.simpleocl.OclModel object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OclMetamodel(be.ac.vub.simpleocl.OclMetamodel object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_be_ac_vub_simpleocl_OclInstanceModel(be.ac.vub.simpleocl.OclInstanceModel object, ContextType context) {
		return null;
	}
	
	private void notifyListeners(org.eclipse.emf.ecore.EObject element) {
		for (be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclInterpreterListener listener : listeners) {
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
	
	public void addListener(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclInterpreterListener newListener) {
		listeners.add(newListener);
	}
	
	public boolean removeListener(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclInterpreterListener listener) {
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
