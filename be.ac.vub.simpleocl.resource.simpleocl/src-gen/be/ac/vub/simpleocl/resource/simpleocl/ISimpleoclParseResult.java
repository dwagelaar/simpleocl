/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl;

/**
 * An interface used to access the result of parsing a document.
 */
public interface ISimpleoclParseResult {
	
	/**
	 * Returns the root object of the document.
	 */
	public org.eclipse.emf.ecore.EObject getRoot();
	
	/**
	 * Returns a list of commands that must be executed after parsing the document.
	 */
	public java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclCommand<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource>> getPostParseCommands();
	
}
