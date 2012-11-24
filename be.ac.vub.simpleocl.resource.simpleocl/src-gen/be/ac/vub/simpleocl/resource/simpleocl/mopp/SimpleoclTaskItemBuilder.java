/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

/**
 * The SimpleoclTaskItemBuilder is used to find task items in text documents. The
 * current implementation uses the generated lexer and the TaskItemDetector to
 * detect task items. This class is called by the BuilderAdapter, which runs both
 * this builder and the default builder that is intended to be customized.
 */
public class SimpleoclTaskItemBuilder {
	
	public void build(org.eclipse.core.resources.IFile resource, org.eclipse.emf.ecore.resource.ResourceSet resourceSet, org.eclipse.core.runtime.IProgressMonitor monitor) {
		monitor.setTaskName("Searching for task items");
		new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMarkerHelper().removeAllMarkers(resource, org.eclipse.core.resources.IMarker.TASK);
		if (isInBinFolder(resource)) {
			return;
		}
		java.util.List<be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTaskItem> taskItems = new java.util.ArrayList<be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTaskItem>();
		be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTaskItemDetector taskItemDetector = new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTaskItemDetector();
		try {
			java.io.InputStream inputStream = resource.getContents();
			String content = be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclStreamUtil.getContent(inputStream);
			be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextScanner lexer = new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation().createLexer();
			lexer.setText(content);
			
			be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextToken nextToken = lexer.getNextToken();
			while (nextToken != null) {
				String text = nextToken.getText();
				taskItems.addAll(taskItemDetector.findTaskItems(text, nextToken.getLine(), nextToken.getOffset()));
				nextToken = lexer.getNextToken();
			}
		} catch (java.io.IOException e) {
			be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclPlugin.logError("Exception while searching for task items", e);
		} catch (org.eclipse.core.runtime.CoreException e) {
			be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclPlugin.logError("Exception while searching for task items", e);
		}
		
		for (be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTaskItem taskItem : taskItems) {
			java.util.Map<String, Object> markerAttributes = new java.util.LinkedHashMap<String, Object>();
			markerAttributes.put(org.eclipse.core.resources.IMarker.USER_EDITABLE, false);
			markerAttributes.put(org.eclipse.core.resources.IMarker.DONE, false);
			markerAttributes.put(org.eclipse.core.resources.IMarker.LINE_NUMBER, taskItem.getLine());
			markerAttributes.put(org.eclipse.core.resources.IMarker.CHAR_START, taskItem.getCharStart());
			markerAttributes.put(org.eclipse.core.resources.IMarker.CHAR_END, taskItem.getCharEnd());
			markerAttributes.put(org.eclipse.core.resources.IMarker.MESSAGE, taskItem.getMessage());
			new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMarkerHelper().createMarker(resource, org.eclipse.core.resources.IMarker.TASK, markerAttributes);
		}
	}
	
	public String getBuilderMarkerId() {
		return org.eclipse.core.resources.IMarker.TASK;
	}
	
	public boolean isInBinFolder(org.eclipse.core.resources.IFile resource) {
		org.eclipse.core.resources.IContainer parent = resource.getParent();
		while (parent != null) {
			if ("bin".equals(parent.getName())) {
				return true;
			}
			parent = parent.getParent();
		}
		return false;
	}
	
}
