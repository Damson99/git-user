package com.gituser.infrastructure.persistence.occurrence;


import com.gituser.domain.user.GitUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@AllArgsConstructor
class UserOccurrenceEntity {
    @Id
    @Field("LOGIN")
    private final GitUsername gitUsername;
    @Field("REQUEST_COUNT")
    private final Long requestOccurrences;
}
