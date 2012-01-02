/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

/**
 * A factory for ContextDependentURIFragments. Given a feasible reference
 * resolver, this factory returns a matching fragment that used the resolver to
 * resolver proxy objects.
 * 
 * @param <ContainerType> the type of the class containing the reference to be
 * resolved
 * @param <ReferenceType> the type of the reference to be resolved
 */
public class SimpleoclContextDependentURIFragmentFactory<ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject>  implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclContextDependentURIFragmentFactory<ContainerType, ReferenceType> {
	
	private final be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<ContainerType, ReferenceType> resolver;
	
	public SimpleoclContextDependentURIFragmentFactory(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<ContainerType, ReferenceType> resolver) {
		this.resolver = resolver;
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclContextDependentURIFragment<?> create(String identifier, ContainerType container, org.eclipse.emf.ecore.EReference reference, int positionInReference, org.eclipse.emf.ecore.EObject proxy) {
		
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclContextDependentURIFragment<ContainerType, ReferenceType>(identifier, container, reference, positionInReference, proxy) {
			public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<ContainerType, ReferenceType> getResolver() {
				return resolver;
			}
		};
	}
}
