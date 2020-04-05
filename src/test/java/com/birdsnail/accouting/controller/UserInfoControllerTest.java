package com.birdsnail.accouting.controller;

import com.birdsnail.accouting.exception.GlobalExceptionHandler;
import com.birdsnail.accouting.manager.UserInfoManager;
import com.birdsnail.accouting.model.common.UserInfoCommon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserInfoControllerTest {

    private MockMvc mockMvc;
    @Mock
    private UserInfoManager userInfoManager;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserInfoController(userInfoManager))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testGetUserInfoByUserId() throws Exception {
        // Arrange
        long id = 1;
        String username = "yanghuadong";
        String password = "123";
        String salt = "abc123";

        UserInfoCommon userInfoInCommon = UserInfoCommon.builder()
                .userId(id)
                .salt(salt)
                .name(username)
                .passWord(password)
                .build();

        doReturn(userInfoInCommon).when(userInfoManager).getUserInfoByUserId(anyLong());

        // Act && Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1.0/users/" + id))
                .andExpect(matchAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        content().string("{\"userId\":1,\"name\":\"yanghuadong\",\"salt\":\"abc123\",\"passWord\":\"123\"}"))
                );
        verify(userInfoManager).getUserInfoByUserId(id);
    }

    @Test
    void testGetUserInfoByUserIdWithInvalidUserId() throws Exception {
        // Arrange
        long id = -1;

        // Act && Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1.0/users/" + id))
                .andExpect(matchAll(
                        status().is4xxClientError(),
                        content().contentType("application/json"),
                        content().string(String.format("{\"statusCode\":400,\"errorMessage\":\"the user id: %s is invalid\",\"errorCode\":\"invalid parameter\",\"errorType\":\"CLIENT\"}", id)))
                );
    }

}