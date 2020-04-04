package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.data.politician.Politician;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;
import java.io.InputStreamReader;

public class GetCurrentLegislature implements ResponseHandler<Long> {

    @Override
    public Long handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        validateResponse(httpResponse);
        return JsonParser.parseReader(new InputStreamReader(httpResponse.getEntity().getContent()))
                .getAsJsonObject()
                .get("dados")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("id")
                .getAsLong();
    }

    private void validateResponse(HttpResponse response) throws IOException{
        if(response.getStatusLine().getStatusCode() != 200){
            throw new IOException(String.format("HTTP request error. Status code: %d.", response.getStatusLine().getStatusCode()));
        }
    }
}
