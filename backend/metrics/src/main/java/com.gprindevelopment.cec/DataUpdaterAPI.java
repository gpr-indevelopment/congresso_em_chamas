package com.gprindevelopment.cec;

import com.gprindevelopment.cec.expense.Expense;
import com.gprindevelopment.cec.externalapi.camara.CamaraAPI;
import com.gprindevelopment.cec.externalapi.camara.Legislature;
import com.gprindevelopment.cec.politician.Politician;
import com.gprindevelopment.cec.politician.Profile;
import com.gprindevelopment.cec.proposition.Processing;
import com.gprindevelopment.cec.proposition.Proposition;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class DataUpdaterAPI {

    private final CamaraAPI camaraAPI;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataUpdaterAPI.class);

    private void doWait(Long delaySeconds) {
        try {
            delaySeconds = Objects.nonNull(delaySeconds) ? delaySeconds : 0;
            TimeUnit.SECONDS.sleep(delaySeconds);
        } catch (InterruptedException e) {
            LOGGER.error("An error was thrown when waiting in between API requests.");
        }
    }

    public List<Expense> requestAllExpensesByPoliticianId(Long id, List<Integer> months, List<Integer> years) {
        try {
            return camaraAPI.requestAllExpensesByPoliticianId(id, months, years);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when updating expense of politician with id {}. Retrying...", id);
            doWait(5L);
            return requestAllExpensesByPoliticianId(id, months, years);
        }
    }

    public List<Long> requestPropositionIdsByPoliticianId(Long id) {
        try {
            return camaraAPI.requestPropositionIdsByPoliticianId(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting proposition ids of politician with id {}. Retrying...", id);
            doWait(5L);
            return requestPropositionIdsByPoliticianId(id);
        }
    }

    public Proposition requestPropositionById(Long id) {
        try {
            return camaraAPI.requestPropositionById(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting propositions by its id: {}. Retrying...", id);
            doWait(5L);
            return requestPropositionById(id);
        }
    }

    public List<String> requestAuthorsByPropositionId(Long id) {
        try {
            return camaraAPI.requestAuthorsByPropositionId(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting a list of authors by a proposition id: {}. Retrying...", id);
            doWait(5L);
            return requestAuthorsByPropositionId(id);
        }
    }

    public List<Processing> requestProcessingHistoryByPropositionId(Long id) {
        try {
            return camaraAPI.requestProcessingHistoryByPropositionId(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting a list of processing history by a proposition id: {}. Retrying...", id);
            doWait(5L);
            return requestProcessingHistoryByPropositionId(id);
        }
    }

    public Politician requestPoliticianById(Long id) {
        try {
            return camaraAPI.requestPoliticianById(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting politician with id {}. Retrying...", id);
            doWait(5L);
            return requestPoliticianById(id);
        }
    }

    public Legislature requestCurrentLegislature() {
        try {
            return camaraAPI.requestCurrentLegislature();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting the current legislature. Retrying...");
            doWait(5L);
            return requestCurrentLegislature();
        }
    }

    public List<Profile> requestProfilesByNameAndLegislatureId(String name, Long legislatureId) {
        try {
            return camaraAPI.requestProfilesByNameAndLegislatureId(name, legislatureId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting profiles by name and legislature id. Retrying...");
            doWait(5L);
            return requestProfilesByNameAndLegislatureId(name, legislatureId);
        }
    }
}
