package com.birdsnail.accouting.dao;

import com.birdsnail.accouting.model.persistent.UserInfo;

/**
 * @author yanghuadong
 * @date 2020年3月18日
 */
public interface UserInfoDao {

    /**
     * get user by name
     * @param username username
     * @return user
     */
    UserInfo getUserInfoByUsername(String username);

    /**
     * 创建一个新的用户，实现用户的注册
     * @param userInfo a new user
     */
    void createNewUser(UserInfo userInfo);
}
