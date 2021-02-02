package com.gprindevelopment.cec.core.batch.rpw;

import com.gprindevelopment.cec.core.batch.DataUpdaterAPI;
import com.gprindevelopment.cec.core.politician.Politician;
import com.gprindevelopment.cec.core.politician.PoliticianRepository;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@JobScope
public class UpdatePoliticianRPW extends UpdaterRPW<Politician, Politician>{

    public UpdatePoliticianRPW(DataUpdaterAPI dataUpdaterAPI, PoliticianRepository mainRepository) {
        super(dataUpdaterAPI, mainRepository);
    }

    @Override
    protected Politician innerRead() {
        return getCamaraAPI().requestPoliticianById(getPoliticianId());
    }

    @Override
    public Politician process(Politician updatedPolitician) throws Exception {
        Optional<Politician> currentPoliticianOpt = getMainRepository().findById(getPoliticianId());
        if(currentPoliticianOpt.isPresent()) {
            Politician currentPolitician = currentPoliticianOpt.get();
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
