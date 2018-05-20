package com.books.controllers;

import com.books.model.Book;
import com.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", bookService.listBooks());
        return "books";
    }

    @RequestMapping(value = "books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
        if (book.getId() == 0) {
            bookService.addBook(book);
        } else {
            bookService.updateBook(book);
        }
        return "redirect:/books";
    }

    @RequestMapping(value = "remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        bookService.removeBook(id);
        return "redirect:/books";
    }

    @RequestMapping(value = "edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("listBooks", bookService.listBooks());
        bookService.removeBook(id);
        return "books";
    }
}
