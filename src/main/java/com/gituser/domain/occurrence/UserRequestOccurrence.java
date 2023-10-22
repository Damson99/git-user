package com.gituser.domain.occurrence;

import com.gituser.domain.user.GitUsername;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.atomic.LongAdder;

@RequiredArgsConstructor
public class UserRequestOccurrence {
    @Getter
    private final GitUsername gitUsername;
    private final LongAdder occurrences = new LongAdder();

    UserRequestOccurrence increment() {
        occurrences.increment();
        return this;
    }

    public long sumOfOccurrences() {
        return occurrences.sum();
    }
}
