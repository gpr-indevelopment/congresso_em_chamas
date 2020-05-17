package com.devindev.congressoemchamas.data.expenses;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class MonthlyExpense {

    @Getter
    private Integer month;

    @Getter
    private Integer year;

    @Getter
    private double value = 0;

    @Getter
    private List<Expense> expenses = new ArrayList<>();

    public MonthlyExpense(Integer month, Integer year) {
        this.month = month;
        this.year = year;
    }

    public void build(){
        this.value = 0;
        expenses.forEach(expense -> addToValue(expense.getValue()));
    }

    public void addToValue(double increment){
        value+=increment;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MonthlyExpense){
            MonthlyExpense castObj = (MonthlyExpense) obj;
            return (castObj.month == this.month && castObj.year == this.year);
        } else {
            return super.equals(obj);
        }
    }
}
