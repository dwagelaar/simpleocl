/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl;

/**
 * Implementors of this interface provide an EMF resource.
 */
public interface ISimpleoclResourceProvider {
	
	/**
	 * Returns the resource.
	 */
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource getResource();
	
}
