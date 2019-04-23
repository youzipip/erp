package com.sxt.sys.controller;

import com.sxt.sys.service.LoggerService;
import com.sxt.sys.vo.LoggerVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sxt.sys.utils.ActiverUser;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.UserVo;

import java.util.Date;

/**
 * 用户登陆控制器
 * 
 * @author LJH
 *
 */
@Controller
public class LoginController {

	private static final String MODEL = "login/";

	@Autowired
	private LoggerService loggerService;

	/**
	 * 用户登陆
	 */
	@RequestMapping(MODEL + "login")
	public String login(UserVo userVo,Model model) {
		// 1,组装token
		UsernamePasswordToken token = new UsernamePasswordToken(userVo.getLoginname(), userVo.getPwd());
		// 2,得到主体
		Subject subject = SecurityUtils.getSubject();
		try {
			// 7,把用户名和密码交给主体
			subject.login(token);
			System.out.println("登陆成功");
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			WebUtils.getCurrentSession().setAttribute("user", activerUser.getUser());
			//添加登录日志
			LoggerVo logger = new LoggerVo();
			logger.setLoginname(activerUser.getUser().getName()+"-"+activerUser.getUser().getLoginname());
			logger.setLogintime(new Date());
			logger.setLoginip(WebUtils.getCurrentRequest().getLocalAddr());
			loggerService.addLogger(logger);
			return "system/index";
		} catch (UnknownAccountException e) {
			System.out.println("用户名不存在");
			model.addAttribute("error", "用户名不存在");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码不正确");
			model.addAttribute("error", "密码不正确");
		} catch (LockedAccountException e) {
			System.out.println("帐户已锁定");
			model.addAttribute("error", "帐户已锁定");
		} catch (ExpiredCredentialsException e) {
			System.out.println("密码已过期");
			model.addAttribute("error", "密码已过期");
		}
		return "system/login";
	}

}
