package com.example.company.device_library.util.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceTypesDto {
    private Long deviceTypeId;

    @NotEmpty(message = "To pole jest wymagane")
    private String typeName;

}
