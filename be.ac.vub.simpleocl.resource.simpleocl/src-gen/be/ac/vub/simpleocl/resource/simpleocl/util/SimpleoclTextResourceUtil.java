/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.util;

/**
 * Class SimpleoclTextResourceUtil can be used to perform common tasks on text
 * resources, such as loading and saving resources, as well as, checking them for
 * errors. This class is deprecated and has been replaced by
 * be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.
 */
public class SimpleoclTextResourceUtil {
	
	/**
	 * Use
	 * be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResource()
	 * instead.
	 */
	@Deprecated	
	public static be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclResource getResource(org.eclipse.core.resources.IFile file) {
		return new be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclEclipseProxy().getResource(file);
	}
	
	/**
	 * Use
	 * be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResource()
	 * instead.
	 */
	@Deprecated	
	public static be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclResource getResource(java.io.File file, java.util.Map<?,?> options) {
		return be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResource(file, options);
	}
	
	/**
	 * Use
	 * be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResource()
	 * instead.
	 */
	@Deprecated	
	public static be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclResource getResource(org.eclipse.emf.common.util.URI uri) {
		return be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResource(uri);
	}
	
	/**
	 * Use
	 * be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResource()
	 * instead.
	 */
	@Deprecated	
	public static be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclResource getResource(org.eclipse.emf.common.util.URI uri, java.util.Map<?,?> options) {
		return be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclResourceUtil.getResource(uri, options);
	}
	
}
