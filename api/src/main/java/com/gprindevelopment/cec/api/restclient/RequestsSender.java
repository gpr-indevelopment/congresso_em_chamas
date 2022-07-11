package com.gprindevelopment.cec.api.restclient;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
public class RequestsSender {

    public <T> T sendRequest(URI target, ResponseHandler<T> handler) throws IOException {
        return Request.Get(target).execute().handleResponse(handler);
    }
}
