package com.devindev.congressoemchamas.data.expenses;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Expense {

    private String documentNumber;

    private double value;

    private Timestamp date;

    private String type;

    private String provider;

    private String documentUrl;
}
