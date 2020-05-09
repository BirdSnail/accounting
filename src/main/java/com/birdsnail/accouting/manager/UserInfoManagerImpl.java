package com.birdsnail.accouting.manager;

import com.birdsnail.accouting.converter.p2c.UserInfoPersistenceToCommon;
import com.birdsnail.accouting.dao.UserInfoDao;
import com.birdsnail.accouting.exception.ResourceNotFoundException;
import com.birdsnail.accouting.model.common.UserInfoCommon;
import com.birdsnail.accouting.model.persistent.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author BirdSnail
 * @date 2020/3/17
 */
@Service
public class UserInfoManagerImpl implements UserInfoManager {

    public static final int HASH_ITERATIONS = 10;
    private UserInfoDao userInfoDao;
    private UserInfoPersistenceToCommon userInfoP2CConverter;

    @Autowired
    public UserInfoManagerImpl(UserInfoDao userInfoDao, UserInfoPersistenceToCommon userInfoP2CConverter) {
        this.userInfoDao = userInfoDao;
        this.userInfoP2CConverter = userInfoP2CConverter;
    }

    @Override
    public UserInfoCommon getUserInfoByName(String username) {
        final UserInfo userInfo = Optional.ofNullable(userInfoDao.getUserInfoByUsername(username))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User %s was not found", username)));
        return userInfoP2CConverter.convert(userInfo);
    }

    @Override
    public UserInfoCommon getUserInfoByUserId(long id) {
        UserInfo userInfo = Optional.ofNullable(userInfoDao.getUserInfoByUserId(id))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User %s was not found", id)));
        return userInfoP2CConverter.convert(userInfo);
    }

    @Override
    public void login(String username, String password) {
        // 1. 收集用户名和密码
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 2. 提交用户名密码给用户认证系统
        final Subject subject = SecurityUtils.getSubject();
        subject.login(token);
    }

    @Override
    public UserInfoCommon register(String username, String password) {
        if (!Objects.isNull(userInfoDao.getUserInfoByUsername(username))) {
            throw new InvalidParameterException(String.format("The user %s is already exits", username));
        }

        // set salt
        String salt = UUID.randomUUID().toString();
        String encryptionPassWord = new Sha256Hash(password, salt, HASH_ITERATIONS).toBase64();

        final UserInfo userInfo = UserInfo.builder()
                .username(username)
                .password(encryptionPassWord)
                .salt(salt)
                .createTime(LocalDateTime.now())
                .build();
        userInfoDao.createNewUser(userInfo);
        return getUserInfoByName(username);
    }

}
