/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl;

/**
 * Implementors of this interface provide an EMF resource.
 */
public interface ISimpleoclResourceProvider {
	
	/**
	 * Returns the resource.
	 */
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource getResource();
	
}
