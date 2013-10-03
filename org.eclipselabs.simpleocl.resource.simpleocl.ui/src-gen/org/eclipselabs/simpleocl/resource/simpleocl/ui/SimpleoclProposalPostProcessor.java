/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.ui;

/**
 * A class which can be overridden to customize code completion proposals.
 */
public class SimpleoclProposalPostProcessor {
	
	public java.util.List<org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclCompletionProposal> process(java.util.List<org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclCompletionProposal> proposals) {
		// the default implementation does returns the proposals as they are
		return proposals;
	}
	
}
