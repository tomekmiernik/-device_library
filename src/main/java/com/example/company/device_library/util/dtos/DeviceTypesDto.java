package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.DeviceManufacturer;
import com.example.company.device_library.model.DeviceType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceTypesDto {

    private Long deviceTypeId;
    private String typeName;
}
