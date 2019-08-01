package com.example.company.device_library.util.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDto {
    private Long manufacturerId;
    private String manufacturerName;

}
