/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.impl;

import be.ac.vub.simpleocl.IterateExp;
import be.ac.vub.simpleocl.LetExp;
import be.ac.vub.simpleocl.LocalVariable;
import be.ac.vub.simpleocl.OclExpression;
import be.ac.vub.simpleocl.SimpleoclPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Local Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link be.ac.vub.simpleocl.impl.LocalVariableImpl#getLetExp <em>Let Exp</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.impl.LocalVariableImpl#getInitExpression <em>Init Expression</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.impl.LocalVariableImpl#getBaseExp <em>Base Exp</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.impl.LocalVariableImpl#getEq <em>Eq</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LocalVariableImpl extends VariableDeclarationImpl implements LocalVariable {
	/**
	 * The cached value of the '{@link #getInitExpression() <em>Init Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitExpression()
	 * @generated
	 * @ordered
	 */
	protected OclExpression initExpression;

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
	protected LocalVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.LOCAL_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LetExp getLetExp() {
		if (eContainerFeatureID() != SimpleoclPackage.LOCAL_VARIABLE__LET_EXP) return null;
		return (LetExp)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLetExp(LetExp newLetExp, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLetExp, SimpleoclPackage.LOCAL_VARIABLE__LET_EXP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLetExp(LetExp newLetExp) {
		if (newLetExp != eInternalContainer() || (eContainerFeatureID() != SimpleoclPackage.LOCAL_VARIABLE__LET_EXP && newLetExp != null)) {
			if (EcoreUtil.isAncestor(this, newLetExp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLetExp != null)
				msgs = ((InternalEObject)newLetExp).eInverseAdd(this, SimpleoclPackage.LET_EXP__VARIABLE, LetExp.class, msgs);
			msgs = basicSetLetExp(newLetExp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.LOCAL_VARIABLE__LET_EXP, newLetExp, newLetExp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getInitExpression() {
		return initExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitExpression(OclExpression newInitExpression, NotificationChain msgs) {
		OclExpression oldInitExpression = initExpression;
		initExpression = newInitExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimpleoclPackage.LOCAL_VARIABLE__INIT_EXPRESSION, oldInitExpression, newInitExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitExpression(OclExpression newInitExpression) {
		if (newInitExpression != initExpression) {
			NotificationChain msgs = null;
			if (initExpression != null)
				msgs = ((InternalEObject)initExpression).eInverseRemove(this, SimpleoclPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE, OclExpression.class, msgs);
			if (newInitExpression != null)
				msgs = ((InternalEObject)newInitExpression).eInverseAdd(this, SimpleoclPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE, OclExpression.class, msgs);
			msgs = basicSetInitExpression(newInitExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.LOCAL_VARIABLE__INIT_EXPRESSION, newInitExpression, newInitExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IterateExp getBaseExp() {
		if (eContainerFeatureID() != SimpleoclPackage.LOCAL_VARIABLE__BASE_EXP) return null;
		return (IterateExp)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBaseExp(IterateExp newBaseExp, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBaseExp, SimpleoclPackage.LOCAL_VARIABLE__BASE_EXP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseExp(IterateExp newBaseExp) {
		if (newBaseExp != eInternalContainer() || (eContainerFeatureID() != SimpleoclPackage.LOCAL_VARIABLE__BASE_EXP && newBaseExp != null)) {
			if (EcoreUtil.isAncestor(this, newBaseExp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBaseExp != null)
				msgs = ((InternalEObject)newBaseExp).eInverseAdd(this, SimpleoclPackage.ITERATE_EXP__RESULT, IterateExp.class, msgs);
			msgs = basicSetBaseExp(newBaseExp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.LOCAL_VARIABLE__BASE_EXP, newBaseExp, newBaseExp));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleoclPackage.LOCAL_VARIABLE__EQ, oldEq, eq));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimpleoclPackage.LOCAL_VARIABLE__LET_EXP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLetExp((LetExp)otherEnd, msgs);
			case SimpleoclPackage.LOCAL_VARIABLE__INIT_EXPRESSION:
				if (initExpression != null)
					msgs = ((InternalEObject)initExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimpleoclPackage.LOCAL_VARIABLE__INIT_EXPRESSION, null, msgs);
				return basicSetInitExpression((OclExpression)otherEnd, msgs);
			case SimpleoclPackage.LOCAL_VARIABLE__BASE_EXP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBaseExp((IterateExp)otherEnd, msgs);
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
			case SimpleoclPackage.LOCAL_VARIABLE__LET_EXP:
				return basicSetLetExp(null, msgs);
			case SimpleoclPackage.LOCAL_VARIABLE__INIT_EXPRESSION:
				return basicSetInitExpression(null, msgs);
			case SimpleoclPackage.LOCAL_VARIABLE__BASE_EXP:
				return basicSetBaseExp(null, msgs);
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
			case SimpleoclPackage.LOCAL_VARIABLE__LET_EXP:
				return eInternalContainer().eInverseRemove(this, SimpleoclPackage.LET_EXP__VARIABLE, LetExp.class, msgs);
			case SimpleoclPackage.LOCAL_VARIABLE__BASE_EXP:
				return eInternalContainer().eInverseRemove(this, SimpleoclPackage.ITERATE_EXP__RESULT, IterateExp.class, msgs);
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
			case SimpleoclPackage.LOCAL_VARIABLE__LET_EXP:
				return getLetExp();
			case SimpleoclPackage.LOCAL_VARIABLE__INIT_EXPRESSION:
				return getInitExpression();
			case SimpleoclPackage.LOCAL_VARIABLE__BASE_EXP:
				return getBaseExp();
			case SimpleoclPackage.LOCAL_VARIABLE__EQ:
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
			case SimpleoclPackage.LOCAL_VARIABLE__LET_EXP:
				setLetExp((LetExp)newValue);
				return;
			case SimpleoclPackage.LOCAL_VARIABLE__INIT_EXPRESSION:
				setInitExpression((OclExpression)newValue);
				return;
			case SimpleoclPackage.LOCAL_VARIABLE__BASE_EXP:
				setBaseExp((IterateExp)newValue);
				return;
			case SimpleoclPackage.LOCAL_VARIABLE__EQ:
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
			case SimpleoclPackage.LOCAL_VARIABLE__LET_EXP:
				setLetExp((LetExp)null);
				return;
			case SimpleoclPackage.LOCAL_VARIABLE__INIT_EXPRESSION:
				setInitExpression((OclExpression)null);
				return;
			case SimpleoclPackage.LOCAL_VARIABLE__BASE_EXP:
				setBaseExp((IterateExp)null);
				return;
			case SimpleoclPackage.LOCAL_VARIABLE__EQ:
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
			case SimpleoclPackage.LOCAL_VARIABLE__LET_EXP:
				return getLetExp() != null;
			case SimpleoclPackage.LOCAL_VARIABLE__INIT_EXPRESSION:
				return initExpression != null;
			case SimpleoclPackage.LOCAL_VARIABLE__BASE_EXP:
				return getBaseExp() != null;
			case SimpleoclPackage.LOCAL_VARIABLE__EQ:
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

} //LocalVariableImpl
