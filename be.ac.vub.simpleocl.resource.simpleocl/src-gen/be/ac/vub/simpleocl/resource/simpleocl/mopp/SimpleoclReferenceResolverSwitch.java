/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

public class SimpleoclReferenceResolverSwitch implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolverSwitch {
	
	protected be.ac.vub.simpleocl.resource.simpleocl.analysis.OclInstanceModelMetamodelReferenceResolver oclInstanceModelMetamodelReferenceResolver = new be.ac.vub.simpleocl.resource.simpleocl.analysis.OclInstanceModelMetamodelReferenceResolver();
	protected be.ac.vub.simpleocl.resource.simpleocl.analysis.OclModelElementExpModelReferenceResolver oclModelElementExpModelReferenceResolver = new be.ac.vub.simpleocl.resource.simpleocl.analysis.OclModelElementExpModelReferenceResolver();
	protected be.ac.vub.simpleocl.resource.simpleocl.analysis.VariableExpReferredVariableReferenceResolver variableExpReferredVariableReferenceResolver = new be.ac.vub.simpleocl.resource.simpleocl.analysis.VariableExpReferredVariableReferenceResolver();
	protected be.ac.vub.simpleocl.resource.simpleocl.analysis.OclModelElementModelReferenceResolver oclModelElementModelReferenceResolver = new be.ac.vub.simpleocl.resource.simpleocl.analysis.OclModelElementModelReferenceResolver();
	
	public be.ac.vub.simpleocl.resource.simpleocl.analysis.OclInstanceModelMetamodelReferenceResolver getOclInstanceModelMetamodelReferenceResolver() {
		return oclInstanceModelMetamodelReferenceResolver;
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.analysis.OclModelElementExpModelReferenceResolver getOclModelElementExpModelReferenceResolver() {
		return oclModelElementExpModelReferenceResolver;
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.analysis.VariableExpReferredVariableReferenceResolver getVariableExpReferredVariableReferenceResolver() {
		return variableExpReferredVariableReferenceResolver;
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.analysis.OclModelElementModelReferenceResolver getOclModelElementModelReferenceResolver() {
		return oclModelElementModelReferenceResolver;
	}
	
	public void setOptions(java.util.Map<?, ?> options) {
		oclInstanceModelMetamodelReferenceResolver.setOptions(options);
		oclModelElementExpModelReferenceResolver.setOptions(options);
		variableExpReferredVariableReferenceResolver.setOptions(options);
		oclModelElementModelReferenceResolver.setOptions(options);
	}
	
	public void resolveFuzzy(String identifier, org.eclipse.emf.ecore.EObject container, org.eclipse.emf.ecore.EReference reference, int position, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolveResult<org.eclipse.emf.ecore.EObject> result) {
		if (container == null) {
			return;
		}
		if (be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getOclInstanceModel().isInstance(container)) {
			SimpleoclFuzzyResolveResult<be.ac.vub.simpleocl.OclMetamodel> frr = new SimpleoclFuzzyResolveResult<be.ac.vub.simpleocl.OclMetamodel>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("metamodel")) {
				oclInstanceModelMetamodelReferenceResolver.resolve(identifier, (be.ac.vub.simpleocl.OclInstanceModel) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElementExp().isInstance(container)) {
			SimpleoclFuzzyResolveResult<be.ac.vub.simpleocl.OclModel> frr = new SimpleoclFuzzyResolveResult<be.ac.vub.simpleocl.OclModel>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("model")) {
				oclModelElementExpModelReferenceResolver.resolve(identifier, (be.ac.vub.simpleocl.OclModelElementExp) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getVariableExp().isInstance(container)) {
			SimpleoclFuzzyResolveResult<be.ac.vub.simpleocl.VariableDeclaration> frr = new SimpleoclFuzzyResolveResult<be.ac.vub.simpleocl.VariableDeclaration>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("referredVariable")) {
				variableExpReferredVariableReferenceResolver.resolve(identifier, (be.ac.vub.simpleocl.VariableExp) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElement().isInstance(container)) {
			SimpleoclFuzzyResolveResult<be.ac.vub.simpleocl.OclModel> frr = new SimpleoclFuzzyResolveResult<be.ac.vub.simpleocl.OclModel>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("model")) {
				oclModelElementModelReferenceResolver.resolve(identifier, (be.ac.vub.simpleocl.OclModelElement) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<? extends org.eclipse.emf.ecore.EObject, ? extends org.eclipse.emf.ecore.EObject> getResolver(org.eclipse.emf.ecore.EStructuralFeature reference) {
		if (reference == be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getOclInstanceModel_Metamodel()) {
			return oclInstanceModelMetamodelReferenceResolver;
		}
		if (reference == be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElementExp_Model()) {
			return oclModelElementExpModelReferenceResolver;
		}
		if (reference == be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getVariableExp_ReferredVariable()) {
			return variableExpReferredVariableReferenceResolver;
		}
		if (reference == be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElement_Model()) {
			return oclModelElementModelReferenceResolver;
		}
		return null;
	}
	
}
