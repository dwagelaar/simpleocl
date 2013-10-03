/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Feature Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.OclFeatureDefinition#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclFeatureDefinition#getContext_ <em>Context </em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.OclFeatureDefinition#isStatic <em>Static</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclFeatureDefinition()
 * @model
 * @generated
 */
public interface OclFeatureDefinition extends ModuleElement {
	/**
	 * Returns the value of the '<em><b>Feature</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclFeature#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature</em>' containment reference.
	 * @see #setFeature(OclFeature)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclFeatureDefinition_Feature()
	 * @see org.eclipselabs.simpleocl.OclFeature#getDefinition
	 * @model opposite="definition" containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclFeature getFeature();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclFeatureDefinition#getFeature <em>Feature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature</em>' containment reference.
	 * @see #getFeature()
	 * @generated
	 */
	void setFeature(OclFeature value);

	/**
	 * Returns the value of the '<em><b>Context </b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.simpleocl.OclContextDefinition#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context </em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context </em>' containment reference.
	 * @see #setContext_(OclContextDefinition)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclFeatureDefinition_Context_()
	 * @see org.eclipselabs.simpleocl.OclContextDefinition#getDefinition
	 * @model opposite="definition" containment="true" ordered="false"
	 * @generated
	 */
	OclContextDefinition getContext_();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclFeatureDefinition#getContext_ <em>Context </em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context </em>' containment reference.
	 * @see #getContext_()
	 * @generated
	 */
	void setContext_(OclContextDefinition value);

	/**
	 * Returns the value of the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static</em>' attribute.
	 * @see #setStatic(boolean)
	 * @see org.eclipselabs.simpleocl.SimpleoclPackage#getOclFeatureDefinition_Static()
	 * @model dataType="org.eclipselabs.simpleocl.Boolean" required="true"
	 * @generated
	 */
	boolean isStatic();

	/**
	 * Sets the value of the '{@link org.eclipselabs.simpleocl.OclFeatureDefinition#isStatic <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' attribute.
	 * @see #isStatic()
	 * @generated
	 */
	void setStatic(boolean value);

} // OclFeatureDefinition
