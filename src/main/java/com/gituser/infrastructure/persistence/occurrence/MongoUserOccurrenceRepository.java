package com.gituser.infrastructure.persistence.occurrence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MongoUserOccurrenceRepository extends MongoRepository<UserOccurrenceEntity, String> {}
