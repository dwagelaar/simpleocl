/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.analysis;

public class OclModelElementExpModelReferenceResolver implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<org.eclipselabs.simpleocl.OclModelElementExp, org.eclipselabs.simpleocl.OclModel> {
	
	private org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultResolverDelegate<org.eclipselabs.simpleocl.OclModelElementExp, org.eclipselabs.simpleocl.OclModel> delegate = new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultResolverDelegate<org.eclipselabs.simpleocl.OclModelElementExp, org.eclipselabs.simpleocl.OclModel>();
	
	public void resolve(String identifier, org.eclipselabs.simpleocl.OclModelElementExp container, org.eclipse.emf.ecore.EReference reference, int position, boolean resolveFuzzy, final org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolveResult<org.eclipselabs.simpleocl.OclModel> result) {
		delegate.resolve(identifier, container, reference, position, resolveFuzzy, result);
	}
	
	public String deResolve(org.eclipselabs.simpleocl.OclModel element, org.eclipselabs.simpleocl.OclModelElementExp container, org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		// save options in a field or leave method empty if this resolver does not depend
		// on any option
	}
	
}
