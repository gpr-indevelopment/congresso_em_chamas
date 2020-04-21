package com.devindev.congressoemchamas.externalapi.camara;

import com.devindev.congressoemchamas.data.processing.Processing;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.externalapi.camara.functions.*;
import com.devindev.congressoemchamas.data.politician.Politician;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CamaraAPI {

    @Autowired
    private CamaraConfig camaraConfig;

    @Getter
    private Long currentLegislatureId;

    @PostConstruct
    private void init(){
        this.currentLegislatureId = requestCurrentLegislatureId();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CamaraAPI.class);

    @Cacheable(cacheNames = "politicianIdsByNameAndLegislatureId")
    public List<Profile> requestProfilesByNameAndLegislatureId(String name, Long legislatureId){
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(camaraConfig.getBaseUrl())
                    .setPath("deputados")
                    .addParameter("nome", name)
                    .addParameter("idLegislatura", legislatureId.toString());
            GetProfilesByName apiFunctionHandler = new GetProfilesByName();
            return Request.Get(builder.build()).execute().handleResponse(apiFunctionHandler);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve politician IDs by name on CamaraAPI.");
            LOGGER.error("Returning empty politicians list.");
            return new ArrayList<>();
        }
    }

    @Cacheable(cacheNames = "politicianById")
    public Politician requestPoliticianById(Long id){
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(camaraConfig.getBaseUrl())
                    .setPathSegments("deputados", id.toString());
            GetPoliticianById apiFunctionHandler = new GetPoliticianById();
            return Request.Get(builder.build()).execute().handleResponse(apiFunctionHandler);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve politician by id on CamaraAPI.");
            LOGGER.error("Returning null instead of a new Politician.");
            return null;
        }
    }

    public Long requestCurrentLegislatureId(){
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(camaraConfig.getBaseUrl())
                    .setPath("legislaturas")
                    .addParameter("ordem", "DESC")
                    .addParameter("ordenarPor", "id");
            GetCurrentLegislature apiFunctionHandler = new GetCurrentLegislature();
            return Request.Get(builder.build()).execute().handleResponse(apiFunctionHandler);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve the current legislature ID from CamaraAPI.");
            LOGGER.error("Returning null instead of the current legislature ID.");
            return null;
        }
    }

    @Cacheable(cacheNames = "propositionIdsByPoliticianId")
    public List<Long> requestPropositionIdsByPoliticianId(Long politicianId) {
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(camaraConfig.getBaseUrl())
                    .setPath("proposicoes")
                    .addParameter("idDeputadoAutor", politicianId.toString())
                    .addParameter("ordem", "DESC")
                    .addParameter("ordenarPor", "id");
            GetPropositionsIdsByPoliticianId apiFunctionHandler = new GetPropositionsIdsByPoliticianId();
            return Request.Get(builder.build()).execute().handleResponse(apiFunctionHandler);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve proposition IDs from CamaraAPI.");
            LOGGER.error("Returning an empty propositions IDs list.");
            return new ArrayList<>();
        }
    }

    @Cacheable(cacheNames = "propositionById")
    public Proposition requestPropositionById(Long propositionId){
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(camaraConfig.getBaseUrl())
                    .setPathSegments("proposicoes", propositionId.toString());
            GetPropositionsByPolitician apiFunctionHandler = new GetPropositionsByPolitician();
            return Request.Get(builder.toString()).execute().handleResponse(apiFunctionHandler);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve a proposition from CamaraAPI.");
            LOGGER.error("Returning a null proposition.");
            return null;
        }
    }

    @Cacheable(cacheNames = "authorsByPropositionId")
    public List<String> requestAuthorsByPropositionId(Long propositionId){
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(camaraConfig.getBaseUrl())
                    .setPathSegments("proposicoes", propositionId.toString(), "autores");
            GetAuthorsByPropositionId apiFunctionHandler = new GetAuthorsByPropositionId();
            return Request.Get(builder.build()).execute().handleResponse(apiFunctionHandler);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve the authors list of a proposition from CamaraAPI.");
            LOGGER.error("Returning an empty authors list.");
            return new ArrayList<>();
        }
    }

    @Cacheable(cacheNames = "processingHistoryByPropositionId")
    public List<Processing> requestProcessingHistoryByPropositionId(Long propositionId){
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(camaraConfig.getBaseUrl())
                    .setPathSegments("proposicoes", propositionId.toString(), "tramitacoes");
            GetProcessingHistoryByPropisitionId apiFunctionHandler = new GetProcessingHistoryByPropisitionId();
            return Request.Get(builder.toString()).execute().handleResponse(apiFunctionHandler);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve the processing list of a proposition from CamaraAPI.");
            LOGGER.error("Returning an empty processing list.");
            return new ArrayList<>();
        }
    }
}
