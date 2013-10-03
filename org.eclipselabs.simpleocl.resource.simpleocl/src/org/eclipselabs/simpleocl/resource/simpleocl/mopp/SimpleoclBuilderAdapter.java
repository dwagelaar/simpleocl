/*******************************************************************************
 * Copyright (c) 2011 Dennis Wagelaar, Vrije Universiteit Brussel.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dennis Wagelaar, Vrije Universiteit Brussel
 *******************************************************************************/
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclBuilder;
import org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemType;
import org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclPlugin;
import org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclResource;

/**
 * Adapter class for {@link ISimpleoclBuilder}.
 * @author Dennis Wagelaar <dennis.wagelaar@vub.ac.be>
 */
public class SimpleoclBuilderAdapter extends org.eclipse.core.resources.IncrementalProjectBuilder {
	
	/**
	 * the ID of the default, generated builder
	 */
	public final static String BUILDER_ID = "org.eclipselabs.simpleocl.resource.simpleocl.builder";

	/**
	 * The file extension for SimpleOCL files.
	 */
	public final static String FILE_EXT = "simpleocl";
	
	/**
	 * the builder object
	 */
	protected ISimpleoclBuilder builder;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder#build(int, java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public IProject[] build(int kind, @SuppressWarnings("rawtypes") Map args, final IProgressMonitor monitor) throws CoreException {
		if (builder==null) builder = new SimpleoclBuilder();
		return build(kind, args, monitor, builder, getProject());
	}

	/**
	 * Builds <code>project</code> using <code>builder</code>.
	 * @param kind
	 * @param args
	 * @param monitor
	 * @param builder
	 * @param project
	 * @return <code>null</code>
	 * @throws CoreException
	 */
	public IProject[] build(int kind, Map<?,?> args, final IProgressMonitor monitor, final ISimpleoclBuilder builder, IProject project) throws CoreException {
		org.eclipse.core.resources.IResourceDelta delta = getDelta(project);
		if (delta == null) {
			return null;
		}
		delta.accept(new org.eclipse.core.resources.IResourceDeltaVisitor() {
			public boolean visit(org.eclipse.core.resources.IResourceDelta delta) throws org.eclipse.core.runtime.CoreException {
				if (delta.getKind() == IResourceDelta.REMOVED) {
					return false;
				}
				return buildResource(monitor, delta.getResource(), builder);
			}
		});
		return null;
	}
	
	/**
	 * Builds <code>resource</code> using <code>builder</code>, if necessary.
	 * @param monitor the progress monitor
	 * @param resource the resource to build
	 * @param builder the builder
	 * @return <code>true</code> if the resource's members should be visited; <code>false</code> if they should be skipped
	 */
	public boolean buildResource(final IProgressMonitor monitor, final IResource resource,
			final ISimpleoclBuilder builder) {
		if (monitor.isCanceled()) {
			return false;
		}
		if (resource instanceof IFile && FILE_EXT.equals(resource.getFileExtension())) {
			final URI uri = URI.createPlatformResourceURI(resource.getFullPath().toString(), true);
			if (builder.isBuildingNeeded(uri)) {
				final SimpleoclResource customResource = (SimpleoclResource) new ResourceSetImpl().getResource(uri, true);
				customResource.unmark(SimpleoclEProblemType.BUILDER_ERROR);
				final IStatus status = builder.build(customResource, monitor);
				if (!status.isOK()) {
					SimpleoclPlugin.getDefault().getLog().log(status);
				}
			}
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder#clean(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void clean(final IProgressMonitor monitor) throws CoreException {
		super.clean(monitor);
		clean(monitor, getProject());
	}

	/**
	 * Cleans <code>project</code>.
	 * @param monitor
	 * @param project
	 */
	public void clean(final IProgressMonitor monitor, final IProject project) {
		try {
			project.accept(new IResourceVisitor() {
				public boolean visit(final IResource resource) throws CoreException {
					if (monitor.isCanceled()) {
						return false;
					}
					if (resource instanceof IFile && FILE_EXT.equals(resource.getFileExtension())) {
						final String name = resource.getName();
						final IFile emftvmFile = resource.getParent().getFile(
								new Path(name.substring(0, name.lastIndexOf('.')).concat(".emftvm")));
						if (emftvmFile.exists()) {
							emftvmFile.delete(true, monitor);
						}
						return false;
					}
					return true;
				}
			});
		} catch (CoreException e) {
			SimpleoclPlugin.getDefault().getLog().log(e.getStatus());
		}
	}
	
}
