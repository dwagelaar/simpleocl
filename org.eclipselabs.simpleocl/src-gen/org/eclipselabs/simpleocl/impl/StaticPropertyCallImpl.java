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
import org.eclipselabs.simpleocl.SimpleoclPackage;
import org.eclipselabs.simpleocl.StaticPropertyCall;
import org.eclipselabs.simpleocl.StaticPropertyCallExp;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Static Property Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.impl.StaticPropertyCallImpl#getStaticCallExp <em>Static Call Exp</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class StaticPropertyCallImpl extends LocatedElementImpl implements StaticPropertyCall {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StaticPropertyCallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.STATIC_PROPERTY_CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticPropertyCallExp getStaticCallExp() {
		if (eContainerFeatureID() != SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP) return null;
		return (StaticPropertyCallExp)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStaticCallExp(StaticPropertyCallExp newStaticCallExp, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newStaticCallExp, SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStaticCallExp(StaticPropertyCallExp newStaticCallExp) {
		if (newStaticCallExp != eInternalContainer() || (eContainerFeatureID() != SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP && newStaticCallExp != null)) {
			if (EcoreUtil.isAncestor(this, newStaticCallExp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newStaticCallExp != null)
				msgs = ((InternalEObject)newStaticCallExp).eInverseAdd(this, SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL, StaticPropertyCallExp.class, msgs);
			msgs = basicSetStaticCallExp(newStaticCallExp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP, newStaticCallExp, newStaticCallExp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetStaticCallExp((StaticPropertyCallExp)otherEnd, msgs);
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
			case SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP:
				return basicSetStaticCallExp(null, msgs);
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
			case SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP:
				return eInternalContainer().eInverseRemove(this, SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL, StaticPropertyCallExp.class, msgs);
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
			case SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP:
				return getStaticCallExp();
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
			case SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP:
				setStaticCallExp((StaticPropertyCallExp)newValue);
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
			case SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP:
				setStaticCallExp((StaticPropertyCallExp)null);
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
			case SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP:
				return getStaticCallExp() != null;
		}
		return super.eIsSet(featureID);
	}

} //StaticPropertyCallImpl
