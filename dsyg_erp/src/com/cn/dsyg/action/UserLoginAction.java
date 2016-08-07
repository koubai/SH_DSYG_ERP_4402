package com.cn.dsyg.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.MD5Util;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.ResourceDto;
import com.cn.dsyg.dto.RoleDto;
import com.cn.dsyg.dto.UserDto;
import com.cn.dsyg.service.ResourceService;
import com.cn.dsyg.service.RoleService;
import com.cn.dsyg.service.UserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用户登录Action
 * @author Frank
 * @time 2015-4-27下午10:28:22
 * @version 1.0
 */
public class UserLoginAction extends BaseAction {

	private static final Logger log = LogManager.getLogger(UserLoginAction.class);
	
	private static final long serialVersionUID = -3307892137806702480L;
	
	private RoleService roleService;
	
	private UserService userService;
	
	private ResourceService resourceService;
	
	private UserDto userDto;
	
	/**
	 * 显示用户登录页
	 * @return
	 */
	public String showLoginAction() {
		try {
			this.clearMessages();
			String ip = this.getIP();
			log.info("ip=" + ip);
			userDto = new UserDto();
		} catch(Exception e) {
			log.error("showLoginAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 用户登录
	 * @return
	 */
	public String loginAction() {
		try {
			this.clearMessages();
			if(userDto == null) {
				return ERROR;
			}
			if(StringUtil.isBlank(userDto.getUserid())) {
				this.addActionMessage("用户名不能为空！");
				return ERROR;
			}
			if(StringUtil.isBlank(userDto.getPassword())) {
				this.addActionMessage("登录密码不能为空！");
				return ERROR;
			}
			if(StringUtil.isBlank(userDto.getVerificationcode())) {
				this.addActionMessage("验证码不能为空！");
				return ERROR;
			}
			String rand = (String) ActionContext.getContext().getSession().get(Constants.SESSION_RANDOM);
			if(!userDto.getVerificationcode().equals(rand)) {
				this.addActionMessage("验证码不正确！");
				return ERROR;
			}

			String belongto= userDto.getBelongto();
			if(StringUtil.isBlank(belongto)) {
				this.addActionMessage("所属地不能为空！");
				return ERROR;
			}
			
			//验证登录密码
			UserDto user = userService.queryUserByID(userDto.getUserid());
			if(user == null) {
				this.addActionMessage("非有效用户！");
				return ERROR;
			}
			if(!MD5Util.md5(userDto.getPassword()).equals(user.getPassword())) {
				this.addActionMessage("登录密码不正确！");
				return ERROR;
			}
			//判断用户状态是否是有效
			if(user.getStatus() != Constants.STATUS_NORMAL) {
				this.addActionMessage("非有效用户！");
				return ERROR;
			}

			//判断用户所属是否是正确
			if(!user.getBelongto().equals(belongto) && !user.getBelongto().equals("99")) {
				this.addActionMessage("非有效用户所属！");
				return ERROR;
			}
			log.info("Belongto()=" + belongto);
			log.info("user.getBelongto()=" + user.getBelongto());

			//用户权限
			RoleDto role = roleService.queryRoleByCode(user.getRolecode());
			ActionContext.getContext().getSession().put(Constants.SESSION_ROLE_RANK, role.getRank());
			ActionContext.getContext().getSession().put(Constants.SESSION_USER_ID, userDto.getUserid());
			ActionContext.getContext().getSession().put(Constants.SESSION_USER_NAME, user.getUsername());
			ActionContext.getContext().getSession().put(Constants.SESSION_BELONGTO, belongto);
			ActionContext.getContext().getSession().put(Constants.SESSION_ROLE_CODE, user.getRolecode());
//			ActionContext.getContext().getSession().put(Constants.SESSION_USER_COLOR, userDto.getUsercolor());
			ActionContext.getContext().getSession().put(Constants.SESSION_LOGIN_TIME, DateUtil.dateToLogintime(new Date()));
			ActionContext.getContext().getSession().put(Constants.SESSION_ISLOGIN, Constants.SESSION_FLAG_IS_LOGIN);
			//角色对应的资源
			List<ResourceDto> resourceList = resourceService.queryResourceByRole("" + role.getId(), "");
			ActionContext.getContext().getSession().put(Constants.SESSION_RESOURCE_LIST, resourceList);
			//角色资源明细MAP（拦截器用）
			Map<String, Integer> map = resourceService.getRoleResourceMap("" + role.getId());
			ActionContext.getContext().getSession().put(Constants.SESSION_RESOURCE_MAP, map);
			log.info(userDto.getUserid() + " login success.");
		} catch(Exception e) {
			log.info("CCC");

			log.error("loginAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	public String logoutAction() {
		try {
			this.clearMessages();
			ActionContext.getContext().getSession().clear();
			userDto = new UserDto();
		} catch(Exception e) {
			log.error("logoutAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
}
