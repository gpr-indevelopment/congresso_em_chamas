package com.devindev.congressoemchamas.controller;

import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.service.LegislatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LegislatureController {

    @Autowired
    private LegislatureService legislatureService;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/legislature", method = RequestMethod.GET)
    public Legislature getCurrentLegislature(){
        return legislatureService.getCurrentLegislature();
    }
}
