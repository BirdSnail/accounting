package com.birdsnail.accouting.manager;


import com.birdsnail.accouting.model.common.UserInfoCommon;
import com.birdsnail.accouting.model.persistent.UserInfo;

/**
 *
 * @author BirdSnail
 * @date 2020/3/17
 */
public interface UserInfoManager {

    /**
     *  get a {@link UserInfo} instance by name
     * @param name name
     * @return user
     */
   UserInfoCommon getUserInfoByName(String name);

    /**
     * login with username and password
     * @param username user name
     * @param password password
     */
    void login(String username, String password);


    /**
     * registry a new user with username and password
     * @param username username
     * @param password password
     * @return new registry user
     */
    UserInfoCommon register(String username, String password);

}
