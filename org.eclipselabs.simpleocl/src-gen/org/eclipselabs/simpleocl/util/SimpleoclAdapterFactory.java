/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipselabs.simpleocl.AddOpCallExp;
import org.eclipselabs.simpleocl.Attribute;
import org.eclipselabs.simpleocl.BagExp;
import org.eclipselabs.simpleocl.BagType;
import org.eclipselabs.simpleocl.BooleanExp;
import org.eclipselabs.simpleocl.BooleanType;
import org.eclipselabs.simpleocl.BraceExp;
import org.eclipselabs.simpleocl.CollectionExp;
import org.eclipselabs.simpleocl.CollectionItem;
import org.eclipselabs.simpleocl.CollectionOperationCall;
import org.eclipselabs.simpleocl.CollectionPart;
import org.eclipselabs.simpleocl.CollectionRange;
import org.eclipselabs.simpleocl.CollectionType;
import org.eclipselabs.simpleocl.EnumLiteralExp;
import org.eclipselabs.simpleocl.EnvExp;
import org.eclipselabs.simpleocl.EnvType;
import org.eclipselabs.simpleocl.EqOpCallExp;
import org.eclipselabs.simpleocl.IfExp;
import org.eclipselabs.simpleocl.Import;
import org.eclipselabs.simpleocl.IntOpCallExp;
import org.eclipselabs.simpleocl.IntegerExp;
import org.eclipselabs.simpleocl.IntegerType;
import org.eclipselabs.simpleocl.IterateExp;
import org.eclipselabs.simpleocl.Iterator;
import org.eclipselabs.simpleocl.IteratorExp;
import org.eclipselabs.simpleocl.LambdaCallExp;
import org.eclipselabs.simpleocl.LambdaType;
import org.eclipselabs.simpleocl.LetExp;
import org.eclipselabs.simpleocl.LocalVariable;
import org.eclipselabs.simpleocl.LocatedElement;
import org.eclipselabs.simpleocl.LoopExp;
import org.eclipselabs.simpleocl.MapElement;
import org.eclipselabs.simpleocl.MapExp;
import org.eclipselabs.simpleocl.MapType;
import org.eclipselabs.simpleocl.Module;
import org.eclipselabs.simpleocl.ModuleElement;
import org.eclipselabs.simpleocl.MulOpCallExp;
import org.eclipselabs.simpleocl.NamedElement;
import org.eclipselabs.simpleocl.NavigationOrAttributeCall;
import org.eclipselabs.simpleocl.NotOpCallExp;
import org.eclipselabs.simpleocl.NumericExp;
import org.eclipselabs.simpleocl.NumericType;
import org.eclipselabs.simpleocl.OclAnyType;
import org.eclipselabs.simpleocl.OclContextDefinition;
import org.eclipselabs.simpleocl.OclExpression;
import org.eclipselabs.simpleocl.OclFeature;
import org.eclipselabs.simpleocl.OclFeatureDefinition;
import org.eclipselabs.simpleocl.OclInstanceModel;
import org.eclipselabs.simpleocl.OclMetamodel;
import org.eclipselabs.simpleocl.OclModel;
import org.eclipselabs.simpleocl.OclModelElement;
import org.eclipselabs.simpleocl.OclModelElementExp;
import org.eclipselabs.simpleocl.OclType;
import org.eclipselabs.simpleocl.OclUndefinedExp;
import org.eclipselabs.simpleocl.Operation;
import org.eclipselabs.simpleocl.OperationCall;
import org.eclipselabs.simpleocl.OperatorCallExp;
import org.eclipselabs.simpleocl.OrderedSetExp;
import org.eclipselabs.simpleocl.OrderedSetType;
import org.eclipselabs.simpleocl.Parameter;
import org.eclipselabs.simpleocl.Primitive;
import org.eclipselabs.simpleocl.PrimitiveExp;
import org.eclipselabs.simpleocl.PropertyCall;
import org.eclipselabs.simpleocl.PropertyCallExp;
import org.eclipselabs.simpleocl.RealExp;
import org.eclipselabs.simpleocl.RealType;
import org.eclipselabs.simpleocl.RelOpCallExp;
import org.eclipselabs.simpleocl.SelfExp;
import org.eclipselabs.simpleocl.SequenceExp;
import org.eclipselabs.simpleocl.SequenceType;
import org.eclipselabs.simpleocl.SetExp;
import org.eclipselabs.simpleocl.SetType;
import org.eclipselabs.simpleocl.SimpleoclPackage;
import org.eclipselabs.simpleocl.StaticNavigationOrAttributeCall;
import org.eclipselabs.simpleocl.StaticOperationCall;
import org.eclipselabs.simpleocl.StaticPropertyCall;
import org.eclipselabs.simpleocl.StaticPropertyCallExp;
import org.eclipselabs.simpleocl.StringExp;
import org.eclipselabs.simpleocl.StringType;
import org.eclipselabs.simpleocl.SuperExp;
import org.eclipselabs.simpleocl.TupleExp;
import org.eclipselabs.simpleocl.TuplePart;
import org.eclipselabs.simpleocl.TupleType;
import org.eclipselabs.simpleocl.TupleTypeAttribute;
import org.eclipselabs.simpleocl.VariableDeclaration;
import org.eclipselabs.simpleocl.VariableExp;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipselabs.simpleocl.SimpleoclPackage
 * @generated
 */
public class SimpleoclAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SimpleoclPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleoclAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SimpleoclPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimpleoclSwitch<Adapter> modelSwitch =
		new SimpleoclSwitch<Adapter>() {
			@Override
			public Adapter caseLocatedElement(LocatedElement object) {
				return createLocatedElementAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseModule(Module object) {
				return createModuleAdapter();
			}
			@Override
			public Adapter caseModuleElement(ModuleElement object) {
				return createModuleElementAdapter();
			}
			@Override
			public Adapter caseImport(Import object) {
				return createImportAdapter();
			}
			@Override
			public Adapter caseOclExpression(OclExpression object) {
				return createOclExpressionAdapter();
			}
			@Override
			public Adapter caseVariableExp(VariableExp object) {
				return createVariableExpAdapter();
			}
			@Override
			public Adapter caseSuperExp(SuperExp object) {
				return createSuperExpAdapter();
			}
			@Override
			public Adapter caseSelfExp(SelfExp object) {
				return createSelfExpAdapter();
			}
			@Override
			public Adapter caseEnvExp(EnvExp object) {
				return createEnvExpAdapter();
			}
			@Override
			public Adapter casePrimitiveExp(PrimitiveExp object) {
				return createPrimitiveExpAdapter();
			}
			@Override
			public Adapter caseStringExp(StringExp object) {
				return createStringExpAdapter();
			}
			@Override
			public Adapter caseBooleanExp(BooleanExp object) {
				return createBooleanExpAdapter();
			}
			@Override
			public Adapter caseNumericExp(NumericExp object) {
				return createNumericExpAdapter();
			}
			@Override
			public Adapter caseRealExp(RealExp object) {
				return createRealExpAdapter();
			}
			@Override
			public Adapter caseIntegerExp(IntegerExp object) {
				return createIntegerExpAdapter();
			}
			@Override
			public Adapter caseCollectionExp(CollectionExp object) {
				return createCollectionExpAdapter();
			}
			@Override
			public Adapter caseCollectionPart(CollectionPart object) {
				return createCollectionPartAdapter();
			}
			@Override
			public Adapter caseCollectionRange(CollectionRange object) {
				return createCollectionRangeAdapter();
			}
			@Override
			public Adapter caseCollectionItem(CollectionItem object) {
				return createCollectionItemAdapter();
			}
			@Override
			public Adapter caseBagExp(BagExp object) {
				return createBagExpAdapter();
			}
			@Override
			public Adapter caseOrderedSetExp(OrderedSetExp object) {
				return createOrderedSetExpAdapter();
			}
			@Override
			public Adapter caseSequenceExp(SequenceExp object) {
				return createSequenceExpAdapter();
			}
			@Override
			public Adapter caseSetExp(SetExp object) {
				return createSetExpAdapter();
			}
			@Override
			public Adapter caseTupleExp(TupleExp object) {
				return createTupleExpAdapter();
			}
			@Override
			public Adapter caseTuplePart(TuplePart object) {
				return createTuplePartAdapter();
			}
			@Override
			public Adapter caseMapExp(MapExp object) {
				return createMapExpAdapter();
			}
			@Override
			public Adapter caseMapElement(MapElement object) {
				return createMapElementAdapter();
			}
			@Override
			public Adapter caseEnumLiteralExp(EnumLiteralExp object) {
				return createEnumLiteralExpAdapter();
			}
			@Override
			public Adapter caseOclUndefinedExp(OclUndefinedExp object) {
				return createOclUndefinedExpAdapter();
			}
			@Override
			public Adapter caseStaticPropertyCallExp(StaticPropertyCallExp object) {
				return createStaticPropertyCallExpAdapter();
			}
			@Override
			public Adapter caseStaticPropertyCall(StaticPropertyCall object) {
				return createStaticPropertyCallAdapter();
			}
			@Override
			public Adapter caseStaticNavigationOrAttributeCall(StaticNavigationOrAttributeCall object) {
				return createStaticNavigationOrAttributeCallAdapter();
			}
			@Override
			public Adapter caseStaticOperationCall(StaticOperationCall object) {
				return createStaticOperationCallAdapter();
			}
			@Override
			public Adapter casePropertyCallExp(PropertyCallExp object) {
				return createPropertyCallExpAdapter();
			}
			@Override
			public Adapter casePropertyCall(PropertyCall object) {
				return createPropertyCallAdapter();
			}
			@Override
			public Adapter caseNavigationOrAttributeCall(NavigationOrAttributeCall object) {
				return createNavigationOrAttributeCallAdapter();
			}
			@Override
			public Adapter caseOperationCall(OperationCall object) {
				return createOperationCallAdapter();
			}
			@Override
			public Adapter caseOperatorCallExp(OperatorCallExp object) {
				return createOperatorCallExpAdapter();
			}
			@Override
			public Adapter caseNotOpCallExp(NotOpCallExp object) {
				return createNotOpCallExpAdapter();
			}
			@Override
			public Adapter caseRelOpCallExp(RelOpCallExp object) {
				return createRelOpCallExpAdapter();
			}
			@Override
			public Adapter caseEqOpCallExp(EqOpCallExp object) {
				return createEqOpCallExpAdapter();
			}
			@Override
			public Adapter caseAddOpCallExp(AddOpCallExp object) {
				return createAddOpCallExpAdapter();
			}
			@Override
			public Adapter caseIntOpCallExp(IntOpCallExp object) {
				return createIntOpCallExpAdapter();
			}
			@Override
			public Adapter caseMulOpCallExp(MulOpCallExp object) {
				return createMulOpCallExpAdapter();
			}
			@Override
			public Adapter caseLambdaCallExp(LambdaCallExp object) {
				return createLambdaCallExpAdapter();
			}
			@Override
			public Adapter caseBraceExp(BraceExp object) {
				return createBraceExpAdapter();
			}
			@Override
			public Adapter caseCollectionOperationCall(CollectionOperationCall object) {
				return createCollectionOperationCallAdapter();
			}
			@Override
			public Adapter caseLoopExp(LoopExp object) {
				return createLoopExpAdapter();
			}
			@Override
			public Adapter caseIterateExp(IterateExp object) {
				return createIterateExpAdapter();
			}
			@Override
			public Adapter caseIteratorExp(IteratorExp object) {
				return createIteratorExpAdapter();
			}
			@Override
			public Adapter caseLetExp(LetExp object) {
				return createLetExpAdapter();
			}
			@Override
			public Adapter caseIfExp(IfExp object) {
				return createIfExpAdapter();
			}
			@Override
			public Adapter caseVariableDeclaration(VariableDeclaration object) {
				return createVariableDeclarationAdapter();
			}
			@Override
			public Adapter caseLocalVariable(LocalVariable object) {
				return createLocalVariableAdapter();
			}
			@Override
			public Adapter caseIterator(Iterator object) {
				return createIteratorAdapter();
			}
			@Override
			public Adapter caseParameter(Parameter object) {
				return createParameterAdapter();
			}
			@Override
			public Adapter caseCollectionType(CollectionType object) {
				return createCollectionTypeAdapter();
			}
			@Override
			public Adapter caseOclType(OclType object) {
				return createOclTypeAdapter();
			}
			@Override
			public Adapter caseOclModelElementExp(OclModelElementExp object) {
				return createOclModelElementExpAdapter();
			}
			@Override
			public Adapter casePrimitive(Primitive object) {
				return createPrimitiveAdapter();
			}
			@Override
			public Adapter caseStringType(StringType object) {
				return createStringTypeAdapter();
			}
			@Override
			public Adapter caseBooleanType(BooleanType object) {
				return createBooleanTypeAdapter();
			}
			@Override
			public Adapter caseNumericType(NumericType object) {
				return createNumericTypeAdapter();
			}
			@Override
			public Adapter caseIntegerType(IntegerType object) {
				return createIntegerTypeAdapter();
			}
			@Override
			public Adapter caseRealType(RealType object) {
				return createRealTypeAdapter();
			}
			@Override
			public Adapter caseBagType(BagType object) {
				return createBagTypeAdapter();
			}
			@Override
			public Adapter caseOrderedSetType(OrderedSetType object) {
				return createOrderedSetTypeAdapter();
			}
			@Override
			public Adapter caseSequenceType(SequenceType object) {
				return createSequenceTypeAdapter();
			}
			@Override
			public Adapter caseSetType(SetType object) {
				return createSetTypeAdapter();
			}
			@Override
			public Adapter caseOclAnyType(OclAnyType object) {
				return createOclAnyTypeAdapter();
			}
			@Override
			public Adapter caseTupleType(TupleType object) {
				return createTupleTypeAdapter();
			}
			@Override
			public Adapter caseTupleTypeAttribute(TupleTypeAttribute object) {
				return createTupleTypeAttributeAdapter();
			}
			@Override
			public Adapter caseOclModelElement(OclModelElement object) {
				return createOclModelElementAdapter();
			}
			@Override
			public Adapter caseMapType(MapType object) {
				return createMapTypeAdapter();
			}
			@Override
			public Adapter caseLambdaType(LambdaType object) {
				return createLambdaTypeAdapter();
			}
			@Override
			public Adapter caseEnvType(EnvType object) {
				return createEnvTypeAdapter();
			}
			@Override
			public Adapter caseOclFeatureDefinition(OclFeatureDefinition object) {
				return createOclFeatureDefinitionAdapter();
			}
			@Override
			public Adapter caseOclContextDefinition(OclContextDefinition object) {
				return createOclContextDefinitionAdapter();
			}
			@Override
			public Adapter caseOclFeature(OclFeature object) {
				return createOclFeatureAdapter();
			}
			@Override
			public Adapter caseAttribute(Attribute object) {
				return createAttributeAdapter();
			}
			@Override
			public Adapter caseOperation(Operation object) {
				return createOperationAdapter();
			}
			@Override
			public Adapter caseOclModel(OclModel object) {
				return createOclModelAdapter();
			}
			@Override
			public Adapter caseOclMetamodel(OclMetamodel object) {
				return createOclMetamodelAdapter();
			}
			@Override
			public Adapter caseOclInstanceModel(OclInstanceModel object) {
				return createOclInstanceModelAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.LocatedElement <em>Located Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.LocatedElement
	 * @generated
	 */
	public Adapter createLocatedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.Module <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.Module
	 * @generated
	 */
	public Adapter createModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.ModuleElement <em>Module Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.ModuleElement
	 * @generated
	 */
	public Adapter createModuleElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.Import <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.Import
	 * @generated
	 */
	public Adapter createImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OclExpression <em>Ocl Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OclExpression
	 * @generated
	 */
	public Adapter createOclExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.VariableExp <em>Variable Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.VariableExp
	 * @generated
	 */
	public Adapter createVariableExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.SuperExp <em>Super Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.SuperExp
	 * @generated
	 */
	public Adapter createSuperExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.SelfExp <em>Self Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.SelfExp
	 * @generated
	 */
	public Adapter createSelfExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.EnvExp <em>Env Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.EnvExp
	 * @generated
	 */
	public Adapter createEnvExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.PrimitiveExp <em>Primitive Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.PrimitiveExp
	 * @generated
	 */
	public Adapter createPrimitiveExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.StringExp <em>String Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.StringExp
	 * @generated
	 */
	public Adapter createStringExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.BooleanExp <em>Boolean Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.BooleanExp
	 * @generated
	 */
	public Adapter createBooleanExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.NumericExp <em>Numeric Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.NumericExp
	 * @generated
	 */
	public Adapter createNumericExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.RealExp <em>Real Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.RealExp
	 * @generated
	 */
	public Adapter createRealExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.IntegerExp <em>Integer Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.IntegerExp
	 * @generated
	 */
	public Adapter createIntegerExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.CollectionExp <em>Collection Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.CollectionExp
	 * @generated
	 */
	public Adapter createCollectionExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.CollectionPart <em>Collection Part</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.CollectionPart
	 * @generated
	 */
	public Adapter createCollectionPartAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.CollectionRange <em>Collection Range</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.CollectionRange
	 * @generated
	 */
	public Adapter createCollectionRangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.CollectionItem <em>Collection Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.CollectionItem
	 * @generated
	 */
	public Adapter createCollectionItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.BagExp <em>Bag Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.BagExp
	 * @generated
	 */
	public Adapter createBagExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OrderedSetExp <em>Ordered Set Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OrderedSetExp
	 * @generated
	 */
	public Adapter createOrderedSetExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.SequenceExp <em>Sequence Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.SequenceExp
	 * @generated
	 */
	public Adapter createSequenceExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.SetExp <em>Set Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.SetExp
	 * @generated
	 */
	public Adapter createSetExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.TupleExp <em>Tuple Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.TupleExp
	 * @generated
	 */
	public Adapter createTupleExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.TuplePart <em>Tuple Part</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.TuplePart
	 * @generated
	 */
	public Adapter createTuplePartAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.MapExp <em>Map Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.MapExp
	 * @generated
	 */
	public Adapter createMapExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.MapElement <em>Map Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.MapElement
	 * @generated
	 */
	public Adapter createMapElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.EnumLiteralExp <em>Enum Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.EnumLiteralExp
	 * @generated
	 */
	public Adapter createEnumLiteralExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OclUndefinedExp <em>Ocl Undefined Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OclUndefinedExp
	 * @generated
	 */
	public Adapter createOclUndefinedExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.StaticPropertyCallExp <em>Static Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.StaticPropertyCallExp
	 * @generated
	 */
	public Adapter createStaticPropertyCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.StaticPropertyCall <em>Static Property Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.StaticPropertyCall
	 * @generated
	 */
	public Adapter createStaticPropertyCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.StaticNavigationOrAttributeCall <em>Static Navigation Or Attribute Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.StaticNavigationOrAttributeCall
	 * @generated
	 */
	public Adapter createStaticNavigationOrAttributeCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.StaticOperationCall <em>Static Operation Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.StaticOperationCall
	 * @generated
	 */
	public Adapter createStaticOperationCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.PropertyCallExp <em>Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.PropertyCallExp
	 * @generated
	 */
	public Adapter createPropertyCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.PropertyCall <em>Property Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.PropertyCall
	 * @generated
	 */
	public Adapter createPropertyCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.NavigationOrAttributeCall <em>Navigation Or Attribute Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.NavigationOrAttributeCall
	 * @generated
	 */
	public Adapter createNavigationOrAttributeCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OperationCall <em>Operation Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OperationCall
	 * @generated
	 */
	public Adapter createOperationCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OperatorCallExp <em>Operator Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OperatorCallExp
	 * @generated
	 */
	public Adapter createOperatorCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.NotOpCallExp <em>Not Op Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.NotOpCallExp
	 * @generated
	 */
	public Adapter createNotOpCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.RelOpCallExp <em>Rel Op Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.RelOpCallExp
	 * @generated
	 */
	public Adapter createRelOpCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.EqOpCallExp <em>Eq Op Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.EqOpCallExp
	 * @generated
	 */
	public Adapter createEqOpCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.AddOpCallExp <em>Add Op Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.AddOpCallExp
	 * @generated
	 */
	public Adapter createAddOpCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.IntOpCallExp <em>Int Op Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.IntOpCallExp
	 * @generated
	 */
	public Adapter createIntOpCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.MulOpCallExp <em>Mul Op Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.MulOpCallExp
	 * @generated
	 */
	public Adapter createMulOpCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.LambdaCallExp <em>Lambda Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.LambdaCallExp
	 * @generated
	 */
	public Adapter createLambdaCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.BraceExp <em>Brace Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.BraceExp
	 * @generated
	 */
	public Adapter createBraceExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.CollectionOperationCall <em>Collection Operation Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.CollectionOperationCall
	 * @generated
	 */
	public Adapter createCollectionOperationCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.LoopExp <em>Loop Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.LoopExp
	 * @generated
	 */
	public Adapter createLoopExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.IterateExp <em>Iterate Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.IterateExp
	 * @generated
	 */
	public Adapter createIterateExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.IteratorExp <em>Iterator Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.IteratorExp
	 * @generated
	 */
	public Adapter createIteratorExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.LetExp <em>Let Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.LetExp
	 * @generated
	 */
	public Adapter createLetExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.IfExp <em>If Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.IfExp
	 * @generated
	 */
	public Adapter createIfExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.VariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.VariableDeclaration
	 * @generated
	 */
	public Adapter createVariableDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.LocalVariable <em>Local Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.LocalVariable
	 * @generated
	 */
	public Adapter createLocalVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.Iterator <em>Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.Iterator
	 * @generated
	 */
	public Adapter createIteratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.CollectionType <em>Collection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.CollectionType
	 * @generated
	 */
	public Adapter createCollectionTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OclType <em>Ocl Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OclType
	 * @generated
	 */
	public Adapter createOclTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OclModelElementExp <em>Ocl Model Element Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OclModelElementExp
	 * @generated
	 */
	public Adapter createOclModelElementExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.Primitive <em>Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.Primitive
	 * @generated
	 */
	public Adapter createPrimitiveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.StringType <em>String Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.StringType
	 * @generated
	 */
	public Adapter createStringTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.BooleanType <em>Boolean Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.BooleanType
	 * @generated
	 */
	public Adapter createBooleanTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.NumericType <em>Numeric Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.NumericType
	 * @generated
	 */
	public Adapter createNumericTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.IntegerType <em>Integer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.IntegerType
	 * @generated
	 */
	public Adapter createIntegerTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.RealType <em>Real Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.RealType
	 * @generated
	 */
	public Adapter createRealTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.BagType <em>Bag Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.BagType
	 * @generated
	 */
	public Adapter createBagTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OrderedSetType <em>Ordered Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OrderedSetType
	 * @generated
	 */
	public Adapter createOrderedSetTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.SequenceType <em>Sequence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.SequenceType
	 * @generated
	 */
	public Adapter createSequenceTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.SetType <em>Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.SetType
	 * @generated
	 */
	public Adapter createSetTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OclAnyType <em>Ocl Any Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OclAnyType
	 * @generated
	 */
	public Adapter createOclAnyTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.TupleType <em>Tuple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.TupleType
	 * @generated
	 */
	public Adapter createTupleTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.TupleTypeAttribute <em>Tuple Type Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.TupleTypeAttribute
	 * @generated
	 */
	public Adapter createTupleTypeAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OclModelElement <em>Ocl Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OclModelElement
	 * @generated
	 */
	public Adapter createOclModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.MapType <em>Map Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.MapType
	 * @generated
	 */
	public Adapter createMapTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.LambdaType <em>Lambda Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.LambdaType
	 * @generated
	 */
	public Adapter createLambdaTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.EnvType <em>Env Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.EnvType
	 * @generated
	 */
	public Adapter createEnvTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OclFeatureDefinition <em>Ocl Feature Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OclFeatureDefinition
	 * @generated
	 */
	public Adapter createOclFeatureDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OclContextDefinition <em>Ocl Context Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OclContextDefinition
	 * @generated
	 */
	public Adapter createOclContextDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OclFeature <em>Ocl Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OclFeature
	 * @generated
	 */
	public Adapter createOclFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.Attribute
	 * @generated
	 */
	public Adapter createAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.Operation
	 * @generated
	 */
	public Adapter createOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OclModel <em>Ocl Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OclModel
	 * @generated
	 */
	public Adapter createOclModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OclMetamodel <em>Ocl Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OclMetamodel
	 * @generated
	 */
	public Adapter createOclMetamodelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipselabs.simpleocl.OclInstanceModel <em>Ocl Instance Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipselabs.simpleocl.OclInstanceModel
	 * @generated
	 */
	public Adapter createOclInstanceModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SimpleoclAdapterFactory
