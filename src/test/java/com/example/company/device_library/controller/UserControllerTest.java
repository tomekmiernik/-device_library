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
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldLoadAddUserPageGivenAddUserForm() throws Exception {
        mvc.perform(get("/admin/add-user"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("title", "Dodawanie osoby"))
                .andExpect(model().attribute("formName", "dodawanie osoby"))
                .andExpect(model().attributeExists("userDto"))
                .andExpect(view().name("admin/add-user"));
    }
}