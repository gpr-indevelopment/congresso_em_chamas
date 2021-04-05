package com.gprindevelopment.cec.core.externalapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ExternalAPICacheManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalAPICacheManager.class);

    @Scheduled(cron = "0 0 3 * * FRI")
    @CacheEvict(cacheNames = {"politicianIdsByNameAndLegislatureId",
    "politicianById", "propositionIdsByPoliticianId", "propositionById",
    "authorsByPropositionId", "processingHistoryByPropositionId", "expensesByPropositionId", "currentLegislatureId", "jarbasReimbursementByDocumentCode"})
    public void cleanCache(){
        LOGGER.info("External API cache was evicted successfully.");
    }
}
