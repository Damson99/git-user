package com.gituser.application.user;

import com.gituser.domain.occurrence.OccurrenceDomainService;
import com.gituser.domain.user.GitUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class UserConfig {
    private final GitUserProvider githubUserProvider;
    private final OccurrenceDomainService occurrenceDomainService;

    @Bean
    UserApplicationService userApplicationService() {
        return new UserApplicationService(githubUserProvider, occurrenceDomainService);
    }
}
