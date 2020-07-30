package com.devindev.congressoemchamas.service;

import com.devindev.congressoemchamas.data.MainRepository;
import com.devindev.congressoemchamas.data.expenses.Expense;
import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import com.devindev.congressoemchamas.externalapi.google.GoogleNewsAPI;
import com.devindev.congressoemchamas.externalapi.twitter.TwitterAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class MainService {

    @Autowired
    private MainRepository politiciansRepository;

    @Autowired
    private CamaraAPI camaraAPI;

    @Autowired
    private TwitterAPI twitterAPI;

    @Autowired
    private GoogleNewsAPI googleNewsAPI;

    public Politician findById(Long politicianId){
        return politiciansRepository.findById(politicianId);
    }

    public List<Proposition> findPropositionsByPoliticianId(Long politicianId){
        return politiciansRepository.findAllPropositionsByPolitician(politicianId);
    }

    public List<News> findNewsByPoliticianId(Long politicianId){
        return googleNewsAPI.requestNews(politiciansRepository.findById(politicianId).getName());
    }

    public List<MonthlyExpense> findMonthlyExpensesByPoliticianId(Long politicianId, List<Integer> years, Integer lastMonths) {
        List<Expense> expenses;
        if(Objects.isNull(lastMonths)){
            if(Objects.isNull(years) || years.isEmpty()) {
                expenses = requestExpensesByCurrentLegislature(politicianId);
            } else {
                expenses = requestExpensesByYears(politicianId, years);
            }
        } else {
            expenses = requestExpensesByLastMonths(politicianId, lastMonths);
        }
        return MonthlyExpense.build(expenses);
    }

    private List<Expense> requestExpensesByYears(Long politicianId, List<Integer> years) {
        return camaraAPI.requestAllExpensesByPoliticianId(politicianId, null, years);
    }

    private List<Expense> requestExpensesByCurrentLegislature(Long politicianId) {
        List<Integer> years = new ArrayList<>();
        Legislature legislature = camaraAPI.requestCurrentLegislature();
        int startYear = legislature.getStartDate().getYear();
        int legislatureDurationYears = 4;
        for (int i = 0; i < legislatureDurationYears; i++) {
            years.add(startYear + i);
        }
        return camaraAPI.requestAllExpensesByPoliticianId(politicianId, null, years);
    }

    private List<Expense> requestExpensesByLastMonths(Long politicianId, Integer lastMonths) {
        List<Expense> expenses = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (int i = 0; i < lastMonths; i++) {
            LocalDate ld = now.minusMonths(i);
            int month = ld.getMonthValue();
            int year = ld.getYear();
            expenses.addAll(camaraAPI.requestAllExpensesByPoliticianId(politicianId, Arrays.asList(month), Arrays.asList(year)));
        }
        return expenses;
    }

    public List<Profile> findProfilesByName(String name){
        return camaraAPI.requestProfilesByNameAndLegislatureId(name, camaraAPI.requestCurrentLegislature().getId());
    }
}
