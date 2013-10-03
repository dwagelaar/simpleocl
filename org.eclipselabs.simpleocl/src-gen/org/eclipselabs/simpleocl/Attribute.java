/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.Attribute#getInitExpression <em>Init Expression</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.Attribute#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getAttribute()
 * @model
 * @generated
 */
public interface Attribute extends OclFeature {
	/**
	 * Returns the value of the '<em><b>Init Expression</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclExpression#getOwningAttribute <em>Owning Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Expression</em>' containment reference.
	 * @see #setInitExpression(OclExpression)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getAttribute_InitExpression()
	 * @see org.eclipselabs.simpleocl.OclExpression#getOwningAttribute
	 * @model opposite="owningAttribute" containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclExpression getInitExpression();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.Attribute#getInitExpression <em>Init Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Init Expression</em>' containment reference.
	 * @see #getInitExpression()
	 * @generated
	 */
	void setInitExpression(OclExpression value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclType#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(OclType)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getAttribute_Type()
	 * @see org.eclipselabs.simpleocl.OclType#getAttribute
	 * @model opposite="attribute" containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclType getType();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.Attribute#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(OclType value);

} // Attribute
