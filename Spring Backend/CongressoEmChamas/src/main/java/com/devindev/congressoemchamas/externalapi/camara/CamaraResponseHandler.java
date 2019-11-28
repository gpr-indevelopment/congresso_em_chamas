package com.devindev.congressoemchamas.externalapi.camara;

import com.devindev.congressoemchamas.politician.Politician;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CamaraResponseHandler implements ResponseHandler<List<Politician>> {

    @Override
    public List<Politician> handleResponse(HttpResponse response) throws IOException {
        validateResponse(response);
        List<Politician> politicians = new ArrayList<>();
        JsonParser.parseReader(new InputStreamReader(response.getEntity().getContent()))
                .getAsJsonObject()
                .get("dados")
                .getAsJsonArray()
                .forEach(data -> {
                    politicians.add(buildPolitician(data.getAsJsonObject()));
                });
        return politicians;
    }

    private void validateResponse(HttpResponse response) throws IOException{
        if(response.getStatusLine().getStatusCode() != 200){
            throw new IOException(String.format("HTTP request error. Status code: %d.", response.getStatusLine().getStatusCode()));
        }
    }

    private Politician buildPolitician(JsonObject data) {
            Politician politician = new Politician();
            politician.setCamaraId(data.get("id").getAsLong());
            politician.setName(retrieveStringFromMember(data, "nome"));
            politician.setParty(retrieveStringFromMember(data, "siglaPartido"));
            politician.setPicture(retrieveStringFromMember(data, "urlFoto"));
        return politician;
    }

    private String retrieveStringFromMember(JsonObject dataObject, String memberName) {
        if (!dataObject.get(memberName).isJsonNull()) {
            return dataObject.get(memberName).getAsString();
        }
        return null;
    }
}
