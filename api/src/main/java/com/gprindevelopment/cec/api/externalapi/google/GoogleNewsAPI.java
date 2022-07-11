package com.gprindevelopment.cec.api.externalapi.google;

import com.gprindevelopment.cec.api.externalapi.google.functions.GetNewsByKeyword;
import com.gprindevelopment.cec.api.politician.News;
import com.gprindevelopment.cec.api.restclient.RequestsSender;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleNewsAPI {

    @Autowired
    private GoogleNewsConfig googleNewsConfig;

    @Autowired
    private RequestsSender requestsSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleNewsAPI.class);

    public List<News> requestNews(String keyword){
        List<News> result = new ArrayList<>();
        if (googleNewsConfig.isGoogleNewsEnabled()) {
            return callGoogleNewsApi(keyword);
        } else {
            LOGGER.warn("Google news API is not enabled. Please check the environment config.");
        }
        return result;
    }

    private List<News> callGoogleNewsApi(String keyword) {
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("https").setHost(googleNewsConfig.getBaseUrl())
                    .addParameter("apiKey", googleNewsConfig.getApiKey())
                    .addParameter("language", "pt")
                    .addParameter("q", "Deputado " + keyword)
                    .addParameter("sortBy", "publishedAt");
            GetNewsByKeyword apiFunctionHandler = new GetNewsByKeyword();
            return requestsSender.sendRequest(builder.build(), apiFunctionHandler);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("An error occurred on requesting news from Google News API. Returning an empty news list.");
            return new ArrayList<>();
        }
    }
}
