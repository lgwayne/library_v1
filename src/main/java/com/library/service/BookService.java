package com.library.service;

import com.library.bean.Book;
import com.library.bean.ClassInfo;
import com.library.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public ArrayList<Book> queryBook(String searchWord,int page,int size) {
        return bookDao.queryBook(searchWord,page,size);
    }

    public ArrayList<Book> getAllBooks(int page,int size) {
        return bookDao.getAllBooks(page,size);
    }

    public ArrayList<ClassInfo> getAllClass(){
        return bookDao.getAllClass();
    }
    public boolean matchBook(String searchWord) {
        return bookDao.matchBook(searchWord) > 0;
    }

    public boolean addBook(Book book) {
        return bookDao.addBook(book) > 0;
    }

    public Book getBook(Long bookId) {
        return bookDao.getBook(bookId);
    }

    public boolean editBook(Book book) {
        return bookDao.editBook(book) > 0;
    }

    public boolean deleteBook(Long bookId) {
        return bookDao.deleteBook(bookId) > 0;
    }

}
