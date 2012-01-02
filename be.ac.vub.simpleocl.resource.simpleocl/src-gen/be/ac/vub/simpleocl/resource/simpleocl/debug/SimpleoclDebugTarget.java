/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.debug;

public class SimpleoclDebugTarget extends be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugElement implements org.eclipse.debug.core.model.IDebugTarget, be.ac.vub.simpleocl.resource.simpleocl.debug.ISimpleoclDebugEventListener {
	
	/**
	 * associated system process
	 */
	private be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugProcess process;
	
	private java.net.Socket eventSocket;
	
	private java.io.BufferedReader eventReader;
	
	/**
	 * containing launch object
	 */
	private org.eclipse.debug.core.ILaunch launch;
	
	private org.eclipse.debug.core.model.IThread[] threads;
	
	private be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugThread thread;
	
	private boolean terminated = false;
	
	/**
	 * event dispatch job
	 */
	private DebugEventDispatcher eventDispatch;
	/**
	 * event listeners
	 */
	private java.util.List<be.ac.vub.simpleocl.resource.simpleocl.debug.ISimpleoclDebugEventListener> eventListeners = new java.util.ArrayList<be.ac.vub.simpleocl.resource.simpleocl.debug.ISimpleoclDebugEventListener>();
	
	private be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugProxy debugProxy;
	
	private class DebugEventDispatcher extends org.eclipse.core.runtime.jobs.Job {
		
		private be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugCommunicationHelper communicationHelper = new be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugCommunicationHelper();
		
		public DebugEventDispatcher() {
			super(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation().getSyntaxName() + " Event Dispatch");
			setSystem(true);
		}
		
		protected org.eclipse.core.runtime.IStatus run(org.eclipse.core.runtime.IProgressMonitor monitor) {
			while (!isTerminated()) {
				be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message = communicationHelper.receive(eventReader);
				if (message != null) {
					notifyListeners(message);
				} else {
					terminated();
					break;
				}
			}
			return org.eclipse.core.runtime.Status.OK_STATUS;
		}
		
		private void notifyListeners(be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message) {
			Object[] listeners = eventListeners.toArray();
			for (int i = 0; i < listeners.length; i++) {
				((be.ac.vub.simpleocl.resource.simpleocl.debug.ISimpleoclDebugEventListener) listeners[i]).handleMessage(message);
			}
		}
	}
	
	public SimpleoclDebugTarget(be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugProcess process, org.eclipse.debug.core.ILaunch launch, int requestPort, int eventPort) {
		super(launch.getDebugTarget());
		this.process = process;
		this.launch = launch;
		this.thread = new be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugThread(this);
		this.threads = new org.eclipse.debug.core.model.IThread[] {this.thread};
		
		// initialize debug proxy
		try {
			this.debugProxy = new be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugProxy(this, requestPort);
		} catch (java.net.UnknownHostException e) {
			e.printStackTrace();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		
		// initialize asynchronous event dispatcher
		try {
			// creating event client socket (trying to connect)...
			this.eventSocket = new java.net.Socket("localhost", eventPort);
			// creating event client socket - done (connected).
			this.eventReader = new java.io.BufferedReader(new java.io.InputStreamReader(this.eventSocket.getInputStream()));
		} catch (java.net.ConnectException e) {
			throw new RuntimeException("Can't create socket: " + e.getMessage());
		} catch (java.net.UnknownHostException e) {
			throw new RuntimeException("Can't create socket: " + e.getMessage());
		} catch (java.io.IOException e) {
			throw new RuntimeException("Can't create socket: " + e.getMessage());
		}
		
		this.eventDispatch = new DebugEventDispatcher();
		this.eventDispatch.schedule();
		
		addEventListener(this);
		addEventListener(this.thread);
		addEventListener(this.process);
		
		org.eclipse.debug.core.DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(this);
	}
	
	/**
	 * Registers the given event listener. The listener will be notified of events in
	 * the program being interpreted. Has no effect if the listener is already
	 * registered.
	 * 
	 * @param listener event listener
	 */
	public void addEventListener(be.ac.vub.simpleocl.resource.simpleocl.debug.ISimpleoclDebugEventListener listener) {
		if (!eventListeners.contains(listener)) {
			eventListeners.add(listener);
		}
	}
	
	/**
	 * Deregisters the given event listener. Has no effect if the listener is not
	 * currently registered.
	 * 
	 * @param listener event listener
	 */
	public void removeEventListener(be.ac.vub.simpleocl.resource.simpleocl.debug.ISimpleoclDebugEventListener listener) {
		eventListeners.remove(listener);
	}
	
	public String getName() throws org.eclipse.debug.core.DebugException {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation().getSyntaxName() + " model";
	}
	
	public org.eclipse.debug.core.model.IDebugTarget getDebugTarget() {
		return this;
	}
	
	public org.eclipse.debug.core.model.IProcess getProcess() {
		return process;
	}
	
	public org.eclipse.debug.core.model.IThread[] getThreads() throws org.eclipse.debug.core.DebugException {
		return threads;
	}
	
	public boolean hasThreads() throws org.eclipse.debug.core.DebugException {
		return true;
	}
	
	public boolean supportsBreakpoint(org.eclipse.debug.core.model.IBreakpoint breakpoint) {
		return breakpoint.getModelIdentifier().equals(getModelIdentifier());
	}
	
	public org.eclipse.debug.core.ILaunch getLaunch() {
		return launch;
	}
	
	public boolean canTerminate() {
		return getProcess().canTerminate();
	}
	
	public boolean isTerminated() {
		return terminated || getProcess().isTerminated();
	}
	
	public void terminate() throws org.eclipse.debug.core.DebugException {
		getThread().terminate();
	}
	
	private synchronized void terminated() {
		terminated = true;
		threads = new org.eclipse.debug.core.model.IThread[0];
		fireTerminateEvent();
		removeEventListener(this);
		removeEventListener(this.thread);
		removeEventListener(this.process);
		org.eclipse.debug.core.DebugPlugin debugPlugin = org.eclipse.debug.core.DebugPlugin.getDefault();
		try {
			debugPlugin.getBreakpointManager().removeBreakpointListener(this);
		} catch (NullPointerException npe) {
			// this is a workaround for Eclipse bug 352315 (see
			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=352315)
		}
		getDebugProxy().terminate();
	}
	
	public boolean canResume() {
		return getThread().canResume();
	}
	
	public boolean canSuspend() {
		return getThread().canSuspend() && !isTerminated();
	}
	
	public boolean isSuspended() {
		return getThread().isSuspended();
	}
	
	public void resume() throws org.eclipse.debug.core.DebugException {
		getThread().resume();
	}
	
	public org.eclipse.debug.core.model.IThread getThread() {
		return thread;
	}
	
	public void suspend() throws org.eclipse.debug.core.DebugException {
		getThread().suspend();
	}
	
	public void breakpointAdded(org.eclipse.debug.core.model.IBreakpoint breakpoint) {
		if (supportsBreakpoint(breakpoint)) {
			try {
				if ((breakpoint.isEnabled() && getBreakpointManager().isEnabled()) || !breakpoint.isRegistered()) {
					be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclLineBreakpoint lineBreakpoint = (be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclLineBreakpoint) breakpoint;
					lineBreakpoint.install(this);
				}
			} catch (org.eclipse.core.runtime.CoreException e) {
			}
		}
	}
	
	public void breakpointRemoved(org.eclipse.debug.core.model.IBreakpoint breakpoint, org.eclipse.core.resources.IMarkerDelta delta) {
		if (supportsBreakpoint(breakpoint)) {
			try {
				if ((breakpoint.isEnabled() && getBreakpointManager().isEnabled()) || !breakpoint.isRegistered()) {
					be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclLineBreakpoint lineBreakpoint = (be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclLineBreakpoint) breakpoint;
					lineBreakpoint.remove(this);
				}
			} catch (org.eclipse.core.runtime.CoreException e) {
			}
		}
	}
	
	public void breakpointChanged(org.eclipse.debug.core.model.IBreakpoint breakpoint, org.eclipse.core.resources.IMarkerDelta delta) {
	}
	
	public boolean canDisconnect() {
		// not supported
		return false;
	}
	
	public void disconnect() throws org.eclipse.debug.core.DebugException {
	}
	
	public boolean isDisconnected() {
		return false;
	}
	
	public org.eclipse.debug.core.model.IMemoryBlock getMemoryBlock(long startAddress, long length) throws org.eclipse.debug.core.DebugException {
		return null;
	}
	
	public boolean supportsStorageRetrieval() {
		return false;
	}
	
	/**
	 * Notification we have connected to the VM and it has started. Resume the VM.
	 */
	private void started() {
		fireCreationEvent();
		installDeferredBreakpoints();
		try {
			resume();
		} catch (org.eclipse.debug.core.DebugException e) {
		}
	}
	
	/**
	 * Install breakpoints that are already registered with the breakpoint manager.
	 */
	private void installDeferredBreakpoints() {
		org.eclipse.debug.core.model.IBreakpoint[] breakpoints = getBreakpointManager().getBreakpoints(getModelIdentifier());
		for (int i = 0; i < breakpoints.length; i++) {
			breakpointAdded(breakpoints[i]);
		}
	}
	
	public void handleMessage(be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message) {
		try {
			if (message.hasType(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.STARTED)) {
				started();
			} else if (message.hasType(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.SUSPENDED)) {
				suspend();
			} else if (message.hasType(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.TERMINATED)) {
				terminated();
			} else if (message.hasType(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.RESUMED)) {
				// ignore, this event is handled by the debug thread
			} else {
				System.out.println("ERROR in " + this.getClass().getName() + ".handleMessage(): unknown event " + message);
			}
		} catch (org.eclipse.debug.core.DebugException e) {
			e.printStackTrace();
		}
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugProxy getDebugProxy() {
		return this.debugProxy;
	}
	
}
