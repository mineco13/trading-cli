package winter_camp_afterburner.interface_adapter.input_receiver

import java.math.BigDecimal

object InputReceiver : AInputReceiver() {
    override fun getIndex(): Int = getInt()
    override fun getNonNegativeBigDecimal(): BigDecimal = getBigDecimal().also { require(it > BigDecimal.ZERO) }
    override fun getSellOrNot(): Boolean = getBoolean("s", "b")
    override fun getAccept(): Boolean = getBoolean("y", "n")
}
