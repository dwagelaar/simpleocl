/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

/**
 * A basic implementation of the
 * org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclURIMapping interface
 * that can map identifiers to URIs.
 * 
 * @param <ReferenceType> unused type parameter which is needed to implement
 * org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclURIMapping.
 */
public class SimpleoclURIMapping<ReferenceType> implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclURIMapping<ReferenceType> {
	
	private org.eclipse.emf.common.util.URI uri;
	private String identifier;
	private String warning;
	
	public SimpleoclURIMapping(String identifier, org.eclipse.emf.common.util.URI newIdentifier, String warning) {
		super();
		this.uri = newIdentifier;
		this.identifier = identifier;
		this.warning = warning;
	}
	
	public org.eclipse.emf.common.util.URI getTargetIdentifier() {
		return uri;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getWarning() {
		return warning;
	}
	
}
