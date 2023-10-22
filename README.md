# git-user

## Additional requirements
if in the formula ```6 / liczba_followers * (2 + liczba_public_repos)``` the liczba_followers is equal to 0, then it will be contractually ```returned -1``` from the algorithm

## Mechanism for incrementing the number of request calls for a given username in git.
In order to make the mechanism as optimal as possible and not burden the database with every update called by a request, update windows were introduced. The service has a ```RequestOccurrenceAggregator``` class, which calculates in memory on an ongoing basis the current api calls for each user. Every given unit of time, user data is cleaned from the aggregator and an update is performed on the database. This mechanism is much more efficient and consumes fewer resources compared to if the service in the database updated the value with each api call.

This is not required in the task and is not implemented in the service, but if the client wanted to obtain the number of calls for a given user, you could retrieve the value from the database and the summed value of the calls from the aggregator and return it to the client.