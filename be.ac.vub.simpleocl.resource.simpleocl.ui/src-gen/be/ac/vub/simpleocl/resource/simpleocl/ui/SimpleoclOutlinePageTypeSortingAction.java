/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.ui;

public class SimpleoclOutlinePageTypeSortingAction extends be.ac.vub.simpleocl.resource.simpleocl.ui.AbstractSimpleoclOutlinePageAction {
	
	public SimpleoclOutlinePageTypeSortingAction(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Group types", org.eclipse.jface.action.IAction.AS_CHECK_BOX);
		initialize("icons/group_types_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewerComparator().setGroupTypes(on);
		getTreeViewer().refresh();
	}
	
}
