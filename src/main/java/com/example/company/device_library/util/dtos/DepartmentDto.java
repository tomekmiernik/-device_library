package com.example.company.device_library.util.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long departmentId;

    @NotEmpty(message = "To pole jest wymagane")
    private String departmentName;

    @NotEmpty(message = "To pole jest wymagane")
    private String departmentShortName;
}
