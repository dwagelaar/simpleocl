/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.util;

/**
 * Class SimpleoclTextResourceUtil can be used to perform common tasks on text
 * resources, such as loading and saving resources, as well as, checking them for
 * errors. This class is deprecated and has been replaced by
 * org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.
 */
public class SimpleoclTextResourceUtil {
	
	/**
	 * Use
	 * org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResou
	 * rce() instead.
	 */
	@Deprecated	
	public static org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclResource getResource(org.eclipse.core.resources.IFile file) {
		return new org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclEclipseProxy().getResource(file);
	}
	
	/**
	 * Use
	 * org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResou
	 * rce() instead.
	 */
	@Deprecated	
	public static org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclResource getResource(java.io.File file, java.util.Map<?,?> options) {
		return org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResource(file, options);
	}
	
	/**
	 * Use
	 * org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResou
	 * rce() instead.
	 */
	@Deprecated	
	public static org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclResource getResource(org.eclipse.emf.common.util.URI uri) {
		return org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResource(uri);
	}
	
	/**
	 * Use
	 * org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResou
	 * rce() instead.
	 */
	@Deprecated	
	public static org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclResource getResource(org.eclipse.emf.common.util.URI uri, java.util.Map<?,?> options) {
		return org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResource(uri, options);
	}
	
}
