package com.gprindevelopment.cec.core.externalapi.camara;

import com.gprindevelopment.cec.core.externalapi.camara.functions.*;
import com.gprindevelopment.cec.core.expense.Expense;
import com.gprindevelopment.cec.core.politician.Politician;
import com.gprindevelopment.cec.core.politician.Profile;
import com.gprindevelopment.cec.core.proposition.Processing;
import com.gprindevelopment.cec.core.proposition.Proposition;
import com.gprindevelopment.cec.core.restclient.CustomURIBuilder;
import com.gprindevelopment.cec.core.restclient.InvalidRequestException;
import com.gprindevelopment.cec.core.restclient.RequestsSender;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@PropertySource("classpath:camara.properties")
public class CamaraAPI {

    @Autowired(required = false)
    private CamaraConfig camaraConfig;

    @Autowired
    private RequestsSender requestsSender;

    @Value("${camara.url}")
    private String baseUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(CamaraAPI.class);

    //@Cacheable(cacheNames = "politicianIdsByNameAndLegislatureId")
    public List<Profile> requestProfilesByNameAndLegislatureId(String name, Long legislatureId) {
        try {
            legislatureId = Objects.isNull(legislatureId) ? requestCurrentLegislature().getId() : legislatureId;
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(baseUrl)
                    .setPath("deputados")
                    .addParameter("nome", name)
                    .addParameter("idLegislatura", legislatureId.toString());
            GetProfilesByName apiFunctionHandler = new GetProfilesByName();
            return requestsSender.sendRequest(builder.build(), apiFunctionHandler);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve politician IDs by name on CamaraAPI.");
            LOGGER.error("Returning null instead of a politician object.");
            return null;
        }
    }

    //@Cacheable(cacheNames = "politicianById")
    public Politician requestPoliticianById(Long id) {
        if(Objects.nonNull(id)){
            try {
                URIBuilder builder = new URIBuilder();
                builder.setScheme("http").setHost(baseUrl)
                        .setPathSegments("deputados", id.toString());
                GetPoliticianById apiFunctionHandler = new GetPoliticianById();
                return requestsSender.sendRequest(builder.build(), apiFunctionHandler);
            } catch (Exception exception) {
                LOGGER.error(exception.getMessage());
                LOGGER.error("Unable to retrieve politician by id on CamaraAPI.");
                LOGGER.error("Returning null instead of a new Politician.");
                return null;
            }
        } else {
            throw new InvalidRequestException("Received null ID when building a request for politician by ID.");
        }

    }

    //@Cacheable(cacheNames = "currentLegislatureId")
    public Legislature requestCurrentLegislature() {
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(baseUrl)
                    .setPath("legislaturas")
                    .addParameter("ordem", "DESC")
                    .addParameter("ordenarPor", "id");
            GetCurrentLegislature apiFunctionHandler = new GetCurrentLegislature();
            return requestsSender.sendRequest(builder.build(), apiFunctionHandler);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve the current legislature ID from CamaraAPI.");
            LOGGER.error("Returning null instead of the current legislature ID.");
            return null;
        }
    }

    //@Cacheable(cacheNames = "propositionIdsByPoliticianId")
    public List<Long> requestPropositionIdsByPoliticianId(Long politicianId) {
        if(Objects.nonNull(politicianId)){
            try {
                URIBuilder builder = new URIBuilder();
                builder.setScheme("http").setHost(baseUrl)
                        .setPath("proposicoes")
                        .addParameter("idDeputadoAutor", politicianId.toString())
                        .addParameter("ordem", "DESC")
                        .addParameter("ordenarPor", "id");
                GetPropositionsIdsByPoliticianId apiFunctionHandler = new GetPropositionsIdsByPoliticianId();
                return requestsSender.sendRequest(builder.build(), apiFunctionHandler);
            } catch (Exception exception) {
                LOGGER.error(exception.getMessage());
                LOGGER.error("Unable to retrieve proposition IDs from CamaraAPI.");
                LOGGER.error("Returning null instead of proposition ids.");
                return null;
            }
        } else {
            throw new InvalidRequestException("Received null ID when building a request for propositions by politician ID.");
        }
    }

    //@Cacheable(cacheNames = "propositionById")
    public Proposition requestPropositionById(Long propositionId) {
        if(Objects.nonNull(propositionId)) {
            try {
                URIBuilder builder = new URIBuilder();
                builder.setScheme("http").setHost(baseUrl)
                        .setPathSegments("proposicoes", propositionId.toString());
                GetPropositionsByPolitician apiFunctionHandler = new GetPropositionsByPolitician();
                return requestsSender.sendRequest(builder.build(), apiFunctionHandler);
            } catch (Exception exception) {
                LOGGER.error(exception.getMessage());
                LOGGER.error("Unable to retrieve a proposition from CamaraAPI.");
                LOGGER.error("Returning a null proposition.");
                return null;
            }
        } else {
            throw new InvalidRequestException("Received null ID when building a request for proposition ID.");
        }

    }

    //@Cacheable(cacheNames = "authorsByPropositionId")
    public List<String> requestAuthorsByPropositionId(Long propositionId) {
        if(Objects.nonNull(propositionId)){
            try {
                URIBuilder builder = new URIBuilder();
                builder.setScheme("http").setHost(baseUrl)
                        .setPathSegments("proposicoes", propositionId.toString(), "autores");
                GetAuthorsByPropositionId apiFunctionHandler = new GetAuthorsByPropositionId();
                return requestsSender.sendRequest(builder.build(), apiFunctionHandler);
            } catch (Exception exception) {
                LOGGER.error(exception.getMessage());
                LOGGER.error("Unable to retrieve the authors list of a proposition from CamaraAPI.");
                LOGGER.error("Returning null instead of a list of authors.");
                return null;
            }
        } else {
            throw new InvalidRequestException("Received null ID when building a request of authores by propositionId.");
        }
    }

    //@Cacheable(cacheNames = "processingHistoryByPropositionId")
    public List<Processing> requestProcessingHistoryByPropositionId(Long propositionId) {
        if(Objects.nonNull(propositionId)){
            try {
                URIBuilder builder = new URIBuilder();
                builder.setScheme("http").setHost(baseUrl)
                        .setPathSegments("proposicoes", propositionId.toString(), "tramitacoes");
                GetProcessingHistoryByPropisitionId apiFunctionHandler = new GetProcessingHistoryByPropisitionId();
                return requestsSender.sendRequest(builder.build(), apiFunctionHandler);
            } catch (Exception exception) {
                LOGGER.error(exception.getMessage());
                LOGGER.error("Unable to retrieve the processing list of a proposition from CamaraAPI.");
                LOGGER.error("Returning null instead of a processing list.");
                return null;
            }
        } else {
            throw new InvalidRequestException("Received null ID when building a request of processings by propositionId.");
        }
    }

    //@Cacheable(cacheNames = "expensesByPropositionId")
    public List<Expense> requestAllExpensesByPoliticianId(Long politicianId, List<Integer> requestMonths, List<Integer> requestYears) {
        if(Objects.nonNull(politicianId)) {
            try {
                CustomURIBuilder builder = new CustomURIBuilder();
                builder.setScheme("http").setHost(baseUrl)
                        .setPathSegments("deputados", politicianId.toString(), "despesas")
                        .addParameter("itens", "100");
                builder.addListParameter("mes", requestMonths);
                builder.addListParameter("ano", requestYears);
                GetAllExpensesByPoliticianId apiFunctionHandler = new GetAllExpensesByPoliticianId();
                return requestsSender.sendRequest(builder.build(), apiFunctionHandler);
            } catch (Exception exception) {
                LOGGER.error(exception.getMessage());
                LOGGER.error("Unable to retrieve the expense list from CamaraAPI.");
                LOGGER.error("Returning null instead of a expense list.");
                return null;
            }
        } else {
            throw new InvalidRequestException("Received null ID when building a request of expense by politician ID.");
        }
    }
}
