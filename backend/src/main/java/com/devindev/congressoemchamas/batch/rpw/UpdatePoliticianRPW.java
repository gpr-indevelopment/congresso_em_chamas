package com.devindev.congressoemchamas.batch.rpw;

import com.devindev.congressoemchamas.data.politician.Politician;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@StepScope
public class UpdatePoliticianRPW extends UpdaterRPW<Politician, Politician>{

    @Override
    protected Politician innerRead() {
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
