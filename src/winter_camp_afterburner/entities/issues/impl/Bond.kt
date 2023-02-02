package winter_camp_afterburner.entities.issues.impl

import winter_camp_afterburner.entities.issues.*
import java.math.BigDecimal

/**
 * 債権を表します
 */
class Bond(code: String, name: String, val maturity: Int, val coupon: BigDecimal, val couponChance: Int) :
    Issue(code, name) {
    init {
        require(!(maturity < 20000101 || maturity > 29991231 || coupon < BigDecimal.ZERO || couponChance < 0))
    }

    val bondType = if (coupon == BigDecimal.ZERO) Type.ZERO_COUPON_BOND else Type.COUPON_BOND

    override val issueType = IssueType.BOND

    override fun toString() =
        "Bond{${super.toString()}, maturity=$maturity, coupon=$coupon, couponChance=$couponChance}"

    override fun equals(other: Any?) =
        if (other is Bond) super.equals(other) && maturity == other.maturity && coupon == other.coupon else false

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = result * 17 + maturity
        result = result * 17 + coupon.hashCode()
        result = result * 17 + couponChance
        return result
    }

    enum class Type {
        COUPON_BOND, ZERO_COUPON_BOND
    }


}