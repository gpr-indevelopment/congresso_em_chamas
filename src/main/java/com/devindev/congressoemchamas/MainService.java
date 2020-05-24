package com.devindev.congressoemchamas;

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

    public List<MonthlyExpense> findByLegislature(Long politicianId, Long legislatureId) {
        return politiciansRepository.findExpensesByPoliticianAndLegislature(politicianId, legislatureId);
    }

    public List<MonthlyExpense> findMonthlyExpensesByPoliticianId(Long politicianId, List<Integer> months, List<Integer> years) {
        return MonthlyExpense.build(camaraAPI.requestAllExpensesByPoliticianId(politicianId, months, years));
    }

    public Legislature getCurrentLegislature(){
        return camaraAPI.requestCurrentLegislatureId();
    }

    public List<Profile> findProfilesByName(String name){
        return camaraAPI.requestProfilesByNameAndLegislatureId(name, camaraAPI.requestCurrentLegislatureId().getId());
    }
}
