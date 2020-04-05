package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
import com.google.gson.JsonObject;

public class GetCurrentLegislature extends CongressoResponseHandler<Long> {

    @Override
    protected Long handleResponse(JsonObject jsonObject) {
        return jsonObject.get("dados")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("id")
                .getAsLong();
    }
}
