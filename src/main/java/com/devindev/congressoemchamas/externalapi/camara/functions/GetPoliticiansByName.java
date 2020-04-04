package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetPoliticiansByName implements ResponseHandler<List<Long>> {

    @Override
    public List<Long> handleResponse(HttpResponse response) throws IOException {
        validateResponse(response);
        List<Long> politicianIds = new ArrayList<>();
        JsonParser.parseReader(new InputStreamReader(response.getEntity().getContent()))
                .getAsJsonObject()
                .get("dados")
                .getAsJsonArray()
                .forEach(data -> politicianIds.add(data.getAsJsonObject().get("id").getAsLong()));
        return politicianIds;
    }

    private void validateResponse(HttpResponse response) throws IOException{
        if(response.getStatusLine().getStatusCode() != 200){
            throw new IOException(String.format("HTTP request error. Status code: %d.", response.getStatusLine().getStatusCode()));
        }
    }
}
