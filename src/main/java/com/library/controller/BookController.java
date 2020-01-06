package com.library.controller;

import com.github.pagehelper.PageInfo;
import com.library.bean.Book;
import com.library.bean.ClassInfo;
import com.library.bean.Lend;
import com.library.bean.ReaderCard;
import com.library.service.BookService;
import com.library.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 图书控制器
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private LendService lendService;

    /**
     * 日期转换器
     * @param pubstr
     * @return
     */
    private Date getDate(String pubstr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.parse(pubstr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 查管理员阅图书
     * @param searchWord
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/querybook.html")
    public ModelAndView queryBookDo(String searchWord,@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "5") int size) {
        if (bookService.matchBook(searchWord)) {
            ArrayList<Book> books = bookService.queryBook(searchWord,page,size);
            PageInfo<Book> pageInfo = new PageInfo<>(books);
            ModelAndView modelAndView = new ModelAndView("admin_books_pages");
            modelAndView.addObject("pageInfo", pageInfo);
            return modelAndView;
        } else {
            return new ModelAndView("admin_books_pages", "error", "没有匹配的图书");
        }
    }

    /**
     * 读者查阅图书
     * @param searchWord
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/reader_querybook_do.html")
    public ModelAndView readerQueryBookDo(String searchWord,@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "5") int size) {
        if (bookService.matchBook(searchWord)) {
            ArrayList<Book> books = bookService.queryBook(searchWord,page,size);
            ModelAndView modelAndView = new ModelAndView("reader_books");
            PageInfo<Book> pageInfo = new PageInfo<>(books);
            modelAndView.addObject("pageInfo", pageInfo);
            return modelAndView;
        } else {
            return new ModelAndView("reader_books", "error", "没有匹配的图书");
        }
    }

    /**
     * 管理员查看全部图书
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/admin_books.html")
    public ModelAndView adminBooks(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "5") int size) {
        ArrayList<Book> books = bookService.getAllBooks(page, size);
        ModelAndView modelAndView = new ModelAndView("admin_books_pages");

        PageInfo<Book> pageInfo = new PageInfo<Book>(books);
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }

    /**
     * 添加图书页面展示
     * @return
     */
    @RequestMapping("/book_add.html")
    public ModelAndView addBook() {
        ModelAndView admin_book_add = new ModelAndView("admin_book_add");
        ArrayList<ClassInfo> allClass = bookService.getAllClass();
        admin_book_add.addObject("classList", allClass);
        return admin_book_add;
    }

    /**
     * 添加图书页面
     * @param pub_date
     * @param book
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/book_add_do.html", method = RequestMethod.POST)
    public String addBookDo(@RequestParam(value = "pub_date") String pub_date, Book book, RedirectAttributes redirectAttributes) {
        System.out.println(pub_date);
        book.setPubdate(getDate(pub_date));
        System.out.println(book);
        if (bookService.addBook(book)) {
            redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
        }
        return "redirect:/admin_books.html";
    }

    /**
     * 更新图书
     * @param request
     * @return
     */
    @RequestMapping("/updatebook.html")
    public ModelAndView bookEdit(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_edit");
        ArrayList<ClassInfo> allClass = bookService.getAllClass();
        modelAndView.addObject("classList", allClass);
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    /**
     * 编辑图书
     * @param pubstr
     * @param book
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/book_edit_do.html")
    public String bookEditDo(@RequestParam(value = "pubstr") String pubstr, Book book, RedirectAttributes redirectAttributes) {
        book.setPubdate(getDate(pubstr));
        if (bookService.editBook(book)) {
            redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书修改失败！");
        }
        return "redirect:/admin_books.html";
    }

    /**
     * 查看图书信息
     * @param request
     * @return
     */
    @RequestMapping("/admin_book_detail.html")
    public ModelAndView adminBookDetail(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    /**
     * 读者查看图书信息（仅供查看）
     * @param request
     * @return
     */
    @RequestMapping("/reader_book_detail.html")
    public ModelAndView readerBookDetail(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("reader_book_detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    /**
     * 管理员页面的header栏
     * @return
     */
    @RequestMapping("/admin_header.html")
    public ModelAndView admin_header() {
        return new ModelAndView("admin_header");
    }

    /**
     * 读者页面的header栏
     * @return
     */
    @RequestMapping("/reader_header.html")
    public ModelAndView reader_header() {
        return new ModelAndView("reader_header");
    }


    /**
     * 读者的图书界面（）
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/reader_books.html")
    public ModelAndView readerBooks(HttpServletRequest request,@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "5") int size) {
        ArrayList<Book> books = bookService.getAllBooks(page,size);
        PageInfo<Book> pageInfo = new PageInfo<>(books);
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ArrayList<Lend> myAllLendList = lendService.myLendList_back(readerCard.getReaderId());
//        System.out.println("※※※※※※※※※※"+myAllLendList.get(1));
        ArrayList<Long> myLendList = new ArrayList<>();
        for (int i = 0; i <myAllLendList.size(); i++) {
            //是否已归还
            System.out.println("这是lend"+myAllLendList.get(i));
            if (myAllLendList.get(i).getBackDate()==null){
                myLendList.add(myAllLendList.get(i).getBookId());
            }
        }
        ModelAndView modelAndView = new ModelAndView("reader_books");
        modelAndView.addObject("pageInfo", pageInfo);
        System.out.println("※※※这是myalllist"+myLendList);
        modelAndView.addObject("myLendList", myLendList);
        return modelAndView;

    }
}
