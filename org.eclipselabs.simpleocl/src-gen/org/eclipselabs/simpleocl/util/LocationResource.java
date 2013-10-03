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
package org.eclipselabs.simpleocl.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Provides parsing location metadata for {@link EObject}s.
 * @author <a href="mailto:dennis.wagelaar@vub.ac.be">Dennis Wagelaar</a>
 */
public interface LocationResource extends Resource {

	/**
	 * Returns the line where the given element starts.
	 */
	public int getLine(EObject element);

	/**
	 * Returns the column where the given element starts.
	 */
	public int getColumn(EObject element);

	/**
	 * Returns the character position where the given element starts.
	 */
	public int getCharStart(EObject element);

	/**
	 * Returns the character position where the given element ends.
	 */
	public int getCharEnd(EObject element);

}
