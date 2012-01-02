/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

/**
 * A basic implementation of the
 * be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolveResult
 * interface that collects mappings in a list.
 * 
 * @param <ReferenceType> the type of the references that can be contained in this
 * result
 */
public class SimpleoclReferenceResolveResult<ReferenceType> implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolveResult<ReferenceType> {
	
	private java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceMapping<ReferenceType>> mappings;
	private String errorMessage;
	private boolean resolveFuzzy;
	private java.util.Set<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix> quickFixes;
	
	public SimpleoclReferenceResolveResult(boolean resolveFuzzy) {
		super();
		this.resolveFuzzy = resolveFuzzy;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix> getQuickFixes() {
		if (quickFixes == null) {
			quickFixes = new java.util.LinkedHashSet<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix>();
		}
		return java.util.Collections.unmodifiableSet(quickFixes);
	}
	
	public void addQuickFix(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix quickFix) {
		if (quickFixes == null) {
			quickFixes = new java.util.LinkedHashSet<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix>();
		}
		quickFixes.add(quickFix);
	}
	
	public java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceMapping<ReferenceType>> getMappings() {
		return mappings;
	}
	
	public boolean wasResolved() {
		return mappings != null;
	}
	
	public boolean wasResolvedMultiple() {
		return mappings != null && mappings.size() > 1;
	}
	
	public boolean wasResolvedUniquely() {
		return mappings != null && mappings.size() == 1;
	}
	
	public void setErrorMessage(String message) {
		errorMessage = message;
	}
	
	public void addMapping(String identifier, ReferenceType target) {
		if (!resolveFuzzy && target == null) {
			throw new IllegalArgumentException("Mapping references to null is only allowed for fuzzy resolution.");
		}
		addMapping(identifier, target, null);
	}
	
	public void addMapping(String identifier, ReferenceType target, String warning) {
		if (mappings == null) {
			mappings = new java.util.ArrayList<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceMapping<ReferenceType>>(1);
		}
		mappings.add(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclElementMapping<ReferenceType>(identifier, target, warning));
		errorMessage = null;
	}
	
	public void addMapping(String identifier, org.eclipse.emf.common.util.URI uri) {
		addMapping(identifier, uri, null);
	}
	
	public void addMapping(String identifier, org.eclipse.emf.common.util.URI uri, String warning) {
		if (mappings == null) {
			mappings = new java.util.ArrayList<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceMapping<ReferenceType>>(1);
		}
		mappings.add(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclURIMapping<ReferenceType>(identifier, uri, warning));
	}
}
