package com.whitehorse.qingzhi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * UserBaseInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_base_info",uniqueConstraints = @UniqueConstraint(columnNames = "user_wx_openid"))

public class UserBaseInfo implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userNickname;
	private Integer userSex;
	private String userHeadImage;
	private String userWxOpenid;
	private String userProvince;
	private String userCity;
	private Boolean userIsLecturer;
	private String userEmail;
	private String userMobilephone;
	private Integer userStatus;
	private Integer userCreateTime;
	private Integer userUpdateTime;
	private Integer userCreateIp;
	private Boolean userIsDelete;
	private Integer userDeleteTime;

	// Constructors

	/** default constructor */
	public UserBaseInfo() {
	}

	/** minimal constructor */
	public UserBaseInfo(String userNickname, Integer userSex, String userHeadImage, String userWxOpenid,
			String userProvince, String userCity, Boolean userIsLecturer, Integer userStatus, Integer userCreateTime,
			Integer userUpdateTime, Integer userCreateIp, Boolean userIsDelete) {
		this.userNickname = userNickname;
		this.userSex = userSex;
		this.userHeadImage = userHeadImage;
		this.userWxOpenid = userWxOpenid;
		this.userProvince = userProvince;
		this.userCity = userCity;
		this.userIsLecturer = userIsLecturer;
		this.userStatus = userStatus;
		this.userCreateTime = userCreateTime;
		this.userUpdateTime = userUpdateTime;
		this.userCreateIp = userCreateIp;
		this.userIsDelete = userIsDelete;
	}

	/** full constructor */
	public UserBaseInfo(String userNickname, Integer userSex, String userHeadImage, String userWxOpenid,
			String userProvince, String userCity, Boolean userIsLecturer, String userEmail, String userMobilephone,
			Integer userStatus, Integer userCreateTime, Integer userUpdateTime, Integer userCreateIp,
			Boolean userIsDelete, Integer userDeleteTime) {
		this.userNickname = userNickname;
		this.userSex = userSex;
		this.userHeadImage = userHeadImage;
		this.userWxOpenid = userWxOpenid;
		this.userProvince = userProvince;
		this.userCity = userCity;
		this.userIsLecturer = userIsLecturer;
		this.userEmail = userEmail;
		this.userMobilephone = userMobilephone;
		this.userStatus = userStatus;
		this.userCreateTime = userCreateTime;
		this.userUpdateTime = userUpdateTime;
		this.userCreateIp = userCreateIp;
		this.userIsDelete = userIsDelete;
		this.userDeleteTime = userDeleteTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "user_id", unique = true, nullable = false)

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "user_nickname", nullable = false, length = 32)

	public String getUserNickname() {
		return this.userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	@Column(name = "user_sex", nullable = false)

	public Integer getUserSex() {
		return this.userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	@Column(name = "user_head_image", nullable = false)

	public String getUserHeadImage() {
		return this.userHeadImage;
	}

	public void setUserHeadImage(String userHeadImage) {
		this.userHeadImage = userHeadImage;
	}

	@Column(name = "user_wx_openid", unique = true, nullable = false)

	public String getUserWxOpenid() {
		return this.userWxOpenid;
	}

	public void setUserWxOpenid(String userWxOpenid) {
		this.userWxOpenid = userWxOpenid;
	}

	@Column(name = "user_province", nullable = false, length = 64)

	public String getUserProvince() {
		return this.userProvince;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	@Column(name = "user_city", nullable = false, length = 64)

	public String getUserCity() {
		return this.userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	@Column(name = "user_is_lecturer", nullable = false)

	public Boolean getUserIsLecturer() {
		return this.userIsLecturer;
	}

	public void setUserIsLecturer(Boolean userIsLecturer) {
		this.userIsLecturer = userIsLecturer;
	}

	@Column(name = "user_email", length = 64)

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "user_mobilephone", length = 64)

	public String getUserMobilephone() {
		return this.userMobilephone;
	}

	public void setUserMobilephone(String userMobilephone) {
		this.userMobilephone = userMobilephone;
	}

	@Column(name = "user_status", nullable = false)

	public Integer getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	@Column(name = "user_create_time", nullable = false)

	public Integer getUserCreateTime() {
		return this.userCreateTime;
	}

	public void setUserCreateTime(Integer userCreateTime) {
		this.userCreateTime = userCreateTime;
	}

	@Column(name = "user_update_time", nullable = false)

	public Integer getUserUpdateTime() {
		return this.userUpdateTime;
	}

	public void setUserUpdateTime(Integer userUpdateTime) {
		this.userUpdateTime = userUpdateTime;
	}

	@Column(name = "user_create_ip", nullable = false)

	public Integer getUserCreateIp() {
		return this.userCreateIp;
	}

	public void setUserCreateIp(Integer userCreateIp) {
		this.userCreateIp = userCreateIp;
	}

	@Column(name = "user_is_delete", nullable = false)

	public Boolean getUserIsDelete() {
		return this.userIsDelete;
	}

	public void setUserIsDelete(Boolean userIsDelete) {
		this.userIsDelete = userIsDelete;
	}

	@Column(name = "user_delete_time")

	public Integer getUserDeleteTime() {
		return this.userDeleteTime;
	}

	public void setUserDeleteTime(Integer userDeleteTime) {
		this.userDeleteTime = userDeleteTime;
	}

	
}