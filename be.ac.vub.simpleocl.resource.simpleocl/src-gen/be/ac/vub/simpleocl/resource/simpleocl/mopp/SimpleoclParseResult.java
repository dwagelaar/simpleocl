/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

public class SimpleoclParseResult implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclParseResult {
	
	private org.eclipse.emf.ecore.EObject root;
	private java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclCommand<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource>> commands = new java.util.ArrayList<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclCommand<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource>>();
	
	public SimpleoclParseResult() {
		super();
	}
	
	public void setRoot(org.eclipse.emf.ecore.EObject root) {
		this.root = root;
	}
	
	public org.eclipse.emf.ecore.EObject getRoot() {
		return root;
	}
	
	public java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclCommand<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource>> getPostParseCommands() {
		return commands;
	}
	
}
