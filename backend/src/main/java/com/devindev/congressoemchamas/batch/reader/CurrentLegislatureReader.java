package com.devindev.congressoemchamas.batch.reader;

import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class CurrentLegislatureReader implements ItemReader<Legislature> {

    @Autowired
    private CamaraAPI camaraAPI;

    @Override
    public Legislature read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return camaraAPI.requestCurrentLegislature();
    }
}
