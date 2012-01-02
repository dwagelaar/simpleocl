/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.grammar;

public class SimpleoclWhiteSpace extends be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclFormattingElement {
	
	private final int amount;
	
	public SimpleoclWhiteSpace(int amount, be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality) {
		super(cardinality);
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String toString() {
		return "#" + getAmount();
	}
	
}
