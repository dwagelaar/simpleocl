/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.ui;

/**
 * A class used to initialize default preference values.
 */
public class SimpleoclPreferenceInitializer extends org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer {
	
	public void initializeDefaultPreferences() {
		
		initializeDefaultSyntaxHighlighting();
		initializeDefaultBrackets();
		
		org.eclipse.jface.preference.IPreferenceStore store = org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclUIPlugin.getDefault().getPreferenceStore();
		// Set default value for matching brackets
		store.setDefault(org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclPreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR, "192,192,192");
		store.setDefault(org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclPreferenceConstants.EDITOR_MATCHING_BRACKETS_CHECKBOX, true);
		
	}
	
	private void initializeDefaultBrackets() {
		org.eclipse.jface.preference.IPreferenceStore store = org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclUIPlugin.getDefault().getPreferenceStore();
		initializeDefaultBrackets(store, new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation());
	}
	
	public void initializeDefaultSyntaxHighlighting() {
		org.eclipse.jface.preference.IPreferenceStore store = org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclUIPlugin.getDefault().getPreferenceStore();
		initializeDefaultSyntaxHighlighting(store, new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation());
	}
	
	private void initializeDefaultBrackets(org.eclipse.jface.preference.IPreferenceStore store, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclMetaInformation metaInformation) {
		String languageId = metaInformation.getSyntaxName();
		// set default brackets for ITextResource bracket set
		org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclBracketSet bracketSet = new org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclBracketSet(null, null);
		final java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclBracketPair> bracketPairs = metaInformation.getBracketPairs();
		if (bracketPairs != null) {
			for (org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclBracketPair bracketPair : bracketPairs) {
				bracketSet.addBracketPair(bracketPair.getOpeningBracket(), bracketPair.getClosingBracket(), bracketPair.isClosingEnabledInside());
			}
		}
		store.setDefault(languageId + org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclPreferenceConstants.EDITOR_BRACKETS_SUFFIX, bracketSet.getBracketString());
	}
	
	private void initializeDefaultSyntaxHighlighting(org.eclipse.jface.preference.IPreferenceStore store, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation metaInformation) {
		String languageId = metaInformation.getSyntaxName();
		String[] tokenNames = metaInformation.getSyntaxHighlightableTokenNames();
		if (tokenNames == null) {
			return;
		}
		for (int i = 0; i < tokenNames.length; i++) {
			String tokenName = tokenNames[i];
			org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenStyle style = metaInformation.getDefaultTokenStyle(tokenName);
			if (style != null) {
				String color = getColorString(style.getColorAsRGB());
				setProperties(store, languageId, tokenName, color, style.isBold(), true, style.isItalic(), style.isStrikethrough(), style.isUnderline());
			} else {
				setProperties(store, languageId, tokenName, "0,0,0", false, false, false, false, false);
			}
		}
	}
	
	private void setProperties(org.eclipse.jface.preference.IPreferenceStore store, String languageID, String tokenName, String color, boolean bold, boolean enable, boolean italic, boolean strikethrough, boolean underline) {
		store.setDefault(org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.BOLD), bold);
		store.setDefault(org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.COLOR), color);
		store.setDefault(org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.ENABLE), enable);
		store.setDefault(org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.ITALIC), italic);
		store.setDefault(org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.STRIKETHROUGH), strikethrough);
		store.setDefault(org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.eclipselabs.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.UNDERLINE), underline);
	}
	
	private String getColorString(int[] colorAsRGB) {
		if (colorAsRGB == null) {
			return "0,0,0";
		}
		if (colorAsRGB.length != 3) {
			return "0,0,0";
		}
		return colorAsRGB[0] + "," +colorAsRGB[1] + ","+ colorAsRGB[2];
	}
}
