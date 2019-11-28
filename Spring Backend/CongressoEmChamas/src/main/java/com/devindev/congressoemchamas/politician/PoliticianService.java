package com.devindev.congressoemchamas.politician;

import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import com.devindev.congressoemchamas.externalapi.twitter.TwitterAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PoliticianService {

    @Autowired
    private PoliticianRepository repository;

    @Autowired
    private CamaraAPI camaraAPI;

    @Autowired
    private TwitterAPI twitterAPI;

    public List<Politician> findByName(String name){
        List<Politician> returnPoliticians = new ArrayList<>();
        camaraAPI.requestIdsByName(name).parallelStream().forEach(foundPoliticianId -> {
            Politician builtPolitician = repository.findById(foundPoliticianId);
            if(Objects.isNull(builtPolitician)){
                builtPolitician = camaraAPI.requestPoliticianById(foundPoliticianId);
                builtPolitician.setTwitterUsername(twitterAPI.searchUsername(builtPolitician.getName()));
                repository.save(builtPolitician);
            }
            returnPoliticians.add(builtPolitician);
        });
        return returnPoliticians;
    }
}
