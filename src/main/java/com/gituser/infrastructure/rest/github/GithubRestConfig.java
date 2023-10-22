package com.gituser.infrastructure.rest.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class GithubRestConfig {

    @Value("${web-client.github-api-base}")
    private String githubBaseApi;
    @Value("${web-client.github-api-user-info}")
    private String githubUserInfoEndpoint;

    @Bean
    WebClient githubWebClient() {
        return WebClient.builder()
                .baseUrl(githubBaseApi)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    GithubMapper githubMapper() {
        return new GithubMapper();
    }

    @Bean
    GithubUserProvider githubUserProvider(WebClient githubWebClient, GithubMapper githubMapper) {
        return new GithubUserProvider(githubWebClient, githubUserInfoEndpoint, githubMapper);
    }
}
