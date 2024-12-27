package com.example.libraryBook.controller


import com.example.libraryBook.model.Book
import com.example.libraryBook.model.Category
import com.example.libraryBook.repository.BookRepository
import com.example.libraryBook.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class LibraryController {

    @Autowired
    CategoryRepository categoryRepository

    @Autowired
    BookRepository bookRepository

    @GetMapping("/categories")
    List<Category> getCategories() {
        categoryRepository.findAll()
    }

    @PostMapping("/categories")
    Category addCategory(@RequestBody Category category) {
        categoryRepository.save(category)
    }

    @GetMapping("/categories/{id}")
    Category getCategoryById(@PathVariable("id") Long id) {
        return categoryRepository.findById(id).orElseThrow { new RuntimeException("Category not found with id: " + id) }
    }

    @DeleteMapping("/categories/{id}")
    void deleteCategory(@PathVariable("id") Long id) {
        categoryRepository.findById(id).ifPresentOrElse(
            { category -> categoryRepository.delete(category) },
            { throw new RuntimeException("category not found with id: " + id) }
        )
    }

    @PutMapping("/categories/{id}")
    Category updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        Category existingCategory = categoryRepository.findById(id)
            .orElseThrow { new RuntimeException("Category not found with id: " + id) }

        // Update category attributes
        existingCategory.name = category.name ?: existingCategory.name
        return categoryRepository.save(existingCategory)
    }

    @GetMapping("/books")
    List<Book> getBooks() {
        bookRepository.findAll()
    }

    @PostMapping("/books")
    Book addBook(@RequestBody Book book) {
        bookRepository.save(book)
    }

    @GetMapping("/books/{id}")
    Book getBookById(@PathVariable("id") Long id) {
        return bookRepository.findById(id).orElseThrow { new RuntimeException("Book not found with id: " + id) }
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable("id") Long id) {
        bookRepository.findById(id).ifPresentOrElse(
            { book -> bookRepository.delete(book) },
            { throw new RuntimeException("Book not found with id: " + id) }
        )
    }

    @PutMapping("/books/{id}")
    Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow { new RuntimeException("Book not found with id: " + id) }

        // Update the fields of the existing book
        existingBook.title = book.title ?: existingBook.title
        existingBook.author = book.author ?: existingBook.author

        if (book.category != null && book.category.id != null) {
            Category category = categoryRepository.findById(book.category.id)
                    .orElseThrow { new RuntimeException("Category not found with id: " + book.category.id) }
            existingBook.category = category
        }

        // Save the updated book
        return bookRepository.save(existingBook)
    }
}