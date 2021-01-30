package com.example.company.device_library.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void shouldLoadWelcomePageGivenAllUsers() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("welcome"));
    }


    @Test
    public void shouldLoadRegisterPageGivenRegisterForm() throws Exception {
        mvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userDto"))
                .andExpect(view().name("register"));
    }
}