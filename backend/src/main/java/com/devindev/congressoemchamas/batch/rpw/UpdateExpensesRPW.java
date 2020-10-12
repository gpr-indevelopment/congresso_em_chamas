package com.devindev.congressoemchamas.batch.rpw;

import com.devindev.congressoemchamas.batch.CongressoBatchException;
import com.devindev.congressoemchamas.data.expenses.Expense;
import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.service.MonthlyExpenseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class UpdateExpensesRPW extends UpdaterRPW<List<Expense>, Politician> {

    @Autowired
    private MonthlyExpenseService monthlyExpenseService;

    private Legislature currentLegislature = getCamaraAPI().requestCurrentLegislature();

    @Override
    protected List<Expense> innerRead() {
        return getCamaraAPI().requestAllExpensesByPoliticianId(getPoliticianId(), null, currentLegislature.getValidityYears());
    }

    @Override
    public Politician process(List<Expense> expenses) throws Exception {
        Politician currentPolitician = getMainRepository().findById(getPoliticianId());
        if(Objects.isNull(currentPolitician)) {
            throw new CongressoBatchException("Update expenses requires a pre-existing politician in the database.");
        }
        List<MonthlyExpense> monthlyExpenses = monthlyExpenseService.computeMonthlyExpenses(expenses);
        currentPolitician.setMonthlyExpenses(monthlyExpenses);
        monthlyExpenses.forEach(monthlyExpense -> {
            monthlyExpense.setPolitician(currentPolitician);
            monthlyExpense.setLegislature(currentLegislature);
        });
        return null;
    }

    @Override
    public void write(List<? extends Politician> politicians) throws Exception {
        politicians.forEach(politician -> getMainRepository().save(politician));
    }
}
