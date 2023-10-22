package com.gituser.infrastructure.persistence.occurrence;

import com.gituser.domain.occurrence.UserRequestOccurrence;
import com.gituser.domain.user.UserId;

class UserOccurrenceMapper {

    UserOccurrenceEntity map(UserId userId, UserRequestOccurrence userRequestOccurrence) {
        return new UserOccurrenceEntity(userId, userRequestOccurrence.getGitUsername(), userRequestOccurrence.sumOfOccurrences());
    }
}
