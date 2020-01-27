package dreamsoft.vasili.balyka.springboottest

import org.springframework.stereotype.Component

@Component
final class Library {
    val library = ArrayList<Book>()
    private val users = ArrayList<User>()
    private var bookIndex: Long = 0
    private var userIndex: Long = 0

    init {
        addBook("Dune")
        addBook("Hyperion")
        addBook("Witcher")
        addBook("Ring World")
        addUser("Jane")
        addUser("Bob")
    }

    fun findBook(bookId: Long): Book? {
        for (book in library)
            if (book.id == bookId) return book
        return null
    }

    fun findUser(userId: Long): User? {
        for (user in users)
            if (user.id == userId) return user
        return null
    }

    fun bookQuantity(): Int = library.size

    fun freeBooks(): ArrayList<Book> {
        val bookList = ArrayList<Book>()
        for (book in library) {
            if (book.isFree()) bookList.add(book)
        }
        return bookList
    }

    fun addBook(name: String): Book {
        val book = Book(name)
        book.id = bookIndex++
        library.add(book)
        return book
    }

    fun addUser(name: String): User {
        val user = User(name)
        user.id = userIndex++
        users.add(user)
        return user
    }

    fun reserveBook(bookId: Long, userId: Long): Boolean {
        val user = findUser(userId)
        val book = findBook(bookId)
        if (user == null || book == null) return false
        if (!book.isFree()) return false
        user.addBook(book)
        book.user = user
        return true
    }

    fun unReserveBook(bookId: Long): Boolean {
        val book = findBook(bookId) ?: return false
        if (book.isFree()) return false
        book.user?.removeBook(book.id)
        book.user = null
        return true
    }

}