/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.OclFeature#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclFeature#getEq <em>Eq</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclFeature()
 * @model abstract="true"
 * @generated
 */
public interface OclFeature extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Definition</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclFeatureDefinition#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition</em>' container reference.
	 * @see #setDefinition(OclFeatureDefinition)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclFeature_Definition()
	 * @see org.eclipselabs.simpleocl.OclFeatureDefinition#getFeature
	 * @model opposite="feature" transient="false" ordered="false"
	 * @generated
	 */
	OclFeatureDefinition getDefinition();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclFeature#getDefinition <em>Definition</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' container reference.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(OclFeatureDefinition value);

	/**
	 * Returns the value of the '<em><b>Eq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Eq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Eq</em>' attribute.
	 * @see #setEq(String)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclFeature_Eq()
	 * @model dataType="org.eclipselabs.simpleocl.String" required="true"
	 * @generated
	 */
	String getEq();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclFeature#getEq <em>Eq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eq</em>' attribute.
	 * @see #getEq()
	 * @generated
	 */
	void setEq(String value);

} // OclFeature
