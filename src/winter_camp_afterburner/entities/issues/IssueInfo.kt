package winter_camp_afterburner.entities.issues

import java.math.BigDecimal

interface IssueInfo {
    val issue: Issue
    var marketValue:BigDecimal?
}