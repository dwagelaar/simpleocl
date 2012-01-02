/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.debug;

public class SimpleoclSourceLookupParticipant extends org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant {
	
	public String getSourceName(Object object) throws org.eclipse.core.runtime.CoreException {
		if (object instanceof be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclStackFrame) {
			be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclStackFrame frame = (be.ac.vub.simpleocl.resource.simpleocl.debug.SimpleoclStackFrame) object;
			return frame.getResourceURI();
		}
		return null;
	}
	
}
