package com.gituser.infrastructure.persistence.occurrence;

import com.gituser.domain.user.GitUsername;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
interface MongoUserOccurrenceRepository extends MongoRepository<UserOccurrenceEntity, String> {

    Set<UserOccurrenceEntity> findByGitUsernameIn(Set<GitUsername> names);
}
