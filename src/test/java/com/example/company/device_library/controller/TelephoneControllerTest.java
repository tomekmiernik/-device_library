package com.example.company.device_library.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TelephoneControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void shouldLoadAddTelephonePageGivenAddTelephoneForm() throws Exception{
        mvc.perform(get("/admin/telephone"))
                .andExpect(model().attribute("formName", "Dodawanie telefonu"))
                .andExpect(model().attributeExists("telephoneDto"))
                .andExpect(view().name("admin/telephone/telephone"));
    }
}