package com.gituser.infrastructure.github;

import com.gituser.domain.GitUser;
import com.gituser.domain.GitUserProvider;
import com.gituser.domain.GitUsername;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
class GithubUserProvider implements GitUserProvider {
    private final WebClient githubWebClient;
    private final String gitUserInfoEndpoint;
    private final GithubMapper githubMapper;

    @Override
    public GitUser retrieveGitUser(GitUsername gitUsername) {
        log.info("retrieving user info from github for {}", gitUsername.username());
        final GithubUserDto githubUserDto = getByUsername(gitUsername);
        if (githubUserDto == null) {
            throw new GithubProviderException("null retrieved from github for " + gitUsername.username());
        }
        return githubMapper.map(githubUserDto);
    }

    private GithubUserDto getByUsername(GitUsername gitUsername) {
        try {
            return githubWebClient
                    .post()
                    .uri(gitUserInfoEndpoint + gitUsername.username())
                    .retrieve()
                    .bodyToMono(GithubUserDto.class)
                    .block();
        } catch (Exception e) {
            log.error("error occurred while retrieving user {} from github {}", gitUsername.username(), e.getMessage());
            throw new GithubProviderException(e.getMessage());
        }
    }
}
