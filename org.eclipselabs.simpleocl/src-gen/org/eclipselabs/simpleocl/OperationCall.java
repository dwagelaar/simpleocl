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
 * A representation of the model object '<em><b>Operation Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.OperationCall#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OperationCall#getOperationName <em>Operation Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOperationCall()
 * @model
 * @generated
 */
public interface OperationCall extends PropertyCall {
	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.OclExpression}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclExpression#getParentOperation <em>Parent Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arguments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arguments</em>' containment reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOperationCall_Arguments()
	 * @see org.eclipselabs.simpleocl.OclExpression#getParentOperation
	 * @model opposite="parentOperation" containment="true"
	 * @generated
	 */
	EList<OclExpression> getArguments();

	/**
	 * Returns the value of the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Name</em>' attribute.
	 * @see #setOperationName(String)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOperationCall_OperationName()
	 * @model unique="false" dataType="org.eclipselabs.simpleocl.String" required="true" ordered="false"
	 * @generated
	 */
	String getOperationName();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OperationCall#getOperationName <em>Operation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Name</em>' attribute.
	 * @see #getOperationName()
	 * @generated
	 */
	void setOperationName(String value);

} // OperationCall
