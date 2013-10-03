/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Static Property Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.StaticPropertyCallExp#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.StaticPropertyCallExp#getStaticCall <em>Static Call</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getStaticPropertyCallExp()
 * @model
 * @generated
 */
public interface StaticPropertyCallExp extends OclExpression {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclType#getStaticPropertyCall <em>Static Property Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' containment reference.
	 * @see #setSource(OclType)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getStaticPropertyCallExp_Source()
	 * @see org.eclipselabs.simpleocl.OclType#getStaticPropertyCall
	 * @model opposite="staticPropertyCall" containment="true" required="true"
	 * @generated
	 */
	OclType getSource();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.StaticPropertyCallExp#getSource <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' containment reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(OclType value);

	/**
	 * Returns the value of the '<em><b>Static Call</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.StaticPropertyCall#getStaticCallExp <em>Static Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static Call</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static Call</em>' containment reference.
	 * @see #setStaticCall(StaticPropertyCall)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getStaticPropertyCallExp_StaticCall()
	 * @see org.eclipselabs.simpleocl.StaticPropertyCall#getStaticCallExp
	 * @model opposite="staticCallExp" containment="true" required="true"
	 * @generated
	 */
	StaticPropertyCall getStaticCall();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.StaticPropertyCallExp#getStaticCall <em>Static Call</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static Call</em>' containment reference.
	 * @see #getStaticCall()
	 * @generated
	 */
	void setStaticCall(StaticPropertyCall value);

} // StaticPropertyCallExp
