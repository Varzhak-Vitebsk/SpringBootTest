package vasili.balyka.springboottest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/library")
class LibraryController(val bookRepository: Library) {

    @RequestMapping("/allbooks")
    fun getAll(): ArrayList<*> = bookRepository.library()

    @RequestMapping("/freebooks")
    fun getFree(): MutableIterable<Book> = bookRepository.freeBooks()

    @RequestMapping("/reserve")
    fun reserve(@RequestParam bookId: String, @RequestParam userId: String): Any =
        try {
            bookRepository.reserveBook(bookId.toLong() , userId.toLong())
        } catch (e: NumberFormatException) {
            "bookId and userId must be integer numbers"
        }


    @RequestMapping("/unreserve")
    fun unReserve(@RequestParam bookId: String): Any =
        try {
            bookRepository.unReserveBook(bookId.toLong())
        } catch (e: NumberFormatException) {
            "bookId must be an integer number"
        }
}