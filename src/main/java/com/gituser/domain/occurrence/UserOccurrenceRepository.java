package com.gituser.domain.occurrence;

import com.gituser.domain.user.UserId;

import java.util.Set;

public interface UserOccurrenceRepository {

    void addNewOccurrencesForUsernames(Set<UserRequestOccurrence> userRequestOccurrences);

    UserId nextIdentity();
}
