/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.grammar;

/**
 * A class to represent a rules in the grammar.
 */
public class SimpleoclRule extends be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement {
	
	private final org.eclipse.emf.ecore.EClass metaclass;
	
	public SimpleoclRule(org.eclipse.emf.ecore.EClass metaclass, be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclChoice choice, be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality) {
		super(cardinality, new be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement[] {choice});
		this.metaclass = metaclass;
	}
	
	public org.eclipse.emf.ecore.EClass getMetaclass() {
		return metaclass;
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclChoice getDefinition() {
		return (be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclChoice) getChildren()[0];
	}
	
}

