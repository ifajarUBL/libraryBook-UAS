package com.example.libraryBook.model


import jakarta.persistence.*

@Entity
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String name

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<Book> books = []
}