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
 * A representation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link be.ac.vub.simpleocl.Module#getName <em>Name</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.Module#getModels <em>Models</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.Module#getFeatures <em>Features</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.Module#getImports <em>Imports</em>}</li>
 * </ul>
 * </p>
 *
 * @see be.ac.vub.simpleocl.SimpleoclPackage#getModule()
 * @model
 * @generated
 */
public interface Module extends LocatedElement {
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
	 * @see be.ac.vub.simpleocl.SimpleoclPackage#getModule_Name()
	 * @model dataType="be.ac.vub.simpleocl.String" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link be.ac.vub.simpleocl.Module#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Models</b></em>' containment reference list.
	 * The list contents are of type {@link be.ac.vub.simpleocl.OclModel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Models</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Models</em>' containment reference list.
	 * @see be.ac.vub.simpleocl.SimpleoclPackage#getModule_Models()
	 * @model containment="true"
	 * @generated
	 */
	EList<OclModel> getModels();

	/**
	 * Returns the value of the '<em><b>Features</b></em>' containment reference list.
	 * The list contents are of type {@link be.ac.vub.simpleocl.OclFeatureDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' containment reference list.
	 * @see be.ac.vub.simpleocl.SimpleoclPackage#getModule_Features()
	 * @model containment="true"
	 * @generated
	 */
	EList<OclFeatureDefinition> getFeatures();

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link be.ac.vub.simpleocl.Import}.
	 * It is bidirectional and its opposite is '{@link be.ac.vub.simpleocl.Import#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference list.
	 * @see be.ac.vub.simpleocl.SimpleoclPackage#getModule_Imports()
	 * @see be.ac.vub.simpleocl.Import#getModule
	 * @model opposite="module" containment="true"
	 * @generated
	 */
	EList<Import> getImports();

} // Module
