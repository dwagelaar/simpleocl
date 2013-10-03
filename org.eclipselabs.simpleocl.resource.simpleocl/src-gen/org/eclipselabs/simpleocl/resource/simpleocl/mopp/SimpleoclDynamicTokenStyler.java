/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

public class SimpleoclDynamicTokenStyler {
	
	/**
	 * This method is called to dynamically style tokens.
	 * 
	 * @param resource the TextResource that contains the token
	 * @param token the token to obtain a style for
	 * @param staticStyle the token style as set in the editor preferences (is
	 * <code>null</code> if syntax highlighting for the token is disabled)
	 */
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenStyle getDynamicTokenStyle(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource resource, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextToken token, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenStyle staticStyle) {
		// The default implementation returns the static style without any changes. To
		// implement dynamic token styling, set the overrideDynamicTokenStyler option to
		// <code>false</code> and customize this method.
		return staticStyle;
	}
	
}
