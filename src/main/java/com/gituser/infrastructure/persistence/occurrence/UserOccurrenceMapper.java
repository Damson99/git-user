package com.gituser.infrastructure.persistence.occurrence;

import com.gituser.domain.occurrence.UserRequestOccurrence;

class UserOccurrenceMapper {
    UserOccurrenceEntity map(UserRequestOccurrence userRequestOccurrence) {
        return new UserOccurrenceEntity(userRequestOccurrence.getGitUsername(), userRequestOccurrence.sumOfOccurrences());
    }
}
