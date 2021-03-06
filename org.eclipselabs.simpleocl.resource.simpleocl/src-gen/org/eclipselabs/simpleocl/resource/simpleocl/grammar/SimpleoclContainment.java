/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.grammar;

public class SimpleoclContainment extends org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclTerminal {
	
	private final org.eclipse.emf.ecore.EClass[] allowedTypes;
	
	public SimpleoclContainment(org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclCardinality cardinality, org.eclipse.emf.ecore.EClass[] allowedTypes, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
		this.allowedTypes = allowedTypes;
	}
	
	public org.eclipse.emf.ecore.EClass[] getAllowedTypes() {
		return allowedTypes;
	}
	
	public String toString() {
		String typeRestrictions = null;
		if (allowedTypes != null && allowedTypes.length > 0) {
			typeRestrictions = org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclStringUtil.explode(allowedTypes, ", ", new org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclFunction1<String, org.eclipse.emf.ecore.EClass>() {
				public String execute(org.eclipse.emf.ecore.EClass eClass) {
					return eClass.getName();
				}
			});
		}
		return getFeature().getName() + (typeRestrictions == null ? "" : "[" + typeRestrictions + "]");
	}
	
}
