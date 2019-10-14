package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.Computer;
import com.example.company.device_library.model.Consumable;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrinterDto {
    private Long printerId;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceManufacturer;

    @NotEmpty(message = "To pole jest wymagane")
    private String deviceType;

    @NotEmpty(message = "To pole jest wymagane")
    private String serialNumber;

    private String ipAddress;

    private Computer computer;

    private Collection<Consumable> consumableCollection;

}
