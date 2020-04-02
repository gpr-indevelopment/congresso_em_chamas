package com.devindev.congressoemchamas.externalapi.google;

import com.devindev.congressoemchamas.externalapi.utils.APIUtils;
import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.news.NewsMediaOutlet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetNewsByName implements ResponseHandler<List<News>> {

    private final String DATE_SEPARATOR_REGEX = "^(.+\\.\\.\\.\\s)";

    @Override
    public List<News> handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        validateResponse(response);
        List<News> news = new ArrayList<>();
        JsonArray dataArray = JsonParser.parseReader(new InputStreamReader(response.getEntity().getContent()))
                .getAsJsonObject()
                .get("items")
                .getAsJsonArray();
        for (int i = 0; i < dataArray.size(); i++) {
            JsonObject jsonObject = dataArray.get(i).getAsJsonObject();
            String title = jsonObject.get("title").getAsString();
            String link = jsonObject.get("link").getAsString();
            String dateString = StringUtils.capitalize(jsonObject.get("htmlSnippet").getAsString().split("<b>")[0]);
            String description = APIUtils.convertLineJumpsToSpace(jsonObject.get("snippet").getAsString()).split(DATE_SEPARATOR_REGEX)[1];
            NewsMediaOutlet newsMediaOutlet = NewsMediaOutlet.fromUrl(jsonObject.get("displayLink").getAsString());
            news.add(new News(title, link, description, dateString, newsMediaOutlet));
        }
        return news;
    }

    private void validateResponse(HttpResponse response) throws IOException{
        if(response.getStatusLine().getStatusCode() != 200){
            throw new IOException(String.format("HTTP request error. Status code: %d.", response.getStatusLine().getStatusCode()));
        }
    }
}