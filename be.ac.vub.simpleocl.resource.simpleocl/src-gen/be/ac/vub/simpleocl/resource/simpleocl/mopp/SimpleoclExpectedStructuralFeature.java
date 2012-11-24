/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

/**
 * A representation for a range in a document where a structural feature (e.g., a
 * reference) is expected.
 */
public class SimpleoclExpectedStructuralFeature extends be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclAbstractExpectedElement {
	
	private be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclPlaceholder placeholder;
	
	public SimpleoclExpectedStructuralFeature(be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclPlaceholder placeholder) {
		super(placeholder.getMetaclass());
		this.placeholder = placeholder;
	}
	
	public org.eclipse.emf.ecore.EStructuralFeature getFeature() {
		return placeholder.getFeature();
	}
	
	/**
	 * Returns the expected placeholder.
	 */
	public be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement getSymtaxElement() {
		return placeholder;
	}
	
	public String getTokenName() {
		return placeholder.getTokenName();
	}
	
	public java.util.Set<String> getTokenNames() {
		return java.util.Collections.singleton(getTokenName());
	}
	
	public String toString() {
		return "EFeature " + getFeature().getEContainingClass().getName() + "." + getFeature().getName();
	}
	
	public boolean equals(Object o) {
		if (o instanceof SimpleoclExpectedStructuralFeature) {
			return getFeature().equals(((SimpleoclExpectedStructuralFeature) o).getFeature());
		}
		return false;
	}
	@Override	
	public int hashCode() {
		return getFeature().hashCode();
	}
	
}
