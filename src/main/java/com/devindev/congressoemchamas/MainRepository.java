package com.devindev.congressoemchamas;

import com.devindev.congressoemchamas.data.CamaraAPIDataUpdater;
import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.politician.PoliticianDAO;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.data.proposition.PropositionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MainRepository {

    @Autowired
    private PoliticianDAO politicianDAO;

    @Autowired
    private PropositionDAO propositionDAO;

    @Autowired
    private CamaraAPIDataUpdater dataUpdater;

    public Politician findById(Long id){
        Optional<Politician> polOpt = politicianDAO.findById(id);
        return polOpt.isPresent() ? polOpt.get() : dataUpdater.updatePolitician(id);
    }

    public Politician save(Politician politician){
        return politicianDAO.save(politician);
    }

    public List<Proposition> findAllPropositionsByPolitician(Long politicianId){
        return propositionDAO.findAllByPolitician(findById(politicianId));
    }

    public List<MonthlyExpense> findExpensesByPoliticianAndLegislature(Long politicianId, Long legislatureId){
        return politicianDAO.findAllExpensesByPoliticianAndLegislature(politicianId, legislatureId);
    }
}
