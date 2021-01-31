package com.gprindevelopment.cec.core.externalapi.camara.functions;

import com.google.gson.JsonObject;
import com.gprindevelopment.cec.core.restclient.CongressoResponseHandler;

import java.util.ArrayList;
import java.util.List;

public class GetPropositionsIdsByPoliticianId extends CongressoResponseHandler<List<Long>> {

    @Override
    protected List<Long> handleResponse(JsonObject jsonObject) {
        List<Long> propositionIds = new ArrayList<>();
        jsonObject.get("dados")
                .getAsJsonArray()
                .forEach(dataElement -> propositionIds.add(dataElement.getAsJsonObject().get("id").getAsLong()));
        return propositionIds;
    }
}
