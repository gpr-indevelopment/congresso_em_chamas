package com.gprindevelopment.cec.core.politician;

import com.gprindevelopment.cec.core.batch.DataUpdaterAPI;
import com.gprindevelopment.cec.core.expense.Expense;
import com.gprindevelopment.cec.core.expense.MonthlyExpense;
import com.gprindevelopment.cec.core.expense.MonthlyExpenseService;
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

    private final DataUpdaterAPI dataUpdaterAPI;

    private final GoogleNewsAPI googleNewsAPI;

    private final MonthlyExpenseService monthlyExpenseService;

    private final PoliticianRepository politicianRepository;

    private final DailyAccessedPoliticianDataUpdateScheduler dailyAccessedPoliticianDataUpdateScheduler;

    public Politician findById(Long politicianId) {
        dailyAccessedPoliticianDataUpdateScheduler.addToQueue(politicianId);
        return dataUpdaterAPI.requestPoliticianById(politicianId);
    }

    public List<Proposition> findAllPropositionsByPoliticianId(Long politicianId) {
        return dataUpdaterAPI
                .requestPropositionIdsByPoliticianId(politicianId)
                .stream()
                .map(propositionId -> {
                    Proposition proposition = dataUpdaterAPI.requestPropositionById(propositionId);
                    proposition.setAuthors(new HashSet<>(dataUpdaterAPI.requestAuthorsByPropositionId(propositionId)));
                    proposition.setProcessingHistory(dataUpdaterAPI.requestProcessingHistoryByPropositionId(propositionId));
                    return proposition;
                })
                .collect(Collectors.toList());
    }

    public List<News> findAllNewsByPoliticianId(Long politicianId) {
        return googleNewsAPI.requestNews(dataUpdaterAPI.requestPoliticianById(politicianId).getName());
    }

    public List<MonthlyExpense> findAllMonthlyExpensesByYearsMonths(Long politicianId, List<Integer> months, List<Integer> years) {
        return monthlyExpenseService.computeMonthlyExpenses(dataUpdaterAPI.requestAllExpensesByPoliticianId(politicianId, months, years));
    }

    public List<Expense> findAllExpensesByYearsMonths(Long politicianId, List<Integer> months, List<Integer> years) {
        return dataUpdaterAPI.requestAllExpensesByPoliticianId(politicianId, months, years);
    }

    public List<MonthlyExpense> findAllCurrentLegislatureMonthlyExpenses(Long politicianId) {
        List<Integer> years = new ArrayList<>();
        Legislature legislature = dataUpdaterAPI.requestCurrentLegislature();
        int startYear = legislature.getStartDate().getYear();
        int legislatureDurationYears = 4;
        for (int i = 0; i < legislatureDurationYears; i++) {
            years.add(startYear + i);
        }
        return monthlyExpenseService.computeMonthlyExpenses(dataUpdaterAPI.requestAllExpensesByPoliticianId(politicianId, null, years));
    }
}
