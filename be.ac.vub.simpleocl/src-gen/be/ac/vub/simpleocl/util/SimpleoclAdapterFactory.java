/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import be.ac.vub.simpleocl.AddOpCallExp;
import be.ac.vub.simpleocl.Attribute;
import be.ac.vub.simpleocl.BagExp;
import be.ac.vub.simpleocl.BagType;
import be.ac.vub.simpleocl.BooleanExp;
import be.ac.vub.simpleocl.BooleanType;
import be.ac.vub.simpleocl.BraceExp;
import be.ac.vub.simpleocl.CollectionExp;
import be.ac.vub.simpleocl.CollectionItem;
import be.ac.vub.simpleocl.CollectionOperationCall;
import be.ac.vub.simpleocl.CollectionPart;
import be.ac.vub.simpleocl.CollectionRange;
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
import be.ac.vub.simpleocl.ModuleElement;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see be.ac.vub.simpleocl.SimpleoclPackage
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
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.LocatedElement <em>Located Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.LocatedElement
	 * @generated
	 */
	public Adapter createLocatedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.Module <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.Module
	 * @generated
	 */
	public Adapter createModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.ModuleElement <em>Module Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.ModuleElement
	 * @generated
	 */
	public Adapter createModuleElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.Import <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.Import
	 * @generated
	 */
	public Adapter createImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OclExpression <em>Ocl Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OclExpression
	 * @generated
	 */
	public Adapter createOclExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.VariableExp <em>Variable Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.VariableExp
	 * @generated
	 */
	public Adapter createVariableExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.SuperExp <em>Super Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.SuperExp
	 * @generated
	 */
	public Adapter createSuperExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.SelfExp <em>Self Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.SelfExp
	 * @generated
	 */
	public Adapter createSelfExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.EnvExp <em>Env Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.EnvExp
	 * @generated
	 */
	public Adapter createEnvExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.PrimitiveExp <em>Primitive Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.PrimitiveExp
	 * @generated
	 */
	public Adapter createPrimitiveExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.StringExp <em>String Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.StringExp
	 * @generated
	 */
	public Adapter createStringExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.BooleanExp <em>Boolean Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.BooleanExp
	 * @generated
	 */
	public Adapter createBooleanExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.NumericExp <em>Numeric Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.NumericExp
	 * @generated
	 */
	public Adapter createNumericExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.RealExp <em>Real Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.RealExp
	 * @generated
	 */
	public Adapter createRealExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.IntegerExp <em>Integer Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.IntegerExp
	 * @generated
	 */
	public Adapter createIntegerExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.CollectionExp <em>Collection Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.CollectionExp
	 * @generated
	 */
	public Adapter createCollectionExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.CollectionPart <em>Collection Part</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.CollectionPart
	 * @generated
	 */
	public Adapter createCollectionPartAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.CollectionRange <em>Collection Range</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.CollectionRange
	 * @generated
	 */
	public Adapter createCollectionRangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.CollectionItem <em>Collection Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.CollectionItem
	 * @generated
	 */
	public Adapter createCollectionItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.BagExp <em>Bag Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.BagExp
	 * @generated
	 */
	public Adapter createBagExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OrderedSetExp <em>Ordered Set Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OrderedSetExp
	 * @generated
	 */
	public Adapter createOrderedSetExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.SequenceExp <em>Sequence Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.SequenceExp
	 * @generated
	 */
	public Adapter createSequenceExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.SetExp <em>Set Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.SetExp
	 * @generated
	 */
	public Adapter createSetExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.TupleExp <em>Tuple Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.TupleExp
	 * @generated
	 */
	public Adapter createTupleExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.TuplePart <em>Tuple Part</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.TuplePart
	 * @generated
	 */
	public Adapter createTuplePartAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.MapExp <em>Map Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.MapExp
	 * @generated
	 */
	public Adapter createMapExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.MapElement <em>Map Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.MapElement
	 * @generated
	 */
	public Adapter createMapElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.EnumLiteralExp <em>Enum Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.EnumLiteralExp
	 * @generated
	 */
	public Adapter createEnumLiteralExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OclUndefinedExp <em>Ocl Undefined Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OclUndefinedExp
	 * @generated
	 */
	public Adapter createOclUndefinedExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.StaticPropertyCallExp <em>Static Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.StaticPropertyCallExp
	 * @generated
	 */
	public Adapter createStaticPropertyCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.StaticPropertyCall <em>Static Property Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.StaticPropertyCall
	 * @generated
	 */
	public Adapter createStaticPropertyCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.StaticNavigationOrAttributeCall <em>Static Navigation Or Attribute Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.StaticNavigationOrAttributeCall
	 * @generated
	 */
	public Adapter createStaticNavigationOrAttributeCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.StaticOperationCall <em>Static Operation Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.StaticOperationCall
	 * @generated
	 */
	public Adapter createStaticOperationCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.PropertyCallExp <em>Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.PropertyCallExp
	 * @generated
	 */
	public Adapter createPropertyCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.PropertyCall <em>Property Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.PropertyCall
	 * @generated
	 */
	public Adapter createPropertyCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.NavigationOrAttributeCall <em>Navigation Or Attribute Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.NavigationOrAttributeCall
	 * @generated
	 */
	public Adapter createNavigationOrAttributeCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OperationCall <em>Operation Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OperationCall
	 * @generated
	 */
	public Adapter createOperationCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OperatorCallExp <em>Operator Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OperatorCallExp
	 * @generated
	 */
	public Adapter createOperatorCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.NotOpCallExp <em>Not Op Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.NotOpCallExp
	 * @generated
	 */
	public Adapter createNotOpCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.RelOpCallExp <em>Rel Op Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.RelOpCallExp
	 * @generated
	 */
	public Adapter createRelOpCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.EqOpCallExp <em>Eq Op Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.EqOpCallExp
	 * @generated
	 */
	public Adapter createEqOpCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.AddOpCallExp <em>Add Op Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.AddOpCallExp
	 * @generated
	 */
	public Adapter createAddOpCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.IntOpCallExp <em>Int Op Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.IntOpCallExp
	 * @generated
	 */
	public Adapter createIntOpCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.MulOpCallExp <em>Mul Op Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.MulOpCallExp
	 * @generated
	 */
	public Adapter createMulOpCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.LambdaCallExp <em>Lambda Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.LambdaCallExp
	 * @generated
	 */
	public Adapter createLambdaCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.BraceExp <em>Brace Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.BraceExp
	 * @generated
	 */
	public Adapter createBraceExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.CollectionOperationCall <em>Collection Operation Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.CollectionOperationCall
	 * @generated
	 */
	public Adapter createCollectionOperationCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.LoopExp <em>Loop Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.LoopExp
	 * @generated
	 */
	public Adapter createLoopExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.IterateExp <em>Iterate Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.IterateExp
	 * @generated
	 */
	public Adapter createIterateExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.IteratorExp <em>Iterator Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.IteratorExp
	 * @generated
	 */
	public Adapter createIteratorExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.LetExp <em>Let Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.LetExp
	 * @generated
	 */
	public Adapter createLetExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.IfExp <em>If Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.IfExp
	 * @generated
	 */
	public Adapter createIfExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.VariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.VariableDeclaration
	 * @generated
	 */
	public Adapter createVariableDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.LocalVariable <em>Local Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.LocalVariable
	 * @generated
	 */
	public Adapter createLocalVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.Iterator <em>Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.Iterator
	 * @generated
	 */
	public Adapter createIteratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.CollectionType <em>Collection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.CollectionType
	 * @generated
	 */
	public Adapter createCollectionTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OclType <em>Ocl Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OclType
	 * @generated
	 */
	public Adapter createOclTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OclModelElementExp <em>Ocl Model Element Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OclModelElementExp
	 * @generated
	 */
	public Adapter createOclModelElementExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.Primitive <em>Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.Primitive
	 * @generated
	 */
	public Adapter createPrimitiveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.StringType <em>String Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.StringType
	 * @generated
	 */
	public Adapter createStringTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.BooleanType <em>Boolean Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.BooleanType
	 * @generated
	 */
	public Adapter createBooleanTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.NumericType <em>Numeric Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.NumericType
	 * @generated
	 */
	public Adapter createNumericTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.IntegerType <em>Integer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.IntegerType
	 * @generated
	 */
	public Adapter createIntegerTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.RealType <em>Real Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.RealType
	 * @generated
	 */
	public Adapter createRealTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.BagType <em>Bag Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.BagType
	 * @generated
	 */
	public Adapter createBagTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OrderedSetType <em>Ordered Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OrderedSetType
	 * @generated
	 */
	public Adapter createOrderedSetTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.SequenceType <em>Sequence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.SequenceType
	 * @generated
	 */
	public Adapter createSequenceTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.SetType <em>Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.SetType
	 * @generated
	 */
	public Adapter createSetTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OclAnyType <em>Ocl Any Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OclAnyType
	 * @generated
	 */
	public Adapter createOclAnyTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.TupleType <em>Tuple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.TupleType
	 * @generated
	 */
	public Adapter createTupleTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.TupleTypeAttribute <em>Tuple Type Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.TupleTypeAttribute
	 * @generated
	 */
	public Adapter createTupleTypeAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OclModelElement <em>Ocl Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OclModelElement
	 * @generated
	 */
	public Adapter createOclModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.MapType <em>Map Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.MapType
	 * @generated
	 */
	public Adapter createMapTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.LambdaType <em>Lambda Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.LambdaType
	 * @generated
	 */
	public Adapter createLambdaTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.EnvType <em>Env Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.EnvType
	 * @generated
	 */
	public Adapter createEnvTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OclFeatureDefinition <em>Ocl Feature Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OclFeatureDefinition
	 * @generated
	 */
	public Adapter createOclFeatureDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OclContextDefinition <em>Ocl Context Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OclContextDefinition
	 * @generated
	 */
	public Adapter createOclContextDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OclFeature <em>Ocl Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OclFeature
	 * @generated
	 */
	public Adapter createOclFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.Attribute
	 * @generated
	 */
	public Adapter createAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.Operation
	 * @generated
	 */
	public Adapter createOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OclModel <em>Ocl Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OclModel
	 * @generated
	 */
	public Adapter createOclModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OclMetamodel <em>Ocl Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OclMetamodel
	 * @generated
	 */
	public Adapter createOclMetamodelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link be.ac.vub.simpleocl.OclInstanceModel <em>Ocl Instance Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see be.ac.vub.simpleocl.OclInstanceModel
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
