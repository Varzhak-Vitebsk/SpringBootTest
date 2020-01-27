package dreamsoft.vasili.balyka.springboottest

open class Book(val title: String, var user: User? = null) {
    var id: Long = 0

    fun isFree(): Boolean = user == null
}