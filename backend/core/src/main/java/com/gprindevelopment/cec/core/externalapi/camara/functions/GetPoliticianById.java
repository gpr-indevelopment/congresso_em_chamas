package com.gprindevelopment.cec.core.externalapi.camara.functions;

import com.google.gson.JsonObject;
import com.gprindevelopment.cec.core.politician.Politician;
import com.gprindevelopment.cec.core.restclient.CongressoResponseHandler;

import java.sql.Timestamp;

public class GetPoliticianById extends CongressoResponseHandler<Politician> {

    @Override
    protected Politician handleResponse(JsonObject jsonObject) {
        Politician politician = buildPolitician(jsonObject.get("dados")
                .getAsJsonObject()
                .get("ultimoStatus")
                .getAsJsonObject());
        politician.setSchooling(nullCheckRetrievedStringValue(jsonObject.get("dados").getAsJsonObject().get("escolaridade")));
        return politician;
    }

    private Politician buildPolitician(JsonObject data) {
        Politician politician = new Politician();
        politician.setId(data.get("id").getAsLong());
        politician.setLegislatureId(data.get("idLegislatura").getAsLong());
        politician.setName(nullCheckRetrievedStringValue(data.get("nome")));
        politician.setParty(nullCheckRetrievedStringValue(data.get("siglaPartido")));
        politician.setPicture(nullCheckRetrievedStringValue(data.get("urlFoto")));
        politician.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return politician;
    }
}
