/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

public class SimpleoclResourceFactory implements org.eclipse.emf.ecore.resource.Resource.Factory {
	
	public SimpleoclResourceFactory() {
		super();
	}
	
	public org.eclipse.emf.ecore.resource.Resource createResource(org.eclipse.emf.common.util.URI uri) {
		return new SimpleoclLocationResource(uri);
	}
	
}
