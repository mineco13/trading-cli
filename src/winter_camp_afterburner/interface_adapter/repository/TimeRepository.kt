package winter_camp_afterburner.interface_adapter.repository

import winter_camp_afterburner.usecases.trade.impl.FinalClosingInteractor
import winter_camp_afterburner.usecases.ui.impl.MenuInteractor
import java.time.LocalDate

class TimeRepository(private val systemLogWriter:ObjectListWriter<LocalDate>) : FinalClosingInteractor.Repository,
    MenuInteractor.Repository{
    override fun getFinalClosingDate(): LocalDate  = systemLogWriter.getObjectListFromFile()[0]

    override fun stampTime() = systemLogWriter.writeFileFromObjectList(listOf(LocalDate.now()))

}
