/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.debug;

/**
 * The DebugProxy allows to communicate between the interpreter, which runs in a
 * separate thread or process and the Eclipse Debug framework (i.e., the
 * DebugTarget class).
 */
public class SimpleoclDebugProxy {
	
	public static final int STARTUP_DELAY = 1000;
	
	private java.io.PrintStream output;
	
	private java.io.BufferedReader reader;
	
	private be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugTarget debugTarget;
	
	private be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugCommunicationHelper communicationHelper = new be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugCommunicationHelper();
	
	public SimpleoclDebugProxy(be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugTarget debugTarget, int requestPort) throws java.net.UnknownHostException, java.io.IOException {
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
		sendCommand(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.RESUME);
	}
	
	public void stepOver() {
		sendCommand(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.STEP_OVER);
	}
	
	public void stepInto() {
		sendCommand(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.STEP_INTO);
	}
	
	public void stepReturn() {
		sendCommand(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.STEP_RETURN);
	}
	
	public void terminate() {
		sendCommand(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.EXIT);
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage getStack() {
		return sendCommandAndRead(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.GET_STACK);
	}
	
	public void addLineBreakpoint(String location, int line) {
		be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message = new be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.ADD_LINE_BREAKPOINT, new String[] {location, Integer.toString(line)});
		communicationHelper.sendEvent(message, output);
	}
	
	public void removeLineBreakpoint(String location, int line) {
		be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message = new be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.REMOVE_LINE_BREAKPOINT, new String[] {location, Integer.toString(line)});
		communicationHelper.sendEvent(message, output);
	}
	
	public org.eclipse.debug.core.model.IVariable[] getStackVariables(String stackFrame) {
		be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage response = sendCommandAndRead(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.GET_FRAME_VARIABLES, new String[] {stackFrame});
		String[] ids = response.getArguments();
		// fetch all variables
		org.eclipse.debug.core.model.IVariable[] variables = getVariables(ids);
		return variables;
	}
	
	public org.eclipse.debug.core.model.IVariable[] getVariables(String... requestedIDs) {
		be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage response = sendCommandAndRead(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.GET_VARIABLES, requestedIDs);
		String[] varStrings = response.getArguments();
		be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugVariable[] variables  = new be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugVariable[varStrings.length];
		int i = 0;
		for (String varString : varStrings) {
			java.util.Map<String, String> properties = be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclStringUtil.convertFromString(varString);
			
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
			org.eclipse.debug.core.model.IValue value = new be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugValue(debugTarget, id, valueString, valueRefType, childVariables);
			
			String variableName = properties.get("!name");
			String variableRefType = properties.get("!type");
			
			be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugVariable variable = new be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugVariable(debugTarget, variableName, value, variableRefType);
			variables[i++] = variable;
		}
		return variables;
	}
	
	private void sendCommand(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes command, String... parameters) {
		be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message = new be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage(command, parameters);
		communicationHelper.sendEvent(message, output);
	}
	
	private be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage sendCommandAndRead(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes command, String... parameters) {
		be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message = new be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage(command, parameters);
		return communicationHelper.sendAndReceive(message, output, reader);
	}
	
}
