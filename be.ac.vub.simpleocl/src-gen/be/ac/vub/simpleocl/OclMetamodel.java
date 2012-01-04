/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Metamodel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link be.ac.vub.simpleocl.OclMetamodel#getModel <em>Model</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.OclMetamodel#getUri <em>Uri</em>}</li>
 * </ul>
 * </p>
 *
 * @see be.ac.vub.simpleocl.SimpleoclPackage#getOclMetamodel()
 * @model
 * @generated
 */
public interface OclMetamodel extends OclModel {
	/**
	 * Returns the value of the '<em><b>Model</b></em>' reference list.
	 * The list contents are of type {@link be.ac.vub.simpleocl.OclInstanceModel}.
	 * It is bidirectional and its opposite is '{@link be.ac.vub.simpleocl.OclInstanceModel#getMetamodel <em>Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' reference list.
	 * @see be.ac.vub.simpleocl.SimpleoclPackage#getOclMetamodel_Model()
	 * @see be.ac.vub.simpleocl.OclInstanceModel#getMetamodel
	 * @model opposite="metamodel" ordered="false"
	 * @generated
	 */
	EList<OclInstanceModel> getModel();

	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uri</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uri</em>' attribute.
	 * @see #setUri(String)
	 * @see be.ac.vub.simpleocl.SimpleoclPackage#getOclMetamodel_Uri()
	 * @model dataType="be.ac.vub.simpleocl.String"
	 * @generated
	 */
	String getUri();

	/**
	 * Sets the value of the '{@link be.ac.vub.simpleocl.OclMetamodel#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(String value);

} // OclMetamodel
