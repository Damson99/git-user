package com.gituser.domain.occurrence;

import java.util.Set;

public interface UserOccurrenceRepository {

    void addNewOccurrencesForUsernames(Set<UserRequestOccurrence> userRequestOccurrences);
}
