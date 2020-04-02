package com.devindev.congressoemchamas.data.politician;

import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.news.NewsDAO;
import com.devindev.congressoemchamas.data.news.NewsRepository;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import com.devindev.congressoemchamas.externalapi.google.GoogleSearchAPI;
import com.devindev.congressoemchamas.externalapi.twitter.TwitterAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    private GoogleSearchAPI googleSearchAPI;

    public List<Politician> findByName(String name){
        List<Politician> returnPoliticians = new ArrayList<>();
        camaraAPI.requestIdsByName(name).parallelStream().forEach(foundPoliticianId -> {
            Politician builtPolitician = repository.findById(foundPoliticianId);
            if(Objects.isNull(builtPolitician)){
                builtPolitician = camaraAPI.requestPoliticianById(foundPoliticianId);
                builtPolitician.setTwitterUsername(twitterAPI.searchTwitterUsername(builtPolitician));
                builtPolitician.setNews(googleSearchAPI.searchNews(builtPolitician));
                builtPolitician = repository.save(builtPolitician);
            }
            returnPoliticians.add(builtPolitician);
        });
        Collections.sort(returnPoliticians);
        return returnPoliticians;
    }
}
