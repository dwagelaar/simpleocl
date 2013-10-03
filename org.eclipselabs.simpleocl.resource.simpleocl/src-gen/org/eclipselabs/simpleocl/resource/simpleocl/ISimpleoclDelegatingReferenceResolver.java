/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl;

/**
 * A delegating reference resolver is an extension of a normal reference resolver
 * that can be configured with another resolver that it may delegate method calls
 * to. This interface can be implemented by additional resolvers to customize
 * resolving using the load option ADDITIONAL_REFERENCE_RESOLVERS.
 * 
 * @see org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclOptions
 */
public interface ISimpleoclDelegatingReferenceResolver<ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject> extends org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<ContainerType, ReferenceType> {
	
	/**
	 * Sets the delegate for this resolver.
	 */
	public void setDelegate(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolver<ContainerType, ReferenceType> delegate);
	
}
