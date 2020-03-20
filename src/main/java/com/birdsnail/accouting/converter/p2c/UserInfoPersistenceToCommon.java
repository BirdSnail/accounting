package com.birdsnail.accouting.converter.p2c;

import com.birdsnail.accouting.model.common.UserInfoCommon;
import com.birdsnail.accouting.model.persistent.UserInfo;
import com.google.common.base.Converter;
import org.springframework.stereotype.Component;

/**
 * @author BirdSnail
 * @date 2020/3/15
 */
@Component
public class UserInfoPersistenceToCommon extends Converter<UserInfo, UserInfoCommon> {

    @Override
    protected UserInfoCommon doForward(UserInfo userInfo) {
        return UserInfoCommon.builder()
                .name(userInfo.getUsername())
                .passWord(userInfo.getPassword())
                .salt(userInfo.getSale())
                .userId(userInfo.getId())
                .build();
    }

    @Override
    protected UserInfo doBackward(UserInfoCommon userInfoCommon) {
        return UserInfo.builder()
                .username(userInfoCommon.getName())
                .build();
    }
}
