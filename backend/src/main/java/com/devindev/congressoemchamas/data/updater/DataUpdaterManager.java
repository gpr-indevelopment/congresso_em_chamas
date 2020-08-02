package com.devindev.congressoemchamas.data.updater;

import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.politician.PoliticianDAO;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.utils.CustomStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Configuration
public class DataUpdaterManager {

    @Autowired
    private PoliticianDAO dao;

    @Autowired
    private DataUpdater dataUpdater;

    @Autowired
    private DataUpdaterAPI dataUpdaterAPI;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataUpdaterManager.class);

    @Scheduled(cron = "0 0 3 * * SUN")
    void scheduledInit() {
        LOGGER.error("Initiating data update scheduled job...");
        CustomStopWatch stopWatch = new CustomStopWatch();
        stopWatch.start("Fetching legislature");
        Legislature legislature = dataUpdaterAPI.requestCurrentLegislature();
        stopWatch.stop();
        stopWatch.start("Fetching profiles");
        List<Profile> profiles = dataUpdaterAPI.requestProfilesByNameAndLegislatureId(null, legislature.getId());
        stopWatch.stop();
        profiles.forEach(profile -> {
            Long currentId = profile.getId();
            Optional<Politician> politicianOpt = dao.findById(currentId);
            if(!politicianOpt.isPresent() || dataUpdater.eligibleForUpdate(politicianOpt.get())){
                stopWatch.start("Filling in politician with id: " + currentId);
                updatePolitician(currentId, legislature);
                stopWatch.stop();
            }
        });
        LOGGER.info(stopWatch.prettyPrint());
    }

    public Politician updatePolitician(Long id, Legislature legislature) {
        legislature = Objects.isNull(legislature) ? dataUpdaterAPI.requestCurrentLegislature() : legislature;
        Politician politician = dataUpdaterAPI.requestPoliticianById(id);
        dataUpdater.updateTwitterUsername(politician);
        dataUpdater.updateExpenses(politician, legislature);
        dataUpdater.updatePropositions(politician);
        return dao.save(politician);
    }
}
