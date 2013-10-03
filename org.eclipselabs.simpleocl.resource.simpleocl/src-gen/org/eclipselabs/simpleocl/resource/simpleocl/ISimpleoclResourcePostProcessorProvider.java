/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl;

/**
 * Implementors of this interface can provide a post-processor for text resources.
 */
public interface ISimpleoclResourcePostProcessorProvider {
	
	/**
	 * Returns the processor that shall be called after text resource are successfully
	 * parsed.
	 */
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclResourcePostProcessor getResourcePostProcessor();
	
}
