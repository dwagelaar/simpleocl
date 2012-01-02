/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl;

public interface ISimpleoclProblem {
	public String getMessage();
	public be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity getSeverity();
	public be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType getType();
	public java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix> getQuickFixes();
}
