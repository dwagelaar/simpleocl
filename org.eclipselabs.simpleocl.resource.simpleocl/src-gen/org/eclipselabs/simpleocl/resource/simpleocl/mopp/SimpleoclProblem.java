/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

public class SimpleoclProblem implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclProblem {
	
	private String message;
	private org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemType type;
	private org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity severity;
	private java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclQuickFix> quickFixes;
	
	public SimpleoclProblem(String message, org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemType type, org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity severity) {
		this(message, type, severity, java.util.Collections.<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclQuickFix>emptySet());
	}
	
	public SimpleoclProblem(String message, org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemType type, org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity severity, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclQuickFix quickFix) {
		this(message, type, severity, java.util.Collections.singleton(quickFix));
	}
	
	public SimpleoclProblem(String message, org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemType type, org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity severity, java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclQuickFix> quickFixes) {
		super();
		this.message = message;
		this.type = type;
		this.severity = severity;
		this.quickFixes = new java.util.LinkedHashSet<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclQuickFix>();
		this.quickFixes.addAll(quickFixes);
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemType getType() {
		return type;
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity getSeverity() {
		return severity;
	}
	
	public String getMessage() {
		return message;
	}
	
	public java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclQuickFix> getQuickFixes() {
		return quickFixes;
	}
	
}
