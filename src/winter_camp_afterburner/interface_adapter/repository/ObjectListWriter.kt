package winter_camp_afterburner.interface_adapter.repository

interface ObjectListWriter<T>: ObjectListReader<T> {
    fun writeFileFromObjectList(obj: List<T>)
}