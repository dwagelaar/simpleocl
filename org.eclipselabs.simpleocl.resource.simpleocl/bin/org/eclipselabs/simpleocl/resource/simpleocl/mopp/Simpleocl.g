grammar Simpleocl;

options {
	superClass = SimpleoclANTLRParserBase;
	backtrack = true;
	memoize = true;
}

@lexer::header {
	package org.eclipselabs.simpleocl.resource.simpleocl.mopp;
}

@lexer::members {
	public java.util.List<org.antlr.runtime3_4_0.RecognitionException> lexerExceptions  = new java.util.ArrayList<org.antlr.runtime3_4_0.RecognitionException>();
	public java.util.List<Integer> lexerExceptionsPosition = new java.util.ArrayList<Integer>();
	
	public void reportError(org.antlr.runtime3_4_0.RecognitionException e) {
		lexerExceptions.add(e);
		lexerExceptionsPosition.add(((org.antlr.runtime3_4_0.ANTLRStringStream) input).index());
	}
}
@header{
	package org.eclipselabs.simpleocl.resource.simpleocl.mopp;
}

@members{
	private org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolverFactory tokenResolverFactory = new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTokenResolverFactory();
	
	/**
	 * the index of the last token that was handled by collectHiddenTokens()
	 */
	private int lastPosition;
	
	/**
	 * A flag that indicates whether the parser should remember all expected elements.
	 * This flag is set to true when using the parse for code completion. Otherwise it
	 * is set to false.
	 */
	private boolean rememberExpectedElements = false;
	
	private Object parseToIndexTypeObject;
	private int lastTokenIndex = 0;
	
	/**
	 * A list of expected elements the were collected while parsing the input stream.
	 * This list is only filled if <code>rememberExpectedElements</code> is set to
	 * true.
	 */
	private java.util.List<org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal> expectedElements = new java.util.ArrayList<org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal>();
	
	private int mismatchedTokenRecoveryTries = 0;
	/**
	 * A helper list to allow a lexer to pass errors to its parser
	 */
	protected java.util.List<org.antlr.runtime3_4_0.RecognitionException> lexerExceptions = java.util.Collections.synchronizedList(new java.util.ArrayList<org.antlr.runtime3_4_0.RecognitionException>());
	
	/**
	 * Another helper list to allow a lexer to pass positions of errors to its parser
	 */
	protected java.util.List<Integer> lexerExceptionsPosition = java.util.Collections.synchronizedList(new java.util.ArrayList<Integer>());
	
	/**
	 * A stack for incomplete objects. This stack is used filled when the parser is
	 * used for code completion. Whenever the parser starts to read an object it is
	 * pushed on the stack. Once the element was parser completely it is popped from
	 * the stack.
	 */
	java.util.List<org.eclipse.emf.ecore.EObject> incompleteObjects = new java.util.ArrayList<org.eclipse.emf.ecore.EObject>();
	
	private int stopIncludingHiddenTokens;
	private int stopExcludingHiddenTokens;
	private int tokenIndexOfLastCompleteElement;
	
	private int expectedElementsIndexOfLastCompleteElement;
	
	/**
	 * The offset indicating the cursor position when the parser is used for code
	 * completion by calling parseToExpectedElements().
	 */
	private int cursorOffset;
	
	/**
	 * The offset of the first hidden token of the last expected element. This offset
	 * is used to discard expected elements, which are not needed for code completion.
	 */
	private int lastStartIncludingHidden;
	
	protected void addErrorToResource(final String errorMessage, final int column, final int line, final int startIndex, final int stopIndex) {
		postParseCommands.add(new org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclCommand<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource>() {
			public boolean execute(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource resource) {
				if (resource == null) {
					// the resource can be null if the parser is used for code completion
					return true;
				}
				resource.addProblem(new org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclProblem() {
					public org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity getSeverity() {
						return org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity.ERROR;
					}
					public org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemType getType() {
						return org.eclipselabs.simpleocl.resource.simpleocl.SimpleoclEProblemType.SYNTAX_ERROR;
					}
					public String getMessage() {
						return errorMessage;
					}
					public java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclQuickFix> getQuickFixes() {
						return null;
					}
				}, column, line, startIndex, stopIndex);
				return true;
			}
		});
	}
	
	public void addExpectedElement(org.eclipse.emf.ecore.EClass eClass, int[] ids) {
		if (!this.rememberExpectedElements) {
			return;
		}
		int terminalID = ids[0];
		int followSetID = ids[1];
		org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclExpectedElement terminal = org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclFollowSetProvider.TERMINALS[terminalID];
		org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[] containmentFeatures = new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[ids.length - 2];
		for (int i = 2; i < ids.length; i++) {
			containmentFeatures[i - 2] = org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclFollowSetProvider.LINKS[ids[i]];
		}
		org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclContainmentTrace containmentTrace = new org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclContainmentTrace(eClass, containmentFeatures);
		org.eclipse.emf.ecore.EObject container = getLastIncompleteElement();
		org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal expectedElement = new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal(container, terminal, followSetID, containmentTrace);
		setPosition(expectedElement, input.index());
		int startIncludingHiddenTokens = expectedElement.getStartIncludingHiddenTokens();
		if (lastStartIncludingHidden >= 0 && lastStartIncludingHidden < startIncludingHiddenTokens && cursorOffset > startIncludingHiddenTokens) {
			// clear list of expected elements
			this.expectedElements.clear();
			this.expectedElementsIndexOfLastCompleteElement = 0;
		}
		lastStartIncludingHidden = startIncludingHiddenTokens;
		this.expectedElements.add(expectedElement);
	}
	
	protected void collectHiddenTokens(org.eclipse.emf.ecore.EObject element) {
	}
	
	protected void copyLocalizationInfos(final org.eclipse.emf.ecore.EObject source, final org.eclipse.emf.ecore.EObject target) {
		if (disableLocationMap) {
			return;
		}
		postParseCommands.add(new org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclCommand<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource>() {
			public boolean execute(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource resource) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclLocationMap locationMap = resource.getLocationMap();
				if (locationMap == null) {
					// the locationMap can be null if the parser is used for code completion
					return true;
				}
				locationMap.setCharStart(target, locationMap.getCharStart(source));
				locationMap.setCharEnd(target, locationMap.getCharEnd(source));
				locationMap.setColumn(target, locationMap.getColumn(source));
				locationMap.setLine(target, locationMap.getLine(source));
				return true;
			}
		});
	}
	
	protected void copyLocalizationInfos(final org.antlr.runtime3_4_0.CommonToken source, final org.eclipse.emf.ecore.EObject target) {
		if (disableLocationMap) {
			return;
		}
		postParseCommands.add(new org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclCommand<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource>() {
			public boolean execute(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource resource) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclLocationMap locationMap = resource.getLocationMap();
				if (locationMap == null) {
					// the locationMap can be null if the parser is used for code completion
					return true;
				}
				if (source == null) {
					return true;
				}
				locationMap.setCharStart(target, source.getStartIndex());
				locationMap.setCharEnd(target, source.getStopIndex());
				locationMap.setColumn(target, source.getCharPositionInLine());
				locationMap.setLine(target, source.getLine());
				return true;
			}
		});
	}
	
	/**
	 * Sets the end character index and the last line for the given object in the
	 * location map.
	 */
	protected void setLocalizationEnd(java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclCommand<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource>> postParseCommands , final org.eclipse.emf.ecore.EObject object, final int endChar, final int endLine) {
		if (disableLocationMap) {
			return;
		}
		postParseCommands.add(new org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclCommand<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource>() {
			public boolean execute(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource resource) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclLocationMap locationMap = resource.getLocationMap();
				if (locationMap == null) {
					// the locationMap can be null if the parser is used for code completion
					return true;
				}
				locationMap.setCharEnd(object, endChar);
				locationMap.setLine(object, endLine);
				return true;
			}
		});
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextParser createInstance(java.io.InputStream actualInputStream, String encoding) {
		try {
			if (encoding == null) {
				return new SimpleoclParser(new org.antlr.runtime3_4_0.CommonTokenStream(new SimpleoclLexer(new org.antlr.runtime3_4_0.ANTLRInputStream(actualInputStream))));
			} else {
				return new SimpleoclParser(new org.antlr.runtime3_4_0.CommonTokenStream(new SimpleoclLexer(new org.antlr.runtime3_4_0.ANTLRInputStream(actualInputStream, encoding))));
			}
		} catch (java.io.IOException e) {
			new org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclRuntimeUtil().logError("Error while creating parser.", e);
			return null;
		}
	}
	
	/**
	 * This default constructor is only used to call createInstance() on it.
	 */
	public SimpleoclParser() {
		super(null);
	}
	
	protected org.eclipse.emf.ecore.EObject doParse() throws org.antlr.runtime3_4_0.RecognitionException {
		this.lastPosition = 0;
		// required because the lexer class can not be subclassed
		((SimpleoclLexer) getTokenStream().getTokenSource()).lexerExceptions = lexerExceptions;
		((SimpleoclLexer) getTokenStream().getTokenSource()).lexerExceptionsPosition = lexerExceptionsPosition;
		Object typeObject = getTypeObject();
		if (typeObject == null) {
			return start();
		} else if (typeObject instanceof org.eclipse.emf.ecore.EClass) {
			org.eclipse.emf.ecore.EClass type = (org.eclipse.emf.ecore.EClass) typeObject;
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.Module.class) {
				return parse_org_eclipselabs_simpleocl_Module();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.Import.class) {
				return parse_org_eclipselabs_simpleocl_Import();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.OclFeatureDefinition.class) {
				return parse_org_eclipselabs_simpleocl_OclFeatureDefinition();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.OclContextDefinition.class) {
				return parse_org_eclipselabs_simpleocl_OclContextDefinition();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.OclMetamodel.class) {
				return parse_org_eclipselabs_simpleocl_OclMetamodel();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.OclInstanceModel.class) {
				return parse_org_eclipselabs_simpleocl_OclInstanceModel();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.LocalVariable.class) {
				return parse_org_eclipselabs_simpleocl_LocalVariable();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.Attribute.class) {
				return parse_org_eclipselabs_simpleocl_Attribute();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.Operation.class) {
				return parse_org_eclipselabs_simpleocl_Operation();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.Parameter.class) {
				return parse_org_eclipselabs_simpleocl_Parameter();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.CollectionRange.class) {
				return parse_org_eclipselabs_simpleocl_CollectionRange();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.CollectionItem.class) {
				return parse_org_eclipselabs_simpleocl_CollectionItem();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.TuplePart.class) {
				return parse_org_eclipselabs_simpleocl_TuplePart();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.MapElement.class) {
				return parse_org_eclipselabs_simpleocl_MapElement();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.StaticOperationCall.class) {
				return parse_org_eclipselabs_simpleocl_StaticOperationCall();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.StaticNavigationOrAttributeCall.class) {
				return parse_org_eclipselabs_simpleocl_StaticNavigationOrAttributeCall();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.OperationCall.class) {
				return parse_org_eclipselabs_simpleocl_OperationCall();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.NavigationOrAttributeCall.class) {
				return parse_org_eclipselabs_simpleocl_NavigationOrAttributeCall();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.IterateExp.class) {
				return parse_org_eclipselabs_simpleocl_IterateExp();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.IteratorExp.class) {
				return parse_org_eclipselabs_simpleocl_IteratorExp();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.Iterator.class) {
				return parse_org_eclipselabs_simpleocl_Iterator();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.CollectionOperationCall.class) {
				return parse_org_eclipselabs_simpleocl_CollectionOperationCall();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.CollectionType.class) {
				return parse_org_eclipselabs_simpleocl_CollectionType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.StringType.class) {
				return parse_org_eclipselabs_simpleocl_StringType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.BooleanType.class) {
				return parse_org_eclipselabs_simpleocl_BooleanType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.IntegerType.class) {
				return parse_org_eclipselabs_simpleocl_IntegerType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.RealType.class) {
				return parse_org_eclipselabs_simpleocl_RealType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.BagType.class) {
				return parse_org_eclipselabs_simpleocl_BagType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.OrderedSetType.class) {
				return parse_org_eclipselabs_simpleocl_OrderedSetType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.SequenceType.class) {
				return parse_org_eclipselabs_simpleocl_SequenceType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.SetType.class) {
				return parse_org_eclipselabs_simpleocl_SetType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.OclAnyType.class) {
				return parse_org_eclipselabs_simpleocl_OclAnyType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.OclType.class) {
				return parse_org_eclipselabs_simpleocl_OclType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.TupleType.class) {
				return parse_org_eclipselabs_simpleocl_TupleType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.TupleTypeAttribute.class) {
				return parse_org_eclipselabs_simpleocl_TupleTypeAttribute();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.OclModelElement.class) {
				return parse_org_eclipselabs_simpleocl_OclModelElement();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.MapType.class) {
				return parse_org_eclipselabs_simpleocl_MapType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.LambdaType.class) {
				return parse_org_eclipselabs_simpleocl_LambdaType();
			}
			if (type.getInstanceClass() == org.eclipselabs.simpleocl.EnvType.class) {
				return parse_org_eclipselabs_simpleocl_EnvType();
			}
		}
		throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclUnexpectedContentTypeException(typeObject);
	}
	
	public int getMismatchedTokenRecoveryTries() {
		return mismatchedTokenRecoveryTries;
	}
	
	public Object getMissingSymbol(org.antlr.runtime3_4_0.IntStream arg0, org.antlr.runtime3_4_0.RecognitionException arg1, int arg2, org.antlr.runtime3_4_0.BitSet arg3) {
		mismatchedTokenRecoveryTries++;
		return super.getMissingSymbol(arg0, arg1, arg2, arg3);
	}
	
	public Object getParseToIndexTypeObject() {
		return parseToIndexTypeObject;
	}
	
	protected Object getTypeObject() {
		Object typeObject = getParseToIndexTypeObject();
		if (typeObject != null) {
			return typeObject;
		}
		java.util.Map<?,?> options = getOptions();
		if (options != null) {
			typeObject = options.get(org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclOptions.RESOURCE_CONTENT_TYPE);
		}
		return typeObject;
	}
	
	/**
	 * Implementation that calls {@link #doParse()} and handles the thrown
	 * RecognitionExceptions.
	 */
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclParseResult parse() {
		terminateParsing = false;
		postParseCommands = new java.util.ArrayList<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclCommand<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource>>();
		org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclParseResult parseResult = new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclParseResult();
		try {
			org.eclipse.emf.ecore.EObject result =  doParse();
			if (lexerExceptions.isEmpty()) {
				parseResult.setRoot(result);
			}
		} catch (org.antlr.runtime3_4_0.RecognitionException re) {
			reportError(re);
		} catch (java.lang.IllegalArgumentException iae) {
			if ("The 'no null' constraint is violated".equals(iae.getMessage())) {
				// can be caused if a null is set on EMF models where not allowed. this will just
				// happen if other errors occurred before
			} else {
				iae.printStackTrace();
			}
		}
		for (org.antlr.runtime3_4_0.RecognitionException re : lexerExceptions) {
			reportLexicalError(re);
		}
		parseResult.getPostParseCommands().addAll(postParseCommands);
		return parseResult;
	}
	
	public java.util.List<org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal> parseToExpectedElements(org.eclipse.emf.ecore.EClass type, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource dummyResource, int cursorOffset) {
		this.rememberExpectedElements = true;
		this.parseToIndexTypeObject = type;
		this.cursorOffset = cursorOffset;
		this.lastStartIncludingHidden = -1;
		final org.antlr.runtime3_4_0.CommonTokenStream tokenStream = (org.antlr.runtime3_4_0.CommonTokenStream) getTokenStream();
		org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclParseResult result = parse();
		for (org.eclipse.emf.ecore.EObject incompleteObject : incompleteObjects) {
			org.antlr.runtime3_4_0.Lexer lexer = (org.antlr.runtime3_4_0.Lexer) tokenStream.getTokenSource();
			int endChar = lexer.getCharIndex();
			int endLine = lexer.getLine();
			setLocalizationEnd(result.getPostParseCommands(), incompleteObject, endChar, endLine);
		}
		if (result != null) {
			org.eclipse.emf.ecore.EObject root = result.getRoot();
			if (root != null) {
				dummyResource.getContentsInternal().add(root);
			}
			for (org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclCommand<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTextResource> command : result.getPostParseCommands()) {
				command.execute(dummyResource);
			}
		}
		// remove all expected elements that were added after the last complete element
		expectedElements = expectedElements.subList(0, expectedElementsIndexOfLastCompleteElement + 1);
		int lastFollowSetID = expectedElements.get(expectedElementsIndexOfLastCompleteElement).getFollowSetID();
		java.util.Set<org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal> currentFollowSet = new java.util.LinkedHashSet<org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal>();
		java.util.List<org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal> newFollowSet = new java.util.ArrayList<org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal>();
		for (int i = expectedElementsIndexOfLastCompleteElement; i >= 0; i--) {
			org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal expectedElementI = expectedElements.get(i);
			if (expectedElementI.getFollowSetID() == lastFollowSetID) {
				currentFollowSet.add(expectedElementI);
			} else {
				break;
			}
		}
		int followSetID = 318;
		int i;
		for (i = tokenIndexOfLastCompleteElement; i < tokenStream.size(); i++) {
			org.antlr.runtime3_4_0.CommonToken nextToken = (org.antlr.runtime3_4_0.CommonToken) tokenStream.get(i);
			if (nextToken.getType() < 0) {
				break;
			}
			if (nextToken.getChannel() == 99) {
				// hidden tokens do not reduce the follow set
			} else {
				// now that we have found the next visible token the position for that expected
				// terminals can be set
				for (org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal nextFollow : newFollowSet) {
					lastTokenIndex = 0;
					setPosition(nextFollow, i);
				}
				newFollowSet.clear();
				// normal tokens do reduce the follow set - only elements that match the token are
				// kept
				for (org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal nextFollow : currentFollowSet) {
					if (nextFollow.getTerminal().getTokenNames().contains(getTokenNames()[nextToken.getType()])) {
						// keep this one - it matches
						java.util.Collection<org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclPair<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclExpectedElement, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[]>> newFollowers = nextFollow.getTerminal().getFollowers();
						for (org.eclipselabs.simpleocl.resource.simpleocl.util.SimpleoclPair<org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclExpectedElement, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContainedFeature[]> newFollowerPair : newFollowers) {
							org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclExpectedElement newFollower = newFollowerPair.getLeft();
							org.eclipse.emf.ecore.EObject container = getLastIncompleteElement();
							org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclContainmentTrace containmentTrace = new org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclContainmentTrace(null, newFollowerPair.getRight());
							org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal newFollowTerminal = new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal(container, newFollower, followSetID, containmentTrace);
							newFollowSet.add(newFollowTerminal);
							expectedElements.add(newFollowTerminal);
						}
					}
				}
				currentFollowSet.clear();
				currentFollowSet.addAll(newFollowSet);
			}
			followSetID++;
		}
		// after the last token in the stream we must set the position for the elements
		// that were added during the last iteration of the loop
		for (org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal nextFollow : newFollowSet) {
			lastTokenIndex = 0;
			setPosition(nextFollow, i);
		}
		return this.expectedElements;
	}
	
	public void setPosition(org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectedTerminal expectedElement, int tokenIndex) {
		int currentIndex = Math.max(0, tokenIndex);
		for (int index = lastTokenIndex; index < currentIndex; index++) {
			if (index >= input.size()) {
				break;
			}
			org.antlr.runtime3_4_0.CommonToken tokenAtIndex = (org.antlr.runtime3_4_0.CommonToken) input.get(index);
			stopIncludingHiddenTokens = tokenAtIndex.getStopIndex() + 1;
			if (tokenAtIndex.getChannel() != 99 && !anonymousTokens.contains(tokenAtIndex)) {
				stopExcludingHiddenTokens = tokenAtIndex.getStopIndex() + 1;
			}
		}
		lastTokenIndex = Math.max(0, currentIndex);
		expectedElement.setPosition(stopExcludingHiddenTokens, stopIncludingHiddenTokens);
	}
	
	public Object recoverFromMismatchedToken(org.antlr.runtime3_4_0.IntStream input, int ttype, org.antlr.runtime3_4_0.BitSet follow) throws org.antlr.runtime3_4_0.RecognitionException {
		if (!rememberExpectedElements) {
			return super.recoverFromMismatchedToken(input, ttype, follow);
		} else {
			return null;
		}
	}
	
	/**
	 * Translates errors thrown by the parser into human readable messages.
	 */
	public void reportError(final org.antlr.runtime3_4_0.RecognitionException e)  {
		String message = e.getMessage();
		if (e instanceof org.antlr.runtime3_4_0.MismatchedTokenException) {
			org.antlr.runtime3_4_0.MismatchedTokenException mte = (org.antlr.runtime3_4_0.MismatchedTokenException) e;
			String expectedTokenName = formatTokenName(mte.expecting);
			String actualTokenName = formatTokenName(e.token.getType());
			message = "Syntax error on token \"" + e.token.getText() + " (" + actualTokenName + ")\", \"" + expectedTokenName + "\" expected";
		} else if (e instanceof org.antlr.runtime3_4_0.MismatchedTreeNodeException) {
			org.antlr.runtime3_4_0.MismatchedTreeNodeException mtne = (org.antlr.runtime3_4_0.MismatchedTreeNodeException) e;
			String expectedTokenName = formatTokenName(mtne.expecting);
			message = "mismatched tree node: " + "xxx" + "; tokenName " + expectedTokenName;
		} else if (e instanceof org.antlr.runtime3_4_0.NoViableAltException) {
			message = "Syntax error on token \"" + e.token.getText() + "\", check following tokens";
		} else if (e instanceof org.antlr.runtime3_4_0.EarlyExitException) {
			message = "Syntax error on token \"" + e.token.getText() + "\", delete this token";
		} else if (e instanceof org.antlr.runtime3_4_0.MismatchedSetException) {
			org.antlr.runtime3_4_0.MismatchedSetException mse = (org.antlr.runtime3_4_0.MismatchedSetException) e;
			message = "mismatched token: " + e.token + "; expecting set " + mse.expecting;
		} else if (e instanceof org.antlr.runtime3_4_0.MismatchedNotSetException) {
			org.antlr.runtime3_4_0.MismatchedNotSetException mse = (org.antlr.runtime3_4_0.MismatchedNotSetException) e;
			message = "mismatched token: " +  e.token + "; expecting set " + mse.expecting;
		} else if (e instanceof org.antlr.runtime3_4_0.FailedPredicateException) {
			org.antlr.runtime3_4_0.FailedPredicateException fpe = (org.antlr.runtime3_4_0.FailedPredicateException) e;
			message = "rule " + fpe.ruleName + " failed predicate: {" +  fpe.predicateText + "}?";
		}
		// the resource may be null if the parser is used for code completion
		final String finalMessage = message;
		if (e.token instanceof org.antlr.runtime3_4_0.CommonToken) {
			final org.antlr.runtime3_4_0.CommonToken ct = (org.antlr.runtime3_4_0.CommonToken) e.token;
			addErrorToResource(finalMessage, ct.getCharPositionInLine(), ct.getLine(), ct.getStartIndex(), ct.getStopIndex());
		} else {
			addErrorToResource(finalMessage, e.token.getCharPositionInLine(), e.token.getLine(), 1, 5);
		}
	}
	
	/**
	 * Translates errors thrown by the lexer into human readable messages.
	 */
	public void reportLexicalError(final org.antlr.runtime3_4_0.RecognitionException e)  {
		String message = "";
		if (e instanceof org.antlr.runtime3_4_0.MismatchedTokenException) {
			org.antlr.runtime3_4_0.MismatchedTokenException mte = (org.antlr.runtime3_4_0.MismatchedTokenException) e;
			message = "Syntax error on token \"" + ((char) e.c) + "\", \"" + (char) mte.expecting + "\" expected";
		} else if (e instanceof org.antlr.runtime3_4_0.NoViableAltException) {
			message = "Syntax error on token \"" + ((char) e.c) + "\", delete this token";
		} else if (e instanceof org.antlr.runtime3_4_0.EarlyExitException) {
			org.antlr.runtime3_4_0.EarlyExitException eee = (org.antlr.runtime3_4_0.EarlyExitException) e;
			message = "required (...)+ loop (decision=" + eee.decisionNumber + ") did not match anything; on line " + e.line + ":" + e.charPositionInLine + " char=" + ((char) e.c) + "'";
		} else if (e instanceof org.antlr.runtime3_4_0.MismatchedSetException) {
			org.antlr.runtime3_4_0.MismatchedSetException mse = (org.antlr.runtime3_4_0.MismatchedSetException) e;
			message = "mismatched char: '" + ((char) e.c) + "' on line " + e.line + ":" + e.charPositionInLine + "; expecting set " + mse.expecting;
		} else if (e instanceof org.antlr.runtime3_4_0.MismatchedNotSetException) {
			org.antlr.runtime3_4_0.MismatchedNotSetException mse = (org.antlr.runtime3_4_0.MismatchedNotSetException) e;
			message = "mismatched char: '" + ((char) e.c) + "' on line " + e.line + ":" + e.charPositionInLine + "; expecting set " + mse.expecting;
		} else if (e instanceof org.antlr.runtime3_4_0.MismatchedRangeException) {
			org.antlr.runtime3_4_0.MismatchedRangeException mre = (org.antlr.runtime3_4_0.MismatchedRangeException) e;
			message = "mismatched char: '" + ((char) e.c) + "' on line " + e.line + ":" + e.charPositionInLine + "; expecting set '" + (char) mre.a + "'..'" + (char) mre.b + "'";
		} else if (e instanceof org.antlr.runtime3_4_0.FailedPredicateException) {
			org.antlr.runtime3_4_0.FailedPredicateException fpe = (org.antlr.runtime3_4_0.FailedPredicateException) e;
			message = "rule " + fpe.ruleName + " failed predicate: {" + fpe.predicateText + "}?";
		}
		addErrorToResource(message, e.charPositionInLine, e.line, lexerExceptionsPosition.get(lexerExceptions.indexOf(e)), lexerExceptionsPosition.get(lexerExceptions.indexOf(e)));
	}
	
	private void startIncompleteElement(Object object) {
		if (object instanceof org.eclipse.emf.ecore.EObject) {
			this.incompleteObjects.add((org.eclipse.emf.ecore.EObject) object);
		}
	}
	
	private void completedElement(Object object, boolean isContainment) {
		if (isContainment && !this.incompleteObjects.isEmpty()) {
			boolean exists = this.incompleteObjects.remove(object);
			if (!exists) {
			}
		}
		if (object instanceof org.eclipse.emf.ecore.EObject) {
			this.tokenIndexOfLastCompleteElement = getTokenStream().index();
			this.expectedElementsIndexOfLastCompleteElement = expectedElements.size() - 1;
		}
	}
	
	private org.eclipse.emf.ecore.EObject getLastIncompleteElement() {
		if (incompleteObjects.isEmpty()) {
			return null;
		}
		return incompleteObjects.get(incompleteObjects.size() - 1);
	}
	
}

start returns [ org.eclipse.emf.ecore.EObject element = null]
:
	{
		// follow set for start rule(s)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[0]);
		expectedElementsIndexOfLastCompleteElement = 0;
	}
	(
		c0 = parse_org_eclipselabs_simpleocl_Module{ element = c0; }
	)
	EOF	{
		retrieveLayoutInformation(element, null, null, false);
	}
	
;

parse_org_eclipselabs_simpleocl_Module returns [org.eclipselabs.simpleocl.Module element = null]
@init{
}
:
	a0 = 'module' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createModule();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_0_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2]);
	}
	
	(
		(
			a1 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createModule();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MODULE__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MODULE__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_0_0_0_1_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3]);
		}
		
		
		|		(
			a2 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createModule();
					startIncompleteElement(element);
				}
				if (a2 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MODULE__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a2).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MODULE__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_0_0_0_1_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a2, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[5]);
	}
	
	a3 = ';' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createModule();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_0_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[6]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[7]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[8]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[9]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[10]);
	}
	
	(
		(
			(
				(
					a4_0 = parse_org_eclipselabs_simpleocl_OclMetamodel					{
						if (terminateParsing) {
							throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
						}
						if (element == null) {
							element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createModule();
							startIncompleteElement(element);
						}
						if (a4_0 != null) {
							if (a4_0 != null) {
								Object value = a4_0;
								addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.MODULE__METAMODELS, value);
								completedElement(value, true);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_0_0_0_3_0_0_1, a4_0, true);
							copyLocalizationInfos(a4_0, element);
						}
					}
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[11]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[12]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[13]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[14]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[15]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[16]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[17]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[18]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[19]);
	}
	
	(
		(
			(
				(
					a5_0 = parse_org_eclipselabs_simpleocl_Import					{
						if (terminateParsing) {
							throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
						}
						if (element == null) {
							element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createModule();
							startIncompleteElement(element);
						}
						if (a5_0 != null) {
							if (a5_0 != null) {
								Object value = a5_0;
								addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.MODULE__IMPORTS, value);
								completedElement(value, true);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_0_0_0_4_0_0_1, a5_0, true);
							copyLocalizationInfos(a5_0, element);
						}
					}
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[20]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[21]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[22]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[23]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[24]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[25]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[26]);
	}
	
	(
		(
			(
				(
					a6_0 = parse_org_eclipselabs_simpleocl_ModuleElement					{
						if (terminateParsing) {
							throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
						}
						if (element == null) {
							element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createModule();
							startIncompleteElement(element);
						}
						if (a6_0 != null) {
							if (a6_0 != null) {
								Object value = a6_0;
								addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.MODULE__ELEMENTS, value);
								completedElement(value, true);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_0_0_0_5_0_0_1, a6_0, true);
							copyLocalizationInfos(a6_0, element);
						}
					}
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[27]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[28]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[29]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
	}
	
;

parse_org_eclipselabs_simpleocl_Import returns [org.eclipselabs.simpleocl.Import element = null]
@init{
}
:
	a0 = 'import' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createImport();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_1_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[30]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[31]);
	}
	
	(
		(
			a1 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createImport();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.IMPORT__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.IMPORT__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_1_0_0_1_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[32]);
		}
		
		
		|		(
			a2 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createImport();
					startIncompleteElement(element);
				}
				if (a2 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.IMPORT__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a2).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.IMPORT__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_1_0_0_1_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a2, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[33]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[34]);
	}
	
	a3 = ';' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createImport();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_1_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[35]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[36]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[37]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[38]);
	}
	
;

parse_org_eclipselabs_simpleocl_OclFeatureDefinition returns [org.eclipselabs.simpleocl.OclFeatureDefinition element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_eclipselabs_simpleocl_OclContextDefinition			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclFeatureDefinition();
					startIncompleteElement(element);
					// initialize boolean attribute
					{
						Object value = false;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__STATIC), value);
					}
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__CONTEXT_), value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_2_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[39]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[40]);
	}
	
	(
		(
			a1 = 'static' {
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclFeatureDefinition();
					startIncompleteElement(element);
					// initialize boolean attribute
					{
						Object value = false;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__STATIC), value);
					}
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_2_0_0_1, true, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
				// set value of boolean attribute
				Object value = true;
				element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__STATIC), value);
				completedElement(value, false);
			}
		)?	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[41]);
	}
	
	a4 = 'def' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclFeatureDefinition();
			startIncompleteElement(element);
			// initialize boolean attribute
			{
				Object value = false;
				element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__STATIC), value);
			}
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_2_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[42]);
	}
	
	a5 = ':' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclFeatureDefinition();
			startIncompleteElement(element);
			// initialize boolean attribute
			{
				Object value = false;
				element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__STATIC), value);
			}
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_2_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclFeatureDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[43]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclFeatureDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[44]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclFeatureDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[45]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclFeatureDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[46]);
	}
	
	(
		a6_0 = parse_org_eclipselabs_simpleocl_OclFeature		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclFeatureDefinition();
				startIncompleteElement(element);
				// initialize boolean attribute
				{
					Object value = false;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__STATIC), value);
				}
			}
			if (a6_0 != null) {
				if (a6_0 != null) {
					Object value = a6_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_FEATURE_DEFINITION__FEATURE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_2_0_0_4, a6_0, true);
				copyLocalizationInfos(a6_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[47]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[48]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[49]);
	}
	
;

parse_org_eclipselabs_simpleocl_OclContextDefinition returns [org.eclipselabs.simpleocl.OclContextDefinition element = null]
@init{
}
:
	a0 = 'context' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclContextDefinition();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_3_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[50]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[51]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[52]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[53]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[54]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[55]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[56]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[57]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[58]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[59]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[60]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[61]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[62]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[63]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[64]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[65]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[66]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOclContextDefinition(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[67]);
	}
	
	(
		a1_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclContextDefinition();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_CONTEXT_DEFINITION__CONTEXT_), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_3_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[68]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[69]);
	}
	
;

parse_org_eclipselabs_simpleocl_OclMetamodel returns [org.eclipselabs.simpleocl.OclMetamodel element = null]
@init{
}
:
	a0 = 'metamodel' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclMetamodel();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_4_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[70]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[71]);
	}
	
	(
		(
			a1 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclMetamodel();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_METAMODEL__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_METAMODEL__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_4_0_0_1_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[72]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[73]);
		}
		
		
		|		(
			a2 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclMetamodel();
					startIncompleteElement(element);
				}
				if (a2 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_METAMODEL__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a2).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_METAMODEL__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_4_0_0_1_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a2, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[74]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[75]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[76]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[77]);
	}
	
	(
		(
			a3 = ':' {
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclMetamodel();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_4_0_0_2_0_0_0, null, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[78]);
			}
			
			(
				a4 = QUOTED_39_39_92				
				{
					if (terminateParsing) {
						throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
					}
					if (element == null) {
						element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclMetamodel();
						startIncompleteElement(element);
					}
					if (a4 != null) {
						org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_39_39_92");
						tokenResolver.setOptions(getOptions());
						org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_METAMODEL__URI), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a4).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStopIndex());
						}
						java.lang.String resolved = (java.lang.String) resolvedObject;
						if (resolved != null) {
							Object value = resolved;
							element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_METAMODEL__URI), value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_4_0_0_2_0_0_1, resolved, true);
						copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a4, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[79]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[80]);
	}
	
	a5 = ';' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclMetamodel();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_4_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[81]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[82]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[83]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[84]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[85]);
	}
	
;

parse_org_eclipselabs_simpleocl_OclInstanceModel returns [org.eclipselabs.simpleocl.OclInstanceModel element = null]
@init{
}
:
	(
		(
			a0 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclInstanceModel();
					startIncompleteElement(element);
				}
				if (a0 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_5_0_0_0_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[86]);
		}
		
		
		|		(
			a1 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclInstanceModel();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_5_0_0_0_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[87]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[88]);
	}
	
	a2 = ':' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclInstanceModel();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_5_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[89]);
	}
	
	(
		a3 = TEXT		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclInstanceModel();
				startIncompleteElement(element);
			}
			if (a3 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a3).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a3).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a3).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a3).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.eclipselabs.simpleocl.OclMetamodel proxy = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclMetamodel();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContextDependentURIFragmentFactory<org.eclipselabs.simpleocl.OclInstanceModel, org.eclipselabs.simpleocl.OclMetamodel>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOclInstanceModelMetamodelReferenceResolver()), element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_INSTANCE_MODEL__METAMODEL), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_5_0_0_2, proxy, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a3, element);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a3, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
	}
	
;

parse_org_eclipselabs_simpleocl_LocalVariable returns [org.eclipselabs.simpleocl.LocalVariable element = null]
@init{
}
:
	(
		a0 = TEXT		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLocalVariable();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__VAR_NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__VAR_NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_6_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[90]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[91]);
	}
	
	(
		(
			a1 = ':' {
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLocalVariable();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_6_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[92]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[93]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[94]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[95]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[96]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[97]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[98]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[99]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[100]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[101]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[102]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[103]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[104]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[105]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[106]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[107]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[108]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[109]);
			}
			
			(
				a2_0 = parse_org_eclipselabs_simpleocl_OclType				{
					if (terminateParsing) {
						throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
					}
					if (element == null) {
						element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLocalVariable();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__TYPE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_6_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[110]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[111]);
	}
	
	(
		a3 = EQ		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLocalVariable();
				startIncompleteElement(element);
			}
			if (a3 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("EQ");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__EQ), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a3).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a3).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a3).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a3).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__EQ), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_6_0_0_2, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a3, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[112]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[113]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[114]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[115]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[116]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[117]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[118]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[119]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[120]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[121]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[122]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[123]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[124]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[125]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[126]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[127]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[128]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[129]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[130]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[131]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[132]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[133]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[134]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[135]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[136]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[137]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[138]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[139]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[140]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[141]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[142]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[143]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[144]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[145]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[146]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[147]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[148]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[149]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[150]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLocalVariable(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[151]);
	}
	
	(
		a4_0 = parse_org_eclipselabs_simpleocl_OclExpression		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLocalVariable();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LOCAL_VARIABLE__INIT_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_6_0_0_3, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[152]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[153]);
	}
	
	|//derived choice rules for sub-classes: 
	
	c0 = parse_org_eclipselabs_simpleocl_TuplePart{ element = c0; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_eclipselabs_simpleocl_Attribute returns [org.eclipselabs.simpleocl.Attribute element = null]
@init{
}
:
	(
		(
			a0 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createAttribute();
					startIncompleteElement(element);
				}
				if (a0 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ATTRIBUTE__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ATTRIBUTE__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_7_0_0_0_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[154]);
		}
		
		
		|		(
			a1 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createAttribute();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ATTRIBUTE__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ATTRIBUTE__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_7_0_0_0_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[155]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[156]);
	}
	
	a2 = ':' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createAttribute();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_7_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[157]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[158]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[159]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[160]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[161]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[162]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[163]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[164]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[165]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[166]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[167]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[168]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[169]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[170]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[171]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[172]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[173]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[174]);
	}
	
	(
		a3_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createAttribute();
				startIncompleteElement(element);
			}
			if (a3_0 != null) {
				if (a3_0 != null) {
					Object value = a3_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ATTRIBUTE__TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_7_0_0_2, a3_0, true);
				copyLocalizationInfos(a3_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[175]);
	}
	
	(
		a4 = EQ		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createAttribute();
				startIncompleteElement(element);
			}
			if (a4 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("EQ");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ATTRIBUTE__EQ), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a4).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ATTRIBUTE__EQ), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_7_0_0_3, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a4, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[176]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[177]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[178]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[179]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[180]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[181]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[182]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[183]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[184]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[185]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[186]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[187]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[188]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[189]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[190]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[191]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[192]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[193]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[194]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[195]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[196]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[197]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[198]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[199]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[200]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[201]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[202]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[203]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[204]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[205]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[206]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[207]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[208]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[209]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[210]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[211]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[212]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[213]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[214]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[215]);
	}
	
	(
		a5_0 = parse_org_eclipselabs_simpleocl_OclExpression		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createAttribute();
				startIncompleteElement(element);
			}
			if (a5_0 != null) {
				if (a5_0 != null) {
					Object value = a5_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ATTRIBUTE__INIT_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_7_0_0_5, a5_0, true);
				copyLocalizationInfos(a5_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[216]);
	}
	
	a6 = ';' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createAttribute();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_7_0_0_6, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[217]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[218]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[219]);
	}
	
;

parse_org_eclipselabs_simpleocl_Operation returns [org.eclipselabs.simpleocl.Operation element = null]
@init{
}
:
	(
		(
			a0 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperation();
					startIncompleteElement(element);
				}
				if (a0 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_8_0_0_0_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[220]);
		}
		
		
		|		(
			a1 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperation();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_8_0_0_0_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[221]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[222]);
	}
	
	a2 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperation();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_8_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[223]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[224]);
	}
	
	(
		(
			(
				a3_0 = parse_org_eclipselabs_simpleocl_Parameter				{
					if (terminateParsing) {
						throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
					}
					if (element == null) {
						element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperation();
						startIncompleteElement(element);
					}
					if (a3_0 != null) {
						if (a3_0 != null) {
							Object value = a3_0;
							addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION__PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_8_0_0_2_0_0_0, a3_0, true);
						copyLocalizationInfos(a3_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[225]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[226]);
			}
			
			(
				(
					a4 = ',' {
						if (element == null) {
							element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperation();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_8_0_0_2_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a4, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[227]);
					}
					
					(
						a5_0 = parse_org_eclipselabs_simpleocl_Parameter						{
							if (terminateParsing) {
								throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
							}
							if (element == null) {
								element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperation();
								startIncompleteElement(element);
							}
							if (a5_0 != null) {
								if (a5_0 != null) {
									Object value = a5_0;
									addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION__PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_8_0_0_2_0_0_1_0_0_1, a5_0, true);
								copyLocalizationInfos(a5_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[228]);
						addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[229]);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[230]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[231]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[232]);
	}
	
	a6 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperation();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_8_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[233]);
	}
	
	a7 = ':' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperation();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_8_0_0_4, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[234]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[235]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[236]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[237]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[238]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[239]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[240]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[241]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[242]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[243]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[244]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[245]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[246]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[247]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[248]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[249]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[250]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[251]);
	}
	
	(
		a8_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperation();
				startIncompleteElement(element);
			}
			if (a8_0 != null) {
				if (a8_0 != null) {
					Object value = a8_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION__RETURN_TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_8_0_0_5, a8_0, true);
				copyLocalizationInfos(a8_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[252]);
	}
	
	(
		a9 = EQ		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperation();
				startIncompleteElement(element);
			}
			if (a9 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("EQ");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a9.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION__EQ), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a9).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a9).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a9).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a9).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION__EQ), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_8_0_0_6, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a9, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[253]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[254]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[255]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[256]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[257]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[258]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[259]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[260]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[261]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[262]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[263]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[264]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[265]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[266]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[267]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[268]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[269]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[270]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[271]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[272]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[273]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[274]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[275]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[276]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[277]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[278]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[279]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[280]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[281]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[282]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[283]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[284]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[285]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[286]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[287]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[288]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[289]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[290]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[291]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperation(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[292]);
	}
	
	(
		a10_0 = parse_org_eclipselabs_simpleocl_OclExpression		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperation();
				startIncompleteElement(element);
			}
			if (a10_0 != null) {
				if (a10_0 != null) {
					Object value = a10_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION__BODY), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_8_0_0_8, a10_0, true);
				copyLocalizationInfos(a10_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[293]);
	}
	
	a11 = ';' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperation();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_8_0_0_9, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a11, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[294]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[295]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getModule(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[296]);
	}
	
;

parse_org_eclipselabs_simpleocl_Parameter returns [org.eclipselabs.simpleocl.Parameter element = null]
@init{
}
:
	(
		a0 = TEXT		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createParameter();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.PARAMETER__VAR_NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.PARAMETER__VAR_NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_9_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[297]);
	}
	
	a1 = ':' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createParameter();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_9_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[298]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[299]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[300]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[301]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[302]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[303]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[304]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[305]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[306]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[307]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[308]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[309]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[310]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[311]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[312]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[313]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[314]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getParameter(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[315]);
	}
	
	(
		a2_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createParameter();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.PARAMETER__TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_9_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[316]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[317]);
	}
	
;

parse_org_eclipselabs_simpleocl_CollectionRange returns [org.eclipselabs.simpleocl.CollectionRange element = null]
@init{
}
:
	(
		a0_0 = parse_org_eclipselabs_simpleocl_OclExpression		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionRange();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.COLLECTION_RANGE__FIRST), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_20_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[318]);
	}
	
	a1 = '..' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionRange();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_20_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[319]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[320]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[321]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[322]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[323]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[324]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[325]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[326]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[327]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[328]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[329]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[330]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[331]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[332]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[333]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[334]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[335]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[336]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[337]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[338]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[339]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[340]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[341]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[342]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[343]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[344]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[345]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[346]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[347]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[348]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[349]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[350]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[351]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[352]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[353]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[354]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[355]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[356]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[357]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionRange(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[358]);
	}
	
	(
		a2_0 = parse_org_eclipselabs_simpleocl_OclExpression		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionRange();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.COLLECTION_RANGE__LAST), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_20_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[359]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[360]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[361]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[362]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[363]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[364]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[365]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[366]);
	}
	
;

parse_org_eclipselabs_simpleocl_CollectionItem returns [org.eclipselabs.simpleocl.CollectionItem element = null]
@init{
}
:
	(
		a0_0 = parse_org_eclipselabs_simpleocl_OclExpression		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionItem();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.COLLECTION_ITEM__ITEM), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_21_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[367]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[368]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[369]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[370]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[371]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[372]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[373]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[374]);
	}
	
;

parse_org_eclipselabs_simpleocl_TuplePart returns [org.eclipselabs.simpleocl.TuplePart element = null]
@init{
}
:
	(
		a0 = TEXT		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTuplePart();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_PART__VAR_NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_PART__VAR_NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_27_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[375]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[376]);
	}
	
	(
		(
			a1 = ':' {
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTuplePart();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_27_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[377]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[378]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[379]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[380]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[381]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[382]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[383]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[384]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[385]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[386]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[387]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[388]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[389]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[390]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[391]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[392]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[393]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[394]);
			}
			
			(
				a2_0 = parse_org_eclipselabs_simpleocl_OclType				{
					if (terminateParsing) {
						throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
					}
					if (element == null) {
						element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTuplePart();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_PART__TYPE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_27_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[395]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[396]);
	}
	
	(
		a3 = EQ		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTuplePart();
				startIncompleteElement(element);
			}
			if (a3 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("EQ");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_PART__EQ), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a3).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a3).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a3).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a3).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_PART__EQ), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_27_0_0_2, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a3, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[397]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[398]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[399]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[400]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[401]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[402]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[403]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[404]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[405]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[406]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[407]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[408]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[409]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[410]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[411]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[412]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[413]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[414]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[415]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[416]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[417]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[418]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[419]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[420]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[421]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[422]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[423]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[424]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[425]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[426]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[427]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[428]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[429]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[430]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[431]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[432]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[433]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[434]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[435]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTuplePart(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[436]);
	}
	
	(
		a4_0 = parse_org_eclipselabs_simpleocl_OclExpression		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTuplePart();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_PART__INIT_EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_27_0_0_3, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[437]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[438]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[439]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[440]);
	}
	
;

parse_org_eclipselabs_simpleocl_MapElement returns [org.eclipselabs.simpleocl.MapElement element = null]
@init{
}
:
	a0 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapElement();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_29_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[441]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[442]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[443]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[444]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[445]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[446]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[447]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[448]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[449]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[450]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[451]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[452]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[453]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[454]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[455]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[456]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[457]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[458]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[459]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[460]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[461]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[462]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[463]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[464]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[465]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[466]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[467]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[468]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[469]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[470]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[471]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[472]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[473]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[474]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[475]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[476]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[477]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[478]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[479]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[480]);
	}
	
	(
		a1_0 = parse_org_eclipselabs_simpleocl_OclExpression		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapElement();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MAP_ELEMENT__KEY), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_29_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[481]);
	}
	
	a2 = ',' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapElement();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_29_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[482]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[483]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[484]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[485]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[486]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[487]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[488]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[489]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[490]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[491]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[492]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[493]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[494]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[495]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[496]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[497]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[498]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[499]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[500]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[501]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[502]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[503]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[504]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[505]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[506]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[507]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[508]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[509]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[510]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[511]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[512]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[513]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[514]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[515]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[516]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[517]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[518]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[519]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[520]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapElement(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[521]);
	}
	
	(
		a3_0 = parse_org_eclipselabs_simpleocl_OclExpression		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapElement();
				startIncompleteElement(element);
			}
			if (a3_0 != null) {
				if (a3_0 != null) {
					Object value = a3_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MAP_ELEMENT__VALUE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_29_0_0_3, a3_0, true);
				copyLocalizationInfos(a3_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[522]);
	}
	
	a4 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapElement();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_29_0_0_4, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[523]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[524]);
	}
	
;

parse_org_eclipselabs_simpleocl_StaticOperationCall returns [org.eclipselabs.simpleocl.StaticOperationCall element = null]
@init{
}
:
	a0 = '::' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticOperationCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_43_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[525]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[526]);
	}
	
	(
		(
			a1 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticOperationCall();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_43_0_0_1_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[527]);
		}
		
		
		|		(
			a2 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticOperationCall();
					startIncompleteElement(element);
				}
				if (a2 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a2).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_43_0_0_1_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a2, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[528]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[529]);
	}
	
	a3 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticOperationCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_43_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[530]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[531]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[532]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[533]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[534]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[535]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[536]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[537]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[538]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[539]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[540]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[541]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[542]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[543]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[544]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[545]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[546]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[547]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[548]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[549]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[550]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[551]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[552]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[553]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[554]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[555]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[556]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[557]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[558]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[559]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[560]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[561]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[562]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[563]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[564]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[565]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[566]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[567]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[568]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[569]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[570]);
	}
	
	(
		(
			(
				a4_0 = parse_org_eclipselabs_simpleocl_OclExpression				{
					if (terminateParsing) {
						throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
					}
					if (element == null) {
						element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticOperationCall();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_43_0_0_3_0_0_0, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[571]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[572]);
			}
			
			(
				(
					a5 = ',' {
						if (element == null) {
							element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticOperationCall();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_43_0_0_3_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[573]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[574]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[575]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[576]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[577]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[578]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[579]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[580]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[581]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[582]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[583]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[584]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[585]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[586]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[587]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[588]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[589]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[590]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[591]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[592]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[593]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[594]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[595]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[596]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[597]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[598]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[599]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[600]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[601]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[602]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[603]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[604]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[605]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[606]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[607]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[608]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[609]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[610]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[611]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[612]);
					}
					
					(
						a6_0 = parse_org_eclipselabs_simpleocl_OclExpression						{
							if (terminateParsing) {
								throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
							}
							if (element == null) {
								element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticOperationCall();
								startIncompleteElement(element);
							}
							if (a6_0 != null) {
								if (a6_0 != null) {
									Object value = a6_0;
									addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.STATIC_OPERATION_CALL__ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_43_0_0_3_0_0_1_0_0_1, a6_0, true);
								copyLocalizationInfos(a6_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[613]);
						addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[614]);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[615]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[616]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[617]);
	}
	
	a7 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticOperationCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_43_0_0_4, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[618]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[619]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[620]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[621]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[622]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[623]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[624]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[625]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[626]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[627]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[628]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[629]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[630]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[631]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[632]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[633]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[634]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[635]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[636]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[637]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[638]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[639]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[640]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[641]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[642]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[643]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[644]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[645]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[646]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[647]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[648]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[649]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[650]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[651]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[652]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[653]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[654]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[655]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[656]);
	}
	
;

parse_org_eclipselabs_simpleocl_StaticNavigationOrAttributeCall returns [org.eclipselabs.simpleocl.StaticNavigationOrAttributeCall element = null]
@init{
}
:
	a0 = '::' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticNavigationOrAttributeCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_44_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[657]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[658]);
	}
	
	(
		(
			a1 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticNavigationOrAttributeCall();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STATIC_NAVIGATION_OR_ATTRIBUTE_CALL__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STATIC_NAVIGATION_OR_ATTRIBUTE_CALL__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_44_0_0_1_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[659]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[660]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[661]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[662]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[663]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[664]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[665]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[666]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[667]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[668]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[669]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[670]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[671]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[672]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[673]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[674]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[675]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[676]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[677]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[678]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[679]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[680]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[681]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[682]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[683]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[684]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[685]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[686]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[687]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[688]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[689]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[690]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[691]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[692]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[693]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[694]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[695]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[696]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[697]);
		}
		
		
		|		(
			a2 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticNavigationOrAttributeCall();
					startIncompleteElement(element);
				}
				if (a2 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STATIC_NAVIGATION_OR_ATTRIBUTE_CALL__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a2).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STATIC_NAVIGATION_OR_ATTRIBUTE_CALL__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_44_0_0_1_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a2, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[698]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[699]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[700]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[701]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[702]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[703]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[704]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[705]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[706]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[707]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[708]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[709]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[710]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[711]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[712]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[713]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[714]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[715]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[716]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[717]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[718]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[719]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[720]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[721]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[722]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[723]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[724]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[725]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[726]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[727]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[728]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[729]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[730]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[731]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[732]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[733]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[734]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[735]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[736]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[737]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[738]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[739]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[740]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[741]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[742]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[743]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[744]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[745]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[746]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[747]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[748]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[749]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[750]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[751]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[752]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[753]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[754]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[755]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[756]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[757]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[758]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[759]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[760]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[761]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[762]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[763]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[764]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[765]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[766]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[767]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[768]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[769]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[770]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[771]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[772]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[773]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[774]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[775]);
	}
	
;

parse_org_eclipselabs_simpleocl_OperationCall returns [org.eclipselabs.simpleocl.OperationCall element = null]
@init{
}
:
	a0 = '.' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperationCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_46_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[776]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[777]);
	}
	
	(
		(
			a1 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperationCall();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION_CALL__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION_CALL__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_46_0_0_1_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[778]);
		}
		
		
		|		(
			a2 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperationCall();
					startIncompleteElement(element);
				}
				if (a2 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION_CALL__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a2).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION_CALL__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_46_0_0_1_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a2, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[779]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[780]);
	}
	
	a3 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperationCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_46_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[781]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[782]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[783]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[784]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[785]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[786]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[787]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[788]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[789]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[790]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[791]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[792]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[793]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[794]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[795]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[796]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[797]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[798]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[799]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[800]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[801]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[802]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[803]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[804]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[805]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[806]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[807]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[808]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[809]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[810]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[811]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[812]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[813]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[814]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[815]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[816]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[817]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[818]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[819]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[820]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[821]);
	}
	
	(
		(
			(
				a4_0 = parse_org_eclipselabs_simpleocl_OclExpression				{
					if (terminateParsing) {
						throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
					}
					if (element == null) {
						element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperationCall();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION_CALL__ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_46_0_0_3_0_0_0, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[822]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[823]);
			}
			
			(
				(
					a5 = ',' {
						if (element == null) {
							element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperationCall();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_46_0_0_3_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[824]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[825]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[826]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[827]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[828]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[829]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[830]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[831]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[832]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[833]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[834]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[835]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[836]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[837]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[838]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[839]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[840]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[841]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[842]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[843]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[844]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[845]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[846]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[847]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[848]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[849]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[850]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[851]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[852]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[853]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[854]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[855]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[856]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[857]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[858]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[859]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[860]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[861]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[862]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[863]);
					}
					
					(
						a6_0 = parse_org_eclipselabs_simpleocl_OclExpression						{
							if (terminateParsing) {
								throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
							}
							if (element == null) {
								element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperationCall();
								startIncompleteElement(element);
							}
							if (a6_0 != null) {
								if (a6_0 != null) {
									Object value = a6_0;
									addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.OPERATION_CALL__ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_46_0_0_3_0_0_1_0_0_1, a6_0, true);
								copyLocalizationInfos(a6_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[864]);
						addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[865]);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[866]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[867]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[868]);
	}
	
	a7 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperationCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_46_0_0_4, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[869]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[870]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[871]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[872]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[873]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[874]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[875]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[876]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[877]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[878]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[879]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[880]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[881]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[882]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[883]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[884]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[885]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[886]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[887]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[888]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[889]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[890]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[891]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[892]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[893]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[894]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[895]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[896]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[897]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[898]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[899]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[900]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[901]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[902]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[903]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[904]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[905]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[906]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[907]);
	}
	
	|//derived choice rules for sub-classes: 
	
	c0 = parse_org_eclipselabs_simpleocl_CollectionOperationCall{ element = c0; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_eclipselabs_simpleocl_NavigationOrAttributeCall returns [org.eclipselabs.simpleocl.NavigationOrAttributeCall element = null]
@init{
}
:
	a0 = '.' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createNavigationOrAttributeCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_47_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[908]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[909]);
	}
	
	(
		(
			a1 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createNavigationOrAttributeCall();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.NAVIGATION_OR_ATTRIBUTE_CALL__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.NAVIGATION_OR_ATTRIBUTE_CALL__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_47_0_0_1_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[910]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[911]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[912]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[913]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[914]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[915]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[916]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[917]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[918]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[919]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[920]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[921]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[922]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[923]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[924]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[925]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[926]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[927]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[928]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[929]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[930]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[931]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[932]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[933]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[934]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[935]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[936]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[937]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[938]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[939]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[940]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[941]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[942]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[943]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[944]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[945]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[946]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[947]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[948]);
		}
		
		
		|		(
			a2 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createNavigationOrAttributeCall();
					startIncompleteElement(element);
				}
				if (a2 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.NAVIGATION_OR_ATTRIBUTE_CALL__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a2).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.NAVIGATION_OR_ATTRIBUTE_CALL__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_47_0_0_1_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a2, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[949]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[950]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[951]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[952]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[953]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[954]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[955]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[956]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[957]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[958]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[959]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[960]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[961]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[962]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[963]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[964]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[965]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[966]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[967]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[968]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[969]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[970]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[971]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[972]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[973]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[974]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[975]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[976]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[977]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[978]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[979]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[980]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[981]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[982]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[983]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[984]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[985]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[986]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[987]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[988]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[989]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[990]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[991]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[992]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[993]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[994]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[995]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[996]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[997]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[998]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[999]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1000]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1001]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1002]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1003]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1004]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1005]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1006]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1007]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1008]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1009]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1010]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1011]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1012]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1013]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1014]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1015]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1016]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1017]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1018]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1019]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1020]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1021]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1022]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1023]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1024]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1025]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1026]);
	}
	
;

parse_org_eclipselabs_simpleocl_IterateExp returns [org.eclipselabs.simpleocl.IterateExp element = null]
@init{
}
:
	a0 = '->' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterateExp();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_48_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1027]);
	}
	
	a1 = 'iterate' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterateExp();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_48_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1028]);
	}
	
	a2 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterateExp();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_48_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1029]);
	}
	
	(
		a3_0 = parse_org_eclipselabs_simpleocl_Iterator		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterateExp();
				startIncompleteElement(element);
			}
			if (a3_0 != null) {
				if (a3_0 != null) {
					Object value = a3_0;
					addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.ITERATE_EXP__ITERATORS, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_48_0_0_3, a3_0, true);
				copyLocalizationInfos(a3_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1030]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1031]);
	}
	
	(
		(
			a4 = ',' {
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterateExp();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_48_0_0_4_0_0_0, null, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a4, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1032]);
			}
			
			(
				a5_0 = parse_org_eclipselabs_simpleocl_Iterator				{
					if (terminateParsing) {
						throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
					}
					if (element == null) {
						element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterateExp();
						startIncompleteElement(element);
					}
					if (a5_0 != null) {
						if (a5_0 != null) {
							Object value = a5_0;
							addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.ITERATE_EXP__ITERATORS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_48_0_0_4_0_0_1, a5_0, true);
						copyLocalizationInfos(a5_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1033]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1034]);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1035]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1036]);
	}
	
	a6 = ';' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterateExp();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_48_0_0_5, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1037]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1038]);
	}
	
	(
		a7_0 = parse_org_eclipselabs_simpleocl_LocalVariable		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterateExp();
				startIncompleteElement(element);
			}
			if (a7_0 != null) {
				if (a7_0 != null) {
					Object value = a7_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ITERATE_EXP__RESULT), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_48_0_0_6, a7_0, true);
				copyLocalizationInfos(a7_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1039]);
	}
	
	a8 = '|' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterateExp();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_48_0_0_7, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a8, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1040]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1041]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1042]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1043]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1044]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1045]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1046]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1047]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1048]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1049]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1050]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1051]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1052]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1053]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1054]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1055]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1056]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1057]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1058]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1059]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1060]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1061]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1062]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1063]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1064]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1065]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1066]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1067]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1068]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1069]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1070]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1071]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1072]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1073]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1074]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1075]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1076]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1077]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1078]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterateExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1079]);
	}
	
	(
		a9_0 = parse_org_eclipselabs_simpleocl_OclExpression		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterateExp();
				startIncompleteElement(element);
			}
			if (a9_0 != null) {
				if (a9_0 != null) {
					Object value = a9_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ITERATE_EXP__BODY), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_48_0_0_8, a9_0, true);
				copyLocalizationInfos(a9_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1080]);
	}
	
	a10 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterateExp();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_48_0_0_9, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a10, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1081]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1082]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1083]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1084]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1085]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1086]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1087]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1088]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1089]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1090]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1091]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1092]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1093]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1094]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1095]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1096]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1097]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1098]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1099]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1100]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1101]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1102]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1103]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1104]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1105]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1106]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1107]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1108]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1109]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1110]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1111]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1112]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1113]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1114]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1115]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1116]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1117]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1118]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1119]);
	}
	
;

parse_org_eclipselabs_simpleocl_IteratorExp returns [org.eclipselabs.simpleocl.IteratorExp element = null]
@init{
}
:
	a0 = '->' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIteratorExp();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_49_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1120]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1121]);
	}
	
	(
		(
			a1 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIteratorExp();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ITERATOR_EXP__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ITERATOR_EXP__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_49_0_0_1_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1122]);
		}
		
		
		|		(
			a2 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIteratorExp();
					startIncompleteElement(element);
				}
				if (a2 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ITERATOR_EXP__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a2).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ITERATOR_EXP__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_49_0_0_1_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a2, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1123]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1124]);
	}
	
	a3 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIteratorExp();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_49_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1125]);
	}
	
	(
		a4_0 = parse_org_eclipselabs_simpleocl_Iterator		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIteratorExp();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.ITERATOR_EXP__ITERATORS, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_49_0_0_3, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1126]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1127]);
	}
	
	(
		(
			a5 = ',' {
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIteratorExp();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_49_0_0_4_0_0_0, null, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1128]);
			}
			
			(
				a6_0 = parse_org_eclipselabs_simpleocl_Iterator				{
					if (terminateParsing) {
						throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
					}
					if (element == null) {
						element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIteratorExp();
						startIncompleteElement(element);
					}
					if (a6_0 != null) {
						if (a6_0 != null) {
							Object value = a6_0;
							addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.ITERATOR_EXP__ITERATORS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_49_0_0_4_0_0_1, a6_0, true);
						copyLocalizationInfos(a6_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1129]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1130]);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1131]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1132]);
	}
	
	a7 = '|' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIteratorExp();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_49_0_0_5, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1133]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1134]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1135]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1136]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1137]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1138]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1139]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1140]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1141]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1142]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1143]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1144]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1145]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1146]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1147]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1148]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1149]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1150]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1151]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1152]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1153]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1154]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1155]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1156]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1157]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1158]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1159]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1160]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1161]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1162]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1163]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1164]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1165]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1166]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1167]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1168]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1169]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1170]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1171]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIteratorExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1172]);
	}
	
	(
		a8_0 = parse_org_eclipselabs_simpleocl_OclExpression		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIteratorExp();
				startIncompleteElement(element);
			}
			if (a8_0 != null) {
				if (a8_0 != null) {
					Object value = a8_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ITERATOR_EXP__BODY), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_49_0_0_6, a8_0, true);
				copyLocalizationInfos(a8_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1173]);
	}
	
	a9 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIteratorExp();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_49_0_0_7, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a9, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1174]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1175]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1176]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1177]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1178]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1179]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1180]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1181]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1182]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1183]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1184]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1185]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1186]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1187]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1188]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1189]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1190]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1191]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1192]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1193]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1194]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1195]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1196]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1197]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1198]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1199]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1200]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1201]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1202]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1203]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1204]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1205]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1206]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1207]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1208]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1209]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1210]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1211]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1212]);
	}
	
;

parse_org_eclipselabs_simpleocl_Iterator returns [org.eclipselabs.simpleocl.Iterator element = null]
@init{
}
:
	(
		a0 = TEXT		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterator();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ITERATOR__VAR_NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ITERATOR__VAR_NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_50_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1213]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1214]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1215]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1216]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1217]);
	}
	
	(
		(
			a1 = ':' {
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterator();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_50_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1218]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1219]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1220]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1221]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1222]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1223]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1224]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1225]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1226]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1227]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1228]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1229]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1230]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1231]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1232]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1233]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1234]);
				addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIterator(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1235]);
			}
			
			(
				a2_0 = parse_org_eclipselabs_simpleocl_OclType				{
					if (terminateParsing) {
						throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
					}
					if (element == null) {
						element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIterator();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ITERATOR__TYPE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_50_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1236]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1237]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1238]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1239]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1240]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1241]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1242]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1243]);
	}
	
;

parse_org_eclipselabs_simpleocl_CollectionOperationCall returns [org.eclipselabs.simpleocl.CollectionOperationCall element = null]
@init{
}
:
	a0 = '->' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionOperationCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_51_0_0_0, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1244]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1245]);
	}
	
	(
		(
			a1 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionOperationCall();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_51_0_0_1_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1246]);
		}
		
		
		|		(
			a2 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionOperationCall();
					startIncompleteElement(element);
				}
				if (a2 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a2).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_51_0_0_1_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a2, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1247]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1248]);
	}
	
	a3 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionOperationCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_51_0_0_2, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1249]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1250]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1251]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1252]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1253]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1254]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1255]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1256]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1257]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1258]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1259]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1260]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1261]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1262]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1263]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1264]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1265]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1266]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1267]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1268]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1269]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1270]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1271]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1272]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1273]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1274]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1275]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1276]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1277]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1278]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1279]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1280]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1281]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1282]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1283]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1284]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1285]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1286]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1287]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1288]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1289]);
	}
	
	(
		(
			(
				a4_0 = parse_org_eclipselabs_simpleocl_OclExpression				{
					if (terminateParsing) {
						throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
					}
					if (element == null) {
						element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionOperationCall();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_51_0_0_3_0_0_0, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1290]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1291]);
			}
			
			(
				(
					a5 = ',' {
						if (element == null) {
							element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionOperationCall();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_51_0_0_3_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1292]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1293]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1294]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1295]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1296]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1297]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1298]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1299]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1300]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1301]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1302]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1303]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1304]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1305]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1306]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1307]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1308]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1309]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1310]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1311]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1312]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1313]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1314]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1315]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1316]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1317]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1318]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1319]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1320]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1321]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1322]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1323]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1324]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1325]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1326]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1327]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1328]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1329]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1330]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionOperationCall(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1331]);
					}
					
					(
						a6_0 = parse_org_eclipselabs_simpleocl_OclExpression						{
							if (terminateParsing) {
								throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
							}
							if (element == null) {
								element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionOperationCall();
								startIncompleteElement(element);
							}
							if (a6_0 != null) {
								if (a6_0 != null) {
									Object value = a6_0;
									addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.COLLECTION_OPERATION_CALL__ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_51_0_0_3_0_0_1_0_0_1, a6_0, true);
								copyLocalizationInfos(a6_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1332]);
						addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1333]);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1334]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1335]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1336]);
	}
	
	a7 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionOperationCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_51_0_0_4, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1337]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1338]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1339]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1340]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1341]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1342]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1343]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1344]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1345]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1346]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1347]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1348]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1349]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1350]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1351]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1352]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1353]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1354]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1355]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1356]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1357]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1358]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1359]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1360]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1361]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1362]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1363]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1364]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1365]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1366]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1367]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1368]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1369]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1370]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1371]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1372]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1373]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1374]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1375]);
	}
	
;

parse_org_eclipselabs_simpleocl_CollectionType returns [org.eclipselabs.simpleocl.CollectionType element = null]
@init{
}
:
	(
		a0 = COLLECTIONTYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("COLLECTIONTYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.COLLECTION_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.COLLECTION_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_52_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1376]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_52_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1377]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1378]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1379]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1380]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1381]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1382]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1383]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1384]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1385]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1386]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1387]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1388]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1389]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1390]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1391]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1392]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1393]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getCollectionType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1394]);
	}
	
	(
		a2_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionType();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.COLLECTION_TYPE__ELEMENT_TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_52_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1395]);
	}
	
	a3 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createCollectionType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_52_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1396]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1397]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1398]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1399]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1400]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1401]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1402]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1403]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1404]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1405]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1406]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1407]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1408]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1409]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1410]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1411]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1412]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1413]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1414]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1415]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1416]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1417]);
	}
	
	|//derived choice rules for sub-classes: 
	
	c0 = parse_org_eclipselabs_simpleocl_BagType{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_eclipselabs_simpleocl_OrderedSetType{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_eclipselabs_simpleocl_SequenceType{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_eclipselabs_simpleocl_SetType{ element = c3; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_eclipselabs_simpleocl_StringType returns [org.eclipselabs.simpleocl.StringType element = null]
@init{
}
:
	(
		a0 = STRINGTYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStringType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("STRINGTYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STRING_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STRING_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_53_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1418]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1419]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1420]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1421]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1422]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1423]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1424]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1425]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1426]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1427]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1428]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1429]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1430]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1431]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1432]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1433]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1434]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1435]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1436]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1437]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1438]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1439]);
	}
	
;

parse_org_eclipselabs_simpleocl_BooleanType returns [org.eclipselabs.simpleocl.BooleanType element = null]
@init{
}
:
	(
		a0 = BOOLEANTYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBooleanType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("BOOLEANTYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.BOOLEAN_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_54_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1440]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1441]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1442]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1443]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1444]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1445]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1446]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1447]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1448]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1449]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1450]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1451]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1452]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1453]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1454]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1455]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1456]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1457]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1458]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1459]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1460]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1461]);
	}
	
;

parse_org_eclipselabs_simpleocl_IntegerType returns [org.eclipselabs.simpleocl.IntegerType element = null]
@init{
}
:
	(
		a0 = INTEGERTYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIntegerType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("INTEGERTYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.INTEGER_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.INTEGER_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_55_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1462]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1463]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1464]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1465]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1466]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1467]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1468]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1469]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1470]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1471]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1472]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1473]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1474]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1475]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1476]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1477]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1478]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1479]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1480]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1481]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1482]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1483]);
	}
	
;

parse_org_eclipselabs_simpleocl_RealType returns [org.eclipselabs.simpleocl.RealType element = null]
@init{
}
:
	(
		a0 = REALTYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createRealType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("REALTYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.REAL_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.REAL_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_56_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1484]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1485]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1486]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1487]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1488]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1489]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1490]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1491]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1492]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1493]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1494]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1495]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1496]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1497]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1498]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1499]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1500]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1501]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1502]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1503]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1504]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1505]);
	}
	
;

parse_org_eclipselabs_simpleocl_BagType returns [org.eclipselabs.simpleocl.BagType element = null]
@init{
}
:
	(
		a0 = BAGTYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBagType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("BAGTYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.BAG_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.BAG_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_57_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1506]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBagType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_57_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1507]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1508]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1509]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1510]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1511]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1512]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1513]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1514]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1515]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1516]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1517]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1518]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1519]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1520]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1521]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1522]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1523]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1524]);
	}
	
	(
		a2_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBagType();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.BAG_TYPE__ELEMENT_TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_57_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1525]);
	}
	
	a3 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBagType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_57_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1526]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1527]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1528]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1529]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1530]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1531]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1532]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1533]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1534]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1535]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1536]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1537]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1538]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1539]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1540]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1541]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1542]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1543]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1544]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1545]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1546]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1547]);
	}
	
;

parse_org_eclipselabs_simpleocl_OrderedSetType returns [org.eclipselabs.simpleocl.OrderedSetType element = null]
@init{
}
:
	(
		a0 = ORDEREDSETTYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOrderedSetType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("ORDEREDSETTYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_58_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1548]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOrderedSetType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_58_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1549]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1550]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1551]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1552]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1553]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1554]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1555]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1556]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1557]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1558]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1559]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1560]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1561]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1562]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1563]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1564]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1565]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1566]);
	}
	
	(
		a2_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOrderedSetType();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ORDERED_SET_TYPE__ELEMENT_TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_58_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1567]);
	}
	
	a3 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOrderedSetType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_58_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1568]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1569]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1570]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1571]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1572]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1573]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1574]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1575]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1576]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1577]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1578]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1579]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1580]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1581]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1582]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1583]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1584]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1585]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1586]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1587]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1588]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1589]);
	}
	
;

parse_org_eclipselabs_simpleocl_SequenceType returns [org.eclipselabs.simpleocl.SequenceType element = null]
@init{
}
:
	(
		a0 = SEQUENCETYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSequenceType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SEQUENCETYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_59_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1590]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSequenceType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_59_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1591]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1592]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1593]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1594]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1595]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1596]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1597]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1598]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1599]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1600]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1601]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1602]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1603]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1604]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1605]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1606]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1607]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1608]);
	}
	
	(
		a2_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSequenceType();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.SEQUENCE_TYPE__ELEMENT_TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_59_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1609]);
	}
	
	a3 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSequenceType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_59_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1610]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1611]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1612]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1613]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1614]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1615]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1616]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1617]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1618]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1619]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1620]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1621]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1622]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1623]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1624]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1625]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1626]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1627]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1628]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1629]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1630]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1631]);
	}
	
;

parse_org_eclipselabs_simpleocl_SetType returns [org.eclipselabs.simpleocl.SetType element = null]
@init{
}
:
	(
		a0 = SETTYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSetType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("SETTYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.SET_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.SET_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_60_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1632]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSetType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_60_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1633]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1634]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1635]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1636]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1637]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1638]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1639]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1640]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1641]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1642]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1643]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1644]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1645]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1646]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1647]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1648]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1649]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1650]);
	}
	
	(
		a2_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSetType();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.SET_TYPE__ELEMENT_TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_60_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1651]);
	}
	
	a3 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSetType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_60_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1652]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1653]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1654]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1655]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1656]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1657]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1658]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1659]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1660]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1661]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1662]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1663]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1664]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1665]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1666]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1667]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1668]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1669]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1670]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1671]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1672]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1673]);
	}
	
;

parse_org_eclipselabs_simpleocl_OclAnyType returns [org.eclipselabs.simpleocl.OclAnyType element = null]
@init{
}
:
	(
		a0 = OCLANYTYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclAnyType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("OCLANYTYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_ANY_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_61_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1674]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1675]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1676]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1677]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1678]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1679]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1680]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1681]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1682]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1683]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1684]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1685]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1686]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1687]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1688]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1689]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1690]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1691]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1692]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1693]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1694]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1695]);
	}
	
;

parse_org_eclipselabs_simpleocl_OclType returns [org.eclipselabs.simpleocl.OclType element = null]
@init{
}
:
	(
		a0 = OCLTYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("OCLTYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_62_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1696]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1697]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1698]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1699]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1700]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1701]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1702]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1703]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1704]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1705]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1706]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1707]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1708]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1709]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1710]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1711]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1712]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1713]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1714]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1715]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1716]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1717]);
	}
	
	|//derived choice rules for sub-classes: 
	
	c0 = parse_org_eclipselabs_simpleocl_CollectionType{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_eclipselabs_simpleocl_StringType{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_eclipselabs_simpleocl_BooleanType{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_eclipselabs_simpleocl_IntegerType{ element = c3; /* this is a subclass or primitive expression choice */ }
	|	c4 = parse_org_eclipselabs_simpleocl_RealType{ element = c4; /* this is a subclass or primitive expression choice */ }
	|	c5 = parse_org_eclipselabs_simpleocl_BagType{ element = c5; /* this is a subclass or primitive expression choice */ }
	|	c6 = parse_org_eclipselabs_simpleocl_OrderedSetType{ element = c6; /* this is a subclass or primitive expression choice */ }
	|	c7 = parse_org_eclipselabs_simpleocl_SequenceType{ element = c7; /* this is a subclass or primitive expression choice */ }
	|	c8 = parse_org_eclipselabs_simpleocl_SetType{ element = c8; /* this is a subclass or primitive expression choice */ }
	|	c9 = parse_org_eclipselabs_simpleocl_OclAnyType{ element = c9; /* this is a subclass or primitive expression choice */ }
	|	c10 = parse_org_eclipselabs_simpleocl_TupleType{ element = c10; /* this is a subclass or primitive expression choice */ }
	|	c11 = parse_org_eclipselabs_simpleocl_OclModelElement{ element = c11; /* this is a subclass or primitive expression choice */ }
	|	c12 = parse_org_eclipselabs_simpleocl_MapType{ element = c12; /* this is a subclass or primitive expression choice */ }
	|	c13 = parse_org_eclipselabs_simpleocl_LambdaType{ element = c13; /* this is a subclass or primitive expression choice */ }
	|	c14 = parse_org_eclipselabs_simpleocl_EnvType{ element = c14; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_eclipselabs_simpleocl_TupleType returns [org.eclipselabs.simpleocl.TupleType element = null]
@init{
}
:
	(
		(
			a0 = TUPLETYPE			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleType();
					startIncompleteElement(element);
				}
				if (a0 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TUPLETYPE");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_TYPE__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_TYPE__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_63_0_0_0_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1718]);
		}
		
		
		|		(
			a1 = TUPLE			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleType();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TUPLE");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_TYPE__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_TYPE__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_63_0_0_0_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1719]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1720]);
	}
	
	a2 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_63_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1721]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1722]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1723]);
	}
	
	(
		(
			(
				a3_0 = parse_org_eclipselabs_simpleocl_TupleTypeAttribute				{
					if (terminateParsing) {
						throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
					}
					if (element == null) {
						element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleType();
						startIncompleteElement(element);
					}
					if (a3_0 != null) {
						if (a3_0 != null) {
							Object value = a3_0;
							addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_TYPE__ATTRIBUTES, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_63_0_0_2_0_0_0, a3_0, true);
						copyLocalizationInfos(a3_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1724]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1725]);
			}
			
			(
				(
					a4 = ',' {
						if (element == null) {
							element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleType();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_63_0_0_2_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a4, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1726]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1727]);
					}
					
					(
						a5_0 = parse_org_eclipselabs_simpleocl_TupleTypeAttribute						{
							if (terminateParsing) {
								throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
							}
							if (element == null) {
								element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleType();
								startIncompleteElement(element);
							}
							if (a5_0 != null) {
								if (a5_0 != null) {
									Object value = a5_0;
									addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_TYPE__ATTRIBUTES, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_63_0_0_2_0_0_1_0_0_1, a5_0, true);
								copyLocalizationInfos(a5_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1728]);
						addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1729]);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1730]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1731]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1732]);
	}
	
	a6 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_63_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1733]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1734]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1735]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1736]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1737]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1738]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1739]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1740]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1741]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1742]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1743]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1744]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1745]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1746]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1747]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1748]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1749]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1750]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1751]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1752]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1753]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1754]);
	}
	
;

parse_org_eclipselabs_simpleocl_TupleTypeAttribute returns [org.eclipselabs.simpleocl.TupleTypeAttribute element = null]
@init{
}
:
	(
		(
			a0 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleTypeAttribute();
					startIncompleteElement(element);
				}
				if (a0 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_64_0_0_0_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1755]);
		}
		
		
		|		(
			a1 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleTypeAttribute();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_64_0_0_0_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1756]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1757]);
	}
	
	a2 = ':' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleTypeAttribute();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_64_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1758]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1759]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1760]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1761]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1762]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1763]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1764]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1765]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1766]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1767]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1768]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1769]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1770]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1771]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1772]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1773]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1774]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleTypeAttribute(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1775]);
	}
	
	(
		a3_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleTypeAttribute();
				startIncompleteElement(element);
			}
			if (a3_0 != null) {
				if (a3_0 != null) {
					Object value = a3_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_TYPE_ATTRIBUTE__TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_64_0_0_2, a3_0, true);
				copyLocalizationInfos(a3_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1776]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1777]);
	}
	
;

parse_org_eclipselabs_simpleocl_OclModelElement returns [org.eclipselabs.simpleocl.OclModelElement element = null]
@init{
}
:
	(
		(
			a0 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclModelElement();
					startIncompleteElement(element);
				}
				if (a0 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__MODEL), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
					}
					String resolved = (String) resolvedObject;
					org.eclipselabs.simpleocl.OclModel proxy = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclMetamodel();
					collectHiddenTokens(element);
					registerContextDependentProxy(new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContextDependentURIFragmentFactory<org.eclipselabs.simpleocl.OclModelElement, org.eclipselabs.simpleocl.OclModel>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOclModelElementModelReferenceResolver()), element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__MODEL), resolved, proxy);
					if (proxy != null) {
						Object value = proxy;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__MODEL), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_65_0_0_0_0_0_0, proxy, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, proxy);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1778]);
		}
		
		
		|		(
			a1 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclModelElement();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__MODEL), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					String resolved = (String) resolvedObject;
					org.eclipselabs.simpleocl.OclModel proxy = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclMetamodel();
					collectHiddenTokens(element);
					registerContextDependentProxy(new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContextDependentURIFragmentFactory<org.eclipselabs.simpleocl.OclModelElement, org.eclipselabs.simpleocl.OclModel>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOclModelElementModelReferenceResolver()), element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__MODEL), resolved, proxy);
					if (proxy != null) {
						Object value = proxy;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__MODEL), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_65_0_0_0_0_1_0, proxy, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, proxy);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1779]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1780]);
	}
	
	a2 = '!' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclModelElement();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_65_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1781]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1782]);
	}
	
	(
		(
			a3 = TEXT			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclModelElement();
					startIncompleteElement(element);
				}
				if (a3 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a3).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a3).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a3).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a3).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_65_0_0_2_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a3, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1783]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1784]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1785]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1786]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1787]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1788]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1789]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1790]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1791]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1792]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1793]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1794]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1795]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1796]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1797]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1798]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1799]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1800]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1801]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1802]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1803]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1804]);
		}
		
		
		|		(
			a4 = QUOTED_34_34_92			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclModelElement();
					startIncompleteElement(element);
				}
				if (a4 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a4).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a4).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT__NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_65_0_0_2_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a4, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1805]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1806]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1807]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1808]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1809]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1810]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1811]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1812]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1813]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1814]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1815]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1816]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1817]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1818]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1819]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1820]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1821]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1822]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1823]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1824]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1825]);
			addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1826]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1827]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1828]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1829]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1830]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1831]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1832]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1833]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1834]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1835]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1836]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1837]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1838]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1839]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1840]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1841]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1842]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1843]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1844]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1845]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1846]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1847]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1848]);
	}
	
;

parse_org_eclipselabs_simpleocl_MapType returns [org.eclipselabs.simpleocl.MapType element = null]
@init{
}
:
	(
		a0 = MAPTYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("MAPTYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MAP_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MAP_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_66_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1849]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_66_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1850]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1851]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1852]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1853]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1854]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1855]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1856]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1857]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1858]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1859]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1860]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1861]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1862]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1863]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1864]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1865]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1866]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1867]);
	}
	
	(
		a2_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapType();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MAP_TYPE__KEY_TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_66_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1868]);
	}
	
	a3 = ',' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_66_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1869]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1870]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1871]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1872]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1873]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1874]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1875]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1876]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1877]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1878]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1879]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1880]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1881]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1882]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1883]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1884]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1885]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1886]);
	}
	
	(
		a4_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapType();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MAP_TYPE__VALUE_TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_66_0_0_4, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1887]);
	}
	
	a5 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_66_0_0_5, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1888]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1889]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1890]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1891]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1892]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1893]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1894]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1895]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1896]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1897]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1898]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1899]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1900]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1901]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1902]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1903]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1904]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1905]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1906]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1907]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1908]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1909]);
	}
	
;

parse_org_eclipselabs_simpleocl_LambdaType returns [org.eclipselabs.simpleocl.LambdaType element = null]
@init{
}
:
	(
		a0 = LAMBDATYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("LAMBDATYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LAMBDA_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LAMBDA_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_67_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1910]);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_67_0_0_1, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1911]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1912]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1913]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1914]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1915]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1916]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1917]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1918]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1919]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1920]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1921]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1922]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1923]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1924]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1925]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1926]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1927]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1928]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1929]);
	}
	
	(
		(
			(
				a2_0 = parse_org_eclipselabs_simpleocl_OclType				{
					if (terminateParsing) {
						throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
					}
					if (element == null) {
						element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaType();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.LAMBDA_TYPE__ARGUMENT_TYPES, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_67_0_0_2_0_0_0, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1930]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1931]);
			}
			
			(
				(
					a3 = ',' {
						if (element == null) {
							element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaType();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_67_0_0_2_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1932]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1933]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1934]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1935]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1936]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1937]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1938]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1939]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1940]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1941]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1942]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1943]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1944]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1945]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1946]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1947]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1948]);
						addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1949]);
					}
					
					(
						a4_0 = parse_org_eclipselabs_simpleocl_OclType						{
							if (terminateParsing) {
								throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
							}
							if (element == null) {
								element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaType();
								startIncompleteElement(element);
							}
							if (a4_0 != null) {
								if (a4_0 != null) {
									Object value = a4_0;
									addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.LAMBDA_TYPE__ARGUMENT_TYPES, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_67_0_0_2_0_0_1_0_0_1, a4_0, true);
								copyLocalizationInfos(a4_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1950]);
						addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1951]);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1952]);
				addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1953]);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1954]);
	}
	
	a5 = ')' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_67_0_0_3, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1955]);
	}
	
	a6 = ':' {
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaType();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_67_0_0_4, null, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1956]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1957]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1958]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1959]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1960]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1961]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1962]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1963]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1964]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1965]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1966]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1967]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1968]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1969]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1970]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1971]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1972]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaType(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1973]);
	}
	
	(
		a7_0 = parse_org_eclipselabs_simpleocl_OclType		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaType();
				startIncompleteElement(element);
			}
			if (a7_0 != null) {
				if (a7_0 != null) {
					Object value = a7_0;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LAMBDA_TYPE__RETURN_TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_67_0_0_5, a7_0, true);
				copyLocalizationInfos(a7_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1974]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1975]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1976]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1977]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1978]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1979]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1980]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1981]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1982]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1983]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1984]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1985]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1986]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1987]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1988]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1989]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1990]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1991]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1992]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1993]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1994]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1995]);
	}
	
;

parse_org_eclipselabs_simpleocl_EnvType returns [org.eclipselabs.simpleocl.EnvType element = null]
@init{
}
:
	(
		a0 = ENVTYPE		
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createEnvType();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("ENVTYPE");
				tokenResolver.setOptions(getOptions());
				org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ENV_TYPE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ENV_TYPE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_68_0_0_0, resolved, true);
				copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1996]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1997]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1998]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[1999]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2000]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2001]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2002]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2003]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2004]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2005]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2006]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2007]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2008]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2009]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2010]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2011]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2012]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2013]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2014]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2015]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2016]);
		addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2017]);
	}
	
;

parseop_OclExpression_level_1 returns [org.eclipselabs.simpleocl.OclExpression element = null]
@init{
}
:
	leftArg = parseop_OclExpression_level_2	((
		()
		{ element = null; }
		(
			a0 = BOOLOP			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperatorCallExp();
					startIncompleteElement(element);
				}
				if (a0 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("BOOLOP");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_35_0_0_1, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2018]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2019]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2020]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2021]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2022]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2023]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2024]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2025]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2026]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2027]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2028]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2029]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2030]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2031]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2032]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2033]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2034]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2035]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2036]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2037]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2038]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2039]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2040]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2041]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2042]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2043]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2044]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2045]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2046]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2047]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2048]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2049]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2050]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2051]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2052]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2053]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2054]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2055]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2056]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOperatorCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2057]);
		}
		
		rightArg = parseop_OclExpression_level_2		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperatorCallExp();
				startIncompleteElement(element);
			}
			if (leftArg != null) {
				if (leftArg != null) {
					Object value = leftArg;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__SOURCE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_35_0_0_0, leftArg, true);
				copyLocalizationInfos(leftArg, element);
			}
		}
		{
			if (terminateParsing) {
				throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
			}
			if (element == null) {
				element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOperatorCallExp();
				startIncompleteElement(element);
			}
			if (rightArg != null) {
				if (rightArg != null) {
					Object value = rightArg;
					element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OPERATOR_CALL_EXP__ARGUMENT), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_35_0_0_2, rightArg, true);
				copyLocalizationInfos(rightArg, element);
			}
		}
		{ leftArg = element; /* this may become an argument in the next iteration */ }
	)+ | /* epsilon */ { element = leftArg; }
	
)
;

parseop_OclExpression_level_2 returns [org.eclipselabs.simpleocl.OclExpression element = null]
@init{
}
:
leftArg = parseop_OclExpression_level_3((
	()
	{ element = null; }
	(
		(
			a0 = EQ			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createEqOpCallExp();
					startIncompleteElement(element);
				}
				if (a0 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("EQ");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_36_0_0_1_0_0_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2058]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2059]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2060]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2061]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2062]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2063]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2064]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2065]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2066]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2067]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2068]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2069]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2070]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2071]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2072]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2073]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2074]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2075]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2076]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2077]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2078]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2079]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2080]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2081]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2082]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2083]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2084]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2085]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2086]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2087]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2088]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2089]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2090]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2091]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2092]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2093]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2094]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2095]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2096]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2097]);
		}
		
		
		|		(
			a1 = NEQ			
			{
				if (terminateParsing) {
					throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
				}
				if (element == null) {
					element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createEqOpCallExp();
					startIncompleteElement(element);
				}
				if (a1 != null) {
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("NEQ");
					tokenResolver.setOptions(getOptions());
					org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
					tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__OPERATION_NAME), result);
					Object resolvedObject = result.getResolvedToken();
					if (resolvedObject == null) {
						addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
					}
					java.lang.String resolved = (java.lang.String) resolvedObject;
					if (resolved != null) {
						Object value = resolved;
						element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__OPERATION_NAME), value);
						completedElement(value, false);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_36_0_0_1_0_1_0, resolved, true);
					copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2098]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2099]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2100]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2101]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2102]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2103]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2104]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2105]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2106]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2107]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2108]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2109]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2110]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2111]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2112]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2113]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2114]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2115]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2116]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2117]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2118]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2119]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2120]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2121]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2122]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2123]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2124]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2125]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2126]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2127]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2128]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2129]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2130]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2131]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2132]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2133]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2134]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2135]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2136]);
			addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2137]);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2138]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2139]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2140]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2141]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2142]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2143]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2144]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2145]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2146]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2147]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2148]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2149]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2150]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2151]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2152]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2153]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2154]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2155]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2156]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2157]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2158]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2159]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2160]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2161]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2162]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2163]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2164]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2165]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2166]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2167]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2168]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2169]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2170]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2171]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2172]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2173]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2174]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2175]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2176]);
		addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getEqOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2177]);
	}
	
	rightArg = parseop_OclExpression_level_3	{
		if (terminateParsing) {
			throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
		}
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createEqOpCallExp();
			startIncompleteElement(element);
		}
		if (leftArg != null) {
			if (leftArg != null) {
				Object value = leftArg;
				element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__SOURCE), value);
				completedElement(value, true);
			}
			collectHiddenTokens(element);
			retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_36_0_0_0, leftArg, true);
			copyLocalizationInfos(leftArg, element);
		}
	}
	{
		if (terminateParsing) {
			throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
		}
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createEqOpCallExp();
			startIncompleteElement(element);
		}
		if (rightArg != null) {
			if (rightArg != null) {
				Object value = rightArg;
				element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.EQ_OP_CALL_EXP__ARGUMENT), value);
				completedElement(value, true);
			}
			collectHiddenTokens(element);
			retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_36_0_0_2, rightArg, true);
			copyLocalizationInfos(rightArg, element);
		}
	}
	{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_OclExpression_level_3 returns [org.eclipselabs.simpleocl.OclExpression element = null]
@init{
}
:
leftArg = parseop_OclExpression_level_4((
()
{ element = null; }
(
	a0 = RELOP	
	{
		if (terminateParsing) {
			throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
		}
		if (element == null) {
			element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createRelOpCallExp();
			startIncompleteElement(element);
		}
		if (a0 != null) {
			org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("RELOP");
			tokenResolver.setOptions(getOptions());
			org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
			tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__OPERATION_NAME), result);
			Object resolvedObject = result.getResolvedToken();
			if (resolvedObject == null) {
				addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
			}
			java.lang.String resolved = (java.lang.String) resolvedObject;
			if (resolved != null) {
				Object value = resolved;
				element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__OPERATION_NAME), value);
				completedElement(value, false);
			}
			collectHiddenTokens(element);
			retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_37_0_0_1, resolved, true);
			copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
		}
	}
)
{
	// expected elements (follow set)
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2178]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2179]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2180]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2181]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2182]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2183]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2184]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2185]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2186]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2187]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2188]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2189]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2190]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2191]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2192]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2193]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2194]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2195]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2196]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2197]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2198]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2199]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2200]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2201]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2202]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2203]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2204]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2205]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2206]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2207]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2208]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2209]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2210]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2211]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2212]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2213]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2214]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2215]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2216]);
	addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getRelOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2217]);
}

rightArg = parseop_OclExpression_level_4{
	if (terminateParsing) {
		throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
	}
	if (element == null) {
		element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createRelOpCallExp();
		startIncompleteElement(element);
	}
	if (leftArg != null) {
		if (leftArg != null) {
			Object value = leftArg;
			element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__SOURCE), value);
			completedElement(value, true);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_37_0_0_0, leftArg, true);
		copyLocalizationInfos(leftArg, element);
	}
}
{
	if (terminateParsing) {
		throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
	}
	if (element == null) {
		element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createRelOpCallExp();
		startIncompleteElement(element);
	}
	if (rightArg != null) {
		if (rightArg != null) {
			Object value = rightArg;
			element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.REL_OP_CALL_EXP__ARGUMENT), value);
			completedElement(value, true);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_37_0_0_2, rightArg, true);
		copyLocalizationInfos(rightArg, element);
	}
}
{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_OclExpression_level_4 returns [org.eclipselabs.simpleocl.OclExpression element = null]
@init{
}
:
leftArg = parseop_OclExpression_level_5((
()
{ element = null; }
(
a0 = ADDOP
{
	if (terminateParsing) {
		throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
	}
	if (element == null) {
		element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createAddOpCallExp();
		startIncompleteElement(element);
	}
	if (a0 != null) {
		org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("ADDOP");
		tokenResolver.setOptions(getOptions());
		org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
		tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__OPERATION_NAME), result);
		Object resolvedObject = result.getResolvedToken();
		if (resolvedObject == null) {
			addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
		}
		java.lang.String resolved = (java.lang.String) resolvedObject;
		if (resolved != null) {
			Object value = resolved;
			element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__OPERATION_NAME), value);
			completedElement(value, false);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_38_0_0_1, resolved, true);
		copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2218]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2219]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2220]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2221]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2222]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2223]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2224]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2225]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2226]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2227]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2228]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2229]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2230]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2231]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2232]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2233]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2234]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2235]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2236]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2237]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2238]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2239]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2240]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2241]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2242]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2243]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2244]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2245]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2246]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2247]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2248]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2249]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2250]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2251]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2252]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2253]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2254]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2255]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2256]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getAddOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2257]);
}

rightArg = parseop_OclExpression_level_5{
if (terminateParsing) {
	throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
	element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createAddOpCallExp();
	startIncompleteElement(element);
}
if (leftArg != null) {
	if (leftArg != null) {
		Object value = leftArg;
		element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__SOURCE), value);
		completedElement(value, true);
	}
	collectHiddenTokens(element);
	retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_38_0_0_0, leftArg, true);
	copyLocalizationInfos(leftArg, element);
}
}
{
if (terminateParsing) {
	throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
	element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createAddOpCallExp();
	startIncompleteElement(element);
}
if (rightArg != null) {
	if (rightArg != null) {
		Object value = rightArg;
		element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ADD_OP_CALL_EXP__ARGUMENT), value);
		completedElement(value, true);
	}
	collectHiddenTokens(element);
	retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_38_0_0_2, rightArg, true);
	copyLocalizationInfos(rightArg, element);
}
}
{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_OclExpression_level_5 returns [org.eclipselabs.simpleocl.OclExpression element = null]
@init{
}
:
leftArg = parseop_OclExpression_level_6((
()
{ element = null; }
(
a0 = INTOP
{
if (terminateParsing) {
	throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
	element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIntOpCallExp();
	startIncompleteElement(element);
}
if (a0 != null) {
	org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("INTOP");
	tokenResolver.setOptions(getOptions());
	org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
	tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__OPERATION_NAME), result);
	Object resolvedObject = result.getResolvedToken();
	if (resolvedObject == null) {
		addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
	}
	java.lang.String resolved = (java.lang.String) resolvedObject;
	if (resolved != null) {
		Object value = resolved;
		element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__OPERATION_NAME), value);
		completedElement(value, false);
	}
	collectHiddenTokens(element);
	retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_39_0_0_1, resolved, true);
	copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2258]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2259]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2260]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2261]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2262]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2263]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2264]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2265]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2266]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2267]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2268]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2269]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2270]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2271]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2272]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2273]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2274]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2275]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2276]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2277]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2278]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2279]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2280]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2281]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2282]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2283]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2284]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2285]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2286]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2287]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2288]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2289]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2290]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2291]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2292]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2293]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2294]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2295]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2296]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIntOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2297]);
}

rightArg = parseop_OclExpression_level_6{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIntOpCallExp();
startIncompleteElement(element);
}
if (leftArg != null) {
if (leftArg != null) {
	Object value = leftArg;
	element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__SOURCE), value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_39_0_0_0, leftArg, true);
copyLocalizationInfos(leftArg, element);
}
}
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIntOpCallExp();
startIncompleteElement(element);
}
if (rightArg != null) {
if (rightArg != null) {
	Object value = rightArg;
	element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.INT_OP_CALL_EXP__ARGUMENT), value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_39_0_0_2, rightArg, true);
copyLocalizationInfos(rightArg, element);
}
}
{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_OclExpression_level_6 returns [org.eclipselabs.simpleocl.OclExpression element = null]
@init{
}
:
leftArg = parseop_OclExpression_level_7((
()
{ element = null; }
(
a0 = MULOP
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMulOpCallExp();
startIncompleteElement(element);
}
if (a0 != null) {
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("MULOP");
tokenResolver.setOptions(getOptions());
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
	addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
	Object value = resolved;
	element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__OPERATION_NAME), value);
	completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_40_0_0_1, resolved, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2298]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2299]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2300]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2301]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2302]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2303]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2304]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2305]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2306]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2307]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2308]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2309]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2310]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2311]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2312]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2313]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2314]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2315]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2316]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2317]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2318]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2319]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2320]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2321]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2322]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2323]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2324]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2325]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2326]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2327]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2328]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2329]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2330]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2331]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2332]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2333]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2334]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2335]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2336]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMulOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2337]);
}

rightArg = parseop_OclExpression_level_7{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMulOpCallExp();
startIncompleteElement(element);
}
if (leftArg != null) {
if (leftArg != null) {
Object value = leftArg;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__SOURCE), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_40_0_0_0, leftArg, true);
copyLocalizationInfos(leftArg, element);
}
}
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMulOpCallExp();
startIncompleteElement(element);
}
if (rightArg != null) {
if (rightArg != null) {
Object value = rightArg;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.MUL_OP_CALL_EXP__ARGUMENT), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_40_0_0_2, rightArg, true);
copyLocalizationInfos(rightArg, element);
}
}
{ leftArg = element; /* this may become an argument in the next iteration */ }
)+ | /* epsilon */ { element = leftArg; }

)
;

parseop_OclExpression_level_7 returns [org.eclipselabs.simpleocl.OclExpression element = null]
@init{
}
:
(
a0 = NOTOP
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createNotOpCallExp();
startIncompleteElement(element);
}
if (a0 != null) {
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("NOTOP");
tokenResolver.setOptions(getOptions());
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__OPERATION_NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__OPERATION_NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_41_0_0_0, resolved, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2338]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2339]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2340]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2341]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2342]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2343]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2344]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2345]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2346]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2347]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2348]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2349]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2350]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2351]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2352]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2353]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2354]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2355]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2356]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2357]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2358]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2359]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2360]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2361]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2362]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2363]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2364]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2365]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2366]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2367]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2368]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2369]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2370]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2371]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2372]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2373]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2374]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2375]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2376]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getNotOpCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2377]);
}

arg = parseop_OclExpression_level_10{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createNotOpCallExp();
startIncompleteElement(element);
}
if (arg != null) {
if (arg != null) {
Object value = arg;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.NOT_OP_CALL_EXP__SOURCE), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_41_0_0_1, arg, true);
copyLocalizationInfos(arg, element);
}
}
|

arg = parseop_OclExpression_level_10{ element = arg; }
;

parseop_OclExpression_level_10 returns [org.eclipselabs.simpleocl.OclExpression element = null]
@init{
}
:
arg = parseop_OclExpression_level_20(
(
(
a0_0 = parse_org_eclipselabs_simpleocl_PropertyCall{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createPropertyCallExp();
startIncompleteElement(element);
}
if (a0_0 != null) {
if (a0_0 != null) {
	Object value = a0_0;
	addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__CALLS, value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_45_0_0_1, a0_0, true);
copyLocalizationInfos(a0_0, element);
}
}
)

)+{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2378]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2379]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2380]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2381]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2382]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2383]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2384]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2385]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2386]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2387]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2388]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2389]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2390]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2391]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2392]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2393]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2394]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2395]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2396]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2397]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2398]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2399]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2400]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2401]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2402]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2403]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2404]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2405]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2406]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2407]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2408]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2409]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2410]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2411]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2412]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2413]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2414]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2415]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2416]);
}

{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createPropertyCallExp();
startIncompleteElement(element);
}
if (arg != null) {
if (arg != null) {
Object value = arg;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.PROPERTY_CALL_EXP__SOURCE), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_45_0_0_0, arg, true);
copyLocalizationInfos(arg, element);
}
}
|
/* epsilon */ { element = arg; } 
)
;

parseop_OclExpression_level_20 returns [org.eclipselabs.simpleocl.OclExpression element = null]
@init{
}
:
c0 = parse_org_eclipselabs_simpleocl_OclModelElementExp{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_eclipselabs_simpleocl_LambdaCallExp{ element = c1; /* this is a subclass or primitive expression choice */ }
|c2 = parse_org_eclipselabs_simpleocl_VariableExp{ element = c2; /* this is a subclass or primitive expression choice */ }
|c3 = parse_org_eclipselabs_simpleocl_SuperExp{ element = c3; /* this is a subclass or primitive expression choice */ }
|c4 = parse_org_eclipselabs_simpleocl_SelfExp{ element = c4; /* this is a subclass or primitive expression choice */ }
|c5 = parse_org_eclipselabs_simpleocl_EnvExp{ element = c5; /* this is a subclass or primitive expression choice */ }
|c6 = parse_org_eclipselabs_simpleocl_StringExp{ element = c6; /* this is a subclass or primitive expression choice */ }
|c7 = parse_org_eclipselabs_simpleocl_BooleanExp{ element = c7; /* this is a subclass or primitive expression choice */ }
|c8 = parse_org_eclipselabs_simpleocl_RealExp{ element = c8; /* this is a subclass or primitive expression choice */ }
|c9 = parse_org_eclipselabs_simpleocl_IntegerExp{ element = c9; /* this is a subclass or primitive expression choice */ }
|c10 = parse_org_eclipselabs_simpleocl_BagExp{ element = c10; /* this is a subclass or primitive expression choice */ }
|c11 = parse_org_eclipselabs_simpleocl_OrderedSetExp{ element = c11; /* this is a subclass or primitive expression choice */ }
|c12 = parse_org_eclipselabs_simpleocl_SequenceExp{ element = c12; /* this is a subclass or primitive expression choice */ }
|c13 = parse_org_eclipselabs_simpleocl_SetExp{ element = c13; /* this is a subclass or primitive expression choice */ }
|c14 = parse_org_eclipselabs_simpleocl_TupleExp{ element = c14; /* this is a subclass or primitive expression choice */ }
|c15 = parse_org_eclipselabs_simpleocl_MapExp{ element = c15; /* this is a subclass or primitive expression choice */ }
|c16 = parse_org_eclipselabs_simpleocl_EnumLiteralExp{ element = c16; /* this is a subclass or primitive expression choice */ }
|c17 = parse_org_eclipselabs_simpleocl_OclUndefinedExp{ element = c17; /* this is a subclass or primitive expression choice */ }
|c18 = parse_org_eclipselabs_simpleocl_LetExp{ element = c18; /* this is a subclass or primitive expression choice */ }
|c19 = parse_org_eclipselabs_simpleocl_IfExp{ element = c19; /* this is a subclass or primitive expression choice */ }
|c20 = parse_org_eclipselabs_simpleocl_BraceExp{ element = c20; /* this is a subclass or primitive expression choice */ }
|c21 = parse_org_eclipselabs_simpleocl_StaticPropertyCallExp{ element = c21; /* this is a subclass or primitive expression choice */ }
;

parse_org_eclipselabs_simpleocl_OclModelElementExp returns [org.eclipselabs.simpleocl.OclModelElementExp element = null]
@init{
}
:
(
a0 = TEXT
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclModelElementExp();
startIncompleteElement(element);
}
if (a0 != null) {
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
tokenResolver.setOptions(getOptions());
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__MODEL), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
}
String resolved = (String) resolvedObject;
org.eclipselabs.simpleocl.OclModel proxy = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclMetamodel();
collectHiddenTokens(element);
registerContextDependentProxy(new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContextDependentURIFragmentFactory<org.eclipselabs.simpleocl.OclModelElementExp, org.eclipselabs.simpleocl.OclModel>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOclModelElementExpModelReferenceResolver()), element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__MODEL), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__MODEL), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_10_0_0_0, proxy, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2417]);
}

a1 = '!' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclModelElementExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_10_0_0_1, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2418]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2419]);
}

(
(
a2 = TEXT
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclModelElementExp();
startIncompleteElement(element);
}
if (a2 != null) {
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
tokenResolver.setOptions(getOptions());
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a2).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_10_0_0_2_0_0_0, resolved, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a2, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2420]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2421]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2422]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2423]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2424]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2425]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2426]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2427]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2428]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2429]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2430]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2431]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2432]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2433]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2434]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2435]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2436]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2437]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2438]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2439]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2440]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2441]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2442]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2443]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2444]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2445]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2446]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2447]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2448]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2449]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2450]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2451]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2452]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2453]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2454]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2455]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2456]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2457]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2458]);
}


|(
a3 = QUOTED_34_34_92
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclModelElementExp();
startIncompleteElement(element);
}
if (a3 != null) {
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
tokenResolver.setOptions(getOptions());
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a3).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a3).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a3).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a3).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.OCL_MODEL_ELEMENT_EXP__NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_10_0_0_2_0_1_0, resolved, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a3, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2459]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2460]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2461]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2462]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2463]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2464]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2465]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2466]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2467]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2468]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2469]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2470]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2471]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2472]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2473]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2474]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2475]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2476]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2477]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2478]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2479]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2480]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2481]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2482]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2483]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2484]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2485]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2486]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2487]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2488]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2489]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2490]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2491]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2492]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2493]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2494]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2495]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2496]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2497]);
}

)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2498]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2499]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2500]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2501]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2502]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2503]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2504]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2505]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2506]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2507]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2508]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2509]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2510]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2511]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2512]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2513]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2514]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2515]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2516]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2517]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2518]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2519]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2520]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2521]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2522]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2523]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2524]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2525]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2526]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2527]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2528]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2529]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2530]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2531]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2532]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2533]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2534]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2535]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2536]);
}

;

parse_org_eclipselabs_simpleocl_LambdaCallExp returns [org.eclipselabs.simpleocl.LambdaCallExp element = null]
@init{
}
:
(
a0 = TEXT
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaCallExp();
startIncompleteElement(element);
}
if (a0 != null) {
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
tokenResolver.setOptions(getOptions());
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__REFERRED_VARIABLE), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
}
String resolved = (String) resolvedObject;
org.eclipselabs.simpleocl.VariableDeclaration proxy = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTuplePart();
collectHiddenTokens(element);
registerContextDependentProxy(new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContextDependentURIFragmentFactory<org.eclipselabs.simpleocl.VariableExp, org.eclipselabs.simpleocl.VariableDeclaration>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getVariableExpReferredVariableReferenceResolver()), element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__REFERRED_VARIABLE), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__REFERRED_VARIABLE), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_11_0_0_0, proxy, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2537]);
}

a1 = '(' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaCallExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_11_0_0_1, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2538]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2539]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2540]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2541]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2542]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2543]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2544]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2545]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2546]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2547]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2548]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2549]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2550]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2551]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2552]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2553]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2554]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2555]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2556]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2557]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2558]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2559]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2560]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2561]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2562]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2563]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2564]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2565]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2566]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2567]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2568]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2569]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2570]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2571]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2572]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2573]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2574]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2575]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2576]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2577]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2578]);
}

(
(
(
a2_0 = parse_org_eclipselabs_simpleocl_OclExpression{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaCallExp();
startIncompleteElement(element);
}
if (a2_0 != null) {
if (a2_0 != null) {
	Object value = a2_0;
	addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__ARGUMENTS, value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_11_0_0_2_0_0_0, a2_0, true);
copyLocalizationInfos(a2_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2579]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2580]);
}

(
(
a3 = ',' {
if (element == null) {
	element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaCallExp();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_11_0_0_2_0_0_1_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2581]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2582]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2583]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2584]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2585]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2586]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2587]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2588]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2589]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2590]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2591]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2592]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2593]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2594]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2595]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2596]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2597]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2598]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2599]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2600]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2601]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2602]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2603]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2604]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2605]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2606]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2607]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2608]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2609]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2610]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2611]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2612]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2613]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2614]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2615]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2616]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2617]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2618]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2619]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLambdaCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2620]);
}

(
a4_0 = parse_org_eclipselabs_simpleocl_OclExpression{
	if (terminateParsing) {
		throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
	}
	if (element == null) {
		element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaCallExp();
		startIncompleteElement(element);
	}
	if (a4_0 != null) {
		if (a4_0 != null) {
			Object value = a4_0;
			addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.LAMBDA_CALL_EXP__ARGUMENTS, value);
			completedElement(value, true);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_11_0_0_2_0_0_1_0_0_1, a4_0, true);
		copyLocalizationInfos(a4_0, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2621]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2622]);
}

)

)*{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2623]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2624]);
}

)

)?{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2625]);
}

a5 = ')' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLambdaCallExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_11_0_0_3, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2626]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2627]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2628]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2629]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2630]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2631]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2632]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2633]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2634]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2635]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2636]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2637]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2638]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2639]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2640]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2641]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2642]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2643]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2644]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2645]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2646]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2647]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2648]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2649]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2650]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2651]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2652]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2653]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2654]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2655]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2656]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2657]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2658]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2659]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2660]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2661]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2662]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2663]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2664]);
}

;

parse_org_eclipselabs_simpleocl_VariableExp returns [org.eclipselabs.simpleocl.VariableExp element = null]
@init{
}
:
(
a0 = TEXT
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createVariableExp();
startIncompleteElement(element);
}
if (a0 != null) {
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
tokenResolver.setOptions(getOptions());
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.VARIABLE_EXP__REFERRED_VARIABLE), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
}
String resolved = (String) resolvedObject;
org.eclipselabs.simpleocl.VariableDeclaration proxy = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTuplePart();
collectHiddenTokens(element);
registerContextDependentProxy(new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclContextDependentURIFragmentFactory<org.eclipselabs.simpleocl.VariableExp, org.eclipselabs.simpleocl.VariableDeclaration>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getVariableExpReferredVariableReferenceResolver()), element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.VARIABLE_EXP__REFERRED_VARIABLE), resolved, proxy);
if (proxy != null) {
Object value = proxy;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.VARIABLE_EXP__REFERRED_VARIABLE), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_12_0_0_0, proxy, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, proxy);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2665]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2666]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2667]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2668]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2669]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2670]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2671]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2672]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2673]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2674]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2675]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2676]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2677]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2678]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2679]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2680]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2681]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2682]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2683]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2684]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2685]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2686]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2687]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2688]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2689]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2690]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2691]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2692]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2693]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2694]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2695]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2696]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2697]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2698]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2699]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2700]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2701]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2702]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2703]);
}

;

parse_org_eclipselabs_simpleocl_SuperExp returns [org.eclipselabs.simpleocl.SuperExp element = null]
@init{
}
:
a0 = 'super' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSuperExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_13_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2704]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2705]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2706]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2707]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2708]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2709]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2710]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2711]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2712]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2713]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2714]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2715]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2716]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2717]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2718]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2719]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2720]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2721]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2722]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2723]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2724]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2725]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2726]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2727]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2728]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2729]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2730]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2731]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2732]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2733]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2734]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2735]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2736]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2737]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2738]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2739]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2740]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2741]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2742]);
}

;

parse_org_eclipselabs_simpleocl_SelfExp returns [org.eclipselabs.simpleocl.SelfExp element = null]
@init{
}
:
a0 = 'self' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSelfExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_14_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2743]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2744]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2745]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2746]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2747]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2748]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2749]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2750]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2751]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2752]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2753]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2754]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2755]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2756]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2757]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2758]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2759]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2760]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2761]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2762]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2763]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2764]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2765]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2766]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2767]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2768]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2769]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2770]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2771]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2772]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2773]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2774]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2775]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2776]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2777]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2778]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2779]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2780]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2781]);
}

;

parse_org_eclipselabs_simpleocl_EnvExp returns [org.eclipselabs.simpleocl.EnvExp element = null]
@init{
}
:
a0 = 'env' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createEnvExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_15_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2782]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2783]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2784]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2785]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2786]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2787]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2788]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2789]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2790]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2791]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2792]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2793]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2794]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2795]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2796]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2797]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2798]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2799]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2800]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2801]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2802]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2803]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2804]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2805]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2806]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2807]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2808]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2809]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2810]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2811]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2812]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2813]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2814]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2815]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2816]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2817]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2818]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2819]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2820]);
}

;

parse_org_eclipselabs_simpleocl_StringExp returns [org.eclipselabs.simpleocl.StringExp element = null]
@init{
}
:
(
a0 = QUOTED_39_39_92
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStringExp();
startIncompleteElement(element);
}
if (a0 != null) {
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_39_39_92");
tokenResolver.setOptions(getOptions());
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STRING_EXP__STRING_SYMBOL), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STRING_EXP__STRING_SYMBOL), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_16_0_0_0, resolved, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2821]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2822]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2823]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2824]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2825]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2826]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2827]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2828]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2829]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2830]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2831]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2832]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2833]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2834]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2835]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2836]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2837]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2838]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2839]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2840]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2841]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2842]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2843]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2844]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2845]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2846]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2847]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2848]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2849]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2850]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2851]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2852]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2853]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2854]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2855]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2856]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2857]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2858]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2859]);
}

;

parse_org_eclipselabs_simpleocl_BooleanExp returns [org.eclipselabs.simpleocl.BooleanExp element = null]
@init{
}
:
(
(
a0 = 'true' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBooleanExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_17_0_0_0, true, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
// set value of boolean attribute
Object value = true;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.BOOLEAN_EXP__BOOLEAN_SYMBOL), value);
completedElement(value, false);
}
|a1 = 'false' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBooleanExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_17_0_0_0, false, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
// set value of boolean attribute
Object value = false;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.BOOLEAN_EXP__BOOLEAN_SYMBOL), value);
completedElement(value, false);
}
)
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2860]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2861]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2862]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2863]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2864]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2865]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2866]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2867]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2868]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2869]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2870]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2871]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2872]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2873]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2874]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2875]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2876]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2877]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2878]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2879]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2880]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2881]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2882]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2883]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2884]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2885]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2886]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2887]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2888]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2889]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2890]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2891]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2892]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2893]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2894]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2895]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2896]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2897]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2898]);
}

;

parse_org_eclipselabs_simpleocl_RealExp returns [org.eclipselabs.simpleocl.RealExp element = null]
@init{
}
:
(
a0 = FLOAT
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createRealExp();
startIncompleteElement(element);
}
if (a0 != null) {
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("FLOAT");
tokenResolver.setOptions(getOptions());
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.REAL_EXP__REAL_SYMBOL), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
}
java.lang.Double resolved = (java.lang.Double) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.REAL_EXP__REAL_SYMBOL), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_18_0_0_0, resolved, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2899]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2900]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2901]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2902]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2903]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2904]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2905]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2906]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2907]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2908]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2909]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2910]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2911]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2912]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2913]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2914]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2915]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2916]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2917]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2918]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2919]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2920]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2921]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2922]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2923]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2924]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2925]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2926]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2927]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2928]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2929]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2930]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2931]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2932]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2933]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2934]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2935]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2936]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2937]);
}

;

parse_org_eclipselabs_simpleocl_IntegerExp returns [org.eclipselabs.simpleocl.IntegerExp element = null]
@init{
}
:
(
a0 = INTEGER
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIntegerExp();
startIncompleteElement(element);
}
if (a0 != null) {
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("INTEGER");
tokenResolver.setOptions(getOptions());
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.INTEGER_EXP__INTEGER_SYMBOL), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a0).getStopIndex());
}
java.lang.Integer resolved = (java.lang.Integer) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.INTEGER_EXP__INTEGER_SYMBOL), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_19_0_0_0, resolved, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2938]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2939]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2940]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2941]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2942]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2943]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2944]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2945]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2946]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2947]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2948]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2949]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2950]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2951]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2952]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2953]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2954]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2955]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2956]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2957]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2958]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2959]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2960]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2961]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2962]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2963]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2964]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2965]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2966]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2967]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2968]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2969]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2970]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2971]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2972]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2973]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2974]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2975]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2976]);
}

;

parse_org_eclipselabs_simpleocl_BagExp returns [org.eclipselabs.simpleocl.BagExp element = null]
@init{
}
:
a0 = 'Bag' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBagExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_22_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2977]);
}

a1 = '{' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBagExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_22_0_0_1, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2978]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2979]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2980]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2981]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2982]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2983]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2984]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2985]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2986]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2987]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2988]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2989]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2990]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2991]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2992]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2993]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2994]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2995]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2996]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2997]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2998]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[2999]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3000]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3001]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3002]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3003]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3004]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3005]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3006]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3007]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3008]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3009]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3010]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3011]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3012]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3013]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3014]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3015]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3016]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3017]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3018]);
}

(
(
(
a2_0 = parse_org_eclipselabs_simpleocl_CollectionPart{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBagExp();
startIncompleteElement(element);
}
if (a2_0 != null) {
if (a2_0 != null) {
	Object value = a2_0;
	addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.BAG_EXP__PARTS, value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_22_0_0_2_0_0_0, a2_0, true);
copyLocalizationInfos(a2_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3019]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3020]);
}

(
(
a3 = ',' {
if (element == null) {
	element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBagExp();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_22_0_0_2_0_0_1_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3021]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3022]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3023]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3024]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3025]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3026]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3027]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3028]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3029]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3030]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3031]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3032]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3033]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3034]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3035]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3036]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3037]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3038]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3039]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3040]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3041]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3042]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3043]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3044]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3045]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3046]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3047]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3048]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3049]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3050]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3051]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3052]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3053]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3054]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3055]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3056]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3057]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3058]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3059]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBagExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3060]);
}

(
a4_0 = parse_org_eclipselabs_simpleocl_CollectionPart{
	if (terminateParsing) {
		throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
	}
	if (element == null) {
		element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBagExp();
		startIncompleteElement(element);
	}
	if (a4_0 != null) {
		if (a4_0 != null) {
			Object value = a4_0;
			addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.BAG_EXP__PARTS, value);
			completedElement(value, true);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_22_0_0_2_0_0_1_0_0_1, a4_0, true);
		copyLocalizationInfos(a4_0, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3061]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3062]);
}

)

)*{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3063]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3064]);
}

)

)?{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3065]);
}

a5 = '}' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBagExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_22_0_0_3, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3066]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3067]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3068]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3069]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3070]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3071]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3072]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3073]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3074]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3075]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3076]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3077]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3078]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3079]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3080]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3081]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3082]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3083]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3084]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3085]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3086]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3087]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3088]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3089]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3090]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3091]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3092]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3093]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3094]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3095]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3096]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3097]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3098]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3099]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3100]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3101]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3102]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3103]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3104]);
}

;

parse_org_eclipselabs_simpleocl_OrderedSetExp returns [org.eclipselabs.simpleocl.OrderedSetExp element = null]
@init{
}
:
a0 = 'OrderedSet' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOrderedSetExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_23_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3105]);
}

a1 = '{' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOrderedSetExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_23_0_0_1, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3106]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3107]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3108]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3109]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3110]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3111]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3112]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3113]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3114]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3115]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3116]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3117]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3118]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3119]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3120]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3121]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3122]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3123]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3124]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3125]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3126]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3127]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3128]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3129]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3130]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3131]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3132]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3133]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3134]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3135]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3136]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3137]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3138]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3139]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3140]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3141]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3142]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3143]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3144]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3145]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3146]);
}

(
(
(
a2_0 = parse_org_eclipselabs_simpleocl_CollectionPart{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOrderedSetExp();
startIncompleteElement(element);
}
if (a2_0 != null) {
if (a2_0 != null) {
	Object value = a2_0;
	addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__PARTS, value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_23_0_0_2_0_0_0, a2_0, true);
copyLocalizationInfos(a2_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3147]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3148]);
}

(
(
a3 = ',' {
if (element == null) {
	element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOrderedSetExp();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_23_0_0_2_0_0_1_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3149]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3150]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3151]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3152]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3153]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3154]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3155]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3156]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3157]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3158]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3159]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3160]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3161]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3162]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3163]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3164]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3165]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3166]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3167]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3168]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3169]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3170]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3171]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3172]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3173]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3174]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3175]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3176]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3177]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3178]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3179]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3180]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3181]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3182]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3183]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3184]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3185]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3186]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3187]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getOrderedSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3188]);
}

(
a4_0 = parse_org_eclipselabs_simpleocl_CollectionPart{
	if (terminateParsing) {
		throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
	}
	if (element == null) {
		element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOrderedSetExp();
		startIncompleteElement(element);
	}
	if (a4_0 != null) {
		if (a4_0 != null) {
			Object value = a4_0;
			addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.ORDERED_SET_EXP__PARTS, value);
			completedElement(value, true);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_23_0_0_2_0_0_1_0_0_1, a4_0, true);
		copyLocalizationInfos(a4_0, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3189]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3190]);
}

)

)*{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3191]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3192]);
}

)

)?{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3193]);
}

a5 = '}' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOrderedSetExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_23_0_0_3, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3194]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3195]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3196]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3197]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3198]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3199]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3200]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3201]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3202]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3203]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3204]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3205]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3206]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3207]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3208]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3209]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3210]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3211]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3212]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3213]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3214]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3215]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3216]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3217]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3218]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3219]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3220]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3221]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3222]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3223]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3224]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3225]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3226]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3227]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3228]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3229]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3230]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3231]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3232]);
}

;

parse_org_eclipselabs_simpleocl_SequenceExp returns [org.eclipselabs.simpleocl.SequenceExp element = null]
@init{
}
:
a0 = 'Sequence' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSequenceExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_24_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3233]);
}

a1 = '{' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSequenceExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_24_0_0_1, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3234]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3235]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3236]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3237]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3238]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3239]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3240]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3241]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3242]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3243]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3244]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3245]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3246]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3247]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3248]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3249]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3250]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3251]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3252]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3253]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3254]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3255]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3256]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3257]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3258]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3259]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3260]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3261]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3262]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3263]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3264]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3265]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3266]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3267]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3268]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3269]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3270]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3271]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3272]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3273]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3274]);
}

(
(
(
a2_0 = parse_org_eclipselabs_simpleocl_CollectionPart{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSequenceExp();
startIncompleteElement(element);
}
if (a2_0 != null) {
if (a2_0 != null) {
	Object value = a2_0;
	addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.SEQUENCE_EXP__PARTS, value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_24_0_0_2_0_0_0, a2_0, true);
copyLocalizationInfos(a2_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3275]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3276]);
}

(
(
a3 = ',' {
if (element == null) {
	element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSequenceExp();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_24_0_0_2_0_0_1_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3277]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3278]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3279]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3280]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3281]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3282]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3283]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3284]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3285]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3286]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3287]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3288]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3289]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3290]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3291]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3292]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3293]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3294]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3295]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3296]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3297]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3298]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3299]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3300]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3301]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3302]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3303]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3304]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3305]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3306]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3307]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3308]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3309]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3310]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3311]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3312]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3313]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3314]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3315]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSequenceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3316]);
}

(
a4_0 = parse_org_eclipselabs_simpleocl_CollectionPart{
	if (terminateParsing) {
		throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
	}
	if (element == null) {
		element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSequenceExp();
		startIncompleteElement(element);
	}
	if (a4_0 != null) {
		if (a4_0 != null) {
			Object value = a4_0;
			addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.SEQUENCE_EXP__PARTS, value);
			completedElement(value, true);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_24_0_0_2_0_0_1_0_0_1, a4_0, true);
		copyLocalizationInfos(a4_0, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3317]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3318]);
}

)

)*{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3319]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3320]);
}

)

)?{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3321]);
}

a5 = '}' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSequenceExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_24_0_0_3, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3322]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3323]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3324]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3325]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3326]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3327]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3328]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3329]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3330]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3331]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3332]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3333]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3334]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3335]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3336]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3337]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3338]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3339]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3340]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3341]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3342]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3343]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3344]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3345]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3346]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3347]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3348]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3349]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3350]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3351]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3352]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3353]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3354]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3355]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3356]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3357]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3358]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3359]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3360]);
}

;

parse_org_eclipselabs_simpleocl_SetExp returns [org.eclipselabs.simpleocl.SetExp element = null]
@init{
}
:
a0 = 'Set' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSetExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_25_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3361]);
}

a1 = '{' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSetExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_25_0_0_1, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3362]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3363]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3364]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3365]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3366]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3367]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3368]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3369]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3370]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3371]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3372]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3373]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3374]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3375]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3376]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3377]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3378]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3379]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3380]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3381]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3382]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3383]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3384]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3385]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3386]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3387]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3388]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3389]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3390]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3391]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3392]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3393]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3394]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3395]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3396]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3397]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3398]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3399]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3400]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3401]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3402]);
}

(
(
(
a2_0 = parse_org_eclipselabs_simpleocl_CollectionPart{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSetExp();
startIncompleteElement(element);
}
if (a2_0 != null) {
if (a2_0 != null) {
	Object value = a2_0;
	addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.SET_EXP__PARTS, value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_25_0_0_2_0_0_0, a2_0, true);
copyLocalizationInfos(a2_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3403]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3404]);
}

(
(
a3 = ',' {
if (element == null) {
	element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSetExp();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_25_0_0_2_0_0_1_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3405]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3406]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3407]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3408]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3409]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3410]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3411]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3412]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3413]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3414]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3415]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3416]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3417]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3418]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3419]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3420]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3421]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3422]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3423]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3424]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3425]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3426]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3427]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3428]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3429]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3430]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3431]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3432]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3433]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3434]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3435]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3436]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3437]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3438]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3439]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3440]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3441]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3442]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3443]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getSetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3444]);
}

(
a4_0 = parse_org_eclipselabs_simpleocl_CollectionPart{
	if (terminateParsing) {
		throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
	}
	if (element == null) {
		element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSetExp();
		startIncompleteElement(element);
	}
	if (a4_0 != null) {
		if (a4_0 != null) {
			Object value = a4_0;
			addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.SET_EXP__PARTS, value);
			completedElement(value, true);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_25_0_0_2_0_0_1_0_0_1, a4_0, true);
		copyLocalizationInfos(a4_0, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3445]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3446]);
}

)

)*{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3447]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3448]);
}

)

)?{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3449]);
}

a5 = '}' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createSetExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_25_0_0_3, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3450]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3451]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3452]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3453]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3454]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3455]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3456]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3457]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3458]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3459]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3460]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3461]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3462]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3463]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3464]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3465]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3466]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3467]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3468]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3469]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3470]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3471]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3472]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3473]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3474]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3475]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3476]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3477]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3478]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3479]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3480]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3481]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3482]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3483]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3484]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3485]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3486]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3487]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3488]);
}

;

parse_org_eclipselabs_simpleocl_TupleExp returns [org.eclipselabs.simpleocl.TupleExp element = null]
@init{
}
:
a0 = 'Tuple' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_26_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3489]);
}

a1 = '{' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_26_0_0_1, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3490]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3491]);
}

(
(
(
a2_0 = parse_org_eclipselabs_simpleocl_TuplePart{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleExp();
startIncompleteElement(element);
}
if (a2_0 != null) {
if (a2_0 != null) {
	Object value = a2_0;
	addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_EXP__TUPLE_PART, value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_26_0_0_2_0_0_0, a2_0, true);
copyLocalizationInfos(a2_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3492]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3493]);
}

(
(
a3 = ',' {
if (element == null) {
	element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleExp();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_26_0_0_2_0_0_1_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getTupleExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3494]);
}

(
a4_0 = parse_org_eclipselabs_simpleocl_TuplePart{
	if (terminateParsing) {
		throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
	}
	if (element == null) {
		element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleExp();
		startIncompleteElement(element);
	}
	if (a4_0 != null) {
		if (a4_0 != null) {
			Object value = a4_0;
			addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.TUPLE_EXP__TUPLE_PART, value);
			completedElement(value, true);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_26_0_0_2_0_0_1_0_0_1, a4_0, true);
		copyLocalizationInfos(a4_0, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3495]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3496]);
}

)

)*{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3497]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3498]);
}

)

)?{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3499]);
}

a5 = '}' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createTupleExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_26_0_0_3, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3500]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3501]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3502]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3503]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3504]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3505]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3506]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3507]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3508]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3509]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3510]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3511]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3512]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3513]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3514]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3515]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3516]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3517]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3518]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3519]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3520]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3521]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3522]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3523]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3524]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3525]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3526]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3527]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3528]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3529]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3530]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3531]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3532]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3533]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3534]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3535]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3536]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3537]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3538]);
}

;

parse_org_eclipselabs_simpleocl_MapExp returns [org.eclipselabs.simpleocl.MapExp element = null]
@init{
}
:
a0 = 'Map' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_28_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3539]);
}

a1 = '{' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_28_0_0_1, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a1, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3540]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3541]);
}

(
(
(
a2_0 = parse_org_eclipselabs_simpleocl_MapElement{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapExp();
startIncompleteElement(element);
}
if (a2_0 != null) {
if (a2_0 != null) {
	Object value = a2_0;
	addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.MAP_EXP__ELEMENTS, value);
	completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_28_0_0_2_0_0_0, a2_0, true);
copyLocalizationInfos(a2_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3542]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3543]);
}

(
(
a3 = ',' {
if (element == null) {
	element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapExp();
	startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_28_0_0_2_0_0_1_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a3, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getMapExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3544]);
}

(
a4_0 = parse_org_eclipselabs_simpleocl_MapElement{
	if (terminateParsing) {
		throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
	}
	if (element == null) {
		element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapExp();
		startIncompleteElement(element);
	}
	if (a4_0 != null) {
		if (a4_0 != null) {
			Object value = a4_0;
			addObjectToList(element, org.eclipselabs.simpleocl.SimpleoclPackage.MAP_EXP__ELEMENTS, value);
			completedElement(value, true);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_28_0_0_2_0_0_1_0_0_1, a4_0, true);
		copyLocalizationInfos(a4_0, element);
	}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3545]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3546]);
}

)

)*{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3547]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3548]);
}

)

)?{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3549]);
}

a5 = '}' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createMapExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_28_0_0_3, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a5, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3550]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3551]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3552]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3553]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3554]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3555]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3556]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3557]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3558]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3559]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3560]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3561]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3562]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3563]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3564]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3565]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3566]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3567]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3568]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3569]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3570]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3571]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3572]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3573]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3574]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3575]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3576]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3577]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3578]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3579]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3580]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3581]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3582]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3583]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3584]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3585]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3586]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3587]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3588]);
}

;

parse_org_eclipselabs_simpleocl_EnumLiteralExp returns [org.eclipselabs.simpleocl.EnumLiteralExp element = null]
@init{
}
:
a0 = '#' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createEnumLiteralExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_30_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3589]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3590]);
}

(
(
a1 = TEXT
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createEnumLiteralExp();
startIncompleteElement(element);
}
if (a1 != null) {
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("TEXT");
tokenResolver.setOptions(getOptions());
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a1).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a1).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_30_0_0_1_0_0_0, resolved, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a1, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3591]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3592]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3593]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3594]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3595]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3596]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3597]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3598]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3599]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3600]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3601]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3602]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3603]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3604]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3605]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3606]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3607]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3608]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3609]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3610]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3611]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3612]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3613]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3614]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3615]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3616]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3617]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3618]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3619]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3620]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3621]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3622]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3623]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3624]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3625]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3626]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3627]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3628]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3629]);
}


|(
a2 = QUOTED_34_34_92
{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createEnumLiteralExp();
startIncompleteElement(element);
}
if (a2 != null) {
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QUOTED_34_34_92");
tokenResolver.setOptions(getOptions());
org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolveResult result = getFreshTokenResolveResult();
tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__NAME), result);
Object resolvedObject = result.getResolvedToken();
if (resolvedObject == null) {
addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_4_0.CommonToken) a2).getLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getCharPositionInLine(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStartIndex(), ((org.antlr.runtime3_4_0.CommonToken) a2).getStopIndex());
}
java.lang.String resolved = (java.lang.String) resolvedObject;
if (resolved != null) {
Object value = resolved;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.ENUM_LITERAL_EXP__NAME), value);
completedElement(value, false);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_30_0_0_1_0_1_0, resolved, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken) a2, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3630]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3631]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3632]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3633]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3634]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3635]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3636]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3637]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3638]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3639]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3640]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3641]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3642]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3643]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3644]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3645]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3646]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3647]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3648]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3649]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3650]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3651]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3652]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3653]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3654]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3655]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3656]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3657]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3658]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3659]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3660]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3661]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3662]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3663]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3664]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3665]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3666]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3667]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3668]);
}

)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3669]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3670]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3671]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3672]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3673]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3674]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3675]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3676]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3677]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3678]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3679]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3680]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3681]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3682]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3683]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3684]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3685]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3686]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3687]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3688]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3689]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3690]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3691]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3692]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3693]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3694]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3695]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3696]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3697]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3698]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3699]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3700]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3701]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3702]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3703]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3704]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3705]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3706]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3707]);
}

;

parse_org_eclipselabs_simpleocl_OclUndefinedExp returns [org.eclipselabs.simpleocl.OclUndefinedExp element = null]
@init{
}
:
a0 = 'OclUndefined' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createOclUndefinedExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_31_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3708]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3709]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3710]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3711]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3712]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3713]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3714]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3715]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3716]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3717]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3718]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3719]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3720]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3721]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3722]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3723]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3724]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3725]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3726]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3727]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3728]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3729]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3730]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3731]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3732]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3733]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3734]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3735]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3736]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3737]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3738]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3739]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3740]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3741]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3742]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3743]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3744]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3745]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3746]);
}

;

parse_org_eclipselabs_simpleocl_LetExp returns [org.eclipselabs.simpleocl.LetExp element = null]
@init{
}
:
a0 = 'let' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLetExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_32_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3747]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3748]);
}

(
a1_0 = parse_org_eclipselabs_simpleocl_LocalVariable{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLetExp();
startIncompleteElement(element);
}
if (a1_0 != null) {
if (a1_0 != null) {
Object value = a1_0;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LET_EXP__VARIABLE), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_32_0_0_1, a1_0, true);
copyLocalizationInfos(a1_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3749]);
}

a2 = 'in' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLetExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_32_0_0_2, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3750]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3751]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3752]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3753]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3754]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3755]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3756]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3757]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3758]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3759]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3760]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3761]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3762]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3763]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3764]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3765]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3766]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3767]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3768]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3769]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3770]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3771]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3772]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3773]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3774]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3775]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3776]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3777]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3778]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3779]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3780]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3781]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3782]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3783]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3784]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3785]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3786]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3787]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3788]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getLetExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3789]);
}

(
a3_0 = parse_org_eclipselabs_simpleocl_OclExpression{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createLetExp();
startIncompleteElement(element);
}
if (a3_0 != null) {
if (a3_0 != null) {
Object value = a3_0;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.LET_EXP__IN_), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_32_0_0_3, a3_0, true);
copyLocalizationInfos(a3_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3790]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3791]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3792]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3793]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3794]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3795]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3796]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3797]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3798]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3799]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3800]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3801]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3802]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3803]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3804]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3805]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3806]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3807]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3808]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3809]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3810]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3811]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3812]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3813]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3814]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3815]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3816]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3817]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3818]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3819]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3820]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3821]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3822]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3823]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3824]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3825]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3826]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3827]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3828]);
}

;

parse_org_eclipselabs_simpleocl_IfExp returns [org.eclipselabs.simpleocl.IfExp element = null]
@init{
}
:
a0 = 'if' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIfExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_33_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3829]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3830]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3831]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3832]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3833]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3834]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3835]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3836]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3837]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3838]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3839]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3840]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3841]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3842]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3843]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3844]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3845]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3846]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3847]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3848]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3849]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3850]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3851]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3852]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3853]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3854]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3855]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3856]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3857]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3858]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3859]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3860]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3861]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3862]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3863]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3864]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3865]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3866]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3867]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3868]);
}

(
a1_0 = parse_org_eclipselabs_simpleocl_OclExpression{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIfExp();
startIncompleteElement(element);
}
if (a1_0 != null) {
if (a1_0 != null) {
Object value = a1_0;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.IF_EXP__CONDITION), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_33_0_0_1, a1_0, true);
copyLocalizationInfos(a1_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3869]);
}

a2 = 'then' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIfExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_33_0_0_2, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3870]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3871]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3872]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3873]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3874]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3875]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3876]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3877]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3878]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3879]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3880]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3881]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3882]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3883]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3884]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3885]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3886]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3887]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3888]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3889]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3890]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3891]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3892]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3893]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3894]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3895]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3896]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3897]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3898]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3899]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3900]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3901]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3902]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3903]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3904]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3905]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3906]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3907]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3908]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3909]);
}

(
a3_0 = parse_org_eclipselabs_simpleocl_OclExpression{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIfExp();
startIncompleteElement(element);
}
if (a3_0 != null) {
if (a3_0 != null) {
Object value = a3_0;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.IF_EXP__THEN_EXPRESSION), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_33_0_0_3, a3_0, true);
copyLocalizationInfos(a3_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3910]);
}

a4 = 'else' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIfExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_33_0_0_4, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a4, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3911]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3912]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3913]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3914]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3915]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3916]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3917]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3918]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3919]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3920]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3921]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3922]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3923]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3924]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3925]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3926]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3927]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3928]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3929]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3930]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3931]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3932]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3933]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3934]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3935]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3936]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3937]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3938]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3939]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3940]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3941]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3942]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3943]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3944]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3945]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3946]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3947]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3948]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3949]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getIfExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3950]);
}

(
a5_0 = parse_org_eclipselabs_simpleocl_OclExpression{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIfExp();
startIncompleteElement(element);
}
if (a5_0 != null) {
if (a5_0 != null) {
Object value = a5_0;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.IF_EXP__ELSE_EXPRESSION), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_33_0_0_5, a5_0, true);
copyLocalizationInfos(a5_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3951]);
}

a6 = 'endif' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createIfExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_33_0_0_6, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a6, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3952]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3953]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3954]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3955]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3956]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3957]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3958]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3959]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3960]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3961]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3962]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3963]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3964]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3965]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3966]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3967]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3968]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3969]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3970]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3971]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3972]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3973]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3974]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3975]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3976]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3977]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3978]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3979]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3980]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3981]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3982]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3983]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3984]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3985]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3986]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3987]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3988]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3989]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3990]);
}

;

parse_org_eclipselabs_simpleocl_BraceExp returns [org.eclipselabs.simpleocl.BraceExp element = null]
@init{
}
:
a0 = '(' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBraceExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_34_0_0_0, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a0, element);
}
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3991]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3992]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3993]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3994]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3995]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3996]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3997]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3998]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[3999]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4000]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4001]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4002]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4003]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4004]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4005]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4006]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4007]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4008]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4009]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4010]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4011]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4012]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4013]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4014]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4015]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4016]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4017]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4018]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4019]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4020]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4021]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4022]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4023]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4024]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4025]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4026]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4027]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4028]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4029]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getBraceExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4030]);
}

(
a1_0 = parse_org_eclipselabs_simpleocl_OclExpression{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBraceExp();
startIncompleteElement(element);
}
if (a1_0 != null) {
if (a1_0 != null) {
Object value = a1_0;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.BRACE_EXP__EXP), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_34_0_0_1, a1_0, true);
copyLocalizationInfos(a1_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4031]);
}

a2 = ')' {
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createBraceExp();
startIncompleteElement(element);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_34_0_0_2, null, true);
copyLocalizationInfos((org.antlr.runtime3_4_0.CommonToken)a2, element);
}
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4032]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4033]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4034]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4035]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4036]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4037]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4038]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4039]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4040]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4041]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4042]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4043]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4044]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4045]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4046]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4047]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4048]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4049]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4050]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4051]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4052]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4053]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4054]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4055]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4056]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4057]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4058]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4059]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4060]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4061]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4062]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4063]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4064]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4065]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4066]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4067]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4068]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4069]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4070]);
}

;

parse_org_eclipselabs_simpleocl_StaticPropertyCallExp returns [org.eclipselabs.simpleocl.StaticPropertyCallExp element = null]
@init{
}
:
(
a0_0 = parse_org_eclipselabs_simpleocl_OclType{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticPropertyCallExp();
startIncompleteElement(element);
}
if (a0_0 != null) {
if (a0_0 != null) {
Object value = a0_0;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__SOURCE), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_42_0_0_0, a0_0, true);
copyLocalizationInfos(a0_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4071]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getStaticPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4072]);
}

(
a1_0 = parse_org_eclipselabs_simpleocl_StaticPropertyCall{
if (terminateParsing) {
throw new org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclTerminateParsingException();
}
if (element == null) {
element = org.eclipselabs.simpleocl.SimpleoclFactory.eINSTANCE.createStaticPropertyCallExp();
startIncompleteElement(element);
}
if (a1_0 != null) {
if (a1_0 != null) {
Object value = a1_0;
element.eSet(element.eClass().getEStructuralFeature(org.eclipselabs.simpleocl.SimpleoclPackage.STATIC_PROPERTY_CALL_EXP__STATIC_CALL), value);
completedElement(value, true);
}
collectHiddenTokens(element);
retrieveLayoutInformation(element, org.eclipselabs.simpleocl.resource.simpleocl.grammar.SimpleoclGrammarInformationProvider.SIMPLEOCL_42_0_0_1, a1_0, true);
copyLocalizationInfos(a1_0, element);
}
}
)
{
// expected elements (follow set)
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4073]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4074]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4075]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4076]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4077]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4078]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4079]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4080]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4081]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4082]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4083]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4084]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4085]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4086]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4087]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4088]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4089]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4090]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4091]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4092]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4093]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4094]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4095]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4096]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4097]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4098]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4099]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4100]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4101]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4102]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4103]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4104]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4105]);
addExpectedElement(org.eclipselabs.simpleocl.SimpleoclPackage.eINSTANCE.getPropertyCallExp(), org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4106]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4107]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4108]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4109]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4110]);
addExpectedElement(null, org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclExpectationConstants.EXPECTATIONS[4111]);
}

;

parse_org_eclipselabs_simpleocl_ModuleElement returns [org.eclipselabs.simpleocl.ModuleElement element = null]
:
c0 = parse_org_eclipselabs_simpleocl_OclFeatureDefinition{ element = c0; /* this is a subclass or primitive expression choice */ }

;

parse_org_eclipselabs_simpleocl_OclFeature returns [org.eclipselabs.simpleocl.OclFeature element = null]
:
c0 = parse_org_eclipselabs_simpleocl_Attribute{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_eclipselabs_simpleocl_Operation{ element = c1; /* this is a subclass or primitive expression choice */ }

;

parse_org_eclipselabs_simpleocl_OclExpression returns [org.eclipselabs.simpleocl.OclExpression element = null]
:
c = parseop_OclExpression_level_1{ element = c; /* this rule is an expression root */ }

;

parse_org_eclipselabs_simpleocl_PropertyCall returns [org.eclipselabs.simpleocl.PropertyCall element = null]
:
c0 = parse_org_eclipselabs_simpleocl_OperationCall{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_eclipselabs_simpleocl_NavigationOrAttributeCall{ element = c1; /* this is a subclass or primitive expression choice */ }
|c2 = parse_org_eclipselabs_simpleocl_IterateExp{ element = c2; /* this is a subclass or primitive expression choice */ }
|c3 = parse_org_eclipselabs_simpleocl_IteratorExp{ element = c3; /* this is a subclass or primitive expression choice */ }
|c4 = parse_org_eclipselabs_simpleocl_CollectionOperationCall{ element = c4; /* this is a subclass or primitive expression choice */ }

;

parse_org_eclipselabs_simpleocl_CollectionPart returns [org.eclipselabs.simpleocl.CollectionPart element = null]
:
c0 = parse_org_eclipselabs_simpleocl_CollectionRange{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_eclipselabs_simpleocl_CollectionItem{ element = c1; /* this is a subclass or primitive expression choice */ }

;

parse_org_eclipselabs_simpleocl_StaticPropertyCall returns [org.eclipselabs.simpleocl.StaticPropertyCall element = null]
:
c0 = parse_org_eclipselabs_simpleocl_StaticOperationCall{ element = c0; /* this is a subclass or primitive expression choice */ }
|c1 = parse_org_eclipselabs_simpleocl_StaticNavigationOrAttributeCall{ element = c1; /* this is a subclass or primitive expression choice */ }

;

NOTOP:
('not')
;
BOOLOP:
(('and'|'or'|'xor'|'implies'))
;
INTOP:
(('div'|'mod'))
;
RELOP:
(('>'|'<'|'>='|'<='))
;
EQ:
('=')
;
NEQ:
('<>')
;
ADDOP:
(('-'|'+'))
;
MULOP:
(('*'|'/'))
;
COMMENT:
('--'(~('\n'|'\r'|'\uffff'))*)
{ _channel = 99; }
;
FLOAT:
('-'?(('0'..'9'))+'.'(('0'..'9'))+)
;
INTEGER:
('-'?(('0'..'9'))+)
;
STRINGTYPE:
('String')
;
BOOLEANTYPE:
('Boolean')
;
INTEGERTYPE:
('Integer')
;
REALTYPE:
('Real')
;
COLLECTIONTYPE:
('Collection')
;
BAGTYPE:
'Bag';
ORDEREDSETTYPE:
'OrderedSet';
SEQUENCETYPE:
'Sequence';
SETTYPE:
'Set';
OCLANYTYPE:
('OclAny')
;
OCLTYPE:
('OclType')
;
TUPLETYPE:
('TupleType')
;
TUPLE:
'Tuple';
MAPTYPE:
'Map';
LAMBDATYPE:
('Lambda')
;
ENVTYPE:
('Env')
;
TEXT:
(('A'..'Z'|'a'..'z')(('A'..'Z'|'a'..'z')|('0'..'9')|'_')*)
;
LINEBREAK:
(('\r\n'|'\'r'|'\n'))
{ _channel = 99; }
;
WHITESPACE:
((' '|'\t'|'\f'))
{ _channel = 99; }
;
QUOTED_34_34_92:
(('"')(('\\''"')|('\\''\\')|(~('"'|'\\')))*('"'))
;
QUOTED_39_39_92:
(('\'')(('\\''\'')|('\\''\\')|(~('\''|'\\')))*('\''))
;

