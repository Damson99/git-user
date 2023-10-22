package com.gituser.infrastructure.persistence.occurrence;


import com.gituser.domain.user.GitUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@AllArgsConstructor
class UserOccurrenceEntity {
    private final GitUsername gitUsername;
    private final Long occurrences;
}
