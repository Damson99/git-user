package com.gituser.domain.occurrence;

import com.gituser.domain.user.GitUsername;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class OccurrenceDomainService {
    private final RequestOccurrenceAggregator requestOccurrenceAggregator;
    private final UserOccurrenceRepository userOccurrenceRepository;

    @Async
    public void requestOccurredWith(GitUsername gitUsername) {
        requestOccurrenceAggregator.incrementFor(gitUsername);
    }

    public void saveCollectedRequestOccurrences() {
        final Set<UserRequestOccurrence> collectedUserRequestOccurrences = requestOccurrenceAggregator.collect();
        log.info("data from request occurrences aggregator collected, saving to database");
        userOccurrenceRepository.addNewOccurrencesForUsernames(collectedUserRequestOccurrences);
        log.info("data from request occurrences aggregator saved");
    }
}
