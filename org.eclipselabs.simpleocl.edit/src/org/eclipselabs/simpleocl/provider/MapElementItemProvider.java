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
import org.eclipselabs.simpleocl.MapElement;
import org.eclipselabs.simpleocl.SimpleoclFactory;
import org.eclipselabs.simpleocl.SimpleoclPackage;

/**
 * This is the item provider adapter for a {@link org.eclipselabs.simpleocl.MapElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MapElementItemProvider
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
	public MapElementItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(SimpleoclPackage.Literals.MAP_ELEMENT__KEY);
			childrenFeatures.add(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE);
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
	 * This returns MapElement.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/MapElement"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		MapElement mapElement = (MapElement)object;
		return getString("_UI_MapElement_type") + " " + mapElement.getLine();
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

		switch (notification.getFeatureID(MapElement.class)) {
			case SimpleoclPackage.MAP_ELEMENT__KEY:
			case SimpleoclPackage.MAP_ELEMENT__VALUE:
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
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createVariableExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createSuperExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createSelfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createEnvExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createStringExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createBooleanExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createRealExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createIntegerExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createBagExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createOrderedSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createSequenceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createTupleExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createMapExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createEnumLiteralExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createOclUndefinedExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createStaticPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createOperatorCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createNotOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createRelOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createEqOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createAddOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createIntOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createMulOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createLambdaCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createBraceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createLetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createIfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__KEY,
				 SimpleoclFactory.eINSTANCE.createOclModelElementExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createVariableExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createSuperExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createSelfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createEnvExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createStringExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createBooleanExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createRealExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createIntegerExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createBagExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createOrderedSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createSequenceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createTupleExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createMapExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createEnumLiteralExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createOclUndefinedExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createStaticPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createOperatorCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createNotOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createRelOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createEqOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createAddOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createIntOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createMulOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createLambdaCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createBraceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createLetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createIfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.MAP_ELEMENT__VALUE,
				 SimpleoclFactory.eINSTANCE.createOclModelElementExp()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == SimpleoclPackage.Literals.MAP_ELEMENT__KEY ||
			childFeature == SimpleoclPackage.Literals.MAP_ELEMENT__VALUE;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
