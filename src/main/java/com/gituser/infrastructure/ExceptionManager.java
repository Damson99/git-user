package com.gituser.infrastructure;

import com.gituser.infrastructure.rest.github.GithubProviderException;
import com.gituser.infrastructure.rest.github.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ExceptionManager {

    @ExceptionHandler(GithubProviderException.class)
    public final ResponseEntity<String> handle(GithubProviderException githubProviderException) {
        return ResponseEntity.internalServerError()
                .body(githubProviderException.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Void> handle(UserNotFoundException githubProviderException) {
        return ResponseEntity.notFound().build();
    }
}
