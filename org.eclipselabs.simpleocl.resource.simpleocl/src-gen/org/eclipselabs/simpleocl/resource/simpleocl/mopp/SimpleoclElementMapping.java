/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

/**
 * A basic implementation of the
 * org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclElementMapping interface.
 * 
 * @param <ReferenceType> the type of the reference that can be mapped to
 */
public class SimpleoclElementMapping<ReferenceType> implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclElementMapping<ReferenceType> {
	
	private final ReferenceType target;
	private String identifier;
	private String warning;
	
	public SimpleoclElementMapping(String identifier, ReferenceType target, String warning) {
		super();
		this.target = target;
		this.identifier = identifier;
		this.warning = warning;
	}
	
	public ReferenceType getTargetElement() {
		return target;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getWarning() {
		return warning;
	}
	
}
