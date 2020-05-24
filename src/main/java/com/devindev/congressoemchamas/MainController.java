package com.devindev.congressoemchamas;

import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.data.proposition.Proposition;
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
    private MainService mainService;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}", method = RequestMethod.GET)
    public Politician getPolitician(@PathVariable Long politicianId){
        return mainService.findById(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/propositions", method = RequestMethod.GET)
    public List<Proposition> getPropositionsByPoliticianId(@PathVariable Long politicianId){
        return mainService.findPropositionsByPoliticianId(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/news", method = RequestMethod.GET)
    public List<News> getNews(@PathVariable Long politicianId){
        return mainService.findNewsByPoliticianId(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/monthlyexpenses/{legislatureId}", method = RequestMethod.GET)
    public List<MonthlyExpense> getMonthlyExpensesByPoliticianId(@PathVariable Long politicianId, @PathVariable Long legislatureId){
        return mainService.findByLegislature(politicianId, legislatureId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/monthlyexpenses", method = RequestMethod.GET)
    public List<MonthlyExpense> getMonthlyExpensesByPoliticianId(@PathVariable Long politicianId, @RequestParam(required = false) List<Integer> months, @RequestParam(required = false) List<Integer> years){
        return mainService.findMonthlyExpensesByPoliticianId(politicianId, months, years);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/legislature", method = RequestMethod.GET)
    public Legislature getCurrentLegislature(){
        return mainService.getCurrentLegislature();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/profiles", method = RequestMethod.GET)
    public List<Profile> getProfilesByName(@RequestParam String name){
        return mainService.findProfilesByName(name);
    }
}