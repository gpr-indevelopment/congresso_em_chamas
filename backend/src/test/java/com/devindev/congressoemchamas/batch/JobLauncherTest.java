package com.devindev.congressoemchamas.batch;

import com.devindev.congressoemchamas.batch.reader.CamaraReader;
import com.devindev.congressoemchamas.batch.writer.CamaraWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UpdateEligibilityDecider.class, CamaraWriter.class, CamaraReader.class, DataUpdaterStepsManager.class, DataUpdaterJobManager.class})
public class JobLauncherTest {

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private DataUpdaterJobManager dataUpdaterJobManager;

    @Test
    public void testBatch() throws Exception {
        launcher.run(dataUpdaterJobManager.updatePoliticianData(), new JobParameters());
    }
}
