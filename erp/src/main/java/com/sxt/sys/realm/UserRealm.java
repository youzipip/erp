package com.sxt.sys.realm;

import com.sxt.sys.domain.Role;
import com.sxt.sys.service.PermissionService;
import com.sxt.sys.service.RoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.ActiverUser;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * 实现认证的方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 取出登陆名
		String loginName = token.getPrincipal().toString();
		// 根据登陆名查询用户
		User user = userService.queryUserByLoginName(loginName);
		if (null != user) {
			ActiverUser activerUser = new ActiverUser();
			activerUser.setUser(user);
			if(user.getType()!=SysConstast.USER_TYPE_NORMAL) {
				//查询权限和角色
				List<String> role = this.roleService.queryRoleByUserIdForList(user.getId());
				List<String> permission = this.permissionService.queryPermissionByUserIdForList(user.getId());
				activerUser.setRoles(role);
				activerUser.setPermissions(permission);
			}
			// 验证密码
			ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser, user.getPwd(), credentialsSalt,
					getName());
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 实现授权的方法 只要用户判断一个角色或者一个权限，就会被回调
	 * 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ActiverUser activerUser = (ActiverUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if (activerUser.getUser().getType() == SysConstast.USER_TYPE_SUPER) {
			info.addStringPermission("*:*");
		} else {
			if (activerUser.getRoles() != null && activerUser.getRoles().size() > 0) {
				// 添加角色
				info.addRoles(activerUser.getRoles());
			}
			if (activerUser.getPermissions() != null && activerUser.getPermissions().size() > 0) {
				// 添加权限
				info.addStringPermissions(activerUser.getPermissions());
			}
		}
		return info;
	}

}
