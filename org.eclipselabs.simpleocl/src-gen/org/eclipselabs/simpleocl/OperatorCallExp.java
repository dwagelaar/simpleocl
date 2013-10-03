/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operator Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.OperatorCallExp#getArgument <em>Argument</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OperatorCallExp#getOperationName <em>Operation Name</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OperatorCallExp#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOperatorCallExp()
 * @model
 * @generated
 */
public interface OperatorCallExp extends OclExpression {
	/**
	 * Returns the value of the '<em><b>Argument</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Argument</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument</em>' containment reference.
	 * @see #setArgument(OclExpression)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOperatorCallExp_Argument()
	 * @model containment="true"
	 * @generated
	 */
	OclExpression getArgument();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OperatorCallExp#getArgument <em>Argument</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Argument</em>' containment reference.
	 * @see #getArgument()
	 * @generated
	 */
	void setArgument(OclExpression value);

	/**
	 * Returns the value of the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Name</em>' attribute.
	 * @see #setOperationName(String)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOperatorCallExp_OperationName()
	 * @model unique="false" dataType="org.eclipselabs.simpleocl.String" required="true" ordered="false"
	 * @generated
	 */
	String getOperationName();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OperatorCallExp#getOperationName <em>Operation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Name</em>' attribute.
	 * @see #getOperationName()
	 * @generated
	 */
	void setOperationName(String value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclExpression#getAppliedOperator <em>Applied Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' containment reference.
	 * @see #setSource(OclExpression)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOperatorCallExp_Source()
	 * @see org.eclipselabs.simpleocl.OclExpression#getAppliedOperator
	 * @model opposite="appliedOperator" containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclExpression getSource();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OperatorCallExp#getSource <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' containment reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(OclExpression value);

} // OperatorCallExp
