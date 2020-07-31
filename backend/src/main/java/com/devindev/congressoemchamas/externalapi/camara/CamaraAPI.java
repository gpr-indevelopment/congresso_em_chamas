package com.devindev.congressoemchamas.externalapi.camara;

import com.devindev.congressoemchamas.data.expenses.Expense;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.processing.Processing;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.externalapi.CustomURIBuilder;
import com.devindev.congressoemchamas.externalapi.camara.functions.*;
import com.devindev.congressoemchamas.data.politician.Politician;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.*;

@Component
public class CamaraAPI {

    @Autowired
    private CamaraConfig camaraConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(CamaraAPI.class);

    @Cacheable(cacheNames = "politicianIdsByNameAndLegislatureId")
    public List<Profile> requestProfilesByNameAndLegislatureId(String name, Long legislatureId) {
        try {
            legislatureId = Objects.isNull(legislatureId) ? requestCurrentLegislature().getId() : legislatureId;
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
            LOGGER.error("Returning null instead of a politician object.");
            return null;
        }
    }

    @Cacheable(cacheNames = "politicianById")
    public Politician requestPoliticianById(Long id) {
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

    @Cacheable(cacheNames = "currentLegislatureId")
    public Legislature requestCurrentLegislature() {
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
            LOGGER.error("Returning null instead of proposition ids.");
            return null;
        }
    }

    @Cacheable(cacheNames = "propositionById")
    public Proposition requestPropositionById(Long propositionId) {
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
    public List<String> requestAuthorsByPropositionId(Long propositionId) {
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(camaraConfig.getBaseUrl())
                    .setPathSegments("proposicoes", propositionId.toString(), "autores");
            GetAuthorsByPropositionId apiFunctionHandler = new GetAuthorsByPropositionId();
            return Request.Get(builder.build()).execute().handleResponse(apiFunctionHandler);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve the authors list of a proposition from CamaraAPI.");
            LOGGER.error("Returning null instead of a list of authors.");
            return null;
        }
    }

    @Cacheable(cacheNames = "processingHistoryByPropositionId")
    public List<Processing> requestProcessingHistoryByPropositionId(Long propositionId) {
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(camaraConfig.getBaseUrl())
                    .setPathSegments("proposicoes", propositionId.toString(), "tramitacoes");
            GetProcessingHistoryByPropisitionId apiFunctionHandler = new GetProcessingHistoryByPropisitionId();
            return Request.Get(builder.toString()).execute().handleResponse(apiFunctionHandler);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve the processing list of a proposition from CamaraAPI.");
            LOGGER.error("Returning null instead of a processing list.");
            return null;
        }
    }

    @Cacheable(cacheNames = "expensesByPropositionId")
    public List<Expense> requestAllExpensesByPoliticianId(Long politicianId, List<Integer> requestMonths, List<Integer> requestYears) {
        try {
            CustomURIBuilder builder = new CustomURIBuilder();
            builder.setScheme("http").setHost(camaraConfig.getBaseUrl())
                    .setPathSegments("deputados", politicianId.toString(), "despesas")
                    .addParameter("itens", "100");
            builder.addListParameter("mes", requestMonths);
            builder.addListParameter("ano", requestYears);
            GetAllExpensesByPoliticianId apiFunctionHandler = new GetAllExpensesByPoliticianId();
            return Request.Get(builder.toString()).execute().handleResponse(apiFunctionHandler);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve the expenses list from CamaraAPI.");
            LOGGER.error("Returning null instead of a expenses list.");
            return null;
        }
    }
}
