/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.ui;

public class SimpleoclOutlinePageActionProvider {
	
	public java.util.List<org.eclipse.jface.action.IAction> getActions(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclOutlinePageTreeViewer treeViewer) {
		// To add custom actions to the outline view, set the
		// 'overrideOutlinePageActionProvider' option to <code>false</code> and modify
		// this method.
		java.util.List<org.eclipse.jface.action.IAction> defaultActions = new java.util.ArrayList<org.eclipse.jface.action.IAction>();
		defaultActions.add(new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclOutlinePageLinkWithEditorAction(treeViewer));
		defaultActions.add(new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclOutlinePageCollapseAllAction(treeViewer));
		defaultActions.add(new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclOutlinePageExpandAllAction(treeViewer));
		defaultActions.add(new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclOutlinePageAutoExpandAction(treeViewer));
		defaultActions.add(new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclOutlinePageLexicalSortingAction(treeViewer));
		defaultActions.add(new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclOutlinePageTypeSortingAction(treeViewer));
		return defaultActions;
	}
	
}
