/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipselabs.simpleocl.OclAnyType;
import org.eclipselabs.simpleocl.SimpleoclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ocl Any Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class OclAnyTypeImpl extends OclTypeImpl implements OclAnyType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OclAnyTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.OCL_ANY_TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		return "OclAny";
	}

} //OclAnyTypeImpl
