package com.gprindevelopment.cec.core.externalapi.camara.functions;

import com.google.gson.JsonObject;
import com.gprindevelopment.cec.core.restclient.CongressoResponseHandler;

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
