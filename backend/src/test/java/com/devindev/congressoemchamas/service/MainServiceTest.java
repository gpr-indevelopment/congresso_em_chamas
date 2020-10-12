package com.devindev.congressoemchamas.service;

import com.devindev.congressoemchamas.TestUtils;
import com.devindev.congressoemchamas.batch.DataUpdateScheduler;
import com.devindev.congressoemchamas.batch.DataUpdaterConfig;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainServiceTest {

    @InjectMocks
    private MainService mainService;

    @Mock
    private MainRepository politiciansRepository;

    @Mock
    private CamaraAPI camaraAPI;

    @Mock
    private GoogleNewsAPI googleNewsAPI;

    @Mock
    private MonthlyExpenseService monthlyExpenseService;

    @Mock
    private DataUpdaterConfig dataUpdaterConfig;

    @Mock
    private DataUpdateScheduler dataUpdateScheduler;

    @Captor
    private ArgumentCaptor<List<Integer>> monthsCaptor;

    @Captor
    private ArgumentCaptor<List<Integer>> yearsCaptor;

    @Test
    public void eligibleForUpdate_politicianIsOlderThan7Days_returnsTrue() {
        // given
        Politician pol = TestUtils.generateRandomPolitician();
        LocalDateTime tenDaysAgo = LocalDateTime.now().minusDays(10);
        pol.setUpdatedAt(Timestamp.valueOf(tenDaysAgo));
        // when
        when(dataUpdaterConfig.getPoliticianExpirationTimeDays()).thenReturn(7);
        // then
        assertThat(mainService.isEligibleForUpdate(pol)).isTrue();
    }

    @Test
    public void eligibleForUpdate_politicianIsNotOlderThan7Days_returnsTrue() {
        // given
        Politician pol = TestUtils.generateRandomPolitician();
        LocalDateTime sixDaysAgo = LocalDateTime.now().minusDays(6);
        pol.setUpdatedAt(Timestamp.valueOf(sixDaysAgo));
        // when
        when(dataUpdaterConfig.getPoliticianExpirationTimeDays()).thenReturn(7);
        // then
        assertThat(mainService.isEligibleForUpdate(pol)).isFalse();
    }

    @Test
    public void findById_receivedValidPoliticianId_returnsPolitician() {
        // given
        Long validPoliticianId = 1L;
        Politician expectedPol = TestUtils.generateRandomPolitician();
        LocalDateTime sixDaysAgo = LocalDateTime.now().minusDays(6);
        expectedPol.setUpdatedAt(Timestamp.valueOf(sixDaysAgo));
        // when
        when(politiciansRepository.findById(validPoliticianId)).thenReturn(Optional.of(expectedPol));
        doNothing().when(dataUpdateScheduler).queuePolitician(expectedPol);
        // then
        Politician actualResponse = mainService.findById(validPoliticianId);
        assertThat(actualResponse).isEqualToComparingFieldByField(expectedPol);
    }

    @Test
    public void findPropositionsByPoliticianId_validPoliticianId_returnsPropositions() {
        // given
        Long validPoliticianId = 1L;
        Proposition prop1 = TestUtils.generateRandomProposition();
        Proposition prop2 = TestUtils.generateRandomProposition();
        // when
        when(politiciansRepository.findAllPropositionsByPolitician(validPoliticianId))
                .thenReturn(Arrays.asList(prop1, prop2));
        // then
        List<Proposition> actualResponse = mainService.findPropositionsByPoliticianId(validPoliticianId);
        assertThat(actualResponse.size()).isEqualTo(2);
        assertThat(actualResponse).containsExactly(prop1, prop2);
    }

    @Test
    public void findNewsByPoliticianId_validPoliticianId_returnsNews() {
        // given
        Long politicianId = 2L;
        Politician pol = TestUtils.generateRandomPolitician();
        pol.setName("testName");
        News expectedNews = TestUtils.generateRandomNews();
        // when
        when(politiciansRepository.findById(politicianId)).thenReturn(Optional.of(pol));
        when(googleNewsAPI.requestNews(pol.getName())).thenReturn(Collections.singletonList(expectedNews));
        // then
        List<News> actualOutput = mainService.findNewsByPoliticianId(politicianId);
        assertThat(actualOutput.size()).isEqualTo(1);
        assertThat(actualOutput.get(0)).isEqualTo(expectedNews);
        verify(politiciansRepository).findById(politicianId);
        verify(googleNewsAPI).requestNews(pol.getName());
    }

    @Test
    public void requestExpensesByLastMonths_sixLastMonths_requestExpensesFromLastMonths() {
        // given
        int lastMonths = 6;
        Long politicianId = 1L;
        Expense exp1 = TestUtils.generateRandomExpense();
        Expense exp2 = TestUtils.generateRandomExpense();
        LocalDate now = LocalDate.now();
        // when
        when(camaraAPI.requestAllExpensesByPoliticianId(anyLong(), monthsCaptor.capture(), anyList()))
                .thenReturn(Arrays.asList(exp1, exp2));
        // then
        List<Expense> actualResponse = mainService.requestExpensesByLastMonths(politicianId, lastMonths);
        assertThat(actualResponse.size()).isEqualTo(2 * lastMonths);
        assertThat(actualResponse).contains(exp1, exp2);
        List<Integer> inputMonths = monthsCaptor.getAllValues().stream().map(inputList -> inputList.get(0)).collect(Collectors.toList());
        assertThat(inputMonths).contains(now.getMonthValue(),
                now.minusMonths(1).getMonthValue(),
                now.minusMonths(2).getMonthValue(),
                now.minusMonths(3).getMonthValue(),
                now.minusMonths(4).getMonthValue(),
                now.minusMonths(5).getMonthValue());
    }

    @Test
    public void requestExpensesByLastMonths_oneLastMonth_requestExpensesFromCurrentMonth() {
        // given
        int lastMonths = 1;
        Long politicianId = 1L;
        Expense exp1 = TestUtils.generateRandomExpense();
        Expense exp2 = TestUtils.generateRandomExpense();
        LocalDate now = LocalDate.now();
        // when
        when(camaraAPI.requestAllExpensesByPoliticianId(anyLong(), monthsCaptor.capture(), yearsCaptor.capture()))
                .thenReturn(Arrays.asList(exp1, exp2));
        // then
        List<Expense> actualResponse = mainService.requestExpensesByLastMonths(politicianId, lastMonths);
        assertThat(actualResponse.size()).isEqualTo(2 * lastMonths);
        assertThat(actualResponse).contains(exp1, exp2);
        List<Integer> monthValues = monthsCaptor.getAllValues().stream().map(inputList -> inputList.get(0)).collect(Collectors.toList());
        List<Integer> yearValues = yearsCaptor.getAllValues().stream().map(inputList -> inputList.get(0)).collect(Collectors.toList());
        assertThat(monthValues).contains(now.getMonthValue());
        assertThat(yearValues).contains(now.getYear());
    }

    @Test
    public void findMonthlyExpensesByPoliticianId_hasLastMonths_requestExpensesFromLastMonths() {
        // given
        Integer lastMonths = 6;
        Long politicianId = 1L;
        Expense exp1 = TestUtils.generateRandomExpense();
        Expense exp2 = TestUtils.generateRandomExpense();
        // when
        when(camaraAPI.requestAllExpensesByPoliticianId(anyLong(), anyList(), anyList()))
                .thenReturn(Arrays.asList(exp1, exp2));
        // then
        List<MonthlyExpense> actualOutput = mainService.findMonthlyExpensesByPoliticianId(politicianId, null, lastMonths);
        verify(camaraAPI, times(6)).requestAllExpensesByPoliticianId(anyLong(), anyList(), anyList());
        assertThat(actualOutput).isNotNull();
    }

    @Test
    public void findMonthlyExpensesByPoliticianId_hasLastMonthsAndYears_lastMonthsTakesPrecedence() {
        // given
        Integer lastMonths = 6;
        Integer year = 2099;
        Long politicianId = 1L;
        Expense exp1 = TestUtils.generateRandomExpense();
        Expense exp2 = TestUtils.generateRandomExpense();
        // when
        when(camaraAPI.requestAllExpensesByPoliticianId(anyLong(), anyList(), yearsCaptor.capture()))
                .thenReturn(Arrays.asList(exp1, exp2));
        // then
        List<MonthlyExpense> actualOutput = mainService.findMonthlyExpensesByPoliticianId(politicianId, Collections.singletonList(year), lastMonths);
        List<Integer> yearValues = yearsCaptor.getAllValues().stream().map(inputList -> inputList.get(0)).collect(Collectors.toList());
        verify(camaraAPI, times(6)).requestAllExpensesByPoliticianId(anyLong(), anyList(), anyList());
        assertThat(actualOutput).isNotNull();
        assertThat(yearValues.contains(year)).isFalse();
    }

    @Test
    public void requestExpensesByYears_validYears_requestExpensesByYears() {
        // given
        Long politicianId = 1L;
        List<Integer> years = Arrays.asList(2050, 2051);
        Expense exp1 = TestUtils.generateRandomExpense();
        Expense exp2 = TestUtils.generateRandomExpense();
        // when
        when(camaraAPI.requestAllExpensesByPoliticianId(politicianId, null, years))
                .thenReturn(Arrays.asList(exp1, exp2));
        // then
        List<Expense> actualOutput = mainService.requestExpensesByYears(politicianId, years);
        assertThat(actualOutput.size()).isEqualTo(2);
        verify(camaraAPI).requestAllExpensesByPoliticianId(politicianId, null, years);
    }

    @Test
    public void findMonthlyExpensesByPoliticianId_validYearsAndNullLastMonths_requestByYears() {
        // given
        Long politicianId = 1L;
        List<Integer> years = Arrays.asList(2050, 2051);
        Expense exp1 = TestUtils.generateRandomExpense();
        Expense exp2 = TestUtils.generateRandomExpense();
        // when
        when(camaraAPI.requestAllExpensesByPoliticianId(politicianId, null, years))
                .thenReturn(Arrays.asList(exp1, exp2));
        when(monthlyExpenseService.computeMonthlyExpenses(anyList())).thenReturn(Collections.singletonList(new MonthlyExpense()));
        // then
        List<MonthlyExpense> actualOutput = mainService.findMonthlyExpensesByPoliticianId(politicianId, years, null);
        assertThat(actualOutput.size()).isEqualTo(1);
        verify(camaraAPI).requestAllExpensesByPoliticianId(politicianId, null, years);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void requestExpensesByCurrentLegislature_validPoliticianId_requestsByCurrentLegislature() {
        // given
        Long politicianId = 1L;
        Legislature legislature = new Legislature();
        legislature.setStartDate(YearMonth.of(2022, 12));
        legislature.setEndDate(YearMonth.of(2019, 2));
        Expense exp1 = TestUtils.generateRandomExpense();
        Expense exp2 = TestUtils.generateRandomExpense();
        // when
        when(camaraAPI.requestCurrentLegislature()).thenReturn(legislature);
        when(camaraAPI.requestAllExpensesByPoliticianId(anyLong(), nullable(List.class), yearsCaptor.capture()))
                .thenReturn(Arrays.asList(exp1, exp2));
        // then
        List<Expense> actualOutput = mainService.requestExpensesByCurrentLegislature(politicianId);
        assertThat(actualOutput).contains(exp1, exp2);
        yearsCaptor.getValue().containsAll(Arrays.asList(2019, 2020, 2021, 2022));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void findMonthlyExpensesByPoliticianId_nullLastMonthsAndYears_requestByCurrentLegislature() {
        // given
        Long politicianId = 1L;
        Legislature legislature = new Legislature();
        legislature.setStartDate(YearMonth.of(2022, 12));
        legislature.setEndDate(YearMonth.of(2019, 2));
        Expense exp1 = TestUtils.generateRandomExpense();
        Expense exp2 = TestUtils.generateRandomExpense();
        // when
        when(camaraAPI.requestCurrentLegislature()).thenReturn(legislature);
        when(camaraAPI.requestAllExpensesByPoliticianId(anyLong(), nullable(List.class), nullable(List.class)))
                .thenReturn(Arrays.asList(exp1, exp2));
        when(monthlyExpenseService.computeMonthlyExpenses(anyList())).thenReturn(Collections.singletonList(new MonthlyExpense()));
        // then
        List<MonthlyExpense> actualOutput = mainService.findMonthlyExpensesByPoliticianId(politicianId, null, null);
        verify(camaraAPI).requestAllExpensesByPoliticianId(anyLong(), nullable(List.class), nullable(List.class));
        assertThat(actualOutput.isEmpty()).isFalse();
    }

    @Test
    public void findProfilesByName_validName_returnsPolitician() {
        // given
        String inputName = "testName";
        Legislature leg = new Legislature();
        leg.setId(2L);
        Profile expectedProfile = new Profile();
        expectedProfile.setName("testProfile");
        // when
        when(camaraAPI.requestCurrentLegislature()).thenReturn(leg);
        when(camaraAPI.requestProfilesByNameAndLegislatureId(inputName, leg.getId())).thenReturn(Collections.singletonList(expectedProfile));
        // then
        List<Profile> actualOutput = mainService.findProfilesByName(inputName);
        assertThat(actualOutput.size()).isEqualTo(1);
        assertThat(actualOutput.get(0)).isEqualTo(expectedProfile);
    }

}
