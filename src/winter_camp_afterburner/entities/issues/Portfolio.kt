package winter_camp_afterburner.entities.issues


interface Portfolio<out T : Position> : Map<Issue, T> {
    fun getOrZero(issue: Issue): T

    interface Mutable<T: Position> : Portfolio<T>,MutableMap<Issue,T>{
        fun add(element: Position): Boolean
        fun addAll(elements: Collection<Position>): Boolean
        override fun clear()
    }

}
