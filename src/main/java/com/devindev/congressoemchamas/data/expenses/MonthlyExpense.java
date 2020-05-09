package com.devindev.congressoemchamas.data.expenses;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MonthlyExpense {

    private int month;

    private int year;

    private double value = 0;

    private List<Expense> expenses;

    public MonthlyExpense(int month, int year, List<Expense> expenses) {
        this.month = month;
        this.year = year;
        this.expenses = expenses;
        expenses.forEach(expense -> {
            addToValue(expense.getValue());
        });
    }

    public void addToValue(double increment){
        value+=increment;
    }
}
