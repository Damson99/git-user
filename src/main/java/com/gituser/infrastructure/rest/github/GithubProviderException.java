package com.gituser.infrastructure.rest.github;

public class GithubProviderException extends RuntimeException {
    public static final GithubProviderException ERROR = init();

    private static GithubProviderException init() {
        return new GithubProviderException();
    }

    private GithubProviderException() {}

    public GithubProviderException(String msg) {
        super(msg, null, false, false);
    }
}
