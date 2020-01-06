package com.library.dao;

import com.github.pagehelper.PageHelper;
import com.library.bean.Book;
import com.library.bean.ClassInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BookDao {

    private final static String NAMESPACE = "com.library.dao.BookDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public int matchBook(final String searchWord) {
        String search = "%" + searchWord + "%";
        return sqlSessionTemplate.selectOne(NAMESPACE + "matchBook", search);
    }

    public ArrayList<Book> queryBook(final String searchWord,int page,int size) {
        String search = "%" + searchWord + "%";
        PageHelper.startPage(page,size);
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "queryBook", search);
        return (ArrayList<Book>) result;
    }

    public ArrayList<Book> getAllBooks(int page,int size) {
        PageHelper.startPage(page,size);
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "getAllBooks");
        return (ArrayList<Book>) result;
    }

    public ArrayList<ClassInfo> getAllClass(){
        List<ClassInfo> result= sqlSessionTemplate.selectList(NAMESPACE+"selectAllClass");
        return (ArrayList<ClassInfo>) result;
    }

    public int addBook(final Book book) {
        return sqlSessionTemplate.insert(NAMESPACE + "addBook", book);
    }

    public Book getBook(final long bookId) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getBook", bookId);
    }

    public int editBook(final Book book) {
        return sqlSessionTemplate.update(NAMESPACE + "editBook", book);
    }

    public int deleteBook(final long bookId) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteBook", bookId);
    }
}
