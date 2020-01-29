package vasili.balyka.springboottest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/library")
class LibraryController(val bookRepository: Library) {

    @GetMapping("/allbooks")
    fun getAll(): MutableIterable<*> = bookRepository.library()

    @GetMapping("/freebooks")
    fun getFree(): MutableIterable<Book> = bookRepository.freeBooks()

    @PostMapping("/reserve")
    fun reserve(@RequestParam bookId: String, @RequestParam userId: String): Boolean =
        try {
            bookRepository.reserveBook(bookId.toLong() , userId.toLong())
        } catch (e: NumberFormatException) {
            throw MNFE("bookId and userID must be integer numbers")
        }

    @PostMapping("/unreserve")
    fun unReserve(@RequestParam bookId: String): Boolean =
        try {
            bookRepository.unReserveBook(bookId.toLong())
        } catch (e: NumberFormatException) {
            throw MNFE("bookId must be an integer number")
        }
}

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class MNFE(s: String): java.lang.NumberFormatException(s)
