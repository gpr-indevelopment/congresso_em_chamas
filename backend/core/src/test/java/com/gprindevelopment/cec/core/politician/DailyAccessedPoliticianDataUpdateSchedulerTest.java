package com.gprindevelopment.cec.core.politician;

import com.gprindevelopment.cec.core.batch.PoliticianDataUpdater;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class DailyAccessedPoliticianDataUpdateSchedulerTest {

    private final DailyAccessedPoliticianDataUpdateScheduler scheduler;

    private final PoliticianDataUpdater politicianDataUpdater;

    public DailyAccessedPoliticianDataUpdateSchedulerTest() {
        politicianDataUpdater = mock(PoliticianDataUpdater.class);
        scheduler = new DailyAccessedPoliticianDataUpdateScheduler(politicianDataUpdater);
    }

    @Test
    public void executeJobs_twoPoliticiansOnQueue_politiciansUpdatedAndQueueReset() throws Exception{
        // given
        Long politician1 = 1L;
        Long politician2 = 2L;
        scheduler.addToQueue(politician1);
        scheduler.addToQueue(politician2);
        // mock
        doNothing().when(politicianDataUpdater).batchUpdate(politician1);
        doNothing().when(politicianDataUpdater).batchUpdate(politician2);
        // then
        scheduler.executeJobs();
        verify(politicianDataUpdater).batchUpdate(politician1);
        verify(politicianDataUpdater).batchUpdate(politician2);
        verify(politicianDataUpdater, times(2)).batchUpdate(anyLong());

        // given 2
        Long politician3 = 3L;
        scheduler.addToQueue(politician3);
        // when 2
        doNothing().when(politicianDataUpdater).batchUpdate(politician3);
        // then 2
        scheduler.executeJobs();
        verify(politicianDataUpdater).batchUpdate(politician3);
        verify(politicianDataUpdater, times(3)).batchUpdate(anyLong());
    }
}
