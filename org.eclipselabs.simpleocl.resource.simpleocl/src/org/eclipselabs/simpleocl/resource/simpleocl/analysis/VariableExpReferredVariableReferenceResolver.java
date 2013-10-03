/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.analysis;

public class VariableExpReferredVariableReferenceResolver implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<org.eclipselabs.simpleocl.VariableExp, org.eclipselabs.simpleocl.VariableDeclaration> {
	
	private org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultResolverDelegate<org.eclipselabs.simpleocl.VariableExp, org.eclipselabs.simpleocl.VariableDeclaration> delegate = new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultResolverDelegate<org.eclipselabs.simpleocl.VariableExp, org.eclipselabs.simpleocl.VariableDeclaration>();
	
	public void resolve(String identifier, org.eclipselabs.simpleocl.VariableExp container, org.eclipse.emf.ecore.EReference reference, int position, boolean resolveFuzzy, final org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolveResult<org.eclipselabs.simpleocl.VariableDeclaration> result) {
		delegate.resolve(identifier, container, reference, position, resolveFuzzy, result);
	}
	
	public String deResolve(org.eclipselabs.simpleocl.VariableDeclaration element, org.eclipselabs.simpleocl.VariableExp container, org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		// save options in a field or leave method empty if this resolver does not depend
		// on any option
	}
	
}
