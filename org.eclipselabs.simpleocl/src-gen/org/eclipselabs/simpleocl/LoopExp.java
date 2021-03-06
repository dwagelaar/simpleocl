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
 * A representation of the model object '<em><b>Loop Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.LoopExp#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.LoopExp#getIterators <em>Iterators</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getLoopExp()
 * @model abstract="true"
 * @generated
 */
public interface LoopExp extends PropertyCall {
	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclExpression#getLoopExp <em>Loop Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(OclExpression)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getLoopExp_Body()
	 * @see org.eclipselabs.simpleocl.OclExpression#getLoopExp
	 * @model opposite="loopExp" containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclExpression getBody();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.LoopExp#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(OclExpression value);

	/**
	 * Returns the value of the '<em><b>Iterators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.simpleocl.Iterator}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.Iterator#getLoopExpr <em>Loop Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iterators</em>' containment reference list.
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getLoopExp_Iterators()
	 * @see org.eclipselabs.simpleocl.Iterator#getLoopExpr
	 * @model opposite="loopExpr" containment="true" required="true" ordered="false"
	 * @generated
	 */
	EList<Iterator> getIterators();

} // LoopExp
