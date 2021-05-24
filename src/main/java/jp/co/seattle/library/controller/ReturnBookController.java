
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
public class ReturnBookController {
    final static Logger logger = LoggerFactory.getLogger(ReturnBookController.class);
    @Autowired
    private BooksService booksService;

    /**
     * 書籍を返却する
     * @param locale
     * @param bookId　書籍ID
     * @param model
     * @return
     */
    @Transactional
    @RequestMapping(value = "/returnBook", method = RequestMethod.POST)
    public String returnBook(Locale locale, @RequestParam("bookId") Integer bookId, Model model) {

        logger.info("Welcome return! The client locale is {}.", locale);

        booksService.returnBook(bookId);
        model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
        model.addAttribute("returnBook", "貸し出し可");

        return "details3";
    }
}