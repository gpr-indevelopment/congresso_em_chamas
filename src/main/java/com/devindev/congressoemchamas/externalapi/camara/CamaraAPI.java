package com.devindev.congressoemchamas.externalapi.camara;

import com.devindev.congressoemchamas.externalapi.camara.functions.GetCurrentLegislature;
import com.devindev.congressoemchamas.externalapi.camara.functions.GetPoliticianById;
import com.devindev.congressoemchamas.externalapi.camara.functions.GetPoliticiansByName;
import com.devindev.congressoemchamas.externalapi.utils.APIUtils;
import com.devindev.congressoemchamas.data.politician.Politician;
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
    private CamaraConfig camaraConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(CamaraAPI.class);

    public List<Long> requestIdsByName(String name, Long legislatureId) {
        try {
            return retrievePoliticianIdsByName(name, legislatureId);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve ids by name on CamaraAPI.");
            LOGGER.error("Returning empty politicians list.");
            return new ArrayList<>();
        }
    }

    public Politician requestPoliticianById(Long id){
        try {
            return retrievePoliticianById(id);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve politician by id on CamaraAPI.");
            LOGGER.error("Returning null instead of a new Politician.");
            return null;
        }
    }

    public Long requestCurrentLegislatureId(){
        try {
            String path = String.format("%s/legislaturas?ordem=DESC&ordenarPor=id", camaraConfig.getBaseUrl());
            GetCurrentLegislature apiFunctionHandler = new GetCurrentLegislature();
            return Request.Get(path).execute().handleResponse(apiFunctionHandler);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve the current legislature ID from CamaraAPI.");
            LOGGER.error("Returning null instead of the current legislature ID.");
            return null;
        }
    }

    private Politician retrievePoliticianById(Long id) throws IOException{
        String path = String.format("%s/deputados/%d", camaraConfig.getBaseUrl(), id);
        GetPoliticianById apiFunctionHandler = new GetPoliticianById();
        return Request.Get(path).execute().handleResponse(apiFunctionHandler);
    }

    private List<Long> retrievePoliticianIdsByName(String name, Long legislatureId) throws IOException {
        name = APIUtils.convertToQueryString(name);
        String path = String.format("%s/deputados?nome=%s&idLegislatura=%d", camaraConfig.getBaseUrl(), name, legislatureId);
        GetPoliticiansByName apiFunctionHandler = new GetPoliticiansByName();
        return Request.Get(path).execute().handleResponse(apiFunctionHandler);
    }
}
