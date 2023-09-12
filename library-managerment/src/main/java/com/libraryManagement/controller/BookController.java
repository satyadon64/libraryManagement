package com.libraryManagement.controller;

import com.libraryManagement.payload.BookDto;

import com.libraryManagement.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookServiceImpl service;
// http://localhost:8080/addBook
    @PostMapping("/addBook")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto dto){
        BookDto savedDto = service.saveBook(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    // http://localhost:8080/getBooks
     @GetMapping("/getBooks")
    public ResponseEntity<List<BookDto>> allBooks(){
        List<BookDto> bookDtos = service.allBooks();
        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }
    // http://localhost:8080/books/delete/{isbn}
    @DeleteMapping("/books/delete/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable("isbn") int isbn){
        String message = service.deleteBook(isbn);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
