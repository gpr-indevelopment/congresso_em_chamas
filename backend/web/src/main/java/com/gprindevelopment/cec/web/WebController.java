package com.gprindevelopment.cec.web;

import com.gprindevelopment.cec.core.expense.MonthlyExpense;
import com.gprindevelopment.cec.core.politician.News;
import com.gprindevelopment.cec.core.politician.Politician;
import com.gprindevelopment.cec.core.politician.Profile;
import com.gprindevelopment.cec.core.proposition.Proposition;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * API design best practices: https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/
 * Partial response for APIs: https://developers.google.com/drive/api/v3/performance
 */
@RestController
@RequiredArgsConstructor
public class WebController {

    private final WebService webService;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}", method = RequestMethod.GET)
    public Politician getPolitician(@PathVariable Long politicianId) {
        return webService.findPoliticianById(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/propositions", method = RequestMethod.GET)
    public List<Proposition> getPropositionsByPoliticianId(@PathVariable Long politicianId) {
        return webService.findPropositionsByPoliticianId(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/news", method = RequestMethod.GET)
    public List<News> getNews(@PathVariable Long politicianId) {
        return webService.findNewsByPoliticianId(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/monthlyexpenses", method = RequestMethod.GET)
    public List<MonthlyExpense> getMonthlyExpenses(@PathVariable Long politicianId, @RequestParam(required = false) List<Integer> years, @RequestParam(required = false) Integer lastMonths) {
        return webService.findMonthlyExpensesByPoliticianId(politicianId, years, lastMonths);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/profiles", method = RequestMethod.GET)
    public List<Profile> getProfilesByName(@RequestParam String name) {
        return webService.findProfilesByName(name);
    }
}