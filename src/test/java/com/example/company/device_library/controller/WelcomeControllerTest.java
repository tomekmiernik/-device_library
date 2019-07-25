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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
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

    }

    @Test
    public void shouldLoadWelcomePageGivenAllUsers() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("welcome"));
    }

    @Test
    public void shouldLoadLoginPageGivenLoginForm() throws Exception{
        mvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void shouldLoadRegisterPageGivenRegisterForm() throws Exception{
        mvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userDto"))
                .andExpect(view().name("register"));
    }
}