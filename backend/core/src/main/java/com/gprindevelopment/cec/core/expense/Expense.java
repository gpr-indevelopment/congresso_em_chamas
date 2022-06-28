package com.gprindevelopment.cec.core.expense;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.gprindevelopment.dominio.Despesa;
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

    private Long documentCode;

    private String documentNumber;

    @Column(name = "expense_value")
    private double value;

    private YearMonth yearMonth;

    private String type;

    private String provider;

    private String documentUrl;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private MonthlyExpense monthlyExpense;

    public Expense() {
    }

    public Expense(Despesa despesa) {
        this.yearMonth = YearMonth.of(despesa.getAno(), despesa.getMes());
        this.documentCode = despesa.getCodDocumento();
        this.documentNumber = despesa.getNumDocumento();
        this.documentUrl = despesa.getUrlDocumento() != null ? despesa.getUrlDocumento().toString() : null;
        this.provider = despesa.getCnpjCpfFornecedor();
        this.type = despesa.getTipoDespesa();
        this.value = despesa.getValorDocumento();
    }
}
