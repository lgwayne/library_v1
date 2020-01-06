package test;

import com.library.bean.Book;
import com.library.bean.Lend;
import com.library.dao.AdminDao;
import com.library.dao.BookDao;
import com.library.dao.LendDao;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Target;
import java.util.ArrayList;

/**
 * @BelongsProject: library_v1
 * @BelongsPackage: test
 * @Author: lgwayne
 * @CreateTime: 2019-12-03 12:01
 * @Description: ${Description}
 */
public class testdao {
    BeanFactory factory = new ClassPathXmlApplicationContext("book-context.xml");

    @Test
    public void test1(){
        AdminDao admin = (AdminDao) factory.getBean("adminDao");
        int i = admin.getMatchCount(123456, "123456");
        System.out.println(i);
    }

    @Test
    public void test2(){
        LendDao lendDao = (LendDao) factory.getBean("lendDao");
        ArrayList<Lend> lends = lendDao.myLendList(10000, 1, 5);
        for (int i = 0; i <lends.size(); i++) {
            System.out.println(lends.get(i));

        }
    }
    @Test
    public void test3(){
        BookDao bookDao = (BookDao) factory.getBean("bookDao");
        ArrayList<Book> allBooks = bookDao.getAllBooks(1, 5);
        System.out.println(allBooks);
    }
    @Test
    public void test4(){
        BookDao bookDao = (BookDao) factory.getBean("bookDao");
        Book book = bookDao.getBook(1);
        System.out.println(book);
    }
}
