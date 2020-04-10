package com.birdsnail.accouting.controller;

import com.birdsnail.accouting.manager.UserInfoManager;
import com.birdsnail.accouting.model.common.UserInfoCommon;
import com.birdsnail.accouting.model.service.UserInfoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;


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

    @PostMapping(produces = "application/json", consumes = "application/json")
    public UserInfoCommon register(@RequestBody UserInfoView userInfoView) {
        return userInfoManager.register(userInfoView.getUsername(), userInfoView.getPassWord());
    }

    @GetMapping("/{id}")
    public UserInfoCommon getUserInfoByUserId(@PathVariable("id") long id) {
        if (id <= 0L) {
            throw new InvalidParameterException(String.format("the user id: %s is invalid", id));
        }
        return userInfoManager.getUserInfoByUserId(id);
    }

}
