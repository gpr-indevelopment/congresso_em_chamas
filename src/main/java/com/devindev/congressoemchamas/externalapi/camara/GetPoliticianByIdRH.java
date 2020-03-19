package com.devindev.congressoemchamas.externalapi.camara;

import com.devindev.congressoemchamas.politician.Politician;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStreamReader;

// TODO: 18-Mar-20 Put the validateResponse on an abstract class to make it obligatory
@Component
public class GetPoliticianByIdRH implements ResponseHandler<Politician> {

    @Override
    public Politician handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        validateResponse(response);
        return buildPolitician(JsonParser.parseReader(new InputStreamReader(response.getEntity().getContent()))
                .getAsJsonObject()
                .get("dados")
                .getAsJsonObject()
                .get("ultimoStatus")
                .getAsJsonObject());
    }

    private Politician buildPolitician(JsonObject data) {
        Politician politician = new Politician();
        politician.setId(data.get("id").getAsLong());
        politician.setName(retrieveStringFromMember(data, "nome"));
        politician.setParty(retrieveStringFromMember(data, "siglaPartido"));
        politician.setPicture(retrieveStringFromMember(data, "urlFoto"));
        return politician;
    }

    private String retrieveStringFromMember(JsonObject dataObject, String memberName) {
        if (!dataObject.get(memberName).isJsonNull()) {
            return dataObject.get(memberName).getAsString();
        }
        return null;
    }

    private void validateResponse(HttpResponse response) throws IOException{
        if(response.getStatusLine().getStatusCode() != 200){
            throw new IOException(String.format("HTTP request error. Status code: %d.", response.getStatusLine().getStatusCode()));
        }
    }
}
