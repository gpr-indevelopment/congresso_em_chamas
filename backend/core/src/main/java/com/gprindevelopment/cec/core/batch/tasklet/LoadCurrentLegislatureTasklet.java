package com.gprindevelopment.cec.core.batch.tasklet;

import com.gprindevelopment.cec.core.externalapi.camara.CamaraClientFacade;
import com.gprindevelopment.cec.core.politician.Legislature;
import org.springframework.batch.core.StepContribution;
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
    private CamaraClientFacade camaraAPI;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Legislature currentLegislature = camaraAPI.requestCurrentLegislature();
        contribution.getStepExecution().getExecutionContext().put("currentLegislature", currentLegislature);
        return RepeatStatus.FINISHED;
    }
}
