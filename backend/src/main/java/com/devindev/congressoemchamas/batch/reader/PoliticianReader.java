package com.devindev.congressoemchamas.batch.reader;

import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class PoliticianReader implements ItemReader<Politician> {

    @Autowired
    private CamaraAPI camaraAPI;

    private Long politicianId;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        politicianId = stepExecution.getJobParameters().getLong("politicianId");
        Legislature currentLegislature = (Legislature) stepExecution.getJobExecution().getExecutionContext().get("currentLegislature");
    }

    @Override
    public Politician read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return camaraAPI.requestPoliticianById(politicianId);
    }
}
