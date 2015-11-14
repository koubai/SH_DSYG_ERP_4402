package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.MD5Util;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.RoleDto;
import com.cn.dsyg.dto.UserDto;
import com.cn.dsyg.service.RoleService;
import com.cn.dsyg.service.UserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @name UserAction.java
 * @author Frank
 * @time 2015-7-18下午10:34:37
 * @version 1.0
 */
public class UserAction extends BaseAction {

	private static final Logger log = LogManager.getLogger(UserAction.class);
	private static final long serialVersionUID = -36067602288448902L;
	
	private UserService userService;
	private RoleService roleService;
	
	private List<RoleDto> roleList;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	//页面显示的产品列表
	private List<UserDto> userList;
	//用户名
	private String strUserName;
	
	//添加用户
	private UserDto addUserDto;
	
	//修改用户
	private UserDto updUserDto;
	private String updUserid;
	
	//删除用户
	private String delUserid;

	//修改密码
	private UserDto updUserPsdDto;

	//用户选择页面=================
	//用户名
	private String strSelectUserName;
	//用户列表
	private List<UserDto> listSelectUser;
	//页码
	private int selectStartIndex;
	//翻页page
	private Page selectPage;
	//一页显示记录数
	private Integer intSelectPageSize;
	//用户选择页面=================
	
	/**
	 * 显示修改密码页面
	 * @return
	 */
	public String showUpdUserPsdAction() {
		try {
			this.clearMessages();
			updUserPsdDto = new UserDto();
		} catch(Exception e) {
			log.error("showUpdUserPsdAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String updUserPsdAction() {
		try {
			this.clearMessages();
			if(updUserPsdDto == null) {
				this.addActionMessage("原始密码不能为空！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updUserPsdDto.getOldpassword())) {
				this.addActionMessage("原始密码不能为空！");
				return "checkerror";
			}
			
			if(StringUtil.isBlank(updUserPsdDto.getPassword())) {
				this.addActionMessage("请输入新密码！");
				return "checkerror";
			}
			if(!updUserPsdDto.getPassword().equals(updUserPsdDto.getRepassword())) {
				this.addActionMessage("两次密码不一致！");
				return "checkerror";
			}
			//判断旧密码是否正确
			String currUser = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			UserDto user = userService.queryUserByID(currUser);
			if(!user.getPassword().equals(MD5Util.md5(updUserPsdDto.getOldpassword()))) {
				this.addActionMessage("旧密码不正确！");
				return "checkerror";
			}
			
			updUserPsdDto.setUserid(currUser);
			userService.updPassword(updUserPsdDto);
			this.addActionMessage("密码修改成功！");
			updUserPsdDto = new UserDto();
		} catch(Exception e) {
			log.error("updUserPsdAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示用户修改页面
	 * @return
	 */
	public String showUpdUserAction() {
		try {
			this.clearMessages();
			initList();
			updUserDto = userService.queryUserByID(updUserid);
		} catch(Exception e) {
			log.error("showUpdUserAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改用户
	 * @return
	 */
	public String updUserAction() {
		try {
			this.clearMessages();
			initList();
			//数据验证
			if(!checkData(updUserDto, "2")) {
				return "checkerror";
			}
			//当前操作用户ID
			String currUser = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			updUserDto.setUpdateuid(currUser);
			userService.updateUser(updUserDto);
			//修改用户成功
			this.addActionMessage("更新成功！");
		} catch(Exception e) {
			log.error("updUserAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示新增用户页面
	 * @return
	 */
	public String showAddUserAction() {
		try {
			this.clearMessages();
			addUserDto = new UserDto();
			addUserDto.setStatus(1);
			initList();
		} catch(Exception e) {
			log.error("showAddUserAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增用户
	 * @return
	 */
	public String addUserAction() {
		try {
			this.clearMessages();
			initList();
			//数据验证
			if(!checkData(addUserDto, "1")) {
				return "checkerror";
			}
			//逻辑主键check
			UserDto uu = userService.queryUserByID(addUserDto.getUserid());
			if(uu != null) {
				this.addActionMessage("用户ID" + addUserDto.getUserid() + "已存在！");
				return "checkerror";
			}
			//当前操作用户ID
			String currUser = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			addUserDto.setCreateuid(currUser);
			addUserDto.setUpdateuid(currUser);
			addUserDto.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
			userService.insertUser(addUserDto);
			//新增用户成功
			this.addActionMessage("添加成功！");
			addUserDto = new UserDto();
			addUserDto.setStatus(1);
		} catch(Exception e) {
			log.error("addUserAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 用户管理页面
	 * @return
	 */
	public String showUserManageAction() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			strUserName = "";
			page = new Page(intPageSize);
			userList = new ArrayList<UserDto>();
			initList();
			
			queryData();
		} catch(Exception e) {
			log.error("showUserManageAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询用户
	 * @return
	 */
	public String queryUserManageAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("queryUserManageAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnUserManageAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData();
		} catch(Exception e) {
			log.error("turnUserManageAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//用户选择页面start
	/**
	 * 显示用户选择页面
	 * @return
	 */
	public String showSelectUserAction() {
		try {
			this.clearMessages();
			selectStartIndex = 0;
			strSelectUserName = "";
			//默认10条
			intSelectPageSize = 10;
			selectPage = new Page(intSelectPageSize);
			//查询数据
			querySelectUser();
		} catch(Exception e) {
			log.error("showSelectUserAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 用户查询（用户选择页面）
	 * @return
	 */
	public String querySelectUserAction() {
		try {
			this.clearMessages();
			selectStartIndex = 0;
			//默认10条
			if(intSelectPageSize == null) {
				intSelectPageSize = 10;
			}
			selectPage = new Page(intSelectPageSize);
			//查询数据
			querySelectUser();
		} catch(Exception e) {
			log.error("querySelectUserAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页（用户选择页面）
	 * @return
	 */
	public String turnSelectUserAction() {
		try {
			this.clearMessages();
			//查询数据
			querySelectUser();
		} catch(Exception e) {
			log.error("turnSelectUserAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	//用户选择页面end
	
	/**
	 * 翻页查询用户列表（用户选择页面用）
	 */
	@SuppressWarnings("unchecked")
	private void querySelectUser() {
		listSelectUser = new ArrayList<UserDto>();
		if(selectPage == null) {
			selectPage = new Page(intSelectPageSize);
		}
		//翻页查询所有用户
		this.selectPage.setStartIndex(selectStartIndex);
		selectPage = userService.queryUserByPage(strSelectUserName, selectPage);
		listSelectUser = (List<UserDto>) selectPage.getItems();
		
		this.setSelectStartIndex(selectPage.getStartIndex());
	}
	
	/**
	 * 翻页查询用户列表
	 */
	@SuppressWarnings("unchecked")
	private void queryData() {
		initList();
		userList = new ArrayList<UserDto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有用户
		this.page.setStartIndex(startIndex);
		page = userService.queryUserByPage(strUserName, page);
		userList = (List<UserDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	public String delUserAction() {
		try {
			this.clearMessages();
			//只有管理员才有权限
			Integer rank = (Integer) ActionContext.getContext().getSession().get(Constants.SESSION_ROLE_RANK);
			if(rank == null || rank < Constants.ROLE_RANK_ADMIN) {
				return "noauthority";
			}
			if(StringUtil.isBlank(delUserid)) {
				this.addActionMessage("用户登录ID不能为空！");
				return "checkerror";
			}
			if(Constants.ROLE_CODE_ADMIN.equals(delUserid)) {
				this.addActionMessage("该用户为系统管理员，不能删除！");
				return "checkerror";
			}
			//删除用户
			userService.deleteUser(delUserid);
			this.addActionMessage("删除成功！");
			//刷新页面
			startIndex = 0;
			queryData();
			delUserid = "";
		} catch(Exception e) {
			log.error("updUserAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 验证数据
	 * @param user
	 * @param flag
	 * @return
	 */
	private boolean checkData(UserDto user, String flag) {
		if("1".equals(flag)) {
			//新增
			if(user == null) {
				this.addActionMessage("用户ID不能为空！");
				return false;
			}
			if(StringUtil.isBlank(user.getUserid())) {
				this.addActionMessage("用户ID不能为空！");
				return false;
			}
			if(StringUtil.isBlank(user.getUsername())) {
				this.addActionMessage("用户名不能为空！");
				return false;
			}
			if(StringUtil.isBlank(user.getPassword())) {
				this.addActionMessage("用户密码不能为空！");
				return false;
			}
			if(!user.getPassword().equals(user.getRepassword())) {
				this.addActionMessage("两次输入的密码不一致！");
				return false;
			}
		} else {
			//修改
			if(user == null) {
				this.addActionMessage("用户名不能为空！");
				return false;
			}
			if(StringUtil.isBlank(user.getUsername())) {
				this.addActionMessage("用户名不能为空！");
				return false;
			}
		}
		if(StringUtil.isBlank(user.getRolecode())) {
			this.addActionMessage("用户角色不能为空！");
			return false;
		}
		if(user.getStatus() == null) {
			this.addActionMessage("请选择用户状态！");
			return false;
		}
		return true;
	}
	
	/**
	 * 初期化数据
	 */
	private void initList() {
		//角色信息
		roleList = roleService.queryAllRole();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getStrSelectUserName() {
		return strSelectUserName;
	}

	public void setStrSelectUserName(String strSelectUserName) {
		this.strSelectUserName = strSelectUserName;
	}

	public List<UserDto> getListSelectUser() {
		return listSelectUser;
	}

	public void setListSelectUser(List<UserDto> listSelectUser) {
		this.listSelectUser = listSelectUser;
	}

	public int getSelectStartIndex() {
		return selectStartIndex;
	}

	public void setSelectStartIndex(int selectStartIndex) {
		this.selectStartIndex = selectStartIndex;
	}

	public Page getSelectPage() {
		return selectPage;
	}

	public void setSelectPage(Page selectPage) {
		this.selectPage = selectPage;
	}

	public Integer getIntSelectPageSize() {
		return intSelectPageSize;
	}

	public void setIntSelectPageSize(Integer intSelectPageSize) {
		this.intSelectPageSize = intSelectPageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}

	public List<UserDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDto> userList) {
		this.userList = userList;
	}

	public String getStrUserName() {
		return strUserName;
	}

	public void setStrUserName(String strUserName) {
		this.strUserName = strUserName;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<RoleDto> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleDto> roleList) {
		this.roleList = roleList;
	}

	public UserDto getAddUserDto() {
		return addUserDto;
	}

	public void setAddUserDto(UserDto addUserDto) {
		this.addUserDto = addUserDto;
	}

	public UserDto getUpdUserDto() {
		return updUserDto;
	}

	public void setUpdUserDto(UserDto updUserDto) {
		this.updUserDto = updUserDto;
	}

	public String getUpdUserid() {
		return updUserid;
	}

	public void setUpdUserid(String updUserid) {
		this.updUserid = updUserid;
	}

	public UserDto getUpdUserPsdDto() {
		return updUserPsdDto;
	}

	public void setUpdUserPsdDto(UserDto updUserPsdDto) {
		this.updUserPsdDto = updUserPsdDto;
	}

	public String getDelUserid() {
		return delUserid;
	}

	public void setDelUserid(String delUserid) {
		this.delUserid = delUserid;
	}
}
