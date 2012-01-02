/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.grammar;

public class SimpleoclSequence extends be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement {
	
	public SimpleoclSequence(be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality, be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement... elements) {
		super(cardinality, elements);
	}
	
	public String toString() {
		return be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclStringUtil.explode(getChildren(), " ");
	}
	
}
