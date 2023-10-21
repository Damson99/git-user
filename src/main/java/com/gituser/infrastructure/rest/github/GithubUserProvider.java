package com.gituser.infrastructure.rest.github;

import com.gituser.domain.GitUser;
import com.gituser.domain.GitUserProvider;
import com.gituser.domain.GitUsername;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
class GithubUserProvider implements GitUserProvider {
    private final WebClient githubWebClient;
    private final String gitUserInfoEndpoint;
    private final GithubMapper githubMapper;

    @Override
    public GitUser retrieveGitUser(GitUsername gitUsername) {
        final String username = gitUsername.username();
        log.info("retrieving user info from github for {}", username);
        final GithubUserDto githubUserDto = getByUsername(gitUsername)
                .onErrorResume(UserNotFoundException.class, e -> {
                    log.warn("user {} not found", username);
                    return Mono.error(e);
                })
                .onErrorResume(GithubProviderException.class, e -> {
                    log.error("error occurred while retrieving user {} :", username, e);
                    return Mono.error(e);
                })
                .block();

        if (githubUserDto == null) {
            throw new GithubProviderException("null retrieved from github for " + username);
        }
        return githubMapper.map(githubUserDto);
    }

    private Mono<GithubUserDto> getByUsername(GitUsername gitUsername) {
        return githubWebClient
                .get()
                .uri(gitUserInfoEndpoint + gitUsername.username())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(UserNotFoundException.NOT_FOUND))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(GithubProviderException.ERROR))
                .bodyToMono(GithubUserDto.class);
    }
}
