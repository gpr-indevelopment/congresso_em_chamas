package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

public class GetPropositionById extends CongressoResponseHandler<Proposition> {

    @Override
    protected Proposition handleResponse(JsonObject jsonObject) {
        Proposition proposition = new Proposition();
        JsonObject propositionResponse = jsonObject.get("dados").getAsJsonObject();
        proposition.setPropositionType(propositionResponse.get("siglaTipo").getAsString());
        proposition.setTitle(StringUtils.abbreviate(propositionResponse.get("ementa").getAsString(), 255));
        proposition.setId(propositionResponse.get("id").getAsLong());
        return proposition;
    }
}
