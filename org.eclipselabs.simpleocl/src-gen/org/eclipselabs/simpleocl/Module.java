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
 * A representation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.Module#getMetamodels <em>Metamodels</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.Module#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.Module#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getModule()
 * @model
 * @generated
 */
public interface Module extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Metamodels</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.OclMetamodel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metamodels</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metamodels</em>' containment reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getModule_Metamodels()
	 * @model containment="true"
	 * @generated
	 */
	EList<OclMetamodel> getMetamodels();

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.Import}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.Import#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getModule_Imports()
	 * @see org.eclipselabs.simpleocl.Import#getModule
	 * @model opposite="module" containment="true"
	 * @generated
	 */
	EList<Import> getImports();

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.ModuleElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.ModuleElement#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getModule_Elements()
	 * @see org.eclipselabs.simpleocl.ModuleElement#getModule
	 * @model opposite="module" containment="true"
	 * @generated
	 */
	EList<ModuleElement> getElements();

} // Module
