package com.devindev.congressoemchamas.externalapi.camara;

import com.devindev.congressoemchamas.politician.Politician;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CamaraAPI {

    @Autowired
    private CamaraResponseHandler responseHandler;

    private static final Logger LOGGER = LoggerFactory.getLogger(CamaraAPI.class);

    private String baseUrl = "https://dadosabertos.camara.leg.br/api/v2";

    public List<Politician> requestByName(String name){
        try{
            return executeGetDeputados(name);
        }
        catch (IOException statusCodeError){
            LOGGER.error(statusCodeError.getMessage());
            LOGGER.error("Returning empty politicians list.");
            return new ArrayList<>();
        }
        catch (Exception exception){
            LOGGER.error(exception.getMessage());
            LOGGER.error("Returning empty politicians list.");
            return new ArrayList<>();
        }
    }

    private List<Politician> executeGetDeputados(String name) throws IOException {
        String path = String.format("%s/sssdeputados?nome=%s", baseUrl, name);
        return Request.Get(path).execute().handleResponse(responseHandler);
    }
}
