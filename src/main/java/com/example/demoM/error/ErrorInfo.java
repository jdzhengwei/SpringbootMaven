package com.example.demoM.error;

/**
 * 	声明异常编码及错误描述
 * 	错误描述将直接响应给客户端
 *
 */
public enum ErrorInfo {
	
	SYS_API3_ERROR("0X01","API3响应")
	,SYS_SERVER_ERROR("0000", "服务端错误")
	,SYS_INTERNAL_SVC_ERROR("0001", "内部服务错误")

	/*******************************************************************************
	 * 约定 api_1,编码code从1000开始
	 *******************************************************************************/
	,ERROR_TIME("1000", "请检查客户端系统时间是否准确")
	,TOKEN_INVALID("1001", "token失效")
	,TOKEN_GEN_ERROR("1002", "token生成失败")
	,PARAM_INVALID("1003", "非法参数")
	,FAILED("1004", "操作失败")
	,CODE_TIMEOUT("1005", "验证码失效")
	,BAD_CODE("1006", "验证码不正确")
	,NO_POWER("1007", "没有权限")
	
	,ERROR_GEN_CAPTCHA_CODE("1008", "生成验证码时发生系统错误")
	,ERROR_WHEN_UPLOAD("1009", "上传文件时发生系统错误")
	,ERROR_FILETYPE_UPLOAD("1010", "上传文件格式错误")
	,ERROR_FILESIZE_UPLOAD("1011", "上传文件大小超出范围")		
	,NO_POWER_ADVICE("1012", "没有查看权限，请联系管理员")		
	/*******************************************************************************
	 * 约定 用户管理服务 ,编码code从2000开始
	 *******************************************************************************/
	,UNAME_NOT_EXIST("2001", "用户名不存在")
	,UNAME_NAME_EXIST("2002", "用户名已存在")
	,PHONE_EXIST("2003", "手机号码已注册")
	,USER_EXIST("2004", "用户已存在")
	,SUPPLIER_EXIST("2005", "企业已注册")
	,PWD_INVALID("2006", "密码错误")
	,MAIL_EXIST("2007", "邮箱已存在")
	,MYSQL_FAIL("2008", "数据库操作失败")
	,MAIL_UNEXIST("2009", "邮箱不存在")
	,PHONE_UNEXIST("2010", "手机号不存在")
	,SUPPLIER_UNEXIST("2011", "企业不存在")
	,USER_UPDATED("2012", "用户已被更新，请刷新重试。")
	,DELETE_ROLE_FAIL("2013", "角色删除失败。")
	,ROLE_NAME_EXIST("2014", "该角色已存在。")
	,UPDATE_ROLE_FAIL("2015", "修改角色信息失败！")
	,CREATE_ROLE_FAIL("2016", "创建角色失败！")
	,SET_ROLE_RES_FAIL("2016", "设置角色权限失败！")
	,LOCK_USER("2017", "用户已禁用")
	,DELETE_USER("2018", "用户已删除")
	,USER_NOT_EXIST("2019", "用户名或密码错误!")
	,SEND_MAIL_FAIL("2020", "邮件发送失败")
	,NAME_PATTERN_ERROR("2021", "用户名格式错误")
	,PWD_PATTERN_ERROR("2022", "密码格式错误")
	,REMARK_NULL_ERROR("2023", "审核未通过原因不能为空！")
	,ROLE_OPERATE_ERROR("2024", "当前角色正在被账号使用，无法进行操作！")
	,ROLE_CONFIGURATION_INUSE_ERROR("2025", "当前角色正在被申购配置使用，无法进行操作！")
	,DEPT_INUSE_ERROR("2026", "当前科室正在被账号使用，无法删除！")
	,DEPT_NAME_EXIST_ERROR("2027", "科室名称重复！")
	,DEPT_CODE_EXIST_ERROR("2028", "科室编码重复！")
	,USER_NAME_VALID_ERROR("2029", "用户名称要求4-20位！")
	,STAFF_NAME_VALID_ERROR("2030", "员工姓名要求2-10位！")
	,DEPT_VALID_ERROR("2031", "科室名称或科室编码重复！")
	,DEPT_MEMBER_BE_USED_ERROR("2032", "发生过业务的成员，不允许删除，可以设置状态为离职。")
	,DEPT_HAS_MEMBER_DISABLED_ERROR("2033", "科室下有在职的成员，无法停用。")
	,DEPT_HAS_USER_DISABLED_ERROR("2034", "该科室有正在使用的账号，无法停用。")
	,DEPT_IN_USED_DELETE_ERROR("2035", "发生过申购业务的科室，不允许删除，可以设置状态为停用。")
	,DEPT_HAS_MEMBER_DELETE_ERROR("2036", "该科室下存在科室成员，不允许删除。")
	,DEPT_HAS_USER_DELETE_ERROR("2037", "该科室下存在账号，不允许删除。")

	
    /*******************************************************************************
     * 约定微信端服务 ,编码code从7000开始
     *******************************************************************************/
    ,REG_AUDIT_FAIL("7001", "注册信息审核失败")
    ,REG_AUDIT_UNPASS_FAIL("7002", "抱歉,您的信息审核失败!请您完善信息")
    ,REG_IN_AUDIT_WARNING("7003", "您的注册信息已提交成功！我们将尽快完成审核,请耐心等待!")
    ,USER_UNBINDING("7004", "微信用户鉴权失败，请绑定供应宝用户")
    
	/*******************************************************************************
	 * 约定运营端服务，编码code从8000开始
	 *******************************************************************************/
	,OP_USER_NOT_EXIST("8001", "用户名或密码错误")
	,OP_LOCK_USER("8002", "用户已禁用")
	,OP_DELETE_USER("8003", "用户已删除")
	,OP_PWD_INVALID("8004", "密码错误")
	,OP_MAIL_FORMAT_ERROR("8005", "邮箱格式错误")
	,OP_BAD_CODE("8006", "验证码不正确")
	,OP_PARAM_INVALID("8007", "非法参数")
	,OP_MYSQL_FAIL("8008", "数据库操作失败")
	,OP_CODE_TIMEOUT("8009", "验证码失效")
	,OP_MAIL_NOT_EXIST("8005", "邮箱不存在")
	,OP_OLD_PWD_INVALID("8010", "原密码输入错误，请检查")
	,OP_PWD_DIFFER_INVALID("8011", "两次密码输入不一致，请检查")
	,OP_PWD_LENGTH_INVALID("8012", "密码长度错误，范围6至20个字符")
	,OP_ROLE_DELETE_INVALID("8013", "该角色已授予用户权限，不能被删除")
	,OP_NO_POWER_INVALID("8014", "该用户没有权限，请联系管理员")
	,OP_SERVER_NOT_CONFIG("8015", "服务未配置")
	,OP_FAIL_CREATE("8016", "开通主账号失败")
	,OP_FAIL_UPDATE("8017", "修改主账号密码失败")
	,OP_NOT_OPEN("8018", "该医院在运维平台中未开通，请联系运维平台相关人员")
	,OP_FAIL_UPDATEDEPLOY("8019", "该医院已经开通主账号，不能切换部署模式")
	
	
	/*******************************************************************************
	 * 约定运维端服务，编码code从9000开始
	 *******************************************************************************/
	,OM_USER_NOT_EXIST("9001", "用户名或密码错误")
	,OM_LOCK_USER("9002", "用户状态异常")
	,OM_MAIL_FORMAT_ERROR("9003", "用户邮箱格式错误")
	,OM_BAD_CODE("9004", "验证码不正确")
	,OM_INVALID_TOKEN("9005", "token失效")
	,OM_MAIL_NOT_EXIST("9006", "邮箱不存在")
	,OM_PARAM_INVALID("9007", "非法参数")
	,OM_MYSQL_FAIL("9008", "数据库操作失败")
	,OM_NOT_AUTHORIZED("9009", "权限不足")
	,OM_HOSP_NOT_EXIST("9010","医院不存在")
	,OM_FAIL_RESTART("9011","重启失败")
	,OM_TIMEOUT_CHECK("9012","状态查询超时")
	,OM_UPDATE_VERSION_FAILED("9013","更新前置服务版本失败")
	,OM_UPLOAD_PACKAGE_FAILED("9014","上传更新包失败")
	,OM_HOSP_OPEN_FAILED("9015", "开通医院失败")
	,OM_UPDATE_SHP_TEMPLATE_FAILED("9016", "更新shp模板失败")
	,OM_UPDATE_SHP_TASK_FAILED("9017", "更新shp任务失败")
	,OM_HOSP_IS_OPEN("9018", "医院已经是开通状态，不能重复开通")
	,OM_FAIL_UPDATE("9019","前置服务信息数据库更新失败")
	,OM_SERVER_NOT_CONFIG("9020", "服务地址未配置")
	,OM_SERVICE_ERROR("9021", "前置服务未启动或服务地址错误")
	,OM_HOSP_NAME_EXIST("9022", "已经存在该医院，请检查")
	,OM_ROLE_NAME_EXIST("9023", "角色名称已存在")
	,OM_ROLE_NOT_EXIST("9024", "角色已删除")
	,OM_HOSP_CONFIG_NOT_UPDATE("9025", "医院配置未更新")
	,OM_SERVICE_CONFIG_ERROR("9026", "前置服务未启动或医院签名配置错误")
	;
	
	private String code;
	private String msg;
	
	private ErrorInfo(String code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	/**
     * 检查给定的条件是否满足,如果不满足将抛出{@link CheckedException}
     * @param expression
     * @throws CheckedException
     */
    public void check(boolean expression) {
        if (!expression) {
            throw new CheckedException(this);
        }
    }
    
    /**
     * 直接抛出{@link CheckedException}
     * @throws StatusException
     */
    public void throwOut()  {
        throw new CheckedException(this);
    }
    
    /**
     * 直接抛出{@link CheckedException}
     * @param msg 错误描述
     * @throws StatusException
     */
    public void throwOut(String msg)  {
        throw new CheckedException(this.getCode(), msg);
    }
    
	/**
	 * ErrorInfo转换为CheckedException
	 * 
	 * @return
	 */
	public CheckedException exception() {
		return new CheckedException(this);
	}
	
	/**
	 * ErrorInfo转换为CheckedException
	 * @param msg 错误描述
	 * @return
	 */
	public CheckedException exception(String msg) {
		return new CheckedException(this.getCode(), msg);
	}
	
    public static void check(boolean expression,String code, String msg) {
        if (!expression) {
            throw new CheckedException(code,msg);
        }
    }
}

