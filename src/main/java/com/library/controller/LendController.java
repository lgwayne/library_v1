package com.library.controller;

import com.github.pagehelper.PageInfo;
import com.library.bean.Lend;
import com.library.bean.ReaderCard;
import com.library.bean.ReaderInfo;
import com.library.service.BookService;
import com.library.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 借阅记录控制器
 */
@Controller
public class LendController {
    @Autowired
    private LendService lendService;

    @Autowired
    private BookService bookService;

    /**
     * 删除图书
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/deletebook.html")
    public String deleteBook(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));

        lendService.deleteLendByBookId(bookId);

        if (bookService.deleteBook(bookId)  ) {
            redirectAttributes.addFlashAttribute("succ", "图书删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书删除失败！");
        }
        return "redirect:/admin_books.html";
    }

    /**
     * 查看借阅记录
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/lendlist.html")
    public ModelAndView lendList(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "5") int size) {
        ModelAndView modelAndView = new ModelAndView("admin_lend_list");
        ArrayList<Lend> lends = lendService.lendList(page,size);
        PageInfo<Lend> pageInfo = new PageInfo<>(lends);
        modelAndView.addObject("pageInfo", pageInfo);

        return modelAndView;
    }

    /**
     * 查看个人借阅记录
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/mylend.html")
    public ModelAndView myLend(HttpServletRequest request,@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "5") int size) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ModelAndView modelAndView = new ModelAndView("reader_lend_list");

        ArrayList<Lend> lends = lendService.myLendList(readerCard.getReaderId(),page,size);
        PageInfo<Lend> pageInfo = new PageInfo<>(lends);
        modelAndView.addObject("pageInfo",pageInfo);

        return modelAndView;
    }

    /**
     * 删除借阅记录
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/deletelend.html")
    public String deleteLend(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long serNum = Long.parseLong(request.getParameter("serNum"));
        if (lendService.deleteLend(serNum) > 0) {
            redirectAttributes.addFlashAttribute("succ", "记录删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "记录删除失败！");
        }
        return "redirect:/lendlist.html";
    }

    /**
     * 读者借阅图书
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/lendbook.html")
    public String bookLend(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        long readerId = ((ReaderCard) request.getSession().getAttribute("readercard")).getReaderId();
        if (lendService.lendBook(bookId, readerId)) {
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
        }
        return "redirect:/reader_books.html";
    }

    /**
     * 读者归还图书
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/returnbook.html")
    public String bookReturn(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        long readerId = ((ReaderCard) request.getSession().getAttribute("readercard")).getReaderId();
        if (lendService.returnBook(bookId, readerId)) {
            redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书归还失败！");
        }
        return "redirect:/reader_books.html";
    }
}
