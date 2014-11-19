/**
 * <copyright>
 * (C) 2013, 2014 Dennis Wagelaar.
 * </copyright>
 */
package org.eclipselabs.simpleocl.resource.simpleocl.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * A class which can be overridden to customize code completion proposals.
 * 
 * @author <a href="dwagelaar@gmail.com">Dennis Wagelaar</a>
 */
public class SimpleoclProposalPostProcessor {

	private final SyntaxProposalPostProcessor syntaxProposalPostProcessor = new SyntaxProposalPostProcessor();

	/**
	 * Adapts the given list of proposals and returns it.
	 * 
	 * @param proposals
	 *            the list of code completion proposals
	 * @return the adapted list of code completion proposals
	 */
	public List<SimpleoclCompletionProposal> process(
			final List<SimpleoclCompletionProposal> proposals) {
		final List<SyntaxCompletionProposal> convertedProposals = new ArrayList<SyntaxCompletionProposal>();
		for (SimpleoclCompletionProposal proposal : proposals) {
			convertedProposals.add(convertProposal(proposal));
		}
		final List<SyntaxCompletionProposal> additionalProposals = syntaxProposalPostProcessor
				.process(convertedProposals);
		final List<SimpleoclCompletionProposal> newProposals = new ArrayList<SimpleoclCompletionProposal>();
		for (SyntaxCompletionProposal proposal : additionalProposals) {
			newProposals.add(buildProposal(proposal));
		}
		// Only return original proposals if no new proposals are suggested
		return newProposals.isEmpty() ? proposals : newProposals;
	}

	/**
	 * Builds a {@link SyntaxCompletionProposal} from a
	 * {@link SimpleoclCompletionProposal}.
	 * 
	 * @param proposal
	 *            the syntax completion proposal to convert
	 * @return the {@link SyntaxCompletionProposal}
	 */
	private SyntaxCompletionProposal convertProposal(
			final SimpleoclCompletionProposal proposal) {
		final EObject expectedTerminalContainer = proposal
				.getExpectedTerminal() != null ? proposal.getExpectedTerminal()
				.getContainer() : null;
		final SyntaxCompletionProposal result = new SyntaxCompletionProposal(
				proposal.getRoot(), proposal.getContainer(),
				expectedTerminalContainer, proposal.getPrefix(),
				proposal.getInsertString(), null, proposal.getDisplayString());
		return result;
	}

	/**
	 * Builds a {@link SimpleoclCompletionProposal} from a
	 * {@link SyntaxCompletionProposal}.
	 * 
	 * @param proposal
	 *            the syntax completion proposal to convert
	 * @return the {@link SimpleoclCompletionProposal}
	 */
	private SimpleoclCompletionProposal buildProposal(
			final SyntaxCompletionProposal proposal) {
		final String prefix = proposal.getPrefix();
		final StringBuilder insertString = new StringBuilder();
		final PropertyCallType propertyCallType = syntaxProposalPostProcessor.getPropertyCallType(prefix);
		if (propertyCallType != null) {
			insertString.append(propertyCallType.getPrefix());
		}
		insertString.append(proposal.getInsertString());
		final SimpleoclCompletionProposal result = new SimpleoclCompletionProposal(
				null, insertString.toString(), prefix, true, null,
				proposal.getContainer(), proposal.getImage(),
				proposal.getDisplayString());
		result.setRoot(proposal.getRoot());
		return result;
	}

}
