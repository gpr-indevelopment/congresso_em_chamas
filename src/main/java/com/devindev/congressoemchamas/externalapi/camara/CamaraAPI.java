package com.devindev.congressoemchamas.externalapi.camara;

import com.devindev.congressoemchamas.externalapi.utils.APIUtils;
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
    private GetPoliticiansByNameRH getPoliticiansByNameRH;

    @Autowired
    private GetPoliticianByIdRH getPoliticianByIdRH;

    private static final Logger LOGGER = LoggerFactory.getLogger(CamaraAPI.class);

    private String baseUrl = "https://dadosabertos.camara.leg.br/api/v2";

    public List<Long> requestIdsByName(String name) {
        try {
            return retrievePoliticianIdsByName(name);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Returning empty politicians list.");
            return new ArrayList<>();
        }
    }

    public Politician requestPoliticianById(Long id){
        try {
            return retrievePoliticianById(id);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Returning null instead of a new Politician.");
            return null;
        }
    }

    private Politician retrievePoliticianById(Long id) throws IOException{
        String path = String.format("%s/deputados/%d", baseUrl, id);
        return Request.Get(path).execute().handleResponse(getPoliticianByIdRH);
    }

    private List<Long> retrievePoliticianIdsByName(String name) throws IOException {
        name = APIUtils.convertToQueryString(name);
        String path = String.format("%s/deputados?nome=%s", baseUrl, name);
        return Request.Get(path).execute().handleResponse(getPoliticiansByNameRH);
    }
}
