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
 * A representation of the model object '<em><b>Tuple Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.TupleExp#getTuplePart <em>Tuple Part</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getTupleExp()
 * @model
 * @generated
 */
public interface TupleExp extends OclExpression {
	/**
	 * Returns the value of the '<em><b>Tuple Part</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.TuplePart}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.TuplePart#getTuple <em>Tuple</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuple Part</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuple Part</em>' containment reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getTupleExp_TuplePart()
	 * @see org.eclipselabs.simpleocl.TuplePart#getTuple
	 * @model opposite="tuple" containment="true"
	 * @generated
	 */
	EList<TuplePart> getTuplePart();

} // TupleExp
