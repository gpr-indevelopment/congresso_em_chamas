package com.devindev.congressoemchamas.batch.writer;

import com.devindev.congressoemchamas.data.legislature.Legislature;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class LegislatureWriter implements ItemWriter<Legislature> {

    private StepExecution stepExecution;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public void write(List<? extends Legislature> items) throws Exception {

    }
}
