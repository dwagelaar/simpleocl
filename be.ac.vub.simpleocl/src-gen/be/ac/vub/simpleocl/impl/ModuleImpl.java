/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import be.ac.vub.simpleocl.Import;
import be.ac.vub.simpleocl.Module;
import be.ac.vub.simpleocl.OclFeatureDefinition;
import be.ac.vub.simpleocl.OclMetamodel;
import be.ac.vub.simpleocl.SimpleoclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link be.ac.vub.simpleocl.impl.ModuleImpl#getMetamodels <em>Metamodels</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.impl.ModuleImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.impl.ModuleImpl#getImports <em>Imports</em>}</li>
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
	 * The cached value of the '{@link #getFeatures() <em>Features</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<OclFeatureDefinition> features;

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
	public EList<OclFeatureDefinition> getFeatures() {
		if (features == null) {
			features = new EObjectContainmentEList<OclFeatureDefinition>(OclFeatureDefinition.class, this, SimpleoclPackage.MODULE__FEATURES);
		}
		return features;
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimpleoclPackage.MODULE__IMPORTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getImports()).basicAdd(otherEnd, msgs);
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
			case SimpleoclPackage.MODULE__FEATURES:
				return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
			case SimpleoclPackage.MODULE__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
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
			case SimpleoclPackage.MODULE__FEATURES:
				return getFeatures();
			case SimpleoclPackage.MODULE__IMPORTS:
				return getImports();
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
			case SimpleoclPackage.MODULE__FEATURES:
				getFeatures().clear();
				getFeatures().addAll((Collection<? extends OclFeatureDefinition>)newValue);
				return;
			case SimpleoclPackage.MODULE__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends Import>)newValue);
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
			case SimpleoclPackage.MODULE__FEATURES:
				getFeatures().clear();
				return;
			case SimpleoclPackage.MODULE__IMPORTS:
				getImports().clear();
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
			case SimpleoclPackage.MODULE__FEATURES:
				return features != null && !features.isEmpty();
			case SimpleoclPackage.MODULE__IMPORTS:
				return imports != null && !imports.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ModuleImpl
