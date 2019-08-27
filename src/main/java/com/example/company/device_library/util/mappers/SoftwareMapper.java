package com.example.company.device_library.util.mappers;

import com.example.company.device_library.model.Software;
import com.example.company.device_library.util.dtos.SoftwareDto;
import org.springframework.stereotype.Component;

@Component
public class SoftwareMapper implements Mapper<Software, SoftwareDto> {
    @Override
    public SoftwareDto map(Software from) {
        return SoftwareDto.builder()
                .softwareId(from.getId())
                .softwareName(from.getSoftwareName())
                .licenseNumber(from.getLicenseNumber())
                .computer(from.getComputer())
                .build();
    }

    @Override
    public Software reverse(SoftwareDto to) {
        Software software = new Software();
        software.setId(to.getSoftwareId());
        software.setSoftwareName(to.getSoftwareName());
        software.setLicenseNumber(to.getLicenseNumber());
        software.setComputer(to.getComputer());
        return software;
    }
}
