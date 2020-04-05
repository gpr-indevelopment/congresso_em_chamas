package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GetPropositionById extends CongressoResponseHandler<Proposition> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetPropositionById.class);

    @Override
    protected Proposition handleResponse(JsonObject jsonObject) {
        Proposition proposition = new Proposition();
        JsonObject propositionResponse = jsonObject.get("dados").getAsJsonObject();
        proposition.setTypeDescription(propositionResponse.get("descricaoTipo").getAsString());
        proposition.setLink(propositionResponse.get("urlInteiroTeor").getAsString());
        proposition.setTitle(StringUtils.abbreviate(propositionResponse.get("ementa").getAsString(), 255));
        proposition.setId(propositionResponse.get("id").getAsLong());
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Timestamp timestamp = new Timestamp(dateFormat.parse(propositionResponse.get("dataApresentacao").getAsString()).getTime());
            proposition.setPresentationTimestamp(timestamp);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unable to parse the presentation date of a proposition. Proposition returning with a null presentation date.");
        }
        return proposition;
    }
}
