package com.devindev.congressoemchamas.service;

import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.news.NewsRepository;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.politician.PoliticianRepository;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import com.devindev.congressoemchamas.externalapi.google.GoogleSearchAPI;
import com.devindev.congressoemchamas.externalapi.twitter.TwitterAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PoliticianService {

    @Autowired
    private PoliticianRepository repository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CamaraAPI camaraAPI;

    @Autowired
    private TwitterAPI twitterAPI;

    @Autowired
    private GoogleSearchAPI googleSearchAPI;

    @Value("${congresso.news.cache-expiration-minutes}")
    private Integer cacheExpirationMinutes;

    private Long currentLegislatureId;

    public List<Long> findIdsByName(String name){
        return camaraAPI.requestIdsByNameAndLegislatureId(name, getCurrentLegislatureId());
    }

    public String findTwitterUsername(Long politicianId){
        Politician politician = camaraAPI.requestPoliticianById(politicianId);
        return twitterAPI.searchTwitterUsername(politician);
    }

    public List<Proposition> findPropositions(Long politicianId){
        return retrievePropositions(politicianId);
    }

    public List<News> findNews(Long politicianId){
        Politician politician = camaraAPI.requestPoliticianById(politicianId);
        return googleSearchAPI.searchNewsByPolitician(politician);
    }

    public List<Politician> findByName(String name){
        List<Politician> returnPoliticians = new ArrayList<>();
        camaraAPI.requestIdsByNameAndLegislatureId(name, getCurrentLegislatureId()).parallelStream().forEach(camaraPoliticianId -> {
            Politician builtPolitician = repository.findById(camaraPoliticianId);
            if(Objects.isNull(builtPolitician)){
                builtPolitician = buildNewPoliticianAndSave(camaraPoliticianId);
            }
            else {
                if(anyNewsExpired(builtPolitician)) {
                    builtPolitician = updateNewsAndSave(builtPolitician);
                }
                builtPolitician.getPropositions().addAll(retrievePropositions(builtPolitician.getId()));
            }
            returnPoliticians.add(builtPolitician);
        });
        Collections.sort(returnPoliticians);
        return returnPoliticians;
    }

    private List<Proposition> retrievePropositions(Long politicianId){
        List<Long> propositionIds = camaraAPI.retrievePropositionIdsByPolitician(politicianId);
        List<Proposition> propositions = new ArrayList<>();
        propositionIds.forEach(propositionId -> {
            Proposition proposition = camaraAPI.retrievePropositionFromId(propositionId);
            proposition.setAuthors(camaraAPI.retrieveAuthorsFromProposition(proposition));
            proposition.setProcessingHistory(camaraAPI.retrieveProcessingHistoryFromProposition(proposition));
            Collections.sort(proposition.getProcessingHistory());
            propositions.add(proposition);
        });
        return propositions;
    }

    private Long getCurrentLegislatureId(){
        if(Objects.isNull(currentLegislatureId)){
            currentLegislatureId = camaraAPI.requestCurrentLegislatureId();
            return currentLegislatureId;
        }
        else{
            return currentLegislatureId;
        }
    }

    private boolean anyNewsExpired(Politician politician){
        List<News> politicianNews = politician.getNews();
        return Objects.isNull(politicianNews) || politicianNews.isEmpty() || politicianNews.stream().anyMatch(news -> {
            Calendar now = Calendar.getInstance();
            now.add(Calendar.MINUTE, - cacheExpirationMinutes);
            return news.getTimestamp().before(now.getTime());
        });
    }

    private Politician updateNewsAndSave(Politician politician){
        List<Long> newsIdsToRemove = new ArrayList<>();
        politician.getNews().forEach(news -> newsIdsToRemove.add(news.getId()));
        politician.setNews(googleSearchAPI.searchNewsByPolitician(politician));
        newsIdsToRemove.forEach(newsId -> newsRepository.delete(newsId));
        return repository.save(politician);
    }

    private Politician buildNewPoliticianAndSave(Long camaraPoliticianId){
        Politician politician = camaraAPI.requestPoliticianById(camaraPoliticianId);
        politician.setTwitterUsername(twitterAPI.searchTwitterUsername(politician));
        politician.setNews(googleSearchAPI.searchNewsByPolitician(politician));
        politician = repository.save(politician);
        politician.getPropositions().addAll(retrievePropositions(politician.getId()));
        return politician;
    }
}
