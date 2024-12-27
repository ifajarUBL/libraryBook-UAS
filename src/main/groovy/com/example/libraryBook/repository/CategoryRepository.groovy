package com.example.libraryBook.repository


import com.example.libraryBook.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository extends JpaRepository<Category, Long> {
}