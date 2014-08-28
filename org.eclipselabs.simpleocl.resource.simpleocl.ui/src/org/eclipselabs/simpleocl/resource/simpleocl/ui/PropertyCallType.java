/*******************************************************************************
 * Copyright (c) 2014 Dennis Wagelaar.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dennis Wagelaar - initial API and
 *         implementation and/or initial documentation
 *******************************************************************************/
package org.eclipselabs.simpleocl.resource.simpleocl.ui;

import org.eclipselabs.simpleocl.PropertyCall;

/**
 * Specifies the kind of {@link PropertyCall} for syntax completion.
 * 
 * @author <a href="dwagelaar@gmail.com">Dennis Wagelaar</a>
 */
public enum PropertyCallType {
	
	/** Regular '.'-style {@link PropertyCall}. */		
	REGULAR("."),
	
	/** Static '::'-style {@link PropertyCall}. */		
	STATIC("::"),
	
	/** Collection operation '->'-style {@link PropertyCall}. */		
	COLLECTION("->");

	private final String prefix;
	
	private PropertyCallType(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * Returns the enum literal's prefix string.
	 * 
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	
}
