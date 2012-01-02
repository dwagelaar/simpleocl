/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl;

public interface ISimpleoclBuilder {
	
	public boolean isBuildingNeeded(org.eclipse.emf.common.util.URI uri);
	
	public org.eclipse.core.runtime.IStatus build(be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclResource resource, org.eclipse.core.runtime.IProgressMonitor monitor);
}
