package com.example.demoM.util;

public class RedisKey {

	/**
	 * 用户
	 * key = USER_KEY_PREFIX + uid
	 * value as hash
	 */
	public static final String USER_KEY_PREFIX = "user:";
	
	/**
	 * 运营平台用户
	 * key = OP_USER_KEY_PREFIX + uid
	 * value as hash
	 */
	public static final String OP_USER_KEY_PREFIX = "op_user:";
	
	/**
	 * 运维平台用户
	 * key = OM_USER_KEY_PREFIX + uid
	 * value as hash
	 */
	public static final String OM_USER_KEY_PREFIX = "om_user:";
	
	/**
	 * 监管平台用户
	 * key = CM_USER_KEY_PREFIX + uid
	 * value as hash
	 */
	public static final String CM_USER_KEY_PREFIX = "cm_user:";
	
	/**
	 * api3接口的的full path
	 * key = hospital_api3_prefix + hid + "-" + apiName
	 * value as string
	 */
	public static final String hospital_api3_prefix = "hospital:api3:";
    /**
     * 医院签名私钥
     * key = hospital_key_prefix + hid
     * value as string
     */
	public final static String hospital_key_prefix = "hospital:key:"; 
	/**
	 * 必须证件类型
	 */
	public final static String dict_requrired_credential_prefix = "credential:required:";
	/**
	 * 证件类型字典
	 */
	public final static String dict_credential_prefix = "credential:dict:";
	
	/**
	 * 签名key
	 * 
	 * <pre>
	 * 格式：signingKey:{apiType}:{uid}
	 * 来源: (authcontroll-svc AuthcService)
	 * </pre>
	 * 
	 * @param apiType
	 * @param uid
	 * @return
	 */
	public static String signingKey(int apiType, long uid) {
		return "signingKey:" + apiType + ":" + uid;
	}
	
	/**
	 * nonce
	 * 格式：nonce:{apiType}:{nonce}
	 * 来源: (authcontroll-svc AuthcService)
	 * @param apiType
	 * @param nonce
	 * @return
	 */
	public static String nonce(int apiType, String nonce) {
		return "nonce:" + apiType + ":" + nonce;
	}
	
}
