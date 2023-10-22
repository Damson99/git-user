package com.gituser.infrastructure.executor.occurrence;

import com.gituser.domain.occurrence.OccurrenceDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
class SaveOccurrencesTrigger {
    private final OccurrenceDomainService occurrenceDomainService;

    @Scheduled(fixedRateString = "${aggregator.time-window-for-saving-occurrences-ms}")
    void saveAggregatedUserOccurrences() {
        log.info("saving collected data from request occurrences aggregator");
        occurrenceDomainService.saveCollectedRequestOccurrences();
    }
}
