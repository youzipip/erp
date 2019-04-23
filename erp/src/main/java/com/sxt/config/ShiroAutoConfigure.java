package com.sxt.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.sxt.sys.realm.UserRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
@ConfigurationProperties(prefix = "shiro")
public class ShiroAutoConfigure {

	private String hashAlgorithmName="md5";
	private int hashIterations=2;

	/**
	 * 创建凭证匹配器
	 */
	@Bean
	public HashedCredentialsMatcher getHashedCredentialsMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		// 设置加密方式
		credentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
		// 设置散列次数
		credentialsMatcher.setHashIterations(hashIterations);
		return credentialsMatcher;
	}

	/**
	 * 创建Realm
	 *
	 */
	@Bean
	public UserRealm getUserRealm(HashedCredentialsMatcher credentialsMatcher) {
		UserRealm realm = new UserRealm();
		// 注入凭着匹配器
		realm.setCredentialsMatcher(credentialsMatcher);
		return realm;
	}

	/**
	 * 创建安全管理器
	 */
	@Bean
	public SecurityManager getSecurityManager(UserRealm userRealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		// 注入realm
		manager.setRealm(userRealm);
		return manager;
	}

	/**
	 * Shiro 的Web过滤器
	 */
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// Shiro的核心安全接口，这个属性是必须的
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 过滤器配置
		Map<String, String> map = new HashMap<String, String>();
		// 登陆的接口放行
		map.put("/login/login*", "anon");
		map.put("/index.html*", "anon");
		map.put("/resources/**", "anon");//放行静态资源
		// 登出操作
		map.put("/logout", "logout");
		// 对所有用户认证
		map.put("/**", "authc");
		// 要求登录时的链接
		shiroFilterFactoryBean.setLoginUrl("/index.html");
		// 错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	/**
	 * //加入注解的使用，不加入这个注解不生效
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * 注册shiro的委托过滤器，相当于之前在web.xml里面配置的
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy() {
		FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<DelegatingFilterProxy>();
		DelegatingFilterProxy proxy = new DelegatingFilterProxy();
		proxy.setTargetFilterLifecycle(true);
		proxy.setTargetBeanName("shiroFilter");
		filterRegistrationBean.setFilter(proxy);
		return filterRegistrationBean;
	}

	// 这里是为了能在html页面引用shiro标签，上面两个函数必须添加，不然会报错
	@Bean(name = "shiroDialect")
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
	
	

	public void setHashAlgorithmName(String hashAlgorithmName) {
		this.hashAlgorithmName = hashAlgorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	public String getHashAlgorithmName() {
		return hashAlgorithmName;
	}

	public int getHashIterations() {
		return hashIterations;
	}

}
