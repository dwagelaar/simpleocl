/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

public class SimpleoclReferenceResolverSwitch implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolverSwitch {
	
	/**
	 * This map stores a copy of the options the were set for loading the resource.
	 */
	private java.util.Map<Object, Object> options;
	
	protected org.eclipselabs.simpleocl.resource.simpleocl.analysis.OclInstanceModelMetamodelReferenceResolver oclInstanceModelMetamodelReferenceResolver = new org.eclipselabs.simpleocl.resource.simpleocl.analysis.OclInstanceModelMetamodelReferenceResolver();
	protected org.eclipselabs.simpleocl.resource.simpleocl.analysis.OclModelElementExpModelReferenceResolver oclModelElementExpModelReferenceResolver = new org.eclipselabs.simpleocl.resource.simpleocl.analysis.OclModelElementExpModelReferenceResolver();
	protected org.eclipselabs.simpleocl.resource.simpleocl.analysis.VariableExpReferredVariableReferenceResolver variableExpReferredVariableReferenceResolver = new org.eclipselabs.simpleocl.resource.simpleocl.analysis.VariableExpReferredVariableReferenceResolver();
	protected org.eclipselabs.simpleocl.resource.simpleocl.analysis.OclModelElementModelReferenceResolver oclModelElementModelReferenceResolver = new org.eclipselabs.simpleocl.resource.simpleocl.analysis.OclModelElementModelReferenceResolver();
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<org.eclipselabs.simpleocl.OclInstanceModel, org.eclipselabs.simpleocl.OclMetamodel> getOclInstanceModelMetamodelReferenceResolver() {
		return getResolverChain(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclInstanceModel_Metamodel(), oclInstanceModelMetamodelReferenceResolver);
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<org.eclipselabs.simpleocl.OclModelElementExp, org.eclipselabs.simpleocl.OclModel> getOclModelElementExpModelReferenceResolver() {
		return getResolverChain(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElementExp_Model(), oclModelElementExpModelReferenceResolver);
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<org.eclipselabs.simpleocl.VariableExp, org.eclipselabs.simpleocl.VariableDeclaration> getVariableExpReferredVariableReferenceResolver() {
		return getResolverChain(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getVariableExp_ReferredVariable(), variableExpReferredVariableReferenceResolver);
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<org.eclipselabs.simpleocl.OclModelElement, org.eclipselabs.simpleocl.OclModel> getOclModelElementModelReferenceResolver() {
		return getResolverChain(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElement_Model(), oclModelElementModelReferenceResolver);
	}
	
	public void setOptions(java.util.Map<?, ?> options) {
		if (options != null) {
			this.options = new java.util.LinkedHashMap<Object, Object>();
			this.options.putAll(options);
		}
		oclInstanceModelMetamodelReferenceResolver.setOptions(options);
		oclModelElementExpModelReferenceResolver.setOptions(options);
		variableExpReferredVariableReferenceResolver.setOptions(options);
		oclModelElementModelReferenceResolver.setOptions(options);
	}
	
	public void resolveFuzzy(String identifier, org.eclipse.emf.ecore.EObject container, org.eclipse.emf.ecore.EReference reference, int position, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolveResult<org.eclipse.emf.ecore.EObject> result) {
		if (container == null) {
			return;
		}
		if (org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclInstanceModel().isInstance(container)) {
			SimpleoclFuzzyResolveResult<org.eclipselabs.simpleocl.OclMetamodel> frr = new SimpleoclFuzzyResolveResult<org.eclipselabs.simpleocl.OclMetamodel>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("metamodel")) {
				oclInstanceModelMetamodelReferenceResolver.resolve(identifier, (org.eclipselabs.simpleocl.OclInstanceModel) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElementExp().isInstance(container)) {
			SimpleoclFuzzyResolveResult<org.eclipselabs.simpleocl.OclModel> frr = new SimpleoclFuzzyResolveResult<org.eclipselabs.simpleocl.OclModel>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("model")) {
				oclModelElementExpModelReferenceResolver.resolve(identifier, (org.eclipselabs.simpleocl.OclModelElementExp) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getVariableExp().isInstance(container)) {
			SimpleoclFuzzyResolveResult<org.eclipselabs.simpleocl.VariableDeclaration> frr = new SimpleoclFuzzyResolveResult<org.eclipselabs.simpleocl.VariableDeclaration>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("referredVariable")) {
				variableExpReferredVariableReferenceResolver.resolve(identifier, (org.eclipselabs.simpleocl.VariableExp) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElement().isInstance(container)) {
			SimpleoclFuzzyResolveResult<org.eclipselabs.simpleocl.OclModel> frr = new SimpleoclFuzzyResolveResult<org.eclipselabs.simpleocl.OclModel>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("model")) {
				oclModelElementModelReferenceResolver.resolve(identifier, (org.eclipselabs.simpleocl.OclModelElement) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<? extends org.eclipse.emf.ecore.EObject, ? extends org.eclipse.emf.ecore.EObject> getResolver(org.eclipse.emf.ecore.EStructuralFeature reference) {
		if (reference == org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclInstanceModel_Metamodel()) {
			return getResolverChain(reference, oclInstanceModelMetamodelReferenceResolver);
		}
		if (reference == org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElementExp_Model()) {
			return getResolverChain(reference, oclModelElementExpModelReferenceResolver);
		}
		if (reference == org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getVariableExp_ReferredVariable()) {
			return getResolverChain(reference, variableExpReferredVariableReferenceResolver);
		}
		if (reference == org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElement_Model()) {
			return getResolverChain(reference, oclModelElementModelReferenceResolver);
		}
		return null;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})	
	public <ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject> org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<ContainerType, ReferenceType> getResolverChain(org.eclipse.emf.ecore.EStructuralFeature reference, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<ContainerType, ReferenceType> originalResolver) {
		if (options == null) {
			return originalResolver;
		}
		Object value = options.get(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclOptions.ADDITIONAL_REFERENCE_RESOLVERS);
		if (value == null) {
			return originalResolver;
		}
		if (!(value instanceof java.util.Map)) {
			// send this to the error log
			new org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclRuntimeUtil().logWarning("Found value with invalid type for option " + org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + java.util.Map.class.getName() + ", but was " + value.getClass().getName() + ")", null);
			return originalResolver;
		}
		java.util.Map<?,?> resolverMap = (java.util.Map<?,?>) value;
		Object resolverValue = resolverMap.get(reference);
		if (resolverValue == null) {
			return originalResolver;
		}
		if (resolverValue instanceof org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver) {
			org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver replacingResolver = (org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver) resolverValue;
			if (replacingResolver instanceof org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclDelegatingReferenceResolver) {
				// pass original resolver to the replacing one
				((org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclDelegatingReferenceResolver) replacingResolver).setDelegate(originalResolver);
			}
			return replacingResolver;
		} else if (resolverValue instanceof java.util.Collection) {
			java.util.Collection replacingResolvers = (java.util.Collection) resolverValue;
			org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver replacingResolver = originalResolver;
			for (Object next : replacingResolvers) {
				if (next instanceof org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceCache) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver nextResolver = (org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver) next;
					if (nextResolver instanceof org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclDelegatingReferenceResolver) {
						// pass original resolver to the replacing one
						((org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclDelegatingReferenceResolver) nextResolver).setDelegate(replacingResolver);
					}
					replacingResolver = nextResolver;
				} else {
					// The collection contains a non-resolver. Send a warning to the error log.
					new org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclRuntimeUtil().logWarning("Found value with invalid type in value map for option " + org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclDelegatingReferenceResolver.class.getName() + ", but was " + next.getClass().getName() + ")", null);
				}
			}
			return replacingResolver;
		} else {
			// The value for the option ADDITIONAL_REFERENCE_RESOLVERS has an unknown type.
			new org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclRuntimeUtil().logWarning("Found value with invalid type in value map for option " + org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclDelegatingReferenceResolver.class.getName() + ", but was " + resolverValue.getClass().getName() + ")", null);
			return originalResolver;
		}
	}
	
}
