/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.grammar;

/**
 * A class to represent a rules in the grammar.
 */
public class SimpleoclRule extends org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement {
	
	private final org.eclipse.emf.ecore.EClass metaclass;
	
	public SimpleoclRule(org.eclipse.emf.ecore.EClass metaclass, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclChoice choice, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality) {
		super(cardinality, new org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement[] {choice});
		this.metaclass = metaclass;
	}
	
	public org.eclipse.emf.ecore.EClass getMetaclass() {
		return metaclass;
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclChoice getDefinition() {
		return (org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclChoice) getChildren()[0];
	}
	
}

