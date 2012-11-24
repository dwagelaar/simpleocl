/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

public class SimpleoclReferenceResolverSwitch implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolverSwitch {
	
	/**
	 * This map stores a copy of the options the were set for loading the resource.
	 */
	private java.util.Map<Object, Object> options;
	
	protected be.ac.vub.simpleocl.resource.simpleocl.analysis.OclInstanceModelMetamodelReferenceResolver oclInstanceModelMetamodelReferenceResolver = new be.ac.vub.simpleocl.resource.simpleocl.analysis.OclInstanceModelMetamodelReferenceResolver();
	protected be.ac.vub.simpleocl.resource.simpleocl.analysis.OclModelElementExpModelReferenceResolver oclModelElementExpModelReferenceResolver = new be.ac.vub.simpleocl.resource.simpleocl.analysis.OclModelElementExpModelReferenceResolver();
	protected be.ac.vub.simpleocl.resource.simpleocl.analysis.VariableExpReferredVariableReferenceResolver variableExpReferredVariableReferenceResolver = new be.ac.vub.simpleocl.resource.simpleocl.analysis.VariableExpReferredVariableReferenceResolver();
	protected be.ac.vub.simpleocl.resource.simpleocl.analysis.OclModelElementModelReferenceResolver oclModelElementModelReferenceResolver = new be.ac.vub.simpleocl.resource.simpleocl.analysis.OclModelElementModelReferenceResolver();
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<be.ac.vub.simpleocl.OclInstanceModel, be.ac.vub.simpleocl.OclMetamodel> getOclInstanceModelMetamodelReferenceResolver() {
		return getResolverChain(be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getOclInstanceModel_Metamodel(), oclInstanceModelMetamodelReferenceResolver);
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<be.ac.vub.simpleocl.OclModelElementExp, be.ac.vub.simpleocl.OclModel> getOclModelElementExpModelReferenceResolver() {
		return getResolverChain(be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElementExp_Model(), oclModelElementExpModelReferenceResolver);
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<be.ac.vub.simpleocl.VariableExp, be.ac.vub.simpleocl.VariableDeclaration> getVariableExpReferredVariableReferenceResolver() {
		return getResolverChain(be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getVariableExp_ReferredVariable(), variableExpReferredVariableReferenceResolver);
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<be.ac.vub.simpleocl.OclModelElement, be.ac.vub.simpleocl.OclModel> getOclModelElementModelReferenceResolver() {
		return getResolverChain(be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElement_Model(), oclModelElementModelReferenceResolver);
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
			return getResolverChain(reference, oclInstanceModelMetamodelReferenceResolver);
		}
		if (reference == be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElementExp_Model()) {
			return getResolverChain(reference, oclModelElementExpModelReferenceResolver);
		}
		if (reference == be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getVariableExp_ReferredVariable()) {
			return getResolverChain(reference, variableExpReferredVariableReferenceResolver);
		}
		if (reference == be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getOclModelElement_Model()) {
			return getResolverChain(reference, oclModelElementModelReferenceResolver);
		}
		return null;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})	
	public <ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject> be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<ContainerType, ReferenceType> getResolverChain(org.eclipse.emf.ecore.EStructuralFeature reference, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<ContainerType, ReferenceType> originalResolver) {
		if (options == null) {
			return originalResolver;
		}
		Object value = options.get(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclOptions.ADDITIONAL_REFERENCE_RESOLVERS);
		if (value == null) {
			return originalResolver;
		}
		if (!(value instanceof java.util.Map)) {
			// send this to the error log
			new be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclRuntimeUtil().logWarning("Found value with invalid type for option " + be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + java.util.Map.class.getName() + ", but was " + value.getClass().getName() + ")", null);
			return originalResolver;
		}
		java.util.Map<?,?> resolverMap = (java.util.Map<?,?>) value;
		Object resolverValue = resolverMap.get(reference);
		if (resolverValue == null) {
			return originalResolver;
		}
		if (resolverValue instanceof be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver) {
			be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver replacingResolver = (be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver) resolverValue;
			if (replacingResolver instanceof be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclDelegatingReferenceResolver) {
				// pass original resolver to the replacing one
				((be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclDelegatingReferenceResolver) replacingResolver).setDelegate(originalResolver);
			}
			return replacingResolver;
		} else if (resolverValue instanceof java.util.Collection) {
			java.util.Collection replacingResolvers = (java.util.Collection) resolverValue;
			be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver replacingResolver = originalResolver;
			for (Object next : replacingResolvers) {
				if (next instanceof be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceCache) {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver nextResolver = (be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver) next;
					if (nextResolver instanceof be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclDelegatingReferenceResolver) {
						// pass original resolver to the replacing one
						((be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclDelegatingReferenceResolver) nextResolver).setDelegate(replacingResolver);
					}
					replacingResolver = nextResolver;
				} else {
					// The collection contains a non-resolver. Send a warning to the error log.
					new be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclRuntimeUtil().logWarning("Found value with invalid type in value map for option " + be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclDelegatingReferenceResolver.class.getName() + ", but was " + next.getClass().getName() + ")", null);
				}
			}
			return replacingResolver;
		} else {
			// The value for the option ADDITIONAL_REFERENCE_RESOLVERS has an unknown type.
			new be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclRuntimeUtil().logWarning("Found value with invalid type in value map for option " + be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclDelegatingReferenceResolver.class.getName() + ", but was " + resolverValue.getClass().getName() + ")", null);
			return originalResolver;
		}
	}
	
}
