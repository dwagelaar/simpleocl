/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl;

/**
 * An element that is expected at a given position in a resource stream.
 */
public interface ISimpleoclExpectedElement {
	
	/**
	 * Returns the names of all tokens that are expected at the given position.
	 */
	public java.util.Set<String> getTokenNames();
	
	/**
	 * Returns the metaclass of the rule that contains the expected element.
	 */
	public org.eclipse.emf.ecore.EClass getRuleMetaclass();
	
	/**
	 * Returns the syntax element that is expected.
	 */
	public be.ac.vub.simpleocl.resource.simpleocl.grammar.SimpleoclSyntaxElement getSymtaxElement();
	
	/**
	 * Adds an element that is a valid follower for this element.
	 */
	public void addFollower(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclExpectedElement follower, be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[] path);
	
	/**
	 * Returns all valid followers for this element. Each follower is represented by a
	 * pair of an expected elements and the containment trace that leads from the
	 * current element to the follower.
	 */
	public java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclPair<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclExpectedElement, be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[]>> getFollowers();
	
}
