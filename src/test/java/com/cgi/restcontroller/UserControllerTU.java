package com.cgi.restcontroller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.cgi.Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureMockMvc
public class UserControllerTU {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindUsers() throws Exception {

        this.mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testFindSuggestions() throws Exception {
        this.mockMvc.perform(get("/user/suggestions/{userId}", 0)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

}
