package com.devindev.congressoemchamas.externalapi.google.functions;

import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.news.NewsMediaOutlet;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GetNewsByPoliticianName extends CongressoResponseHandler<List<News>> {

    private final String DATE_SEPARATOR_REGEX = "^(.+\\.\\.\\.\\s)";

    @Override
    protected List<News> handleResponse(JsonObject jsonObject) {
        List<News> news = new ArrayList<>();
        jsonObject.get("items")
                .getAsJsonArray()
                .forEach(arrayElement -> news.add(buildNews(arrayElement.getAsJsonObject())));
        return news;
    }

    private News buildNews(JsonObject jsonObject){
        News news = new News();
        news.setTitle(jsonObject.get("title").getAsString());
        news.setLink(jsonObject.get("link").getAsString());
        news.setDateString(StringUtils.capitalize(jsonObject.get("htmlSnippet").getAsString().split("<b>")[0]));
        news.setDescription(StringUtils.normalizeSpace(jsonObject.get("snippet").getAsString()).split(DATE_SEPARATOR_REGEX)[1]);
        news.setMediaOutlet(NewsMediaOutlet.fromUrl(jsonObject.get("displayLink").getAsString()));
        news.setRequestTimestamp(new Timestamp(System.currentTimeMillis()));
        return news;
    }
}