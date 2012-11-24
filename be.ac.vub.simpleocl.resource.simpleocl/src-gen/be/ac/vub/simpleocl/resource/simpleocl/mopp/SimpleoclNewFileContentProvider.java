/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

public class SimpleoclNewFileContentProvider {
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclMetaInformation getMetaInformation() {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation();
	}
	
	public String getNewFileContent(String newFileName) {
		return getExampleContent(new org.eclipse.emf.ecore.EClass[] {
			be.ac.vub.simpleocl.SimpleoclPackage.eINSTANCE.getModule(),
		}, getMetaInformation().getClassesWithSyntax(), newFileName);
	}
	
	protected String getExampleContent(org.eclipse.emf.ecore.EClass[] startClasses, org.eclipse.emf.ecore.EClass[] allClassesWithSyntax, String newFileName) {
		String content = "";
		for (org.eclipse.emf.ecore.EClass next : startClasses) {
			content = getExampleContent(next, allClassesWithSyntax, newFileName);
			if (content.trim().length() > 0) {
				break;
			}
		}
		return content;
	}
	
	protected String getExampleContent(org.eclipse.emf.ecore.EClass eClass, org.eclipse.emf.ecore.EClass[] allClassesWithSyntax, String newFileName) {
		// create a minimal model
		org.eclipse.emf.ecore.EObject root = new be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclMinimalModelHelper().getMinimalModel(eClass, allClassesWithSyntax, newFileName);
		if (root == null) {
			// could not create a minimal model. returning an empty document is the best we
			// can do.
			return "";
		}
		// use printer to get text for model
		java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
		be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextPrinter printer = getPrinter(buffer);
		try {
			printer.print(root);
		} catch (java.io.IOException e) {
			new be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclRuntimeUtil().logError("Exception while generating example content.", e);
		}
		return buffer.toString();
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextPrinter getPrinter(java.io.OutputStream outputStream) {
		return getMetaInformation().createPrinter(outputStream, new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclResource());
	}
	
}
