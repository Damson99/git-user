package com.gituser.infrastructure.persistence.occurrence;

import com.gituser.domain.occurrence.UserOccurrenceRepository;
import com.gituser.domain.occurrence.UserRequestOccurrence;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class UserOccurrenceFacade implements UserOccurrenceRepository {
    private final UserOccurrenceMapper userOccurrenceMapper;
    private final MongoUserOccurrenceRepository userOccurrenceRepository;

    @Override
    public void addNewOccurrencesForUsernames(Set<UserRequestOccurrence> userRequestOccurrences) {
        final Set<UserOccurrenceEntity> userOccurrencesToSave = userRequestOccurrences.stream()
                .map(userOccurrenceMapper::map)
                .collect(Collectors.toUnmodifiableSet());
        userOccurrenceRepository.saveAll(userOccurrencesToSave);
    }
}
