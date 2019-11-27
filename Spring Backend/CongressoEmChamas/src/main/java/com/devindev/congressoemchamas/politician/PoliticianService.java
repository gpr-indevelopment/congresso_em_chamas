package com.devindev.congressoemchamas.politician;

import com.devindev.congressoemchamas.externalapi.CamaraAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoliticianService {

    @Autowired
    private PoliticianRepository repository;

    @Autowired
    private CamaraAPI camaraAPI;

    public void findByName(String name){
        camaraAPI.requestByName(name);
    }
}
