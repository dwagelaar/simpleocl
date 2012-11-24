/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.ui;

public class SimpleoclOutlinePageLexicalSortingAction extends be.ac.vub.simpleocl.resource.simpleocl.ui.AbstractSimpleoclOutlinePageAction {
	
	public SimpleoclOutlinePageLexicalSortingAction(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Sort alphabetically", org.eclipse.jface.action.IAction.AS_CHECK_BOX);
		initialize("icons/sort_lexically_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewerComparator().setSortLexically(on);
		getTreeViewer().refresh();
	}
	
}
