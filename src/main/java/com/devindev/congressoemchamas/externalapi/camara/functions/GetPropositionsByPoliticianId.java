package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetPropositionsByPoliticianId implements ResponseHandler<List<Proposition>> {

    @Override
    public List<Proposition> handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        validateResponse(httpResponse);
        List<Proposition> propositions = new ArrayList<>();
        JsonParser.parseReader(new InputStreamReader(httpResponse.getEntity().getContent()))
                .getAsJsonObject()
                .get("dados")
                .getAsJsonArray().forEach(dataElement -> {
                    propositions.add(buildProposition(dataElement.getAsJsonObject()));
                });
        return propositions;
    }

    private Proposition buildProposition(JsonObject data){
        Proposition proposition = new Proposition();
        proposition.setId(data.get("id").getAsLong());
        proposition.setTitle(StringUtils.abbreviate(data.get("ementa").getAsString(), 255));
        proposition.setPropositionType(data.get("siglaTipo").getAsString());
        return proposition;
    }

    private void validateResponse(HttpResponse response) throws IOException{
        if(response.getStatusLine().getStatusCode() != 200){
            throw new IOException(String.format("HTTP request error. Status code: %d.", response.getStatusLine().getStatusCode()));
        }
    }
}
