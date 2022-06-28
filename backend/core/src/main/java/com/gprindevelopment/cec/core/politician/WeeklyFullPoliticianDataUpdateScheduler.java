package com.gprindevelopment.cec.core.politician;

import com.gprindevelopment.cec.core.batch.BatchConfig;
import com.gprindevelopment.cec.core.batch.DataUpdaterAPI;
import com.gprindevelopment.cec.core.batch.PoliticianDataUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class WeeklyFullPoliticianDataUpdateScheduler {

    @Autowired
    private DataUpdaterAPI camaraAPI;

    @Autowired
    private PoliticianRepository politiciansRepository;

    @Autowired
    private PoliticianDataUpdater politicianDataUpdater;

    @Autowired
    private BatchConfig batchConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(WeeklyFullPoliticianDataUpdateScheduler.class);

    @Scheduled(cron = "0 0 3 * * FRI")
    private void executeJobs() throws Exception {
        LOGGER.info("Scheduled time has arrived. Database full update.");
        for (Long id : enlistIdsEligibleToUpdate()) {
            politicianDataUpdater.batchUpdate(id);
        }
    }

    private Set<Long> enlistIdsEligibleToUpdate() {
        Set<Long> scheduledIds = new HashSet<>();
        List<Profile> profiles = camaraAPI.requestProfilesByNameAndLegislatureId("", camaraAPI.requestCurrentLegislature().getId());
        profiles.forEach(profile -> {
            Optional<Politician> politicianOpt = politiciansRepository.findById(profile.getId());
            if(politicianOpt.isEmpty() || hasExpired(politicianOpt.get())) {
                scheduledIds.add(profile.getId());
            }
        });
        return scheduledIds;
    }

    private boolean hasExpired(Politician politician) {
        LocalDateTime nowTimestamp = LocalDateTime.now();
        LocalDateTime lastUpdatedTimestamp = politician.getUpdatedAt().toLocalDateTime();
        return nowTimestamp.minusDays(batchConfig.getPoliticianExpirationDays()).isAfter(lastUpdatedTimestamp);
    }
}
