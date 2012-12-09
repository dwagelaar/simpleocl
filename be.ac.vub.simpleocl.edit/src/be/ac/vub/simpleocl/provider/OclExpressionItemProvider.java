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
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import be.ac.vub.simpleocl.OclExpression;
import be.ac.vub.simpleocl.SimpleoclFactory;
import be.ac.vub.simpleocl.SimpleoclPackage;

/**
 * This is the item provider adapter for a {@link be.ac.vub.simpleocl.OclExpression} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OclExpressionItemProvider
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
	public OclExpressionItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE);
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
		OclExpression oclExpression = (OclExpression)object;
		return getString("_UI_OclExpression_type") + " " + oclExpression.getLine();
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

		switch (notification.getFeatureID(OclExpression.class)) {
			case SimpleoclPackage.OCL_EXPRESSION__TYPE:
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
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createOclType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createCollectionType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createStringType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createBooleanType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createIntegerType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createRealType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createBagType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createOrderedSetType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createSequenceType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createSetType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createOclAnyType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createTupleType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createOclModelElement()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createMapType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createLambdaType()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.OCL_EXPRESSION__TYPE,
				 SimpleoclFactory.eINSTANCE.createEnvType()));
	}

}
