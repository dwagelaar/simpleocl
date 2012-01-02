/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.grammar;

public class SimpleoclContainment extends be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclTerminal {
	
	public SimpleoclContainment(org.eclipse.emf.ecore.EStructuralFeature feature, be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
	}
	
	public String toString() {
		return getFeature().getName();
	}
	
}
