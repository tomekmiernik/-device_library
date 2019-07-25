package com.example.company.device_library;

import com.example.company.device_library.controller.WelcomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceLibraryApplicationTests {

    @Autowired
    private WelcomeController welcomeController;

    @Test
    public void contextLoads() {
        assertThat(welcomeController).isNotNull();
    }

}
