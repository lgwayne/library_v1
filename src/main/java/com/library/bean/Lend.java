package com.library.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 借阅表实体类
 */
public class Lend implements Serializable {

    private long ser_num;
    private long book_id;
    private long reader_id;
    private Date lend_date;
    private Date back_date;
    private Book book;
    private ReaderCard readerCard;

    public ReaderCard getReaderCard() {
        return readerCard;
    }

    public void setReaderCard(ReaderCard readerCard) {
        this.readerCard = readerCard;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public long getReader_id() {
        return reader_id;
    }

    public void setReader_id(long reader_id) {
        this.reader_id = reader_id;
    }

    public Date getLend_date() {
        return lend_date;
    }

    public void setLend_date(Date lend_date) {
        this.lend_date = lend_date;
    }

    public Date getBack_date() {
        return back_date;
    }

    public void setBack_date(Date back_date) {
        this.back_date = back_date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public long getReaderId() {
        return reader_id;
    }

    public void setReaderId(long reader_id) {
        this.reader_id = reader_id;
    }

    public long getBookId() {
        return book_id;
    }

    public void setBookId(long book_id) {
        this.book_id = book_id;
    }

    public void setSer_num(long ser_num) {
        this.ser_num = ser_num;
    }

    public Date getBackDate() {
        return back_date;
    }

    public void setBackDate(Date back_date) {
        this.back_date = back_date;
    }

    public Date getLendDate() {
        return lend_date;
    }

    public void setLendDate(Date lend_date) {
        this.lend_date = lend_date;
    }

    public long getSer_num() {
        return ser_num;
    }

    @Override
    public String toString() {
        return "Lend{" +
                "book_id=" + book_id +
                ", lend_date=" + lend_date +
                ", back_date=" + back_date +
                ", readerCard=" + readerCard +
                '}';
    }
}
