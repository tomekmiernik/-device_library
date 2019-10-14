package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Computer;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareDto {
    private Long softwareId;

    @NotEmpty(message = "To pole jest wymagane")
    private String softwareName;

    private String licenseNumber;

    private Computer computer;
}
