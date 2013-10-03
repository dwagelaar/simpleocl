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
 * A representation of the model object '<em><b>Collection Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.CollectionExp#getParts <em>Parts</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getCollectionExp()
 * @model abstract="true"
 * @generated
 */
public interface CollectionExp extends OclExpression {
	/**
	 * Returns the value of the '<em><b>Parts</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.CollectionPart}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.CollectionPart#getCollection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parts</em>' containment reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getCollectionExp_Parts()
	 * @see org.eclipselabs.simpleocl.CollectionPart#getCollection
	 * @model opposite="collection" containment="true"
	 * @generated
	 */
	EList<CollectionPart> getParts();

} // CollectionExp
