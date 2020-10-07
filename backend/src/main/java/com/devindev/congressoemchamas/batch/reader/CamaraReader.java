package com.devindev.congressoemchamas.batch.reader;

import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class CamaraReader implements ItemReader<Politician> {

    @Value("#{jobParameters['politicianId']}")
    private Long politicianId;

    @Override
    public Politician read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
