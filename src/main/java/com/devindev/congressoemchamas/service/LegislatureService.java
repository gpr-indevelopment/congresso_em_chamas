package com.devindev.congressoemchamas.service;

import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegislatureService {

    @Autowired
    private CamaraAPI camaraAPI;

    public Legislature getCurrentLegislature(){
        return camaraAPI.requestCurrentLegislatureId();
    }
}
