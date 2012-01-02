/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

/**
 * A basic implementation of the
 * be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclURIMapping interface that can
 * map identifiers to URIs.
 * 
 * @param <ReferenceType> unused type parameter which is needed to implement
 * be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclURIMapping.
 */
public class SimpleoclURIMapping<ReferenceType> implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclURIMapping<ReferenceType> {
	
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
