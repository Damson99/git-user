package com.gituser.infrastructure.persistence.occurrence;

import com.gituser.domain.occurrence.UserOccurrenceRepository;
import com.gituser.domain.occurrence.UserRequestOccurrence;
import com.gituser.domain.user.GitUsername;
import com.gituser.domain.user.UserId;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class UserOccurrenceFacade implements UserOccurrenceRepository {
    private final UserOccurrenceMapper userOccurrenceMapper;
    private final MongoUserOccurrenceRepository userOccurrenceRepository;

    @Override
    public void addNewOccurrencesForUsernames(Set<UserRequestOccurrence> userRequestOccurrences) {
        final Map<GitUsername, UserRequestOccurrence> occurrenceMap = userRequestOccurrences.stream()
                .collect(Collectors.toUnmodifiableMap(UserRequestOccurrence::getGitUsername, occurrence -> occurrence));
        final Map<GitUsername, UserOccurrenceEntity> occurrencesPreparedToSave = sumExistingOccurrencesWithNewOnes(occurrenceMap);
        final Set<UserOccurrenceEntity> newUsernamesWithOccurrencesToSave = userRequestOccurrences.stream()
                .filter(userRequestOccurrence -> !occurrencesPreparedToSave.containsKey(userRequestOccurrence.getGitUsername()))
                .map(occurrenceToSave -> userOccurrenceMapper.map(nextIdentity(), occurrenceToSave))
                .collect(Collectors.toUnmodifiableSet());

        final Set<UserOccurrenceEntity> entitiesToSaveOrUpdate = new HashSet<>(userRequestOccurrences.size());
        entitiesToSaveOrUpdate.addAll(occurrencesPreparedToSave.values());
        entitiesToSaveOrUpdate.addAll(newUsernamesWithOccurrencesToSave);
        userOccurrenceRepository.saveAll(entitiesToSaveOrUpdate);
    }

    private Map<GitUsername, UserOccurrenceEntity> sumExistingOccurrencesWithNewOnes(Map<GitUsername, UserRequestOccurrence> occurrenceMap) {
        final Set<UserOccurrenceEntity> occurrencesToUpdate = userOccurrenceRepository.findByGitUsernameIn(occurrenceMap.keySet());
        return occurrencesToUpdate.stream()
                .peek(occurrence -> {
                    final GitUsername gitUsername = occurrence.getGitUsername();
                    if (occurrenceMap.containsKey(gitUsername)) {
                        final UserRequestOccurrence userRequestOccurrence = occurrenceMap.get(gitUsername);
                        occurrence.addExistingOccurrences(userRequestOccurrence.sumOfOccurrences());
                    }
                })
                .collect(Collectors.toUnmodifiableMap(UserOccurrenceEntity::getGitUsername, occurrence -> occurrence));
    }

    @Override
    public UserId nextIdentity() {
        return new UserId(UUID.randomUUID().toString());
    }
}
