package com.devindev.congressoemchamas.batch;

import com.devindev.congressoemchamas.batch.reader.CamaraReader;
import com.devindev.congressoemchamas.batch.reader.CurrentLegislatureReader;
import com.devindev.congressoemchamas.batch.reader.PoliticianReader;
import com.devindev.congressoemchamas.batch.reader.UpdatePoliticianRPW;
import com.devindev.congressoemchamas.batch.tasklet.LoadCurrentLegislatureTasklet;
import com.devindev.congressoemchamas.batch.writer.CamaraWriter;
import com.devindev.congressoemchamas.batch.writer.LegislatureWriter;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.politician.Politician;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataUpdaterStepsManager {

    @Autowired
    private StepBuilderFactory factory;

    @Autowired
    private CamaraReader camaraReader;

    @Autowired
    private PoliticianReader politicianReader;

    @Autowired
    private CamaraWriter camaraWriter;

    @Autowired
    private CurrentLegislatureReader currentLegislatureReader;

    @Autowired
    private LegislatureWriter legislatureWriter;

    @Autowired
    private LoadCurrentLegislatureTasklet loadCurrentLegislatureTasklet;

    @Autowired
    @Qualifier("currentLegislaturePromotionListener")
    private ExecutionContextPromotionListener executionContextPromotionListener;

    @Autowired
    private UpdatePoliticianRPW updatePoliticianRPW;

    @Bean
    public Step updatePoliticianStep() {
        return factory.get("updatePoliticianStep")
                .<Politician, Politician>chunk(1)
                .reader(updatePoliticianRPW)
                .processor(updatePoliticianRPW)
                .writer(updatePoliticianRPW)
                .build();
    }

    @Bean
    public Step loadCurrentLegislature() {
        return factory.get("loadCurrentLegislature")
                .tasklet(loadCurrentLegislatureTasklet)
                .listener(executionContextPromotionListener)
                .build();
    }

    @Bean
    public Step loadCamaraPolitician() {
        return factory.get("loadCamaraPolitician")
                .<Politician, Politician>chunk(10)
                .reader(politicianReader)
                .writer(camaraWriter)
                .build();
    }

    @Bean
    public Step loadCamaraExpenses() {
        return factory.get("loadCamaraExpenses")
                .<Politician, Politician>chunk(10)
                .reader(camaraReader)
                .writer(camaraWriter)
                .build();
    }

    @Bean
    public Step loadCamaraPropositions() {
        return factory.get("loadCamaraPropositions")
                .<Politician, Politician>chunk(10)
                .reader(camaraReader)
                .writer(camaraWriter)
                .build();
    }

    @Bean
    public Step savePolitician() {
        return factory.get("savePolitician")
                .<Politician, Politician>chunk(10)
                .reader(camaraReader)
                .writer(camaraWriter)
                .build();
    }
}
