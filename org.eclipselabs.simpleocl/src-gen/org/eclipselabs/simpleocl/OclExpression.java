/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.OclExpression#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclExpression#getIfExp3 <em>If Exp3</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclExpression#getAppliedProperty <em>Applied Property</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclExpression#getLetExp <em>Let Exp</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclExpression#getLoopExp <em>Loop Exp</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclExpression#getParentOperation <em>Parent Operation</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclExpression#getInitializedVariable <em>Initialized Variable</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclExpression#getIfExp2 <em>If Exp2</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclExpression#getOwningOperation <em>Owning Operation</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclExpression#getIfExp1 <em>If Exp1</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclExpression#getOwningAttribute <em>Owning Attribute</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclExpression#getAppliedOperator <em>Applied Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression()
 * @model abstract="true"
 * @generated
 */
public interface OclExpression extends LocatedElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclType#getOclExpression <em>Ocl Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(OclType)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression_Type()
	 * @see org.eclipselabs.simpleocl.OclType#getOclExpression
	 * @model opposite="oclExpression" containment="true" ordered="false"
	 * @generated
	 */
	OclType getType();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclExpression#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(OclType value);

	/**
	 * Returns the value of the '<em><b>If Exp3</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.IfExp#getElseExpression <em>Else Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>If Exp3</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>If Exp3</em>' container reference.
	 * @see #setIfExp3(IfExp)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression_IfExp3()
	 * @see org.eclipselabs.simpleocl.IfExp#getElseExpression
	 * @model opposite="elseExpression" transient="false" ordered="false"
	 * @generated
	 */
	IfExp getIfExp3();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclExpression#getIfExp3 <em>If Exp3</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>If Exp3</em>' container reference.
	 * @see #getIfExp3()
	 * @generated
	 */
	void setIfExp3(IfExp value);

	/**
	 * Returns the value of the '<em><b>Applied Property</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.PropertyCallExp#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applied Property</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applied Property</em>' container reference.
	 * @see #setAppliedProperty(PropertyCallExp)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression_AppliedProperty()
	 * @see org.eclipselabs.simpleocl.PropertyCallExp#getSource
	 * @model opposite="source" transient="false" ordered="false"
	 * @generated
	 */
	PropertyCallExp getAppliedProperty();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclExpression#getAppliedProperty <em>Applied Property</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applied Property</em>' container reference.
	 * @see #getAppliedProperty()
	 * @generated
	 */
	void setAppliedProperty(PropertyCallExp value);

	/**
	 * Returns the value of the '<em><b>Let Exp</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.LetExp#getIn_ <em>In </em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Let Exp</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Let Exp</em>' container reference.
	 * @see #setLetExp(LetExp)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression_LetExp()
	 * @see org.eclipselabs.simpleocl.LetExp#getIn_
	 * @model opposite="in_" transient="false" ordered="false"
	 * @generated
	 */
	LetExp getLetExp();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclExpression#getLetExp <em>Let Exp</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Let Exp</em>' container reference.
	 * @see #getLetExp()
	 * @generated
	 */
	void setLetExp(LetExp value);

	/**
	 * Returns the value of the '<em><b>Loop Exp</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.LoopExp#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Exp</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Exp</em>' container reference.
	 * @see #setLoopExp(LoopExp)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression_LoopExp()
	 * @see org.eclipselabs.simpleocl.LoopExp#getBody
	 * @model opposite="body" transient="false" ordered="false"
	 * @generated
	 */
	LoopExp getLoopExp();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclExpression#getLoopExp <em>Loop Exp</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Exp</em>' container reference.
	 * @see #getLoopExp()
	 * @generated
	 */
	void setLoopExp(LoopExp value);

	/**
	 * Returns the value of the '<em><b>Parent Operation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OperationCall#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Operation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Operation</em>' container reference.
	 * @see #setParentOperation(OperationCall)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression_ParentOperation()
	 * @see org.eclipselabs.simpleocl.OperationCall#getArguments
	 * @model opposite="arguments" transient="false" ordered="false"
	 * @generated
	 */
	OperationCall getParentOperation();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclExpression#getParentOperation <em>Parent Operation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Operation</em>' container reference.
	 * @see #getParentOperation()
	 * @generated
	 */
	void setParentOperation(OperationCall value);

	/**
	 * Returns the value of the '<em><b>Initialized Variable</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.LocalVariable#getInitExpression <em>Init Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initialized Variable</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initialized Variable</em>' container reference.
	 * @see #setInitializedVariable(LocalVariable)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression_InitializedVariable()
	 * @see org.eclipselabs.simpleocl.LocalVariable#getInitExpression
	 * @model opposite="initExpression" transient="false" ordered="false"
	 * @generated
	 */
	LocalVariable getInitializedVariable();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclExpression#getInitializedVariable <em>Initialized Variable</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initialized Variable</em>' container reference.
	 * @see #getInitializedVariable()
	 * @generated
	 */
	void setInitializedVariable(LocalVariable value);

	/**
	 * Returns the value of the '<em><b>If Exp2</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.IfExp#getThenExpression <em>Then Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>If Exp2</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>If Exp2</em>' container reference.
	 * @see #setIfExp2(IfExp)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression_IfExp2()
	 * @see org.eclipselabs.simpleocl.IfExp#getThenExpression
	 * @model opposite="thenExpression" transient="false" ordered="false"
	 * @generated
	 */
	IfExp getIfExp2();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclExpression#getIfExp2 <em>If Exp2</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>If Exp2</em>' container reference.
	 * @see #getIfExp2()
	 * @generated
	 */
	void setIfExp2(IfExp value);

	/**
	 * Returns the value of the '<em><b>Owning Operation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.Operation#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Operation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Operation</em>' container reference.
	 * @see #setOwningOperation(Operation)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression_OwningOperation()
	 * @see org.eclipselabs.simpleocl.Operation#getBody
	 * @model opposite="body" transient="false" ordered="false"
	 * @generated
	 */
	Operation getOwningOperation();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclExpression#getOwningOperation <em>Owning Operation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Operation</em>' container reference.
	 * @see #getOwningOperation()
	 * @generated
	 */
	void setOwningOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>If Exp1</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.IfExp#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>If Exp1</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>If Exp1</em>' container reference.
	 * @see #setIfExp1(IfExp)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression_IfExp1()
	 * @see org.eclipselabs.simpleocl.IfExp#getCondition
	 * @model opposite="condition" transient="false" ordered="false"
	 * @generated
	 */
	IfExp getIfExp1();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclExpression#getIfExp1 <em>If Exp1</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>If Exp1</em>' container reference.
	 * @see #getIfExp1()
	 * @generated
	 */
	void setIfExp1(IfExp value);

	/**
	 * Returns the value of the '<em><b>Owning Attribute</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.Attribute#getInitExpression <em>Init Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Attribute</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Attribute</em>' container reference.
	 * @see #setOwningAttribute(Attribute)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression_OwningAttribute()
	 * @see org.eclipselabs.simpleocl.Attribute#getInitExpression
	 * @model opposite="initExpression" transient="false" ordered="false"
	 * @generated
	 */
	Attribute getOwningAttribute();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclExpression#getOwningAttribute <em>Owning Attribute</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Attribute</em>' container reference.
	 * @see #getOwningAttribute()
	 * @generated
	 */
	void setOwningAttribute(Attribute value);

	/**
	 * Returns the value of the '<em><b>Applied Operator</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OperatorCallExp#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applied Operator</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applied Operator</em>' container reference.
	 * @see #setAppliedOperator(OperatorCallExp)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclExpression_AppliedOperator()
	 * @see org.eclipselabs.simpleocl.OperatorCallExp#getSource
	 * @model opposite="source" transient="false" ordered="false"
	 * @generated
	 */
	OperatorCallExp getAppliedOperator();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclExpression#getAppliedOperator <em>Applied Operator</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applied Operator</em>' container reference.
	 * @see #getAppliedOperator()
	 * @generated
	 */
	void setAppliedOperator(OperatorCallExp value);

} // OclExpression
