package com.gprindevelopment.cec.core.politician;

import com.gprindevelopment.cec.core.batch.PoliticianDataUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashSet;

@Component
@RequiredArgsConstructor
public class DailyAccessedPoliticianDataUpdateScheduler {

    private final PoliticianDataUpdater politicianDataUpdater;

    private LinkedHashSet<Long> queueIds = new LinkedHashSet<>();

    public boolean addToQueue(Long politicianId) {
        return queueIds.add(politicianId);
    }

    @Scheduled(cron = "0 0 1 * * MON-SAT")
    public void executeJobs() throws Exception {
        Iterator<Long> iterator = queueIds.iterator();
        while(iterator.hasNext()) {
            Long current = iterator.next();
            politicianDataUpdater.batchUpdate(current);
            iterator.remove();
        }
    }
}
