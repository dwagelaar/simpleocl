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
import org.eclipselabs.simpleocl.OclType;
import org.eclipselabs.simpleocl.SimpleoclPackage;
import org.eclipselabs.simpleocl.StaticPropertyCall;
import org.eclipselabs.simpleocl.StaticPropertyCallExp;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Static Property Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.impl.StaticPropertyCallExpImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.impl.StaticPropertyCallExpImpl#getStaticCall <em>Static Call</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StaticPropertyCallExpImpl extends OclExpressionImpl implements StaticPropertyCallExp {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected OclType source;

	/**
	 * The cached value of the '{@link #getStaticCall() <em>Static Call</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticCall()
	 * @generated
	 * @ordered
	 */
	protected StaticPropertyCall staticCall;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StaticPropertyCallExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclType getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(OclType newSource, NotificationChain msgs) {
		OclType oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE, oldSource, newSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(OclType newSource) {
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, SimpleoclPackage.OCL_TYPE__STATIC_PROPERTY_CALL, OclType.class, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, SimpleoclPackage.OCL_TYPE__STATIC_PROPERTY_CALL, OclType.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticPropertyCall getStaticCall() {
		return staticCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStaticCall(StaticPropertyCall newStaticCall, NotificationChain msgs) {
		StaticPropertyCall oldStaticCall = staticCall;
		staticCall = newStaticCall;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL, oldStaticCall, newStaticCall);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStaticCall(StaticPropertyCall newStaticCall) {
		if (newStaticCall != staticCall) {
			NotificationChain msgs = null;
			if (staticCall != null)
				msgs = ((InternalEObject)staticCall).eInverseRemove(this, SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP, StaticPropertyCall.class, msgs);
			if (newStaticCall != null)
				msgs = ((InternalEObject)newStaticCall).eInverseAdd(this, SimpleoclPackage.STATIC_PROPERTY_CALL__STATIC_CALL_EXP, StaticPropertyCall.class, msgs);
			msgs = basicSetStaticCall(newStaticCall, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL, newStaticCall, newStaticCall));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE:
				if (source != null)
					msgs = ((InternalEObject)source).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE, null, msgs);
				return basicSetSource((OclType)otherEnd, msgs);
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL:
				if (staticCall != null)
					msgs = ((InternalEObject)staticCall).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL, null, msgs);
				return basicSetStaticCall((StaticPropertyCall)otherEnd, msgs);
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
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE:
				return basicSetSource(null, msgs);
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL:
				return basicSetStaticCall(null, msgs);
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
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE:
				return getSource();
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL:
				return getStaticCall();
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
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE:
				setSource((OclType)newValue);
				return;
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL:
				setStaticCall((StaticPropertyCall)newValue);
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
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE:
				setSource((OclType)null);
				return;
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL:
				setStaticCall((StaticPropertyCall)null);
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
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE:
				return source != null;
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL:
				return staticCall != null;
		}
		return super.eIsSet(featureID);
	}

} //StaticPropertyCallExpImpl
