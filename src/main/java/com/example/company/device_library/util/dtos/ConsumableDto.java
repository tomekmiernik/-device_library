package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Consumable;
import com.example.company.device_library.model.Printer;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumableDto {
    private Long consumableId;
    private Consumable.ConsumableType consumableType;
    private String color;
    private String consumableMark;
    private Printer printer;
}
