package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.Consumable;
import com.example.company.device_library.util.dtos.ConsumableDto;
import org.springframework.stereotype.Component;

@Component
public class ConsumableMapper implements Mapper<Consumable, ConsumableDto> {
    @Override
    public ConsumableDto map(Consumable from) {
        return ConsumableDto.builder()
                .consumableId(from.getId())
                .consumableType(from.getConsumableType())
                .consumableMark(from.getConsumableMark())
                .color(from.getColor())
                .printer(from.getPrinter())
                .build();
    }

    @Override
    public Consumable reverse(ConsumableDto to) {
        Consumable consumable = new Consumable();
        consumable.setId(to.getConsumableId());
        consumable.setConsumableType(to.getConsumableType());
        consumable.setColor(to.getColor());
        consumable.setConsumableMark(to.getConsumableMark());
        consumable.setPrinter(to.getPrinter());
        return consumable;
    }
}
