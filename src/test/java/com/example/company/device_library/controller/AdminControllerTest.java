package com.example.company.device_library.controller;

import com.example.company.device_library.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mvc;

    /*@MockBean
    private TestEntityManager entityManager;

    @Before
    public void initialize() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("UserFirstName");
        user.setLastName("UserLastName");
        user.setEmail("user@com.pl");
        user.setLocalization("User workplace");
        user.setActive(true);
        user.setPosition("User work position");

        User user1 = new User();
        user1.setId(2L);
        user1.setFirstName("User_1_FirstName");
        user1.setLastName("User_1_LastName");
        user1.setEmail("user_1@com.pl");
        user1.setLocalization("User_1 workplace");
        user1.setActive(true);
        user1.setPosition("User_1 work position");

        entityManager.persist(user);
        entityManager.persist(user1);
        entityManager.flush();
    }*/

    @Test
    public void shouldLoadMainAdminToolsGivenAllTools() throws Exception {
        mvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/admin-tools"));
    }

}