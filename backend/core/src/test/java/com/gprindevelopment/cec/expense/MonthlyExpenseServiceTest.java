package com.gprindevelopment.cec.expense;

import com.gprindevelopment.cec.util.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MonthlyExpenseServiceTest {

    @InjectMocks
    private MonthlyExpenseService monthlyExpenseService;

    private List<Expense> buildTestExpenses() {
        Expense exp1 = TestUtils.generateRandomExpense();
        exp1.setYearMonth(YearMonth.of(2020, 1));
        exp1.setValue(200);
        Expense exp2 = TestUtils.generateRandomExpense();
        exp2.setYearMonth(YearMonth.of(2020, 1));
        exp2.setValue(150);
        Expense exp3 = TestUtils.generateRandomExpense();
        exp3.setYearMonth(YearMonth.of(2021, 2));
        exp3.setValue(36);
        Expense exp4 = TestUtils.generateRandomExpense();
        exp4.setYearMonth(YearMonth.of(2021, 2));
        exp4.setValue(98);
        Expense exp5 = TestUtils.generateRandomExpense();
        exp5.setYearMonth(YearMonth.of(2020, 4));
        exp5.setValue(112);
        Expense exp6 = TestUtils.generateRandomExpense();
        exp6.setYearMonth(YearMonth.of(2020, 2));
        exp6.setValue(12);
        Expense exp7 = TestUtils.generateRandomExpense();
        exp7.setYearMonth(YearMonth.of(2020, 9));
        exp7.setValue(76);
        return Arrays.asList(exp1, exp2, exp3, exp4, exp5, exp6, exp7);
    }

    @Test
    public void sortExpensesByYearMonth_validExpenses_returnsSortedMap() {
        // then
        List<Expense> inputExpenses = buildTestExpenses();
        Map<YearMonth, List<Expense>> outputMap = monthlyExpenseService.sortExpensesByYearMonth(inputExpenses);
        Assertions.assertThat(outputMap.size()).isEqualTo(5);
        Assertions.assertThat(outputMap.keySet())
                .contains(
                        YearMonth.of(2020, 1),
                        YearMonth.of(2021, 2),
                        YearMonth.of(2020, 4),
                        YearMonth.of(2020, 2),
                        YearMonth.of(2020, 9));
        List<Expense> outputExpenses = new ArrayList<>();
        outputMap.values().forEach(value -> outputExpenses.addAll(value));
        Assertions.assertThat(outputExpenses).containsOnly((Expense[]) inputExpenses.toArray(new Expense[]{}));
    }

    private void assertMonthlyExpenses(List<MonthlyExpense> monthlyExpenses) {
        Assertions.assertThat(monthlyExpenses).size().isEqualTo(5);
        monthlyExpenses.forEach(monthlyExpense -> {
            switch ((int) monthlyExpense.getValue()) {
                case 350: {
                    assertThat(monthlyExpense.getYearMonth().equals(YearMonth.of(2020, 1)));
                    break;
                }
                case 134: {
                    assertThat(monthlyExpense.getYearMonth().equals(YearMonth.of(2021, 2)));
                    break;
                }
                case 112: {
                    assertThat(monthlyExpense.getYearMonth().equals(YearMonth.of(2020, 4)));
                    break;
                }
                case 12: {
                    assertThat(monthlyExpense.getYearMonth().equals(YearMonth.of(2020, 2)));
                    break;
                }
                default: {
                    assertThat(monthlyExpense.getYearMonth().equals(YearMonth.of(2020, 9)));
                    break;
                }
            }
        });
    }

    @Test
    public void buildMonthlyExpenses_validSortedMap_returnsMonthlyExpenses() {
        // given
        Map<YearMonth, List<Expense>> inputMap = monthlyExpenseService.sortExpensesByYearMonth(buildTestExpenses());
        // then
        List<MonthlyExpense> monthlyExpenses = monthlyExpenseService.buildMonthlyExpenses(inputMap);
        assertMonthlyExpenses(monthlyExpenses);
    }

    @Test
    public void computeMonthlyExpenses_validExpenses_returnsComputedMonthlyExpenses() {
        // given
        List<Expense> inputExpenses = buildTestExpenses();
        // then
        List<MonthlyExpense> monthlyExpenses = monthlyExpenseService.computeMonthlyExpenses(inputExpenses);
        assertMonthlyExpenses(monthlyExpenses);
    }
}
