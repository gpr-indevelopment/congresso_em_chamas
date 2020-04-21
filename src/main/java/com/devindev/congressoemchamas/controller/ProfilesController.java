package com.devindev.congressoemchamas.controller;

import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.service.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfilesController {

    @Autowired
    private ProfilesService profilesService;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/profiles", method = RequestMethod.GET)
    public List<Profile> getProfilesByName(@RequestParam String name){
        return profilesService.findProfilesByName(name);
    }
}
