package com.gprindevelopment.cec.metrics;

import com.gprindevelopment.cec.core.expense.Expense;
import com.gprindevelopment.cec.core.expense.MonthlyExpense;
import com.gprindevelopment.cec.core.expense.MonthlyExpenseService;
import com.gprindevelopment.cec.core.politician.Politician;
import com.gprindevelopment.cec.core.politician.PoliticianRepository;
import com.gprindevelopment.cec.core.proposition.Proposition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OnlineDataUpdater {

    private final PoliticianRepository politicianRepository;

    private final DataUpdaterAPI dataUpdaterAPI;

    private final MonthlyExpenseService monthlyExpenseService;

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
