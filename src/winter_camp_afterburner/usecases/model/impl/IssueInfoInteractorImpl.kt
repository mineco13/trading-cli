package winter_camp_afterburner.usecases.model.impl

import winter_camp_afterburner.entities.issues.*
import winter_camp_afterburner.entities.issues.impl.*
import winter_camp_afterburner.usecases.model.*

class IssueInfoInteractorImpl(repository: Repository) : IssueInfoInteractor {
    override val issueInfoMap: IssueInfoMap = IssueInfoMapImpl(repository.getMapCodeToIssues())

    private class IssueInfoMapImpl(mutableMap: Map<String, IssueInfo>) : IssueInfoMap,
        Map<String, IssueInfo> by mutableMap

    interface Repository {
        fun getMapCodeToIssues(): Map<String, IssueInfo>
    }

}
