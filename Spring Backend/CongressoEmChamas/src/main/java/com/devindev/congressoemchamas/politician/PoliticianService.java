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

    // TODO: 27/11/2019 Optimize this arquitecture and query to gain performance. 
    public List<Politician> findByName(String name){
        List<Politician> foundPoliticians = camaraAPI.requestByName(name);
        List<Politician> returnPoliticians = new ArrayList<>();
        foundPoliticians.forEach(foundPolitician -> {
            Politician builtPolitician = repository.findById(foundPolitician.getId());
            if(Objects.isNull(builtPolitician)){
                foundPolitician.setTwitterUsername(twitterAPI.searchUsername(foundPolitician.getName()));
                builtPolitician = foundPolitician;
                repository.save(builtPolitician);
            }
            returnPoliticians.add(builtPolitician);
        });
        return returnPoliticians;
    }
}
