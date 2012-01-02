/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

/**
 * A representation for a range in a document where a boolean attribute is
 * expected.
 */
public class SimpleoclExpectedBooleanTerminal extends be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclAbstractExpectedElement {
	
	private be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclBooleanTerminal booleanTerminal;
	
	public SimpleoclExpectedBooleanTerminal(be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclBooleanTerminal booleanTerminal) {
		super(booleanTerminal.getMetaclass());
		this.booleanTerminal = booleanTerminal;
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclBooleanTerminal getBooleanTerminal() {
		return booleanTerminal;
	}
	
	private org.eclipse.emf.ecore.EStructuralFeature getFeature() {
		return booleanTerminal.getFeature();
	}
	
	public String toString() {
		return "EFeature " + getFeature().getEContainingClass().getName() + "." + getFeature().getName();
	}
	
	public boolean equals(Object o) {
		if (o instanceof SimpleoclExpectedBooleanTerminal) {
			return getFeature().equals(((SimpleoclExpectedBooleanTerminal) o).getFeature());
		}
		return false;
	}
	
	public java.util.Set<String> getTokenNames() {
		// BooleanTerminals are associated with two or one token(s)
		java.util.Set<String> tokenNames = new java.util.LinkedHashSet<String>(2);
		String trueLiteral = booleanTerminal.getTrueLiteral();
		if (!"".equals(trueLiteral)) {
			tokenNames.add("'" + trueLiteral + "'");
		}
		String falseLiteral = booleanTerminal.getFalseLiteral();
		if (!"".equals(falseLiteral)) {
			tokenNames.add("'" + falseLiteral + "'");
		}
		return tokenNames;
	}
	
}
