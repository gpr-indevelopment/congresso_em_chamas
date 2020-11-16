package com.devindev.congressoemchamas.batch;

import com.devindev.congressoemchamas.CongressoEmChamasApplication;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CongressoEmChamasApplication.class})
@SpringBatchTest
@RunWith(SpringRunner.class)
@Ignore
public class UpdatePoliticianDataJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncher;

    @Test
    public void test() throws Exception {
        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addLong("politicianId", 204558L);
        JobExecution execution = jobLauncher.launchJob(parametersBuilder.toJobParameters());
        assertThat(execution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }
}
