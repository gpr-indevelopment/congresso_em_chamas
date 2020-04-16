package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class GetPoliticianIdsByName extends CongressoResponseHandler<List<Long>> {

    @Override
    protected List<Long> handleResponse(JsonObject jsonObject) {
        List<Long> politicianIds = new ArrayList<>();
        jsonObject.get("dados")
                .getAsJsonArray()
                .forEach(data -> politicianIds.add(data.getAsJsonObject().get("id").getAsLong()));
        return politicianIds;
    }
}
