/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

public class SimpleoclBracketInformationProvider {
	
	public class BracketPair implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclBracketPair {
		
		private String opening;
		private String closing;
		private boolean closingEnabledInside;
		
		public BracketPair(String opening, String closing, boolean closingEnabledInside) {
			super();
			this.opening = opening;
			this.closing = closing;
			this.closingEnabledInside = closingEnabledInside;
		}
		
		public String getOpeningBracket() {
			return opening;
		}
		
		public String getClosingBracket() {
			return closing;
		}
		
		public boolean isClosingEnabledInside() {
			return closingEnabledInside;
		}
	}
	
	public java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclBracketPair> getBracketPairs() {
		java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclBracketPair> result = new java.util.ArrayList<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclBracketPair>();
		result.add(new BracketPair("(", ")", true));
		result.add(new BracketPair("{", "}", true));
		result.add(new BracketPair("\"", "\"", false));
		result.add(new BracketPair("'", "'", false));
		return result;
	}
	
}
