/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

/**
 * A factory for ContextDependentURIFragments. Given a feasible reference
 * resolver, this factory returns a matching fragment that used the resolver to
 * resolver proxy objects.
 * 
 * @param <ContainerType> the type of the class containing the reference to be
 * resolved
 * @param <ReferenceType> the type of the reference to be resolved
 */
public class SimpleoclContextDependentURIFragmentFactory<ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject>  implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclContextDependentURIFragmentFactory<ContainerType, ReferenceType> {
	
	private final org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<ContainerType, ReferenceType> resolver;
	
	public SimpleoclContextDependentURIFragmentFactory(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<ContainerType, ReferenceType> resolver) {
		this.resolver = resolver;
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclContextDependentURIFragment<?> create(String identifier, ContainerType container, org.eclipse.emf.ecore.EReference reference, int positionInReference, org.eclipse.emf.ecore.EObject proxy) {
		
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContextDependentURIFragment<ContainerType, ReferenceType>(identifier, container, reference, positionInReference, proxy) {
			public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<ContainerType, ReferenceType> getResolver() {
				return resolver;
			}
		};
	}
}
