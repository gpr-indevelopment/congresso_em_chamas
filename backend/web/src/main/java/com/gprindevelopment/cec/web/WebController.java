package com.gprindevelopment.cec.web;

import com.gprindevelopment.cec.core.expense.MonthlyExpense;
import com.gprindevelopment.cec.core.externalapi.jarbas.model.JarbasReimbursement;
import com.gprindevelopment.cec.core.politician.News;
import com.gprindevelopment.cec.core.politician.Politician;
import com.gprindevelopment.cec.core.politician.Profile;
import com.gprindevelopment.cec.core.proposition.Proposition;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * API design best practices: https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/
 * Partial response for APIs: https://developers.google.com/drive/api/v3/performance
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class WebController {

    private final WebService webService;

    @RequestMapping(path = "/politicians/{politicianId}", method = RequestMethod.GET)
    public Politician getPolitician(@PathVariable Long politicianId) {
        return webService.findPoliticianById(politicianId);
    }

    @RequestMapping(path = "/politicians/{politicianId}/propositions", method = RequestMethod.GET)
    public List<Proposition> getPropositionsByPoliticianId(@PathVariable Long politicianId) {
        return webService.findPropositionsByPoliticianId(politicianId);
    }

    @RequestMapping(path = "/politicians/{politicianId}/news", method = RequestMethod.GET)
    public List<News> getNews(@PathVariable Long politicianId) {
        return webService.findNewsByPoliticianId(politicianId);
    }

    @RequestMapping(path = "/politicians/{politicianId}/monthlyexpenses", method = RequestMethod.GET)
    public List<MonthlyExpense> getMonthlyExpenses(@PathVariable Long politicianId, @RequestParam(required = false) List<Integer> years, @RequestParam(required = false) Integer lastMonths) {
        return webService.findMonthlyExpensesByPoliticianId(politicianId, years, lastMonths);
    }

    @RequestMapping(path = "/profiles", method = RequestMethod.GET)
    public List<Profile> getProfilesByName(@RequestParam String name) {
        return webService.findProfilesByName(name);
    }

    @RequestMapping(path = "/expenses/{documentCode}", method = RequestMethod.GET)
    @Cacheable(cacheNames = "jarbasReimbursementByDocumentCode")
    public JarbasReimbursement getJarbasReimbursement(@PathVariable Long documentCode) {
        return webService.findJarbasReimbursement(documentCode);
    }
}