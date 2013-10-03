/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl;

/**
 * A simple interface for commands that can be executed and that return
 * information about the success of their execution.
 */
public interface ISimpleoclCommand<ContextType> {
	
	public boolean execute(ContextType context);
}
