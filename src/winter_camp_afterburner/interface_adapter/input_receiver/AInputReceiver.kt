package winter_camp_afterburner.interface_adapter.input_receiver

import winter_camp_afterburner.usecases.inventry_control.impl.InventoryControlInputReceiver
import winter_camp_afterburner.usecases.trade.impl.TradeInputReceiver
import winter_camp_afterburner.usecases.ui.impl.MenuInteractor
import java.math.BigDecimal
import java.util.*

abstract class AInputReceiver : InventoryControlInputReceiver,
    TradeInputReceiver, MenuInteractor.InputReceiver {
    override fun getInput(): String = Scanner(System.`in`).next()
    protected fun getInt(): Int = InputReceiver.getInput().toInt()
    override fun getBigDecimal(): BigDecimal = BigDecimal(InputReceiver.getInput())
    protected fun getBoolean(trueString: String, falseString: String): Boolean = when (InputReceiver.getInput()) {
        trueString -> true
        falseString -> false
        else -> throw IllegalArgumentException()
    }
}