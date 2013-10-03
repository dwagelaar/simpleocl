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
 * A representation of the model object '<em><b>Map Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.MapExp#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getMapExp()
 * @model
 * @generated
 */
public interface MapExp extends OclExpression {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.MapElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.MapElement#getMap <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getMapExp_Elements()
	 * @see org.eclipselabs.simpleocl.MapElement#getMap
	 * @model opposite="map" containment="true"
	 * @generated
	 */
	EList<MapElement> getElements();

} // MapExp
