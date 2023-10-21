package com.gituser.infrastructure.github;

public class GithubProviderException extends RuntimeException {

    public GithubProviderException(String msg) {
        super(msg, null, false, false);
    }
}
