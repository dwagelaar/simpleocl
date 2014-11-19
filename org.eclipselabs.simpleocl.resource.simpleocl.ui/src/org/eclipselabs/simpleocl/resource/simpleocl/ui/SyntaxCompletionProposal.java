/**
 * <copyright>
 * (C) 2013, 2014 Dennis Wagelaar.
 * </copyright>
 */
package org.eclipselabs.simpleocl.resource.simpleocl.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

/**
 * General syntax completion proposal class.
 * 
 * @author <a href="dwagelaar@gmail.com">Dennis Wagelaar</a>
 */
public class SyntaxCompletionProposal {

	private final EObject root;
	private final EObject container;
	private final EObject expectedTerminalContainer;
	private final String prefix;
	private final String insertString;
	private final URL imageURL;
	private final String displayString;

	/**
	 * Creates a new {@link SyntaxCompletionProposal}.
	 * 
	 * @param root
	 *            the root AST node
	 * @param container
	 *            the container AST node
	 * @param expectedTerminalContainer
	 *            the container AST node of the expected grammar terminal
	 * @param prefix
	 *            the syntax prefix
	 * @param insertString
	 *            the string that will be inserted on selection
	 * @param imageURL
	 *            the icon image {@link URL}
	 * @param displayString
	 *            the display string to show to the user
	 */
	public SyntaxCompletionProposal(EObject root, EObject container,
			EObject expectedTerminalContainer, String prefix,
			String insertString, URL imageURL, String displayString) {
		this.root = root;
		this.container = container;
		this.expectedTerminalContainer = expectedTerminalContainer;
		this.prefix = prefix;
		this.insertString = insertString;
		this.imageURL = imageURL;
		this.displayString = displayString;
	}

	/**
	 * Returns the root AST node.
	 * 
	 * @return the root
	 */
	public EObject getRoot() {
		return root;
	}

	/**
	 * Returns the container AST node.
	 * 
	 * @return the container
	 */
	public EObject getContainer() {
		return container;
	}

	/**
	 * Returns the container AST node of the expected grammar terminal.
	 * 
	 * @return the expectedTerminalContainer
	 */
	public EObject getExpectedTerminalContainer() {
		return expectedTerminalContainer;
	}

	/**
	 * Returns the syntax prefix.
	 * 
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * Returns the string that will be inserted on selection.
	 * 
	 * @return the insertString
	 */
	public String getInsertString() {
		return insertString;
	}

	/**
	 * Returns the icon image {@link URL}.
	 * 
	 * @return the imageURL
	 */
	public URL getImageURL() {
		return imageURL;
	}

	/**
	 * Returns the {@link #getImageURL()} as an {@link Image}.
	 * 
	 * @return the {@link Image}
	 */
	public Image getImage() {
		Image image = null;
		if (imageURL != null) {
			try {
				final InputStream input = imageURL.openStream();
				try {
					image = new Image(SimpleoclUIPlugin.getDefault()
							.getWorkbench().getDisplay(), input);
				} finally {
					input.close();
				}
			} catch (IOException e) {
				SimpleoclUIPlugin.logError(e.getLocalizedMessage(), e);
			}
		}
		return image;
	}

	/**
	 * Returns the display string to show to the user.
	 * 
	 * @return the displayString
	 */
	public String getDisplayString() {
		return displayString;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "SyntaxCompletionProposal [root=" + root + ", container="
				+ container + ", expectedTerminalContainer="
				+ expectedTerminalContainer + ", prefix=" + prefix
				+ ", insertString=" + insertString + ", imageURL=" + imageURL
				+ ", displayString=" + displayString + "]";
	}

}