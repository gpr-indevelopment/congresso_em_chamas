package com.devindev.congressoemchamas.externalapi.camara;

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
        String path = String.format("%s/deputados/%d", camaraConfig.getBaseUrl(), id);
        GetPoliticianById apiFunctionHandler = new GetPoliticianById();
        return Request.Get(path).execute().handleResponse(apiFunctionHandler);
    }

    private List<Long> retrievePoliticianIdsByName(String name) throws IOException {
        name = APIUtils.convertToQueryString(name);
        String path = String.format("%s/deputados?nome=%s&idLegislatura=%d", camaraConfig.getBaseUrl(), name, camaraConfig.getCurrentLegislatureId());
        GetPoliticiansByName apiFunctionHandler = new GetPoliticiansByName();
        return Request.Get(path).execute().handleResponse(apiFunctionHandler);
    }
}
