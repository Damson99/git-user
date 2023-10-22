package com.gituser.domain.occurrence;

import com.gituser.domain.user.GitUsername;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class OccurrenceDomainService {
    private final RequestOccurrenceAggregator requestOccurrenceAggregator;
    private final UserOccurrenceRepository userOccurrenceRepository;

    public void requestOccurredWith(GitUsername gitUsername) {
        requestOccurrenceAggregator.incrementFor(gitUsername);
    }

    public void saveCollectedRequestOccurrences() {
        final Set<UserRequestOccurrence> collectedUserRequestOccurrences = requestOccurrenceAggregator.collect();
        userOccurrenceRepository.addNewOccurrencesForUsernames(collectedUserRequestOccurrences);
    }
}
