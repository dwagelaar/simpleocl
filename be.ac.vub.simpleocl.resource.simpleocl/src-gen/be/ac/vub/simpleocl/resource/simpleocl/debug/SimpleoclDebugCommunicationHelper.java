/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.debug;

public class SimpleoclDebugCommunicationHelper {
	
	public void sendEvent(be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message, java.io.PrintStream stream) {
		synchronized (stream) {
			stream.println(message.serialize());
		}
	}
	
	/**
	 * Sends a message using the given stream and waits for an answer.
	 * 
	 * @param messageType the type of message to send
	 * @param stream the stream to send the message to
	 * @param reader the reader to obtain the answer from
	 * @param parameters additional parameter to send
	 * 
	 * @return the answer that is received
	 */
	public be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage sendAndReceive(be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage message, java.io.PrintStream stream, java.io.BufferedReader reader) {
		synchronized (stream) {
			sendEvent(message, stream);
			be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage response = receive(reader);
			return response;
		}
	}
	
	/**
	 * Receives a message from the given reader. This method block until a message has
	 * arrived.
	 * 
	 * @param reader the read to obtain the message from
	 * 
	 * @return the received message
	 */
	public be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage receive(java.io.BufferedReader reader) {
		try {
			String response = reader.readLine();
			if (response == null) {
				return null;
			}
			be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage receivedMessage = be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclDebugMessage.deserialize(response);
			return receivedMessage;
		} catch (java.io.IOException e) {
			return null;
		}
	}
	
}
