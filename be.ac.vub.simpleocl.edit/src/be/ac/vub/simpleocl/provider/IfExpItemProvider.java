/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.provider;


import be.ac.vub.simpleocl.IfExp;
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
 * This is the item provider adapter for a {@link be.ac.vub.simpleocl.IfExp} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IfExpItemProvider
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
	public IfExpItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION);
			childrenFeatures.add(SimpleoclPackage.Literals.IF_EXP__CONDITION);
			childrenFeatures.add(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION);
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
	 * This returns IfExp.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/IfExp"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		IfExp ifExp = (IfExp)object;
		return getString("_UI_IfExp_type") + " " + ifExp.getLine();
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

		switch (notification.getFeatureID(IfExp.class)) {
			case SimpleoclPackage.IF_EXP__THEN_EXPRESSION:
			case SimpleoclPackage.IF_EXP__CONDITION:
			case SimpleoclPackage.IF_EXP__ELSE_EXPRESSION:
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
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createVariableExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createSuperExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createSelfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createEnvExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createStringExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createBooleanExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createRealExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createIntegerExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createBagExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createOrderedSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createSequenceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createTupleExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createMapExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createEnumLiteralExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createOclUndefinedExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createStaticPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createOperatorCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createNotOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createRelOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createEqOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createAddOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createIntOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createMulOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createLambdaCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createBraceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createLetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createIfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createOclModelElementExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createVariableExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createSuperExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createSelfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createEnvExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createStringExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createBooleanExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createRealExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createIntegerExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createBagExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createOrderedSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createSequenceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createTupleExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createMapExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createEnumLiteralExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createOclUndefinedExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createStaticPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createOperatorCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createNotOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createRelOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createEqOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createAddOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createIntOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createMulOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createLambdaCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createBraceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createLetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createIfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__CONDITION,
				 SimpleoclFactory.eINSTANCE.createOclModelElementExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createVariableExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createSuperExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createSelfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createEnvExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createStringExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createBooleanExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createRealExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createIntegerExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createBagExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createOrderedSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createSequenceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createSetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createTupleExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createMapExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createEnumLiteralExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createOclUndefinedExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createStaticPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createPropertyCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createOperatorCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createNotOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createRelOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createEqOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createAddOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createIntOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createMulOpCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createLambdaCallExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createBraceExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createLetExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
				 SimpleoclFactory.eINSTANCE.createIfExp()));

		newChildDescriptors.add
			(createChildParameter
				(SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION,
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
			childFeature == SimpleoclPackage.Literals.IF_EXP__THEN_EXPRESSION ||
			childFeature == SimpleoclPackage.Literals.IF_EXP__CONDITION ||
			childFeature == SimpleoclPackage.Literals.IF_EXP__ELSE_EXPRESSION;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
