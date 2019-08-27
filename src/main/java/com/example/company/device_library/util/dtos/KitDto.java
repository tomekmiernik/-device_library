package com.example.company.device_library.util.dtos;

import com.example.company.device_library.model.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KitDto {
    private Long kitId;
    private Long userId;
    private Long mobileDeviceId;
    private Long computerId;
    private Long monitorId;
    private Long printerId;
}
