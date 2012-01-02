/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

public class SimpleoclProblem implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclProblem {
	
	private String message;
	private be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType type;
	private be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity severity;
	private java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix> quickFixes;
	
	public SimpleoclProblem(String message, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType type, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity severity) {
		this(message, type, severity, java.util.Collections.<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix>emptySet());
	}
	
	public SimpleoclProblem(String message, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType type, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity severity, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix quickFix) {
		this(message, type, severity, java.util.Collections.singleton(quickFix));
	}
	
	public SimpleoclProblem(String message, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType type, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity severity, java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix> quickFixes) {
		super();
		this.message = message;
		this.type = type;
		this.severity = severity;
		this.quickFixes = new java.util.LinkedHashSet<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix>();
		this.quickFixes.addAll(quickFixes);
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType getType() {
		return type;
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity getSeverity() {
		return severity;
	}
	
	public String getMessage() {
		return message;
	}
	
	public java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix> getQuickFixes() {
		return quickFixes;
	}
	
}
