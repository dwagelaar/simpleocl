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
 * A representation of the model object '<em><b>Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.VariableDeclaration#getVarName <em>Var Name</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.VariableDeclaration#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.VariableDeclaration#getVariableExp <em>Variable Exp</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getVariableDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface VariableDeclaration extends LocatedElement {
	/**
	 * Returns the value of the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Var Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Var Name</em>' attribute.
	 * @see #setVarName(String)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getVariableDeclaration_VarName()
	 * @model unique="false" dataType="org.eclipselabs.simpleocl.String" required="true" ordered="false"
	 * @generated
	 */
	String getVarName();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.VariableDeclaration#getVarName <em>Var Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Var Name</em>' attribute.
	 * @see #getVarName()
	 * @generated
	 */
	void setVarName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclType#getVariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(OclType)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getVariableDeclaration_Type()
	 * @see org.eclipselabs.simpleocl.OclType#getVariableDeclaration
	 * @model opposite="variableDeclaration" containment="true" ordered="false"
	 * @generated
	 */
	OclType getType();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.VariableDeclaration#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(OclType value);

	/**
	 * Returns the value of the '<em><b>Variable Exp</b></em>' reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.VariableExp}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.VariableExp#getReferredVariable <em>Referred Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Exp</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Exp</em>' reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getVariableDeclaration_VariableExp()
	 * @see org.eclipselabs.simpleocl.VariableExp#getReferredVariable
	 * @model opposite="referredVariable" ordered="false"
	 * @generated
	 */
	EList<VariableExp> getVariableExp();

} // VariableDeclaration
