/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getOclExpression <em>Ocl Expression</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getOperation <em>Operation</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getMapType2 <em>Map Type2</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getMapType <em>Map Type</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getCollectionTypes <em>Collection Types</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getTupleTypeAttribute <em>Tuple Type Attribute</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getVariableDeclaration <em>Variable Declaration</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getLambdaReturnType <em>Lambda Return Type</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getLambdaArgType <em>Lambda Arg Type</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclType#getStaticPropertyCall <em>Static Property Call</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType()
 * @model
 * @generated
 */
public interface OclType extends LocatedElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_Name()
	 * @model unique="false" dataType="org.eclipselabs.simpleocl.String" required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Definitions</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclContextDefinition#getContext_ <em>Context </em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definitions</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definitions</em>' container reference.
	 * @see #setDefinitions(OclContextDefinition)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_Definitions()
	 * @see org.eclipselabs.simpleocl.OclContextDefinition#getContext_
	 * @model opposite="context_" transient="false" ordered="false"
	 * @generated
	 */
	OclContextDefinition getDefinitions();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getDefinitions <em>Definitions</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definitions</em>' container reference.
	 * @see #getDefinitions()
	 * @generated
	 */
	void setDefinitions(OclContextDefinition value);

	/**
	 * Returns the value of the '<em><b>Ocl Expression</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclExpression#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Expression</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl Expression</em>' container reference.
	 * @see #setOclExpression(OclExpression)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_OclExpression()
	 * @see org.eclipselabs.simpleocl.OclExpression#getType
	 * @model opposite="type" transient="false" ordered="false"
	 * @generated
	 */
	OclExpression getOclExpression();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getOclExpression <em>Ocl Expression</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Expression</em>' container reference.
	 * @see #getOclExpression()
	 * @generated
	 */
	void setOclExpression(OclExpression value);

	/**
	 * Returns the value of the '<em><b>Operation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.Operation#getReturnType <em>Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' container reference.
	 * @see #setOperation(Operation)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_Operation()
	 * @see org.eclipselabs.simpleocl.Operation#getReturnType
	 * @model opposite="returnType" transient="false" ordered="false"
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getOperation <em>Operation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' container reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>Map Type2</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.MapType#getValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map Type2</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Map Type2</em>' container reference.
	 * @see #setMapType2(MapType)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_MapType2()
	 * @see org.eclipselabs.simpleocl.MapType#getValueType
	 * @model opposite="valueType" transient="false" ordered="false"
	 * @generated
	 */
	MapType getMapType2();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getMapType2 <em>Map Type2</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Map Type2</em>' container reference.
	 * @see #getMapType2()
	 * @generated
	 */
	void setMapType2(MapType value);

	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.Attribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute</em>' container reference.
	 * @see #setAttribute(Attribute)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_Attribute()
	 * @see org.eclipselabs.simpleocl.Attribute#getType
	 * @model opposite="type" transient="false" ordered="false"
	 * @generated
	 */
	Attribute getAttribute();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getAttribute <em>Attribute</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute</em>' container reference.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(Attribute value);

	/**
	 * Returns the value of the '<em><b>Map Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.MapType#getKeyType <em>Key Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Map Type</em>' container reference.
	 * @see #setMapType(MapType)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_MapType()
	 * @see org.eclipselabs.simpleocl.MapType#getKeyType
	 * @model opposite="keyType" transient="false" ordered="false"
	 * @generated
	 */
	MapType getMapType();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getMapType <em>Map Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Map Type</em>' container reference.
	 * @see #getMapType()
	 * @generated
	 */
	void setMapType(MapType value);

	/**
	 * Returns the value of the '<em><b>Collection Types</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.CollectionType#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection Types</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collection Types</em>' container reference.
	 * @see #setCollectionTypes(CollectionType)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_CollectionTypes()
	 * @see org.eclipselabs.simpleocl.CollectionType#getElementType
	 * @model opposite="elementType" transient="false" ordered="false"
	 * @generated
	 */
	CollectionType getCollectionTypes();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getCollectionTypes <em>Collection Types</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collection Types</em>' container reference.
	 * @see #getCollectionTypes()
	 * @generated
	 */
	void setCollectionTypes(CollectionType value);

	/**
	 * Returns the value of the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.TupleTypeAttribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuple Type Attribute</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuple Type Attribute</em>' container reference.
	 * @see #setTupleTypeAttribute(TupleTypeAttribute)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_TupleTypeAttribute()
	 * @see org.eclipselabs.simpleocl.TupleTypeAttribute#getType
	 * @model opposite="type" transient="false" ordered="false"
	 * @generated
	 */
	TupleTypeAttribute getTupleTypeAttribute();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getTupleTypeAttribute <em>Tuple Type Attribute</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tuple Type Attribute</em>' container reference.
	 * @see #getTupleTypeAttribute()
	 * @generated
	 */
	void setTupleTypeAttribute(TupleTypeAttribute value);

	/**
	 * Returns the value of the '<em><b>Variable Declaration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.VariableDeclaration#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Declaration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Declaration</em>' container reference.
	 * @see #setVariableDeclaration(VariableDeclaration)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_VariableDeclaration()
	 * @see org.eclipselabs.simpleocl.VariableDeclaration#getType
	 * @model opposite="type" transient="false" ordered="false"
	 * @generated
	 */
	VariableDeclaration getVariableDeclaration();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getVariableDeclaration <em>Variable Declaration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Declaration</em>' container reference.
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	void setVariableDeclaration(VariableDeclaration value);

	/**
	 * Returns the value of the '<em><b>Lambda Return Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.LambdaType#getReturnType <em>Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lambda Return Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lambda Return Type</em>' container reference.
	 * @see #setLambdaReturnType(LambdaType)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_LambdaReturnType()
	 * @see org.eclipselabs.simpleocl.LambdaType#getReturnType
	 * @model opposite="returnType" transient="false"
	 * @generated
	 */
	LambdaType getLambdaReturnType();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getLambdaReturnType <em>Lambda Return Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lambda Return Type</em>' container reference.
	 * @see #getLambdaReturnType()
	 * @generated
	 */
	void setLambdaReturnType(LambdaType value);

	/**
	 * Returns the value of the '<em><b>Lambda Arg Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.LambdaType#getArgumentTypes <em>Argument Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lambda Arg Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lambda Arg Type</em>' container reference.
	 * @see #setLambdaArgType(LambdaType)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_LambdaArgType()
	 * @see org.eclipselabs.simpleocl.LambdaType#getArgumentTypes
	 * @model opposite="argumentTypes" transient="false"
	 * @generated
	 */
	LambdaType getLambdaArgType();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getLambdaArgType <em>Lambda Arg Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lambda Arg Type</em>' container reference.
	 * @see #getLambdaArgType()
	 * @generated
	 */
	void setLambdaArgType(LambdaType value);

	/**
	 * Returns the value of the '<em><b>Static Property Call</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.StaticPropertyCallExp#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static Property Call</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static Property Call</em>' container reference.
	 * @see #setStaticPropertyCall(StaticPropertyCallExp)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclType_StaticPropertyCall()
	 * @see org.eclipselabs.simpleocl.StaticPropertyCallExp#getSource
	 * @model opposite="source" transient="false"
	 * @generated
	 */
	StaticPropertyCallExp getStaticPropertyCall();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclType#getStaticPropertyCall <em>Static Property Call</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static Property Call</em>' container reference.
	 * @see #getStaticPropertyCall()
	 * @generated
	 */
	void setStaticPropertyCall(StaticPropertyCallExp value);

} // OclType
