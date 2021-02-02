package com.gprindevelopment.cec.core.externalapi.camara.functions;

import com.google.gson.JsonObject;
import com.gprindevelopment.cec.core.proposition.Processing;
import com.gprindevelopment.cec.core.restclient.CongressoResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GetProcessingHistoryByPropisitionId extends CongressoResponseHandler<List<Processing>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetProcessingHistoryByPropisitionId.class);

    @Override
    protected List<Processing> handleResponse(JsonObject jsonObject) {
        List<Processing> processings = new ArrayList<>();
        jsonObject.get("dados").getAsJsonArray().forEach(dataElement -> {
            JsonObject processingResponse = dataElement.getAsJsonObject();
            Processing processing = new Processing();
            processing.setDescription(processingResponse.get("despacho").getAsString());
            processing.setTitle(processingResponse.get("descricaoTramitacao").getAsString());
            processing.setEntityInitials(processingResponse.get("siglaOrgao").getAsString());
            processing.setSequence(processingResponse.get("sequencia").getAsInt());
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Timestamp timestamp = new Timestamp(dateFormat.parse(processingResponse.get("dataHora").getAsString()).getTime());
                processing.setTimestamp(timestamp);
            } catch (ParseException e) {
                LOGGER.error(e.getMessage());
                LOGGER.error("Unable to parse the date and time of a processing object. Processing returning with a null date.");
            }
            processings.add(processing);
        });
        return processings;
    }
}
