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
 * A representation of the model object '<em><b>Ocl Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.OclModel#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclModel()
 * @model abstract="true"
 * @generated
 */
public interface OclModel extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.OclModelElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclModelElement#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclModel_Elements()
	 * @see org.eclipselabs.simpleocl.OclModelElement#getModel
	 * @model opposite="model" ordered="false"
	 * @generated
	 */
	EList<OclModelElement> getElements();

} // OclModel
