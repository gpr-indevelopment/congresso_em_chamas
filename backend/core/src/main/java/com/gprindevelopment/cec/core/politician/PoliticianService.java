package com.gprindevelopment.cec.core.politician;

import com.gprindevelopment.cec.core.expense.Expense;
import com.gprindevelopment.cec.core.expense.MonthlyExpense;
import com.gprindevelopment.cec.core.expense.MonthlyExpenseService;
import com.gprindevelopment.cec.core.externalapi.camara.CamaraClientFacade;
import com.gprindevelopment.cec.core.externalapi.google.GoogleNewsAPI;
import com.gprindevelopment.cec.core.proposition.Proposition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PoliticianService {

    private final CamaraClientFacade camaraClientFacade;

    private final GoogleNewsAPI googleNewsAPI;

    private final MonthlyExpenseService monthlyExpenseService;

    private final PoliticianRepository politicianRepository;

    public Politician findById(Long politicianId) {
        return camaraClientFacade.requestPoliticianById(politicianId);
    }

    public List<Proposition> findAllPropositionsByPoliticianId(Long politicianId) {
        return camaraClientFacade
                .requestPropositionIdsByPoliticianId(politicianId)
                .stream()
                .map(propositionId -> {
                    Proposition proposition = camaraClientFacade.requestPropositionById(propositionId);
                    proposition.setAuthors(new HashSet<>(camaraClientFacade.requestAuthorsByPropositionId(propositionId)));
                    proposition.setProcessingHistory(camaraClientFacade.requestProcessingHistoryByPropositionId(propositionId));
                    return proposition;
                })
                .collect(Collectors.toList());
    }

    public List<News> findAllNewsByPoliticianId(Long politicianId) {
        return googleNewsAPI.requestNews(camaraClientFacade.requestPoliticianById(politicianId).getName());
    }

    public List<MonthlyExpense> findAllMonthlyExpensesByYearsMonths(Long politicianId, List<Integer> months, List<Integer> years) {
        return monthlyExpenseService.computeMonthlyExpenses(camaraClientFacade.requestAllExpensesByPoliticianId(politicianId, months, years));
    }

    public List<Expense> findAllExpensesByYearsMonths(Long politicianId, List<Integer> months, List<Integer> years) {
        return camaraClientFacade.requestAllExpensesByPoliticianId(politicianId, months, years);
    }

    public List<MonthlyExpense> findAllCurrentLegislatureMonthlyExpenses(Long politicianId) {
        List<Integer> years = new ArrayList<>();
        Legislature legislature = camaraClientFacade.requestCurrentLegislature();
        int startYear = legislature.getStartDate().getYear();
        int legislatureDurationYears = 4;
        for (int i = 0; i < legislatureDurationYears; i++) {
            years.add(startYear + i);
        }
        return monthlyExpenseService.computeMonthlyExpenses(camaraClientFacade.requestAllExpensesByPoliticianId(politicianId, null, years));
    }
}
