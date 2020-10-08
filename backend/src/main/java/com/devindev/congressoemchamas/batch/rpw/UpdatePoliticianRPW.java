package com.devindev.congressoemchamas.batch.rpw;

import com.devindev.congressoemchamas.batch.CongressoBatchException;
import com.devindev.congressoemchamas.data.MainRepository;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@StepScope
public class UpdatePoliticianRPW implements ItemWriter<Politician>, ItemReader<Politician>, ItemProcessor<Politician, Politician> {

    @Autowired
    private CamaraAPI camaraAPI;

    @Autowired
    private MainRepository mainRepository;

    private Long politicianId;

    @BeforeStep
    private void beforeStep(StepExecution stepExecution) {
        this.politicianId = stepExecution.getJobParameters().getLong("politicianId");
        if(Objects.isNull(politicianId)) {
            throw new CongressoBatchException("This RPW expects a politicianId parameter");
        }
    }

    @Override
    public Politician read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return camaraAPI.requestPoliticianById(politicianId);
    }

    @Override
    public Politician process(Politician updatedPolitician) throws Exception {
        Politician currentPolitician = mainRepository.findById(politicianId);
        if(Objects.nonNull(currentPolitician)) {
            updatedPolitician.setPropositions(currentPolitician.getPropositions());
            updatedPolitician.setMonthlyExpenses(currentPolitician.getMonthlyExpenses());
            updatedPolitician.setNews(currentPolitician.getNews());
        }
        return updatedPolitician;
    }

    @Override
    public void write(List<? extends Politician> items) throws Exception {
        items.forEach(politician -> mainRepository.save(politician));
    }
}
