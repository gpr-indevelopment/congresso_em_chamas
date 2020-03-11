package com.devindev.congressoemchamas;

import com.devindev.congressoemchamas.politician.Politician;
import com.devindev.congressoemchamas.politician.PoliticianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private PoliticianService politicianService;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politician", method = RequestMethod.GET)
    public List<Politician> getPolitician(@RequestParam ("name") String name){
        return politicianService.findByName(name);
    }
}
