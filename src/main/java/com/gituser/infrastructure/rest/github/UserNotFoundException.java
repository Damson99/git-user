package com.gituser.infrastructure.rest.github;

public class UserNotFoundException extends RuntimeException {
    public static final UserNotFoundException NOT_FOUND = init();

    private UserNotFoundException() {}

    private static UserNotFoundException init() {
        return new UserNotFoundException();
    }
}
