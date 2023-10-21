package com.gituser.application.user;

import com.gituser.domain.GitUser;
import com.gituser.domain.GitUserProvider;
import com.gituser.domain.GitUsername;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserApplicationService {
    private final GitUserProvider githubUserProvider;

    public GitUser handle(GetUsernameCommand getUsernameCommand) {
        final GitUsername gitUsername = new GitUsername(getUsernameCommand.username());
        return githubUserProvider.retrieveGitUser(gitUsername);
    }
}
