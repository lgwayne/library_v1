package com.library.service;

import com.library.bean.Lend;
import com.library.dao.LendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LendService {
    @Autowired
    private LendDao lendDao;

    public boolean returnBook(long bookId, long readerId){
        return lendDao.returnBookOne(bookId, readerId)>0 && lendDao.returnBookTwo(bookId)>0;
    }

    public boolean lendBook(long bookId,long readerId){
        return lendDao.lendBookOne(bookId,readerId)>0 && lendDao.lendBookTwo(bookId)>0;
    }

    public ArrayList<Lend> lendList(int page,int size){
        return lendDao.lendList(page,size);
    }
    public ArrayList<Lend> myLendList(long readerId,int page,int size){
        return lendDao.myLendList(readerId,page,size);
    }

    public int deleteLend(long serNum) {
        return lendDao.deleteLend(serNum);
    }

    public ArrayList<String> myLendList_bookName(long readerId) {
        return lendDao.getBookName(readerId);
    }

    public ArrayList<Lend> myLendList_back(long readerId) {
        return lendDao.myLendList(readerId,1,10000);
    }

    public boolean deleteLendByBookId(long bookId) {
        return lendDao.deleteLendByBookId(bookId);
    }
}
