package com.birdsnail.accouting.controller;

import com.birdsnail.accouting.manager.UserInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BirdSnail
 * @date 2020/3/19
 */
@RestController
@RequestMapping("v1.0/session")
public class SessionController {

    private UserInfoManager userInfoManager;

    @Autowired
    public SessionController(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    @PostMapping
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        userInfoManager.login(username, password);
        return "login success";
    }

}
