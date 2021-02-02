package com.gprindevelopment.cec.core.batch.rpw;

import com.gprindevelopment.cec.core.batch.CongressoBatchException;
import com.gprindevelopment.cec.core.batch.DataUpdaterAPI;
import com.gprindevelopment.cec.core.expense.Expense;
import com.gprindevelopment.cec.core.expense.MonthlyExpense;
import com.gprindevelopment.cec.core.expense.MonthlyExpenseService;
import com.gprindevelopment.cec.core.externalapi.camara.Legislature;
import com.gprindevelopment.cec.core.politician.Politician;
import com.gprindevelopment.cec.core.politician.PoliticianRepository;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Component
@JobScope
public class UpdateExpensesRPW extends UpdaterRPW<List<Expense>, Politician> {

    private final MonthlyExpenseService monthlyExpenseService;

    private Legislature currentLegislature;

    public UpdateExpensesRPW(DataUpdaterAPI dataUpdaterAPI, PoliticianRepository mainRepository, MonthlyExpenseService monthlyExpenseService) {
        super(dataUpdaterAPI, mainRepository);
        this.monthlyExpenseService = monthlyExpenseService;
    }

    @PostConstruct
    private void setLegislature() {
         currentLegislature = getCamaraAPI().requestCurrentLegislature();
    }

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
            currentPolitician.getMonthlyExpenses().clear();
            currentPolitician.getMonthlyExpenses().addAll(monthlyExpenses);
            monthlyExpenses.forEach(monthlyExpense -> {
                monthlyExpense.setPolitician(currentPolitician);
                monthlyExpense.setLegislature(currentLegislature);
                monthlyExpense.getExpenses().forEach(expense -> expense.setMonthlyExpense(monthlyExpense));
            });
            return currentPolitician;
        }
        else {
            throw new CongressoBatchException("Update expense requires a pre-existing politician in the database.");
        }
    }

    @Override
    public void write(List<? extends Politician> politicians) throws Exception {
        politicians.forEach(politician -> getMainRepository().save(politician));
    }
}
