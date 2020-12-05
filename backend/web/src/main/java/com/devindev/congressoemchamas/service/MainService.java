package com.devindev.congressoemchamas.service;

import com.devindev.congressoemchamas.batch.BatchDataUpdateScheduler;
import com.devindev.congressoemchamas.batch.DataUpdaterConfig;
import com.devindev.congressoemchamas.batch.OnlineDataUpdater;
import com.devindev.congressoemchamas.data.MainRepository;
import com.devindev.congressoemchamas.data.expenses.Expense;
import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import com.devindev.congressoemchamas.externalapi.google.GoogleNewsAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class MainService {

    @Autowired
    private MainRepository politiciansRepository;

    @Autowired
    private CamaraAPI camaraAPI;

    @Autowired
    private GoogleNewsAPI googleNewsAPI;

    @Autowired
    private MonthlyExpenseService monthlyExpenseService;

    @Autowired
    private BatchDataUpdateScheduler batchDataUpdateScheduler;

    @Autowired
    private DataUpdaterConfig dataUpdaterConfig;

    @Autowired
    private OnlineDataUpdater onlineDataUpdater;

    public Politician findById(Long politicianId){
        Politician politician = politiciansRepository.findById(politicianId).orElseGet(() -> onlineDataUpdater.update(politicianId));
        if(Objects.nonNull(politician) && isEligibleForUpdate(politician)) {
            batchDataUpdateScheduler.queuePolitician(politician);
        }
        return politician;
    }

    public boolean isEligibleForUpdate(Politician politician) {
        boolean eligibleForUpdate = false;
        LocalDateTime polUpdatedAt = politician.getUpdatedAt().toLocalDateTime();
        if(polUpdatedAt.plusDays(dataUpdaterConfig.getPoliticianExpirationTimeDays()).isBefore(LocalDateTime.now())){
            eligibleForUpdate = true;
        }
        return eligibleForUpdate;
    }

    public List<Proposition> findPropositionsByPoliticianId(Long politicianId){
        List<Proposition> dbProps = politiciansRepository.findAllPropositionsByPolitician(politicianId);
        if(!dbProps.isEmpty()) {
            return dbProps;
        } else {
            List<Proposition> props = new ArrayList<>();
            camaraAPI.requestPropositionIdsByPoliticianId(politicianId).forEach(propId -> {
                Proposition prop = camaraAPI.requestPropositionById(propId);
                prop.getAuthors().addAll(camaraAPI.requestAuthorsByPropositionId(propId));
                prop.setProcessingHistory(camaraAPI.requestProcessingHistoryByPropositionId(propId));
                props.add(prop);
            });
            return props;
        }
    }

    public List<News> findNewsByPoliticianId(Long politicianId){
        Politician politician = politiciansRepository.findById(politicianId).orElseGet(() -> onlineDataUpdater.update(politicianId));
        return new ArrayList<>(googleNewsAPI.requestNews(politician.getName()));
    }

    public List<MonthlyExpense> findMonthlyExpensesByPoliticianId(Long politicianId, List<Integer> years, Integer lastMonths) {
        List<Expense> expenses;
        if(Objects.isNull(lastMonths)){
            if(Objects.isNull(years) || years.isEmpty()) {
                expenses = requestExpensesByCurrentLegislature(politicianId);
            } else {
                expenses = requestExpensesByYears(politicianId, years);
            }
        } else {
            expenses = requestExpensesByLastMonths(politicianId, lastMonths);
        }
        return monthlyExpenseService.computeMonthlyExpenses(expenses);
    }

    List<Expense> requestExpensesByYears(Long politicianId, List<Integer> years) {
        return camaraAPI.requestAllExpensesByPoliticianId(politicianId, null, years);
    }

    List<Expense> requestExpensesByCurrentLegislature(Long politicianId) {
        List<Integer> years = new ArrayList<>();
        Legislature legislature = camaraAPI.requestCurrentLegislature();
        int startYear = legislature.getStartDate().getYear();
        int legislatureDurationYears = 4;
        for (int i = 0; i < legislatureDurationYears; i++) {
            years.add(startYear + i);
        }
        return camaraAPI.requestAllExpensesByPoliticianId(politicianId, null, years);
    }

    List<Expense> requestExpensesByLastMonths(Long politicianId, Integer lastMonths) {
        List<Expense> expenses = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (int i = 0; i < lastMonths; i++) {
            LocalDate ld = now.minusMonths(i);
            int month = ld.getMonthValue();
            int year = ld.getYear();
            expenses.addAll(camaraAPI.requestAllExpensesByPoliticianId(politicianId, Arrays.asList(month), Arrays.asList(year)));
        }
        return expenses;
    }

    public List<Profile> findProfilesByName(String name){
        return camaraAPI.requestProfilesByNameAndLegislatureId(name, camaraAPI.requestCurrentLegislature().getId());
    }
}
