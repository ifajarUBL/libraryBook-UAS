package com.example.libraryBook.model

import jakarta.persistence.*

@Entity
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String title
    String author

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category
}