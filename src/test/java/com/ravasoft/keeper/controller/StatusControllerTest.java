package com.ravasoft.keeper.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
@WebAppConfiguration
public class StatusControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void before() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .build();
    }

    @Test
    public void process() throws Exception {

        LocalDateTime currentDateTime = LocalDateTime.now();

        MockHttpServletResponse response = this.mockMvc.perform(get("/status")).andReturn().getResponse();
        assertEquals("application/json", response.getContentType());
        assertEquals(200, response.getStatus());

        String content = response.getContentAsString();
        int semicolonPos = content.indexOf(':');
        int endBracePos = content.indexOf('}');

        LocalDateTime serverDateTime = LocalDateTime.parse(content.substring(semicolonPos + 2, endBracePos - 1));
        long responseTime = ChronoUnit.SECONDS.between(currentDateTime, serverDateTime);
        assertEquals(0, responseTime, "Response time should be less that one second");
    }

}
