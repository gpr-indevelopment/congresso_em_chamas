package com.devindev.congressoemchamas.externalapi.google;

import com.devindev.congressoemchamas.externalapi.google.functions.GetNewsByPoliticianName;
import com.devindev.congressoemchamas.externalapi.utils.APIUtils;
import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.politician.Politician;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleSearchAPI {

    @Autowired
    private GoogleConfig googleConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleSearchAPI.class);

    // TODO: 18-Mar-20 Take a look at this try catch. Its empty. 
    public List<News> searchNewsByPolitician(Politician politician) {
        try {
            String url = String.format("%s&q=%s", googleConfig.getURL(), APIUtils.convertToQueryString(politician.getName()));
            GetNewsByPoliticianName apiFunctionHandler = new GetNewsByPoliticianName();
            List<News> foundNews = Request.Get(url).execute().handleResponse(apiFunctionHandler);
            foundNews.forEach(news -> news.setPolitician(politician));
            return foundNews;
        } catch (IOException exception) {
            exception.printStackTrace();
            LOGGER.error(exception.getMessage());
            LOGGER.error("Returning empty list of news.");
            return new ArrayList<>();
        }
    }
}
