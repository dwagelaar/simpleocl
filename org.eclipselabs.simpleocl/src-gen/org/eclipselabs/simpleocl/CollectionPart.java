/**
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.CollectionPart#getCollection <em>Collection</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getCollectionPart()
 * @model abstract="true"
 * @generated
 */
public interface CollectionPart extends LocatedElement {
	/**
	 * Returns the value of the '<em><b>Collection</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.CollectionExp#getParts <em>Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collection</em>' container reference.
	 * @see #setCollection(CollectionExp)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getCollectionPart_Collection()
	 * @see org.eclipselabs.simpleocl.CollectionExp#getParts
	 * @model opposite="parts" transient="false" ordered="false"
	 * @generated
	 */
	CollectionExp getCollection();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.CollectionPart#getCollection <em>Collection</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collection</em>' container reference.
	 * @see #getCollection()
	 * @generated
	 */
	void setCollection(CollectionExp value);

} // CollectionPart
