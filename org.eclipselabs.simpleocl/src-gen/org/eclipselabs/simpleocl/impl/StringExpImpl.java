/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipselabs.simpleocl.SimpleoclPackage;
import org.eclipselabs.simpleocl.StringExp;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.impl.StringExpImpl#getStringSymbol <em>String Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StringExpImpl extends PrimitiveExpImpl implements StringExp {
	/**
	 * The default value of the '{@link #getStringSymbol() <em>String Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_SYMBOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStringSymbol() <em>String Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringSymbol()
	 * @generated
	 * @ordered
	 */
	protected String stringSymbol = STRING_SYMBOL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StringExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.STRING_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStringSymbol() {
		return stringSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStringSymbol(String newStringSymbol) {
		String oldStringSymbol = stringSymbol;
		stringSymbol = newStringSymbol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.STRING_EXP__STRING_SYMBOL, oldStringSymbol, stringSymbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SimpleoclPackage.STRING_EXP__STRING_SYMBOL:
				return getStringSymbol();
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
			case SimpleoclPackage.STRING_EXP__STRING_SYMBOL:
				setStringSymbol((String)newValue);
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
			case SimpleoclPackage.STRING_EXP__STRING_SYMBOL:
				setStringSymbol(STRING_SYMBOL_EDEFAULT);
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
			case SimpleoclPackage.STRING_EXP__STRING_SYMBOL:
				return STRING_SYMBOL_EDEFAULT == null ? stringSymbol != null : !STRING_SYMBOL_EDEFAULT.equals(stringSymbol);
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
		result.append(" (stringSymbol: ");
		result.append(stringSymbol);
		result.append(')');
		return result.toString();
	}

} //StringExpImpl
