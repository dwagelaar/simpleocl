/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Static Property Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link be.ac.vub.simpleocl.StaticPropertyCall#getStaticCallExp <em>Static Call Exp</em>}</li>
 * </ul>
 * </p>
 *
 * @see be.ac.vub.simpleocl.SimpleoclPackage#getStaticPropertyCall()
 * @model abstract="true"
 * @generated
 */
public interface StaticPropertyCall extends LocatedElement {

	/**
	 * Returns the value of the '<em><b>Static Call Exp</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link be.ac.vub.simpleocl.StaticPropertyCallExp#getStaticCall <em>Static Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static Call Exp</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static Call Exp</em>' container reference.
	 * @see #setStaticCallExp(StaticPropertyCallExp)
	 * @see be.ac.vub.simpleocl.SimpleoclPackage#getStaticPropertyCall_StaticCallExp()
	 * @see be.ac.vub.simpleocl.StaticPropertyCallExp#getStaticCall
	 * @model opposite="staticCall" required="true" transient="false"
	 * @generated
	 */
	StaticPropertyCallExp getStaticCallExp();

	/**
	 * Sets the value of the '{@link be.ac.vub.simpleocl.StaticPropertyCall#getStaticCallExp <em>Static Call Exp</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static Call Exp</em>' container reference.
	 * @see #getStaticCallExp()
	 * @generated
	 */
	void setStaticCallExp(StaticPropertyCallExp value);
} // StaticPropertyCall
