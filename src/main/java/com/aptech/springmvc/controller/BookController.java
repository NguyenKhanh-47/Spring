package com.aptech.springmvc.controller;

import com.aptech.springmvc.constant.SortBookColumn;
import com.aptech.springmvc.entity.Author;
import com.aptech.springmvc.entity.Book;
import com.aptech.springmvc.service.AuthorService;
import com.aptech.springmvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public String listBooks(Model theModel, @RequestParam(required = false) String sort) {

        List<Book> theBooks = null;

        if (sort != null) {
            int theSortField = Integer.parseInt(sort);
            theBooks = bookService.getBooks(theSortField);
        } else {
            theBooks = bookService.getBooks(SortBookColumn.TITLE);
        }

        theModel.addAttribute("books", theBooks);

        return "list-books";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data

        List<Author> authors = authorService.getAuthors();
        theModel.addAttribute("authors", authors);
        System.out.println(("Khanh " + authors));

        Book theBook = new Book();
        theModel.addAttribute("book", theBook);

        return "book-form";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book theBook, @RequestParam("fileData") CommonsMultipartFile file) {
        if (null != file && !file.isEmpty()) {
            try {
                String uploadRootPath = "D:\\Aptech\\Java\\Spring\\NguyenDuyKhanh-Spring-MVC\\src\\main\\webapp\\resources\\css\\Images";
                String filename = file.getOriginalFilename();

                File uploadRootDir = new File(uploadRootPath);

                if (!uploadRootDir.exists()) {
                    uploadRootDir.mkdirs();
                }

                byte[] bytes = file.getBytes();

                System.out.println("path test: " + uploadRootPath + File.separator + filename);

                BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(
                        new File(uploadRootPath + File.separator + filename)));
                stream.write(bytes);
                stream.flush();
                stream.close();

                theBook.setImage(filename);
            } catch (IOException e) {
                System.out.println("Error Write file");
            }
        }

        System.out.println(theBook);
        bookService.saveBook(theBook);

        // redirect from controller to another URL in controller
        return "redirect:/book/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId, Model theModel) {
        // get book from DB
        Book theBook = bookService.getBook(theId);

        // bind data to theModel
        theModel.addAttribute("book", theBook);

        return "book-form";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("bookId") int theId) {
        bookService.deleteBook(theId);
        return "redirect:/book/list";
    }

    @GetMapping("search")
    public String searchBook(@RequestParam("theSearchName") String theSearchName, Model theModel) {
        List<Book> theBooks = bookService.searchBooks(theSearchName);
        theModel.addAttribute("books", theBooks);
        return "list-books";
    }
}