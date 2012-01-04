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
 *   <li>{@link be.ac.vub.simpleocl.Module#getMetamodels <em>Metamodels</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.Module#getImports <em>Imports</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.Module#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see be.ac.vub.simpleocl.SimpleoclPackage#getModule()
 * @model
 * @generated
 */
public interface Module extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Metamodels</b></em>' containment reference list.
	 * The list contents are of type {@link be.ac.vub.simpleocl.OclMetamodel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metamodels</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metamodels</em>' containment reference list.
	 * @see be.ac.vub.simpleocl.SimpleoclPackage#getModule_Metamodels()
	 * @model containment="true"
	 * @generated
	 */
	EList<OclMetamodel> getMetamodels();

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
