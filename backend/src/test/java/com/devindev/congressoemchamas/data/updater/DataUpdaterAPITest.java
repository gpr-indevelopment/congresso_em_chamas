package com.devindev.congressoemchamas.data.updater;

import com.devindev.congressoemchamas.TestUtils;
import com.devindev.congressoemchamas.data.expenses.Expense;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.processing.Processing;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import com.devindev.congressoemchamas.externalapi.twitter.TwitterAPI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DataUpdaterAPITest {

    @InjectMocks
    private DataUpdaterAPI dataUpdaterAPI;

    @Mock
    private CamaraAPI camaraAPI;

    @Mock
    private TwitterAPI twitterAPI;

    private StopWatch stopWatch;

    @Before
    public void startStopWatch() {
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    @After
    public void stopAndAssertStopWatch() {
        stopWatch.stop();
        assertThat(stopWatch.getLastTaskTimeMillis()).isGreaterThan(4999);
    }

    @Test
    public void requestAllExpensesByPoliticianId_exceptionThrown_waits5SecondsAndReturns() {
        // given
        Long polId = 1L;
        List<Integer> months = Arrays.asList(1, 2, 3);
        List<Integer> years = Arrays.asList(4, 5, 6);
        Expense expense = TestUtils.generateRandomExpense();
        // when
        when(camaraAPI.requestAllExpensesByPoliticianId(polId, months, years)).thenThrow(new RuntimeException("Expected error"))
                .thenReturn(Collections.singletonList(expense));
        // then
        List<Expense> output = dataUpdaterAPI.requestAllExpensesByPoliticianId(polId, months, years);
        assertThat(output).containsExactly(expense);
    }

    @Test
    public void requestPropositionIdsByPoliticianId_exceptionThrown_waits5SecondsAndReturns() {
        // given
        Long polId = 1L;
        // when
        when(camaraAPI.requestPropositionIdsByPoliticianId(polId)).thenThrow(new RuntimeException("Expected exception")).thenReturn(Collections.singletonList(2L));
        // then
        assertThat(dataUpdaterAPI.requestPropositionIdsByPoliticianId(polId)).containsExactly(2L);
    }

    @Test
    public void requestPropositionById_exceptionThrown_waits5SecondsAndReturns() {
        // given
        Long propositionId = 1L;
        Proposition prop = TestUtils.generateRandomProposition();
        // when
        when(camaraAPI.requestPropositionById(propositionId)).thenThrow(new RuntimeException("Expected exception")).thenReturn(prop);
        // then
        assertThat(dataUpdaterAPI.requestPropositionById(propositionId)).isEqualTo(prop);
    }

    @Test
    public void requestAuthorsByPropositionId_exceptionThrown_waits5SecondsAndReturns() {
        // given
        Long propId = 1L;
        List<String> authors = Collections.singletonList("testAuthor");
        // when
        when(camaraAPI.requestAuthorsByPropositionId(propId)).thenThrow(new RuntimeException("Expected exception")).thenReturn(authors);
        // then
        assertThat(dataUpdaterAPI.requestAuthorsByPropositionId(propId)).isEqualTo(authors);
    }

    @Test
    public void requestProcessingHistoryByPropositionId_exceptionThrown_waits5SecondsAndReturns() {
        // given
        Long propId = 5L;
        List<Processing> processings = Collections.singletonList(TestUtils.generateRandomProcessing());
        // when
        when(camaraAPI.requestProcessingHistoryByPropositionId(propId)).thenThrow(new RuntimeException("Expected exception")).thenReturn(processings);
        // then
        assertThat(dataUpdaterAPI.requestProcessingHistoryByPropositionId(propId)).isEqualTo(processings);
    }

    @Test
    public void requestTwitterUsernameByName_exceptionThrown_waits5SecondsAndReturns() {
        // given
        String name = "testName";
        String twitterUsername = "twitterUsername";
        // when
        when(twitterAPI.requestTwitterUsernameByName(name)).thenThrow(new RuntimeException("Expected exception")).thenReturn(twitterUsername);
        // then
        assertThat(dataUpdaterAPI.requestTwitterUsernameByName(name)).isEqualTo(twitterUsername);
    }

    @Test
    public void requestPoliticianById_exceptionThrown_waits5SecondsAndReturns() {
        // given
        Long politicianId = 1L;
        Politician expectedPol = TestUtils.generateRandomPolitician();
        // when
        when(camaraAPI.requestPoliticianById(politicianId)).thenThrow(new RuntimeException("Expected exception")).thenReturn(expectedPol);
        // then
        assertThat(dataUpdaterAPI.requestPoliticianById(politicianId)).isEqualTo(expectedPol);
    }

    @Test
    public void requestCurrentLegislature_exceptionThrown_waits5SecondsAndReturns() {
        // given
        Legislature expectedLegislature = TestUtils.generateRandomLegislature();
        // when
        when(camaraAPI.requestCurrentLegislature()).thenThrow(new RuntimeException("Expected exception")).thenReturn(expectedLegislature);
        // then
        assertThat(dataUpdaterAPI.requestCurrentLegislature()).isEqualTo(expectedLegislature);
    }

    @Test
    public void requestProfilesByNameAndLegislatureId_exceptionThrown_waits5SecondsAndReturns() {
        // given
        String name = "testName";
        Long legId = 1L;
        List<Profile> expectedOutput = Collections.singletonList(TestUtils.generateRandomProfile());
        // when
        when(camaraAPI.requestProfilesByNameAndLegislatureId(name, legId)).thenThrow(new RuntimeException("Expected exception")).thenReturn(expectedOutput);
        // then
        assertThat(dataUpdaterAPI.requestProfilesByNameAndLegislatureId(name, legId)).isEqualTo(expectedOutput);
    }
}
