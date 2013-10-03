/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipselabs.simpleocl.Import;
import org.eclipselabs.simpleocl.Module;
import org.eclipselabs.simpleocl.ModuleElement;
import org.eclipselabs.simpleocl.OclMetamodel;
import org.eclipselabs.simpleocl.SimpleoclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.impl.ModuleImpl#getMetamodels <em>Metamodels</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.impl.ModuleImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.impl.ModuleImpl#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModuleImpl extends NamedElementImpl implements Module {
	/**
	 * The cached value of the '{@link #getMetamodels() <em>Metamodels</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetamodels()
	 * @generated
	 * @ordered
	 */
	protected EList<OclMetamodel> metamodels;

	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<Import> imports;

	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<ModuleElement> elements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.MODULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OclMetamodel> getMetamodels() {
		if (metamodels == null) {
			metamodels = new EObjectContainmentEList<OclMetamodel>(OclMetamodel.class, this, SimpleoclPackage.MODULE__METAMODELS);
		}
		return metamodels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Import> getImports() {
		if (imports == null) {
			imports = new EObjectContainmentWithInverseEList<Import>(Import.class, this, SimpleoclPackage.MODULE__IMPORTS, SimpleoclPackage.IMPORT__MODULE);
		}
		return imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModuleElement> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentWithInverseEList<ModuleElement>(ModuleElement.class, this, SimpleoclPackage.MODULE__ELEMENTS, SimpleoclPackage.MODULE_ELEMENT__MODULE);
		}
		return elements;
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
			case SimpleoclPackage.MODULE__IMPORTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getImports()).basicAdd(otherEnd, msgs);
			case SimpleoclPackage.MODULE__ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getElements()).basicAdd(otherEnd, msgs);
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
			case SimpleoclPackage.MODULE__METAMODELS:
				return ((InternalEList<?>)getMetamodels()).basicRemove(otherEnd, msgs);
			case SimpleoclPackage.MODULE__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
			case SimpleoclPackage.MODULE__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
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
			case SimpleoclPackage.MODULE__METAMODELS:
				return getMetamodels();
			case SimpleoclPackage.MODULE__IMPORTS:
				return getImports();
			case SimpleoclPackage.MODULE__ELEMENTS:
				return getElements();
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
			case SimpleoclPackage.MODULE__METAMODELS:
				getMetamodels().clear();
				getMetamodels().addAll((Collection<? extends OclMetamodel>)newValue);
				return;
			case SimpleoclPackage.MODULE__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends Import>)newValue);
				return;
			case SimpleoclPackage.MODULE__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends ModuleElement>)newValue);
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
			case SimpleoclPackage.MODULE__METAMODELS:
				getMetamodels().clear();
				return;
			case SimpleoclPackage.MODULE__IMPORTS:
				getImports().clear();
				return;
			case SimpleoclPackage.MODULE__ELEMENTS:
				getElements().clear();
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
			case SimpleoclPackage.MODULE__METAMODELS:
				return metamodels != null && !metamodels.isEmpty();
			case SimpleoclPackage.MODULE__IMPORTS:
				return imports != null && !imports.isEmpty();
			case SimpleoclPackage.MODULE__ELEMENTS:
				return elements != null && !elements.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ModuleImpl
