package com.gprindevelopment.cec.despesas;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.YearMonth;

@Entity
@Getter @Setter
public class Expense {

    @Id
    @GeneratedValue
    private Long id;

    private String documentNumber;

    private double value;

    private YearMonth yearMonth;

    private String type;

    private String provider;

    private String documentUrl;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private MonthlyExpense monthlyExpense;
}
