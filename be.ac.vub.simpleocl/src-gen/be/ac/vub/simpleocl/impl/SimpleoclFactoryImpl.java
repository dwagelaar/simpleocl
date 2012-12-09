/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import be.ac.vub.simpleocl.AddOpCallExp;
import be.ac.vub.simpleocl.Attribute;
import be.ac.vub.simpleocl.BagExp;
import be.ac.vub.simpleocl.BagType;
import be.ac.vub.simpleocl.BooleanExp;
import be.ac.vub.simpleocl.BooleanType;
import be.ac.vub.simpleocl.BraceExp;
import be.ac.vub.simpleocl.CollectionItem;
import be.ac.vub.simpleocl.CollectionOperationCall;
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
import be.ac.vub.simpleocl.MapElement;
import be.ac.vub.simpleocl.MapExp;
import be.ac.vub.simpleocl.MapType;
import be.ac.vub.simpleocl.Module;
import be.ac.vub.simpleocl.MulOpCallExp;
import be.ac.vub.simpleocl.NavigationOrAttributeCall;
import be.ac.vub.simpleocl.NotOpCallExp;
import be.ac.vub.simpleocl.OclAnyType;
import be.ac.vub.simpleocl.OclContextDefinition;
import be.ac.vub.simpleocl.OclFeatureDefinition;
import be.ac.vub.simpleocl.OclInstanceModel;
import be.ac.vub.simpleocl.OclMetamodel;
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
import be.ac.vub.simpleocl.PropertyCallExp;
import be.ac.vub.simpleocl.RealExp;
import be.ac.vub.simpleocl.RealType;
import be.ac.vub.simpleocl.RelOpCallExp;
import be.ac.vub.simpleocl.SelfExp;
import be.ac.vub.simpleocl.SequenceExp;
import be.ac.vub.simpleocl.SequenceType;
import be.ac.vub.simpleocl.SetExp;
import be.ac.vub.simpleocl.SetType;
import be.ac.vub.simpleocl.SimpleoclFactory;
import be.ac.vub.simpleocl.SimpleoclPackage;
import be.ac.vub.simpleocl.StaticNavigationOrAttributeCall;
import be.ac.vub.simpleocl.StaticOperationCall;
import be.ac.vub.simpleocl.StaticPropertyCallExp;
import be.ac.vub.simpleocl.StringExp;
import be.ac.vub.simpleocl.StringType;
import be.ac.vub.simpleocl.SuperExp;
import be.ac.vub.simpleocl.TupleExp;
import be.ac.vub.simpleocl.TuplePart;
import be.ac.vub.simpleocl.TupleType;
import be.ac.vub.simpleocl.TupleTypeAttribute;
import be.ac.vub.simpleocl.VariableExp;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SimpleoclFactoryImpl extends EFactoryImpl implements SimpleoclFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SimpleoclFactory init() {
		try {
			SimpleoclFactory theSimpleoclFactory = (SimpleoclFactory)EPackage.Registry.INSTANCE.getEFactory("http://soft.vub.ac.be/simpleocl/2011/SimpleOCL"); 
			if (theSimpleoclFactory != null) {
				return theSimpleoclFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SimpleoclFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleoclFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SimpleoclPackage.MODULE: return createModule();
			case SimpleoclPackage.IMPORT: return createImport();
			case SimpleoclPackage.VARIABLE_EXP: return createVariableExp();
			case SimpleoclPackage.SUPER_EXP: return createSuperExp();
			case SimpleoclPackage.SELF_EXP: return createSelfExp();
			case SimpleoclPackage.ENV_EXP: return createEnvExp();
			case SimpleoclPackage.STRING_EXP: return createStringExp();
			case SimpleoclPackage.BOOLEAN_EXP: return createBooleanExp();
			case SimpleoclPackage.REAL_EXP: return createRealExp();
			case SimpleoclPackage.INTEGER_EXP: return createIntegerExp();
			case SimpleoclPackage.COLLECTION_RANGE: return createCollectionRange();
			case SimpleoclPackage.COLLECTION_ITEM: return createCollectionItem();
			case SimpleoclPackage.BAG_EXP: return createBagExp();
			case SimpleoclPackage.ORDERED_SET_EXP: return createOrderedSetExp();
			case SimpleoclPackage.SEQUENCE_EXP: return createSequenceExp();
			case SimpleoclPackage.SET_EXP: return createSetExp();
			case SimpleoclPackage.TUPLE_EXP: return createTupleExp();
			case SimpleoclPackage.TUPLE_PART: return createTuplePart();
			case SimpleoclPackage.MAP_EXP: return createMapExp();
			case SimpleoclPackage.MAP_ELEMENT: return createMapElement();
			case SimpleoclPackage.ENUM_LITERAL_EXP: return createEnumLiteralExp();
			case SimpleoclPackage.OCL_UNDEFINED_EXP: return createOclUndefinedExp();
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP: return createStaticPropertyCallExp();
			case SimpleoclPackage.STATIC_NAVIGATION_OR_ATTRIBUTE_CALL: return createStaticNavigationOrAttributeCall();
			case SimpleoclPackage.STATIC_OPERATION_CALL: return createStaticOperationCall();
			case SimpleoclPackage.PROPERTY_CALL_EXP: return createPropertyCallExp();
			case SimpleoclPackage.NAVIGATION_OR_ATTRIBUTE_CALL: return createNavigationOrAttributeCall();
			case SimpleoclPackage.OPERATION_CALL: return createOperationCall();
			case SimpleoclPackage.OPERATOR_CALL_EXP: return createOperatorCallExp();
			case SimpleoclPackage.NOT_OP_CALL_EXP: return createNotOpCallExp();
			case SimpleoclPackage.REL_OP_CALL_EXP: return createRelOpCallExp();
			case SimpleoclPackage.EQ_OP_CALL_EXP: return createEqOpCallExp();
			case SimpleoclPackage.ADD_OP_CALL_EXP: return createAddOpCallExp();
			case SimpleoclPackage.INT_OP_CALL_EXP: return createIntOpCallExp();
			case SimpleoclPackage.MUL_OP_CALL_EXP: return createMulOpCallExp();
			case SimpleoclPackage.LAMBDA_CALL_EXP: return createLambdaCallExp();
			case SimpleoclPackage.BRACE_EXP: return createBraceExp();
			case SimpleoclPackage.COLLECTION_OPERATION_CALL: return createCollectionOperationCall();
			case SimpleoclPackage.ITERATE_EXP: return createIterateExp();
			case SimpleoclPackage.ITERATOR_EXP: return createIteratorExp();
			case SimpleoclPackage.LET_EXP: return createLetExp();
			case SimpleoclPackage.IF_EXP: return createIfExp();
			case SimpleoclPackage.LOCAL_VARIABLE: return createLocalVariable();
			case SimpleoclPackage.ITERATOR: return createIterator();
			case SimpleoclPackage.PARAMETER: return createParameter();
			case SimpleoclPackage.COLLECTION_TYPE: return createCollectionType();
			case SimpleoclPackage.OCL_TYPE: return createOclType();
			case SimpleoclPackage.OCL_MODEL_ELEMENT_EXP: return createOclModelElementExp();
			case SimpleoclPackage.STRING_TYPE: return createStringType();
			case SimpleoclPackage.BOOLEAN_TYPE: return createBooleanType();
			case SimpleoclPackage.INTEGER_TYPE: return createIntegerType();
			case SimpleoclPackage.REAL_TYPE: return createRealType();
			case SimpleoclPackage.BAG_TYPE: return createBagType();
			case SimpleoclPackage.ORDERED_SET_TYPE: return createOrderedSetType();
			case SimpleoclPackage.SEQUENCE_TYPE: return createSequenceType();
			case SimpleoclPackage.SET_TYPE: return createSetType();
			case SimpleoclPackage.OCL_ANY_TYPE: return createOclAnyType();
			case SimpleoclPackage.TUPLE_TYPE: return createTupleType();
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE: return createTupleTypeAttribute();
			case SimpleoclPackage.OCL_MODEL_ELEMENT: return createOclModelElement();
			case SimpleoclPackage.MAP_TYPE: return createMapType();
			case SimpleoclPackage.LAMBDA_TYPE: return createLambdaType();
			case SimpleoclPackage.ENV_TYPE: return createEnvType();
			case SimpleoclPackage.OCL_FEATURE_DEFINITION: return createOclFeatureDefinition();
			case SimpleoclPackage.OCL_CONTEXT_DEFINITION: return createOclContextDefinition();
			case SimpleoclPackage.ATTRIBUTE: return createAttribute();
			case SimpleoclPackage.OPERATION: return createOperation();
			case SimpleoclPackage.OCL_METAMODEL: return createOclMetamodel();
			case SimpleoclPackage.OCL_INSTANCE_MODEL: return createOclInstanceModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case SimpleoclPackage.BOOLEAN:
				return createBooleanFromString(eDataType, initialValue);
			case SimpleoclPackage.DOUBLE:
				return createDoubleFromString(eDataType, initialValue);
			case SimpleoclPackage.INTEGER:
				return createIntegerFromString(eDataType, initialValue);
			case SimpleoclPackage.STRING:
				return createStringFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case SimpleoclPackage.BOOLEAN:
				return convertBooleanToString(eDataType, instanceValue);
			case SimpleoclPackage.DOUBLE:
				return convertDoubleToString(eDataType, instanceValue);
			case SimpleoclPackage.INTEGER:
				return convertIntegerToString(eDataType, instanceValue);
			case SimpleoclPackage.STRING:
				return convertStringToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Module createModule() {
		ModuleImpl module = new ModuleImpl();
		return module;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Import createImport() {
		ImportImpl import_ = new ImportImpl();
		return import_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableExp createVariableExp() {
		VariableExpImpl variableExp = new VariableExpImpl();
		return variableExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SuperExp createSuperExp() {
		SuperExpImpl superExp = new SuperExpImpl();
		return superExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelfExp createSelfExp() {
		SelfExpImpl selfExp = new SelfExpImpl();
		return selfExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvExp createEnvExp() {
		EnvExpImpl envExp = new EnvExpImpl();
		return envExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringExp createStringExp() {
		StringExpImpl stringExp = new StringExpImpl();
		return stringExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanExp createBooleanExp() {
		BooleanExpImpl booleanExp = new BooleanExpImpl();
		return booleanExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RealExp createRealExp() {
		RealExpImpl realExp = new RealExpImpl();
		return realExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerExp createIntegerExp() {
		IntegerExpImpl integerExp = new IntegerExpImpl();
		return integerExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionRange createCollectionRange() {
		CollectionRangeImpl collectionRange = new CollectionRangeImpl();
		return collectionRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionItem createCollectionItem() {
		CollectionItemImpl collectionItem = new CollectionItemImpl();
		return collectionItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BagExp createBagExp() {
		BagExpImpl bagExp = new BagExpImpl();
		return bagExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderedSetExp createOrderedSetExp() {
		OrderedSetExpImpl orderedSetExp = new OrderedSetExpImpl();
		return orderedSetExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceExp createSequenceExp() {
		SequenceExpImpl sequenceExp = new SequenceExpImpl();
		return sequenceExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SetExp createSetExp() {
		SetExpImpl setExp = new SetExpImpl();
		return setExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TupleExp createTupleExp() {
		TupleExpImpl tupleExp = new TupleExpImpl();
		return tupleExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TuplePart createTuplePart() {
		TuplePartImpl tuplePart = new TuplePartImpl();
		return tuplePart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapExp createMapExp() {
		MapExpImpl mapExp = new MapExpImpl();
		return mapExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapElement createMapElement() {
		MapElementImpl mapElement = new MapElementImpl();
		return mapElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumLiteralExp createEnumLiteralExp() {
		EnumLiteralExpImpl enumLiteralExp = new EnumLiteralExpImpl();
		return enumLiteralExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclUndefinedExp createOclUndefinedExp() {
		OclUndefinedExpImpl oclUndefinedExp = new OclUndefinedExpImpl();
		return oclUndefinedExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticPropertyCallExp createStaticPropertyCallExp() {
		StaticPropertyCallExpImpl staticPropertyCallExp = new StaticPropertyCallExpImpl();
		return staticPropertyCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticNavigationOrAttributeCall createStaticNavigationOrAttributeCall() {
		StaticNavigationOrAttributeCallImpl staticNavigationOrAttributeCall = new StaticNavigationOrAttributeCallImpl();
		return staticNavigationOrAttributeCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticOperationCall createStaticOperationCall() {
		StaticOperationCallImpl staticOperationCall = new StaticOperationCallImpl();
		return staticOperationCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyCallExp createPropertyCallExp() {
		PropertyCallExpImpl propertyCallExp = new PropertyCallExpImpl();
		return propertyCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationOrAttributeCall createNavigationOrAttributeCall() {
		NavigationOrAttributeCallImpl navigationOrAttributeCall = new NavigationOrAttributeCallImpl();
		return navigationOrAttributeCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationCall createOperationCall() {
		OperationCallImpl operationCall = new OperationCallImpl();
		return operationCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperatorCallExp createOperatorCallExp() {
		OperatorCallExpImpl operatorCallExp = new OperatorCallExpImpl();
		return operatorCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotOpCallExp createNotOpCallExp() {
		NotOpCallExpImpl notOpCallExp = new NotOpCallExpImpl();
		return notOpCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelOpCallExp createRelOpCallExp() {
		RelOpCallExpImpl relOpCallExp = new RelOpCallExpImpl();
		return relOpCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EqOpCallExp createEqOpCallExp() {
		EqOpCallExpImpl eqOpCallExp = new EqOpCallExpImpl();
		return eqOpCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddOpCallExp createAddOpCallExp() {
		AddOpCallExpImpl addOpCallExp = new AddOpCallExpImpl();
		return addOpCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntOpCallExp createIntOpCallExp() {
		IntOpCallExpImpl intOpCallExp = new IntOpCallExpImpl();
		return intOpCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MulOpCallExp createMulOpCallExp() {
		MulOpCallExpImpl mulOpCallExp = new MulOpCallExpImpl();
		return mulOpCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LambdaCallExp createLambdaCallExp() {
		LambdaCallExpImpl lambdaCallExp = new LambdaCallExpImpl();
		return lambdaCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BraceExp createBraceExp() {
		BraceExpImpl braceExp = new BraceExpImpl();
		return braceExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionOperationCall createCollectionOperationCall() {
		CollectionOperationCallImpl collectionOperationCall = new CollectionOperationCallImpl();
		return collectionOperationCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IterateExp createIterateExp() {
		IterateExpImpl iterateExp = new IterateExpImpl();
		return iterateExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IteratorExp createIteratorExp() {
		IteratorExpImpl iteratorExp = new IteratorExpImpl();
		return iteratorExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LetExp createLetExp() {
		LetExpImpl letExp = new LetExpImpl();
		return letExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IfExp createIfExp() {
		IfExpImpl ifExp = new IfExpImpl();
		return ifExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalVariable createLocalVariable() {
		LocalVariableImpl localVariable = new LocalVariableImpl();
		return localVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Iterator createIterator() {
		IteratorImpl iterator = new IteratorImpl();
		return iterator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionType createCollectionType() {
		CollectionTypeImpl collectionType = new CollectionTypeImpl();
		return collectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclType createOclType() {
		OclTypeImpl oclType = new OclTypeImpl();
		return oclType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclModelElementExp createOclModelElementExp() {
		OclModelElementExpImpl oclModelElementExp = new OclModelElementExpImpl();
		return oclModelElementExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringType createStringType() {
		StringTypeImpl stringType = new StringTypeImpl();
		return stringType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanType createBooleanType() {
		BooleanTypeImpl booleanType = new BooleanTypeImpl();
		return booleanType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerType createIntegerType() {
		IntegerTypeImpl integerType = new IntegerTypeImpl();
		return integerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RealType createRealType() {
		RealTypeImpl realType = new RealTypeImpl();
		return realType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BagType createBagType() {
		BagTypeImpl bagType = new BagTypeImpl();
		return bagType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderedSetType createOrderedSetType() {
		OrderedSetTypeImpl orderedSetType = new OrderedSetTypeImpl();
		return orderedSetType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceType createSequenceType() {
		SequenceTypeImpl sequenceType = new SequenceTypeImpl();
		return sequenceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SetType createSetType() {
		SetTypeImpl setType = new SetTypeImpl();
		return setType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclAnyType createOclAnyType() {
		OclAnyTypeImpl oclAnyType = new OclAnyTypeImpl();
		return oclAnyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TupleType createTupleType() {
		TupleTypeImpl tupleType = new TupleTypeImpl();
		return tupleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TupleTypeAttribute createTupleTypeAttribute() {
		TupleTypeAttributeImpl tupleTypeAttribute = new TupleTypeAttributeImpl();
		return tupleTypeAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclModelElement createOclModelElement() {
		OclModelElementImpl oclModelElement = new OclModelElementImpl();
		return oclModelElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapType createMapType() {
		MapTypeImpl mapType = new MapTypeImpl();
		return mapType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LambdaType createLambdaType() {
		LambdaTypeImpl lambdaType = new LambdaTypeImpl();
		return lambdaType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvType createEnvType() {
		EnvTypeImpl envType = new EnvTypeImpl();
		return envType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclFeatureDefinition createOclFeatureDefinition() {
		OclFeatureDefinitionImpl oclFeatureDefinition = new OclFeatureDefinitionImpl();
		return oclFeatureDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclContextDefinition createOclContextDefinition() {
		OclContextDefinitionImpl oclContextDefinition = new OclContextDefinitionImpl();
		return oclContextDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation createOperation() {
		OperationImpl operation = new OperationImpl();
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclMetamodel createOclMetamodel() {
		OclMetamodelImpl oclMetamodel = new OclMetamodelImpl();
		return oclMetamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclInstanceModel createOclInstanceModel() {
		OclInstanceModelImpl oclInstanceModel = new OclInstanceModelImpl();
		return oclInstanceModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean createBooleanFromString(EDataType eDataType, String initialValue) {
		return (Boolean)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBooleanToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double createDoubleFromString(EDataType eDataType, String initialValue) {
		return (Double)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDoubleToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer createIntegerFromString(EDataType eDataType, String initialValue) {
		return (Integer)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIntegerToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createStringFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStringToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleoclPackage getSimpleoclPackage() {
		return (SimpleoclPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SimpleoclPackage getPackage() {
		return SimpleoclPackage.eINSTANCE;
	}

} //SimpleoclFactoryImpl
