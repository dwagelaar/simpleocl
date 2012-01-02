/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.grammar;

public class SimpleoclChoice extends be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement {
	
	public SimpleoclChoice(be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality, be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement... choices) {
		super(cardinality, choices);
	}
	
	public String toString() {
		return be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclStringUtil.explode(getChildren(), "|");
	}
	
}
