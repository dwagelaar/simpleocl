/**
 * <copyright>
 * (C) 2013, 2014 Dennis Wagelaar.
 * </copyright>
 */
package org.eclipselabs.simpleocl.resource.simpleocl.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.m2m.atl.emftvm.EmftvmFactory;
import org.eclipse.m2m.atl.emftvm.ExecEnv;
import org.eclipse.m2m.atl.emftvm.Feature;
import org.eclipse.m2m.atl.emftvm.InputRuleElement;
import org.eclipse.m2m.atl.emftvm.Metamodel;
import org.eclipse.m2m.atl.emftvm.Module;
import org.eclipse.m2m.atl.emftvm.Operation;
import org.eclipse.m2m.atl.emftvm.Parameter;
import org.eclipse.m2m.atl.emftvm.Rule;
import org.eclipse.m2m.atl.emftvm.RuleMode;
import org.eclipse.m2m.atl.emftvm.provider.EmftvmItemProviderAdapterFactory;
import org.eclipse.m2m.atl.emftvm.provider.OperationItemProvider;
import org.eclipse.m2m.atl.emftvm.util.DefaultModuleResolver;
import org.eclipse.m2m.atl.emftvm.util.EMFTVMUtil;
import org.eclipse.m2m.atl.emftvm.util.ModuleResolver;
import org.eclipse.m2m.atl.emftvm.util.ResourceIterable;
import org.eclipselabs.simpleocl.CollectionType;
import org.eclipselabs.simpleocl.Import;
import org.eclipselabs.simpleocl.IterateExp;
import org.eclipselabs.simpleocl.IteratorExp;
import org.eclipselabs.simpleocl.ModuleElement;
import org.eclipselabs.simpleocl.OclFeatureDefinition;
import org.eclipselabs.simpleocl.OclMetamodel;
import org.eclipselabs.simpleocl.OclModel;
import org.eclipselabs.simpleocl.OclModelElement;
import org.eclipselabs.simpleocl.OclModelElementExp;
import org.eclipselabs.simpleocl.PropertyCall;
import org.eclipselabs.simpleocl.StaticPropertyCall;
import org.eclipselabs.simpleocl.provider.SimpleoclItemProviderAdapterFactory;

/**
 * Syntax completion post-processor.
 * 
 * @author <a href="dwagelaar@gmail.com">Dennis Wagelaar</a>
 */
public class SyntaxProposalPostProcessor {

	public static final Map<String, String> REGULAR_OPS = new LinkedHashMap<String, String>();

	public static final Map<String, String> COLLECTION_OPS = new LinkedHashMap<String, String>();

	static {
		REGULAR_OPS.put("oclIsUndefined()",
				"oclIsUndefined() : Boolean - OclAny");

		COLLECTION_OPS.put("any(e | <expr>)",
				"any(e | <expr>) : OclAny - Collection");
		COLLECTION_OPS
				.put("append(object)",
						"append(object : OclAny) : Sequence(OclAny)/OrderedSet(OclAny) - Sequence, OrderedSet");
		COLLECTION_OPS.put("at(index)",
				"at(index : Integer) : OclAny - Sequence, OrderedSet");
		COLLECTION_OPS.put("asBag()", "asBag() : Bag(OclAny) - Collection");
		COLLECTION_OPS.put("asOrderedSet()",
				"asOrderedSet() : OrderedSet(OclAny) - Collection");
		COLLECTION_OPS.put("asSequence()",
				"asSequence() : Sequence(OclAny) - Collection");
		COLLECTION_OPS.put("asSet()", "asSet() : Set(OclAny) - Collection");
		COLLECTION_OPS
				.put("collect(e | <expr>)",
						"collect(e | <expr>) : Bag(OclAny)/Sequence(OclAny) - Collection");
		COLLECTION_OPS.put("count(object)",
				"count(object : OclAny) : Integer - Collection");
		COLLECTION_OPS.put("excludes(object)",
				"excludes(object : OclAny) : Boolean - Collection");
		COLLECTION_OPS
				.put("excludesAll(collection)",
						"excludesAll(collection : Collection(OclAny)) : Boolean - Collection");
		COLLECTION_OPS.put("excluding(object)",
				"excluding(object : OclAny) : Collection(OclAny) - Collection");
		COLLECTION_OPS
				.put("excludingAll(collection)",
						"excludingAll(collection : Collection(OclAny)) : Collection(OclAny) - Collection");
		COLLECTION_OPS.put("exists(e | <expr>)",
				"exists(e | <expr>) : Boolean - Collection");
		COLLECTION_OPS.put("exists(e, e2 | <expr)",
				"exists(e, e2 | <expr) : Boolean - Collection");
		COLLECTION_OPS
				.put("first()", "first() : OclAny - Sequence, OrderedSet");
		COLLECTION_OPS.put("flatten()",
				"flatten() : Collection(OclAny) - Collection");
		COLLECTION_OPS.put("forAll(e | <expr>)",
				"forAll(e | <expr>) : Boolean - Collection");
		COLLECTION_OPS.put("forAll(e, e2 | <expr>)",
				"forAll(e, e2 | <expr>) : Boolean - Collection");
		COLLECTION_OPS.put("includes(object)",
				"includes(object : OclAny) : Boolean - Collection");
		COLLECTION_OPS
				.put("includesAll(collection)",
						"includesAll(collection : Collection(OclAny)) : Boolean - Collection");
		COLLECTION_OPS.put("including(object)",
				"including(object : OclAny) : Collection(OclAny) - Collection");
		COLLECTION_OPS
				.put("including(object, index)",
						"including(object : OclAny, index : Integer) : Sequence(OclAny)/OrderedSet(OclAny) - Sequence, OrderedSet");
		COLLECTION_OPS
				.put("includingAll(collection)",
						"includingAll(collection : Collection(OclAny)) : Collection(OclAny) - Collection");
		COLLECTION_OPS
				.put("includingAll(collection, index)",
						"includingAll(collection : Collection(OclAny), index : Integer) : Sequence(OclAny)/OrderedSet(OclAny) - Sequence, OrderedSet");
		COLLECTION_OPS
				.put("includingRange(first, last)",
						"includingRange(first : Integer, last : Integer) : Collection(OclAny) - Collection");
		COLLECTION_OPS.put("indexOf(object)",
				"indexOf(object : OclAny) : Integer - Sequence, OrderedSet");
		COLLECTION_OPS
				.put("insertAt(index, object)",
						"insertAt(index : Integer, object : OclAny) : Sequence(OclAny)/OrderedSet(OclAny) - Sequence, OrderedSet");
		COLLECTION_OPS
				.put("intersection(setOrBag)",
						"intersection(setOrBag : Bag(OclAny)/Set(OclAny)/OrderedSet(OclAny)) : Bag(OclAny)/Set(OclAny)/OrderedSet(OclAny) - Bag, Set, OrderedSet");
		COLLECTION_OPS.put("isEmpty()", "isEmpty() : Boolean - Collection");
		COLLECTION_OPS.put("isUnique(e | <expr>)",
				"isUnique(e | <expr>) - Collection");
		COLLECTION_OPS.put("iterate(e; acc : <type> = <expr> | <expr>)",
				"iterate(e; acc : <type> = <expr> | <expr>) - Collection");
		COLLECTION_OPS.put("last()", "last() : OclAny - Sequence, OrderedSet");
		COLLECTION_OPS
				.put("lastIndexOf(object)",
						"lastIndexOf(object : OclAny) : Integer - Sequence, OrderedSet");
		COLLECTION_OPS.put("max()", "max() : Integer/Real - Collection");
		COLLECTION_OPS.put("min()", "min() : Integer/Real - Collection");
		COLLECTION_OPS.put("notEmpty()", "notEmpty() : Boolean - Collection");
		COLLECTION_OPS.put("one(e | <expr>)",
				"one(e | <expr>) : Boolean - Collection");
		COLLECTION_OPS
				.put("prepend(object)",
						"prepend(object : OclAny) : Sequence(OclAny)/OrderedSet(OclAny) - Sequence, OrderedSet");
		COLLECTION_OPS
				.put("product(collection)",
						"product(collection : Collection(OclAny)) : Set(TupleType(first : OclAny, second : OclAny)) - Collection");
		COLLECTION_OPS.put("reject(e | <expr>)",
				"reject(e | <expr>) : Collection(OclAny) - Collection");
		COLLECTION_OPS
				.put("reverse()",
						"reverse() : Sequence(OclAny)/OrderedSet(OclAny) - Sequence, OrderedSet");
		COLLECTION_OPS.put("select(e | <expr>)",
				"select(e | <expr>) : Collection(OclAny) - Collection");
		COLLECTION_OPS.put("size()", "size() : Integer - Collection");
		COLLECTION_OPS
				.put("sortedBy(e | <expr>)",
						"sortedBy(e | <expr>) : Sequence(OclAny)/OrderedSet(OclAny) - Collection");
		COLLECTION_OPS.put("subtract(set)",
				"subtract(set : Set(OclAny)/OrderedSet(OclAny))");
		COLLECTION_OPS
				.put("subSequence(fromIndex, toIndex)",
						"subSequence(fromIndex : Integer, toIndex : Integer) : Sequence(OclAny) - Sequence");
		COLLECTION_OPS.put("sum()", "sum() : Integer/Real - Collection");
		COLLECTION_OPS
				.put("symmetricDifference(set)",
						"symmetricDifference(set : Set(OclAny)/OrderedSet(OclAny)) : Set(OclAny)/OrderedSet(OclAny) - Set, OrderedSet");
		COLLECTION_OPS
				.put("union(collection)",
						"union(collection : Collection(OclAny)) : Collection(OclAny) - Collection");
	}

	protected final SimpleoclItemProviderAdapterFactory simpleoclItemProviderFactory = new SimpleoclItemProviderAdapterFactory();
	protected final EmftvmItemProviderAdapterFactory emftvmItemProviderFactory = new EmftvmItemProviderAdapterFactory();
	protected final EcoreItemProviderAdapterFactory ecoreItemProviderFactory = new EcoreItemProviderAdapterFactory();

	/**
	 * Adapts the given list of proposals and returns it.
	 * 
	 * @param proposals
	 *            the list of code completion proposals
	 * @return the adapted list of code completion proposals, or an empty list if no adaptation should be done
	 */
	public List<SyntaxCompletionProposal> process(
			final List<SyntaxCompletionProposal> proposals) {
		final List<SyntaxCompletionProposal> additionalProposals = new ArrayList<SyntaxCompletionProposal>();
		for (SyntaxCompletionProposal proposal : proposals) {
			final String prefix = proposal.getPrefix();
			final PropertyCallType propertyCallType = getPropertyCallType(prefix);
			if (propertyCallType != null) {
				addAdditionalProposals(additionalProposals, proposal, propertyCallType);
				break;
			} else {
				final EObject container = proposal.getContainer();
				final EObject expectedTerminalContainer = proposal
						.getExpectedTerminalContainer();
				OclModel oclModel = null;
				if (container instanceof OclModelElement) {
					oclModel = ((OclModelElement) container).getModel();
				} else if (container instanceof OclModelElementExp) {
					oclModel = ((OclModelElementExp) container).getModel();
				} else if (container instanceof IteratorExp) {
					addAdditionalProposals(additionalProposals, proposal, PropertyCallType.COLLECTION);
					break;
				} else if (expectedTerminalContainer instanceof IterateExp) {
					addAdditionalProposals(additionalProposals, proposal, PropertyCallType.COLLECTION);
					break;
				} else if (container instanceof StaticPropertyCall) {
					addAdditionalProposals(additionalProposals, proposal, PropertyCallType.STATIC);
					break;
				} else if (container instanceof PropertyCall) {
					addAdditionalProposals(additionalProposals, proposal, PropertyCallType.REGULAR);
					break;
				}
				if (oclModel instanceof OclMetamodel) {
					addAdditionalProposals(additionalProposals, proposal, (OclMetamodel) oclModel);
					break;
				}
			}
		}
		return additionalProposals;
	}

	/**
	 * Adds additional syntax completion proposals to
	 * <code>additionalProposals</code> for the given original proposal and
	 * {@link PropertyCallType}.
	 * 
	 * @param additionalProposals
	 *            the list of proposals to add to
	 * @param proposal
	 *            the original syntax completion proposal
	 * @param propertyCallType
	 *            the {@link PropertyCallType}
	 */
	protected void addAdditionalProposals(
			final List<SyntaxCompletionProposal> additionalProposals,
			final SyntaxCompletionProposal proposal,
			final PropertyCallType propertyCallType) {
		additionalProposals.addAll(getEFeatureProposals(proposal.getPrefix(),
				proposal.getRoot(), propertyCallType));
		additionalProposals.addAll(getModuleProposals(proposal.getPrefix(),
				proposal.getRoot(), propertyCallType));
		additionalProposals.addAll(getEMFTVMProposals(proposal.getPrefix(),
				proposal.getRoot(), propertyCallType));
	}

	/**
	 * Adds additional syntax completion proposals to
	 * <code>additionalProposals</code> for the given original proposal and
	 * {@link OclMetamodel}.
	 * 
	 * @param additionalProposals
	 *            the list of proposals to add to
	 * @param proposal
	 *            the original syntax completion proposal
	 * @param oclMetamodel
	 *            the metamodel URI
	 */
	protected void addAdditionalProposals(
			final List<SyntaxCompletionProposal> additionalProposals,
			final SyntaxCompletionProposal proposal,
			final OclMetamodel oclMetamodel) {
		final String uri = oclMetamodel.getUri();
		additionalProposals.addAll(getEClassifiers(proposal.getPrefix(), uri,
				proposal.getRoot()));
	}

	/**
	 * Returns <code>true</code> if the given prefix represents a
	 * {@link PropertyCall} token.
	 * 
	 * @param prefix
	 *            the prefix
	 * @return <code>true</code> if the given prefix represents a
	 *         {@link PropertyCall} token
	 */
	public boolean isPropertyCallPrefix(final String prefix) {
		return getPropertyCallType(prefix) != null;
	}

	/**
	 * Returns the stripped prefix if the given prefix represents a
	 * {@link PropertyCall} token.
	 * 
	 * @param prefix
	 *            the prefix
	 * @return the prefix without the {@link PropertyCall} token
	 */
	public String stripPropertyCallPrefix(final String prefix) {
		final PropertyCallType propertyCallType = getPropertyCallType(prefix);
		if (propertyCallType != null) {
			return prefix.substring(propertyCallType.getPrefix().length());
		}
		return prefix;
	}

	/**
	 * Returns the {@link PropertyCallType} for the given prefix.
	 * 
	 * @param prefix
	 *            the syntax prefix
	 * @return the {@link PropertyCallType} for the given prefix
	 */
	public PropertyCallType getPropertyCallType(final String prefix) {
		if (prefix.startsWith(PropertyCallType.COLLECTION.getPrefix())) {
			return PropertyCallType.COLLECTION;
		} else if (prefix.startsWith(PropertyCallType.STATIC.getPrefix())) {
			return PropertyCallType.STATIC;
		} else if (prefix.startsWith(PropertyCallType.REGULAR.getPrefix())) {
			return PropertyCallType.REGULAR;
		}
		return null;
	}

	/**
	 * Returns the SimpleOCL {@link org.eclipselabs.simpleocl.Module} syntax
	 * completion proposals for the given prefix and root.
	 * 
	 * @param prefix
	 *            the syntax prefix
	 * @param root
	 *            the root AST node
	 * @param propertyCallType
	 *            the {@link PropertyCallType}
	 * @return the syntax completion proposals
	 */
	protected List<SyntaxCompletionProposal> getModuleProposals(
			final String prefix, final EObject root,
			final PropertyCallType propertyCallType) {
		if (!(root instanceof org.eclipselabs.simpleocl.Module)) {
			return Collections.<SyntaxCompletionProposal> emptyList();
		}
		final String strippedPrefix = stripPropertyCallPrefix(prefix);
		final boolean isCollectionOp = propertyCallType == PropertyCallType.COLLECTION;
		final boolean isStatic = propertyCallType == PropertyCallType.STATIC;
		final SortedSet<String> features = new TreeSet<String>();
		final Map<String, URL> images = new HashMap<String, URL>();
		final Map<String, String> displayStrings = new HashMap<String, String>();
		for (ModuleElement element : ((org.eclipselabs.simpleocl.Module) root)
				.getElements()) {
			if (element instanceof OclFeatureDefinition) {
				OclFeatureDefinition fd = (OclFeatureDefinition) element;
				if (isStatic != fd.isStatic()) {
					continue;
				}
				if (isCollectionOp != (fd.getContext_() != null && fd
						.getContext_().getContext_() instanceof CollectionType)) {
					continue;
				}
				String name = fd.getFeature().getName();
				if (strippedPrefix.isEmpty() || name.startsWith(strippedPrefix)) {
					StringBuilder sb = new StringBuilder(name);
					StringBuilder displayString = new StringBuilder(name);
					if (fd.getFeature() instanceof org.eclipselabs.simpleocl.Operation) {
						sb.append('(');
						displayString.append('(');
						boolean first = true;
						for (org.eclipselabs.simpleocl.Parameter par : ((org.eclipselabs.simpleocl.Operation) fd
								.getFeature()).getParameters()) {
							if (!first) {
								sb.append(", ");
								displayString.append(", ");
							}
							sb.append(par.getVarName());
							displayString.append(par.getVarName())
									.append(" : ").append(par.getType());
							first = false;
						}
						sb.append(')');
						displayString.append(')');
					}
					if (fd.getContext_() != null) {
						displayString.append(" - ").append(
								fd.getContext_().getContext_());
					}
					final ItemProviderAdapter itemProviderAdapter = (ItemProviderAdapter) simpleoclItemProviderFactory
							.createAdapter(fd.getFeature());
					images.put(sb.toString(),
							getItemProviderImageURL(itemProviderAdapter
									.getImage(fd.getFeature())));
					displayStrings.put(sb.toString(), displayString.toString());
					features.add(sb.toString());
				}
			}
		}
		return buildProposals(prefix, root, features, images, displayStrings);
	}

	/**
	 * Returns the EMFTVM syntax completion proposals for the given prefix and
	 * root.
	 * 
	 * @param prefix
	 *            the syntax prefix
	 * @param root
	 *            the root AST node
	 * @param propertyCallType
	 *            the {@link PropertyCallType}
	 * @return the syntax completion proposals
	 */
	protected List<SyntaxCompletionProposal> getEMFTVMProposals(
			final String prefix, final EObject root,
			final PropertyCallType propertyCallType) {
		final String strippedPrefix = stripPropertyCallPrefix(prefix);
		final boolean isCollectionOp = propertyCallType == PropertyCallType.COLLECTION;
		final boolean isStatic = propertyCallType == PropertyCallType.STATIC;
		final SortedSet<String> features = new TreeSet<String>();
		final Map<String, URL> images = new HashMap<String, URL>();
		final Map<String, String> displayStrings = new HashMap<String, String>();
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
				if (context != null
						&& isCollectionOp != Collection.class
								.isAssignableFrom(context.getInstanceClass())) {
					continue;
				}
				String name = f.getName();
				if (strippedPrefix.isEmpty() || name.startsWith(strippedPrefix)) {
					StringBuilder sb = new StringBuilder(name);
					StringBuilder displayString = new StringBuilder(name);
					if (f instanceof Operation) {
						sb.append('(');
						displayString.append('(');
						boolean first = true;
						for (Parameter par : ((Operation) f).getParameters()) {
							if (!first) {
								sb.append(", ");
								displayString.append(", ");
							}
							sb.append(par.getName());
							displayString.append(par.getName()).append(" : ");
							if (!EMFTVMUtil.NATIVE.equals(par.getTypeModel())) {
								displayString.append(par.getTypeModel())
										.append("!");
							}
							displayString.append(par.getType());
							first = false;
						}
						sb.append(')');
						displayString.append(") - ");
						if (!EMFTVMUtil.NATIVE.equals(f.getContextModel())) {
							displayString.append(f.getContextModel()).append(
									"!");
						}
						displayString.append(f.getContext());
					}
					ItemProviderAdapter itemProviderAdapter = (ItemProviderAdapter) emftvmItemProviderFactory
							.createAdapter(f);
					images.put(sb.toString(),
							getItemProviderImageURL(itemProviderAdapter
									.getImage(f)));
					displayStrings.put(sb.toString(), displayString.toString());
					features.add(sb.toString());
				}
			}
			if (isStatic) {
				for (Rule r : module.getRules()) {
					if (r.getMode() != RuleMode.MANUAL) {
						continue;
					}
					String name = r.getName();
					if (strippedPrefix.isEmpty()
							|| name.startsWith(strippedPrefix)) {
						StringBuilder sb = new StringBuilder(name);
						StringBuilder displayString = new StringBuilder(name);
						sb.append('(');
						displayString.append('(');
						boolean first = true;
						for (InputRuleElement re : r.getInputElements()) {
							if (!first) {
								sb.append(", ");
								displayString.append(", ");
							}
							sb.append(re.getName());
							displayString.append(re.getName()).append(" : ");
							if (!EMFTVMUtil.NATIVE.equals(re.getTypeModel())) {
								displayString.append(re.getTypeModel()).append(
										"!");
							}
							displayString.append(re.getType());
							first = false;
						}
						sb.append(')');
						displayString.append(')');
						ItemProviderAdapter itemProviderAdapter = (ItemProviderAdapter) emftvmItemProviderFactory
								.createAdapter(r);
						images.put(sb.toString(),
								getItemProviderImageURL(itemProviderAdapter
										.getImage(r)));
						displayStrings.put(sb.toString(),
								displayString.toString());
						features.add(sb.toString());
					}
				}
			}
		}
		final Map<String, String> builtinOps;
		if (isCollectionOp) {
			builtinOps = COLLECTION_OPS;
		} else if (isStatic) {
			builtinOps = Collections.<String, String> emptyMap();
		} else {
			builtinOps = REGULAR_OPS;
		}
		final ItemProviderAdapter itemProviderAdapter = new OperationItemProvider(
				emftvmItemProviderFactory);
		for (String f : builtinOps.keySet()) {
			if (strippedPrefix.isEmpty() || f.startsWith(strippedPrefix)) {
				features.add(f);
				images.put(f, getItemProviderImageURL(itemProviderAdapter
						.getImage((Object) null)));
				displayStrings.put(f, builtinOps.get(f));
			}
		}
		return buildProposals(prefix, root, features, images, displayStrings);
	}

	/**
	 * Returns the {@link EStructuralFeature} and {@link EOperation} syntax
	 * completion proposals for the given prefix and root.
	 * 
	 * @param prefix
	 *            the syntax prefix
	 * @param root
	 *            the root AST node
	 * @param propertyCallType
	 *            the {@link PropertyCallType}
	 * @return the syntax completion proposals
	 */
	protected List<SyntaxCompletionProposal> getEFeatureProposals(
			final String prefix, final EObject root,
			final PropertyCallType propertyCallType) {
		if (propertyCallType != PropertyCallType.REGULAR
				|| !(root instanceof org.eclipselabs.simpleocl.Module)) {
			return Collections.<SyntaxCompletionProposal> emptyList();
		}
		final String strippedPrefix = stripPropertyCallPrefix(prefix);
		final SortedSet<String> features = new TreeSet<String>();
		final Map<String, URL> images = new HashMap<String, URL>();
		final Map<String, String> displayStrings = new HashMap<String, String>();
		final org.eclipselabs.simpleocl.Module module = (org.eclipselabs.simpleocl.Module) root;
		final ExecEnv env = EmftvmFactory.eINSTANCE.createExecEnv();
		loadMetamodels(module, env);
		for (OclMetamodel metamodel : module.getMetamodels()) {
			try {
				final URI emfURI = URI.createURI(metamodel.getUri());
				final ResourceSet rs = new ResourceSetImpl();
				final Resource resource = rs.getResource(emfURI, true);
				for (EObject eObject : new ResourceIterable(resource)) {
					if (!(eObject.eContainer() instanceof EClassifier)) {
						continue;
					}
					if (eObject instanceof EStructuralFeature
							|| eObject instanceof EOperation) {
						String name = ((ETypedElement) eObject).getName();
						if (strippedPrefix.isEmpty()
								|| name.startsWith(strippedPrefix)) {
							StringBuilder sb = new StringBuilder(name);
							StringBuilder displayString = new StringBuilder(
									name);
							if (eObject instanceof EOperation) {
								sb.append('(');
								displayString.append('(');
								boolean first = true;
								for (EParameter par : ((EOperation) eObject)
										.getEParameters()) {
									if (!first) {
										sb.append(", ");
										displayString.append(", ");
									}
									sb.append(par.getName());
									displayString.append(par.getName())
											.append(" : ")
											.append(toPrettyString(par, env));
									first = false;
								}
								sb.append(')');
								displayString.append(')');
							}
							displayString
									.append(" : ")
									.append(toPrettyString(
											(ETypedElement) eObject, env))
									.append(" - ")
									.append(EMFTVMUtil.toPrettyString(
											eObject.eContainer(), env));
							features.add(sb.toString());
							ItemProviderAdapter itemProviderAdapter = (ItemProviderAdapter) ecoreItemProviderFactory
									.createAdapter(eObject);
							images.put(sb.toString(),
									getItemProviderImageURL(itemProviderAdapter
											.getImage(eObject)));
							displayStrings.put(sb.toString(),
									displayString.toString());
						}
					}
				}
			} catch (RuntimeException e) {
				SimpleoclUIPlugin.logError(e.getLocalizedMessage(), e);
			}
		}
		return buildProposals(prefix, root, features, images, displayStrings);
	}

	/**
	 * Returns the {@link EClassifier} syntax completion proposals for the given
	 * EMF URI.
	 * 
	 * @param prefix
	 *            the syntax prefix
	 * @param uri
	 *            the EMF URI
	 * @param root
	 *            the root AST node
	 * @return the syntax completion proposals
	 */
	protected List<SyntaxCompletionProposal> getEClassifiers(
			final String prefix, final String uri, final EObject root) {
		final SortedSet<String> features = new TreeSet<String>();
		final Map<String, URL> images = new HashMap<String, URL>();
		final Map<String, String> displayStrings = new HashMap<String, String>();
		try {
			final URI emfURI = URI.createURI(uri);
			final ResourceSet rs = new ResourceSetImpl();
			final Resource resource = rs.getResource(emfURI, true);
			for (EObject eObject : new ResourceIterable(resource)) {
				if (eObject instanceof EClassifier) {
					String f = ((EClassifier) eObject).getName();
					if (f.startsWith(prefix)) {
						features.add(f);
						ItemProviderAdapter itemProviderAdapter = (ItemProviderAdapter) ecoreItemProviderFactory
								.createAdapter(eObject);
						images.put(f,
								getItemProviderImageURL(itemProviderAdapter
										.getImage(eObject)));
						displayStrings.put(f, f);
					}
				}
			}
		} catch (RuntimeException e) {
			SimpleoclUIPlugin.logError(e.getLocalizedMessage(), e);
		}
		return buildProposals(prefix, root, features, images, displayStrings);
	}

	/**
	 * Loads the imports for the given root into the given {@link ExecEnv}.
	 * 
	 * @param root
	 *            the root AST node
	 * @param env
	 *            the {@link ExecEnv}
	 */
	protected void loadImports(final org.eclipselabs.simpleocl.Module root,
			final ExecEnv env) {
		String uriPrefix = root.eResource().getURI().toPlatformString(true);
		uriPrefix = uriPrefix.substring(0, uriPrefix.lastIndexOf('/') + 1);
		final ModuleResolver mr = new DefaultModuleResolver(uriPrefix,
				new ResourceSetImpl());
		for (Import imp : root.getImports()) {
			try {
				env.loadModule(mr, imp.getName(), false);
			} catch (Exception e) {
				SimpleoclUIPlugin.logError(e.getLocalizedMessage(), e);
			}
		}
	}

	/**
	 * Loads the metamodels for the given root into the given {@link ExecEnv}.
	 * 
	 * @param root
	 *            the root AST node
	 * @param env
	 *            the {@link ExecEnv}
	 */
	protected void loadMetamodels(final org.eclipselabs.simpleocl.Module root,
			final ExecEnv env) {
		final ResourceSet rs = new ResourceSetImpl();
		for (OclMetamodel oclMetamodel : root.getMetamodels()) {
			String uriString = oclMetamodel.getUri();
			if (uriString == null || uriString.trim().isEmpty()) {
				continue;
			}
			try {
				URI uri = URI.createURI(uriString);
				Resource res = rs.getResource(uri, true);
				Metamodel metamodel = EmftvmFactory.eINSTANCE.createMetamodel();
				metamodel.setResource(res);
				env.registerMetaModel(oclMetamodel.getName(), metamodel);
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
	 * @param displayStrings
	 *            the syntax completion display strings to show to the user
	 * @return the syntax completion proposal
	 */
	protected List<SyntaxCompletionProposal> buildProposals(
			final String prefix, final EObject root,
			final Collection<String> features, final Map<String, URL> images,
			final Map<String, String> displayStrings) {
		final List<SyntaxCompletionProposal> proposals = new ArrayList<SyntaxCompletionProposal>();
		for (String f : features) {
			proposals.add(new SyntaxCompletionProposal(root, null, null,
					prefix, f, images.get(f), displayStrings.get(f)));
		}
		return proposals;
	}

	/**
	 * Returns the {@link URL} for the given <code>itemProvderImage</code>.
	 * 
	 * @param itemProviderImage
	 *            the image returned by
	 *            {@link ItemProviderAdapter#getImage(Object)}
	 * @return the {@link URL} for the given <code>itemProvderImage</code>
	 */
	public URL getItemProviderImageURL(final Object itemProviderImage) {
		if (itemProviderImage instanceof ComposedImage) {
			return getItemProviderImageURL(((ComposedImage) itemProviderImage)
					.getImages().get(0));
		}
		return (URL) itemProviderImage;
	}

	/**
	 * Offers an alternative to the default <code>toString()</code> method. Uses
	 * <code>env</code> to determine the containing model of types. Compensates
	 * for {@link EObject}'s notoriously bad <code>toString()</code>.
	 * 
	 * @param object
	 * @param env
	 * @return the string representation of <code>object</code>.
	 * @see EMFTVMUtil#toPrettyString(Object, ExecEnv)
	 */
	public String toPrettyString(final ETypedElement element,
			final ExecEnv env) {
		final StringBuilder sb = new StringBuilder();
		sb.append(EMFTVMUtil.toPrettyString(element.getEType(), env));
		if (element.getUpperBound() != 1) {
			sb.append(" [").append(element.getLowerBound()).append("..");
			if (element.getUpperBound() == -1) {
				sb.append('*');
			} else {
				sb.append(element.getUpperBound());
			}
			sb.append(']');
		}
		return sb.toString();
	}

}
