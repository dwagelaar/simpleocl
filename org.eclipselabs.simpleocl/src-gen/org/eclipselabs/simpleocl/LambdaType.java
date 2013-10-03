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
 * A representation of the model object '<em><b>Lambda Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.LambdaType#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.LambdaType#getArgumentTypes <em>Argument Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getLambdaType()
 * @model
 * @generated
 */
public interface LambdaType extends OclType {
	/**
	 * Returns the value of the '<em><b>Return Type</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclType#getLambdaReturnType <em>Lambda Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Type</em>' containment reference.
	 * @see #setReturnType(OclType)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getLambdaType_ReturnType()
	 * @see org.eclipselabs.simpleocl.OclType#getLambdaReturnType
	 * @model opposite="lambdaReturnType" containment="true" required="true"
	 * @generated
	 */
	OclType getReturnType();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.LambdaType#getReturnType <em>Return Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type</em>' containment reference.
	 * @see #getReturnType()
	 * @generated
	 */
	void setReturnType(OclType value);

	/**
	 * Returns the value of the '<em><b>Argument Types</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.OclType}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclType#getLambdaArgType <em>Lambda Arg Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Argument Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument Types</em>' containment reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getLambdaType_ArgumentTypes()
	 * @see org.eclipselabs.simpleocl.OclType#getLambdaArgType
	 * @model opposite="lambdaArgType" containment="true"
	 * @generated
	 */
	EList<OclType> getArgumentTypes();

} // LambdaType
