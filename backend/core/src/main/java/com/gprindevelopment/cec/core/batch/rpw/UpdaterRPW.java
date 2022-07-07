package com.gprindevelopment.cec.core.batch.rpw;

import com.gprindevelopment.cec.core.batch.CongressoBatchException;
import com.gprindevelopment.cec.core.externalapi.camara.CamaraClientFacade;
import com.gprindevelopment.cec.core.politician.PoliticianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.*;

import java.util.Objects;

@RequiredArgsConstructor
public abstract class UpdaterRPW <A, B> implements ItemReader<A>, ItemProcessor<A, B>, ItemWriter<B>  {

    private final CamaraClientFacade camaraClientFacade;

    private final PoliticianRepository mainRepository;

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

    public CamaraClientFacade getCamaraAPI() {
        return camaraClientFacade;
    }

    public PoliticianRepository getMainRepository() {
        return mainRepository;
    }

    public Long getPoliticianId() {
        return politicianId;
    }
}
