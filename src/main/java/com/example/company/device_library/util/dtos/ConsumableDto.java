package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Consumable;
import com.example.company.device_library.model.Printer;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumableDto {
    private Long consumableId;

    @NotNull(message = "To pole jest wymagane")
    private Consumable.ConsumableType consumableType;

    @NotNull(message = "To pole jest wymagane")
    private Consumable.Color color;

    @NotEmpty(message = "To pole jest wymagane")
    private String consumableMark;

    private Printer printer;
}
