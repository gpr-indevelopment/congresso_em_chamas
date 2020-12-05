package com.gprindevelopment.cec;

import com.gprindevelopment.cec.expense.Expense;
import com.gprindevelopment.cec.expense.MonthlyExpense;
import com.gprindevelopment.cec.expense.MonthlyExpenseService;
import com.gprindevelopment.cec.politician.Politician;
import com.gprindevelopment.cec.politician.PoliticianRepository;
import com.gprindevelopment.cec.politician.PoliticianService;
import com.gprindevelopment.cec.proposition.Proposition;
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
