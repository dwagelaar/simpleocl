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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclResource;
import org.eclipselabs.simpleocl.util.LocationResource;

/**
 * Adds {@link LocationResource} methods to {@link SimplegtResource}.
 * @author <a href="mailto:dennis.wagelaar@vub.ac.be">Dennis Wagelaar</a>
 *
 */
public class SimpleoclLocationResource extends SimpleoclResource implements
		LocationResource {

	/**
	 * Creates a new {@link SimpleoclLocationResource}.
	 */
	public SimpleoclLocationResource() {
		super();
	}

	/**
	 * Creates a new {@link SimpleoclLocationResource}.
	 * @param uri the resource URI
	 */
	public SimpleoclLocationResource(URI uri) {
		super(uri);
	}

	/* (non-Javadoc)
	 * @see be.ac.vub.simplegt.util.LocationResource#getCharEnd(org.eclipse.emf.ecore.EObject)
	 */
	public int getCharEnd(final EObject element) {
		return getLocationMap().getCharEnd(element);
	}

	/* (non-Javadoc)
	 * @see be.ac.vub.simplegt.util.LocationResource#getCharStart(org.eclipse.emf.ecore.EObject)
	 */
	public int getCharStart(final EObject element) {
		return getLocationMap().getCharStart(element);
	}

	/* (non-Javadoc)
	 * @see be.ac.vub.simplegt.util.LocationResource#getColumn(org.eclipse.emf.ecore.EObject)
	 */
	public int getColumn(EObject element) {
		return getLocationMap().getColumn(element);
	}

	/* (non-Javadoc)
	 * @see be.ac.vub.simplegt.util.LocationResource#getLine(org.eclipse.emf.ecore.EObject)
	 */
	public int getLine(EObject element) {
		return getLocationMap().getLine(element);
	}

}
