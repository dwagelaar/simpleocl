/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

public class SimpleoclMetaInformation implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclMetaInformation {
	
	public String getSyntaxName() {
		return "simpleocl";
	}
	
	public String getURI() {
		return "http://eclipselabs.org/simpleocl/2013/SimpleOCL";
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextScanner createLexer() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclAntlrScanner(new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclLexer());
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextParser createParser(java.io.InputStream inputStream, String encoding) {
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclParser().createInstance(inputStream, encoding);
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextPrinter createPrinter(java.io.OutputStream outputStream, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource resource) {
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclPrinter2(outputStream, resource);
	}
	
	public org.eclipse.emf.ecore.EClass[] getClassesWithSyntax() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclSyntaxCoverageInformationProvider().getClassesWithSyntax();
	}
	
	public org.eclipse.emf.ecore.EClass[] getStartSymbols() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclSyntaxCoverageInformationProvider().getStartSymbols();
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclReferenceResolverSwitch getReferenceResolverSwitch() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclReferenceResolverSwitch();
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolverFactory getTokenResolverFactory() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTokenResolverFactory();
	}
	
	public String getPathToCSDefinition() {
		return "org.eclipselabs.simpleocl/metamodel/simpleocl.cs";
	}
	
	public String[] getTokenNames() {
		return org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclParser.tokenNames;
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenStyle getDefaultTokenStyle(String tokenName) {
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTokenStyleInformationProvider().getDefaultTokenStyle(tokenName);
	}
	
	public java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclBracketPair> getBracketPairs() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclBracketInformationProvider().getBracketPairs();
	}
	
	public org.eclipse.emf.ecore.EClass[] getFoldableClasses() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclFoldingInformationProvider().getFoldableClasses();
	}
	
	public org.eclipse.emf.ecore.resource.Resource.Factory createResourceFactory() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclResourceFactory();
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclNewFileContentProvider getNewFileContentProvider() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclNewFileContentProvider();
	}
	
	public void registerResourceFactory() {
		org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(getSyntaxName(), new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclResourceFactory());
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
		return "org.eclipselabs.simpleocl.resource.simpleocl.ui.launchConfigurationType";
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclNameProvider createNameProvider() {
		return new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultNameProvider();
	}
	
	public String[] getSyntaxHighlightableTokenNames() {
		org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclAntlrTokenHelper tokenHelper = new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclAntlrTokenHelper();
		java.util.List<String> highlightableTokens = new java.util.ArrayList<String>();
		String[] parserTokenNames = getTokenNames();
		for (int i = 0; i < parserTokenNames.length; i++) {
			// If ANTLR is used we need to normalize the token names
			if (!tokenHelper.canBeUsedForSyntaxHighlighting(i)) {
				continue;
			}
			String tokenName = tokenHelper.getTokenName(parserTokenNames, i);
			if (tokenName == null) {
				continue;
			}
			highlightableTokens.add(tokenName);
		}
		highlightableTokens.add(org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTokenStyleInformationProvider.TASK_ITEM_TOKEN_NAME);
		return highlightableTokens.toArray(new String[highlightableTokens.size()]);
	}
	
}
