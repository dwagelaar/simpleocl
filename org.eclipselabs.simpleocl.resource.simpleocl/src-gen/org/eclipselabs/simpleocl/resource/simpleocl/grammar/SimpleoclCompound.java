/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.grammar;

public class SimpleoclCompound extends org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement {
	
	public SimpleoclCompound(org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclChoice choice, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality) {
		super(cardinality, new org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement[] {choice});
	}
	
	public String toString() {
		return "(" + getChildren()[0] + ")";
	}
	
}
