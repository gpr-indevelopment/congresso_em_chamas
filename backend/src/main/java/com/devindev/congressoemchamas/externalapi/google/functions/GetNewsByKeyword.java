package com.devindev.congressoemchamas.externalapi.google.functions;

import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetNewsByKeyword extends CongressoResponseHandler<List<News>> {

    @Override
    protected List<News> handleResponse(JsonObject jsonObject) {
        List<News> news = new ArrayList<>();
        Set<String> titles = new HashSet<>();
        jsonObject.get("articles").getAsJsonArray().forEach(articleJsonObject -> {
            News newsPiece = buildNews(articleJsonObject.getAsJsonObject());
            if(titles.add(newsPiece.getTitle())){
                news.add(newsPiece);
            }
        });
        return news;
    }

    private News buildNews(JsonObject jsonObject){
        News newsPiece = new News();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime publishedDate = LocalDateTime.parse(StringUtils.substring(jsonObject.get("publishedAt").getAsString(), 0, 19), inputFormatter);
        newsPiece.setPublishedDate(Timestamp.valueOf(publishedDate));
        newsPiece.setTitle(nullCheckRetrievedStringValue(jsonObject.get("title")));
        newsPiece.setLink(nullCheckRetrievedStringValue(jsonObject.get("url")));
        newsPiece.setImageLink(nullCheckRetrievedStringValue(jsonObject.get("urlToImage")));
        newsPiece.setDescription(nullCheckRetrievedStringValue(jsonObject.get("description")));
        newsPiece.setSourceName(nullCheckRetrievedStringValue(jsonObject.get("source").getAsJsonObject().get("name")));
        return newsPiece;
    }
}
