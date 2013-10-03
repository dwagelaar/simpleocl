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
import org.eclipselabs.simpleocl.MapElement;
import org.eclipselabs.simpleocl.MapExp;
import org.eclipselabs.simpleocl.OclExpression;
import org.eclipselabs.simpleocl.SimpleoclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Map Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.impl.MapElementImpl#getMap <em>Map</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.impl.MapElementImpl#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.impl.MapElementImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MapElementImpl extends LocatedElementImpl implements MapElement {
	/**
	 * The cached value of the '{@link #getKey() <em>Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected OclExpression key;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected OclExpression value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MapElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.MAP_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapExp getMap() {
		if (eContainerFeatureID() != SimpleoclPackage.MAP_ELEMENT__MAP) return null;
		return (MapExp)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMap(MapExp newMap, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newMap, SimpleoclPackage.MAP_ELEMENT__MAP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMap(MapExp newMap) {
		if (newMap != eInternalContainer() || (eContainerFeatureID() != SimpleoclPackage.MAP_ELEMENT__MAP && newMap != null)) {
			if (EcoreUtil.isAncestor(this, newMap))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newMap != null)
				msgs = ((InternalEObject)newMap).eInverseAdd(this, SimpleoclPackage.MAP_EXP__ELEMENTS, MapExp.class, msgs);
			msgs = basicSetMap(newMap, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.MAP_ELEMENT__MAP, newMap, newMap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getKey() {
		return key;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKey(OclExpression newKey, NotificationChain msgs) {
		OclExpression oldKey = key;
		key = newKey;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimpleoclPackage.MAP_ELEMENT__KEY, oldKey, newKey);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKey(OclExpression newKey) {
		if (newKey != key) {
			NotificationChain msgs = null;
			if (key != null)
				msgs = ((InternalEObject)key).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimpleoclPackage.MAP_ELEMENT__KEY, null, msgs);
			if (newKey != null)
				msgs = ((InternalEObject)newKey).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SimpleoclPackage.MAP_ELEMENT__KEY, null, msgs);
			msgs = basicSetKey(newKey, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.MAP_ELEMENT__KEY, newKey, newKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValue(OclExpression newValue, NotificationChain msgs) {
		OclExpression oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimpleoclPackage.MAP_ELEMENT__VALUE, oldValue, newValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(OclExpression newValue) {
		if (newValue != value) {
			NotificationChain msgs = null;
			if (value != null)
				msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimpleoclPackage.MAP_ELEMENT__VALUE, null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SimpleoclPackage.MAP_ELEMENT__VALUE, null, msgs);
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.MAP_ELEMENT__VALUE, newValue, newValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimpleoclPackage.MAP_ELEMENT__MAP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetMap((MapExp)otherEnd, msgs);
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
			case SimpleoclPackage.MAP_ELEMENT__MAP:
				return basicSetMap(null, msgs);
			case SimpleoclPackage.MAP_ELEMENT__KEY:
				return basicSetKey(null, msgs);
			case SimpleoclPackage.MAP_ELEMENT__VALUE:
				return basicSetValue(null, msgs);
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
			case SimpleoclPackage.MAP_ELEMENT__MAP:
				return eInternalContainer().eInverseRemove(this, SimpleoclPackage.MAP_EXP__ELEMENTS, MapExp.class, msgs);
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
			case SimpleoclPackage.MAP_ELEMENT__MAP:
				return getMap();
			case SimpleoclPackage.MAP_ELEMENT__KEY:
				return getKey();
			case SimpleoclPackage.MAP_ELEMENT__VALUE:
				return getValue();
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
			case SimpleoclPackage.MAP_ELEMENT__MAP:
				setMap((MapExp)newValue);
				return;
			case SimpleoclPackage.MAP_ELEMENT__KEY:
				setKey((OclExpression)newValue);
				return;
			case SimpleoclPackage.MAP_ELEMENT__VALUE:
				setValue((OclExpression)newValue);
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
			case SimpleoclPackage.MAP_ELEMENT__MAP:
				setMap((MapExp)null);
				return;
			case SimpleoclPackage.MAP_ELEMENT__KEY:
				setKey((OclExpression)null);
				return;
			case SimpleoclPackage.MAP_ELEMENT__VALUE:
				setValue((OclExpression)null);
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
			case SimpleoclPackage.MAP_ELEMENT__MAP:
				return getMap() != null;
			case SimpleoclPackage.MAP_ELEMENT__KEY:
				return key != null;
			case SimpleoclPackage.MAP_ELEMENT__VALUE:
				return value != null;
		}
		return super.eIsSet(featureID);
	}

} //MapElementImpl
