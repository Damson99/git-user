package com.gituser.domain.user;

public interface GitUserProvider {

    GitUser retrieveGitUser(GitUsername gitUsername);
}
