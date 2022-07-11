package com.gprindevelopment.cec.api.expense;

import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MonthlyExpenseService {

    public List<MonthlyExpense> computeMonthlyExpenses(List<Expense> expenses) {
        Map<YearMonth, List<Expense>> sortedMap = sortExpensesByYearMonth(expenses);
        return buildMonthlyExpenses(sortedMap);
    }

    List<MonthlyExpense> buildMonthlyExpenses(Map<YearMonth, List<Expense>> sortedMap) {
        List<MonthlyExpense> monthlyExpenses = new ArrayList<>();
        sortedMap.keySet().forEach(yearMonth -> {
            MonthlyExpense monthlyExpense = new MonthlyExpense();
            monthlyExpense.setYearMonth(yearMonth);
            sortedMap.get(yearMonth).forEach(expense -> {
                monthlyExpense.getExpenses().add(expense);
                monthlyExpense.setValue(monthlyExpense.getValue() + expense.getValue());
            });
            monthlyExpenses.add(monthlyExpense);
        });
        return monthlyExpenses;
    }

    Map<YearMonth, List<Expense>> sortExpensesByYearMonth(List<Expense> expenses){
        Map<YearMonth, List<Expense>> sortedMap = new HashMap<>();
        expenses.forEach(expense -> {
            YearMonth currentYearMonth = expense.getYearMonth();
            sortedMap.putIfAbsent(currentYearMonth, new ArrayList<>());
            sortedMap.get(currentYearMonth).add(expense);
        });
        return sortedMap;
    }
}
