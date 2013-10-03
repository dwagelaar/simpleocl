/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.grammar;

public class SimpleoclLineBreak extends org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclFormattingElement {
	
	private final int tabs;
	
	public SimpleoclLineBreak(org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality, int tabs) {
		super(cardinality);
		this.tabs = tabs;
	}
	
	public int getTabs() {
		return tabs;
	}
	
	public String toString() {
		return "!" + getTabs();
	}
	
}
