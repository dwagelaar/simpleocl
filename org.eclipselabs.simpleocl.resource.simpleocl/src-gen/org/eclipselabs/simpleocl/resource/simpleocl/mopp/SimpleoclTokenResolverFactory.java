/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.eclipselabs.simpleocl.resource.simpleocl.mopp;

/**
 * The SimpleoclTokenResolverFactory class provides access to all generated token
 * resolvers. By giving the name of a defined token, the corresponding resolve can
 * be obtained. Despite the fact that this class is called TokenResolverFactory is
 * does NOT create new token resolvers whenever a client calls methods to obtain a
 * resolver. Rather, this class maintains a map of all resolvers and creates each
 * resolver at most once.
 */
public class SimpleoclTokenResolverFactory implements org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolverFactory {
	
	private java.util.Map<String, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver> tokenName2TokenResolver;
	private java.util.Map<String, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver> featureName2CollectInTokenResolver;
	private static org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver defaultResolver = new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclDefaultTokenResolver();
	
	public SimpleoclTokenResolverFactory() {
		tokenName2TokenResolver = new java.util.LinkedHashMap<String, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver>();
		featureName2CollectInTokenResolver = new java.util.LinkedHashMap<String, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver>();
		registerTokenResolver("NOTOP", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclNOTOPTokenResolver());
		registerTokenResolver("BOOLOP", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclBOOLOPTokenResolver());
		registerTokenResolver("INTOP", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclINTOPTokenResolver());
		registerTokenResolver("RELOP", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclRELOPTokenResolver());
		registerTokenResolver("EQ", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclEQTokenResolver());
		registerTokenResolver("NEQ", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclNEQTokenResolver());
		registerTokenResolver("ADDOP", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclADDOPTokenResolver());
		registerTokenResolver("MULOP", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclMULOPTokenResolver());
		registerTokenResolver("FLOAT", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclFLOATTokenResolver());
		registerTokenResolver("INTEGER", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclINTEGERTokenResolver());
		registerTokenResolver("STRINGTYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclSTRINGTYPETokenResolver());
		registerTokenResolver("BOOLEANTYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclBOOLEANTYPETokenResolver());
		registerTokenResolver("INTEGERTYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclINTEGERTYPETokenResolver());
		registerTokenResolver("REALTYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclREALTYPETokenResolver());
		registerTokenResolver("COLLECTIONTYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclCOLLECTIONTYPETokenResolver());
		registerTokenResolver("BAGTYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclBAGTYPETokenResolver());
		registerTokenResolver("ORDEREDSETTYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclORDEREDSETTYPETokenResolver());
		registerTokenResolver("SEQUENCETYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclSEQUENCETYPETokenResolver());
		registerTokenResolver("SETTYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclSETTYPETokenResolver());
		registerTokenResolver("OCLANYTYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclOCLANYTYPETokenResolver());
		registerTokenResolver("OCLTYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclOCLTYPETokenResolver());
		registerTokenResolver("TUPLETYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclTUPLETYPETokenResolver());
		registerTokenResolver("TUPLE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclTUPLETokenResolver());
		registerTokenResolver("MAPTYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclMAPTYPETokenResolver());
		registerTokenResolver("LAMBDATYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclLAMBDATYPETokenResolver());
		registerTokenResolver("ENVTYPE", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclENVTYPETokenResolver());
		registerTokenResolver("TEXT", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclTEXTTokenResolver());
		registerTokenResolver("QUOTED_34_34_92", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclQUOTED_34_34_92TokenResolver());
		registerTokenResolver("QUOTED_39_39_92", new org.eclipselabs.simpleocl.resource.simpleocl.analysis.SimpleoclQUOTED_39_39_92TokenResolver());
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver createTokenResolver(String tokenName) {
		return internalCreateResolver(tokenName2TokenResolver, tokenName);
	}
	
	public org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver createCollectInTokenResolver(String featureName) {
		return internalCreateResolver(featureName2CollectInTokenResolver, featureName);
	}
	
	protected boolean registerTokenResolver(String tokenName, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver){
		return internalRegisterTokenResolver(tokenName2TokenResolver, tokenName, resolver);
	}
	
	protected boolean registerCollectInTokenResolver(String featureName, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver){
		return internalRegisterTokenResolver(featureName2CollectInTokenResolver, featureName, resolver);
	}
	
	protected org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver deRegisterTokenResolver(String tokenName){
		return tokenName2TokenResolver.remove(tokenName);
	}
	
	private org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver internalCreateResolver(java.util.Map<String, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver> resolverMap, String key) {
		if (resolverMap.containsKey(key)){
			return resolverMap.get(key);
		} else {
			return defaultResolver;
		}
	}
	
	private boolean internalRegisterTokenResolver(java.util.Map<String, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver> resolverMap, String key, org.eclipselabs.simpleocl.resource.simpleocl.ISimpleoclTokenResolver resolver) {
		if (!resolverMap.containsKey(key)) {
			resolverMap.put(key,resolver);
			return true;
		}
		return false;
	}
	
}
