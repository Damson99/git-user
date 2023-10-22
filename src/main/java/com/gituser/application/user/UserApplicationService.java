package com.gituser.application.user;

import com.gituser.domain.occurrence.OccurrenceDomainService;
import com.gituser.domain.user.GitUser;
import com.gituser.domain.user.GitUserProvider;
import com.gituser.domain.user.GitUsername;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserApplicationService {
    private final GitUserProvider githubUserProvider;
    private final OccurrenceDomainService occurrenceDomainService;

    public GitUser handle(GetUsernameCommand getUsernameCommand) {
        final GitUsername gitUsername = new GitUsername(getUsernameCommand.username());
        occurrenceDomainService.requestOccurredWith(gitUsername);
        return githubUserProvider.retrieveGitUser(gitUsername);
    }
}
