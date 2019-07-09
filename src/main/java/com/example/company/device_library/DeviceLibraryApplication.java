package com.example.company.device_library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config.properties")
@SpringBootApplication
public class DeviceLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceLibraryApplication.class, args);
    }

}
