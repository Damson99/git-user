package com.gituser.domain.occurrence;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class OccurrenceConfig {
    private final UserOccurrenceRepository userOccurrenceRepository;

    @Bean
    RequestOccurrenceAggregator requestOccurrenceAggregator() {
        return new RequestOccurrenceAggregator();
    }

    @Bean
    OccurrenceDomainService occurrenceDomainService(RequestOccurrenceAggregator requestOccurrenceAggregator) {
        return new OccurrenceDomainService(requestOccurrenceAggregator, userOccurrenceRepository);
    }
}
