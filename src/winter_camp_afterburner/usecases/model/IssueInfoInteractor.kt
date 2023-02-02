package winter_camp_afterburner.usecases.model

import winter_camp_afterburner.entities.issues.impl.IssueInfoMap

interface IssueInfoInteractor {
    val issueInfoMap: IssueInfoMap
}