package com.example.company.device_library.util.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDto {
    private Long manufacturerId;

    @NotEmpty(message = "To pole jest wymagane")
    private String manufacturerName;
}
