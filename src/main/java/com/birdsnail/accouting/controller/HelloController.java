package com.birdsnail.accouting.controller;

import com.birdsnail.accouting.converter.p2c.UserInfoPersistenceToCommon;
import com.birdsnail.accouting.manager.UserInfoManager;
import com.birdsnail.accouting.model.common.UserInfoCommon;
import com.birdsnail.accouting.model.persistent.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BirdSnail
 * @date 2020/3/17
 */
@RestController
public class HelloController {

    private UserInfoManager userInfoManager;
    private UserInfoPersistenceToCommon userInfoConverter;

    @Autowired
    public HelloController(UserInfoManager userInfoManager, UserInfoPersistenceToCommon userInfoConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoConverter = userInfoConverter;
    }

    @GetMapping("v1/hello")
    public UserInfoCommon sayHello(@RequestParam("name") String name) {
        return userInfoManager.getUserInfoByName(name);
    }

}
