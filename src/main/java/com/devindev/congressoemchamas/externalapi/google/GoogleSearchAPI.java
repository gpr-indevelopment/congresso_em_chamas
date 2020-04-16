package com.devindev.congressoemchamas.externalapi.google;

import com.devindev.congressoemchamas.externalapi.google.functions.GetNewsByPoliticianName;
import com.devindev.congressoemchamas.data.news.News;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
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
    public List<News> searchNewsByPoliticianName(String politicianName) {
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("https").setHost(googleConfig.getUrl())
                    .addParameter("key", googleConfig.getApiKey())
                    .addParameter("cx", googleConfig.getEngineId())
                    .addParameter("sort", "date")
                    .addParameter("q", politicianName);
            GetNewsByPoliticianName apiFunctionHandler = new GetNewsByPoliticianName();
            return Request.Get(builder.build()).execute().handleResponse(apiFunctionHandler);
        } catch (Exception exception) {
            exception.printStackTrace();
            LOGGER.error(exception.getMessage());
            LOGGER.error("Returning empty list of news.");
            return new ArrayList<>();
        }
    }
}
