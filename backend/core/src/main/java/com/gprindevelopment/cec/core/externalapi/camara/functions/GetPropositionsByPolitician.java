package com.gprindevelopment.cec.core.externalapi.camara.functions;

import com.google.gson.JsonObject;
import com.gprindevelopment.cec.core.proposition.Proposition;
import com.gprindevelopment.cec.core.restclient.CongressoResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GetPropositionsByPolitician extends CongressoResponseHandler<Proposition> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetPropositionsByPolitician.class);

    @Override
    protected Proposition handleResponse(JsonObject jsonObject) {
        Proposition proposition = new Proposition();
        JsonObject propositionResponse = jsonObject.get("dados").getAsJsonObject();
        proposition.setTypeDescription(nullCheckRetrievedStringValue(propositionResponse.get("descricaoTipo")));
        proposition.setLink(nullCheckRetrievedStringValue(propositionResponse.get("urlInteiroTeor")));
        proposition.setTitle(nullCheckRetrievedStringValue(propositionResponse.get("ementa")));
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
