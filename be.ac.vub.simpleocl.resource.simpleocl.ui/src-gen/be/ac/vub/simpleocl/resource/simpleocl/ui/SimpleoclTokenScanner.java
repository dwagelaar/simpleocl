/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.ui;

/**
 * An adapter from the Eclipse
 * <code>org.eclipse.jface.text.rules.ITokenScanner</code> interface to the
 * generated lexer.
 */
public class SimpleoclTokenScanner implements org.eclipse.jface.text.rules.ITokenScanner {
	
	private be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextScanner lexer;
	private be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextToken currentToken;
	private int offset;
	private String languageId;
	private org.eclipse.jface.preference.IPreferenceStore store;
	private be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclColorManager colorManager;
	private be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource resource;
	
	/**
	 * 
	 * @param colorManager A manager to obtain color objects
	 */
	public SimpleoclTokenScanner(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource resource, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclColorManager colorManager) {
		this.resource = resource;
		this.colorManager = colorManager;
		this.lexer = new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation().createLexer();
		this.languageId = new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation().getSyntaxName();
		this.store = be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclUIPlugin.getDefault().getPreferenceStore();
	}
	
	public int getTokenLength() {
		return currentToken.getLength();
	}
	
	public int getTokenOffset() {
		return offset + currentToken.getOffset();
	}
	
	public org.eclipse.jface.text.rules.IToken nextToken() {
		be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclDynamicTokenStyler dynamicTokenStyler = new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclDynamicTokenStyler();
		currentToken = lexer.getNextToken();
		if (currentToken == null || !currentToken.canBeUsedForSyntaxHighlighting()) {
			return org.eclipse.jface.text.rules.Token.EOF;
		}
		org.eclipse.jface.text.TextAttribute ta = null;
		String tokenName = currentToken.getName();
		if (tokenName != null) {
			String enableKey = be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.ENABLE);
			boolean enabled = store.getBoolean(enableKey);
			be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenStyle staticStyle = null;
			if (enabled) {
				String colorKey = be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.COLOR);
				org.eclipse.swt.graphics.RGB foregroundRGB = org.eclipse.jface.preference.PreferenceConverter.getColor(store, colorKey);
				org.eclipse.swt.graphics.RGB backgroundRGB = null;
				boolean bold = store.getBoolean(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.BOLD));
				boolean italic = store.getBoolean(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.ITALIC));
				boolean strikethrough = store.getBoolean(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.STRIKETHROUGH));
				boolean underline = store.getBoolean(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.UNDERLINE));
				// now call dynamic token styler to allow to apply modifications to the static
				// style
				staticStyle = new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTokenStyle(convertToIntArray(foregroundRGB), convertToIntArray(backgroundRGB), bold, italic, strikethrough, underline);
			}
			be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenStyle dynamicStyle = dynamicTokenStyler.getDynamicTokenStyle(resource, currentToken, staticStyle);
			if (dynamicStyle != null) {
				int[] foregroundColorArray = dynamicStyle.getColorAsRGB();
				org.eclipse.swt.graphics.Color foregroundColor = colorManager.getColor(new org.eclipse.swt.graphics.RGB(foregroundColorArray[0], foregroundColorArray[1], foregroundColorArray[2]));
				int[] backgroundColorArray = dynamicStyle.getBackgroundColorAsRGB();
				org.eclipse.swt.graphics.Color backgroundColor = null;
				if (backgroundColorArray != null) {
					org.eclipse.swt.graphics.RGB backgroundRGB = new org.eclipse.swt.graphics.RGB(backgroundColorArray[0], backgroundColorArray[1], backgroundColorArray[2]);
					backgroundColor = colorManager.getColor(backgroundRGB);
				}
				int style = org.eclipse.swt.SWT.NORMAL;
				if (dynamicStyle.isBold()) {
					style = style | org.eclipse.swt.SWT.BOLD;
				}
				if (dynamicStyle.isItalic()) {
					style = style | org.eclipse.swt.SWT.ITALIC;
				}
				if (dynamicStyle.isStrikethrough()) {
					style = style | org.eclipse.jface.text.TextAttribute.STRIKETHROUGH;
				}
				if (dynamicStyle.isUnderline()) {
					style = style | org.eclipse.jface.text.TextAttribute.UNDERLINE;
				}
				ta = new org.eclipse.jface.text.TextAttribute(foregroundColor, backgroundColor, style);
			}
		}
		return new org.eclipse.jface.text.rules.Token(ta);
	}
	
	public void setRange(org.eclipse.jface.text.IDocument document, int offset, int length) {
		this.offset = offset;
		try {
			lexer.setText(document.get(offset, length));
		} catch (org.eclipse.jface.text.BadLocationException e) {
			// ignore this error. It might occur during editing when locations are outdated
			// quickly.
		}
	}
	
	public String getTokenText() {
		return currentToken.getText();
	}
	
	public int[] convertToIntArray(org.eclipse.swt.graphics.RGB rgb) {
		if (rgb == null) {
			return null;
		}
		return new int[] {rgb.red, rgb.green, rgb.blue};
	}
	
}
