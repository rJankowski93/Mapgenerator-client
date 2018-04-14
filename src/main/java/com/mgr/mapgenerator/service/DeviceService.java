package com.mgr.mapgenerator.service;

import com.mgr.mapgenerator.dto.DeviceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Value("${resource.devices}")
    private String resourceDevices;


    @Value("${resource.device}")
    private String resourceDevice;


    @Value("${resource.search}")
    private String resourceSearch;

    @Value("${resource.create}")
    private String resourceDeviceCreate;


    private RestTemplate restTemplate;

    @Autowired
    public DeviceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<DeviceDTO> getDevices() {
        return Arrays.stream(restTemplate.getForObject(resourceDevices, DeviceDTO[].class)).collect(Collectors.toList());
    }

    public List<DeviceDTO> getDevice() {
        return Arrays.stream(restTemplate.getForObject(resourceDevice, DeviceDTO[].class)).collect(Collectors.toList());
    }

    public List<DeviceDTO> searchDevices() {
        return Arrays.stream(restTemplate.getForObject(resourceSearch, DeviceDTO[].class)).collect(Collectors.toList());
    }

    public DeviceDTO save(String  name) {
        return restTemplate.postForObject(resourceDeviceCreate, name, DeviceDTO.class);
    }
}
