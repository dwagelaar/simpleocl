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
 * A representation of the model object '<em><b>Tuple Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.TupleType#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getTupleType()
 * @model
 * @generated
 */
public interface TupleType extends OclType {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.TupleTypeAttribute}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.TupleTypeAttribute#getTupleType <em>Tuple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getTupleType_Attributes()
	 * @see org.eclipselabs.simpleocl.TupleTypeAttribute#getTupleType
	 * @model opposite="tupleType" containment="true"
	 * @generated
	 */
	EList<TupleTypeAttribute> getAttributes();

} // TupleType
