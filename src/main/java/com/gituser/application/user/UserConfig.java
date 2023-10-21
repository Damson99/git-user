package com.gituser.application.user;

import com.gituser.domain.GitUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class UserConfig {
    private final GitUserProvider githubUserProvider;

    @Bean
    UserApplicationService userApplicationService() {
        return new UserApplicationService(githubUserProvider);
    }
}
