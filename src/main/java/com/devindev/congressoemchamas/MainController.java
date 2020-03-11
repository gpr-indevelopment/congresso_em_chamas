package com.devindev.congressoemchamas;

import com.devindev.congressoemchamas.politician.Politician;
import com.devindev.congressoemchamas.politician.PoliticianService;
import com.devindev.congressoemchamas.requests.PoliticiansRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private PoliticianService politicianService;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians", method = RequestMethod.POST)
    public List<Politician> getPoliticians(PoliticiansRequest request){
        return politicianService.findByName(request.getQueryString());
    }
}
