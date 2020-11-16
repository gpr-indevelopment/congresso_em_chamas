package com.devindev.congressoemchamas.batch;

import com.devindev.congressoemchamas.data.MainRepository;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataUpdateScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private DataUpdaterJobManager dataUpdaterJobManager;

    @Autowired
    private CamaraAPI camaraAPI;

    @Autowired
    private MainRepository politiciansRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataUpdateScheduler.class);

    private Set<Long> scheduledIds = new HashSet<>();

    public void queuePolitician(Politician politician) {
        scheduledIds.add(politician.getId());
    }

    @Scheduled(cron = "0 0 3 * * *")
    private void executeJobs() throws Exception {
        LOGGER.info("Scheduled time has arrived. Updating data from {} scheduled IDs.", scheduledIds.size());
        List<Profile> profiles = camaraAPI.requestProfilesByNameAndLegislatureId("", camaraAPI.requestCurrentLegislature().getId());
        profiles.forEach(profile -> {
            if(!politiciansRepository.findById(profile.getId()).isPresent()) {
                scheduledIds.add(profile.getId());
            }
        });
        for (Long id : scheduledIds) {
            JobParametersBuilder parametersBuilder = new JobParametersBuilder();
            parametersBuilder.addLong("politicianId", id);
            jobLauncher.run(dataUpdaterJobManager.updatePoliticianData(), parametersBuilder.toJobParameters());
        }
    }
}
