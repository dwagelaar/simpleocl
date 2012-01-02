/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.debug;

/**
 * DebugMessages are exchanged between the debug server (the Eclipse debug
 * framework) and the debug client (a running process or interpreter). To exchange
 * messages they are serialized and sent over sockets.
 */
public class SimpleoclDebugMessage {
	
	private static final char DELIMITER = ':';
	private be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes messageType;
	private String[] arguments;
	
	public SimpleoclDebugMessage(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes messageType, String[] arguments) {
		super();
		this.messageType = messageType;
		this.arguments = arguments;
	}
	
	public SimpleoclDebugMessage(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes messageType, java.util.List<String> arguments) {
		super();
		this.messageType = messageType;
		this.arguments = new String[arguments.size()];
		for (int i = 0; i < arguments.size(); i++) {
			this.arguments[i] = arguments.get(i);
		}
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes getMessageType() {
		return messageType;
	}
	
	public String[] getArguments() {
		return arguments;
	}
	
	public String serialize() {
		java.util.List<String> parts = new java.util.ArrayList<String>();
		parts.add(messageType.name());
		for (String argument : arguments) {
			parts.add(argument);
		}
		return be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclStringUtil.encode(DELIMITER, parts);
	}
	
	public static SimpleoclDebugMessage deserialize(String response) {
		java.util.List<String> parts = be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclStringUtil.decode(response, DELIMITER);
		String messageType = parts.get(0);
		String[] arguments = new String[parts.size() - 1];
		for (int i = 1; i < parts.size(); i++) {
			arguments[i - 1] = parts.get(i);
		}
		be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes type = be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes.valueOf(messageType);
		SimpleoclDebugMessage message = new SimpleoclDebugMessage(type, arguments);
		return message;
	}
	
	public boolean hasType(be.ac.vub.simpleocl.resource.simpleocl.debug.ESimpleoclDebugMessageTypes type) {
		return this.messageType == type;
	}
	
	public String getArgument(int index) {
		return getArguments()[index];
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + "[" + messageType.name() + ": " + be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclStringUtil.explode(arguments, ", ") + "]";
	}
	
}
