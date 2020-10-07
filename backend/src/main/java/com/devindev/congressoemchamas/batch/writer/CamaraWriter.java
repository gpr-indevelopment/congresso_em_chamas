package com.devindev.congressoemchamas.batch.writer;

import com.devindev.congressoemchamas.data.politician.Politician;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class CamaraWriter implements ItemWriter<Politician> {

    @Override
    public void write(List<? extends Politician> items) throws Exception {
        System.out.println("teste");
    }
}
