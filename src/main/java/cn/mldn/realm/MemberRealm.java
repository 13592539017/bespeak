package cn.mldn.realm;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.mldn.singup.service.back.IMemberServiceBack;
import cn.mldn.singup.vo.Member;
import cn.mldn.util.encrypt.MyPasswordEncrypt;

public class MemberRealm extends AuthorizingRealm {
	private Logger log = Logger.getLogger(MemberRealm.class);
	@Resource
	private IMemberServiceBack memberServiceBack;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("********** 2、用户角色与权限：doGetAuthorizationInfo **********");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		log.info("********** 1、用户登录认证：doGetAuthenticationInfo() **********");
		// 1、登录认证的方法需要先执行，需要用他来判断登录的用户信息是否合法
		String username = (String) token.getPrincipal(); // 取得用户名
		// 需要通过用户名取得用户的完整信息，利用业务层操作
		Member vo =  this.memberServiceBack.get(username);	// 根据后台业务查询用户的完整数据
		if (vo == null) {
			throw new UnknownAccountException("该用户名称不存在！");
		} else { // 进行密码的验证处理
			String password = MyPasswordEncrypt.encryptPassword(new String((char[]) token.getCredentials()));
			// 将数据库中的密码与输入的密码进行比较，这样就可以确定当前用户是否可以正常登录
			if (vo.getPassword().equals(password)) { // 密码正确
				AuthenticationInfo auth = new SimpleAuthenticationInfo(username, password, "memberRealm");
				return auth; 
			} else {
				throw new IncorrectCredentialsException("密码错误！");
			}
		}
	}
}
