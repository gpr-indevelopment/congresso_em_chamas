package com.gprindevelopment.cec.core.externalapi.camara.functions;

import com.google.gson.JsonObject;
import com.gprindevelopment.cec.core.externalapi.camara.Legislature;
import com.gprindevelopment.cec.core.restclient.CongressoResponseHandler;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class GetCurrentLegislature extends CongressoResponseHandler<Legislature> {

    @Override
    protected Legislature handleResponse(JsonObject jsonObject) {
        return buildLegislature(jsonObject.get("dados")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject());
    }

    private Legislature buildLegislature(JsonObject jsonObject){
        Legislature legislature = new Legislature();
        legislature.setId(jsonObject.get("id").getAsLong());
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        legislature.setStartDate(YearMonth.parse(jsonObject.get("dataInicio").getAsString(), dateFormat));
        legislature.setEndDate(YearMonth.parse(jsonObject.get("dataFim").getAsString(), dateFormat));
        return legislature;
    }
}
