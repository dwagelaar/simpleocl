/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.grammar;

public class SimpleoclChoice extends org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement {
	
	public SimpleoclChoice(org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement... choices) {
		super(cardinality, choices);
	}
	
	public String toString() {
		return org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclStringUtil.explode(getChildren(), "|");
	}
	
}
