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
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipselabs.simpleocl.CollectionType;
import org.eclipselabs.simpleocl.SimpleoclFactory;
import org.eclipselabs.simpleocl.SimpleoclPackage;

/**
 * This is the item provider adapter for a {@link org.eclipselabs.simpleocl.CollectionType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CollectionTypeItemProvider
	extends OclTypeItemProvider
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
	public CollectionTypeItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE);
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
	 * This returns CollectionType.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CollectionType"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((CollectionType)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_CollectionType_type") :
			getString("_UI_CollectionType_type") + " " + label;
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

		switch (notification.getFeatureID(CollectionType.class)) {
			case SimpleoclPackage.COLLECTION_TYPE__ELEMENT_TYPE:
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
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createOclType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createCollectionType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createStringType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createBooleanType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createIntegerType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createRealType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createBagType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createOrderedSetType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createSequenceType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createSetType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createOclAnyType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createTupleType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createOclModelElement()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createMapType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createLambdaType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.COLLECTION_TYPE__ELEMENT_TYPE,
				 SimpleoclFactory.eINSTANCE.createEnvType()));
	}

}
