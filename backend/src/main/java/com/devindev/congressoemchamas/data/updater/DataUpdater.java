package com.devindev.congressoemchamas.data.updater;

import com.devindev.congressoemchamas.data.expenses.Expense;
import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.processing.Processing;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.exceptions.DataUpdateException;
import com.devindev.congressoemchamas.service.MonthlyExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class DataUpdater {

    @Autowired
    private DataUpdaterAPI dataUpdaterAPI;

    @Autowired
    private MonthlyExpenseService monthlyExpenseService;

    @Autowired
    private DataUpdaterConfig dataUpdaterConfig;

    public void updateTwitterUsername(Politician politician){
        if(Objects.nonNull(politician)) {
            politician.setTwitterUsername(dataUpdaterAPI.requestTwitterUsernameByName(politician.getName()));
        } else {
            throw new DataUpdateException("An error was thrown when updating twitter username of a null politician.");
        }
    }

    public void updatePropositions(Politician politician){
        if(Objects.nonNull(politician)){
            List<Proposition> propositions = new ArrayList<>();
            dataUpdaterAPI.requestPropositionIdsByPoliticianId(politician.getId()).forEach(propositionId -> {
                Proposition proposition = dataUpdaterAPI.requestPropositionById(propositionId);
                proposition.getAuthors().addAll(dataUpdaterAPI.requestAuthorsByPropositionId(propositionId));
                List<Processing> processings = dataUpdaterAPI.requestProcessingHistoryByPropositionId(propositionId);
                proposition.setProcessingHistory(processings);
                processings.forEach(processing -> processing.setProposition(proposition));
                proposition.setPolitician(politician);
                propositions.add(proposition);
            });
            politician.setPropositions(propositions);
        } else {
            throw new DataUpdateException("An error was thrown when updating propositions with a null politician;");
        }
    }

    public void updateExpenses(Politician politician, Legislature legislature){
        List<Integer> years = new ArrayList<>();
        if(Objects.nonNull(politician) && Objects.nonNull(legislature)){
            Long id = politician.getId();
            List<Expense> expenses = dataUpdaterAPI.requestAllExpensesByPoliticianId(id, null, years);
            List<MonthlyExpense> monthlyExpenses = monthlyExpenseService.computeMonthlyExpenses(expenses);
            monthlyExpenses.forEach(monthlyExpense -> {
                monthlyExpense.setPolitician(politician);
                monthlyExpense.setLegislature(legislature);
            });
            politician.setMonthlyExpenses(monthlyExpenses);
        } else {
            throw new DataUpdateException("An error was thrown when updating expenses with a null politician or legislature.");
        }
    }

    public boolean eligibleForUpdate(Politician politician){
        if(Objects.nonNull(politician)){
            boolean eligibleForUpdate = false;
            LocalDateTime polUpdatedAt = politician.getUpdatedAt().toLocalDateTime();
            if(polUpdatedAt.plusDays(dataUpdaterConfig.getPoliticianExpirationTimeDays()).isBefore(LocalDateTime.now())){
                eligibleForUpdate = true;
            }
            return eligibleForUpdate;
        } else {
            throw new DataUpdateException("An error was thrown when checking the update eligibility of a politician.");
        }
    }
}
