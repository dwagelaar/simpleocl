/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

public class SimpleoclMetaInformation implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclMetaInformation {
	
	public String getSyntaxName() {
		return "simpleocl";
	}
	
	public String getURI() {
		return "http://soft.vub.ac.be/simpleocl/2011/SimpleOCL";
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextScanner createLexer() {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclAntlrScanner(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclLexer());
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextParser createParser(java.io.InputStream inputStream, String encoding) {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclParser().createInstance(inputStream, encoding);
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextPrinter createPrinter(java.io.OutputStream outputStream, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource resource) {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclPrinter2(outputStream, resource);
	}
	
	public org.eclipse.emf.ecore.EClass[] getClassesWithSyntax() {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclSyntaxCoverageInformationProvider().getClassesWithSyntax();
	}
	
	public org.eclipse.emf.ecore.EClass[] getStartSymbols() {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclSyntaxCoverageInformationProvider().getStartSymbols();
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolverSwitch getReferenceResolverSwitch() {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclReferenceResolverSwitch();
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenResolverFactory getTokenResolverFactory() {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTokenResolverFactory();
	}
	
	public String getPathToCSDefinition() {
		return "be.ac.vub.simpleocl/metamodel/simpleocl.cs";
	}
	
	public String[] getTokenNames() {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclParser(null).getTokenNames();
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenStyle getDefaultTokenStyle(String tokenName) {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTokenStyleInformationProvider().getDefaultTokenStyle(tokenName);
	}
	
	public java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclBracketPair> getBracketPairs() {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclBracketInformationProvider().getBracketPairs();
	}
	
	public org.eclipse.emf.ecore.EClass[] getFoldableClasses() {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclFoldingInformationProvider().getFoldableClasses();
	}
	
	public org.eclipse.emf.ecore.resource.Resource.Factory createResourceFactory() {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclResourceFactory();
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclNewFileContentProvider getNewFileContentProvider() {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclNewFileContentProvider();
	}
	
	public void registerResourceFactory() {
		org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(getSyntaxName(), new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclResourceFactory());
	}
	
	/**
	 * Returns the key of the option that can be used to register a preprocessor that
	 * is used as a pipe when loading resources. This key is language-specific. To
	 * register one preprocessor for multiple resource types, it must be registered
	 * individually using all keys.
	 */
	public String getInputStreamPreprocessorProviderOptionKey() {
		return getSyntaxName() + "_" + "INPUT_STREAM_PREPROCESSOR_PROVIDER";
	}
	
	/**
	 * Returns the key of the option that can be used to register a post-processors
	 * that are invoked after loading resources. This key is language-specific. To
	 * register one post-processor for multiple resource types, it must be registered
	 * individually using all keys.
	 */
	public String getResourcePostProcessorProviderOptionKey() {
		return getSyntaxName() + "_" + "RESOURCE_POSTPROCESSOR_PROVIDER";
	}
	
	public String getLaunchConfigurationType() {
		return "be.ac.vub.simpleocl.resource.simpleocl.ui.launchConfigurationType";
	}
	
}
