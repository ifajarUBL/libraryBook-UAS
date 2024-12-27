package com.example.libraryBook.repository


import com.example.libraryBook.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository extends JpaRepository<Book, Long> {
}