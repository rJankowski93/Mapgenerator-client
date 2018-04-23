package com.mgr.mapgenerator.controller;

import com.mgr.mapgenerator.dto.EncoderDataDTO;
import com.mgr.mapgenerator.service.DeviceService;
import com.mgr.mapgenerator.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/map")
public class MapController {

    private MapService mapService;

    private DeviceService deviceService;

    @Autowired
    public MapController(MapService mapService, DeviceService deviceService) {
        this.mapService = mapService;
        this.deviceService = deviceService;
    }

    @RequestMapping(path = "/encoderData", method = RequestMethod.GET)
    public String getEncoderData(Model model, @RequestParam(required = false) String selectedDevice) {
        mapService.refreshData();
        model.addAttribute("selectedDevice", selectedDevice);
        model.addAttribute("connectedDevices", deviceService.getConnectedDevices());
        model.addAttribute("encoderDataList", mapService.getEncoderData(selectedDevice));
        return "encoder-data.html";
    }

    @RequestMapping(path = "/map", params = {"selectedDevice"}, method = RequestMethod.GET)
    public String getMap(Model model, @RequestParam String selectedDevice) {
        mapService.refreshData();
        model.addAttribute("selectedDevice", selectedDevice);
        model.addAttribute("connectedDevices", deviceService.getConnectedDevices());
        model.addAttribute("encoderDataList", mapService.getEncoderData(selectedDevice));
        return "map.html";
    }
}
