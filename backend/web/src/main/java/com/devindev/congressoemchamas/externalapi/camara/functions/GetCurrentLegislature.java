package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class GetCurrentLegislature extends CongressoResponseHandler<Legislature> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetCurrentLegislature.class);

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
