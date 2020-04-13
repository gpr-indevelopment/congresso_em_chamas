package com.devindev.congressoemchamas;

import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.externalapi.google.GoogleSearchAPI;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.service.PoliticianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private PoliticianService politicianService;

    @Autowired
    private GoogleSearchAPI googleSearchAPI;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/getPoliticianIds", method = RequestMethod.POST)
    public List<Long> getPoliticianIds(@RequestParam String queryString){
        return politicianService.findIdsByName(queryString);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/getTwitterUsername", method = RequestMethod.POST)
    public String getTwitterUsername(@RequestParam Long politicianId){
        return politicianService.findTwitterUsername(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/getNews", method = RequestMethod.POST)
    public List<News> getNews(@RequestParam Long politicianId){
        return politicianService.findNews(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/getPropositions", method = RequestMethod.POST)
    public List<Proposition> getPropositions(@RequestParam Long politicianId){
        return politicianService.findPropositions(politicianId);
    }
}
