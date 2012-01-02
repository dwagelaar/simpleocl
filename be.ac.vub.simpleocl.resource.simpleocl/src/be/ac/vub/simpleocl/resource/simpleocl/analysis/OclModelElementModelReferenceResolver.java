/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.analysis;

public class OclModelElementModelReferenceResolver implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<be.ac.vub.simpleocl.OclModelElement, be.ac.vub.simpleocl.OclModel> {
	
	private be.ac.vub.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultResolverDelegate<be.ac.vub.simpleocl.OclModelElement, be.ac.vub.simpleocl.OclModel> delegate = new be.ac.vub.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultResolverDelegate<be.ac.vub.simpleocl.OclModelElement, be.ac.vub.simpleocl.OclModel>();
	
	public void resolve(String identifier, be.ac.vub.simpleocl.OclModelElement container, org.eclipse.emf.ecore.EReference reference, int position, boolean resolveFuzzy, final be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolveResult<be.ac.vub.simpleocl.OclModel> result) {
		delegate.resolve(identifier, container, reference, position, resolveFuzzy, result);
	}
	
	public String deResolve(be.ac.vub.simpleocl.OclModel element, be.ac.vub.simpleocl.OclModelElement container, org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		// save options in a field or leave method empty if this resolver does not depend
		// on any option
	}
	
}
