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

/**
 * 削除コントローラー
 */
@Controller //APIの入り口
public class DeleteBookController {
    final static Logger logger = LoggerFactory.getLogger(DeleteBookController.class);

    //削除ボタン  

    @Autowired
    private BooksService booksService;

    /**
     * 対象書籍を削除する
     *
     * @param locale ロケール情報
     * @param bookId 書籍ID
     * @param model モデル情報
     * @return 遷移先画面名
     */
    @Transactional
    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    public String deleteBook(
            Locale locale,
            @RequestParam("bookId") Integer bookId,
            Model model) {
        logger.info("Welcome delete! The client locale is {}.", locale);
        /**
         * 貸し出し可能な書籍のみ削除する
         */
        if (booksService.getRentBookInfo(bookId) == 0) {
            booksService.deleteBook(bookId);
            model.addAttribute("bookList", booksService.getBookList());
            return "home1";
        }

        model.addAttribute("deleteError", "貸し出し中のため本を削除できません");
        model.addAttribute("cantRent", "貸し出し中");
        model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
        return "details3";

    }
}
