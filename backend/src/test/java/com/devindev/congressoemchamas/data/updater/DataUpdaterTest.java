package com.devindev.congressoemchamas.data.updater;

import com.devindev.congressoemchamas.TestUtils;
import com.devindev.congressoemchamas.batch.DataUpdaterAPI;
import com.devindev.congressoemchamas.batch.DataUpdaterConfig;
import com.devindev.congressoemchamas.data.expenses.Expense;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.processing.Processing;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.exceptions.DataUpdateException;
import com.devindev.congressoemchamas.service.MonthlyExpenseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DataUpdaterTest {

    @InjectMocks
    private DataUpdater dataUpdater;

    @Mock
    private DataUpdaterAPI dataUpdaterAPI;

    @Spy
    private MonthlyExpenseService monthlyExpenseService;

    @Mock
    private DataUpdaterConfig dataUpdaterConfig;

    @Test
    public void updateExpenses_validPoliticianAndLegislatureNullDelay_expensesUpdated() {
        // given
        Politician pol = TestUtils.generateRandomPolitician();
        Legislature leg = TestUtils.generateRandomLegislature();
        leg.setStartDate(YearMonth.of(2019, 2));
        leg.setEndDate(YearMonth.of(2023, 1));
        Expense exp1 = TestUtils.generateRandomExpense();
        Expense exp2 = TestUtils.generateRandomExpense();
        Expense exp3 = TestUtils.generateRandomExpense();
        // when
        when(dataUpdaterAPI.requestAllExpensesByPoliticianId(pol.getId(), null, Arrays.asList(2019, 2020, 2021, 2022)))
                .thenReturn(Arrays.asList(exp1, exp2, exp3));
        // then
        dataUpdater.updateExpenses(pol, leg);
        assertThat(pol.getMonthlyExpenses().isEmpty()).isFalse();
        assertThat(pol.getMonthlyExpenses().size()).isEqualTo(1);
        assertThat(pol.getMonthlyExpenses().get(0).getExpenses()).containsExactly(exp1, exp2, exp3);
        assertThat(pol.getMonthlyExpenses().get(0).getPolitician()).isEqualTo(pol);
        assertThat(pol.getMonthlyExpenses().get(0).getLegislature()).isEqualTo(leg);
    }

    @Test
    public void updateExpenses_nullPolitician_throwsDataUpdateException() {
        try {
            // then
            dataUpdater.updateExpenses(null, new Legislature());
            throw new AssertionError();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(DataUpdateException.class);
        }
    }

    @Test
    public void updateExpenses_nullLegislature_throwsDataUpdateException() {
        try {
            // then
            dataUpdater.updateExpenses(new Politician(), null);
            throw new AssertionError();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(DataUpdateException.class);
        }
    }

    @Test
    public void updatePropositions_validPolitician_updatesPropositions() {
        // given
        Politician pol = TestUtils.generateRandomPolitician();
        Proposition prop1 = TestUtils.generateRandomProposition();
        Proposition prop2 = TestUtils.generateRandomProposition();
        Proposition prop3 = TestUtils.generateRandomProposition();
        Processing process1 = TestUtils.generateRandomProcessing();
        Processing process2 = TestUtils.generateRandomProcessing();
        Processing process3 = TestUtils.generateRandomProcessing();
        // when
        when(dataUpdaterAPI.requestPropositionIdsByPoliticianId(pol.getId())).thenReturn(Arrays.asList(1L, 2L, 3L));
        when(dataUpdaterAPI.requestPropositionById(1L)).thenReturn(prop1);
        when(dataUpdaterAPI.requestPropositionById(2L)).thenReturn(prop2);
        when(dataUpdaterAPI.requestPropositionById(3L)).thenReturn(prop3);
        when(dataUpdaterAPI.requestAuthorsByPropositionId(1L)).thenReturn(Collections.singletonList("Author1"));
        when(dataUpdaterAPI.requestAuthorsByPropositionId(2L)).thenReturn(Collections.singletonList("Author2"));
        when(dataUpdaterAPI.requestAuthorsByPropositionId(3L)).thenReturn(Collections.singletonList("Author3"));
        when(dataUpdaterAPI.requestProcessingHistoryByPropositionId(1L)).thenReturn(Collections.singletonList(process1));
        when(dataUpdaterAPI.requestProcessingHistoryByPropositionId(2L)).thenReturn(Collections.singletonList(process2));
        when(dataUpdaterAPI.requestProcessingHistoryByPropositionId(3L)).thenReturn(Collections.singletonList(process3));
        // then
        dataUpdater.updatePropositions(pol);
        assertThat(pol.getPropositions()).containsExactly(prop1, prop2, prop3);
        assertThat(prop1.getAuthors()).containsExactly("Author1");
        assertThat(prop2.getAuthors()).containsExactly("Author2");
        assertThat(prop3.getAuthors()).containsExactly("Author3");
        assertThat(prop1.getProcessingHistory()).containsExactly(process1);
        assertThat(prop2.getProcessingHistory()).containsExactly(process2);
        assertThat(prop3.getProcessingHistory()).containsExactly(process3);
    }

    @Test
    public void updatePropositions_nullPolitician_throwsDataUpdateException() {
        try {
            // then
            dataUpdater.updatePropositions(null);
            throw new AssertionError();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(DataUpdateException.class);
        }
    }

    @Test
    public void updatePropositions_noPropositionIdsFound_setsEmptyListToPolitician() {
        // given
        Politician pol = TestUtils.generateRandomPolitician();
        // when
        when(dataUpdaterAPI.requestPropositionIdsByPoliticianId(pol.getId())).thenReturn(new ArrayList<>());
        // then
        dataUpdater.updatePropositions(pol);
        assertThat(pol.getPropositions()).isNotNull();
        assertThat(pol.getPropositions().isEmpty()).isTrue();
    }

    @Test
    public void updateTwitterUsername_validPolitician_usernameSet() {
        // given
        Politician pol = TestUtils.generateRandomPolitician();
        // when
        when(dataUpdaterAPI.requestTwitterUsernameByName(pol.getName())).thenReturn("testUsername");
        // then
        dataUpdater.updateTwitterUsername(pol);
        assertThat(pol.getTwitterUsername()).isEqualTo("testUsername");
    }

    @Test
    public void updateTwitterUsername_nullPolitician_throwsDataUpdateException() {
        try {
            // then
            dataUpdater.updateTwitterUsername(null);
            throw new AssertionError();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(DataUpdateException.class);
        }
    }

    @Test
    public void eligibleForUpdate_politicianIsOlderThan7Days_returnsTrue() {
        // given
        Politician pol = TestUtils.generateRandomPolitician();
        LocalDateTime tenDaysAgo = LocalDateTime.now().minusDays(10);
        pol.setUpdatedAt(Timestamp.valueOf(tenDaysAgo));
        // when
        when(dataUpdaterConfig.getPoliticianExpirationTimeDays()).thenReturn(7);
        // then
        assertThat(dataUpdater.eligibleForUpdate(pol)).isTrue();
    }

    @Test
    public void eligibleForUpdate_politicianIsNotOlderThan7Days_returnsTrue() {
        // given
        Politician pol = TestUtils.generateRandomPolitician();
        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(6);
        pol.setUpdatedAt(Timestamp.valueOf(threeDaysAgo));
        // when
        when(dataUpdaterConfig.getPoliticianExpirationTimeDays()).thenReturn(7);
        // then
        assertThat(dataUpdater.eligibleForUpdate(pol)).isFalse();
    }

    @Test
    public void eligibleForUpdate_nullPolitician_throwsDataUpdateException() {
        try {
            // then
            dataUpdater.eligibleForUpdate(null);
            throw new AssertionError();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(DataUpdateException.class);
        }
    }
}
