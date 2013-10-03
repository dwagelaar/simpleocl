/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipselabs.simpleocl.SimpleoclFactory;
import org.eclipselabs.simpleocl.SimpleoclPackage;
import org.eclipselabs.simpleocl.VariableDeclaration;

/**
 * This is the item provider adapter for a {@link org.eclipselabs.simpleocl.VariableDeclaration} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class VariableDeclarationItemProvider
	extends LocatedElementItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclarationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addVarNamePropertyDescriptor(object);
			addVariableExpPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Var Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVarNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_VariableDeclaration_varName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_VariableDeclaration_varName_feature", "_UI_VariableDeclaration_type"),
				 SimpleoclPackage.Literals.VARIABLE_DECLARATION__VAR_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Variable Exp feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVariableExpPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_VariableDeclaration_variableExp_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_VariableDeclaration_variableExp_feature", "_UI_VariableDeclaration_type"),
				 SimpleoclPackage.Literals.VARIABLE_DECLARATION__VARIABLE_EXP,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((VariableDeclaration)object).getVarName();
		return label == null || label.length() == 0 ?
			getString("_UI_VariableDeclaration_type") :
			getString("_UI_VariableDeclaration_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(VariableDeclaration.class)) {
			case SimpleoclPackage.VARIABLE_DECLARATION__VAR_NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case SimpleoclPackage.VARIABLE_DECLARATION__TYPE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createOclType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createCollectionType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createStringType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createBooleanType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createIntegerType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createRealType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createBagType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createOrderedSetType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createSequenceType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createSetType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createOclAnyType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createTupleType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createOclModelElement()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createMapType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createLambdaType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.VARIABLE_DECLARATION__TYPE,
				 SimpleoclFactory.eINSTANCE.createEnvType()));
	}

}
