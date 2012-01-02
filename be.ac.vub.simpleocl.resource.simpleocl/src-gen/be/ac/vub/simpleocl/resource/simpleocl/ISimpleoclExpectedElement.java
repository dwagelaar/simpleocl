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
	 * Returns the names of all tokens that are expected at the given position
	 */
	public java.util.Set<String> getTokenNames();
	
	/**
	 * Returns the metaclass of the rule that contains the expected element
	 */
	public org.eclipse.emf.ecore.EClass getRuleMetaclass();
	
	/**
	 * Adds an element that is a valid follower for this element
	 */
	public void addFollower(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclExpectedElement follower, org.eclipse.emf.ecore.EStructuralFeature[] path);
	
	/**
	 * Returns all valid followers for this element
	 */
	public java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclPair<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclExpectedElement, org.eclipse.emf.ecore.EStructuralFeature[]>> getFollowers();
	
}
