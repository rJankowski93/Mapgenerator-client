package com.mgr.mapgenerator.service;

import com.mgr.mapgenerator.dto.DeviceDTO;
import com.mgr.mapgenerator.dto.EncoderDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapService {

    @Value("${resource.map.generateData}")
    private String resourceGenerateData;


    @Value("${resource.map.encoderData}")
    private String resourceEncoderData;


    private RestTemplate restTemplate;

    @Autowired
    public MapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void generateData() {
        restTemplate.postForEntity(resourceGenerateData, null, EncoderDataDTO[].class);
    }

    public List<EncoderDataDTO> getAll() {
        return Arrays.stream(restTemplate.getForObject(resourceEncoderData, EncoderDataDTO[].class)).collect(Collectors.toList());
    }
}
