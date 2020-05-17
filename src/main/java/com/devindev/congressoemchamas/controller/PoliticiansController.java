package com.devindev.congressoemchamas.controller;

import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.externalapi.google.GoogleNewsAPI;
import com.devindev.congressoemchamas.service.PoliticiansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * API design best practices: https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/
 * Partial response for APIs: https://developers.google.com/drive/api/v3/performance
 */
@RestController
public class PoliticiansController {

    @Autowired
    private PoliticiansService politiciansService;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}", method = RequestMethod.GET)
    public Politician getPolitician(@PathVariable Long politicianId){
        return politiciansService.findPolitician(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/propositions", method = RequestMethod.GET)
    public List<Proposition> getPropositionsByPoliticianId(@PathVariable Long politicianId){
        return politiciansService.findPropositionsByPoliticianId(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/monthlyexpenses", method = RequestMethod.GET)
    public List<MonthlyExpense> getMonthlyExpensesByPoliticianId(@PathVariable Long politicianId, @RequestParam(required = false) Integer[] months, @RequestParam(required = false) Integer[] years){
        return politiciansService.findMonthlyExpensesByPoliticianId(politicianId, months, years);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/news", method = RequestMethod.GET)
    public List<News> getNews(@PathVariable Long politicianId){
        return politiciansService.findNewsByPoliticianId(politicianId);
    }
}