/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

public class SimpleoclParseResult implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclParseResult {
	
	private org.eclipse.emf.ecore.EObject root;
	private java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclCommand<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource>> commands = new java.util.ArrayList<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclCommand<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource>>();
	
	public SimpleoclParseResult() {
		super();
	}
	
	public void setRoot(org.eclipse.emf.ecore.EObject root) {
		this.root = root;
	}
	
	public org.eclipse.emf.ecore.EObject getRoot() {
		return root;
	}
	
	public java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclCommand<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource>> getPostParseCommands() {
		return commands;
	}
	
}
