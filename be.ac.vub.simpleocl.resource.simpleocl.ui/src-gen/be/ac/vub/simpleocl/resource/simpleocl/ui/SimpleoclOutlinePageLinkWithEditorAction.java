/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.ui;

public class SimpleoclOutlinePageLinkWithEditorAction extends be.ac.vub.simpleocl.resource.simpleocl.ui.AbstractSimpleoclOutlinePageAction {
	
	public SimpleoclOutlinePageLinkWithEditorAction(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Link with Editor", org.eclipse.jface.action.IAction.AS_CHECK_BOX);
		initialize("icons/link_with_editor_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewer().setLinkWithEditor(on);
	}
	
}
