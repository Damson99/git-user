package com.gituser.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Getter
@Builder
public class GitUser {
    private final String id;
    private final String login;
    private final String name;
    private final String type;
    private final String avatarUrl;
    private final LocalDateTime createdAt;
    private final BigDecimal calculations;

    public static class GitUserBuilder {
        private static final BigDecimal MAGICAL_NUMBER_6 = BigDecimal.valueOf(6);
        private static final BigDecimal MAGICAL_NUMBER_2 = BigDecimal.valueOf(2);
        private static final Integer ZERO = 0;
        private static final Integer DECIMAL_SCALE = 6;
        private static final BigDecimal NUMBER_IN_ERROR_CASE = BigDecimal.valueOf(-1);

        public GitUserBuilder calculations(final Integer countOfFollowers, final Integer countOfPublicRepos) {
            if (ZERO.equals(countOfFollowers)) {
                this.calculations = NUMBER_IN_ERROR_CASE;
            } else {
                final BigDecimal countOfFollowersAsDecimal = convertToBigDecimal(countOfFollowers);
                final BigDecimal countOfReposAsDecimal = convertToBigDecimal(countOfPublicRepos);

                final BigDecimal dividing = MAGICAL_NUMBER_6.divide(countOfFollowersAsDecimal, DECIMAL_SCALE, RoundingMode.HALF_UP);
                final BigDecimal adding = MAGICAL_NUMBER_2.add(countOfReposAsDecimal);
                this.calculations = dividing.multiply(adding);
            }
            return this;
        }

        private BigDecimal convertToBigDecimal(Integer givenInteger) {
            return BigDecimal.valueOf(givenInteger)
                    .setScale(DECIMAL_SCALE, RoundingMode.HALF_UP);
        }

        public GitUserBuilder createdAt(final String createdAt) {
            this.createdAt = LocalDateTime.parse(createdAt);
            return this;
        }
    }
}
