package com.slife.shiro;

import com.slife.constant.Global;
import com.slife.constant.Setting;
import com.slife.entity.SysRole;
import com.slife.entity.SysUser;
import com.slife.service.ISysRoleService;
import com.slife.service.ISysUserService;
import com.slife.util.ApplicationContextRegister;
import com.slife.util.Encodes;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by chen on 2017/7/14.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */

@Component(value = "authRealm")
public class AuthRealm extends AuthorizingRealm {



    private  Logger logger= LoggerFactory.getLogger(getClass());



    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Setting.HASH_ALGORITHM);
        matcher.setHashIterations(Setting.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
        logger.info("---------密码校验器  -matcher----------------");
    }

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginName=((UsernamePasswordToken) token).getUsername();
        ISysUserService sysUserService = ApplicationContextRegister.getBean(ISysUserService.class);
        SysUser sysUser = sysUserService.getByLoginName(loginName);
        if (sysUser != null) {
            logger.info(sysUser.getName()+"登录");
            if (Global.NO.equals(sysUser.getLoginFlag())) {
                throw new DisabledAccountException();
            }
            byte[] salt = Encodes.decodeHex(sysUser.getPassword().substring(0, 16));
            return new SimpleAuthenticationInfo(new ShiroUser(sysUser.getId(),loginName, sysUser.getName(),sysUser.getPhoto()),
                    sysUser.getPassword().substring(16), ByteSource.Util.bytes(salt),
                    getName());
        } else {
            logger.info(sysUser.getName()+"登录失败");
            throw new UnknownAccountException();
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("登录授权");
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        ISysRoleService sysRoleService = ApplicationContextRegister.getBean(ISysRoleService.class);

        for (SysRole sysRole : sysRoleService.selectRoleByUserId(shiroUser.id)) {
            // 基于Role的权限信息
            info.addRole(sysRole.getCode());
            // 基于Permission的权限信息
            info.addStringPermissions(sysRole.getPermissionList());
        }
        return info;
    }


}