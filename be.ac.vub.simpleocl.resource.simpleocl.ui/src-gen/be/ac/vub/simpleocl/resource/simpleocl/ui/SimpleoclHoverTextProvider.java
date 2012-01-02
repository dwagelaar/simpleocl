/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.ui;

public class SimpleoclHoverTextProvider implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclHoverTextProvider {
	
	private be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclDefaultHoverTextProvider defaultProvider = new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclDefaultHoverTextProvider();
	
	public String getHoverText(org.eclipse.emf.ecore.EObject container, org.eclipse.emf.ecore.EObject referencedObject) {
		// Set option overrideHoverTextProvider to false and customize this method to
		// obtain custom hover texts.
		return defaultProvider.getHoverText(referencedObject);
	}
	
	public String getHoverText(org.eclipse.emf.ecore.EObject object) {
		// Set option overrideHoverTextProvider to false and customize this method to
		// obtain custom hover texts.
		return defaultProvider.getHoverText(object);
	}
	
}
