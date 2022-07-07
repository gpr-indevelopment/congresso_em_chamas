package com.gprindevelopment.cec.core.batch.rpw;

import com.gprindevelopment.cec.core.batch.CongressoBatchException;
import com.gprindevelopment.cec.core.externalapi.camara.CamaraClientFacade;
import com.gprindevelopment.cec.core.politician.Politician;
import com.gprindevelopment.cec.core.politician.PoliticianRepository;
import com.gprindevelopment.cec.core.proposition.Proposition;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
@JobScope
public class UpdatePropositionsRPW extends UpdaterRPW<List<Proposition>, Politician> {

    public UpdatePropositionsRPW(CamaraClientFacade camaraClientFacade, PoliticianRepository mainRepository) {
        super(camaraClientFacade, mainRepository);
    }

    @Override
    protected List<Proposition> innerRead() {
        List<Proposition> propositions = new ArrayList<>();
        getCamaraAPI()
                .requestPropositionIdsByPoliticianId(getPoliticianId())
                .forEach(propositionId -> {
                    Proposition proposition = getCamaraAPI().requestPropositionById(propositionId);
                    proposition.setAuthors(new HashSet<>(getCamaraAPI().requestAuthorsByPropositionId(propositionId)));
                    proposition.setProcessingHistory(getCamaraAPI().requestProcessingHistoryByPropositionId(propositionId));
                    propositions.add(proposition);
                });
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
