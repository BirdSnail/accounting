package com.birdsnail.accouting.converter.p2c;

import com.birdsnail.accouting.model.common.UserInfoCommon;
import com.birdsnail.accouting.model.persistent.UserInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoProtractToCommonTest {


    static UserInfoPersistenceToCommon userToCommon;

    @BeforeAll
    static void setUp() {
        userToCommon = new UserInfoPersistenceToCommon();
    }

    @Test
    void p2c() {
        UserInfo userInfo = UserInfo.builder().username("yhd").build();
        final UserInfoCommon convert = userToCommon.convert(userInfo);
        final UserInfo reverseUserInfo = userToCommon.reverse().convert(convert);
        assertEquals(userInfo, reverseUserInfo);
    }
}