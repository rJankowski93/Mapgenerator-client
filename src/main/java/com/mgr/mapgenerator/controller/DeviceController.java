package com.mgr.mapgenerator.controller;

import com.mgr.mapgenerator.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/device")
public class DeviceController {

    private DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping(path = "/devices", method = RequestMethod.GET)
    public String getDevices(Model model) {
        model.addAttribute("devices", deviceService.getDevices());
        return "devices";
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("devices", deviceService.searchDevices());
        return "find-devices";
    }

    @RequestMapping(path = "/save", params = {"name"}, method = RequestMethod.GET)
    public String create(@RequestParam("name") String name) {
        deviceService.save(name);
        return "redirect:devices";
    }

    @RequestMapping(path = "/connect/{deviceId}", method = RequestMethod.GET)
    public String connect(@PathVariable("deviceId") Long deviceId) {
        deviceService.connect();
        return "redirect:devices";
    }

    @RequestMapping(path = "/remove/{deviceId}", method = RequestMethod.DELETE)
    public String remove(@PathVariable("deviceId") Long deviceId) {
        deviceService.remove(deviceId);
        return "redirect:devices";
    }
}
