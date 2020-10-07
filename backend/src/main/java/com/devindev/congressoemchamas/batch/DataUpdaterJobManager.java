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

    @Autowired
    private UpdateEligibilityDecider updateEligibilityDecider;

    public static final String ELIGIBLE_STATUS = "ELIGIBLE";

    public static final String INELIGIBLE_STATUS = "INELIGIBLE";

    @Bean
    public Job updatePoliticianData() {
        return factory.get("someJob")
                .start(dataUpdaterStepsManager.loadCamaraPolitician())
                .next(updateEligibilityDecider).on(INELIGIBLE_STATUS).end()
                .from(updateEligibilityDecider).on(ELIGIBLE_STATUS).to(politicianDataUpdateFlow())
                .end()
                .build();
    }

    private Flow politicianDataUpdateFlow() {
        return new FlowBuilder<SimpleFlow>("politicianDataUpdateFlow")
                .start(dataUpdaterStepsManager.loadCamaraExpenses())
                .next(dataUpdaterStepsManager.loadCamaraPropositions())
                .next(dataUpdaterStepsManager.savePolitician())
                .end();
    }
}
