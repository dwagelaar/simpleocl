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
 * A representation of the model object '<em><b>Ocl Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link be.ac.vub.simpleocl.OclModel#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see be.ac.vub.simpleocl.SimpleoclPackage#getOclModel()
 * @model abstract="true"
 * @generated
 */
public interface OclModel extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' reference list.
	 * The list contents are of type {@link be.ac.vub.simpleocl.OclModelElement}.
	 * It is bidirectional and its opposite is '{@link be.ac.vub.simpleocl.OclModelElement#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' reference list.
	 * @see be.ac.vub.simpleocl.SimpleoclPackage#getOclModel_Elements()
	 * @see be.ac.vub.simpleocl.OclModelElement#getModel
	 * @model opposite="model" ordered="false"
	 * @generated
	 */
	EList<OclModelElement> getElements();

} // OclModel
