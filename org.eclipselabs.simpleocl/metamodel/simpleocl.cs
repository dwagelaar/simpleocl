SYNTAXDEF simpleocl
FOR <http://eclipselabs.org/simpleocl/2013/SimpleOCL>
START Module

OPTIONS {
	usePredefinedTokens = "false";
	disableTokenSorting = "true";
	overrideResourceFactory = "false";
	overrideBuilder = "false";
	overrideBuilderAdapter = "false";
	overrideManifest = "false";
	disableLaunchSupport = "true";
	overrideLineBreakpoint = "false";
	overrideLineBreakpointAdapter = "false";
	overrideUIManifest = "false";
	overrideNewFileWizard = "false";
	overrideNewFileWizardPage = "false";
	overrideNewProjectWizard = "false";
	overrideUIPluginXML = "false";
	overrideProjectFile = "false";
	overrideBuildProperties = "false";
	overrideProposalPostProcessor = "false";
}

TOKENS {
	DEFINE FRAGMENT DIGIT $('0'..'9')$;
	DEFINE FRAGMENT CHAR $('A'..'Z'|'a'..'z')$;
	DEFINE NOTOP $'not'$;
	DEFINE BOOLOP $('and'|'or'|'xor'|'implies')$;
	DEFINE INTOP $('div'|'mod')$;
	DEFINE RELOP $('>'|'<'|'>='|'<=')$;
	DEFINE EQ $'='$;
	DEFINE NEQ $'<>'$;
	DEFINE ADDOP $('-'|'+')$;
	DEFINE MULOP $('*'|'/')$;
	DEFINE COMMENT $'--'(~('\n'|'\r'|'\uffff'))*$;
	DEFINE FLOAT INTEGER + $'.'($ + DIGIT + $)+$;
	DEFINE INTEGER $'-'?($ + DIGIT + $)+$;
	DEFINE STRINGTYPE $'String'$;
	DEFINE BOOLEANTYPE $'Boolean'$;
	DEFINE INTEGERTYPE $'Integer'$;
	DEFINE REALTYPE $'Real'$;
	DEFINE COLLECTIONTYPE $'Collection'$;
	DEFINE BAGTYPE $'Bag'$;
	DEFINE ORDEREDSETTYPE $'OrderedSet'$;
	DEFINE SEQUENCETYPE $'Sequence'$;
	DEFINE SETTYPE $'Set'$;
	DEFINE OCLANYTYPE $'OclAny'$;
	DEFINE OCLTYPE $'OclType'$;
	DEFINE TUPLETYPE $'TupleType'$;
	DEFINE TUPLE $'Tuple'$;
	DEFINE MAPTYPE $'Map'$;
	DEFINE LAMBDATYPE $'Lambda'$;
	DEFINE ENVTYPE $'Env'$;
	DEFINE TEXT CHAR + $($ + CHAR + $|$ + DIGIT + $|'_')*$;
	DEFINE LINEBREAK $('\r\n'|'\'r'|'\n')$;
	DEFINE WHITESPACE $(' '|'\t'|'\f')$;
}

TOKENSTYLES {
	"NOTOP" COLOR #800040, BOLD;
	"BOOLOP" COLOR #800040, BOLD;
	"INTOP" COLOR #800040, BOLD;
	"static" COLOR #800040, BOLD;
	"COMMENT" COLOR #53876D;
	"FLOAT" COLOR #2A00FF;
	"INTEGER" COLOR #2A00FF;
	"STRINGTYPE" COLOR #00C000;
	"BOOLEANTYPE" COLOR #00C000;
	"INTEGERTYPE" COLOR #00C000;
	"REALTYPE" COLOR #00C000;
	"COLLECTIONTYPE" COLOR #00C000;
	"BAGTYPE" COLOR #00C000;
	"ORDEREDSETTYPE" COLOR #00C000;
	"SEQUENCETYPE" COLOR #00C000;
	"SETTYPE" COLOR #00C000;
	"OCLANYTYPE" COLOR #00C000;
	"OCLTYPE" COLOR #00C000;
	"TUPLE" COLOR #00C000;
	"TUPLETYPE" COLOR #00C000;
	"MAPTYPE" COLOR #00C000;
	"LAMBDATYPE" COLOR #00C000;
	"ENVTYPE" COLOR #00C000;
	"true" COLOR #00D0FF;
	"false" COLOR #00D0FF;
	"iterate" COLOR #000000;
}

RULES {
	Module ::= "module" (name[]|name['"','"','\\']) ";" (!0 metamodels*)? (!0 imports*)? (!0 elements*)?;
	Import ::= "import" (name[]|name['"','"','\\']) ";";
	@Foldable
	OclFeatureDefinition ::= context_? static["static" : ""] "def" ":" feature;
	OclContextDefinition ::= "context" context_;
	OclMetamodel ::= "metamodel" (name[]|name['"','"','\\']) (":" uri['\'','\'','\\'])? ";";
	OclInstanceModel ::= (name[]|name['"','"','\\']) ":" metamodel[];
	LocalVariable ::= varName[] (":" type)? eq[EQ] initExpression;
	Attribute ::= (name[]|name['"','"','\\']) ":" type eq[EQ] !1 initExpression ";";
	Operation ::= (name[]|name['"','"','\\']) "(" (parameters ("," parameters)*)? ")" ":" returnType eq[EQ] !1 body ";";
	Parameter ::= varName[] ":" type;

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	OclModelElementExp ::= model[] "!" (name[]|name['"','"','\\']);

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	LambdaCallExp ::= referredVariable[] "(" (arguments ("," arguments)*)? ")";
	
	@Operator(type="primitive", weight="20", superclass="OclExpression")
	VariableExp ::= referredVariable[];

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	SuperExp ::= "super";

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	SelfExp ::= "self";

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	EnvExp ::= "env";

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	StringExp ::= stringSymbol['\'', '\'', '\\'];

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	BooleanExp ::= booleanSymbol["true" : "false"];

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	RealExp ::= realSymbol[FLOAT];

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	IntegerExp ::= integerSymbol[INTEGER];
	
	CollectionRange ::= first ".." last;
	CollectionItem ::= item;

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	BagExp ::= "Bag" "{" (parts ("," parts)*)? "}";

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	OrderedSetExp ::= "OrderedSet" "{" (parts ("," parts)*)? "}";

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	SequenceExp ::= "Sequence" "{" (parts ("," parts)*)? "}";

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	SetExp ::= "Set" "{" (parts ("," parts)*)? "}";

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	TupleExp ::= "Tuple" "{" (tuplePart ("," tuplePart)*)? "}";

	TuplePart ::= varName[] (":" type)? eq[EQ] initExpression;

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	MapExp ::= "Map" "{" (elements ("," elements)*)? "}";

	MapElement ::= "(" key "," value ")";

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	EnumLiteralExp ::= "#" (name[]|name['"','"','\\']);

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	OclUndefinedExp ::= "OclUndefined";

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	LetExp ::= "let" variable "in" in_;

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	IfExp ::= "if" condition "then" thenExpression "else" elseExpression "endif";

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	BraceExp ::= "(" exp ")";

	@Operator(type="binary_left_associative", weight="1", superclass="OclExpression")
	OperatorCallExp ::= source operationName[BOOLOP] argument;

	@Operator(type="binary_left_associative", weight="2", superclass="OclExpression")
	EqOpCallExp ::= source (operationName[EQ]|operationName[NEQ]) argument;

	@Operator(type="binary_left_associative", weight="3", superclass="OclExpression")
	RelOpCallExp ::= source operationName[RELOP] argument;

	@Operator(type="binary_left_associative", weight="4", superclass="OclExpression")
	AddOpCallExp ::= source operationName[ADDOP] argument;

	@Operator(type="binary_left_associative", weight="5", superclass="OclExpression")
	IntOpCallExp ::= source operationName[INTOP] argument;

	@Operator(type="binary_left_associative", weight="6", superclass="OclExpression")
	MulOpCallExp ::= source operationName[MULOP] argument;

	@Operator(type="unary_prefix", weight="7", superclass="OclExpression")
	NotOpCallExp ::= operationName[NOTOP] source;

	@Operator(type="primitive", weight="20", superclass="OclExpression")
	StaticPropertyCallExp ::= source staticCall;
	
	StaticOperationCall ::= "::" (operationName[]|operationName['"','"','\\']) "(" (arguments ("," arguments)*)? ")";
	StaticNavigationOrAttributeCall ::= "::" (name[]|name['"','"','\\']);

	@Operator(type="unary_postfix", weight="10", superclass="OclExpression")
	PropertyCallExp ::= source calls+;

	OperationCall ::= "." (operationName[]|operationName['"','"','\\']) "(" (arguments ("," arguments)*)? ")";
	NavigationOrAttributeCall ::= "." (name[]|name['"','"','\\']);
	IterateExp ::= "->" "iterate" "(" iterators ("," iterators)* ";" result "|" body ")";
	IteratorExp ::= "->" (name[]|name['"','"','\\']) "(" iterators ("," iterators)* "|" body ")";
	Iterator ::= varName[] (":" type)?;
	CollectionOperationCall ::= "->" (operationName[]|operationName['"','"','\\']) "(" (arguments ("," arguments)*)? ")";

	CollectionType ::= name[COLLECTIONTYPE] "(" elementType ")";
	StringType ::= name[STRINGTYPE];
	BooleanType ::= name[BOOLEANTYPE];
	IntegerType ::= name[INTEGERTYPE];
	RealType ::= name[REALTYPE];
	BagType ::= name[BAGTYPE] "(" elementType ")";
	OrderedSetType ::= name[ORDEREDSETTYPE] "(" elementType ")";
	SequenceType ::= name[SEQUENCETYPE] "(" elementType ")";
	SetType ::= name[SETTYPE] "(" elementType ")";
	OclAnyType ::= name[OCLANYTYPE];
	OclType ::= name[OCLTYPE];
	TupleType ::= (name[TUPLETYPE]|name[TUPLE]) "(" (attributes ("," attributes)*)? ")";
	TupleTypeAttribute ::= (name[]|name['"','"','\\']) ":" type;
	OclModelElement ::= (model[]|model['"','"','\\']) "!" (name[]|name['"','"','\\']);
	MapType ::= name[MAPTYPE] "(" keyType "," valueType ")";
	LambdaType ::= name[LAMBDATYPE] "(" (argumentTypes ("," argumentTypes)*)? ")" ":" returnType;
	EnvType ::= name[ENVTYPE];
}