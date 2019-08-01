package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Computer;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareDto {
    private Long softwareId;
    private String softwareName;
    private String licenseNumber;
    private Computer computer;
}
