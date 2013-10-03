/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

public class SimpleoclAntlrScanner implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextScanner {
	
	private org.antlr.runtime3_4_0.Lexer antlrLexer;
	
	public SimpleoclAntlrScanner(org.antlr.runtime3_4_0.Lexer antlrLexer) {
		this.antlrLexer = antlrLexer;
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextToken getNextToken() {
		if (antlrLexer.getCharStream() == null) {
			return null;
		}
		final org.antlr.runtime3_4_0.Token current = antlrLexer.nextToken();
		if (current == null || current.getType() < 0) {
			return null;
		}
		org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextToken result = new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclANTLRTextToken(current);
		return result;
	}
	
	public void setText(String text) {
		antlrLexer.setCharStream(new org.antlr.runtime3_4_0.ANTLRStringStream(text));
	}
	
}
