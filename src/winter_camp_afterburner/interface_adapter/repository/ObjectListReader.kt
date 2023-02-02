package winter_camp_afterburner.interface_adapter.repository

interface ObjectListReader<out T> {
    fun getObjectListFromFile(): List<T>
}