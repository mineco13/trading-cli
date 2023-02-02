package winter_camp_afterburner.interface_adapter.repository

import winter_camp_afterburner.entities.issues.*
import winter_camp_afterburner.entities.issues.impl.*
import winter_camp_afterburner.usecases.model.impl.IssueInfoInteractorImpl

class IssueInfoMapRepository(private val issueFileReader: ObjectListReader<Issue>) : IssueInfoInteractorImpl.Repository {
    override fun getMapCodeToIssues(): Map<String, IssueInfo> =
        issueFileReader.getObjectListFromFile().map { IssueInfoImpl(it, null) }.associateBy { it.issue.code }
}
