package com.gprindevelopment.cec.core.batch.rpw;

import com.gprindevelopment.cec.core.batch.CongressoBatchException;
import com.gprindevelopment.cec.core.expense.Expense;
import com.gprindevelopment.cec.core.expense.MonthlyExpense;
import com.gprindevelopment.cec.core.expense.MonthlyExpenseService;
import com.gprindevelopment.cec.core.externalapi.camara.CamaraClientFacade;
import com.gprindevelopment.cec.core.politician.Legislature;
import com.gprindevelopment.cec.core.politician.Politician;
import com.gprindevelopment.cec.core.politician.PoliticianRepository;
import io.github.gprindevelopment.exception.CamaraClientStatusException;
import io.github.gprindevelopment.exception.RecursoNaoExisteException;
import io.github.gprindevelopment.exception.RespostaNaoEsperadaException;
import io.github.gprindevelopment.legislaturas.LegislaturaClient;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@JobScope
public class UpdateExpensesRPW extends UpdaterRPW<List<Expense>, Politician> {

    private final MonthlyExpenseService monthlyExpenseService;

    private final LegislaturaClient legislaturaClient;

    private Legislature currentLegislature;

    public UpdateExpensesRPW(CamaraClientFacade camaraClientFacade, PoliticianRepository mainRepository, MonthlyExpenseService monthlyExpenseService, LegislaturaClient legislaturaClient) {
        super(camaraClientFacade, mainRepository);
        this.monthlyExpenseService = monthlyExpenseService;
        this.legislaturaClient = legislaturaClient;
    }

    @PostConstruct
    private void setLegislature() throws RespostaNaoEsperadaException, CamaraClientStatusException, RecursoNaoExisteException, IOException {
         currentLegislature = new Legislature(legislaturaClient.consultarLegislaturaAtual());
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
