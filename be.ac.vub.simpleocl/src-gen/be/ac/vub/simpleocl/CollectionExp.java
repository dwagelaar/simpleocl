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
 * A representation of the model object '<em><b>Collection Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link be.ac.vub.simpleocl.CollectionExp#getParts <em>Parts</em>}</li>
 * </ul>
 * </p>
 *
 * @see be.ac.vub.simpleocl.SimpleoclPackage#getCollectionExp()
 * @model abstract="true"
 * @generated
 */
public interface CollectionExp extends OclExpression {
	/**
	 * Returns the value of the '<em><b>Parts</b></em>' containment reference list.
	 * The list contents are of type {@link be.ac.vub.simpleocl.CollectionPart}.
	 * It is bidirectional and its opposite is '{@link be.ac.vub.simpleocl.CollectionPart#getCollection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parts</em>' containment reference list.
	 * @see be.ac.vub.simpleocl.SimpleoclPackage#getCollectionExp_Parts()
	 * @see be.ac.vub.simpleocl.CollectionPart#getCollection
	 * @model opposite="collection" containment="true"
	 * @generated
	 */
	EList<CollectionPart> getParts();

} // CollectionExp
