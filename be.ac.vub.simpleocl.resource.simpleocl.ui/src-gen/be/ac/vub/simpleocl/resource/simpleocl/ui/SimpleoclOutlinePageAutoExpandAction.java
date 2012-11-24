/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.ui;

public class SimpleoclOutlinePageAutoExpandAction extends be.ac.vub.simpleocl.resource.simpleocl.ui.AbstractSimpleoclOutlinePageAction {
	
	public SimpleoclOutlinePageAutoExpandAction(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Auto expand", org.eclipse.jface.action.IAction.AS_CHECK_BOX);
		initialize("icons/auto_expand_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewer().setAutoExpand(on);
		getTreeViewer().refresh();
	}
	
}
