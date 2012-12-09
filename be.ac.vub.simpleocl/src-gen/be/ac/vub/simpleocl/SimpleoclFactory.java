/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see be.ac.vub.simpleocl.SimpleoclPackage
 * @generated
 */
public interface SimpleoclFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SimpleoclFactory eINSTANCE = be.ac.vub.simpleocl.impl.SimpleoclFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Module</em>'.
	 * @generated
	 */
	Module createModule();

	/**
	 * Returns a new object of class '<em>Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Import</em>'.
	 * @generated
	 */
	Import createImport();

	/**
	 * Returns a new object of class '<em>Variable Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable Exp</em>'.
	 * @generated
	 */
	VariableExp createVariableExp();

	/**
	 * Returns a new object of class '<em>Super Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Super Exp</em>'.
	 * @generated
	 */
	SuperExp createSuperExp();

	/**
	 * Returns a new object of class '<em>Self Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Self Exp</em>'.
	 * @generated
	 */
	SelfExp createSelfExp();

	/**
	 * Returns a new object of class '<em>Env Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Env Exp</em>'.
	 * @generated
	 */
	EnvExp createEnvExp();

	/**
	 * Returns a new object of class '<em>String Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Exp</em>'.
	 * @generated
	 */
	StringExp createStringExp();

	/**
	 * Returns a new object of class '<em>Boolean Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Exp</em>'.
	 * @generated
	 */
	BooleanExp createBooleanExp();

	/**
	 * Returns a new object of class '<em>Real Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Real Exp</em>'.
	 * @generated
	 */
	RealExp createRealExp();

	/**
	 * Returns a new object of class '<em>Integer Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Exp</em>'.
	 * @generated
	 */
	IntegerExp createIntegerExp();

	/**
	 * Returns a new object of class '<em>Collection Range</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collection Range</em>'.
	 * @generated
	 */
	CollectionRange createCollectionRange();

	/**
	 * Returns a new object of class '<em>Collection Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collection Item</em>'.
	 * @generated
	 */
	CollectionItem createCollectionItem();

	/**
	 * Returns a new object of class '<em>Bag Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bag Exp</em>'.
	 * @generated
	 */
	BagExp createBagExp();

	/**
	 * Returns a new object of class '<em>Ordered Set Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ordered Set Exp</em>'.
	 * @generated
	 */
	OrderedSetExp createOrderedSetExp();

	/**
	 * Returns a new object of class '<em>Sequence Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sequence Exp</em>'.
	 * @generated
	 */
	SequenceExp createSequenceExp();

	/**
	 * Returns a new object of class '<em>Set Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Set Exp</em>'.
	 * @generated
	 */
	SetExp createSetExp();

	/**
	 * Returns a new object of class '<em>Tuple Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tuple Exp</em>'.
	 * @generated
	 */
	TupleExp createTupleExp();

	/**
	 * Returns a new object of class '<em>Tuple Part</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tuple Part</em>'.
	 * @generated
	 */
	TuplePart createTuplePart();

	/**
	 * Returns a new object of class '<em>Map Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Map Exp</em>'.
	 * @generated
	 */
	MapExp createMapExp();

	/**
	 * Returns a new object of class '<em>Map Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Map Element</em>'.
	 * @generated
	 */
	MapElement createMapElement();

	/**
	 * Returns a new object of class '<em>Enum Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Enum Literal Exp</em>'.
	 * @generated
	 */
	EnumLiteralExp createEnumLiteralExp();

	/**
	 * Returns a new object of class '<em>Ocl Undefined Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Undefined Exp</em>'.
	 * @generated
	 */
	OclUndefinedExp createOclUndefinedExp();

	/**
	 * Returns a new object of class '<em>Static Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Static Property Call Exp</em>'.
	 * @generated
	 */
	StaticPropertyCallExp createStaticPropertyCallExp();

	/**
	 * Returns a new object of class '<em>Static Navigation Or Attribute Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Static Navigation Or Attribute Call</em>'.
	 * @generated
	 */
	StaticNavigationOrAttributeCall createStaticNavigationOrAttributeCall();

	/**
	 * Returns a new object of class '<em>Static Operation Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Static Operation Call</em>'.
	 * @generated
	 */
	StaticOperationCall createStaticOperationCall();

	/**
	 * Returns a new object of class '<em>Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property Call Exp</em>'.
	 * @generated
	 */
	PropertyCallExp createPropertyCallExp();

	/**
	 * Returns a new object of class '<em>Navigation Or Attribute Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigation Or Attribute Call</em>'.
	 * @generated
	 */
	NavigationOrAttributeCall createNavigationOrAttributeCall();

	/**
	 * Returns a new object of class '<em>Operation Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Call</em>'.
	 * @generated
	 */
	OperationCall createOperationCall();

	/**
	 * Returns a new object of class '<em>Operator Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operator Call Exp</em>'.
	 * @generated
	 */
	OperatorCallExp createOperatorCallExp();

	/**
	 * Returns a new object of class '<em>Not Op Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Not Op Call Exp</em>'.
	 * @generated
	 */
	NotOpCallExp createNotOpCallExp();

	/**
	 * Returns a new object of class '<em>Rel Op Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rel Op Call Exp</em>'.
	 * @generated
	 */
	RelOpCallExp createRelOpCallExp();

	/**
	 * Returns a new object of class '<em>Eq Op Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Eq Op Call Exp</em>'.
	 * @generated
	 */
	EqOpCallExp createEqOpCallExp();

	/**
	 * Returns a new object of class '<em>Add Op Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Op Call Exp</em>'.
	 * @generated
	 */
	AddOpCallExp createAddOpCallExp();

	/**
	 * Returns a new object of class '<em>Int Op Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Int Op Call Exp</em>'.
	 * @generated
	 */
	IntOpCallExp createIntOpCallExp();

	/**
	 * Returns a new object of class '<em>Mul Op Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mul Op Call Exp</em>'.
	 * @generated
	 */
	MulOpCallExp createMulOpCallExp();

	/**
	 * Returns a new object of class '<em>Lambda Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lambda Call Exp</em>'.
	 * @generated
	 */
	LambdaCallExp createLambdaCallExp();

	/**
	 * Returns a new object of class '<em>Brace Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Brace Exp</em>'.
	 * @generated
	 */
	BraceExp createBraceExp();

	/**
	 * Returns a new object of class '<em>Collection Operation Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collection Operation Call</em>'.
	 * @generated
	 */
	CollectionOperationCall createCollectionOperationCall();

	/**
	 * Returns a new object of class '<em>Iterate Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Iterate Exp</em>'.
	 * @generated
	 */
	IterateExp createIterateExp();

	/**
	 * Returns a new object of class '<em>Iterator Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Iterator Exp</em>'.
	 * @generated
	 */
	IteratorExp createIteratorExp();

	/**
	 * Returns a new object of class '<em>Let Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Let Exp</em>'.
	 * @generated
	 */
	LetExp createLetExp();

	/**
	 * Returns a new object of class '<em>If Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>If Exp</em>'.
	 * @generated
	 */
	IfExp createIfExp();

	/**
	 * Returns a new object of class '<em>Local Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Local Variable</em>'.
	 * @generated
	 */
	LocalVariable createLocalVariable();

	/**
	 * Returns a new object of class '<em>Iterator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Iterator</em>'.
	 * @generated
	 */
	Iterator createIterator();

	/**
	 * Returns a new object of class '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter</em>'.
	 * @generated
	 */
	Parameter createParameter();

	/**
	 * Returns a new object of class '<em>Collection Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collection Type</em>'.
	 * @generated
	 */
	CollectionType createCollectionType();

	/**
	 * Returns a new object of class '<em>Ocl Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Type</em>'.
	 * @generated
	 */
	OclType createOclType();

	/**
	 * Returns a new object of class '<em>Ocl Model Element Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Model Element Exp</em>'.
	 * @generated
	 */
	OclModelElementExp createOclModelElementExp();

	/**
	 * Returns a new object of class '<em>String Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Type</em>'.
	 * @generated
	 */
	StringType createStringType();

	/**
	 * Returns a new object of class '<em>Boolean Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Type</em>'.
	 * @generated
	 */
	BooleanType createBooleanType();

	/**
	 * Returns a new object of class '<em>Integer Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Type</em>'.
	 * @generated
	 */
	IntegerType createIntegerType();

	/**
	 * Returns a new object of class '<em>Real Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Real Type</em>'.
	 * @generated
	 */
	RealType createRealType();

	/**
	 * Returns a new object of class '<em>Bag Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bag Type</em>'.
	 * @generated
	 */
	BagType createBagType();

	/**
	 * Returns a new object of class '<em>Ordered Set Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ordered Set Type</em>'.
	 * @generated
	 */
	OrderedSetType createOrderedSetType();

	/**
	 * Returns a new object of class '<em>Sequence Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sequence Type</em>'.
	 * @generated
	 */
	SequenceType createSequenceType();

	/**
	 * Returns a new object of class '<em>Set Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Set Type</em>'.
	 * @generated
	 */
	SetType createSetType();

	/**
	 * Returns a new object of class '<em>Ocl Any Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Any Type</em>'.
	 * @generated
	 */
	OclAnyType createOclAnyType();

	/**
	 * Returns a new object of class '<em>Tuple Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tuple Type</em>'.
	 * @generated
	 */
	TupleType createTupleType();

	/**
	 * Returns a new object of class '<em>Tuple Type Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tuple Type Attribute</em>'.
	 * @generated
	 */
	TupleTypeAttribute createTupleTypeAttribute();

	/**
	 * Returns a new object of class '<em>Ocl Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Model Element</em>'.
	 * @generated
	 */
	OclModelElement createOclModelElement();

	/**
	 * Returns a new object of class '<em>Map Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Map Type</em>'.
	 * @generated
	 */
	MapType createMapType();

	/**
	 * Returns a new object of class '<em>Lambda Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lambda Type</em>'.
	 * @generated
	 */
	LambdaType createLambdaType();

	/**
	 * Returns a new object of class '<em>Env Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Env Type</em>'.
	 * @generated
	 */
	EnvType createEnvType();

	/**
	 * Returns a new object of class '<em>Ocl Feature Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Feature Definition</em>'.
	 * @generated
	 */
	OclFeatureDefinition createOclFeatureDefinition();

	/**
	 * Returns a new object of class '<em>Ocl Context Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Context Definition</em>'.
	 * @generated
	 */
	OclContextDefinition createOclContextDefinition();

	/**
	 * Returns a new object of class '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute</em>'.
	 * @generated
	 */
	Attribute createAttribute();

	/**
	 * Returns a new object of class '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation</em>'.
	 * @generated
	 */
	Operation createOperation();

	/**
	 * Returns a new object of class '<em>Ocl Metamodel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Metamodel</em>'.
	 * @generated
	 */
	OclMetamodel createOclMetamodel();

	/**
	 * Returns a new object of class '<em>Ocl Instance Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Instance Model</em>'.
	 * @generated
	 */
	OclInstanceModel createOclInstanceModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SimpleoclPackage getSimpleoclPackage();

} //SimpleoclFactory
