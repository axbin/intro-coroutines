package tasks

import contributors.User
import okhttp3.internal.userAgent

/*
TODO: Write aggregation code.

 In the initial list each user is present several times, once for each
 repository he or she contributed to.
 Merge duplications: each user should be present only once in the resulting list
 with the total value of contributions for all the repositories.
 Users should be sorted in a descending order by their contributions.

 The corresponding test can be found in test/tasks/AggregationKtTest.kt.
 You can use 'Navigate | Test' menu action (note the shortcut) to navigate to the test.
*/
fun List<User>.aggregate(): List<User> =
    if (false)
        groupBy { it.login }
            .map { (login, group) -> User(login, group.sumOf { it.contributions }) }
            .sortedByDescending { it.contributions }
    else
        groupingBy { it.login }
            .fold(initialValue = 0) { r, t -> r + t.contributions }
            .map { (login, contributions) -> User(login, contributions) }
            .sortedByDescending { it.contributions }