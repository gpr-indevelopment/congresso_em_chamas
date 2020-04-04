package com.devindev.congressoemchamas.service;

import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.news.NewsRepository;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.politician.PoliticianRepository;
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

    public List<Politician> findByName(String name){
        List<Politician> returnPoliticians = new ArrayList<>();
        camaraAPI.requestIdsByName(name, getCurrentLegislatureId()).parallelStream().forEach(camaraPoliticianId -> {
            Politician builtPolitician = repository.findById(camaraPoliticianId);
            if(Objects.isNull(builtPolitician)){
                builtPolitician = buildNewPoliticianAndSave(camaraPoliticianId);
            }
            else if(anyNewsExpired(builtPolitician)) {
                builtPolitician = updateNewsAndSave(builtPolitician);
            }
            returnPoliticians.add(builtPolitician);
        });
        Collections.sort(returnPoliticians);
        return returnPoliticians;
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
        politician.setNews(googleSearchAPI.searchNews(politician));
        newsIdsToRemove.forEach(newsId -> newsRepository.delete(newsId));
        return repository.save(politician);
    }

    private Politician buildNewPoliticianAndSave(Long camaraPoliticianId){
        Politician politician = camaraAPI.requestPoliticianById(camaraPoliticianId);
        politician.setTwitterUsername(twitterAPI.searchTwitterUsername(politician));
        politician.setNews(googleSearchAPI.searchNews(politician));
        return  repository.save(politician);
    }
}
