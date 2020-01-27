package dreamsoft.vasili.balyka.springboottest

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class SpringBootTestApplicationTests {

    @Test
	fun bookIsFreeTest() {
		val book = Book("SomeBook")
		val user = User("SomeName")
		assertTrue(book.isFree())
        book.user = user
		assertFalse(book.isFree())
	}

	@Test
	fun userRemoveBookTest() {
		val user = User("SomeName")
		var book = Book("SomeBook")
		book.id = 100
		user.addBook(book)
		book = Book("AnotherBook")
		book.id = 101
		user.addBook(book)
		assertEquals(user.books.size, 2)
		user.removeBook(-1)
		assertEquals(user.books.size, 2)
		user.removeBook(book.id)
		assertEquals(user.books.size, 1)
	}

	@Test
	fun libraryFindBookTest() {
		val library = Library()
		val book = library.addBook("ABC")
		assertNotNull(library.findBook(book.id))
		assertEquals(library.findBook(-1), null)
	}

	@Test
	fun libraryFindUserTest() {
		val library = Library()
		val user = library.addUser("Somebody")
		assertNotNull(library.findUser(user.id))
		assertEquals(library.findUser(-1), null)
	}

	@Test
	fun libraryFreeBooksTest() {
		val library = Library()
		assertEquals(library.freeBooks().size, library.bookQuantity())
		val user = library.addUser("Somebody")
		val book = library.addBook("ABC")
		book.user = user
		assertEquals(library.freeBooks().size, library.bookQuantity() - 1)
	}

	@Test
	fun libraryReserveBookTest() {
		val library = Library()
		val book = library.addBook("ABC")
		val user = library.addUser("Somebody")
		assertFalse(library.reserveBook(-1, -1))
		assertFalse(library.reserveBook(-1, user.id))
		assertFalse(library.reserveBook(book.id, -1))
		assertTrue(library.reserveBook(book.id, user.id))
		assertFalse(library.reserveBook(book.id, user.id))
	}

}
