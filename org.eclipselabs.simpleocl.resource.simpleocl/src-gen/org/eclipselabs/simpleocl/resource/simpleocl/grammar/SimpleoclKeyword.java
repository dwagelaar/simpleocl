/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.grammar;

/**
 * A class to represent a keyword in the grammar.
 */
public class SimpleoclKeyword extends org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement {
	
	private final String value;
	
	public SimpleoclKeyword(String value, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality) {
		super(cardinality, null);
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return value;
	}
	
}
