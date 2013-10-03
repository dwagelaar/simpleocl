/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

public class SimpleoclSyntaxCoverageInformationProvider {
	
	public org.eclipse.emf.ecore.EClass[] getClassesWithSyntax() {
		return new org.eclipse.emf.ecore.EClass[] {
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getImport(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclFeatureDefinition(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclMetamodel(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclInstanceModel(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElementExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getVariableExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSuperExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSelfExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEnvExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStringExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBooleanExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRealExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntegerExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionItem(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEnumLiteralExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclUndefinedExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticNavigationOrAttributeCall(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNavigationOrAttributeCall(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStringType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBooleanType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntegerType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRealType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclAnyType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElement(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(),
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEnvType(),
		};
	}
	
	public org.eclipse.emf.ecore.EClass[] getStartSymbols() {
		return new org.eclipse.emf.ecore.EClass[] {
			org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(),
		};
	}
	
}
