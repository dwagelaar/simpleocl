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
import org.eclipselabs.simpleocl.OclType;
import org.eclipselabs.simpleocl.SimpleoclPackage;
import org.eclipselabs.simpleocl.TupleType;
import org.eclipselabs.simpleocl.TupleTypeAttribute;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuple Type Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.impl.TupleTypeAttributeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.impl.TupleTypeAttributeImpl#getTupleType <em>Tuple Type</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.impl.TupleTypeAttributeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TupleTypeAttributeImpl extends LocatedElementImpl implements TupleTypeAttribute {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected OclType type;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TupleTypeAttributeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.TUPLE_TYPE_ATTRIBUTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(OclType newType, NotificationChain msgs) {
		OclType oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(OclType newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, SimpleoclPackage.OCL_TYPE__TUPLE_TYPE_ATTRIBUTE, OclType.class, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, SimpleoclPackage.OCL_TYPE__TUPLE_TYPE_ATTRIBUTE, OclType.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TupleType getTupleType() {
		if (eContainerFeatureID() != SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE) return null;
		return (TupleType)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTupleType(TupleType newTupleType, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTupleType, SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTupleType(TupleType newTupleType) {
		if (newTupleType != eInternalContainer() || (eContainerFeatureID() != SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE && newTupleType != null)) {
			if (EcoreUtil.isAncestor(this, newTupleType))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTupleType != null)
				msgs = ((InternalEObject)newTupleType).eInverseAdd(this, SimpleoclPackage.TUPLE_TYPE__ATTRIBUTES, TupleType.class, msgs);
			msgs = basicSetTupleType(newTupleType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE, newTupleType, newTupleType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TYPE:
				if (type != null)
					msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TYPE, null, msgs);
				return basicSetType((OclType)otherEnd, msgs);
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTupleType((TupleType)otherEnd, msgs);
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
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TYPE:
				return basicSetType(null, msgs);
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE:
				return basicSetTupleType(null, msgs);
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
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE:
				return eInternalContainer().eInverseRemove(this, SimpleoclPackage.TUPLE_TYPE__ATTRIBUTES, TupleType.class, msgs);
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
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TYPE:
				return getType();
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE:
				return getTupleType();
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME:
				return getName();
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
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TYPE:
				setType((OclType)newValue);
				return;
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE:
				setTupleType((TupleType)newValue);
				return;
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME:
				setName((String)newValue);
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
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TYPE:
				setType((OclType)null);
				return;
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE:
				setTupleType((TupleType)null);
				return;
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME:
				setName(NAME_EDEFAULT);
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
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TYPE:
				return type != null;
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE:
				return getTupleType() != null;
			case SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TupleTypeAttributeImpl
