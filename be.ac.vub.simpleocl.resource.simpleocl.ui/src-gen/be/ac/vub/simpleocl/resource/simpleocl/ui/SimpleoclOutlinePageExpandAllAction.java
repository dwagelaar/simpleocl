/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.ui;

public class SimpleoclOutlinePageExpandAllAction extends be.ac.vub.simpleocl.resource.simpleocl.ui.AbstractSimpleoclOutlinePageAction {
	
	public SimpleoclOutlinePageExpandAllAction(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Expand all", org.eclipse.jface.action.IAction.AS_PUSH_BUTTON);
		initialize("icons/expand_all_icon.gif");
	}
	
	public void runInternal(boolean on) {
		if (on) {
			getTreeViewer().expandAll();
		}
	}
	
	public boolean keepState() {
		return false;
	}
	
}
