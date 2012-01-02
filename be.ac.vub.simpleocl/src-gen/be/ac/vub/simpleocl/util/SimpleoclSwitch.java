/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import be.ac.vub.simpleocl.AddOpCallExp;
import be.ac.vub.simpleocl.Attribute;
import be.ac.vub.simpleocl.BagExp;
import be.ac.vub.simpleocl.BagType;
import be.ac.vub.simpleocl.BooleanExp;
import be.ac.vub.simpleocl.BooleanType;
import be.ac.vub.simpleocl.BraceExp;
import be.ac.vub.simpleocl.CollectionExp;
import be.ac.vub.simpleocl.CollectionOperationCall;
import be.ac.vub.simpleocl.CollectionType;
import be.ac.vub.simpleocl.EnumLiteralExp;
import be.ac.vub.simpleocl.EnvExp;
import be.ac.vub.simpleocl.EnvType;
import be.ac.vub.simpleocl.EqOpCallExp;
import be.ac.vub.simpleocl.IfExp;
import be.ac.vub.simpleocl.Import;
import be.ac.vub.simpleocl.IntOpCallExp;
import be.ac.vub.simpleocl.IntegerExp;
import be.ac.vub.simpleocl.IntegerType;
import be.ac.vub.simpleocl.IterateExp;
import be.ac.vub.simpleocl.Iterator;
import be.ac.vub.simpleocl.IteratorExp;
import be.ac.vub.simpleocl.LambdaCallExp;
import be.ac.vub.simpleocl.LambdaType;
import be.ac.vub.simpleocl.LetExp;
import be.ac.vub.simpleocl.LocalVariable;
import be.ac.vub.simpleocl.LocatedElement;
import be.ac.vub.simpleocl.LoopExp;
import be.ac.vub.simpleocl.MapElement;
import be.ac.vub.simpleocl.MapExp;
import be.ac.vub.simpleocl.MapType;
import be.ac.vub.simpleocl.Module;
import be.ac.vub.simpleocl.MulOpCallExp;
import be.ac.vub.simpleocl.NamedElement;
import be.ac.vub.simpleocl.NavigationOrAttributeCall;
import be.ac.vub.simpleocl.NotOpCallExp;
import be.ac.vub.simpleocl.NumericExp;
import be.ac.vub.simpleocl.NumericType;
import be.ac.vub.simpleocl.OclAnyType;
import be.ac.vub.simpleocl.OclContextDefinition;
import be.ac.vub.simpleocl.OclExpression;
import be.ac.vub.simpleocl.OclFeature;
import be.ac.vub.simpleocl.OclFeatureDefinition;
import be.ac.vub.simpleocl.OclInstanceModel;
import be.ac.vub.simpleocl.OclMetamodel;
import be.ac.vub.simpleocl.OclModel;
import be.ac.vub.simpleocl.OclModelElement;
import be.ac.vub.simpleocl.OclModelElementExp;
import be.ac.vub.simpleocl.OclType;
import be.ac.vub.simpleocl.OclUndefinedExp;
import be.ac.vub.simpleocl.Operation;
import be.ac.vub.simpleocl.OperationCall;
import be.ac.vub.simpleocl.OperatorCallExp;
import be.ac.vub.simpleocl.OrderedSetExp;
import be.ac.vub.simpleocl.OrderedSetType;
import be.ac.vub.simpleocl.Parameter;
import be.ac.vub.simpleocl.Primitive;
import be.ac.vub.simpleocl.PrimitiveExp;
import be.ac.vub.simpleocl.PropertyCall;
import be.ac.vub.simpleocl.PropertyCallExp;
import be.ac.vub.simpleocl.RealExp;
import be.ac.vub.simpleocl.RealType;
import be.ac.vub.simpleocl.RelOpCallExp;
import be.ac.vub.simpleocl.SelfExp;
import be.ac.vub.simpleocl.SequenceExp;
import be.ac.vub.simpleocl.SequenceType;
import be.ac.vub.simpleocl.SetExp;
import be.ac.vub.simpleocl.SetType;
import be.ac.vub.simpleocl.SimpleoclPackage;
import be.ac.vub.simpleocl.StaticNavigationOrAttributeCall;
import be.ac.vub.simpleocl.StaticOperationCall;
import be.ac.vub.simpleocl.StaticPropertyCall;
import be.ac.vub.simpleocl.StaticPropertyCallExp;
import be.ac.vub.simpleocl.StringExp;
import be.ac.vub.simpleocl.StringType;
import be.ac.vub.simpleocl.SuperExp;
import be.ac.vub.simpleocl.TupleExp;
import be.ac.vub.simpleocl.TuplePart;
import be.ac.vub.simpleocl.TupleType;
import be.ac.vub.simpleocl.TupleTypeAttribute;
import be.ac.vub.simpleocl.VariableDeclaration;
import be.ac.vub.simpleocl.VariableExp;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see be.ac.vub.simpleocl.SimpleoclPackage
 * @generated
 */
public class SimpleoclSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SimpleoclPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleoclSwitch() {
		if (modelPackage == null) {
			modelPackage = SimpleoclPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case SimpleoclPackage.LOCATED_ELEMENT: {
				LocatedElement locatedElement = (LocatedElement)theEObject;
				T result = caseLocatedElement(locatedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.NAMED_ELEMENT: {
				NamedElement namedElement = (NamedElement)theEObject;
				T result = caseNamedElement(namedElement);
				if (result == null) result = caseLocatedElement(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.MODULE: {
				Module module = (Module)theEObject;
				T result = caseModule(module);
				if (result == null) result = caseNamedElement(module);
				if (result == null) result = caseLocatedElement(module);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.IMPORT: {
				Import import_ = (Import)theEObject;
				T result = caseImport(import_);
				if (result == null) result = caseNamedElement(import_);
				if (result == null) result = caseLocatedElement(import_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OCL_EXPRESSION: {
				OclExpression oclExpression = (OclExpression)theEObject;
				T result = caseOclExpression(oclExpression);
				if (result == null) result = caseLocatedElement(oclExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.VARIABLE_EXP: {
				VariableExp variableExp = (VariableExp)theEObject;
				T result = caseVariableExp(variableExp);
				if (result == null) result = caseOclExpression(variableExp);
				if (result == null) result = caseLocatedElement(variableExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.SUPER_EXP: {
				SuperExp superExp = (SuperExp)theEObject;
				T result = caseSuperExp(superExp);
				if (result == null) result = caseOclExpression(superExp);
				if (result == null) result = caseLocatedElement(superExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.SELF_EXP: {
				SelfExp selfExp = (SelfExp)theEObject;
				T result = caseSelfExp(selfExp);
				if (result == null) result = caseOclExpression(selfExp);
				if (result == null) result = caseLocatedElement(selfExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.ENV_EXP: {
				EnvExp envExp = (EnvExp)theEObject;
				T result = caseEnvExp(envExp);
				if (result == null) result = caseOclExpression(envExp);
				if (result == null) result = caseLocatedElement(envExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.PRIMITIVE_EXP: {
				PrimitiveExp primitiveExp = (PrimitiveExp)theEObject;
				T result = casePrimitiveExp(primitiveExp);
				if (result == null) result = caseOclExpression(primitiveExp);
				if (result == null) result = caseLocatedElement(primitiveExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.STRING_EXP: {
				StringExp stringExp = (StringExp)theEObject;
				T result = caseStringExp(stringExp);
				if (result == null) result = casePrimitiveExp(stringExp);
				if (result == null) result = caseOclExpression(stringExp);
				if (result == null) result = caseLocatedElement(stringExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.BOOLEAN_EXP: {
				BooleanExp booleanExp = (BooleanExp)theEObject;
				T result = caseBooleanExp(booleanExp);
				if (result == null) result = casePrimitiveExp(booleanExp);
				if (result == null) result = caseOclExpression(booleanExp);
				if (result == null) result = caseLocatedElement(booleanExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.NUMERIC_EXP: {
				NumericExp numericExp = (NumericExp)theEObject;
				T result = caseNumericExp(numericExp);
				if (result == null) result = casePrimitiveExp(numericExp);
				if (result == null) result = caseOclExpression(numericExp);
				if (result == null) result = caseLocatedElement(numericExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.REAL_EXP: {
				RealExp realExp = (RealExp)theEObject;
				T result = caseRealExp(realExp);
				if (result == null) result = caseNumericExp(realExp);
				if (result == null) result = casePrimitiveExp(realExp);
				if (result == null) result = caseOclExpression(realExp);
				if (result == null) result = caseLocatedElement(realExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.INTEGER_EXP: {
				IntegerExp integerExp = (IntegerExp)theEObject;
				T result = caseIntegerExp(integerExp);
				if (result == null) result = caseNumericExp(integerExp);
				if (result == null) result = casePrimitiveExp(integerExp);
				if (result == null) result = caseOclExpression(integerExp);
				if (result == null) result = caseLocatedElement(integerExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.COLLECTION_EXP: {
				CollectionExp collectionExp = (CollectionExp)theEObject;
				T result = caseCollectionExp(collectionExp);
				if (result == null) result = caseOclExpression(collectionExp);
				if (result == null) result = caseLocatedElement(collectionExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.BAG_EXP: {
				BagExp bagExp = (BagExp)theEObject;
				T result = caseBagExp(bagExp);
				if (result == null) result = caseCollectionExp(bagExp);
				if (result == null) result = caseOclExpression(bagExp);
				if (result == null) result = caseLocatedElement(bagExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.ORDERED_SET_EXP: {
				OrderedSetExp orderedSetExp = (OrderedSetExp)theEObject;
				T result = caseOrderedSetExp(orderedSetExp);
				if (result == null) result = caseCollectionExp(orderedSetExp);
				if (result == null) result = caseOclExpression(orderedSetExp);
				if (result == null) result = caseLocatedElement(orderedSetExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.SEQUENCE_EXP: {
				SequenceExp sequenceExp = (SequenceExp)theEObject;
				T result = caseSequenceExp(sequenceExp);
				if (result == null) result = caseCollectionExp(sequenceExp);
				if (result == null) result = caseOclExpression(sequenceExp);
				if (result == null) result = caseLocatedElement(sequenceExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.SET_EXP: {
				SetExp setExp = (SetExp)theEObject;
				T result = caseSetExp(setExp);
				if (result == null) result = caseCollectionExp(setExp);
				if (result == null) result = caseOclExpression(setExp);
				if (result == null) result = caseLocatedElement(setExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.TUPLE_EXP: {
				TupleExp tupleExp = (TupleExp)theEObject;
				T result = caseTupleExp(tupleExp);
				if (result == null) result = caseOclExpression(tupleExp);
				if (result == null) result = caseLocatedElement(tupleExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.TUPLE_PART: {
				TuplePart tuplePart = (TuplePart)theEObject;
				T result = caseTuplePart(tuplePart);
				if (result == null) result = caseLocalVariable(tuplePart);
				if (result == null) result = caseVariableDeclaration(tuplePart);
				if (result == null) result = caseLocatedElement(tuplePart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.MAP_EXP: {
				MapExp mapExp = (MapExp)theEObject;
				T result = caseMapExp(mapExp);
				if (result == null) result = caseOclExpression(mapExp);
				if (result == null) result = caseLocatedElement(mapExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.MAP_ELEMENT: {
				MapElement mapElement = (MapElement)theEObject;
				T result = caseMapElement(mapElement);
				if (result == null) result = caseLocatedElement(mapElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.ENUM_LITERAL_EXP: {
				EnumLiteralExp enumLiteralExp = (EnumLiteralExp)theEObject;
				T result = caseEnumLiteralExp(enumLiteralExp);
				if (result == null) result = caseOclExpression(enumLiteralExp);
				if (result == null) result = caseLocatedElement(enumLiteralExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OCL_UNDEFINED_EXP: {
				OclUndefinedExp oclUndefinedExp = (OclUndefinedExp)theEObject;
				T result = caseOclUndefinedExp(oclUndefinedExp);
				if (result == null) result = caseOclExpression(oclUndefinedExp);
				if (result == null) result = caseLocatedElement(oclUndefinedExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP: {
				StaticPropertyCallExp staticPropertyCallExp = (StaticPropertyCallExp)theEObject;
				T result = caseStaticPropertyCallExp(staticPropertyCallExp);
				if (result == null) result = caseOclExpression(staticPropertyCallExp);
				if (result == null) result = caseLocatedElement(staticPropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.STATIC_PROPERTY_CALL: {
				StaticPropertyCall staticPropertyCall = (StaticPropertyCall)theEObject;
				T result = caseStaticPropertyCall(staticPropertyCall);
				if (result == null) result = caseLocatedElement(staticPropertyCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.STATIC_NAVIGATION_OR_ATTRIBUTE_CALL: {
				StaticNavigationOrAttributeCall staticNavigationOrAttributeCall = (StaticNavigationOrAttributeCall)theEObject;
				T result = caseStaticNavigationOrAttributeCall(staticNavigationOrAttributeCall);
				if (result == null) result = caseStaticPropertyCall(staticNavigationOrAttributeCall);
				if (result == null) result = caseLocatedElement(staticNavigationOrAttributeCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.STATIC_OPERATION_CALL: {
				StaticOperationCall staticOperationCall = (StaticOperationCall)theEObject;
				T result = caseStaticOperationCall(staticOperationCall);
				if (result == null) result = caseStaticPropertyCall(staticOperationCall);
				if (result == null) result = caseLocatedElement(staticOperationCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.PROPERTY_CALL_EXP: {
				PropertyCallExp propertyCallExp = (PropertyCallExp)theEObject;
				T result = casePropertyCallExp(propertyCallExp);
				if (result == null) result = caseOclExpression(propertyCallExp);
				if (result == null) result = caseLocatedElement(propertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.PROPERTY_CALL: {
				PropertyCall propertyCall = (PropertyCall)theEObject;
				T result = casePropertyCall(propertyCall);
				if (result == null) result = caseLocatedElement(propertyCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.NAVIGATION_OR_ATTRIBUTE_CALL: {
				NavigationOrAttributeCall navigationOrAttributeCall = (NavigationOrAttributeCall)theEObject;
				T result = caseNavigationOrAttributeCall(navigationOrAttributeCall);
				if (result == null) result = casePropertyCall(navigationOrAttributeCall);
				if (result == null) result = caseLocatedElement(navigationOrAttributeCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OPERATION_CALL: {
				OperationCall operationCall = (OperationCall)theEObject;
				T result = caseOperationCall(operationCall);
				if (result == null) result = casePropertyCall(operationCall);
				if (result == null) result = caseLocatedElement(operationCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OPERATOR_CALL_EXP: {
				OperatorCallExp operatorCallExp = (OperatorCallExp)theEObject;
				T result = caseOperatorCallExp(operatorCallExp);
				if (result == null) result = casePropertyCallExp(operatorCallExp);
				if (result == null) result = caseOclExpression(operatorCallExp);
				if (result == null) result = caseLocatedElement(operatorCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.NOT_OP_CALL_EXP: {
				NotOpCallExp notOpCallExp = (NotOpCallExp)theEObject;
				T result = caseNotOpCallExp(notOpCallExp);
				if (result == null) result = caseOperatorCallExp(notOpCallExp);
				if (result == null) result = casePropertyCallExp(notOpCallExp);
				if (result == null) result = caseOclExpression(notOpCallExp);
				if (result == null) result = caseLocatedElement(notOpCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.REL_OP_CALL_EXP: {
				RelOpCallExp relOpCallExp = (RelOpCallExp)theEObject;
				T result = caseRelOpCallExp(relOpCallExp);
				if (result == null) result = caseOperatorCallExp(relOpCallExp);
				if (result == null) result = casePropertyCallExp(relOpCallExp);
				if (result == null) result = caseOclExpression(relOpCallExp);
				if (result == null) result = caseLocatedElement(relOpCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.EQ_OP_CALL_EXP: {
				EqOpCallExp eqOpCallExp = (EqOpCallExp)theEObject;
				T result = caseEqOpCallExp(eqOpCallExp);
				if (result == null) result = caseOperatorCallExp(eqOpCallExp);
				if (result == null) result = casePropertyCallExp(eqOpCallExp);
				if (result == null) result = caseOclExpression(eqOpCallExp);
				if (result == null) result = caseLocatedElement(eqOpCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.ADD_OP_CALL_EXP: {
				AddOpCallExp addOpCallExp = (AddOpCallExp)theEObject;
				T result = caseAddOpCallExp(addOpCallExp);
				if (result == null) result = caseOperatorCallExp(addOpCallExp);
				if (result == null) result = casePropertyCallExp(addOpCallExp);
				if (result == null) result = caseOclExpression(addOpCallExp);
				if (result == null) result = caseLocatedElement(addOpCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.INT_OP_CALL_EXP: {
				IntOpCallExp intOpCallExp = (IntOpCallExp)theEObject;
				T result = caseIntOpCallExp(intOpCallExp);
				if (result == null) result = caseOperatorCallExp(intOpCallExp);
				if (result == null) result = casePropertyCallExp(intOpCallExp);
				if (result == null) result = caseOclExpression(intOpCallExp);
				if (result == null) result = caseLocatedElement(intOpCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.MUL_OP_CALL_EXP: {
				MulOpCallExp mulOpCallExp = (MulOpCallExp)theEObject;
				T result = caseMulOpCallExp(mulOpCallExp);
				if (result == null) result = caseOperatorCallExp(mulOpCallExp);
				if (result == null) result = casePropertyCallExp(mulOpCallExp);
				if (result == null) result = caseOclExpression(mulOpCallExp);
				if (result == null) result = caseLocatedElement(mulOpCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.LAMBDA_CALL_EXP: {
				LambdaCallExp lambdaCallExp = (LambdaCallExp)theEObject;
				T result = caseLambdaCallExp(lambdaCallExp);
				if (result == null) result = caseVariableExp(lambdaCallExp);
				if (result == null) result = caseOclExpression(lambdaCallExp);
				if (result == null) result = caseLocatedElement(lambdaCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.BRACE_EXP: {
				BraceExp braceExp = (BraceExp)theEObject;
				T result = caseBraceExp(braceExp);
				if (result == null) result = caseOclExpression(braceExp);
				if (result == null) result = caseLocatedElement(braceExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.COLLECTION_OPERATION_CALL: {
				CollectionOperationCall collectionOperationCall = (CollectionOperationCall)theEObject;
				T result = caseCollectionOperationCall(collectionOperationCall);
				if (result == null) result = caseOperationCall(collectionOperationCall);
				if (result == null) result = casePropertyCall(collectionOperationCall);
				if (result == null) result = caseLocatedElement(collectionOperationCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.LOOP_EXP: {
				LoopExp loopExp = (LoopExp)theEObject;
				T result = caseLoopExp(loopExp);
				if (result == null) result = casePropertyCall(loopExp);
				if (result == null) result = caseLocatedElement(loopExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.ITERATE_EXP: {
				IterateExp iterateExp = (IterateExp)theEObject;
				T result = caseIterateExp(iterateExp);
				if (result == null) result = caseLoopExp(iterateExp);
				if (result == null) result = casePropertyCall(iterateExp);
				if (result == null) result = caseLocatedElement(iterateExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.ITERATOR_EXP: {
				IteratorExp iteratorExp = (IteratorExp)theEObject;
				T result = caseIteratorExp(iteratorExp);
				if (result == null) result = caseLoopExp(iteratorExp);
				if (result == null) result = casePropertyCall(iteratorExp);
				if (result == null) result = caseLocatedElement(iteratorExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.LET_EXP: {
				LetExp letExp = (LetExp)theEObject;
				T result = caseLetExp(letExp);
				if (result == null) result = caseOclExpression(letExp);
				if (result == null) result = caseLocatedElement(letExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.IF_EXP: {
				IfExp ifExp = (IfExp)theEObject;
				T result = caseIfExp(ifExp);
				if (result == null) result = caseOclExpression(ifExp);
				if (result == null) result = caseLocatedElement(ifExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.VARIABLE_DECLARATION: {
				VariableDeclaration variableDeclaration = (VariableDeclaration)theEObject;
				T result = caseVariableDeclaration(variableDeclaration);
				if (result == null) result = caseLocatedElement(variableDeclaration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.LOCAL_VARIABLE: {
				LocalVariable localVariable = (LocalVariable)theEObject;
				T result = caseLocalVariable(localVariable);
				if (result == null) result = caseVariableDeclaration(localVariable);
				if (result == null) result = caseLocatedElement(localVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.ITERATOR: {
				Iterator iterator = (Iterator)theEObject;
				T result = caseIterator(iterator);
				if (result == null) result = caseVariableDeclaration(iterator);
				if (result == null) result = caseLocatedElement(iterator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.PARAMETER: {
				Parameter parameter = (Parameter)theEObject;
				T result = caseParameter(parameter);
				if (result == null) result = caseVariableDeclaration(parameter);
				if (result == null) result = caseLocatedElement(parameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.COLLECTION_TYPE: {
				CollectionType collectionType = (CollectionType)theEObject;
				T result = caseCollectionType(collectionType);
				if (result == null) result = caseOclType(collectionType);
				if (result == null) result = caseLocatedElement(collectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OCL_TYPE: {
				OclType oclType = (OclType)theEObject;
				T result = caseOclType(oclType);
				if (result == null) result = caseLocatedElement(oclType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OCL_MODEL_ELEMENT_EXP: {
				OclModelElementExp oclModelElementExp = (OclModelElementExp)theEObject;
				T result = caseOclModelElementExp(oclModelElementExp);
				if (result == null) result = caseOclExpression(oclModelElementExp);
				if (result == null) result = caseLocatedElement(oclModelElementExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.PRIMITIVE: {
				Primitive primitive = (Primitive)theEObject;
				T result = casePrimitive(primitive);
				if (result == null) result = caseOclType(primitive);
				if (result == null) result = caseLocatedElement(primitive);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.STRING_TYPE: {
				StringType stringType = (StringType)theEObject;
				T result = caseStringType(stringType);
				if (result == null) result = casePrimitive(stringType);
				if (result == null) result = caseOclType(stringType);
				if (result == null) result = caseLocatedElement(stringType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.BOOLEAN_TYPE: {
				BooleanType booleanType = (BooleanType)theEObject;
				T result = caseBooleanType(booleanType);
				if (result == null) result = casePrimitive(booleanType);
				if (result == null) result = caseOclType(booleanType);
				if (result == null) result = caseLocatedElement(booleanType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.NUMERIC_TYPE: {
				NumericType numericType = (NumericType)theEObject;
				T result = caseNumericType(numericType);
				if (result == null) result = casePrimitive(numericType);
				if (result == null) result = caseOclType(numericType);
				if (result == null) result = caseLocatedElement(numericType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.INTEGER_TYPE: {
				IntegerType integerType = (IntegerType)theEObject;
				T result = caseIntegerType(integerType);
				if (result == null) result = caseNumericType(integerType);
				if (result == null) result = casePrimitive(integerType);
				if (result == null) result = caseOclType(integerType);
				if (result == null) result = caseLocatedElement(integerType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.REAL_TYPE: {
				RealType realType = (RealType)theEObject;
				T result = caseRealType(realType);
				if (result == null) result = caseNumericType(realType);
				if (result == null) result = casePrimitive(realType);
				if (result == null) result = caseOclType(realType);
				if (result == null) result = caseLocatedElement(realType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.BAG_TYPE: {
				BagType bagType = (BagType)theEObject;
				T result = caseBagType(bagType);
				if (result == null) result = caseCollectionType(bagType);
				if (result == null) result = caseOclType(bagType);
				if (result == null) result = caseLocatedElement(bagType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.ORDERED_SET_TYPE: {
				OrderedSetType orderedSetType = (OrderedSetType)theEObject;
				T result = caseOrderedSetType(orderedSetType);
				if (result == null) result = caseCollectionType(orderedSetType);
				if (result == null) result = caseOclType(orderedSetType);
				if (result == null) result = caseLocatedElement(orderedSetType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.SEQUENCE_TYPE: {
				SequenceType sequenceType = (SequenceType)theEObject;
				T result = caseSequenceType(sequenceType);
				if (result == null) result = caseCollectionType(sequenceType);
				if (result == null) result = caseOclType(sequenceType);
				if (result == null) result = caseLocatedElement(sequenceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.SET_TYPE: {
				SetType setType = (SetType)theEObject;
				T result = caseSetType(setType);
				if (result == null) result = caseCollectionType(setType);
				if (result == null) result = caseOclType(setType);
				if (result == null) result = caseLocatedElement(setType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OCL_ANY_TYPE: {
				OclAnyType oclAnyType = (OclAnyType)theEObject;
				T result = caseOclAnyType(oclAnyType);
				if (result == null) result = caseOclType(oclAnyType);
				if (result == null) result = caseLocatedElement(oclAnyType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.TUPLE_TYPE: {
				TupleType tupleType = (TupleType)theEObject;
				T result = caseTupleType(tupleType);
				if (result == null) result = caseOclType(tupleType);
				if (result == null) result = caseLocatedElement(tupleType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE: {
				TupleTypeAttribute tupleTypeAttribute = (TupleTypeAttribute)theEObject;
				T result = caseTupleTypeAttribute(tupleTypeAttribute);
				if (result == null) result = caseLocatedElement(tupleTypeAttribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OCL_MODEL_ELEMENT: {
				OclModelElement oclModelElement = (OclModelElement)theEObject;
				T result = caseOclModelElement(oclModelElement);
				if (result == null) result = caseOclType(oclModelElement);
				if (result == null) result = caseLocatedElement(oclModelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.MAP_TYPE: {
				MapType mapType = (MapType)theEObject;
				T result = caseMapType(mapType);
				if (result == null) result = caseOclType(mapType);
				if (result == null) result = caseLocatedElement(mapType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.LAMBDA_TYPE: {
				LambdaType lambdaType = (LambdaType)theEObject;
				T result = caseLambdaType(lambdaType);
				if (result == null) result = caseOclType(lambdaType);
				if (result == null) result = caseLocatedElement(lambdaType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.ENV_TYPE: {
				EnvType envType = (EnvType)theEObject;
				T result = caseEnvType(envType);
				if (result == null) result = caseOclType(envType);
				if (result == null) result = caseLocatedElement(envType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OCL_FEATURE_DEFINITION: {
				OclFeatureDefinition oclFeatureDefinition = (OclFeatureDefinition)theEObject;
				T result = caseOclFeatureDefinition(oclFeatureDefinition);
				if (result == null) result = caseLocatedElement(oclFeatureDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OCL_CONTEXT_DEFINITION: {
				OclContextDefinition oclContextDefinition = (OclContextDefinition)theEObject;
				T result = caseOclContextDefinition(oclContextDefinition);
				if (result == null) result = caseLocatedElement(oclContextDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OCL_FEATURE: {
				OclFeature oclFeature = (OclFeature)theEObject;
				T result = caseOclFeature(oclFeature);
				if (result == null) result = caseNamedElement(oclFeature);
				if (result == null) result = caseLocatedElement(oclFeature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.ATTRIBUTE: {
				Attribute attribute = (Attribute)theEObject;
				T result = caseAttribute(attribute);
				if (result == null) result = caseOclFeature(attribute);
				if (result == null) result = caseNamedElement(attribute);
				if (result == null) result = caseLocatedElement(attribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OPERATION: {
				Operation operation = (Operation)theEObject;
				T result = caseOperation(operation);
				if (result == null) result = caseOclFeature(operation);
				if (result == null) result = caseNamedElement(operation);
				if (result == null) result = caseLocatedElement(operation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OCL_MODEL: {
				OclModel oclModel = (OclModel)theEObject;
				T result = caseOclModel(oclModel);
				if (result == null) result = caseNamedElement(oclModel);
				if (result == null) result = caseLocatedElement(oclModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OCL_METAMODEL: {
				OclMetamodel oclMetamodel = (OclMetamodel)theEObject;
				T result = caseOclMetamodel(oclMetamodel);
				if (result == null) result = caseOclModel(oclMetamodel);
				if (result == null) result = caseNamedElement(oclMetamodel);
				if (result == null) result = caseLocatedElement(oclMetamodel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimpleoclPackage.OCL_INSTANCE_MODEL: {
				OclInstanceModel oclInstanceModel = (OclInstanceModel)theEObject;
				T result = caseOclInstanceModel(oclInstanceModel);
				if (result == null) result = caseOclModel(oclInstanceModel);
				if (result == null) result = caseNamedElement(oclInstanceModel);
				if (result == null) result = caseLocatedElement(oclInstanceModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Located Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Located Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocatedElement(LocatedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModule(Module object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Import</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Import</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImport(Import object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclExpression(OclExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableExp(VariableExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Super Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Super Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSuperExp(SuperExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Self Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Self Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSelfExp(SelfExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Env Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Env Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvExp(EnvExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveExp(PrimitiveExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringExp(StringExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanExp(BooleanExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Numeric Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Numeric Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumericExp(NumericExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Real Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Real Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRealExp(RealExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerExp(IntegerExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCollectionExp(CollectionExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bag Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bag Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBagExp(BagExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordered Set Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordered Set Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrderedSetExp(OrderedSetExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSequenceExp(SequenceExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Set Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Set Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSetExp(SetExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tuple Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tuple Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTupleExp(TupleExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tuple Part</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tuple Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTuplePart(TuplePart object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMapExp(MapExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMapElement(MapElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enum Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enum Literal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumLiteralExp(EnumLiteralExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Undefined Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Undefined Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclUndefinedExp(OclUndefinedExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Static Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Static Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStaticPropertyCallExp(StaticPropertyCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Static Property Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Static Property Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStaticPropertyCall(StaticPropertyCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Static Navigation Or Attribute Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Static Navigation Or Attribute Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStaticNavigationOrAttributeCall(StaticNavigationOrAttributeCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Static Operation Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Static Operation Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStaticOperationCall(StaticOperationCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyCallExp(PropertyCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyCall(PropertyCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigation Or Attribute Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation Or Attribute Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigationOrAttributeCall(NavigationOrAttributeCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationCall(OperationCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operator Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operator Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperatorCallExp(OperatorCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Not Op Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Not Op Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNotOpCallExp(NotOpCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rel Op Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rel Op Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelOpCallExp(RelOpCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Eq Op Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Eq Op Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEqOpCallExp(EqOpCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Op Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Op Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddOpCallExp(AddOpCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Int Op Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Int Op Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntOpCallExp(IntOpCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mul Op Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mul Op Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMulOpCallExp(MulOpCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lambda Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lambda Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLambdaCallExp(LambdaCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Brace Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Brace Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBraceExp(BraceExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Operation Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Operation Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCollectionOperationCall(CollectionOperationCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Loop Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Loop Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLoopExp(LoopExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Iterate Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iterate Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIterateExp(IterateExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Iterator Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iterator Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIteratorExp(IteratorExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Let Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Let Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLetExp(LetExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>If Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>If Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIfExp(IfExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableDeclaration(VariableDeclaration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Local Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Local Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocalVariable(LocalVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Iterator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iterator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIterator(Iterator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameter(Parameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCollectionType(CollectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclType(OclType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Model Element Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Model Element Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclModelElementExp(OclModelElementExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitive(Primitive object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringType(StringType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanType(BooleanType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Numeric Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Numeric Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumericType(NumericType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerType(IntegerType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Real Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Real Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRealType(RealType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bag Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bag Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBagType(BagType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordered Set Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordered Set Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrderedSetType(OrderedSetType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSequenceType(SequenceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Set Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Set Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSetType(SetType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Any Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Any Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclAnyType(OclAnyType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tuple Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tuple Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTupleType(TupleType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tuple Type Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tuple Type Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTupleTypeAttribute(TupleTypeAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclModelElement(OclModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMapType(MapType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lambda Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lambda Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLambdaType(LambdaType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Env Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Env Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvType(EnvType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Feature Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Feature Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclFeatureDefinition(OclFeatureDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Context Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Context Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclContextDefinition(OclContextDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclFeature(OclFeature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttribute(Attribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperation(Operation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclModel(OclModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Metamodel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Metamodel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclMetamodel(OclMetamodel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Instance Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Instance Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclInstanceModel(OclInstanceModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //SimpleoclSwitch
