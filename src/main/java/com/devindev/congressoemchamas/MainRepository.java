package com.devindev.congressoemchamas;

import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.politician.PoliticianDAO;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.data.proposition.PropositionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MainRepository {

    @Autowired
    private PoliticianDAO politicianDAO;

    @Autowired
    private PropositionDAO propositionDAO;

    public Politician findById(Long id){
        return politicianDAO.findById(id).get();
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
