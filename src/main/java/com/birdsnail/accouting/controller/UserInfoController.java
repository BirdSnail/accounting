package com.birdsnail.accouting.controller;

import com.birdsnail.accouting.manager.UserInfoManager;
import com.birdsnail.accouting.model.common.UserInfoCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BirdSnail
 * @date 2020/3/18
 */
@RestController
@RequestMapping("v1.0/users")
public class UserInfoController {

    private UserInfoManager userInfoManager;

    @Autowired
    public UserInfoController(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    @PostMapping
    public UserInfoCommon register(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        return userInfoManager.register(username, password);
    }

}
