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
	private java.util.List<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextToken> nextTokens;
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
		be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclUIPlugin plugin = be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclUIPlugin.getDefault();
		if (plugin != null) {
			this.store = plugin.getPreferenceStore();
		}
		this.nextTokens = new java.util.ArrayList<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextToken>();
	}
	
	public int getTokenLength() {
		return currentToken.getLength();
	}
	
	public int getTokenOffset() {
		return offset + currentToken.getOffset();
	}
	
	public org.eclipse.jface.text.rules.IToken nextToken() {
		boolean isOriginalToken = true;
		if (!nextTokens.isEmpty()) {
			currentToken = nextTokens.remove(0);
			isOriginalToken = false;
		} else {
			currentToken = lexer.getNextToken();
		}
		if (currentToken == null || !currentToken.canBeUsedForSyntaxHighlighting()) {
			return org.eclipse.jface.text.rules.Token.EOF;
		}
		
		if (isOriginalToken) {
			splitCurrentToken();
		}
		
		org.eclipse.jface.text.TextAttribute textAttribute = null;
		String tokenName = currentToken.getName();
		if (tokenName != null) {
			be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenStyle staticStyle = getStaticTokenStyle();
			// now call dynamic token styler to allow to apply modifications to the static
			// style
			be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenStyle dynamicStyle = getDynamicTokenStyle(staticStyle);
			if (dynamicStyle != null) {
				textAttribute = getTextAttribute(dynamicStyle);
			}
		}
		
		return new org.eclipse.jface.text.rules.Token(textAttribute);
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
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenStyle getStaticTokenStyle() {
		be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenStyle staticStyle = null;
		String tokenName = currentToken.getName();
		String enableKey = be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.ENABLE);
		boolean enabled = store.getBoolean(enableKey);
		if (enabled) {
			String colorKey = be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.COLOR);
			org.eclipse.swt.graphics.RGB foregroundRGB = org.eclipse.jface.preference.PreferenceConverter.getColor(store, colorKey);
			org.eclipse.swt.graphics.RGB backgroundRGB = null;
			boolean bold = store.getBoolean(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.BOLD));
			boolean italic = store.getBoolean(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.ITALIC));
			boolean strikethrough = store.getBoolean(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.STRIKETHROUGH));
			boolean underline = store.getBoolean(be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, be.ac.vub.simpleocl.resource.simpleocl.ui.SimpleoclSyntaxColoringHelper.StyleProperty.UNDERLINE));
			staticStyle = new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTokenStyle(convertToIntArray(foregroundRGB), convertToIntArray(backgroundRGB), bold, italic, strikethrough, underline);
		}
		return staticStyle;
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenStyle getDynamicTokenStyle(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenStyle staticStyle) {
		be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclDynamicTokenStyler dynamicTokenStyler = new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclDynamicTokenStyler();
		be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenStyle dynamicStyle = dynamicTokenStyler.getDynamicTokenStyle(resource, currentToken, staticStyle);
		return dynamicStyle;
	}
	
	public org.eclipse.jface.text.TextAttribute getTextAttribute(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTokenStyle tokeStyle) {
		int[] foregroundColorArray = tokeStyle.getColorAsRGB();
		org.eclipse.swt.graphics.Color foregroundColor = null;
		if (colorManager != null) {
			foregroundColor = colorManager.getColor(new org.eclipse.swt.graphics.RGB(foregroundColorArray[0], foregroundColorArray[1], foregroundColorArray[2]));
		}
		int[] backgroundColorArray = tokeStyle.getBackgroundColorAsRGB();
		org.eclipse.swt.graphics.Color backgroundColor = null;
		if (backgroundColorArray != null) {
			org.eclipse.swt.graphics.RGB backgroundRGB = new org.eclipse.swt.graphics.RGB(backgroundColorArray[0], backgroundColorArray[1], backgroundColorArray[2]);
			if (colorManager != null) {
				backgroundColor = colorManager.getColor(backgroundRGB);
			}
		}
		int style = org.eclipse.swt.SWT.NORMAL;
		if (tokeStyle.isBold()) {
			style = style | org.eclipse.swt.SWT.BOLD;
		}
		if (tokeStyle.isItalic()) {
			style = style | org.eclipse.swt.SWT.ITALIC;
		}
		if (tokeStyle.isStrikethrough()) {
			style = style | org.eclipse.jface.text.TextAttribute.STRIKETHROUGH;
		}
		if (tokeStyle.isUnderline()) {
			style = style | org.eclipse.jface.text.TextAttribute.UNDERLINE;
		}
		return new org.eclipse.jface.text.TextAttribute(foregroundColor, backgroundColor, style);
	}
	
	/**
	 * Tries to split the current token if it contains task items.
	 */
	public void splitCurrentToken() {
		final String text = currentToken.getText();
		final String name = currentToken.getName();
		final int line = currentToken.getLine();
		final int charStart = currentToken.getOffset();
		final int column = currentToken.getColumn();
		
		java.util.List<be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTaskItem> taskItems = new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTaskItemDetector().findTaskItems(text, line, charStart);
		
		// this is the offset for the next token to be added
		int offset = charStart;
		int itemBeginRelative;
		java.util.List<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextToken> newItems = new java.util.ArrayList<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextToken>();
		for (be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTaskItem taskItem : taskItems) {
			int itemBegin = taskItem.getCharStart();
			int itemLine = taskItem.getLine();
			int itemColumn = 0;
			
			itemBeginRelative = itemBegin - charStart;
			// create token before task item (TODO if required)
			String textBefore = text.substring(offset - charStart, itemBeginRelative);
			int textBeforeLength = textBefore.length();
			newItems.add(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTextToken(name, textBefore, offset, textBeforeLength, line, column, true));
			
			// create token for the task item itself
			offset = offset + textBeforeLength;
			String itemText = taskItem.getKeyword();
			int itemTextLength = itemText.length();
			newItems.add(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTextToken(be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTokenStyleInformationProvider.TASK_ITEM_TOKEN_NAME, itemText, offset, itemTextLength, itemLine, itemColumn, true));
			
			offset = offset + itemTextLength;
		}
		
		if (!taskItems.isEmpty()) {
			// create token after last task item (TODO if required)
			String textAfter = text.substring(offset - charStart);
			newItems.add(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTextToken(name, textAfter, offset, textAfter.length(), line, column, true));
		}
		
		if (!newItems.isEmpty()) {
			// replace tokens
			currentToken = newItems.remove(0);
			nextTokens = newItems;
		}
		
	}
}
