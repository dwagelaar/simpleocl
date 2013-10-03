/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.analysis;

public class OclInstanceModelMetamodelReferenceResolver implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<org.eclipselabs.simpleocl.OclInstanceModel, org.eclipselabs.simpleocl.OclMetamodel> {
	
	private org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultResolverDelegate<org.eclipselabs.simpleocl.OclInstanceModel, org.eclipselabs.simpleocl.OclMetamodel> delegate = new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultResolverDelegate<org.eclipselabs.simpleocl.OclInstanceModel, org.eclipselabs.simpleocl.OclMetamodel>();
	
	public void resolve(String identifier, org.eclipselabs.simpleocl.OclInstanceModel container, org.eclipse.emf.ecore.EReference reference, int position, boolean resolveFuzzy, final org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolveResult<org.eclipselabs.simpleocl.OclMetamodel> result) {
		delegate.resolve(identifier, container, reference, position, resolveFuzzy, result);
	}
	
	public String deResolve(org.eclipselabs.simpleocl.OclMetamodel element, org.eclipselabs.simpleocl.OclInstanceModel container, org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		// save options in a field or leave method empty if this resolver does not depend
		// on any option
	}
	
}
