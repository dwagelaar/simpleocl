/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

public class SimpleoclResource extends org.eclipse.emf.ecore.resource.impl.ResourceImpl implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource {
	
	public class ElementBasedTextDiagnostic implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextDiagnostic {
		
		private final be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclLocationMap locationMap;
		private final org.eclipse.emf.common.util.URI uri;
		private final org.eclipse.emf.ecore.EObject element;
		private final be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclProblem problem;
		
		public ElementBasedTextDiagnostic(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclLocationMap locationMap, org.eclipse.emf.common.util.URI uri, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclProblem problem, org.eclipse.emf.ecore.EObject element) {
			super();
			this.uri = uri;
			this.locationMap = locationMap;
			this.element = element;
			this.problem = problem;
		}
		
		public String getMessage() {
			return problem.getMessage();
		}
		
		public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclProblem getProblem() {
			return problem;
		}
		
		public String getLocation() {
			return uri.toString();
		}
		
		public int getCharStart() {
			return Math.max(0, locationMap.getCharStart(element));
		}
		
		public int getCharEnd() {
			return Math.max(0, locationMap.getCharEnd(element));
		}
		
		public int getColumn() {
			return Math.max(0, locationMap.getColumn(element));
		}
		
		public int getLine() {
			return Math.max(0, locationMap.getLine(element));
		}
		
		public org.eclipse.emf.ecore.EObject getElement() {
			return element;
		}
		
		public boolean wasCausedBy(org.eclipse.emf.ecore.EObject element) {
			if (this.element == null) {
				return false;
			}
			return this.element.equals(element);
		}
		
		public String toString() {
			return getMessage() + " at " + getLocation() + " line " + getLine() + ", column " + getColumn();
		}
	}
	
	public class PositionBasedTextDiagnostic implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextDiagnostic {
		
		private final org.eclipse.emf.common.util.URI uri;
		
		private int column;
		private int line;
		private int charStart;
		private int charEnd;
		private be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclProblem problem;
		
		public PositionBasedTextDiagnostic(org.eclipse.emf.common.util.URI uri, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclProblem problem, int column, int line, int charStart, int charEnd) {
			
			super();
			this.uri = uri;
			this.column = column;
			this.line = line;
			this.charStart = charStart;
			this.charEnd = charEnd;
			this.problem = problem;
		}
		
		public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclProblem getProblem() {
			return problem;
		}
		
		public int getCharStart() {
			return charStart;
		}
		
		public int getCharEnd() {
			return charEnd;
		}
		
		public int getColumn() {
			return column;
		}
		
		public int getLine() {
			return line;
		}
		
		public String getLocation() {
			return uri.toString();
		}
		
		public String getMessage() {
			return problem.getMessage();
		}
		
		public boolean wasCausedBy(org.eclipse.emf.ecore.EObject element) {
			return false;
		}
		
		public String toString() {
			return getMessage() + " at " + getLocation() + " line " + getLine() + ", column " + getColumn();
		}
	}
	
	private be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolverSwitch resolverSwitch;
	private be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclLocationMap locationMap;
	private int proxyCounter = 0;
	private be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextParser parser;
	private java.util.Map<String, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject>> internalURIFragmentMap = new java.util.LinkedHashMap<String, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject>>();
	private java.util.Map<String, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix> quickFixMap = new java.util.LinkedHashMap<String, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix>();
	private java.util.Map<?, ?> loadOptions;
	
	/**
	 * If a post-processor is currently running, this field holds a reference to it.
	 * This reference is used to terminate post-processing if needed.
	 */
	private be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclResourcePostProcessor runningPostProcessor;
	
	/**
	 * A flag to indicate whether reloading of the resource shall be cancelled.
	 */
	private boolean terminateReload = false;
	
	public SimpleoclResource() {
		super();
		resetLocationMap();
	}
	
	public SimpleoclResource(org.eclipse.emf.common.util.URI uri) {
		super(uri);
		resetLocationMap();
	}
	
	protected void doLoad(java.io.InputStream inputStream, java.util.Map<?,?> options) throws java.io.IOException {
		this.loadOptions = options;
		this.terminateReload = false;
		String encoding = null;
		java.io.InputStream actualInputStream = inputStream;
		Object inputStreamPreProcessorProvider = null;
		if (options != null) {
			inputStreamPreProcessorProvider = options.get(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclOptions.INPUT_STREAM_PREPROCESSOR_PROVIDER);
		}
		if (inputStreamPreProcessorProvider != null) {
			if (inputStreamPreProcessorProvider instanceof be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclInputStreamProcessorProvider) {
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclInputStreamProcessorProvider provider = (be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclInputStreamProcessorProvider) inputStreamPreProcessorProvider;
				be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclInputStreamProcessor processor = provider.getInputStreamProcessor(inputStream);
				actualInputStream = processor;
				encoding = processor.getOutputEncoding();
			}
		}
		
		parser = getMetaInformation().createParser(actualInputStream, encoding);
		parser.setOptions(options);
		be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolverSwitch referenceResolverSwitch = getReferenceResolverSwitch();
		referenceResolverSwitch.setOptions(options);
		be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclParseResult result = parser.parse();
		clearState();
		getContentsInternal().clear();
		org.eclipse.emf.ecore.EObject root = null;
		if (result != null) {
			root = result.getRoot();
			if (root != null) {
				getContentsInternal().add(root);
			}
			java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclCommand<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource>> commands = result.getPostParseCommands();
			if (commands != null) {
				for (be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclCommand<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextResource>  command : commands) {
					command.execute(this);
				}
			}
		}
		getReferenceResolverSwitch().setOptions(options);
		if (getErrors().isEmpty()) {
			runPostProcessors(options);
			if (root != null) {
				runValidators(root);
			}
		}
	}
	
	public void reload(java.io.InputStream inputStream, java.util.Map<?,?> options) throws java.io.IOException {
		try {
			isLoaded = false;
			java.util.Map<Object, Object> loadOptions = addDefaultLoadOptions(options);
			doLoad(inputStream, loadOptions);
			org.eclipse.emf.ecore.util.EcoreUtil.resolveAll(this.getResourceSet());
		} catch (be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException tpe) {
			// do nothing - the resource is left unchanged if this exception is thrown
		}
		isLoaded = true;
	}
	
	public void cancelReload() {
		be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextParser parserCopy = parser;
		parserCopy.terminate();
		this.terminateReload = true;
		be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclResourcePostProcessor runningPostProcessorCopy = runningPostProcessor;
		if (runningPostProcessorCopy != null) {
			runningPostProcessorCopy.terminate();
		}
	}
	
	protected void doSave(java.io.OutputStream outputStream, java.util.Map<?,?> options) throws java.io.IOException {
		be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextPrinter printer = getMetaInformation().createPrinter(outputStream, this);
		be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolverSwitch referenceResolverSwitch = getReferenceResolverSwitch();
		referenceResolverSwitch.setOptions(options);
		for (org.eclipse.emf.ecore.EObject root : getContentsInternal()) {
			printer.print(root);
		}
	}
	
	protected String getSyntaxName() {
		return "simpleocl";
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolverSwitch getReferenceResolverSwitch() {
		if (resolverSwitch == null) {
			resolverSwitch = new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclReferenceResolverSwitch();
		}
		return resolverSwitch;
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation getMetaInformation() {
		return new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMetaInformation();
	}
	
	protected void resetLocationMap() {
		locationMap = new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclLocationMap();
	}
	
	public void addURIFragment(String internalURIFragment, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject> uriFragment) {
		internalURIFragmentMap.put(internalURIFragment, uriFragment);
	}
	
	public <ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject> void registerContextDependentProxy(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclContextDependentURIFragmentFactory<ContainerType, ReferenceType> factory, ContainerType container, org.eclipse.emf.ecore.EReference reference, String id, org.eclipse.emf.ecore.EObject proxyElement, int position) {
		org.eclipse.emf.ecore.InternalEObject proxy = (org.eclipse.emf.ecore.InternalEObject) proxyElement;
		String internalURIFragment = be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclContextDependentURIFragment.INTERNAL_URI_FRAGMENT_PREFIX + (proxyCounter++) + "_" + id;
		be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclContextDependentURIFragment<?> uriFragment = factory.create(id, container, reference, position, proxy);
		proxy.eSetProxyURI(getURI().appendFragment(internalURIFragment));
		addURIFragment(internalURIFragment, uriFragment);
	}
	
	public org.eclipse.emf.ecore.EObject getEObject(String id) {
		if (internalURIFragmentMap.containsKey(id)) {
			be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject> uriFragment = internalURIFragmentMap.get(id);
			boolean wasResolvedBefore = uriFragment.isResolved();
			be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolveResult<? extends org.eclipse.emf.ecore.EObject> result = null;
			// catch and report all Exceptions that occur during proxy resolving
			try {
				result = uriFragment.resolve();
			} catch (Exception e) {
				String message = "An expection occured while resolving the proxy for: "+ id + ". (" + e.toString() + ")";
				addProblem(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclProblem(message, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.UNRESOLVED_REFERENCE, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity.ERROR),uriFragment.getProxy());
				be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclPlugin.logError(message, e);
			}
			if (result == null) {
				// the resolving did call itself
				return null;
			}
			if (!wasResolvedBefore && !result.wasResolved()) {
				attachResolveError(result, uriFragment.getProxy());
				return null;
			} else if (!result.wasResolved()) {
				return null;
			} else {
				org.eclipse.emf.ecore.EObject proxy = uriFragment.getProxy();
				// remove an error that might have been added by an earlier attempt
				removeDiagnostics(proxy, getErrors());
				// remove old warnings and attach new
				removeDiagnostics(proxy, getWarnings());
				attachResolveWarnings(result, proxy);
				be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceMapping<? extends org.eclipse.emf.ecore.EObject> mapping = result.getMappings().iterator().next();
				org.eclipse.emf.ecore.EObject resultElement = getResultElement(uriFragment, mapping, proxy, result.getErrorMessage());
				org.eclipse.emf.ecore.EObject container = uriFragment.getContainer();
				replaceProxyInLayoutAdapters(container, proxy, resultElement);
				return resultElement;
			}
		} else {
			return super.getEObject(id);
		}
	}
	
	protected void replaceProxyInLayoutAdapters(org.eclipse.emf.ecore.EObject container, org.eclipse.emf.ecore.EObject proxy, org.eclipse.emf.ecore.EObject target) {
		for (org.eclipse.emf.common.notify.Adapter adapter : container.eAdapters()) {
			if (adapter instanceof be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclLayoutInformationAdapter) {
				be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclLayoutInformationAdapter layoutInformationAdapter = (be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclLayoutInformationAdapter) adapter;
				layoutInformationAdapter.replaceProxy(proxy, target);
			}
		}
	}
	
	private org.eclipse.emf.ecore.EObject getResultElement(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclContextDependentURIFragment<? extends org.eclipse.emf.ecore.EObject> uriFragment, be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceMapping<? extends org.eclipse.emf.ecore.EObject> mapping, org.eclipse.emf.ecore.EObject proxy, final String errorMessage) {
		if (mapping instanceof be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclURIMapping<?>) {
			org.eclipse.emf.common.util.URI uri = ((be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclURIMapping<? extends org.eclipse.emf.ecore.EObject>)mapping).getTargetIdentifier();
			if (uri != null) {
				org.eclipse.emf.ecore.EObject result = null;
				try {
					result = this.getResourceSet().getEObject(uri, true);
				} catch (Exception e) {
					// we can catch exceptions here, because EMF will try to resolve again and handle
					// the exception
				}
				if (result == null || result.eIsProxy()) {
					// unable to resolve: attach error
					if (errorMessage == null) {
						assert(false);
					} else {
						addProblem(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclProblem(errorMessage, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.UNRESOLVED_REFERENCE, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity.ERROR), proxy);
					}
				}
				return result;
			}
			return null;
		} else if (mapping instanceof be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclElementMapping<?>) {
			org.eclipse.emf.ecore.EObject element = ((be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclElementMapping<? extends org.eclipse.emf.ecore.EObject>)mapping).getTargetElement();
			org.eclipse.emf.ecore.EReference reference = uriFragment.getReference();
			org.eclipse.emf.ecore.EReference oppositeReference = uriFragment.getReference().getEOpposite();
			if (!uriFragment.getReference().isContainment() && oppositeReference != null) {
				if (reference.isMany()) {
					org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList.ManyInverse<org.eclipse.emf.ecore.EObject> list = be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclCastUtil.cast(element.eGet(oppositeReference, false));										// avoids duplicate entries in the reference caused by adding to the
					// oppositeReference
					list.basicAdd(uriFragment.getContainer(),null);
				} else {
					uriFragment.getContainer().eSet(uriFragment.getReference(), element);
				}
			}
			return element;
		} else {
			assert(false);
			return null;
		}
	}
	
	private void removeDiagnostics(org.eclipse.emf.ecore.EObject cause, java.util.List<org.eclipse.emf.ecore.resource.Resource.Diagnostic> diagnostics) {
		// remove all errors/warnings from this resource
		for (org.eclipse.emf.ecore.resource.Resource.Diagnostic errorCand : new org.eclipse.emf.common.util.BasicEList<org.eclipse.emf.ecore.resource.Resource.Diagnostic>(diagnostics)) {
			if (errorCand instanceof be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextDiagnostic) {
				if (((be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextDiagnostic) errorCand).wasCausedBy(cause)) {
					diagnostics.remove(errorCand);
					be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMarkerHelper.unmark(this, cause);
				}
			}
		}
	}
	
	private void attachResolveError(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolveResult<?> result, org.eclipse.emf.ecore.EObject proxy) {
		// attach errors to this resource
		assert result != null;
		final String errorMessage = result.getErrorMessage();
		if (errorMessage == null) {
			assert(false);
		} else {
			addProblem(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclProblem(errorMessage, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.UNRESOLVED_REFERENCE, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity.ERROR, result.getQuickFixes()), proxy);
		}
	}
	
	private void attachResolveWarnings(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceResolveResult<? extends org.eclipse.emf.ecore.EObject> result, org.eclipse.emf.ecore.EObject proxy) {
		assert result != null;
		assert result.wasResolved();
		if (result.wasResolved()) {
			for (be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclReferenceMapping<? extends org.eclipse.emf.ecore.EObject> mapping : result.getMappings()) {
				final String warningMessage = mapping.getWarning();
				if (warningMessage == null) {
					continue;
				}
				addProblem(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclProblem(warningMessage, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.UNRESOLVED_REFERENCE, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity.WARNING), proxy);
			}
		}
	}
	
	/**
	 * Extends the super implementation by clearing all information about element
	 * positions.
	 */
	protected void doUnload() {
		super.doUnload();
		clearState();
		loadOptions = null;
	}
	
	protected void runPostProcessors(java.util.Map<?, ?> loadOptions) {
		be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMarkerHelper.unmark(this, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.ANALYSIS_PROBLEM);
		if (terminateReload) {
			return;
		}
		// first, run the generated post processor
		runPostProcessor(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclResourcePostProcessor());
		if (loadOptions == null) {
			return;
		}
		// then, run post processors that are registered via the load options extension
		// point
		Object resourcePostProcessorProvider = loadOptions.get(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclOptions.RESOURCE_POSTPROCESSOR_PROVIDER);
		if (resourcePostProcessorProvider != null) {
			if (resourcePostProcessorProvider instanceof be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclResourcePostProcessorProvider) {
				runPostProcessor(((be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclResourcePostProcessorProvider) resourcePostProcessorProvider).getResourcePostProcessor());
			} else if (resourcePostProcessorProvider instanceof java.util.Collection<?>) {
				java.util.Collection<?> resourcePostProcessorProviderCollection = (java.util.Collection<?>) resourcePostProcessorProvider;
				for (Object processorProvider : resourcePostProcessorProviderCollection) {
					if (terminateReload) {
						return;
					}
					if (processorProvider instanceof be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclResourcePostProcessorProvider) {
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclResourcePostProcessorProvider csProcessorProvider = (be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclResourcePostProcessorProvider) processorProvider;
						be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclResourcePostProcessor postProcessor = csProcessorProvider.getResourcePostProcessor();
						runPostProcessor(postProcessor);
					}
				}
			}
		}
	}
	
	protected void runPostProcessor(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclResourcePostProcessor postProcessor) {
		try {
			this.runningPostProcessor = postProcessor;
			postProcessor.process(this);
		} catch (Exception e) {
			be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclPlugin.logError("Exception while running a post-processor.", e);
		}
		this.runningPostProcessor = null;
	}
	
	public void load(java.util.Map<?, ?> options) throws java.io.IOException {
		java.util.Map<Object, Object> loadOptions = addDefaultLoadOptions(options);
		super.load(loadOptions);
		org.eclipse.emf.ecore.util.EcoreUtil.resolveAll(this.getResourceSet());
	}
	
	public void setURI(org.eclipse.emf.common.util.URI uri) {
		// because of the context dependent proxy resolving it is essential to resolve all
		// proxies before the URI is changed which can cause loss of object identities
		org.eclipse.emf.ecore.util.EcoreUtil.resolveAll(this);
		super.setURI(uri);
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclLocationMap getLocationMap() {
		return locationMap;
	}
	
	public void addProblem(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclProblem problem, org.eclipse.emf.ecore.EObject element) {
		ElementBasedTextDiagnostic diagnostic = new ElementBasedTextDiagnostic(locationMap, getURI(), problem, element);
		getDiagnostics(problem.getSeverity()).add(diagnostic);
		if (isMarkerCreationEnabled()) {
			be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMarkerHelper.mark(this, diagnostic);
		}
		java.util.Collection<be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix> quickFixes = problem.getQuickFixes();
		if (quickFixes != null) {
			for (be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix quickFix : quickFixes) {
				if (quickFix != null) {
					quickFixMap.put(quickFix.getContextAsString(), quickFix);
				}
			}
		}
	}
	
	public void addProblem(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclProblem problem, int column, int line, int charStart, int charEnd) {
		PositionBasedTextDiagnostic diagnostic = new PositionBasedTextDiagnostic(getURI(), problem, column, line, charStart, charEnd);
		getDiagnostics(problem.getSeverity()).add(diagnostic);
		if (isMarkerCreationEnabled()) {
			be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMarkerHelper.mark(this, diagnostic);
		}
	}
	
	@Deprecated	
	public void addError(String message, org.eclipse.emf.ecore.EObject cause) {
		addError(message, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.UNKNOWN, cause);
	}
	
	public void addError(String message, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType type, org.eclipse.emf.ecore.EObject cause) {
		addProblem(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclProblem(message, type, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity.ERROR), cause);
	}
	
	@Deprecated	
	public void addWarning(String message, org.eclipse.emf.ecore.EObject cause) {
		addWarning(message, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.UNKNOWN, cause);
	}
	
	public void addWarning(String message, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType type, org.eclipse.emf.ecore.EObject cause) {
		addProblem(new be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclProblem(message, type, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity.WARNING), cause);
	}
	
	private java.util.List<org.eclipse.emf.ecore.resource.Resource.Diagnostic> getDiagnostics(be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity severity) {
		if (severity == be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity.ERROR) {
			return getErrors();
		} else {
			return getWarnings();
		}
	}
	
	protected java.util.Map<Object, Object> addDefaultLoadOptions(java.util.Map<?, ?> loadOptions) {
		java.util.Map<Object, Object> loadOptionsCopy = be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclMapUtil.copySafelyToObjectToObjectMap(loadOptions);
		if (org.eclipse.core.runtime.Platform.isRunning()) {
			// find default load option providers
			org.eclipse.core.runtime.IExtensionRegistry extensionRegistry = org.eclipse.core.runtime.Platform.getExtensionRegistry();
			org.eclipse.core.runtime.IConfigurationElement configurationElements[] = extensionRegistry.getConfigurationElementsFor(be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclPlugin.EP_DEFAULT_LOAD_OPTIONS_ID);
			for (org.eclipse.core.runtime.IConfigurationElement element : configurationElements) {
				try {
					be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclOptionProvider provider = (be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclOptionProvider) element.createExecutableExtension("class");
					final java.util.Map<?, ?> options = provider.getOptions();
					final java.util.Collection<?> keys = options.keySet();
					for (Object key : keys) {
						addLoadOption(loadOptionsCopy, key, options.get(key));
					}
				} catch (org.eclipse.core.runtime.CoreException ce) {
					be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclPlugin.logError("Exception while getting default options.", ce);
				}
			}
		}
		return loadOptionsCopy;
	}
	
	/**
	 * Adds a new key,value pair to the list of options. If there is already an option
	 * with the same key, the two values are collected in a list.
	 */
	private void addLoadOption(java.util.Map<Object, Object> options,Object key, Object value) {
		// check if there is already an option set
		if (options.containsKey(key)) {
			Object currentValue = options.get(key);
			if (currentValue instanceof java.util.List<?>) {
				// if the current value is a list, we add the new value to this list
				java.util.List<?> currentValueAsList = (java.util.List<?>) currentValue;
				java.util.List<Object> currentValueAsObjectList = be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclListUtil.copySafelyToObjectList(currentValueAsList);
				if (value instanceof java.util.Collection<?>) {
					currentValueAsObjectList.addAll((java.util.Collection<?>) value);
				} else {
					currentValueAsObjectList.add(value);
				}
				options.put(key, currentValueAsObjectList);
			} else {
				// if the current value is not a list, we create a fresh list and add both the old
				// (current) and the new value to this list
				java.util.List<Object> newValueList = new java.util.ArrayList<Object>();
				newValueList.add(currentValue);
				if (value instanceof java.util.Collection<?>) {
					newValueList.addAll((java.util.Collection<?>) value);
				} else {
					newValueList.add(value);
				}
				options.put(key, newValueList);
			}
		} else {
			options.put(key, value);
		}
	}
	
	/**
	 * Extends the super implementation by clearing all information about element
	 * positions.
	 */
	protected void clearState() {
		// clear concrete syntax information
		resetLocationMap();
		internalURIFragmentMap.clear();
		getErrors().clear();
		getWarnings().clear();
		if (isMarkerCreationEnabled()) {
			be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMarkerHelper.unmark(this, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.UNKNOWN);
			be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMarkerHelper.unmark(this, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.SYNTAX_ERROR);
			be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclMarkerHelper.unmark(this, be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.UNRESOLVED_REFERENCE);
		}
		proxyCounter = 0;
		resolverSwitch = null;
	}
	
	/**
	 * Returns a copy of the contents of this resource wrapped in a list that
	 * propagates changes to the original resource list. Wrapping is required to make
	 * sure that clients which obtain a reference to the list of contents do not
	 * interfere when changing the list.
	 */
	public org.eclipse.emf.common.util.EList<org.eclipse.emf.ecore.EObject> getContents() {
		return new be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclCopiedEObjectInternalEList((org.eclipse.emf.ecore.util.InternalEList<org.eclipse.emf.ecore.EObject>) super.getContents());
	}
	
	/**
	 * Returns the raw contents of this resource.
	 */
	public org.eclipse.emf.common.util.EList<org.eclipse.emf.ecore.EObject> getContentsInternal() {
		return super.getContents();
	}
	
	public org.eclipse.emf.common.util.EList<org.eclipse.emf.ecore.resource.Resource.Diagnostic> getWarnings() {
		return new be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclCopiedEList<org.eclipse.emf.ecore.resource.Resource.Diagnostic>(super.getWarnings());
	}
	
	public org.eclipse.emf.common.util.EList<org.eclipse.emf.ecore.resource.Resource.Diagnostic> getErrors() {
		return new be.ac.vub.simpleocl.resource.simpleocl.util.SimpleoclCopiedEList<org.eclipse.emf.ecore.resource.Resource.Diagnostic>(super.getErrors());
	}
	
	@SuppressWarnings("restriction")	
	private void runValidators(org.eclipse.emf.ecore.EObject root) {
		// checking constraints provided by EMF validator classes was disabled
		
		// check EMF validation constraints
		// EMF validation does not work if OSGi is not running
		// The EMF validation framework code throws a NPE if the validation plug-in is not
		// loaded. This is a bug, which is fixed in the Helios release. Nonetheless, we
		// need to catch the exception here.
		if (org.eclipse.core.runtime.Platform.isRunning()) {
			// The EMF validation framework code throws a NPE if the validation plug-in is not
			// loaded. This is a workaround for bug 322079.
			if (org.eclipse.emf.validation.internal.EMFModelValidationPlugin.getPlugin() != null) {
				try {
					org.eclipse.emf.validation.service.ModelValidationService service = org.eclipse.emf.validation.service.ModelValidationService.getInstance();
					org.eclipse.emf.validation.service.IBatchValidator validator = service.<org.eclipse.emf.ecore.EObject, org.eclipse.emf.validation.service.IBatchValidator>newValidator(org.eclipse.emf.validation.model.EvaluationMode.BATCH);
					validator.setIncludeLiveConstraints(true);
					org.eclipse.core.runtime.IStatus status = validator.validate(root);
					addStatus(status, root);
				} catch (Throwable t) {
					be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclPlugin.logError("Exception while checking contraints provided by EMF validator classes.", t);
				}
			}
		}
	}
	
	private void addStatus(org.eclipse.core.runtime.IStatus status, org.eclipse.emf.ecore.EObject root) {
		java.util.List<org.eclipse.emf.ecore.EObject> causes = new java.util.ArrayList<org.eclipse.emf.ecore.EObject>();
		causes.add(root);
		if (status instanceof org.eclipse.emf.validation.model.ConstraintStatus) {
			org.eclipse.emf.validation.model.ConstraintStatus constraintStatus = (org.eclipse.emf.validation.model.ConstraintStatus) status;
			java.util.Set<org.eclipse.emf.ecore.EObject> resultLocus = constraintStatus.getResultLocus();
			causes.clear();
			causes.addAll(resultLocus);
		}
		boolean hasChildren = status.getChildren() != null && status.getChildren().length > 0;
		// Ignore composite status objects that have children. The actual status
		// information is then contained in the child objects.
		if (!status.isMultiStatus() || !hasChildren) {
			if (status.getSeverity() == org.eclipse.core.runtime.IStatus.ERROR) {
				for (org.eclipse.emf.ecore.EObject cause : causes) {
					addError(status.getMessage(), be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.ANALYSIS_PROBLEM, cause);
				}
			}
			if (status.getSeverity() == org.eclipse.core.runtime.IStatus.WARNING) {
				for (org.eclipse.emf.ecore.EObject cause : causes) {
					addWarning(status.getMessage(), be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType.ANALYSIS_PROBLEM, cause);
				}
			}
		}
		for (org.eclipse.core.runtime.IStatus child : status.getChildren()) {
			addStatus(child, root);
		}
	}
	
	public be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclQuickFix getQuickFix(String quickFixContext) {
		return quickFixMap.get(quickFixContext);
	}
	
	public boolean isMarkerCreationEnabled() {
		if (loadOptions == null) {
			return true;
		}
		return !loadOptions.containsKey(be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclOptions.DISABLE_CREATING_MARKERS_FOR_PROBLEMS);
	}
}
