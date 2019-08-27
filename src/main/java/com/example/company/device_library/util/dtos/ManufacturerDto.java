package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.DeviceType;
import lombok.*;

import java.util.HashSet;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDto {
    private Long manufacturerId;
    private String manufacturerName;
    private HashSet<DeviceType> deviceTypes;
}
