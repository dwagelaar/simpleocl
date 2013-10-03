/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.BooleanExp#isBooleanSymbol <em>Boolean Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getBooleanExp()
 * @model
 * @generated
 */
public interface BooleanExp extends PrimitiveExp {
	/**
	 * Returns the value of the '<em><b>Boolean Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Boolean Symbol</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Boolean Symbol</em>' attribute.
	 * @see #setBooleanSymbol(boolean)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getBooleanExp_BooleanSymbol()
	 * @model unique="false" dataType="org.eclipselabs.simpleocl.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isBooleanSymbol();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.BooleanExp#isBooleanSymbol <em>Boolean Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Boolean Symbol</em>' attribute.
	 * @see #isBooleanSymbol()
	 * @generated
	 */
	void setBooleanSymbol(boolean value);

} // BooleanExp
