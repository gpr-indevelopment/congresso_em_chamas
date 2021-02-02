package com.gprindevelopment.cec.core.batch;

import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PoliticianDataUpdater {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private DataUpdaterJobManager dataUpdaterJobManager;

    public void batchUpdate(Long id) throws Exception {
        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addLong("politicianId", id);
        parametersBuilder.addDate("date", new Date());
        jobLauncher.run(dataUpdaterJobManager.updatePoliticianData(), parametersBuilder.toJobParameters());
    }
}
