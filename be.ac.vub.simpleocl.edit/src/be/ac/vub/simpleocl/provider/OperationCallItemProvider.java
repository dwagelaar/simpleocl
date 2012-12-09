/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.provider;


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

import be.ac.vub.simpleocl.OperationCall;
import be.ac.vub.simpleocl.SimpleoclFactory;
import be.ac.vub.simpleocl.SimpleoclPackage;

/**
 * This is the item provider adapter for a {@link be.ac.vub.simpleocl.OperationCall} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OperationCallItemProvider
	extends PropertyCallItemProvider
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
	public OperationCallItemProvider(AdapterFactory adapterFactory) {
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

			addOperationNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Operation Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOperationNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_OperationCall_operationName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_OperationCall_operationName_feature", "_UI_OperationCall_type"),
				 SimpleoclPackage.Literals.OPERATION_CALL__OPERATION_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
			childrenFeatures.add(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS);
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
	 * This returns OperationCall.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/OperationCall"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((OperationCall)object).getOperationName();
		return label == null || label.length() == 0 ?
			getString("_UI_OperationCall_type") :
			getString("_UI_OperationCall_type") + " " + label;
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

		switch (notification.getFeatureID(OperationCall.class)) {
			case SimpleoclPackage.OPERATION_CALL__OPERATION_NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case SimpleoclPackage.OPERATION_CALL__ARGUMENTS:
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
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createVariableExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createSuperExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createSelfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createEnvExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createStringExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createBooleanExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createRealExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createIntegerExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createBagExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createOrderedSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createSequenceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createTupleExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createMapExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createEnumLiteralExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createOclUndefinedExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createStaticPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createOperatorCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createNotOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createRelOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createEqOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createAddOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createIntOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createMulOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createLambdaCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createBraceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createLetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createIfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OPERATION_CALL__ARGUMENTS,
				 SimpleoclFactory.eINSTANCE.createOclModelElementExp()));
	}

}
