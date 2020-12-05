package com.devindev.congressoemchamas.batch.rpw;

import com.devindev.congressoemchamas.batch.CongressoBatchException;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@JobScope
public class UpdatePropositionsRPW extends UpdaterRPW<List<Proposition>, Politician> {

    @Override
    protected List<Proposition> innerRead() {
        List<Proposition> propositions = new ArrayList<>();
        getCamaraAPI()
                .requestPropositionIdsByPoliticianId(getPoliticianId())
                .forEach(propositionId -> propositions.add(getCamaraAPI().requestPropositionById(propositionId)));
        return propositions;
    }

    @Override
    public Politician process(List<Proposition> propositions) throws Exception {
        Optional<Politician> currentPoliticianOpt = getMainRepository().findById(getPoliticianId());
        if(currentPoliticianOpt.isPresent()) {
            Politician currentPolitician = currentPoliticianOpt.get();
            currentPolitician.getPropositions().clear();
            currentPolitician.getPropositions().addAll(propositions);
            propositions.forEach(proposition -> proposition.setPolitician(currentPolitician));
            return currentPolitician;
        }
        else {
            throw new CongressoBatchException("Update propositions requires a pre-existing politician in the database.");
        }
    }

    @Override
    public void write(List<? extends Politician> politicians) throws Exception {
        politicians.forEach(politician -> getMainRepository().save(politician));
    }
}
