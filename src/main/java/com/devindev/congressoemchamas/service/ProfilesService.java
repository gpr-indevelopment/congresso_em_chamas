package com.devindev.congressoemchamas.service;

import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfilesService {

    @Autowired
    private CamaraAPI camaraAPI;

    private Long currentLegislatureId;

    public List<Profile> findProfilesByName(String name){
        return camaraAPI.requestProfilesByNameAndLegislatureId(name, camaraAPI.requestCurrentLegislatureId().getId());
    }
}
