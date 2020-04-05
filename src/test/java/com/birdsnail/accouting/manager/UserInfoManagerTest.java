package com.birdsnail.accouting.manager;

import com.birdsnail.accouting.converter.p2c.UserInfoPersistenceToCommon;
import com.birdsnail.accouting.dao.UserInfoDao;
import com.birdsnail.accouting.exception.ResourceNotFoundException;
import com.birdsnail.accouting.model.common.UserInfoCommon;
import com.birdsnail.accouting.model.persistent.UserInfo;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserInfoManagerTest {

    private UserInfoManager userInfoManager;
    @Mock
    private UserInfoDao userInfoDao;

    @BeforeEach
    void setup() {
        userInfoManager = new UserInfoManagerImpl(userInfoDao, new UserInfoPersistenceToCommon());
    }

    @Test
    void testGetUserInfoByName() {
        // Arrange
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
        when(userInfoDao.getUserInfoByUsername(username)).thenReturn(userInfo);
        // Act
        final UserInfoCommon userInfoCommon = userInfoManager.getUserInfoByName(username);
        // Assert
//        assertEquals(username, userInfoCommon.getName());
//        assertEquals(password, userInfoCommon.getPassWord());
//        assertEquals(id, userInfoCommon.getUserId());
        // Assert
        MatcherAssert.assertThat(userInfoCommon, allOf(
                is(notNullValue()),
                hasProperty("userId", is(id)),
                hasProperty("name", is(username)),
                hasProperty("passWord", is(password))
        ));
        verify(userInfoDao).getUserInfoByUsername(username);
    }

    @Test
    void testGetUserInfoByNameWithInvalid() {
        // Arrange
        String username = "";

        // Act
        doReturn(null).when(userInfoDao).getUserInfoByUsername(username);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> userInfoManager.getUserInfoByName(username));
        verify(userInfoDao).getUserInfoByUsername(username);
    }
}