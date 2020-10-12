package com.devindev.congressoemchamas.batch.rpw;

import com.devindev.congressoemchamas.batch.CongressoBatchException;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.proposition.Proposition;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        Politician currentPolitician = getMainRepository().findById(getPoliticianId());
        if(Objects.isNull(currentPolitician)) {
            throw new CongressoBatchException("Update propositions requires a pre-existing politician in the database.");
        }
        currentPolitician.setPropositions(propositions);
        propositions.forEach(proposition -> proposition.setPolitician(currentPolitician));
        return currentPolitician;
    }

    @Override
    public void write(List<? extends Politician> politicians) throws Exception {
        politicians.forEach(politician -> getMainRepository().save(politician));
    }
}
