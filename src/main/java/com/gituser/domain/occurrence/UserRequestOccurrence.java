package com.gituser.domain.occurrence;

import com.gituser.domain.user.GitUsername;
import lombok.Getter;

import java.util.concurrent.atomic.LongAdder;

public class UserRequestOccurrence {
    @Getter
    private final GitUsername gitUsername;
    private final LongAdder occurrences = new LongAdder();

    UserRequestOccurrence(GitUsername gitUsername) {
        this.gitUsername = gitUsername;
        occurrences.increment();
    }

    UserRequestOccurrence increment() {
        occurrences.increment();
        return this;
    }

    public long sumOfOccurrences() {
        return occurrences.sum();
    }
}
