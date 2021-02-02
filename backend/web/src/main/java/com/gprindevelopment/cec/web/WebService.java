package com.gprindevelopment.cec.web;

import com.gprindevelopment.cec.core.expense.Expense;
import com.gprindevelopment.cec.core.expense.MonthlyExpense;
import com.gprindevelopment.cec.core.expense.MonthlyExpenseService;
import com.gprindevelopment.cec.core.politician.*;
import com.gprindevelopment.cec.core.proposition.Proposition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WebService {

    private final PoliticianService politicianService;

    private final ProfileService profileService;

    private final MonthlyExpenseService monthlyExpenseService;

    public Politician findPoliticianById(Long politicianId) {
        return politicianService.findById(politicianId);
    }

    public List<Proposition> findPropositionsByPoliticianId(Long politicianId) {
        return politicianService.findAllPropositionsByPoliticianId(politicianId);
    }

    public List<News> findNewsByPoliticianId(Long politicianId) {
        return politicianService.findAllNewsByPoliticianId(politicianId);
    }

    public List<MonthlyExpense> findMonthlyExpensesByPoliticianId(Long politicianId, List<Integer> years, Integer lastMonths) {
        if(CollectionUtils.isEmpty(years)) {
            if(Objects.isNull(lastMonths)) {
                return politicianService.findAllCurrentLegislatureMonthlyExpenses(politicianId);
            } else {
                List<Expense> expenses = new ArrayList<>();
                LocalDate now = LocalDate.now();
                for (int i = 0; i < lastMonths; i++) {
                    LocalDate ld = now.minusMonths(i);
                    int month = ld.getMonthValue();
                    int year = ld.getYear();
                    expenses.addAll(politicianService.findAllExpensesByYearsMonths(politicianId, Arrays.asList(month), Arrays.asList(year)));
                }
                return monthlyExpenseService.computeMonthlyExpenses(expenses);
            }
        } else {
            return politicianService.findAllMonthlyExpensesByYearsMonths(politicianId, null, years);
        }
    }

    public List<Profile> findProfilesByName(String name) {
        return profileService.findAllOnCurrentLegislatureByPoliticianName(name);
    }
}
