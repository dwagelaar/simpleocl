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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import be.ac.vub.simpleocl.OclType;
import be.ac.vub.simpleocl.SimpleoclPackage;
import be.ac.vub.simpleocl.VariableDeclaration;
import be.ac.vub.simpleocl.VariableExp;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link be.ac.vub.simpleocl.impl.VariableDeclarationImpl#getVarName <em>Var Name</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.impl.VariableDeclarationImpl#getType <em>Type</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.impl.VariableDeclarationImpl#getVariableExp <em>Variable Exp</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class VariableDeclarationImpl extends LocatedElementImpl implements VariableDeclaration {
	/**
	 * The default value of the '{@link #getVarName() <em>Var Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVarName()
	 * @generated
	 * @ordered
	 */
	protected static final String VAR_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVarName() <em>Var Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVarName()
	 * @generated
	 * @ordered
	 */
	protected String varName = VAR_NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getVariableExp() <em>Variable Exp</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariableExp()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableExp> variableExp;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.VARIABLE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVarName() {
		return varName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVarName(String newVarName) {
		String oldVarName = varName;
		varName = newVarName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.VARIABLE_DECLARATION__VAR_NAME, oldVarName, varName));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimpleoclPackage.VARIABLE_DECLARATION__TYPE, oldType, newType);
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
				msgs = ((InternalEObject)type).eInverseRemove(this, SimpleoclPackage.OCL_TYPE__VARIABLE_DECLARATION, OclType.class, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, SimpleoclPackage.OCL_TYPE__VARIABLE_DECLARATION, OclType.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.VARIABLE_DECLARATION__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariableExp> getVariableExp() {
		if (variableExp == null) {
			variableExp = new EObjectWithInverseResolvingEList<VariableExp>(VariableExp.class, this, SimpleoclPackage.VARIABLE_DECLARATION__VARIABLE_EXP, SimpleoclPackage.VARIABLE_EXP__REFERRED_VARIABLE);
		}
		return variableExp;
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
			case SimpleoclPackage.VARIABLE_DECLARATION__TYPE:
				if (type != null)
					msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimpleoclPackage.VARIABLE_DECLARATION__TYPE, null, msgs);
				return basicSetType((OclType)otherEnd, msgs);
			case SimpleoclPackage.VARIABLE_DECLARATION__VARIABLE_EXP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getVariableExp()).basicAdd(otherEnd, msgs);
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
			case SimpleoclPackage.VARIABLE_DECLARATION__TYPE:
				return basicSetType(null, msgs);
			case SimpleoclPackage.VARIABLE_DECLARATION__VARIABLE_EXP:
				return ((InternalEList<?>)getVariableExp()).basicRemove(otherEnd, msgs);
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
			case SimpleoclPackage.VARIABLE_DECLARATION__VAR_NAME:
				return getVarName();
			case SimpleoclPackage.VARIABLE_DECLARATION__TYPE:
				return getType();
			case SimpleoclPackage.VARIABLE_DECLARATION__VARIABLE_EXP:
				return getVariableExp();
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
			case SimpleoclPackage.VARIABLE_DECLARATION__VAR_NAME:
				setVarName((String)newValue);
				return;
			case SimpleoclPackage.VARIABLE_DECLARATION__TYPE:
				setType((OclType)newValue);
				return;
			case SimpleoclPackage.VARIABLE_DECLARATION__VARIABLE_EXP:
				getVariableExp().clear();
				getVariableExp().addAll((Collection<? extends VariableExp>)newValue);
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
			case SimpleoclPackage.VARIABLE_DECLARATION__VAR_NAME:
				setVarName(VAR_NAME_EDEFAULT);
				return;
			case SimpleoclPackage.VARIABLE_DECLARATION__TYPE:
				setType((OclType)null);
				return;
			case SimpleoclPackage.VARIABLE_DECLARATION__VARIABLE_EXP:
				getVariableExp().clear();
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
			case SimpleoclPackage.VARIABLE_DECLARATION__VAR_NAME:
				return VAR_NAME_EDEFAULT == null ? varName != null : !VAR_NAME_EDEFAULT.equals(varName);
			case SimpleoclPackage.VARIABLE_DECLARATION__TYPE:
				return type != null;
			case SimpleoclPackage.VARIABLE_DECLARATION__VARIABLE_EXP:
				return variableExp != null && !variableExp.isEmpty();
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
		result.append(" (varName: ");
		result.append(varName);
		result.append(')');
		return result.toString();
	}

} //VariableDeclarationImpl
