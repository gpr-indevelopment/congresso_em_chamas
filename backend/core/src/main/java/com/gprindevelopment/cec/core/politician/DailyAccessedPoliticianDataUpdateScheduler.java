package com.gprindevelopment.cec.core.politician;

import com.gprindevelopment.cec.core.batch.PoliticianDataUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashSet;

@Component
public class DailyAccessedPoliticianDataUpdateScheduler {

    @Autowired
    private PoliticianDataUpdater politicianDataUpdater;

    private LinkedHashSet<Long> queueIds = new LinkedHashSet<>();

    public boolean addToQueue(Long politicianId) {
        return queueIds.add(politicianId);
    }

    //@Scheduled(cron = "0 0 1 * * MON-SAT")
    public void executeJobs() throws Exception {
        Iterator<Long> iterator = queueIds.iterator();
        while(iterator.hasNext()) {
            Long current = iterator.next();
            politicianDataUpdater.batchUpdate(current);
            iterator.remove();
        }
    }
}
