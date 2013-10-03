/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.OclModelElement#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclModelElement()
 * @model
 * @generated
 */
public interface OclModelElement extends OclType {
	/**
	 * Returns the value of the '<em><b>Model</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclModel#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' reference.
	 * @see #setModel(OclModel)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclModelElement_Model()
	 * @see org.eclipselabs.simpleocl.OclModel#getElements
	 * @model opposite="elements" required="true" ordered="false"
	 * @generated
	 */
	OclModel getModel();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclModelElement#getModel <em>Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(OclModel value);

} // OclModelElement
