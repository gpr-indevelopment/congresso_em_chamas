package com.devindev.congressoemchamas.batch;

import com.devindev.congressoemchamas.data.MainRepository;
import com.devindev.congressoemchamas.data.expenses.Expense;
import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.service.MonthlyExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OnlineDataUpdater {

    @Autowired
    private MainRepository politicianRepository;

    @Autowired
    private DataUpdaterAPI dataUpdaterAPI;

    @Autowired
    private MonthlyExpenseService monthlyExpenseService;

    public Politician update(Long id) {
        Politician politician = dataUpdaterAPI.requestPoliticianById(id);
        updateExpenses(politician);
        updatePropositions(politician);
        return politicianRepository.save(politician);
    }

    private void updateExpenses(Politician politician) {
        List<Expense> expenses = dataUpdaterAPI.requestAllExpensesByPoliticianId(politician.getId(), null, dataUpdaterAPI.requestCurrentLegislature().getValidityYears());
        List<MonthlyExpense> monthlyExpenses = monthlyExpenseService.computeMonthlyExpenses(expenses);
        monthlyExpenses.forEach(me -> me.setPolitician(politician));
        politician.getMonthlyExpenses().clear();
        politician.getMonthlyExpenses().addAll(monthlyExpenses);
    }

    private void updatePropositions(Politician politician) {
        dataUpdaterAPI.requestPropositionIdsByPoliticianId(politician.getId()).forEach(propId -> {
            Proposition proposition = dataUpdaterAPI.requestPropositionById(propId);
            proposition.getAuthors().clear();
            proposition.getAuthors().addAll(dataUpdaterAPI.requestAuthorsByPropositionId(proposition.getId()));
            proposition.getProcessingHistory().clear();
            dataUpdaterAPI.requestProcessingHistoryByPropositionId(proposition.getId()).forEach(ph -> {
                ph.setProposition(proposition);
                proposition.getProcessingHistory().add(ph);
            });
            proposition.setPolitician(politician);
            politician.getPropositions().add(proposition);
        });
    }
}
