package com.cn.dsyg.action;

import java.io.ByteArrayInputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.RandomNumUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 验证码Action
 * @name ValidateCodeAction.java
 * @author Frank
 * @time 2014-12-8下午11:53:20
 * @version 1.0
 */
public class ValidateCodeAction extends BaseAction {

	private static final long serialVersionUID = -3878770196579795834L;
	private ByteArrayInputStream inputStream;
	private static final Logger log = LogManager.getLogger(ValidateCodeAction.class);

	public String execute() throws Exception {
		RandomNumUtil rdnu = RandomNumUtil.Instance();
		this.setInputStream(rdnu.getImage());// 取得带有随机字符串的图片
		log.info("rand=" + rdnu.getString());
		ActionContext.getContext().getSession().put(Constants.SESSION_RANDOM, rdnu.getString());// 取得随机字符串放入HttpSession
		return "success";
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

}
