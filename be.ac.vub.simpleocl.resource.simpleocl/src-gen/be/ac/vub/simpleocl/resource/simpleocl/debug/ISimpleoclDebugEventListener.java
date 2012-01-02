/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.debug;

public interface ISimpleoclDebugEventListener {
	
	/**
	 * Notification that the given event occurred in the while debugging.
	 */
	public void handleMessage(be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message);
}
