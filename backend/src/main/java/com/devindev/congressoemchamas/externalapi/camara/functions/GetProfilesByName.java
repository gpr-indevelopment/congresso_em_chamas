package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class GetProfilesByName extends CongressoResponseHandler<List<Profile>> {

    @Override
    protected List<Profile> handleResponse(JsonObject jsonObject) {
        List<Profile> profiles = new ArrayList<>();
        jsonObject.get("dados")
                .getAsJsonArray()
                .forEach(data -> {
                    Profile profile = new Profile();
                    JsonObject internalObject = data.getAsJsonObject();
                    profile.setId(internalObject.get("id").getAsLong());
                    profile.setName(internalObject.get("nome").getAsString());
                    profile.setParty(internalObject.get("siglaPartido").getAsString());
                    profile.setLegislatureId(internalObject.get("idLegislatura").getAsLong());
                    profile.setPicture(internalObject.get("urlFoto").getAsString());
                    profiles.add(profile);
                });
        return profiles;
    }
}
