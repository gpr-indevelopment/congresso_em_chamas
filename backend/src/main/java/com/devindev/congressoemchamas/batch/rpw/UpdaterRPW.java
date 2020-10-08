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

import java.util.Objects;

@Component
@StepScope
public abstract class UpdaterRPW <A, B> implements ItemReader<A>, ItemProcessor<A, B>, ItemWriter<B>  {

    @Autowired
    private CamaraAPI camaraAPI;

    @Autowired
    private MainRepository mainRepository;

    private Long politicianId;

    private boolean isAllowedToRead = true;

    public static final String POLITICIAN_ID_KEY = "politicianId";

    @BeforeStep
    private void beforeStep(StepExecution stepExecution) {
        this.politicianId = stepExecution.getJobParameters().getLong(POLITICIAN_ID_KEY);
        if(Objects.isNull(politicianId)) {
            throw new CongressoBatchException("This RPW expects a politicianId parameter");
        }
    }

    @Override
    public A read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(isAllowedToRead) {
            isAllowedToRead = false;
            return innerRead();
        } else {
            return null;
        }
    }

    protected abstract A innerRead();

    public CamaraAPI getCamaraAPI() {
        return camaraAPI;
    }

    public MainRepository getMainRepository() {
        return mainRepository;
    }

    public Long getPoliticianId() {
        return politicianId;
    }
}
