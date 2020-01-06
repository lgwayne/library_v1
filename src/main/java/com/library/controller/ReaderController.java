package com.library.controller;

import com.github.pagehelper.PageInfo;
import com.library.bean.ReaderCard;
import com.library.bean.ReaderInfo;
import com.library.service.LoginService;
import com.library.service.ReaderCardService;
import com.library.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 读者控制器
 */
@Controller
public class ReaderController {
    @Autowired
    private ReaderInfoService readerInfoService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ReaderCardService readerCardService;

    private ReaderInfo getReaderInfo(long readerId, String name, String sex, String birth, String address, String phone) {
        ReaderInfo readerInfo = new ReaderInfo();
        Date date = new Date();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        readerInfo.setAddress(address);
        readerInfo.setName(name);
        readerInfo.setReaderId(readerId);
        readerInfo.setPhone(phone);
        readerInfo.setSex(sex);
        readerInfo.setBirth(date);
        return readerInfo;
    }

    /**
     * 查看所有读者
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("allreaders.html")
    public ModelAndView allBooks(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "5") int size) {
        ArrayList<ReaderInfo> readers = readerInfoService.readerInfos(page,size);
        ModelAndView modelAndView = new ModelAndView("admin_readers");
        PageInfo<ReaderInfo> pageInfo = new PageInfo<>(readers);
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }

    /**
     * 删除读者
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("reader_delete.html")
    public String readerDelete(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        long readerId = Long.parseLong(request.getParameter("readerId"));
//        System.out.println("※※※※※※※※※※※※※※※※※※※※※※※※※这是读者id"+readerId);
        boolean b = readerCardService.deleteReaderCard(readerId);
        System.out.println("删除card结果："+b);
        boolean b1 = readerInfoService.deleteReaderInfo(readerId);
        System.out.println("删除info结果："+b1);
        if (b && b1) {
            redirectAttributes.addFlashAttribute("succ", "删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "删除失败！");
        }
        return "redirect:/allreaders.html";
    }

    /**
     * 读者详细信息
     * @param request
     * @return
     */
    @RequestMapping("/reader_info.html")
    public ModelAndView toReaderInfo(HttpServletRequest request) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerCard.getReaderId());
        ModelAndView modelAndView = new ModelAndView("reader_info");
        modelAndView.addObject("readerinfo", readerInfo);
        return modelAndView;
    }

    /**
     * 编辑读者信息的页面
     * @param request
     * @return
     */
    @RequestMapping("reader_edit.html")
    public ModelAndView readerInfoEdit(HttpServletRequest request) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerId);
        ModelAndView modelAndView = new ModelAndView("admin_reader_edit");
        modelAndView.addObject("readerInfo", readerInfo);
        return modelAndView;
    }

    /**
     * 编辑读者信息
     * @param request
     * @param name
     * @param sex
     * @param birth
     * @param address
     * @param phone
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("reader_edit_do.html")
    public String readerInfoEditDo(HttpServletRequest request, String name, String sex, String birth, String address, String phone, RedirectAttributes redirectAttributes) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        ReaderInfo readerInfo = getReaderInfo(readerId, name, sex, birth, address, phone);
        if (readerInfoService.editReaderInfo(readerInfo) && readerInfoService.editReaderCard(readerInfo)) {
            redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
        }
        return "redirect:/allreaders.html";
    }

    /**
     * 添加读者信息页面
     * @return
     */
    @RequestMapping("reader_add.html")
    public ModelAndView readerInfoAdd() {
        return new ModelAndView("admin_reader_add");
    }

    /**
     * 添加读者信息
     * @param name
     * @param sex
     * @param birth
     * @param address
     * @param phone
     * @param password
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("reader_add_do.html")
    public String readerInfoAddDo(String name, String sex, String birth, String address, String phone, String password, RedirectAttributes redirectAttributes) {
        ReaderInfo readerInfo = getReaderInfo(0, name, sex, birth, address, phone);
        long readerId = readerInfoService.addReaderInfo(readerInfo);
        readerInfo.setReaderId(readerId);
        System.out.println("※※※※※※※※※※※※※※※※※※※"+readerInfo);
        if (readerId > 0 && readerCardService.addReaderCard(readerInfo, password)) {
            redirectAttributes.addFlashAttribute("succ", "添加读者信息成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "添加读者信息失败！");
        }
        return "redirect:/allreaders.html";
    }

    /**
     * 编辑读者信息页面
     * @param request
     * @return
     */
    @RequestMapping("reader_info_edit.html")
    public ModelAndView readerInfoEditReader(HttpServletRequest request) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerCard.getReaderId());
        ModelAndView modelAndView = new ModelAndView("reader_info_edit");
        modelAndView.addObject("readerinfo", readerInfo);
        return modelAndView;
    }

    /**
     * 编辑读者信息
     * @param request
     * @param name
     * @param sex
     * @param birth
     * @param address
     * @param phone
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("reader_edit_do_r.html")
    public String readerInfoEditDoReader(HttpServletRequest request, String name, String sex, String birth, String address, String phone, RedirectAttributes redirectAttributes) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo = getReaderInfo(readerCard.getReaderId(), name, sex, birth, address, phone);
        if (readerInfoService.editReaderInfo(readerInfo) && readerInfoService.editReaderCard(readerInfo)) {
            ReaderCard readerCardNew = loginService.findReaderCardByReaderId(readerCard.getReaderId());
            request.getSession().setAttribute("readercard", readerCardNew);
            redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "信息修改失败！");
        }
        return "redirect:/reader_info.html";
    }
}
