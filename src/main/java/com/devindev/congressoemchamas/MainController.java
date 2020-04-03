package com.devindev.congressoemchamas;

import com.devindev.congressoemchamas.externalapi.google.GoogleSearchAPI;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.politician.PoliticianService;
import com.devindev.congressoemchamas.requests.PoliticiansRequest;
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
    @RequestMapping(path = "/politicians", method = RequestMethod.POST)
    public List<Politician> getPoliticians(PoliticiansRequest request){
        List<Politician> politicians = politicianService.findByName(request.getQueryString());
        return politicians;
    }
}
