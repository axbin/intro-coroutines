package tasks

import contributors.*

suspend fun loadContributorsSuspend(service: GitHubService, req: RequestData): List<User> {
    val repos = service
        .getOrgRepos(req.org)
//        .execute() // Executes request and blocks the current thread
        .also { logRepos(req, it) }
        .body() ?: emptyList()

    return repos.flatMap { repo ->
        service
            .getRepoContributors(req.org, repo.name)
//            .execute() // Executes request and blocks the current thread
            .also {
                logUsers(repo, it)
                Thread.sleep(1000) //coroutine running in UI Thread, UI won't be responsive during sleep
            }
            .bodyList()
    }.aggregate()
}