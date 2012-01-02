/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link be.ac.vub.simpleocl.OclFeature#getDefinition <em>Definition</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.OclFeature#getEq <em>Eq</em>}</li>
 * </ul>
 * </p>
 *
 * @see be.ac.vub.simpleocl.SimpleoclPackage#getOclFeature()
 * @model abstract="true"
 * @generated
 */
public interface OclFeature extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Definition</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link be.ac.vub.simpleocl.OclFeatureDefinition#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition</em>' container reference.
	 * @see #setDefinition(OclFeatureDefinition)
	 * @see be.ac.vub.simpleocl.SimpleoclPackage#getOclFeature_Definition()
	 * @see be.ac.vub.simpleocl.OclFeatureDefinition#getFeature
	 * @model opposite="feature" transient="false" ordered="false"
	 * @generated
	 */
	OclFeatureDefinition getDefinition();

	/**
	 * Sets the value of the '{@link be.ac.vub.simpleocl.OclFeature#getDefinition <em>Definition</em>}' container reference.
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
	 * @see be.ac.vub.simpleocl.SimpleoclPackage#getOclFeature_Eq()
	 * @model dataType="be.ac.vub.simpleocl.String" required="true"
	 * @generated
	 */
	String getEq();

	/**
	 * Sets the value of the '{@link be.ac.vub.simpleocl.OclFeature#getEq <em>Eq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eq</em>' attribute.
	 * @see #getEq()
	 * @generated
	 */
	void setEq(String value);

} // OclFeature
