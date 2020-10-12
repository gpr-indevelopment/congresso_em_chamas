package com.devindev.congressoemchamas.batch.rpw;

import com.devindev.congressoemchamas.batch.CongressoBatchException;
import com.devindev.congressoemchamas.data.expenses.Expense;
import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.service.MonthlyExpenseService;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@StepScope
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
        Optional<Politician> currentPoliticianOpt = getMainRepository().findById(getPoliticianId());
        if(currentPoliticianOpt.isPresent()) {
            Politician currentPolitician = currentPoliticianOpt.get();
            List<MonthlyExpense> monthlyExpenses = monthlyExpenseService.computeMonthlyExpenses(expenses);
            currentPolitician.setMonthlyExpenses(monthlyExpenses);
            monthlyExpenses.forEach(monthlyExpense -> {
                monthlyExpense.setPolitician(currentPolitician);
                monthlyExpense.setLegislature(currentLegislature);
            });
            return currentPolitician;
        }
        else {
            throw new CongressoBatchException("Update expenses requires a pre-existing politician in the database.");
        }
    }

    @Override
    public void write(List<? extends Politician> politicians) throws Exception {
        politicians.forEach(politician -> getMainRepository().save(politician));
    }
}
