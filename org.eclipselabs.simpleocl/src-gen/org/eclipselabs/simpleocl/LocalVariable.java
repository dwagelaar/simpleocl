/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Local Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.LocalVariable#getLetExp <em>Let Exp</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.LocalVariable#getInitExpression <em>Init Expression</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.LocalVariable#getBaseExp <em>Base Exp</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.LocalVariable#getEq <em>Eq</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getLocalVariable()
 * @model
 * @generated
 */
public interface LocalVariable extends VariableDeclaration {
	/**
	 * Returns the value of the '<em><b>Let Exp</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.LetExp#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Let Exp</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Let Exp</em>' container reference.
	 * @see #setLetExp(LetExp)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getLocalVariable_LetExp()
	 * @see org.eclipselabs.simpleocl.LetExp#getVariable
	 * @model opposite="variable" transient="false" ordered="false"
	 * @generated
	 */
	LetExp getLetExp();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.LocalVariable#getLetExp <em>Let Exp</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Let Exp</em>' container reference.
	 * @see #getLetExp()
	 * @generated
	 */
	void setLetExp(LetExp value);

	/**
	 * Returns the value of the '<em><b>Init Expression</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclExpression#getInitializedVariable <em>Initialized Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Expression</em>' containment reference.
	 * @see #setInitExpression(OclExpression)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getLocalVariable_InitExpression()
	 * @see org.eclipselabs.simpleocl.OclExpression#getInitializedVariable
	 * @model opposite="initializedVariable" containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclExpression getInitExpression();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.LocalVariable#getInitExpression <em>Init Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Init Expression</em>' containment reference.
	 * @see #getInitExpression()
	 * @generated
	 */
	void setInitExpression(OclExpression value);

	/**
	 * Returns the value of the '<em><b>Base Exp</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.IterateExp#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Exp</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Exp</em>' container reference.
	 * @see #setBaseExp(IterateExp)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getLocalVariable_BaseExp()
	 * @see org.eclipselabs.simpleocl.IterateExp#getResult
	 * @model opposite="result" transient="false" ordered="false"
	 * @generated
	 */
	IterateExp getBaseExp();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.LocalVariable#getBaseExp <em>Base Exp</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Exp</em>' container reference.
	 * @see #getBaseExp()
	 * @generated
	 */
	void setBaseExp(IterateExp value);

	/**
	 * Returns the value of the '<em><b>Eq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Eq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Eq</em>' attribute.
	 * @see #setEq(String)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getLocalVariable_Eq()
	 * @model dataType="org.eclipselabs.simpleocl.String" required="true"
	 * @generated
	 */
	String getEq();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.LocalVariable#getEq <em>Eq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eq</em>' attribute.
	 * @see #getEq()
	 * @generated
	 */
	void setEq(String value);

} // LocalVariable
