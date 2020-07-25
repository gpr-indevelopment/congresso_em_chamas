package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class GetAuthorsByPropositionId extends CongressoResponseHandler<List<String>> {


    @Override
    protected List<String> handleResponse(JsonObject jsonObject) {
        List<String> authors = new ArrayList<>();
        jsonObject.get("dados").getAsJsonArray().forEach(dataElement -> {
            authors.add(dataElement.getAsJsonObject().get("nome").getAsString());
        });
        return authors;
    }
}
