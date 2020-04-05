package com.devindev.congressoemchamas.externalapi;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;
import java.io.InputStreamReader;

public abstract class CongressoResponseHandler<T> implements ResponseHandler<T> {

    @Override
    public T handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        validateResponse(httpResponse);
        return handleResponse(JsonParser.parseReader(new InputStreamReader(httpResponse.getEntity().getContent())).getAsJsonObject());
    }

    // TODO: 4/4/2020 Add a description for each status code.
    private void validateResponse(HttpResponse response) throws IOException{
        if(response.getStatusLine().getStatusCode() != 200){
            throw new IOException(String.format("HTTP request error. Status code: %d.", response.getStatusLine().getStatusCode()));
        }
    }

    protected abstract T handleResponse(JsonObject jsonObject);
}
