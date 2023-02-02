package winter_camp_afterburner.entities.issues

abstract class Issue(val code: String, val name: String){
    abstract val issueType: Type
    override fun toString() = "Issue{code=$code, name=$name}"

    override fun equals(other: Any?) = if (other is Issue) code == other.code else false

    override fun hashCode(): Int {
        var result = 0
        result = result * 17 + code.hashCode()
        result = result * 17 + name.hashCode()
        return result
    }
    interface Type

}