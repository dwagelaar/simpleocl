/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.analysis;

public class SimpleoclQUOTED_34_34_92TokenResolver implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolver {
	
	private be.ac.vub.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultTokenResolver defaultTokenResolver = new be.ac.vub.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultTokenResolver(true);
	
	public String deResolve(Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		// By default token de-resolving is delegated to the DefaultTokenResolver.
		String result = defaultTokenResolver.deResolve(value, feature, container, "\"", "\"", "\\");
		return result;
	}
	
	public void resolve(String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result) {
		// By default token resolving is delegated to the DefaultTokenResolver.
		defaultTokenResolver.resolve(lexem, feature, result, "\"", "\"", "\\");
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		defaultTokenResolver.setOptions(options);
	}
	
}
