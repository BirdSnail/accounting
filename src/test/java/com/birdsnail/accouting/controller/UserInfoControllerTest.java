package com.birdsnail.accouting.controller;

import com.birdsnail.accouting.manager.UserInfoManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilderSupport;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HelloControllerTest {

    private MockMvc mockMvc;
    @Mock
    private UserInfoManager userInfoManager;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController(userInfoManager)).build();
    }

    @Test
    void testSayHello() {

    }
}