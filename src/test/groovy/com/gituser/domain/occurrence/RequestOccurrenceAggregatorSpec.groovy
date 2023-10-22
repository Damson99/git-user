package com.gituser.domain.occurrence

import com.gituser.domain.user.GitUsername
import spock.lang.Specification
import spock.lang.Subject

import java.util.stream.LongStream

@Subject(RequestOccurrenceAggregator)
class RequestOccurrenceAggregatorSpec extends Specification {

    def "should count requests properly for users in multi-threaded env and when data from aggregator is collected should handle new requests" () {
        given:
            RequestOccurrenceAggregator aggregator = new RequestOccurrenceAggregator()
            Map<String, Long> listOfRequestsForUsername = Map.of(
                    "username1", 5432L,
                    "username2", 2L,
                    "username3", 53L,
                    "username4", 124L
            )
            Map<String, Long> listOfRequestsForUsername2 = Map.of(
                    "username2", 123L,
                    "username5", 123L
            )

            Long expectedAllRequestsCount = listOfRequestsForUsername.values().stream()
                    .mapToLong(a -> a).sum()
            Long expectedAllRequestsCount2 = listOfRequestsForUsername2.values().stream()
                    .mapToLong(a -> a).sum()
        when:
            fillAggregateWithData(listOfRequestsForUsername, aggregator)
            Runnable runnable = () -> fillAggregateWithData(listOfRequestsForUsername2, aggregator)

            Thread anotherAsyncAmountOfData = new Thread(runnable)
            anotherAsyncAmountOfData.start()

            Set<UserRequestOccurrence> collectedOccurrences = aggregator.collect()
            anotherAsyncAmountOfData.join()
            Set<UserRequestOccurrence> collectedOccurrences2 = aggregator.collect()
        then:
            boolean allRequestedUsersAreProperlyCounted = collectedOccurrences.stream()
                    .allMatch(occurrence -> listOfRequestsForUsername.get(occurrence.gitUsername.username()) == occurrence.sumOfOccurrences())

            collectedOccurrences.size() == 4
            allRequestedUsersAreProperlyCounted
            expectedAllRequestsCount == collectedOccurrences.stream()
                    .mapToLong(gitUsername -> gitUsername.sumOfOccurrences())
                    .sum()

            // second async set of data
            boolean allRequestedUsersAreProperlyCounted2 = collectedOccurrences2.stream()
                    .allMatch(occurrence -> listOfRequestsForUsername2.get(occurrence.gitUsername.username()) == occurrence.sumOfOccurrences())

            collectedOccurrences2.size() == 2
            allRequestedUsersAreProperlyCounted2
            expectedAllRequestsCount2 == collectedOccurrences2.stream()
                    .mapToLong(gitUsername -> gitUsername.sumOfOccurrences())
                    .sum()


    }

    private fillAggregateWithData(Map<String, Long> listOfRequestsForUsername, aggregator) {
        listOfRequestsForUsername.entrySet().parallelStream()
                .forEach(entry -> LongStream.range(0L, entry.getValue() + 1L)
                        .forEach(request -> aggregator.incrementFor(new GitUsername(entry.getKey()))))
    }
}
