/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iterate Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.IterateExp#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getIterateExp()
 * @model
 * @generated
 */
public interface IterateExp extends LoopExp {
	/**
	 * Returns the value of the '<em><b>Result</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.LocalVariable#getBaseExp <em>Base Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result</em>' containment reference.
	 * @see #setResult(LocalVariable)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getIterateExp_Result()
	 * @see org.eclipselabs.simpleocl.LocalVariable#getBaseExp
	 * @model opposite="baseExp" containment="true" required="true" ordered="false"
	 * @generated
	 */
	LocalVariable getResult();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.IterateExp#getResult <em>Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result</em>' containment reference.
	 * @see #getResult()
	 * @generated
	 */
	void setResult(LocalVariable value);

} // IterateExp
