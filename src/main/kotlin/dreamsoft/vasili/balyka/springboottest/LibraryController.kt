package dreamsoft.vasili.balyka.springboottest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/library")
class LibraryController(val bookRepository: Library) {

    @RequestMapping("/allbooks")
    fun getAll(): MutableIterable<Book> = bookRepository.library

    @RequestMapping("/freebooks")
    fun getFree(): MutableIterable<Book> = bookRepository.freeBooks()

    fun reserve() {

    }
}