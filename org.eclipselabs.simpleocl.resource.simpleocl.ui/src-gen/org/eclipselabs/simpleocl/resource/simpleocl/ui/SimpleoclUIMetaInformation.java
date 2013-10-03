/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.ui;

public class SimpleoclUIMetaInformation extends org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation {
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclHoverTextProvider getHoverTextProvider() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclHoverTextProvider();
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclImageProvider getImageProvider() {
		return org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclImageProvider.INSTANCE;
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclColorManager createColorManager() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclColorManager();
	}
	
	/**
	 * @deprecated this method is only provided to preserve API compatibility. Use
	 * createTokenScanner(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextRe
	 * source, org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclColorManager)
	 * instead.
	 */
	public org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclTokenScanner createTokenScanner(org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclColorManager colorManager) {
		return createTokenScanner(null, colorManager);
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclTokenScanner createTokenScanner(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource resource, org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclColorManager colorManager) {
		return new org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclTokenScanner(resource, colorManager);
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclCodeCompletionHelper createCodeCompletionHelper() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclCodeCompletionHelper();
	}
	
}
