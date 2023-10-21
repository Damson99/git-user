package com.gituser.domain

import spock.lang.Specification
import spock.lang.Subject

@Subject(GitUser)
class GitUserSpec extends Specification {

    def "should return correct #result when given #followers and #repos"() {
        when:
            GitUser gitUser = GitUser.builder()
                    .calculations(followers, repos)
                    .build()
        then:
            result == gitUser.calculations
        where:
            result                             | followers | repos
            BigDecimal.valueOf(-1)             | 0         | 22
            BigDecimal.valueOf(-1)             | 0         | 0
            BigDecimal.valueOf(1.200000000000) | 10        | 0
            BigDecimal.valueOf(72)             | 2         | 22
            BigDecimal.valueOf(6.461532000000) | 13        | 12
            BigDecimal.valueOf(5.600000000000) | 15        | 12
    }
}
