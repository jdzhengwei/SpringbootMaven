package com.example.demoM.vo;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author liupeijiang
 * @since 2018-05-28
 */

@ApiModel(description = "用户信息表")
public class UserInfoVO {

	public static interface PassWordValid{}
	
    @ApiModelProperty("用户信息Id")
    private Long id;

    @ApiModelProperty("用户名称")
    @NotBlank(message="用户名称不能为空！")
    @Length(max=20, message="用户名称要求最长20位！")
    private String userName;

    @ApiModelProperty("密码")
    @Pattern(regexp = "^[0-9a-zA-Z]{8,20}$", message = "密码要求8-20位字母数字组合！", groups= {PassWordValid.class})
    private String password;

    @ApiModelProperty("手机号")
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9])|(17[0-9]))\\d{8}$", message = "手机号码格式错误")
    private String mobile;

    @ApiModelProperty("密码盐值")
    private String salt;

    @ApiModelProperty("用户类型 【1: 医院；2: 供应商】")
    private Integer userType;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("更新时间")
    private String updateTime;

    @ApiModelProperty("乐观锁")
    private Integer ver;

    @ApiModelProperty("状态 1：信息不完整(非会员)  2：待审核，3：信息完整,审核通过(会员), 4:审核不通过，5：禁用，6：删除")
    private Integer status;

    @ApiModelProperty("当前医院ID")
    private long hospitalId;
    @ApiModelProperty("当前供应商ID")
    private Long supplierInfoId;
    
    @ApiModelProperty("父用户id")
    private long pid;
    
    @ApiModelProperty("员工姓名")
    @NotBlank(message="员工姓名不能为空！")
    @Length(max=10, message="员工姓名要求最长10位！")
    private String staffName = "";
    
    @ApiModelProperty("科室id")
    private long deptId;
    
    @ApiModelProperty("科室编码")
    private String deptCode = "";
    
    @ApiModelProperty("科室名称")
    private String deptName = "";
    
    @ApiModelProperty("角色id列表")
    private List<Long> roleIds;
    
    @ApiModelProperty("上次登录时间")
    private String lastLoginTime;
    
    @ApiModelProperty("角色名称列表")
    private List<String> roleNames;

    @ApiModelProperty("所属医院模式[1:平台模式, 2:院内模式]")
    private int hospitalModel;
    
	public List<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Long getSupplierInfoId() {
        return supplierInfoId;
    }

    public void setSupplierInfoId(Long supplierInfoId) {
        this.supplierInfoId = supplierInfoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getHospitalModel() {
        return hospitalModel;
    }

    public void setHospitalModel(int hospitalModel) {
        this.hospitalModel = hospitalModel;
    }

}