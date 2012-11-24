/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

/**
 * Abstract super class for all expected elements. Provides methods to add
 * followers.
 */
public abstract class SimpleoclAbstractExpectedElement implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclExpectedElement {
	
	private org.eclipse.emf.ecore.EClass ruleMetaclass;
	
	private java.util.Set<be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclPair<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclExpectedElement, be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[]>> followers = new java.util.LinkedHashSet<be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclPair<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclExpectedElement, be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[]>>();
	
	public SimpleoclAbstractExpectedElement(org.eclipse.emf.ecore.EClass ruleMetaclass) {
		super();
		this.ruleMetaclass = ruleMetaclass;
	}
	
	public org.eclipse.emf.ecore.EClass getRuleMetaclass() {
		return ruleMetaclass;
	}
	
	public void addFollower(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclExpectedElement follower, be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[] path) {
		followers.add(new be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclPair<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclExpectedElement, be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[]>(follower, path));
	}
	
	public java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclPair<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclExpectedElement, be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[]>> getFollowers() {
		return followers;
	}
	
}
