/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Instance Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.OclInstanceModel#getMetamodel <em>Metamodel</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclInstanceModel()
 * @model
 * @generated
 */
public interface OclInstanceModel extends OclModel {
	/**
	 * Returns the value of the '<em><b>Metamodel</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclMetamodel#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metamodel</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metamodel</em>' reference.
	 * @see #setMetamodel(OclMetamodel)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclInstanceModel_Metamodel()
	 * @see org.eclipselabs.simpleocl.OclMetamodel#getModel
	 * @model opposite="model" required="true" ordered="false"
	 * @generated
	 */
	OclMetamodel getMetamodel();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclInstanceModel#getMetamodel <em>Metamodel</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metamodel</em>' reference.
	 * @see #getMetamodel()
	 * @generated
	 */
	void setMetamodel(OclMetamodel value);

} // OclInstanceModel
