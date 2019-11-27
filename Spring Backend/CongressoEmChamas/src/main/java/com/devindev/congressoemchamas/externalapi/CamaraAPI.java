package com.devindev.congressoemchamas.externalapi;

import com.devindev.congressoemchamas.politician.Politician;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Component
public class CamaraAPI {

    private String baseUrl = "https://dadosabertos.camara.leg.br/api/v2";

    // TODO: 27/11/2019 Improve error handling with try catches. Let the error surface. Dont cover it. 
    public List<Politician> requestByName(String name){
        Content response = executeGetDeputados(name);
        if(Objects.nonNull(response)){
            return buildPoliticians(response);
        }
        return null;
    }

    private Content executeGetDeputados(String name){
        String path = String.format("%s/deputados?nome=%s", baseUrl, name);
        Content response = null;
        try {
            response = Request.Get(path).execute().returnContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private List<Politician> buildPoliticians(Content response){
        List<Politician> politicians = new LinkedList<>();
        JsonObject treeObject = JsonParser.parseString(response.asString()).getAsJsonObject();
        treeObject.get("dados").getAsJsonArray().forEach(data -> {
            Politician politician = new Politician();
            JsonObject dataObject = data.getAsJsonObject();
            politician.setCamaraId(dataObject.get("id").getAsLong());
            politician.setName(retrieveStringFromMember(dataObject, "nome"));
            politician.setParty(retrieveStringFromMember(dataObject, "siglaPartido"));
            politician.setPicture(retrieveStringFromMember(dataObject, "urlFoto"));
            politicians.add(politician);
        });
        return politicians;
    }

    private String retrieveStringFromMember(JsonObject dataObject, String memberName){
        if(!dataObject.get(memberName).isJsonNull()){
            return dataObject.get(memberName).getAsString();
        }
        return null;
    }
}
