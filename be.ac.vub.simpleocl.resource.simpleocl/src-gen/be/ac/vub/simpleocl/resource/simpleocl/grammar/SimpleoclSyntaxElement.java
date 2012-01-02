/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.grammar;

/**
 * The abstract super class for all elements of a grammar. This class provides
 * methods to traverse the grammar rules.
 */
public abstract class SimpleoclSyntaxElement {
	
	private SimpleoclSyntaxElement[] children;
	private SimpleoclSyntaxElement parent;
	private be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality;
	
	public SimpleoclSyntaxElement(be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality, SimpleoclSyntaxElement[] children) {
		this.cardinality = cardinality;
		this.children = children;
		if (this.children != null) {
			for (SimpleoclSyntaxElement child : this.children) {
				child.setParent(this);
			}
		}
	}
	
	public void setParent(SimpleoclSyntaxElement parent) {
		assert this.parent == null;
		this.parent = parent;
	}
	
	public SimpleoclSyntaxElement[] getChildren() {
		if (children == null) {
			return new SimpleoclSyntaxElement[0];
		}
		return children;
	}
	
	public org.eclipse.emf.ecore.EClass getMetaclass() {
		return parent.getMetaclass();
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality getCardinality() {
		return cardinality;
	}
	
}