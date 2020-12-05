package com.gprindevelopment.cec;

import com.gprindevelopment.cec.externalapi.camara.CamaraAPI;
import com.gprindevelopment.cec.politician.Politician;
import com.gprindevelopment.cec.politician.PoliticianRepository;
import com.gprindevelopment.cec.politician.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@EnableScheduling
public class BatchDataUpdateScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private DataUpdaterJobManager dataUpdaterJobManager;

    @Autowired
    private CamaraAPI camaraAPI;

    @Autowired
    private PoliticianRepository politiciansRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchDataUpdateScheduler.class);

    private Set<Long> scheduledIds = new HashSet<>();

    public void queuePolitician(Politician politician) {
        scheduledIds.add(politician.getId());
    }

    //@Scheduled(cron = "0 0 3 * * *")
    private void executeJobs() throws Exception {
        LOGGER.info("Scheduled time has arrived. Updating data from {} scheduled IDs.", scheduledIds.size());
        queueMissingPolitician();
        for (Long id : scheduledIds) {
            batchUpdate(id);
        }
    }

    private void batchUpdate(Long id) throws Exception {
        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addLong("politicianId", id);
        jobLauncher.run(dataUpdaterJobManager.updatePoliticianData(), parametersBuilder.toJobParameters());
    }

    private void queueMissingPolitician() {
        List<Profile> profiles = camaraAPI.requestProfilesByNameAndLegislatureId("", camaraAPI.requestCurrentLegislature().getId());
        profiles.forEach(profile -> {
            if(!politiciansRepository.findById(profile.getId()).isPresent()) {
                scheduledIds.add(profile.getId());
            }
        });
    }
}
