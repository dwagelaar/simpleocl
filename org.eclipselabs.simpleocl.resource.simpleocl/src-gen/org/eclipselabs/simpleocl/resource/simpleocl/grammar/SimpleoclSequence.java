/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.grammar;

public class SimpleoclSequence extends org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement {
	
	public SimpleoclSequence(org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement... elements) {
		super(cardinality, elements);
	}
	
	public String toString() {
		return org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclStringUtil.explode(getChildren(), " ");
	}
	
}
