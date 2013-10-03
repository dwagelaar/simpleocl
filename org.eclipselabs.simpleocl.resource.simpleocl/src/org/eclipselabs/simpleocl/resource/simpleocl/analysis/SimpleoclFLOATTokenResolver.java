/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.analysis;

public class SimpleoclFLOATTokenResolver implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver {
	
	private org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultTokenResolver defaultTokenResolver = new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultTokenResolver(true);
	
	public String deResolve(Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		// By default token de-resolving is delegated to the DefaultTokenResolver.
		String result = defaultTokenResolver.deResolve(value, feature, container, null, null, null);
		return result;
	}
	
	public void resolve(String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result) {
		// By default token resolving is delegated to the DefaultTokenResolver.
		defaultTokenResolver.resolve(lexem, feature, result, null, null, null);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		defaultTokenResolver.setOptions(options);
	}
	
}
