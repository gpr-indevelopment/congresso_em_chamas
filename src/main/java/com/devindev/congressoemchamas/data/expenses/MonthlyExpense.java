package com.devindev.congressoemchamas.data.expenses;

import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class MonthlyExpense {

    @Id
    @GeneratedValue
    private Long id;

    private YearMonth yearMonth;

    private double value = 0;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Politician politician;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @JsonManagedReference
    private Legislature legislature;

    @OneToMany(mappedBy = "monthlyExpense", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Expense> expenses = new ArrayList<>();

    public static List<MonthlyExpense> build(List<Expense> expenses) {
        List<MonthlyExpense> monthlyExpenses = new ArrayList<>();
        Map<YearMonth, MonthlyExpense> dateToMonthlyExpenses = new HashMap<>();
        expenses.forEach(expense -> {
            YearMonth currentYearMonth = expense.getYearMonth();
            dateToMonthlyExpenses.putIfAbsent(currentYearMonth, new MonthlyExpense(currentYearMonth));
            MonthlyExpense monthlyExpense = dateToMonthlyExpenses.get(currentYearMonth);
            monthlyExpense.getExpenses().add(expense);
            expense.setMonthlyExpense(monthlyExpense);
        });
        monthlyExpenses.addAll(dateToMonthlyExpenses.values());
        monthlyExpenses.forEach(monthlyExpense -> monthlyExpense.build());
        return monthlyExpenses;
    }

    public MonthlyExpense() {
    }

    public MonthlyExpense(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    private void build() {
        this.value = 0;
        expenses.forEach(expense -> addToValue(expense.getValue()));
    }

    private void addToValue(double increment) {
        value += increment;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MonthlyExpense) {
            MonthlyExpense castObj = (MonthlyExpense) obj;
            return (this.getYearMonth().equals(castObj.getYearMonth()));
        } else {
            return super.equals(obj);
        }
    }
}
