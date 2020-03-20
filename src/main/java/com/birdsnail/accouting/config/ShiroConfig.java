package com.birdsnail.accouting.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

import static com.birdsnail.accouting.manager.UserInfoManagerImpl.HASH_ITERATIONS;

/**
 * @author BirdSnail
 * @date 2020/3/18
 */
@Configuration
public class ShiroConfig {

    public static final String ALGORITHM_NAME = "SHA-256";

    @Bean
    public SecurityManager securityManager(Realm realm) {
        return new DefaultWebSecurityManager(realm);
    }

    /**
     * shiro filter . 实现权限相关的拦截
     *
     * anon: 无需认证就可以访问
     * authc: 要求登陆才可以访问
     * user: remember me -> access
     * role: role -> access
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        final ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
        shiroFilterFactory.setSecurityManager(securityManager);

        LinkedHashMap<String, String> shiroFilterDefinitionMap = new LinkedHashMap<>();
        shiroFilterDefinitionMap.put("/v1.0/session", "anon");
        shiroFilterDefinitionMap.put("/v1.0/users", "anon");
        shiroFilterDefinitionMap.put("/**", "authc");
        shiroFilterFactory.setFilterChainDefinitionMap(shiroFilterDefinitionMap);

        return shiroFilterFactory;
    }


    /**
     * 对加密密码进行匹配，用于登录的时候验证密码
     * @return credential matcher
     */
    @Bean
    public HashedCredentialsMatcher matcher() {
        final HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(ALGORITHM_NAME);
        matcher.setHashIterations(HASH_ITERATIONS);
        matcher.setHashSalted(true);
        matcher.setStoredCredentialsHexEncoded(false);

        return matcher;
    }

}
