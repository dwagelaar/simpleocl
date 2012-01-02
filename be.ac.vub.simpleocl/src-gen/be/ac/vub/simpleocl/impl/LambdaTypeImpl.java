/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import be.ac.vub.simpleocl.LambdaType;
import be.ac.vub.simpleocl.OclType;
import be.ac.vub.simpleocl.SimpleoclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lambda Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link be.ac.vub.simpleocl.impl.LambdaTypeImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.impl.LambdaTypeImpl#getArgumentTypes <em>Argument Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LambdaTypeImpl extends OclTypeImpl implements LambdaType {
	/**
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected OclType returnType;

	/**
	 * The cached value of the '{@link #getArgumentTypes() <em>Argument Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArgumentTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<OclType> argumentTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LambdaTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.LAMBDA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclType getReturnType() {
		return returnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReturnType(OclType newReturnType, NotificationChain msgs) {
		OclType oldReturnType = returnType;
		returnType = newReturnType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimpleoclPackage.LAMBDA_TYPE__RETURN_TYPE, oldReturnType, newReturnType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnType(OclType newReturnType) {
		if (newReturnType != returnType) {
			NotificationChain msgs = null;
			if (returnType != null)
				msgs = ((InternalEObject)returnType).eInverseRemove(this, SimpleoclPackage.OCL_TYPE__LAMBDA_RETURN_TYPE, OclType.class, msgs);
			if (newReturnType != null)
				msgs = ((InternalEObject)newReturnType).eInverseAdd(this, SimpleoclPackage.OCL_TYPE__LAMBDA_RETURN_TYPE, OclType.class, msgs);
			msgs = basicSetReturnType(newReturnType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.LAMBDA_TYPE__RETURN_TYPE, newReturnType, newReturnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OclType> getArgumentTypes() {
		if (argumentTypes == null) {
			argumentTypes = new EObjectContainmentWithInverseEList<OclType>(OclType.class, this, SimpleoclPackage.LAMBDA_TYPE__ARGUMENT_TYPES, SimpleoclPackage.OCL_TYPE__LAMBDA_ARG_TYPE);
		}
		return argumentTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimpleoclPackage.LAMBDA_TYPE__RETURN_TYPE:
				if (returnType != null)
					msgs = ((InternalEObject)returnType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimpleoclPackage.LAMBDA_TYPE__RETURN_TYPE, null, msgs);
				return basicSetReturnType((OclType)otherEnd, msgs);
			case SimpleoclPackage.LAMBDA_TYPE__ARGUMENT_TYPES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getArgumentTypes()).basicAdd(otherEnd, msgs);
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
			case SimpleoclPackage.LAMBDA_TYPE__RETURN_TYPE:
				return basicSetReturnType(null, msgs);
			case SimpleoclPackage.LAMBDA_TYPE__ARGUMENT_TYPES:
				return ((InternalEList<?>)getArgumentTypes()).basicRemove(otherEnd, msgs);
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
			case SimpleoclPackage.LAMBDA_TYPE__RETURN_TYPE:
				return getReturnType();
			case SimpleoclPackage.LAMBDA_TYPE__ARGUMENT_TYPES:
				return getArgumentTypes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SimpleoclPackage.LAMBDA_TYPE__RETURN_TYPE:
				setReturnType((OclType)newValue);
				return;
			case SimpleoclPackage.LAMBDA_TYPE__ARGUMENT_TYPES:
				getArgumentTypes().clear();
				getArgumentTypes().addAll((Collection<? extends OclType>)newValue);
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
			case SimpleoclPackage.LAMBDA_TYPE__RETURN_TYPE:
				setReturnType((OclType)null);
				return;
			case SimpleoclPackage.LAMBDA_TYPE__ARGUMENT_TYPES:
				getArgumentTypes().clear();
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
			case SimpleoclPackage.LAMBDA_TYPE__RETURN_TYPE:
				return returnType != null;
			case SimpleoclPackage.LAMBDA_TYPE__ARGUMENT_TYPES:
				return argumentTypes != null && !argumentTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LambdaTypeImpl
