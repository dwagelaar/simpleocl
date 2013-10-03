/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

/**
 * Abstract super class for all expected elements. Provides methods to add
 * followers.
 */
public abstract class SimpleoclAbstractExpectedElement implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclExpectedElement {
	
	private org.eclipse.emf.ecore.EClass ruleMetaclass;
	
	private java.util.Set<org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclPair<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclExpectedElement, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[]>> followers = new java.util.LinkedHashSet<org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclPair<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclExpectedElement, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[]>>();
	
	public SimpleoclAbstractExpectedElement(org.eclipse.emf.ecore.EClass ruleMetaclass) {
		super();
		this.ruleMetaclass = ruleMetaclass;
	}
	
	public org.eclipse.emf.ecore.EClass getRuleMetaclass() {
		return ruleMetaclass;
	}
	
	public void addFollower(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclExpectedElement follower, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[] path) {
		followers.add(new org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclPair<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclExpectedElement, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[]>(follower, path));
	}
	
	public java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclPair<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclExpectedElement, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[]>> getFollowers() {
		return followers;
	}
	
}
