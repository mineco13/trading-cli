package winter_camp_afterburner.entities.issues.impl

import winter_camp_afterburner.entities.issues.*
import java.math.BigDecimal


/** 複数のポジションを管理します*/
abstract class PortfolioImpl<T : Position>(private val balanceMap: MutableMap<Issue, T>) :
    PortfolioMutable<T>, MutableMap<Issue, T> by balanceMap {

    override fun add(element: Position): Boolean =
        (get(element.issue)?.let { it + element } ?: element).let {
            require(it.amount >= BigDecimal.ZERO)
            balanceMap[it.issue] = it.converted()
            true
        }


    override fun addAll(elements: Collection<Position>): Boolean = elements.forEach { add(it) }.run { true }

    protected abstract fun Position.converted(): T
}
