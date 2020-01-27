package dreamsoft.vasili.balyka.springboottest

class User (val name: String) {
    val books = ArrayList<Book>()
    var id: Long = 0

    fun addBook(book: Book) = books.add(book)

    fun removeBook(bookId: Long) {
        for(book in books) {
            if(book.id == bookId) {
                books.remove(book)
                break
            }
        }
    }

    fun findBook(bookId: Long): Boolean {
        for (book in books)
            if (book.id == bookId) return true
        return false
    }
}