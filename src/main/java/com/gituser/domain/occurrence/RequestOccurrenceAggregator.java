package com.gituser.domain.occurrence;

import com.gituser.domain.user.GitUsername;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class RequestOccurrenceAggregator {
    private static final ConcurrentMap<GitUsername, UserRequestOccurrence> USER_REQUEST_OCCURRENCES = new ConcurrentHashMap<>();


    void incrementFor(GitUsername gitUsername) {
        USER_REQUEST_OCCURRENCES.compute(gitUsername, (username, occurrence) -> occurrence == null
                ? new UserRequestOccurrence(username)
                : occurrence.increment());
    }

    synchronized Set<UserRequestOccurrence> collect() {
        final Set<UserRequestOccurrence> userRequestOccurrences = Set.copyOf(USER_REQUEST_OCCURRENCES.values());
        USER_REQUEST_OCCURRENCES.clear();
        return userRequestOccurrences;
    }
}
