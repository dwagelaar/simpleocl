/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

/**
 * A representation for a range in a document where an enumeration literal (i.e.,
 * a set of static strings) is expected.
 */
public class SimpleoclExpectedEnumerationTerminal extends org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclAbstractExpectedElement {
	
	private org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclEnumerationTerminal enumerationTerminal;
	
	public SimpleoclExpectedEnumerationTerminal(org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclEnumerationTerminal enumerationTerminal) {
		super(enumerationTerminal.getMetaclass());
		this.enumerationTerminal = enumerationTerminal;
	}
	
	public java.util.Set<String> getTokenNames() {
		// EnumerationTerminals are associated with multiple tokens, one for each literal
		// that was mapped to a string
		java.util.Set<String> tokenNames = new java.util.LinkedHashSet<String>();
		java.util.Map<String, String> mapping = enumerationTerminal.getLiteralMapping();
		for (String literalName : mapping.keySet()) {
			String text = mapping.get(literalName);
			if (text != null && !"".equals(text)) {
				tokenNames.add("'" + text + "'");
			}
		}
		return tokenNames;
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclEnumerationTerminal getEnumerationTerminal() {
		return this.enumerationTerminal;
	}
	
	/**
	 * Returns the expected enumeration terminal.
	 */
	public org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement getSymtaxElement() {
		return enumerationTerminal;
	}
	
	public String toString() {
		return "EnumTerminal \"" + getEnumerationTerminal() + "\"";
	}
	
}
