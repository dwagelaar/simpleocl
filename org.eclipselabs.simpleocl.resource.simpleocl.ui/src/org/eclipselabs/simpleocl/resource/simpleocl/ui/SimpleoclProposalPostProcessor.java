/**
 * <copyright>
 * (C) 2013, 2014 Dennis Wagelaar.
 * </copyright>
 */
package org.eclipselabs.simpleocl.resource.simpleocl.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.m2m.atl.emftvm.EmftvmFactory;
import org.eclipse.m2m.atl.emftvm.ExecEnv;
import org.eclipse.m2m.atl.emftvm.Feature;
import org.eclipse.m2m.atl.emftvm.InputRuleElement;
import org.eclipse.m2m.atl.emftvm.Module;
import org.eclipse.m2m.atl.emftvm.Operation;
import org.eclipse.m2m.atl.emftvm.Parameter;
import org.eclipse.m2m.atl.emftvm.Rule;
import org.eclipse.m2m.atl.emftvm.RuleMode;
import org.eclipse.m2m.atl.emftvm.provider.EmftvmItemProviderAdapterFactory;
import org.eclipse.m2m.atl.emftvm.provider.OperationItemProvider;
import org.eclipse.m2m.atl.emftvm.util.DefaultModuleResolver;
import org.eclipse.m2m.atl.emftvm.util.ModuleResolver;
import org.eclipse.swt.graphics.Image;
import org.eclipselabs.simpleocl.CollectionType;
import org.eclipselabs.simpleocl.Import;
import org.eclipselabs.simpleocl.ModuleElement;
import org.eclipselabs.simpleocl.OclFeatureDefinition;
import org.eclipselabs.simpleocl.provider.SimpleoclItemProviderAdapterFactory;

/**
 * A class which can be overridden to customize code completion proposals.
 * 
 * @author <a href="dwagelaar@gmail.com">Dennis Wagelaar</a>
 */
public class SimpleoclProposalPostProcessor {
	
	public static final List<String> COLLECTION_OPS = Arrays.asList(
			"any(e | <expr>)",
			"append(object)",
			"at(index)",
			"asBag()",
			"asOrderedSet()",
			"asSequence()",
			"asSet()",
			"collect(e | <expr>",
			"count(object)",
			"excludes(object)",
			"excludesAll(collection)",
			"excluding(object)",
			"excludingAll(collection)",
			"exists(e | <expr>)",
			"exists(e, e2 | <expr)",
			"first()",
			"flatten()",
			"forAll(e | <expr>)",
			"forAll(e, e2 | <expr>)",
			"includes(object)",
			"includesAll(collection)",
			"including(object)",
			"including(object, index)",
			"includingAll(collection)",
			"includingAll(collection, index)",
			"includingRange(first, last)",
			"indexOf(object)",
			"insertAt(index, object)",
			"intersection(setOrBag)",
			"isEmpty()",
			"isUnique(e | <expr>)",
			"iterate(e; acc : <type> = <expr> | <expr>)",
			"last()",
			"lastIndexOf(object)",
			"max()",
			"min()",
			"notEmpty()",
			"one(e | <expr>)",
			"prepend(object)",
			"product(collection)",
			"reject(e | <expr>)",
			"reverse()",
			"select(e | <expr>)",
			"size()",
			"sortedBy(e | <expr>)",
			"subtract(set)",
			"subSequence(fromIndex, toIndex)",
			"sum()",
			"symmetricDifference(set)",
			"union(collection)"
		);
	
	/**
	 * General syntax completion proposal class.
	 * 
	 * @author <a href="dwagelaar@gmail.com">Dennis Wagelaar</a>
	 */
	public static class SyntaxCompletionProposal {
		
		private final EObject root;
		private final String prefix;
		private final String insertString;
		private final URL imageURL;
		
		/**
		 * Creates a new {@link SyntaxCompletionProposal}.
		 * 
		 * @param root
		 *            the root AST node
		 * @param prefix
		 *            the syntax prefix
		 * @param insertString
		 *            the string that will be inserted on selection
		 * @param imageURL
		 *            the icon image {@link URL}
		 */
		public SyntaxCompletionProposal(EObject root, String prefix, String insertString, URL imageURL) {
			this.root = root;
			this.prefix = prefix;
			this.insertString = insertString;
			this.imageURL = imageURL;
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
						image = new Image(SimpleoclUIPlugin.getDefault().getWorkbench().getDisplay(), input);
					} finally {
						input.close();
					}
				} catch (IOException e) {
					SimpleoclUIPlugin.logError(e.getLocalizedMessage(), e);
				}
			}
			return image;
		}
		
	}

	protected final SimpleoclItemProviderAdapterFactory simpleoclItemProviderFactory = new SimpleoclItemProviderAdapterFactory();
	protected final EmftvmItemProviderAdapterFactory emftvmItemProviderFactory = new EmftvmItemProviderAdapterFactory();
	
	/**
	 * Adapts the given list of proposals and returns it.
	 * 
	 * @param proposals
	 *            the list of code completion proposals
	 * @return the adapted list of code completion proposals
	 */
	public List<SimpleoclCompletionProposal> process(final List<SimpleoclCompletionProposal> proposals) {
		final List<SyntaxCompletionProposal> additionalProposals = new ArrayList<SyntaxCompletionProposal>();
		for (SimpleoclCompletionProposal proposal : proposals) {
			final String prefix = proposal.getPrefix();
			if (prefix.equals(".") || prefix.equals("->") || prefix.equals("::")) {
				additionalProposals.addAll(getModuleProposals(proposal.getPrefix(), proposal.getRoot()));
				additionalProposals.addAll(getEMFTVMProposals(proposal.getPrefix(), proposal.getRoot()));
				break;
			}
		}
		final List<SimpleoclCompletionProposal> newProposals = new ArrayList<SimpleoclCompletionProposal>();
		for (SyntaxCompletionProposal proposal : additionalProposals) {
			newProposals.add(buildProposal(proposal));
		}
		newProposals.addAll(proposals);
		return newProposals;
	}

	/**
	 * Returns the SimpleOCL {@link org.eclipselabs.simpleocl.Module} syntax completion proposals for the given prefix and root.
	 * 
	 * @param prefix
	 *            the syntax prefix
	 * @param root
	 *            the root AST node
	 * @return the syntax completion proposals
	 */
	protected List<SyntaxCompletionProposal> getModuleProposals(final String prefix, final EObject root) {
		if (!(root instanceof org.eclipselabs.simpleocl.Module)) {
			return Collections.<SyntaxCompletionProposal> emptyList();
		}
		final boolean isCollectionOp = prefix.equals("->");
		final boolean isStatic = prefix.equals("::");
		final SortedSet<String> features = new TreeSet<String>();
		final Map<String, URL> images = new HashMap<String, URL>();
		for (ModuleElement element : ((org.eclipselabs.simpleocl.Module)root).getElements()) {
			if (element instanceof OclFeatureDefinition) {
				OclFeatureDefinition fd = (OclFeatureDefinition) element;
				if (isStatic != fd.isStatic()) {
					continue;
				}
				if (fd.getContext_() != null && isCollectionOp != fd.getContext_().getContext_() instanceof CollectionType) {
					continue;
				}
				StringBuilder sb = new StringBuilder(fd.getFeature().getName());
				if (fd.getFeature() instanceof org.eclipselabs.simpleocl.Operation) {
					sb.append('(');
					boolean first = true;
					for (org.eclipselabs.simpleocl.Parameter par : ((org.eclipselabs.simpleocl.Operation) fd.getFeature()).getParameters()) {
						if (!first) {
							sb.append(", ");
						}
						sb.append(par.getVarName());
						first = false;
					}
					sb.append(')');
				}
				final ItemProviderAdapter itemProviderAdapter = (ItemProviderAdapter) simpleoclItemProviderFactory.createAdapter(fd.getFeature());
				images.put(sb.toString(), (URL) itemProviderAdapter.getImage(fd.getFeature()));
				features.add(sb.toString());
			}
		}
		return buildProposals(prefix, root, features, images);
	}

	/**
	 * Returns the EMFTVM syntax completion proposals for the given prefix and root.
	 * 
	 * @param prefix
	 *            the syntax prefix
	 * @param root
	 *            the root AST node
	 * @return the syntax completion proposals
	 */
	protected List<SyntaxCompletionProposal> getEMFTVMProposals(final String prefix, final EObject root) {
		final boolean isCollectionOp = prefix.equals("->");
		final boolean isStatic = prefix.equals("::");
		final SortedSet<String> features = new TreeSet<String>();
		final Map<String, URL> images = new HashMap<String, URL>();
		final ExecEnv env = EmftvmFactory.eINSTANCE.createExecEnv();
		if (root instanceof org.eclipselabs.simpleocl.Module) {
			loadImports((org.eclipselabs.simpleocl.Module) root, env);
		}
		for (Module module : env.getModules().values()) {
			for (Feature f : module.getFeatures()) {
				if (!f.getName().matches("^\\w+")) {
					continue;
				}
				if (isStatic != f.isStatic()) {
					continue;
				}
				EClassifier context = f.getEContext();
				if (context != null && isCollectionOp != Collection.class.isAssignableFrom(context.getInstanceClass())) {
					continue;
				}
				StringBuilder sb = new StringBuilder(f.getName());
				if (f instanceof Operation) {
					sb.append('(');
					boolean first = true;
					for (Parameter par : ((Operation) f).getParameters()) {
						if (!first) {
							sb.append(", ");
						}
						sb.append(par.getName());
						first = false;
					}
					sb.append(')');
				}
				ItemProviderAdapter itemProviderAdapter = (ItemProviderAdapter) emftvmItemProviderFactory.createAdapter(f);
				images.put(sb.toString(), (URL) itemProviderAdapter.getImage(f));
				features.add(sb.toString());
			}
			if (isStatic) {
				for (Rule r : module.getRules()) {
					if (r.getMode() != RuleMode.MANUAL) {
						continue;
					}
					StringBuilder sb = new StringBuilder(r.getName());
					sb.append('(');
					boolean first = true;
					for (InputRuleElement re : r.getInputElements()) {
						if (!first) {
							sb.append(", ");
						}
						sb.append(re.getName());
						first = false;
					}
					sb.append(')');
					ItemProviderAdapter itemProviderAdapter = (ItemProviderAdapter) emftvmItemProviderFactory.createAdapter(r);
					images.put(sb.toString(), (URL) itemProviderAdapter.getImage(r));
					features.add(sb.toString());
				}
			}
		}
		if (isCollectionOp) {
			features.addAll(COLLECTION_OPS);
			ItemProviderAdapter itemProviderAdapter = new OperationItemProvider(emftvmItemProviderFactory);
			for (String f : COLLECTION_OPS) {
				images.put(f, (URL) itemProviderAdapter.getImage((Object) null));
			}
		}
		return buildProposals(prefix, root, features, images);
	}
	
	/**
	 * Loads the imports for the given root into the given {@link ExecEnv}.
	 * 
	 * @param root
	 *            the root AST node
	 * @param env
	 *            the {@link ExecEnv}
	 * @return the list of imported modules
	 */
	protected void loadImports(final org.eclipselabs.simpleocl.Module root, final ExecEnv env) {
		String uriPrefix = root.eResource().getURI().toPlatformString(true);
		uriPrefix = uriPrefix.substring(0, uriPrefix.lastIndexOf('/') + 1);
		final ModuleResolver mr = new DefaultModuleResolver(uriPrefix, new ResourceSetImpl());
		for (Import imp : root.getImports()) {
			try {
				env.loadModule(mr, imp.getName(), false);
			} catch (Exception e) {
				SimpleoclUIPlugin.logError(e.getLocalizedMessage(), e);
			}
		}
	}

	/**
	 * Builds syntax completion proposals for the given features.
	 * 
	 * @param prefix
	 *            the syntax prefix
	 * @param root
	 *            the root AST node
	 * @param features
	 *            the syntax completion features (insert strings)
	 * @param images
	 *            the icon image {@link URL}s
	 * @return the syntax completion proposal
	 */
	protected List<SyntaxCompletionProposal> buildProposals(final String prefix, final EObject root, final Collection<String> features, final Map<String, URL> images) {
		final List<SyntaxCompletionProposal> proposals = new ArrayList<SyntaxCompletionProposal>();
		for (String f : features) {
			proposals.add(new SyntaxCompletionProposal(root, prefix, f, images.get(f)));
		}
		return proposals;
	}

	/**
	 * Builds a {@link SimpleoclCompletionProposal} from a {@link SyntaxCompletionProposal}.
	 * 
	 * @param proposal
	 *            the syntax completion proposal to convert
	 * @return the {@link SimpleoclCompletionProposal}
	 */
	private SimpleoclCompletionProposal buildProposal(final SyntaxCompletionProposal proposal) {
		final SimpleoclCompletionProposal result = new SimpleoclCompletionProposal(null, proposal.getPrefix() + proposal.getInsertString(), proposal.getPrefix(), true, null, null,
				proposal.getImage(), proposal.getInsertString());
		result.setRoot(proposal.getRoot());
		return result;
	}

}
