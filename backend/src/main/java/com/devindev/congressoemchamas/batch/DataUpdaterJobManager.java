package com.devindev.congressoemchamas.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class DataUpdaterJobManager {

    @Autowired
    private JobBuilderFactory factory;

    @Autowired
    private DataUpdaterStepsManager dataUpdaterStepsManager;

    @Bean
    public Job updatePoliticianData() {
        return factory.get("someJob")
                .start(dataUpdaterStepsManager.loadDatabasePolitician())
                .on("INELIGIBLE").end()
                .from(dataUpdaterStepsManager.loadDatabasePolitician())
                .on("ELIGIBLE").to(politicianDataUpdateFlow())
                .end()
                .build();
    }

    private Flow politicianDataUpdateFlow() {
        return new FlowBuilder<SimpleFlow>("politicianDataUpdateFlow")
                .start(dataUpdaterStepsManager.loadCamaraPolitician())
                .next(dataUpdaterStepsManager.loadCamaraExpenses())
                .next(dataUpdaterStepsManager.loadCamaraPropositions())
                .next(dataUpdaterStepsManager.savePolitician())
                .build();
    }
}
