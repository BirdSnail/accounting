package com.birdsnail.accouting.dao;

import com.birdsnail.accouting.dao.mapper.UserInfoMapper;
import com.birdsnail.accouting.model.persistent.UserInfo;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserInfoDaoTest {

    private UserInfoDao userInfoDao;
    @Mock
    private UserInfoMapper userInfoMapper;

    @BeforeEach
    void setup() {
        userInfoDao = new UserInfoDapImpl(userInfoMapper);
    }

    @Test
    void testGetUserInfoByUsername() {
        long id = 0;
        String username = "yanghuadong";
        String password = "123";
        LocalDateTime createTime = LocalDateTime.now();
        UserInfo userInfo = UserInfo.builder()
                .id(id)
                .username(username)
                .password(password)
                .createTime(createTime)
                .build();

        doReturn(userInfo).when(userInfoMapper).getUserInfoByUsername(username);

        // act
        final UserInfo result = userInfoDao.getUserInfoByUsername(username);

        // assert
        MatcherAssert.assertThat(result, samePropertyValuesAs(userInfo));
        verify(userInfoMapper).getUserInfoByUsername(username);
    }

}