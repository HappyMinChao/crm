package com.atguigu.crm.realms;

import java.util.Collection;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.crm.services.UserService;
import com.atuigu.crm.entity.Authority;
import com.atuigu.crm.entity.User;

//不知道继承哪个类， 只要是记住realm就可以了可以看他的继承关系
public class MyRealms extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	// 进行授权(看用户是否有某一个权限)的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		//在doGetAuthenticationInfo 把user转行成了Object类型的principal，在此在转换回来
		User user = (User) principal;

		//2. 创建 SimpleAuthorizationInfo 对象
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		//3. 调用 SimpleAuthorizationInfo#addRoles(Collection<String>); 添加用户所有的权限
		Collection<String> roles = new HashSet<>();
		for(Authority authority: user.getRole().getAuthorities()){
			roles.add(authority.getName());
		}
		
		info.addRoles(roles);
		return info;
	}

	// 进行认证(登陆)的方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		//在此方法中进行登录验证， 即判断是否能够登录成功
		//AuthenticationToken是UsernamePasswordToken的父类
		//需要强转后获取到用户名和密码
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		//
		String passowrd = token.getPassword().toString();
		
		User user = userService.getUserByUserName(username);
		
		if(user == null){
			return null;
		}
		
		Object principal = user;
		Object hashedCredentials = user.getPassword();
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		
		String realmName = getName();
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		
		//为什么在登录的时候一直都没有用到用户登录时输入的密码？
		
		return info;
	}
	
	//相当于 init-method. 构造器被调用后, 属性被初始化完成后, 被调用
	@PostConstruct
	public void initCredentialsMatcher(){
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(1024);
		
		//设置凭证的匹配方式. 
		setCredentialsMatcher(credentialsMatcher);
	}
	
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		String credentials = "123456";
		ByteSource salt = ByteSource.Util.bytes("8d8baf08cc25c27c");
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		
		System.out.println(result);
	}

}
