package com.devindev.congressoemchamas.data;

import com.devindev.congressoemchamas.data.accesslog.AccessLog;
import com.devindev.congressoemchamas.data.accesslog.AccessLogDAO;
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
    private AccessLogDAO accessLogDAO;

    public Optional<Politician> findById(Long id){
        return politicianDAO.findById(id);
    }

    public Politician save(Politician politician){
        return politicianDAO.save(politician);
    }

    public List<Proposition> findAllPropositionsByPolitician(Long politicianId){
        return propositionDAO.findAllByPoliticianId(politicianId);
    }

    public List<MonthlyExpense> findExpensesByPoliticianAndLegislature(Long politicianId, Long legislatureId){
        return politicianDAO.findAllExpensesByPoliticianAndLegislature(politicianId, legislatureId);
    }

    public AccessLog saveAccessLog(AccessLog accessLog){
        return accessLogDAO.save(accessLog);
    }

    public boolean existsById(Long id) {
        return politicianDAO.existsById(id);
    }

    public void delete(Long id) {
        politicianDAO.deleteById(id);
    }
}
