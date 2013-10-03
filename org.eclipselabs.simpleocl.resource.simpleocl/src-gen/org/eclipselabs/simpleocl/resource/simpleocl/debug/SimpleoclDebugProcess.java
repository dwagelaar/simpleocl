/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.debug;

public class SimpleoclDebugProcess extends org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugElement implements org.eclipse.debug.core.model.IProcess, org.eclipselabs.simpleocl.resource.simpleocl.debug.ISimpleoclDebugEventListener {
	
	private org.eclipse.debug.core.ILaunch launch;
	
	private boolean terminated = false;
	
	public SimpleoclDebugProcess(org.eclipse.debug.core.ILaunch launch) {
		super(launch.getDebugTarget());
		this.launch = launch;
	}
	
	public boolean canTerminate() {
		return !terminated;
	}
	
	public boolean isTerminated() {
		return terminated;
	}
	
	public void terminate() throws org.eclipse.debug.core.DebugException {
		terminated = true;
	}
	
	public String getLabel() {
		return null;
	}
	
	public org.eclipse.debug.core.ILaunch getLaunch() {
		return launch;
	}
	
	public org.eclipse.debug.core.model.IStreamsProxy getStreamsProxy() {
		return null;
	}
	
	public void setAttribute(String key, String value) {
	}
	
	public String getAttribute(String key) {
		return null;
	}
	
	public int getExitValue() throws org.eclipse.debug.core.DebugException {
		return 0;
	}
	
	public void handleMessage(org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message) {
		if (message.hasType(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.TERMINATED)) {
			terminated = true;
		} else {
			// ignore other events
		}
	}
	
}
