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
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipselabs.simpleocl.SimpleoclFactory;
import org.eclipselabs.simpleocl.SimpleoclPackage;
import org.eclipselabs.simpleocl.StaticPropertyCallExp;

/**
 * This is the item provider adapter for a {@link org.eclipselabs.simpleocl.StaticPropertyCallExp} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StaticPropertyCallExpItemProvider
	extends OclExpressionItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticPropertyCallExpItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE);
			childrenFeatures.add(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__STATIC_CALL);
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
	 * This returns StaticPropertyCallExp.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/StaticPropertyCallExp"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		StaticPropertyCallExp staticPropertyCallExp = (StaticPropertyCallExp)object;
		return getString("_UI_StaticPropertyCallExp_type") + " " + staticPropertyCallExp.getLine();
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

		switch (notification.getFeatureID(StaticPropertyCallExp.class)) {
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE:
			case SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL:
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
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createOclType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createCollectionType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createStringType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createBooleanType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createIntegerType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createRealType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createBagType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createOrderedSetType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createSequenceType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createSetType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createOclAnyType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createTupleType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createOclModelElement()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createMapType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createLambdaType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createEnvType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__STATIC_CALL,
				 SimpleoclFactory.eINSTANCE.createStaticNavigationOrAttributeCall()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__STATIC_CALL,
				 SimpleoclFactory.eINSTANCE.createStaticOperationCall()));
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
			childFeature == SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE ||
			childFeature == SimpleoclPackage.Literals.STATIC_PROPERTY_CALL_EXP__SOURCE;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
