package com.mgr.mapgenerator.service;

import com.mgr.mapgenerator.dto.DeviceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Value("${resource.device.devices}")
    private String resourceDevices;


    @Value("${resource.device.device}")
    private String resourceDevice;

    @Value("${resource.device.search}")
    private String resourceSearch;

    @Value("${resource.device.create}")
    private String resourceDeviceCreate;

    @Value("${resource.device.connect}")
    private String resourceDeviceConnect;

    @Value("${resource.device.remove}")
    private String resourceDeviceRemove;

    private RestTemplate restTemplate;

    @Autowired
    public DeviceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<DeviceDTO> getDevices() {
        return Arrays.stream(restTemplate.getForObject(resourceDevices, DeviceDTO[].class)).collect(Collectors.toList());
    }

    public List<DeviceDTO> getDevice(Long id) {
        return Arrays.stream(restTemplate.getForObject(resourceDevice, DeviceDTO[].class, id)).collect(Collectors.toList());
    }

    public List<DeviceDTO> searchDevices() {
        return Arrays.stream(restTemplate.getForObject(resourceSearch, DeviceDTO[].class)).collect(Collectors.toList());
    }

    public DeviceDTO save(String name) {
        return restTemplate.postForObject(resourceDeviceCreate, name, DeviceDTO.class);
    }

    public ResponseEntity connect() {
        return restTemplate.getForObject(resourceDeviceConnect, ResponseEntity.class);
    }

    public void remove(Long id) {
        restTemplate.delete(resourceDeviceRemove, id);
    }
}
