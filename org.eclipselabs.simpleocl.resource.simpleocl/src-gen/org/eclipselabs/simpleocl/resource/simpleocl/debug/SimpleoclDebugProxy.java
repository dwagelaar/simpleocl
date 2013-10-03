/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.debug;

/**
 * The DebugProxy allows to communicate between the interpreter, which runs in a
 * separate thread or process and the Eclipse Debug framework (i.e., the
 * DebugTarget class).
 */
public class SimpleoclDebugProxy {
	
	public static final int STARTUP_DELAY = 1000;
	
	private java.io.PrintStream output;
	
	private java.io.BufferedReader reader;
	
	private org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugTarget debugTarget;
	
	private org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugCommunicationHelper communicationHelper = new org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugCommunicationHelper();
	
	public SimpleoclDebugProxy(org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugTarget debugTarget, int requestPort) throws java.net.UnknownHostException, java.io.IOException {
		this.debugTarget = debugTarget;
		// give interpreter a chance to start
		try {
			Thread.sleep(STARTUP_DELAY);
		} catch (InterruptedException e) {
		}
		startSocket(requestPort);
	}
	
	private void startSocket(int requestPort) throws java.net.UnknownHostException, java.io.IOException {
		// creating client proxy socket (trying to connect)...
		java.net.Socket client = new java.net.Socket("localhost", requestPort);
		// creating client proxy socket - done. (connected)
		try {
			java.io.BufferedInputStream input = new java.io.BufferedInputStream(client.getInputStream());
			reader = new java.io.BufferedReader(new java.io.InputStreamReader(input));
		} catch (java.io.IOException e) {
			System.out.println(e);
		}
		try {
			output = new java.io.PrintStream(client.getOutputStream());
		} catch (java.io.IOException e) {
			System.out.println(e);
		}
	}
	
	public void resume() {
		sendCommand(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.RESUME);
	}
	
	public void stepOver() {
		sendCommand(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.STEP_OVER);
	}
	
	public void stepInto() {
		sendCommand(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.STEP_INTO);
	}
	
	public void stepReturn() {
		sendCommand(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.STEP_RETURN);
	}
	
	public void terminate() {
		sendCommand(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.EXIT);
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage getStack() {
		return sendCommandAndRead(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.GET_STACK);
	}
	
	public void addLineBreakpoint(String location, int line) {
		org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message = new org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.ADD_LINE_BREAKPOINT, new String[] {location, Integer.toString(line)});
		communicationHelper.sendEvent(message, output);
	}
	
	public void removeLineBreakpoint(String location, int line) {
		org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message = new org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.REMOVE_LINE_BREAKPOINT, new String[] {location, Integer.toString(line)});
		communicationHelper.sendEvent(message, output);
	}
	
	public org.eclipse.debug.core.model.IVariable[] getStackVariables(String stackFrame) {
		org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage response = sendCommandAndRead(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.GET_FRAME_VARIABLES, new String[] {stackFrame});
		String[] ids = response.getArguments();
		// fetch all variables
		org.eclipse.debug.core.model.IVariable[] variables = getVariables(ids);
		return variables;
	}
	
	public org.eclipse.debug.core.model.IVariable[] getVariables(String... requestedIDs) {
		org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage response = sendCommandAndRead(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.GET_VARIABLES, requestedIDs);
		String[] varStrings = response.getArguments();
		org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugVariable[] variables  = new org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugVariable[varStrings.length];
		int i = 0;
		for (String varString : varStrings) {
			java.util.Map<String, String> properties = org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclStringUtil.convertFromString(varString);
			
			// convert varString to variables and values
			String valueString = properties.get("!valueString");
			String valueRefType = "valueRefType";
			java.util.Map<String, Long> childVariables = new java.util.TreeMap<String, Long>(new java.util.Comparator<String>() {
				public int compare(String s1, String s2) {
					return s1.compareToIgnoreCase(s2);
				}
			});
			for (String property : properties.keySet()) {
				// ignore special properties - they are not children
				if (property.startsWith("!")) {
					continue;
				}
				childVariables.put(property, Long.parseLong(properties.get(property)));
			}
			String id = properties.get("!id");
			org.eclipse.debug.core.model.IValue value = new org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugValue(debugTarget, id, valueString, valueRefType, childVariables);
			
			String variableName = properties.get("!name");
			String variableRefType = properties.get("!type");
			
			org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugVariable variable = new org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugVariable(debugTarget, variableName, value, variableRefType);
			variables[i++] = variable;
		}
		return variables;
	}
	
	private void sendCommand(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes command, String... parameters) {
		org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message = new org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage(command, parameters);
		communicationHelper.sendEvent(message, output);
	}
	
	private org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage sendCommandAndRead(org.eclipselabs.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes command, String... parameters) {
		org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message = new org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage(command, parameters);
		return communicationHelper.sendAndReceive(message, output, reader);
	}
	
}
