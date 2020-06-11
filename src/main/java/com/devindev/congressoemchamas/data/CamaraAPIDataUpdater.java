package com.devindev.congressoemchamas.data;

import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.politician.PoliticianDAO;
import com.devindev.congressoemchamas.data.processing.Processing;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import com.devindev.congressoemchamas.externalapi.twitter.TwitterAPI;
import com.devindev.congressoemchamas.utils.CustomStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Configuration
@PropertySource("classpath:dataupdate.properties")
public class CamaraAPIDataUpdater {

    @Autowired
    private CamaraAPI camaraAPI;

    @Autowired
    private PoliticianDAO dao;

    @Autowired
    private TwitterAPI twitterAPI;

    @Value("${data.update.politician-expiration-time-days}")
    private Integer politicianExpirationTimeDays;

    private static final Logger LOGGER = LoggerFactory.getLogger(CamaraAPIDataUpdater.class);

    @Scheduled(cron = "0 0 3 * * SUN")
    private void init() {
        LOGGER.error("Initiating data update scheduled job...");
        CustomStopWatch stopWatch = new CustomStopWatch();
        stopWatch.start("Fetching legislature");
        Legislature legislature = camaraAPI.requestCurrentLegislatureId();
        stopWatch.stop();
        stopWatch.start("Fetching profiles");
        List<Profile> profiles = camaraAPI.requestProfilesByNameAndLegislatureId(null, legislature.getId());
        stopWatch.stop();
        profiles.forEach(profile -> {
            Long currentId = profile.getId();
            Optional<Politician> politicianOpt = dao.findById(currentId);
            if(!politicianOpt.isPresent() || eligibleForUpdate(politicianOpt.get())){
                stopWatch.start("Filling in politician with id: " + currentId);
                updatePolitician(currentId, legislature, null);
                stopWatch.stop();
            }
        });
        LOGGER.info(stopWatch.prettyPrint());
    }

    private boolean eligibleForUpdate(Politician politician){
        boolean eligibleForUpdate = false;
        LocalDateTime polUpdatedAt = politician.getUpdatedAt().toLocalDateTime();
        if(polUpdatedAt.plusDays(politicianExpirationTimeDays).isBefore(LocalDateTime.now())){
            eligibleForUpdate = true;
        }
        return eligibleForUpdate;
    }

    private Politician updatePolitician(Long id, Legislature legislature, Long delay) {
        try {
            delay = Objects.nonNull(delay) ? delay : 0;
            TimeUnit.SECONDS.sleep(delay);
            Politician politician = camaraAPI.requestPoliticianById(id);
            updateTwitterUsername(politician, null);
            updateExpenses(politician, legislature, null);
            updatePropositions(politician, null);
            return dao.save(politician);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace()[0].toString());
            LOGGER.error("An error occurred when updating politician with id {}. Retrying...", id);
            return updatePolitician(id, legislature,5l);
        }
    }

    private void updateTwitterUsername(Politician politician, Long delay){
        try {
            delay = Objects.nonNull(delay) ? delay : 0;
            TimeUnit.SECONDS.sleep(delay);
            politician.setTwitterUsername(twitterAPI.requestTwitterUsernameByName(politician.getName()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace()[0].toString());
            LOGGER.error("An error occurred when updating twitter username of politician with id {}. Retrying...", politician.getId());
            updateTwitterUsername(politician, 5l);
        }
    }

    private void updatePropositions(Politician politician, Long delay){
        try {
            delay = Objects.nonNull(delay) ? delay : 0;
            TimeUnit.SECONDS.sleep(delay);
            List<Proposition> propositions = new ArrayList<>();
            camaraAPI.requestPropositionIdsByPoliticianId(politician.getId()).forEach(propositionId -> {
                Proposition proposition = camaraAPI.requestPropositionById(propositionId);
                proposition.getAuthors().addAll(camaraAPI.requestAuthorsByPropositionId(propositionId));
                List<Processing> processings = camaraAPI.requestProcessingHistoryByPropositionId(propositionId);
                proposition.setProcessingHistory(processings);
                processings.forEach(processing -> processing.setProposition(proposition));
                proposition.setPolitician(politician);
                propositions.add(proposition);
            });
            politician.setPropositions(propositions);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace()[0].toString());
            LOGGER.error("An error occurred when updating propositions of politician with id {}. Retrying...", politician.getId());
            updatePropositions(politician, 5l);
        }
    }

    private void updateExpenses(Politician politician, Legislature legislature, Long delay){
        try {
            List<Integer> years = new ArrayList<>();
            while(legislature.getStartDate().getYear() < legislature.getEndDate().getYear()){
                years.add(legislature.getStartDate().getYear());
                legislature.setStartDate(legislature.getStartDate().plusYears(1l));
            }
            legislature.setStartDate(legislature.getStartDate().minusYears(4l));
            Long id = politician.getId();
            delay = Objects.nonNull(delay) ? delay : 0;
            TimeUnit.SECONDS.sleep(delay);
            List<MonthlyExpense> monthlyExpenses = MonthlyExpense.build(camaraAPI.requestAllExpensesByPoliticianId(id, null, years));
            monthlyExpenses.forEach(monthlyExpense -> {
                monthlyExpense.setPolitician(politician);
                monthlyExpense.setLegislature(legislature);
            });
            politician.setMonthlyExpenses(monthlyExpenses);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace()[0].toString());
            LOGGER.error("An error occurred when updating expenses of politician with id {}. Retrying...", politician.getId());
            updateExpenses(politician, legislature, 5l);
        }
    }
}
