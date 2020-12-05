package com.devindev.congressoemchamas.batch.rpw;

import com.devindev.congressoemchamas.batch.CongressoBatchException;
import com.devindev.congressoemchamas.batch.DataUpdaterAPI;
import com.devindev.congressoemchamas.data.MainRepository;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class UpdaterRPW <A, B> implements ItemReader<A>, ItemProcessor<A, B>, ItemWriter<B>  {

    @Autowired
    private DataUpdaterAPI dataUpdaterAPI;

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

    public DataUpdaterAPI getCamaraAPI() {
        return dataUpdaterAPI;
    }

    public MainRepository getMainRepository() {
        return mainRepository;
    }

    public Long getPoliticianId() {
        return politicianId;
    }
}
