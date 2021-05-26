package jp.co.seattle.library.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.seattle.library.service.BooksService;

@Controller
public class CategoryBookController2 {
    final static Logger logger = LoggerFactory.getLogger(CategoryBookController2.class);

    @Autowired
    private BooksService booksService;


    /**
     * それぞれのカテゴリの書籍一覧
     * @param locale
     * @param category
     * @param model
     * @return
     */
    @Transactional
    @RequestMapping(value = "/bookCategory", method = RequestMethod.GET)
    public String displayBooks(Locale locale, @RequestParam("category") String category, Model model) {

        model.addAttribute("oneCategoryBooks", booksService.getBookCategory(category));
        return "categoryBook2";
    }
}
