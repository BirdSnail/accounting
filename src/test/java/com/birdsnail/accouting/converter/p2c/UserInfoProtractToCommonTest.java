package com.birdsnail.accouting.converter.p2c;

import com.birdsnail.accouting.model.common.UserInfoCommon;
import com.birdsnail.accouting.model.persistent.UserInfo;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UserInfoProtractToCommonTest {

    private UserInfoPersistenceToCommon p2c;

    @BeforeEach
    void setup() {
        p2c = new UserInfoPersistenceToCommon();
    }

    @Test
    void testDoForward() {
        long id = 0;
        String username = "yanghuadong";
        String password = "123";
        String salt = "abc123";
        LocalDateTime createTime = LocalDateTime.now();

        UserInfo userInfo = UserInfo.builder()
                .id(id)
                .salt(salt)
                .username(username)
                .password(password)
                .createTime(createTime)
                .build();

        final UserInfoCommon result = p2c.convert(userInfo);

        MatcherAssert.assertThat(result, allOf(
                is(notNullValue()),
                hasProperty("userId", is(id)),
                hasProperty("name", is(username)),
                hasProperty("salt", is(salt)),
                hasProperty("passWord", is(password))
        ));
    }

    @Test
    void testDoBackward() {

    }
}