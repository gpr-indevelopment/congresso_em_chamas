package com.devindev.congressoemchamas.batch;

import com.devindev.congressoemchamas.CongressoEmChamasApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CongressoEmChamasApplication.class})
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
