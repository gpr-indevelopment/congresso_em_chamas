package com.devindev.congressoemchamas.batch.tasklet;

import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.externalapi.camara.CamaraAPI;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class LoadCurrentLegislatureTasklet implements Tasklet {

    @Autowired
    private CamaraAPI camaraAPI;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Legislature currentLegislature = camaraAPI.requestCurrentLegislature();
        contribution.getStepExecution().getExecutionContext().put("currentLegislature", currentLegislature);
        return RepeatStatus.FINISHED;
    }
}
