package com.libraryManagement.service.impl;

import com.libraryManagement.entity.Book;
import com.libraryManagement.payload.BookDto;
import com.libraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl {


    @Autowired
    private BookRepository bookRepo;

    public BookDto saveBook(BookDto dto) {
        Book book = this.mapToEntity(dto);
        Book savedBook = bookRepo.save(book);
        return mapToDto(book);
    }

    public List<BookDto> allBooks(){
        List<Book> books = bookRepo.findAll();
        List<BookDto> dtos = books.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dtos;
    }

    public String deleteBook(int isbn) {
      bookRepo.deleteById(isbn);
      return "Successfully Deleted!!...";
    }

    public Book mapToEntity(BookDto dto){
        Book book= new Book(dto.getISBN(), dto.getTitle(),dto.getAuthor(),dto.getYear());
        return  book;
    }

    public BookDto mapToDto(Book book){
       BookDto dto= new BookDto(book.getISBN(),book.getTitle(),book.getAuthor(),book.getYear());
        return  dto;
    }
}
