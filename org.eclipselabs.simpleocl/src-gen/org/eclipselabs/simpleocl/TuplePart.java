/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuple Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.TuplePart#getTuple <em>Tuple</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getTuplePart()
 * @model
 * @generated
 */
public interface TuplePart extends LocalVariable {
	/**
	 * Returns the value of the '<em><b>Tuple</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.TupleExp#getTuplePart <em>Tuple Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuple</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuple</em>' container reference.
	 * @see #setTuple(TupleExp)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getTuplePart_Tuple()
	 * @see org.eclipselabs.simpleocl.TupleExp#getTuplePart
	 * @model opposite="tuplePart" required="true" transient="false" ordered="false"
	 * @generated
	 */
	TupleExp getTuple();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.TuplePart#getTuple <em>Tuple</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tuple</em>' container reference.
	 * @see #getTuple()
	 * @generated
	 */
	void setTuple(TupleExp value);

} // TuplePart
