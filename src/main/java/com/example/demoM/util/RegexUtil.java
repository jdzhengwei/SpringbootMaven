package com.example.demoM.util;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * 通过正则表达判断是否正确的手机号，固定电话，身份证，邮箱等.
 * 
 * 从AndroidUtilCode的RegexUtils移植, 性能优化将正则表达式为预编译, 并修改了TEL的正则表达式.
 * 
 * @author calvin
 */
public class RegexUtil {

	private RegexUtil(){
		
	}
	/**
	 * 正则：手机号, 已知3位前缀＋8位数字
	 * <p>
	 * 移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188
	 * </p>
	 * <p>
	 * 联通：130、131、132、145、155、156、175、176、185、186
	 * </p>
	 * <p>
	 * 电信：133、153、173、177、180、181、189
	 * </p>
	 * <p>
	 * 全球星：1349
	 * </p>
	 * <p>
	 * 虚拟运营商：170
	 * </p>
	 */
	private static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9])|(17[0-9]))\\d{8}$";
	private static final Pattern PATTERN_REGEX_MOBILE_EXACT = Pattern.compile(REGEX_MOBILE_EXACT);


	/**
	 * 正则：邮箱, 有效字符(不支持中文), 且中间必须有@，后半部分必须有.
	 */
	private static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	private static final Pattern PATTERN_REGEX_EMAIL = Pattern.compile(REGEX_EMAIL);

	/**
	 * 正则：URL, 必须有"://",前面必须是英文，后面不能有空格
	 */
	private static final String REGEX_URL = "[a-zA-z]+://[^\\s]*";
	private static final Pattern PATTERN_REGEX_URL = Pattern.compile(REGEX_URL);


	/**
	 * 正则：IP地址
	 */
	private static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
	private static final Pattern PATTERN_REGEX_IP = Pattern.compile(REGEX_IP);
	
	/**
	 * 正则: 用户名
	 */
	private static final String REGEX_USER_NAME = "^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9_\u4e00-\u9fa5]{2,19}$";
	private static final Pattern PATTERN_REGEX_USER_NAME = Pattern.compile(REGEX_USER_NAME);
	
	/**
	 * 正则: 密码
	 */
//	private static final String REGEX_PASSWORD = "^[a-zA-Z0-9`~!@#$%^&*()_\\-=+\\\\|\\{\\}\\[\\];:'\"/?.>,<]{6,20}$";
	private static final String REGEX_PASSWORD = "^[A-Za-z0-9]{8,20}$";
	private static final Pattern PATTERN_REGEX_PASSWORD = Pattern.compile(REGEX_PASSWORD);
	
	/**
	 * 验证手机号
	 */
	public static boolean isPhone(String input) {
		return isMatch(PATTERN_REGEX_MOBILE_EXACT, input);
	}

	/**
	 * 验证邮箱
	 */
	public static boolean isEmail(String input) {
		return isMatch(PATTERN_REGEX_EMAIL, input);
	}

	/**
	 * 验证URL
	 */
	public static boolean isUrl(String input) {
		return isMatch(PATTERN_REGEX_URL, input);
	}

	/**
	 * 验证IP地址
	 */
	public static boolean isIp(String input) {
		return isMatch(PATTERN_REGEX_IP, input);
	}
	
	/**
	 * 验证用户名
	 */
	public static boolean isUserName(String input) {
		return isMatch(PATTERN_REGEX_USER_NAME, input);
	}
	
	/**
	 * 验密码
	 */
	public static boolean isPassword(String input) {
		return isMatch(PATTERN_REGEX_PASSWORD, input);
	}

	private static boolean isMatch(Pattern pattern, String input) {
		return (!StringUtils.isEmpty(input)) && pattern.matcher(input).matches();
	}
}
