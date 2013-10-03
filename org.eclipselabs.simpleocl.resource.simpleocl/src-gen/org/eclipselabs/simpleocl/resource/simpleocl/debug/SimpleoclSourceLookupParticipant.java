/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.debug;

public class SimpleoclSourceLookupParticipant extends org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant {
	
	public String getSourceName(Object object) throws org.eclipse.core.runtime.CoreException {
		if (object instanceof org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclStackFrame) {
			org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclStackFrame frame = (org.eclipselabs.simpleocl.resource.simpleocl.debug.SimpleoclStackFrame) object;
			return frame.getResourceURI();
		}
		return null;
	}
	
}
