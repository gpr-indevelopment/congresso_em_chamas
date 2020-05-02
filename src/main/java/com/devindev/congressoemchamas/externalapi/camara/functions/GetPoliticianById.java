package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
import com.google.gson.JsonObject;

public class GetPoliticianById extends CongressoResponseHandler<Politician> {

    @Override
    protected Politician handleResponse(JsonObject jsonObject) {
        Politician politician = buildPolitician(jsonObject.get("dados")
                .getAsJsonObject()
                .get("ultimoStatus")
                .getAsJsonObject());
        politician.setSchooling(jsonObject.get("dados").getAsJsonObject().get("escolaridade").getAsString());
        return politician;
    }

    private Politician buildPolitician(JsonObject data) {
        Politician politician = new Politician();
        politician.setId(data.get("id").getAsLong());
        politician.setLegislatureId(data.get("idLegislatura").getAsLong());
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
