package com.devindev.congressoemchamas.externalapi.google;

import com.devindev.congressoemchamas.externalapi.utils.APIUtils;
import com.devindev.congressoemchamas.politician.Politician;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoogleSearchAPI {

    @Autowired
    private GoogleConfig googleConfig;

    @Autowired
    private GetNewsByName getNewsByName;

    // TODO: 18-Mar-20 Take a look at this try catch. Its empty. 
    public void getNews(Politician politician) {
        try {
            String url = String.format("%s&q=%s", googleConfig.getURL(), APIUtils.convertToQueryString(politician.getName()));
            politician.setNews(Request.Get(url).execute().handleResponse(getNewsByName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
