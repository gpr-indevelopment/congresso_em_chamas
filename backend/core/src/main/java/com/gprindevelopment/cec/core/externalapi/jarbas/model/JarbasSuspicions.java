package com.gprindevelopment.cec.core.externalapi.jarbas.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Map;

@Data
@JsonDeserialize(using = JarbasSuspicionsDeserializer.class)
public class JarbasSuspicions {

    private JarbasSuspicion mealPriceOutlier = new JarbasSuspicion("meal_price_outlier", "Preço de refeição muito incomum");

    private JarbasSuspicion overMonthlySubquotaLimit = new JarbasSuspicion("over_monthly_subquota_limit", "Extrapolou limita da (sub)quota");

    private JarbasSuspicion suspiciousTraveledSpeedDay = new JarbasSuspicion("suspicious_traveled_speed_day", "Muitas despesas em diferentes cidades no mesmo dia");

    private JarbasSuspicion invalidCnpjOrCpf = new JarbasSuspicion("invalid_cnpj_cpf", "CPF ou CNPJ inválidos");

    private JarbasSuspicion electionExpenses = new JarbasSuspicion("election_expenses", "Gasto com campanha eleitoral");

    private JarbasSuspicion irregularCompaniesClassifier = new JarbasSuspicion("irregular_companies_classifier", "CNPJ irregular");

    private Map<String, JarbasSuspicion> keyToSuspicion = Map.of(
            "meal_price_outlier", mealPriceOutlier,
            "over_monthly_subquota_limit", overMonthlySubquotaLimit,
            "suspicious_traveled_speed_day", suspiciousTraveledSpeedDay,
            "invalid_cnpj_cpf", invalidCnpjOrCpf,
            "election_expenses", electionExpenses,
            "irregular_companies_classifier", irregularCompaniesClassifier);
}
