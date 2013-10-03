/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

public class SimpleoclSyntaxElementDecorator {
	
	/**
	 * the syntax element to be decorated
	 */
	private org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement decoratedElement;
	
	/**
	 * an array of child decorators (one decorator per child of the decorated syntax
	 * element
	 */
	private SimpleoclSyntaxElementDecorator[] childDecorators;
	
	/**
	 * a list of the indices that must be printed
	 */
	private java.util.List<Integer> indicesToPrint = new java.util.ArrayList<Integer>();
	
	public SimpleoclSyntaxElementDecorator(org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement decoratedElement, SimpleoclSyntaxElementDecorator[] childDecorators) {
		super();
		this.decoratedElement = decoratedElement;
		this.childDecorators = childDecorators;
	}
	
	public void addIndexToPrint(Integer index) {
		indicesToPrint.add(index);
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement getDecoratedElement() {
		return decoratedElement;
	}
	
	public SimpleoclSyntaxElementDecorator[] getChildDecorators() {
		return childDecorators;
	}
	
	public Integer getNextIndexToPrint() {
		if (indicesToPrint.size() == 0) {
			return null;
		}
		return indicesToPrint.remove(0);
	}
	
	public String toString() {
		return "" + getDecoratedElement();
	}
	
}
