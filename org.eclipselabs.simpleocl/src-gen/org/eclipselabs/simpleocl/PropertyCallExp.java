/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.PropertyCallExp#getCalls <em>Calls</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.PropertyCallExp#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getPropertyCallExp()
 * @model
 * @generated
 */
public interface PropertyCallExp extends OclExpression {
	/**
	 * Returns the value of the '<em><b>Calls</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.PropertyCall}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.PropertyCall#getCallExp <em>Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Calls</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Calls</em>' containment reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getPropertyCallExp_Calls()
	 * @see org.eclipselabs.simpleocl.PropertyCall#getCallExp
	 * @model opposite="callExp" containment="true" required="true"
	 * @generated
	 */
	EList<PropertyCall> getCalls();

	/**
	 * Returns the value of the '<em><b>Source</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclExpression#getAppliedProperty <em>Applied Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' containment reference.
	 * @see #setSource(OclExpression)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getPropertyCallExp_Source()
	 * @see org.eclipselabs.simpleocl.OclExpression#getAppliedProperty
	 * @model opposite="appliedProperty" containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclExpression getSource();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.PropertyCallExp#getSource <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' containment reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(OclExpression value);

} // PropertyCallExp
