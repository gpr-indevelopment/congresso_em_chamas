package com.devindev.congressoemchamas.batch.rpw;

import com.devindev.congressoemchamas.data.politician.Politician;
import org.springframework.batch.item.*;

import java.util.List;
import java.util.Objects;

public class UpdatePoliticianRPW extends UpdaterRPW<Politician, Politician>{

    @Override
    public Politician read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return getCamaraAPI().requestPoliticianById(getPoliticianId());
    }

    @Override
    public Politician process(Politician updatedPolitician) throws Exception {
        Politician currentPolitician = getMainRepository().findById(getPoliticianId());
        if(Objects.nonNull(currentPolitician)) {
            updatedPolitician.setPropositions(currentPolitician.getPropositions());
            updatedPolitician.setMonthlyExpenses(currentPolitician.getMonthlyExpenses());
            updatedPolitician.setNews(currentPolitician.getNews());
        }
        return updatedPolitician;
    }

    @Override
    public void write(List<? extends Politician> items) throws Exception {
        items.forEach(politician -> getMainRepository().save(politician));
    }
}
