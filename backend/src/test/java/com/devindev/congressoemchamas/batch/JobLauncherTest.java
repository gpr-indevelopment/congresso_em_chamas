package com.devindev.congressoemchamas.batch;

import com.devindev.congressoemchamas.batch.reader.CamaraReader;
import com.devindev.congressoemchamas.batch.reader.CurrentLegislatureReader;
import com.devindev.congressoemchamas.batch.reader.PoliticianReader;
import com.devindev.congressoemchamas.batch.tasklet.LoadCurrentLegislatureTasklet;
import com.devindev.congressoemchamas.batch.writer.CamaraWriter;
import com.devindev.congressoemchamas.batch.writer.LegislatureWriter;
import com.devindev.congressoemchamas.externalapi.RequestsSender;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import com.devindev.congressoemchamas.externalapi.camara.CamaraConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ListenersManager.class, CurrentLegislatureReader.class, LoadCurrentLegislatureTasklet.class, LegislatureWriter.class, RequestsSender.class, CamaraConfig.class, PoliticianReader.class, UpdateEligibilityDecider.class, CamaraWriter.class, CamaraReader.class, DataUpdaterStepsManager.class, DataUpdaterJobManager.class, CamaraAPI.class})
public class JobLauncherTest {

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private DataUpdaterJobManager dataUpdaterJobManager;

    @Test
    public void testBatch() throws Exception {
        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addLong("politicianId", 205863L);
        launcher.run(dataUpdaterJobManager.updatePoliticianData(), parametersBuilder.toJobParameters());
    }
}
