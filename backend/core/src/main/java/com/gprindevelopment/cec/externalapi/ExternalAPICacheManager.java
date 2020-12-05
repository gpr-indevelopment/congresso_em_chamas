package com.gprindevelopment.cec.externalapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ExternalAPICacheManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalAPICacheManager.class);

    @Scheduled(cron = "0 0 2 * * *")
    @CacheEvict(cacheNames = {"politicianIdsByNameAndLegislatureId",
    "politicianById", "propositionIdsByPoliticianId", "propositionById",
    "authorsByPropositionId", "processingHistoryByPropositionId", "expensesByPropositionId", "currentLegislatureId"})
    private void cleanCache(){
        LOGGER.info("Scheduled time has arrived. Evicting cache from external API calls.");
    }
}
