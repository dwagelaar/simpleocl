/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

/**
 * A representation for a range in a document where a keyword (i.e., a static
 * string) is expected.
 */
public class SimpleoclExpectedCsString extends org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclAbstractExpectedElement {
	
	private org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclKeyword keyword;
	
	public SimpleoclExpectedCsString(org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclKeyword keyword) {
		super(keyword.getMetaclass());
		this.keyword = keyword;
	}
	
	public String getValue() {
		return keyword.getValue();
	}
	
	/**
	 * Returns the expected keyword.
	 */
	public org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement getSymtaxElement() {
		return keyword;
	}
	
	public java.util.Set<String> getTokenNames() {
		return java.util.Collections.singleton("'" + getValue() + "'");
	}
	
	public String toString() {
		return "CsString \"" + getValue() + "\"";
	}
	
	public boolean equals(Object o) {
		if (o instanceof SimpleoclExpectedCsString) {
			return getValue().equals(((SimpleoclExpectedCsString) o).getValue());
		}
		return false;
	}
	
	@Override	
	public int hashCode() {
		return getValue().hashCode();
	}
	
}
