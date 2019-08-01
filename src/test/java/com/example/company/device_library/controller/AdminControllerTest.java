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
                .andExpect(model().attribute("title", "Narzędzia administracyjne"))
                .andExpect(model().attribute("pageName", "Narzędzia administracyjne"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/admin-tools"));
    }


    @Test
    public void shouldLoadAddComputerPageGivenAddComputerForm() throws Exception {
        mvc.perform(get("/admin/add-computer"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("title", "Dodawanie komputera"))
                .andExpect(model().attribute("formName", "dodawanie komputera"))
                .andExpect(model().attributeExists("computerDto"))
                .andExpect(view().name("admin/add-computer"));
    }

    @Test
    public void shouldLoadAddConsumablePageGivenAddConsumableForm() throws Exception {
        mvc.perform(get("/admin/add-consumable"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("title", "Dodawanie tuszu / toneru"))
                .andExpect(model().attribute("formName", "dodawanie tuszu / toneru"))
                .andExpect(model().attributeExists("consumableDto"))
                .andExpect(view().name("admin/add-consumable"));
    }

    @Test
    public void shouldLoadAddDepartmentPageGivenAddDepartmentForm() throws Exception {
        mvc.perform(get("/admin/add-department"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("title", "Dodawanie działu"))
                .andExpect(model().attribute("formName", "dodawanie działu"))
                .andExpect(model().attributeExists("departmentDto"))
                .andExpect(view().name("admin/add-department"));
    }




    @Test
    public void shouldLoadAddPeripheralPageGivenAddPeripheralForm() throws Exception {
        mvc.perform(get("/admin/add-peripheral"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("title", "Dodawanie myszy / klawiatury"))
                .andExpect(model().attribute("formName", "dodawanie myszy / klawiatury"))
                .andExpect(model().attributeExists("peripheralDto"))
                .andExpect(view().name("admin/add-peripheral"));
    }

    @Test
    public void shouldLoadAddPrinterPageGivenAddPrinterForm() throws Exception {
        mvc.perform(get("/admin/add-printer"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("title", "Dodawanie drukarki"))
                .andExpect(model().attribute("formName", "dodawanie drukarki"))
                .andExpect(model().attributeExists("printerDto"))
                .andExpect(view().name("admin/add-printer"));
    }

    @Test
    public void shouldLoadAddSoftwarePageGivenAddSoftwareForm() throws Exception{
        mvc.perform(get("/admin/add-software"))
                .andExpect(model().attribute("title", "Dodawanie oprogramowania"))
                .andExpect(model().attribute("formName", "dodawanie oprogramowania"))
                .andExpect(model().attributeExists("softwareDto"))
                .andExpect(view().name("admin/add-software"));
    }

    @Test
    public void shouldLoadAddTelephonePageGivenAddTelephoneForm() throws Exception{
        mvc.perform(get("/admin/add-telephone"))
                .andExpect(model().attribute("title", "Dodawanie telefonu"))
                .andExpect(model().attribute("formName", "dodawanie telefonu"))
                .andExpect(model().attributeExists("telephoneDto"))
                .andExpect(view().name("admin/add-telephone"));
    }
}