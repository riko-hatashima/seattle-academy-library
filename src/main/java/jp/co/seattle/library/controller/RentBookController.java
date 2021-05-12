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
public class RentBookController {
    final static Logger logger = LoggerFactory.getLogger(RentBookController.class);
    @Autowired
    private BooksService booksService;

    /**
     * 書籍を借りる
     * @param locale
     * @param bookId　書籍ID
     * @param model
     * @return
     */
    @Transactional
    @RequestMapping(value = "/rentBook", method = RequestMethod.POST)
    public String rentBook(Locale locale, @RequestParam("bookId") Integer bookId, Model model) {
        logger.info("Welcome rent! The client locale is {}.", locale);

        if (booksService.getRentBookInfo(bookId) != 0) {

            model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
            model.addAttribute("cantRent", "貸し出し中");

            return "details";
        }

        booksService.rentBook(bookId);
        model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
        model.addAttribute("cantRent", "貸し出し中");

        return "details";
    }
}