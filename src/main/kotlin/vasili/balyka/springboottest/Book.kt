package vasili.balyka.springboottest

open class Book(val title: String, var userId: Long = -1) {
    var id: Long = 0

    fun isFree(): Boolean = userId < 0
}