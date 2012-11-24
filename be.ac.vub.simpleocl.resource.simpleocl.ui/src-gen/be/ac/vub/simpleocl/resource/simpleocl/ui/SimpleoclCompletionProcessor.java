/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.ui;

public class SimpleoclCompletionProcessor implements org.eclipse.jface.text.contentassist.IContentAssistProcessor {
	
	private be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclResourceProvider resourceProvider;
	private be.ac.vub.simpleocl.resource.simpleocl.ui.ISimpleoclBracketHandlerProvider bracketHandlerProvider;
	
	public SimpleoclCompletionProcessor(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclResourceProvider resourceProvider, be.ac.vub.simpleocl.resource.simpleocl.ui.ISimpleoclBracketHandlerProvider bracketHandlerProvider) {
		this.resourceProvider = resourceProvider;
		this.bracketHandlerProvider = bracketHandlerProvider;
	}
	
	public org.eclipse.jface.text.contentassist.ICompletionProposal[] computeCompletionProposals(org.eclipse.jface.text.ITextViewer viewer, int offset) {
		be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource textResource = resourceProvider.getResource();
		if (textResource == null) {
			return new org.eclipse.jface.text.contentassist.ICompletionProposal[0];
		}
		String content = viewer.getDocument().get();
		be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclCodeCompletionHelper helper = new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclCodeCompletionHelper();
		be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclCompletionProposal[] computedProposals = helper.computeCompletionProposals(textResource, content, offset);
		
		// call completion proposal post processor to allow for customizing the proposals
		be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclProposalPostProcessor proposalPostProcessor = new be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclProposalPostProcessor();
		java.util.List<be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclCompletionProposal> computedProposalList = java.util.Arrays.asList(computedProposals);
		java.util.List<be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclCompletionProposal> extendedProposalList = proposalPostProcessor.process(computedProposalList);
		if (extendedProposalList == null) {
			extendedProposalList = java.util.Collections.emptyList();
		}
		java.util.List<be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclCompletionProposal> finalProposalList = new java.util.ArrayList<be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclCompletionProposal>();
		for (be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclCompletionProposal proposal : extendedProposalList) {
			if (proposal.getMatchesPrefix()) {
				finalProposalList.add(proposal);
			}
		}
		org.eclipse.jface.text.contentassist.ICompletionProposal[] result = new org.eclipse.jface.text.contentassist.ICompletionProposal[finalProposalList.size()];
		int i = 0;
		for (be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclCompletionProposal proposal : finalProposalList) {
			String proposalString = proposal.getInsertString();
			String displayString = proposal.getDisplayString();
			String prefix = proposal.getPrefix();
			org.eclipse.swt.graphics.Image image = proposal.getImage();
			org.eclipse.jface.text.contentassist.IContextInformation info;
			info = new org.eclipse.jface.text.contentassist.ContextInformation(image, proposalString, proposalString);
			int begin = offset - prefix.length();
			int replacementLength = prefix.length();
			// if a closing bracket was automatically inserted right before, we enlarge the
			// replacement length in order to overwrite the bracket.
			be.ac.vub.simpleocl.resource.simpleocl.ui.ISimpleoclBracketHandler bracketHandler = bracketHandlerProvider.getBracketHandler();
			String closingBracket = bracketHandler.getClosingBracket();
			if (bracketHandler.addedClosingBracket() && proposalString.endsWith(closingBracket)) {
				replacementLength += closingBracket.length();
			}
			result[i++] = new org.eclipse.jface.text.contentassist.CompletionProposal(proposalString, begin, replacementLength, proposalString.length(), image, displayString, info, proposalString);
		}
		return result;
	}
	
	public org.eclipse.jface.text.contentassist.IContextInformation[] computeContextInformation(org.eclipse.jface.text.ITextViewer viewer, int offset) {
		return null;
	}
	
	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}
	
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}
	
	public org.eclipse.jface.text.contentassist.IContextInformationValidator getContextInformationValidator() {
		return null;
	}
	
	public String getErrorMessage() {
		return null;
	}
}
