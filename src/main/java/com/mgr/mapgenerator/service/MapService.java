package com.mgr.mapgenerator.service;

import com.mgr.mapgenerator.dto.DeviceDTO;
import com.mgr.mapgenerator.dto.EncoderDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MapService {

    @Value("${resource.map.refreshData}")
    private String resourceRefreshData;


    @Value("${resource.map.encoderData}")
    private String resourceEncoderData;

    private RestTemplate restTemplate;

    @Autowired
    public MapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void refreshData(String deviceName) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("deviceName", deviceName);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(parameters, new HttpHeaders());
        restTemplate.postForEntity(resourceRefreshData, entity, EncoderDataDTO[].class);
    }

    public List<EncoderDataDTO> getEncoderData(String selectedDevice) {
        if (selectedDevice != null) {
            return Arrays.stream(restTemplate.getForObject(resourceEncoderData, EncoderDataDTO[].class, selectedDevice)).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

}
