package com.cn.dsyg.dto;

import java.util.Date;

import com.cn.common.dto.BaseDto;

/**
 * 员工档案
 * @author 
 * @time 
 * @version 1.0
 */
public class PersonalDto extends BaseDto {

	private static final long serialVersionUID = 9186189460103502437L;

	/**
	 * ID
	 */
	private int id;

	/**
	 * 员工ID
	 */
	private String userid;

	/**
	 * 员工档案所属地（以后可能分上海和深圳）
	 */
	private String belongto;

	/**
	 * 员工编号
	 */
	private String userno;

	/**
	 * 登记时间
	 */
	private String registdate;

	/**
	 * 登记时间（显示用）
	 */
	private String showregistdate;

	/**
	 * 员工姓名
	 */
	private String username;

	/**
	 * 性别
	 */
	private int sex;

	/**
	 * 出生时间
	 */
	private String birthday;

	/**
	 * 出生时间（显示用）
	 */
	private String showbirthday;

	/**
	 * 岗位
	 */
	private String post;

	/**
	 * 直接领导
	 */
	private String superior;

	/**
	 * 手机
	 */
	private String phone;

	/**
	 * 电话
	 */
	private String tell;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 入职时间
	 */
	private String employeddate;

	/**
	 * 入职时间（显示用）
	 */
	private String showemployeddate;
	
	/**
	 * 离职时间
	 */
	private String retiredate;
	/**
	 * 离职时间（显示用）
	 */
	private String showretiredate;
	
	
	/**
	 * 档案明细
	 */
	private String personaldesc;

	/**
	 * 内容介绍
	 */
	private String note;
	
	/**
	 * 级别(0-99)
	 */
	private int rank;
	
	/**
	 * 状态
	 */
	private int status;

	/**
	 * 预备项目1
	 */
	private String res01;

	/**
	 * 预备项目2
	 */
	private String res02;

	/**
	 * 预备项目3
	 */
	private String res03;

	/**
	 * 预备项目4
	 */
	private String res04;

	/**
	 * 预备项目5
	 */
	private String res05;

	/**
	 * 预备项目6
	 */
	private String res06;

	/**
	 * 预备项目7
	 */
	private String res07;

	/**
	 * 预备项目8
	 */
	private String res08;

	/**
	 * 预备项目9
	 */
	private String res09;

	/**
	 * 预备项目10
	 */
	private String res10;

	/**
	 * 作成者
	 */
	private String createuid;

	/**
	 * 作成时间
	 */
	private Date createdate;

	/**
	 * 更新者
	 */
	private String updateuid;

	/**
	 * 更新时间
	 */
	private Date updatedate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRes01() {
		return res01;
	}

	public void setRes01(String res01) {
		this.res01 = res01;
	}

	public String getRes02() {
		return res02;
	}

	public void setRes02(String res02) {
		this.res02 = res02;
	}

	public String getRes03() {
		return res03;
	}

	public void setRes03(String res03) {
		this.res03 = res03;
	}

	public String getRes04() {
		return res04;
	}

	public void setRes04(String res04) {
		this.res04 = res04;
	}

	public String getRes05() {
		return res05;
	}

	public void setRes05(String res05) {
		this.res05 = res05;
	}

	public String getRes06() {
		return res06;
	}

	public void setRes06(String res06) {
		this.res06 = res06;
	}

	public String getRes07() {
		return res07;
	}

	public void setRes07(String res07) {
		this.res07 = res07;
	}

	public String getRes08() {
		return res08;
	}

	public void setRes08(String res08) {
		this.res08 = res08;
	}

	public String getRes09() {
		return res09;
	}

	public void setRes09(String res09) {
		this.res09 = res09;
	}

	public String getRes10() {
		return res10;
	}

	public void setRes10(String res10) {
		this.res10 = res10;
	}

	public String getCreateuid() {
		return createuid;
	}

	public void setCreateuid(String createuid) {
		this.createuid = createuid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getUpdateuid() {
		return updateuid;
	}

	public void setUpdateuid(String updateuid) {
		this.updateuid = updateuid;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getRegistdate() {
		return registdate;
	}

	public void setRegistdate(String registdate) {
		this.registdate = registdate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getSuperior() {
		return superior;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmployeddate() {
		return employeddate;
	}

	public void setEmployeddate(String employeddate) {
		this.employeddate = employeddate;
	}

	public String getRetiredate() {
		return retiredate;
	}

	public void setRetiredate(String retiredate) {
		this.retiredate = retiredate;
	}

	public String getPersonaldesc() {
		return personaldesc;
	}

	public void setPersonaldesc(String personaldesc) {
		this.personaldesc = personaldesc;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getShowbirthday() {
		if(getBirthday() != null && !getBirthday().equals("")){
			showbirthday =  getBirthday().substring(0, 10);
		} else {
			showbirthday="";
		}
		return showbirthday;
	}

	public void setShowbirthday(String showbirthday) {
		this.showbirthday = showbirthday;
	}

	public String getShowregistdate() {
		if(getRegistdate() != null && !getRegistdate().equals("")){
			showregistdate =  getRegistdate().substring(0, 10);
		} else {
			showregistdate="";
		}
		return showregistdate;
	}

	public void setShowregistdate(String showregistdate) {
		this.showregistdate = showregistdate;
	}

	public String getShowemployeddate() {
		if(getEmployeddate() != null && !getEmployeddate().equals("")){
			showemployeddate =  getEmployeddate().substring(0, 10);
		} else {
			showemployeddate="";
		}
		return showemployeddate;
	}

	public void setShowemployeddate(String showemployeddate) {
		this.showemployeddate = showemployeddate;
	}

	public String getShowretiredate() {
		if(getRetiredate() != null && !getRetiredate().equals("")){
			showretiredate =  getRetiredate().substring(0, 10);
		} else {
			showretiredate="";
		}
		return showretiredate;
	}

	public void setShowretiredate(String showretiredate) {
		this.showretiredate = showretiredate;
	}

}
