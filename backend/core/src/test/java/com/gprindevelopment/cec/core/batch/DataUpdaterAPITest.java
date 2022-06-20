package com.gprindevelopment.cec.core.batch;

import com.gprindevelopment.cec.core.expense.Expense;
import com.gprindevelopment.cec.core.externalapi.camara.CamaraAPI;
import com.gprindevelopment.cec.core.externalapi.camara.Legislature;
import com.gprindevelopment.cec.core.politician.Politician;
import com.gprindevelopment.cec.core.politician.Profile;
import com.gprindevelopment.cec.core.proposition.Processing;
import com.gprindevelopment.cec.core.proposition.Proposition;
import com.gprindevelopment.cec.core.util.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DataUpdaterAPITest {

    @InjectMocks
    private DataUpdaterAPI dataUpdaterAPI;

    @Mock
    private CamaraAPI camaraAPI;

    private StopWatch stopWatch;

    @BeforeEach
    public void startStopWatch() {
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    @AfterEach
    public void stopAndAssertStopWatch() {
        stopWatch.stop();
        assertThat(stopWatch.getLastTaskTimeMillis()).isGreaterThan(4995);
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
