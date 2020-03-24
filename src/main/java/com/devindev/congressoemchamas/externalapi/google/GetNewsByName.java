package com.devindev.congressoemchamas.externalapi.google;

import com.devindev.congressoemchamas.externalapi.utils.APIUtils;
import com.devindev.congressoemchamas.politician.News;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetNewsByName implements ResponseHandler<List<News>> {

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
            String description = APIUtils.convertLineJumpsToSpace(jsonObject.get("snippet").getAsString());
            news.add(new News(title, link, description));
        }
        return news;
    }

    private void validateResponse(HttpResponse response) throws IOException{
        if(response.getStatusLine().getStatusCode() != 200){
            throw new IOException(String.format("HTTP request error. Status code: %d.", response.getStatusLine().getStatusCode()));
        }
    }
}
