package com.mgr.mapgenerator.controller;

import com.mgr.mapgenerator.dto.EncoderDataDTO;
import com.mgr.mapgenerator.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/map")
public class MapController {

    private MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

//    @RequestMapping(path = "/generateData", method = RequestMethod.GET)
//    public String generateData() {
//        mapService.generateData();
//        return "forward:/encoderData";
//    }

    @RequestMapping(path = "/encoderData", method = RequestMethod.GET)
    public String getEncoderData(Model model) {
        mapService.generateData();
        model.addAttribute("encoderDataList",  mapService.getAll());
        return "encoder-data.html";
    }
}
