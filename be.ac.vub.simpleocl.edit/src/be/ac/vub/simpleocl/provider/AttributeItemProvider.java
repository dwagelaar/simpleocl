/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.provider;


import be.ac.vub.simpleocl.Attribute;
import be.ac.vub.simpleocl.SimpleoclFactory;
import be.ac.vub.simpleocl.SimpleoclPackage;

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

/**
 * This is the item provider adapter for a {@link be.ac.vub.simpleocl.Attribute} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AttributeItemProvider
	extends OclFeatureItemProvider
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
	public AttributeItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION);
			childrenFeatures.add(SimpleoclPackage.Literals.ATTRIBUTE__TYPE);
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
	 * This returns Attribute.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Attribute"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Attribute)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Attribute_type") :
			getString("_UI_Attribute_type") + " " + label;
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

		switch (notification.getFeatureID(Attribute.class)) {
			case SimpleoclPackage.ATTRIBUTE__INIT_EXPRESSION:
			case SimpleoclPackage.ATTRIBUTE__TYPE:
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
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createVariableExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createSuperExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createSelfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createEnvExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createStringExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createBooleanExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createRealExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createIntegerExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createBagExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createOrderedSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createSequenceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createTupleExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createMapExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createEnumLiteralExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createOclUndefinedExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createStaticPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createOperatorCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createNotOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createRelOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createEqOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createAddOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createIntOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createMulOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createLambdaCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createBraceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createLetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createIfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__INIT_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createOclModelElementExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createCollectionType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createStringType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createBooleanType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createIntegerType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createRealType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createBagType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createOrderedSetType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createSequenceType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createSetType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createOclAnyType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createTupleType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createOclModelElement()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createMapType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createLambdaType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.ATTRIBUTE__TYPE,
				 SimpleoclFactory.eINSTANCE.createEnvType()));
	}

}
