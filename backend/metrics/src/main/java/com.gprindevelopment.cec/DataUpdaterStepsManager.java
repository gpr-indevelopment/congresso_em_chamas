package com.gprindevelopment.cec;

import com.gprindevelopment.cec.expense.Expense;
import com.gprindevelopment.cec.politician.Politician;
import com.gprindevelopment.cec.proposition.Proposition;
import com.gprindevelopment.cec.rpw.UpdateExpensesRPW;
import com.gprindevelopment.cec.rpw.UpdatePoliticianRPW;
import com.gprindevelopment.cec.rpw.UpdatePropositionsRPW;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DataUpdaterStepsManager {

    @Autowired
    private UpdatePoliticianRPW updatePoliticianRPW;

    @Autowired
    private UpdateExpensesRPW updateExpensesRPW;

    @Autowired
    private UpdatePropositionsRPW updatePropositionsRPW;

    @Autowired
    private StepBuilderFactory factory;

    @Bean
    public Step updatePoliticianStep() {
        return factory.get("updatePoliticianStep")
                .<Politician, Politician>chunk(1)
                .reader(updatePoliticianRPW)
                .processor(updatePoliticianRPW)
                .writer(updatePoliticianRPW)
                .build();
    }

    @Bean
    public Step updateExpensesStep() {
        return factory.get("updateExpensesStep")
                .<List<Expense>, Politician>chunk(1)
                .reader(updateExpensesRPW)
                .processor(updateExpensesRPW)
                .writer(updateExpensesRPW)
                .build();
    }

    @Bean
    public Step updatePropositionsStep() {
        return factory.get("updatePropositionsStep")
                .<List<Proposition>, Politician>chunk(1)
                .reader(updatePropositionsRPW)
                .processor(updatePropositionsRPW)
                .writer(updatePropositionsRPW)
                .build();
    }
}
