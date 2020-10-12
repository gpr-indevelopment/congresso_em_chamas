package com.devindev.congressoemchamas.batch;

import com.devindev.congressoemchamas.data.politician.Politician;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataUpdateScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private DataUpdaterJobManager dataUpdaterJobManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataUpdateScheduler.class);

    private List<Long> scheduledIds = new ArrayList<>();

    public void queuePolitician(Politician politician) {
        scheduledIds.add(politician.getId());
    }

    @Scheduled(cron = "0 0 3 * * *")
    private void executeJobs() throws Exception {
        LOGGER.info("Scheduled time has arrived. Updating data from scheduled IDs.");
        for (Long id : scheduledIds) {
            JobParametersBuilder parametersBuilder = new JobParametersBuilder();
            parametersBuilder.addLong("politicianId", id);
            jobLauncher.run(dataUpdaterJobManager.updatePoliticianData(), parametersBuilder.toJobParameters());
        }
    }
}
