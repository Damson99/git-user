package com.gituser.infrastructure.persistence.occurrence;

import com.gituser.domain.occurrence.UserOccurrenceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PersistenceOccurrenceConfig {

    @Bean
    UserOccurrenceMapper userOccurrenceMapper() {
        return new UserOccurrenceMapper();
    }

    @Bean
    UserOccurrenceRepository userOccurrenceRepository(UserOccurrenceMapper userOccurrenceMapper,
                                                      MongoUserOccurrenceRepository mongoUserOccurrenceRepository) {
        return new UserOccurrenceFacade(userOccurrenceMapper, mongoUserOccurrenceRepository);
    }
}
