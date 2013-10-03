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
import org.eclipselabs.simpleocl.PropertyCallExp;
import org.eclipselabs.simpleocl.SimpleoclFactory;
import org.eclipselabs.simpleocl.SimpleoclPackage;

/**
 * This is the item provider adapter for a {@link org.eclipselabs.simpleocl.PropertyCallExp} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PropertyCallExpItemProvider
	extends OclExpressionItemProvider
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
	public PropertyCallExpItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__CALLS);
			childrenFeatures.add(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE);
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
	 * This returns PropertyCallExp.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/PropertyCallExp"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		PropertyCallExp propertyCallExp = (PropertyCallExp)object;
		return getString("_UI_PropertyCallExp_type") + " " + propertyCallExp.getLine();
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

		switch (notification.getFeatureID(PropertyCallExp.class)) {
			case SimpleoclPackage.PROPERTY_CALL_EXP__CALLS:
			case SimpleoclPackage.PROPERTY_CALL_EXP__SOURCE:
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
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__CALLS,
				 SimpleoclFactory.eINSTANCE.createNavigationOrAttributeCall()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__CALLS,
				 SimpleoclFactory.eINSTANCE.createOperationCall()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__CALLS,
				 SimpleoclFactory.eINSTANCE.createCollectionOperationCall()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__CALLS,
				 SimpleoclFactory.eINSTANCE.createIterateExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__CALLS,
				 SimpleoclFactory.eINSTANCE.createIteratorExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createVariableExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createSuperExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createSelfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createEnvExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createStringExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createBooleanExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createRealExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createIntegerExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createBagExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createOrderedSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createSequenceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createTupleExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createMapExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createEnumLiteralExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createOclUndefinedExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createStaticPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createOperatorCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createNotOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createRelOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createEqOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createAddOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createIntOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createMulOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createLambdaCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createBraceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createLetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createIfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.PROPERTY_CALL_EXP__SOURCE,
				 SimpleoclFactory.eINSTANCE.createOclModelElementExp()));
	}

}
