package com.mgr.mapgenerator.controller;

import com.mgr.mapgenerator.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String create(@RequestParam("name") String name, RedirectAttributes redirectAttributes) {
        try {
            deviceService.save(name);
            redirectAttributes.addFlashAttribute("message", "Added device");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed operation with message: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/device/devices";
    }

    @RequestMapping(path = "/connect/{deviceId}", method = RequestMethod.GET)
    public String connect(@PathVariable("deviceId") Long deviceId, RedirectAttributes redirectAttributes) {
        try {
            deviceService.connect(deviceId);
            redirectAttributes.addFlashAttribute("message", "Connected device");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("message", "Failed operation with message: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/device/devices";
    }

    @RequestMapping(path = "/remove/{deviceId}", method = RequestMethod.DELETE)
    public String remove(@PathVariable("deviceId") Long deviceId, RedirectAttributes redirectAttributes) {
        try {
            deviceService.remove(deviceId);
            redirectAttributes.addFlashAttribute("message", "Removed device");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed operation with message: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/device/devices";
    }
}
