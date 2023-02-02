package winter_camp_afterburner.entities.issues


interface PortfolioMutable<T: Position> : Portfolio<T>,MutableMap<Issue,T>{
    fun add(element: Position): Boolean
    fun addAll(elements: Collection<Position>): Boolean
    override fun clear()
}
