package com.gprindevelopment.cec.core.batch;

import com.gprindevelopment.cec.core.expense.Expense;
import com.gprindevelopment.cec.core.expense.MonthlyExpense;
import com.gprindevelopment.cec.core.expense.MonthlyExpenseService;
import com.gprindevelopment.cec.core.externalapi.camara.CamaraClientFacade;
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

    private final CamaraClientFacade camaraClientFacade;

    private final MonthlyExpenseService monthlyExpenseService;

    public Politician update(Long id) {
        Politician politician = camaraClientFacade.requestPoliticianById(id);
        updateExpenses(politician);
        updatePropositions(politician);
        return politicianRepository.save(politician);
    }

    private void updateExpenses(Politician politician) {
        List<Expense> expenses = camaraClientFacade.requestAllExpensesByPoliticianId(politician.getId(), null, camaraClientFacade.requestCurrentLegislature().getValidityYears());
        List<MonthlyExpense> monthlyExpenses = monthlyExpenseService.computeMonthlyExpenses(expenses);
        monthlyExpenses.forEach(me -> me.setPolitician(politician));
        politician.getMonthlyExpenses().clear();
        politician.getMonthlyExpenses().addAll(monthlyExpenses);
    }

    private void updatePropositions(Politician politician) {
        camaraClientFacade.requestPropositionIdsByPoliticianId(politician.getId()).forEach(propId -> {
            Proposition proposition = camaraClientFacade.requestPropositionById(propId);
            proposition.getAuthors().clear();
            proposition.getAuthors().addAll(camaraClientFacade.requestAuthorsByPropositionId(proposition.getId()));
            proposition.getProcessingHistory().clear();
            camaraClientFacade.requestProcessingHistoryByPropositionId(proposition.getId()).forEach(ph -> {
                ph.setProposition(proposition);
                proposition.getProcessingHistory().add(ph);
            });
            proposition.setPolitician(politician);
            politician.getPropositions().add(proposition);
        });
    }
}
