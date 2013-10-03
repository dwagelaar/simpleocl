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
import org.eclipselabs.simpleocl.OclInstanceModel;
import org.eclipselabs.simpleocl.OclMetamodel;
import org.eclipselabs.simpleocl.SimpleoclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ocl Instance Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.impl.OclInstanceModelImpl#getMetamodel <em>Metamodel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OclInstanceModelImpl extends OclModelImpl implements OclInstanceModel {
	/**
	 * The cached value of the '{@link #getMetamodel() <em>Metamodel</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetamodel()
	 * @generated
	 * @ordered
	 */
	protected OclMetamodel metamodel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OclInstanceModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.OCL_INSTANCE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclMetamodel getMetamodel() {
		if (metamodel != null && metamodel.eIsProxy()) {
			InternalEObject oldMetamodel = (InternalEObject)metamodel;
			metamodel = (OclMetamodel)eResolveProxy(oldMetamodel);
			if (metamodel != oldMetamodel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL, oldMetamodel, metamodel));
			}
		}
		return metamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclMetamodel basicGetMetamodel() {
		return metamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMetamodel(OclMetamodel newMetamodel, NotificationChain msgs) {
		OclMetamodel oldMetamodel = metamodel;
		metamodel = newMetamodel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL, oldMetamodel, newMetamodel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetamodel(OclMetamodel newMetamodel) {
		if (newMetamodel != metamodel) {
			NotificationChain msgs = null;
			if (metamodel != null)
				msgs = ((InternalEObject)metamodel).eInverseRemove(this, SimpleoclPackage.OCL_METAMODEL__MODEL, OclMetamodel.class, msgs);
			if (newMetamodel != null)
				msgs = ((InternalEObject)newMetamodel).eInverseAdd(this, SimpleoclPackage.OCL_METAMODEL__MODEL, OclMetamodel.class, msgs);
			msgs = basicSetMetamodel(newMetamodel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL, newMetamodel, newMetamodel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL:
				if (metamodel != null)
					msgs = ((InternalEObject)metamodel).eInverseRemove(this, SimpleoclPackage.OCL_METAMODEL__MODEL, OclMetamodel.class, msgs);
				return basicSetMetamodel((OclMetamodel)otherEnd, msgs);
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
			case SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL:
				return basicSetMetamodel(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL:
				if (resolve) return getMetamodel();
				return basicGetMetamodel();
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
			case SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL:
				setMetamodel((OclMetamodel)newValue);
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
			case SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL:
				setMetamodel((OclMetamodel)null);
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
			case SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL:
				return metamodel != null;
		}
		return super.eIsSet(featureID);
	}

} //OclInstanceModelImpl
