/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.debug;

public interface ISimpleoclDebugEventListener {
	
	/**
	 * Notification that the given event occurred in the while debugging.
	 */
	public void handleMessage(org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message);
}
