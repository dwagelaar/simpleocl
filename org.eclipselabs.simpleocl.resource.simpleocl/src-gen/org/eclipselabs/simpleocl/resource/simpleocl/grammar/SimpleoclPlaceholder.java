/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.grammar;

/**
 * A class to represent placeholders in a grammar.
 */
public class SimpleoclPlaceholder extends org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclTerminal {
	
	private final String tokenName;
	
	public SimpleoclPlaceholder(org.eclipse.emf.ecore.EStructuralFeature feature, String tokenName, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
		this.tokenName = tokenName;
	}
	
	public String getTokenName() {
		return tokenName;
	}
	
}
