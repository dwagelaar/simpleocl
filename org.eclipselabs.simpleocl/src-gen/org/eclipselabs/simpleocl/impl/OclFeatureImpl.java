/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipselabs.simpleocl.OclFeature;
import org.eclipselabs.simpleocl.OclFeatureDefinition;
import org.eclipselabs.simpleocl.SimpleoclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ocl Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.impl.OclFeatureImpl#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.impl.OclFeatureImpl#getEq <em>Eq</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OclFeatureImpl extends NamedElementImpl implements OclFeature {
	/**
	 * The default value of the '{@link #getEq() <em>Eq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEq()
	 * @generated
	 * @ordered
	 */
	protected static final String EQ_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEq() <em>Eq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEq()
	 * @generated
	 * @ordered
	 */
	protected String eq = EQ_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OclFeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.OCL_FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclFeatureDefinition getDefinition() {
		if (eContainerFeatureID() != SimpleoclPackage.OCL_FEATURE__DEFINITION) return null;
		return (OclFeatureDefinition)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefinition(OclFeatureDefinition newDefinition, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDefinition, SimpleoclPackage.OCL_FEATURE__DEFINITION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinition(OclFeatureDefinition newDefinition) {
		if (newDefinition != eInternalContainer() || (eContainerFeatureID() != SimpleoclPackage.OCL_FEATURE__DEFINITION && newDefinition != null)) {
			if (EcoreUtil.isAncestor(this, newDefinition))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDefinition != null)
				msgs = ((InternalEObject)newDefinition).eInverseAdd(this, SimpleoclPackage.OCL_FEATURE_DEFINITION__FEATURE, OclFeatureDefinition.class, msgs);
			msgs = basicSetDefinition(newDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.OCL_FEATURE__DEFINITION, newDefinition, newDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEq() {
		return eq;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEq(String newEq) {
		String oldEq = eq;
		eq = newEq;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.OCL_FEATURE__EQ, oldEq, eq));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimpleoclPackage.OCL_FEATURE__DEFINITION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDefinition((OclFeatureDefinition)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimpleoclPackage.OCL_FEATURE__DEFINITION:
				return basicSetDefinition(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case SimpleoclPackage.OCL_FEATURE__DEFINITION:
				return eInternalContainer().eInverseRemove(this, SimpleoclPackage.OCL_FEATURE_DEFINITION__FEATURE, OclFeatureDefinition.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SimpleoclPackage.OCL_FEATURE__DEFINITION:
				return getDefinition();
			case SimpleoclPackage.OCL_FEATURE__EQ:
				return getEq();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SimpleoclPackage.OCL_FEATURE__DEFINITION:
				setDefinition((OclFeatureDefinition)newValue);
				return;
			case SimpleoclPackage.OCL_FEATURE__EQ:
				setEq((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SimpleoclPackage.OCL_FEATURE__DEFINITION:
				setDefinition((OclFeatureDefinition)null);
				return;
			case SimpleoclPackage.OCL_FEATURE__EQ:
				setEq(EQ_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SimpleoclPackage.OCL_FEATURE__DEFINITION:
				return getDefinition() != null;
			case SimpleoclPackage.OCL_FEATURE__EQ:
				return EQ_EDEFAULT == null ? eq != null : !EQ_EDEFAULT.equals(eq);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (eq: ");
		result.append(eq);
		result.append(')');
		return result.toString();
	}

} //OclFeatureImpl
