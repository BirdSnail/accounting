package com.birdsnail.accouting.dao;

import com.birdsnail.accouting.dao.mapper.UserInfoMapper;
import com.birdsnail.accouting.model.persistent.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author BirdSnail
 * @date 2020/3/18
 */
@Repository
public class UserInfoDapImpl implements UserInfoDao {

    private UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoDapImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        return userInfoMapper.getUserInfoByUsername(username);
    }

    @Override
    public UserInfo getUserInfoByUserId(long id) {
        return userInfoMapper.getUserInfoByUserId(id);
    }

    @Override
    public void createNewUser(UserInfo userInfo) {
        userInfoMapper.createNewUser(userInfo);
    }
}
