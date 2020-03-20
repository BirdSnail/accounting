package com.birdsnail.accouting.config;

import com.birdsnail.accouting.manager.UserInfoManager;
import com.birdsnail.accouting.model.common.UserInfoCommon;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author BirdSnail
 * @date 2020/3/18
 */
@Component
public class UserRealm extends AuthorizingRealm {

    private UserInfoManager userInfoManager;
    private HashedCredentialsMatcher credentialsMatcher;

    @Autowired
    public UserRealm(UserInfoManager userInfoManager, HashedCredentialsMatcher credentialsMatcher) {
        super(credentialsMatcher);
        this.userInfoManager = userInfoManager;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        UserInfoCommon userInfo = userInfoManager.getUserInfoByName(username);
        return new SimpleAuthenticationInfo(
                username,
                userInfo.getPassWord(),
                ByteSource.Util.bytes(userInfo.getSalt()),
                this.getName());
    }
}
