package com.devindev.congressoemchamas;

import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.service.PoliticianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * API design best practices: https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/
 * Partial response for APIs: https://developers.google.com/drive/api/v3/performance
 */
@RestController
public class MainController {

    @Autowired
    private PoliticianService politicianService;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians", method = RequestMethod.GET)
    public List<Long> getPoliticiansByName(@RequestParam String name){
        return politicianService.findPoliticianIdsByName(name);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}", method = RequestMethod.GET)
    public Politician getPolitician(@PathVariable Long politicianId){
        return politicianService.findPolitician(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/propositions", method = RequestMethod.GET)
    public List<Proposition> getPropositionsByPoliticianId(@PathVariable Long politicianId){
        return politicianService.findPropositionsByPoliticianId(politicianId);
    }
}
