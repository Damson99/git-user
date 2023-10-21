package com.gituser.infrastructure.rest.github;

import com.gituser.domain.GitUser;

class GithubMapper {

    GitUser map(GithubUserDto githubUserDto) {
        return GitUser.builder()
                .id(githubUserDto.id())
                .login(githubUserDto.login())
                .name(githubUserDto.name())
                .type(githubUserDto.type())
                .avatarUrl(githubUserDto.avatarUrl())
                .createdAt(githubUserDto.createdAt())
                .calculations(githubUserDto.followers(), githubUserDto.publicRepos())
                .build();
    }
}
