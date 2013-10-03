/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl;

public interface ISimpleoclProblem {
	public String getMessage();
	public org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity getSeverity();
	public org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemType getType();
	public java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclQuickFix> getQuickFixes();
}
