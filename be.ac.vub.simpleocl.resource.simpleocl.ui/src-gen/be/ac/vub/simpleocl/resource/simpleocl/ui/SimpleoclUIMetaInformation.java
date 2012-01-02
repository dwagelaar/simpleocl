/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.ui;

public class SimpleoclUIMetaInformation extends be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation {
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclHoverTextProvider getHoverTextProvider() {
		return new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclHoverTextProvider();
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclImageProvider getImageProvider() {
		return be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclImageProvider.INSTANCE;
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclColorManager createColorManager() {
		return new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclColorManager();
	}
	
	/**
	 * @deprecated this method is only provided to preserve API compatibility. Use
	 * createTokenScanner(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource
	 * , be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclColorManager) instead.
	 */
	public be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclTokenScanner createTokenScanner(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclColorManager colorManager) {
		return createTokenScanner(null, colorManager);
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclTokenScanner createTokenScanner(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource resource, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclColorManager colorManager) {
		return new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclTokenScanner(resource, colorManager);
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclCodeCompletionHelper createCodeCompletionHelper() {
		return new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclCodeCompletionHelper();
	}
	
}
