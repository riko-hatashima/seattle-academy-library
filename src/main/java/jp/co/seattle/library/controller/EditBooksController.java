package jp.co.seattle.library.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.multipart.MultipartFile;

import jp.co.seattle.library.dto.BookDetailsInfo;
import jp.co.seattle.library.service.BooksService;
import jp.co.seattle.library.service.ThumbnailService;

@Controller
public class EditBooksController {
    /**
     * Handles requests for the application home page.
     */
    //APIの入り口

    final static Logger logger = LoggerFactory.getLogger(EditBooksController.class);

    @Autowired
    private BooksService booksService;

    @Autowired
    private ThumbnailService thumbnailService;

    /**
     * 編集画面に遷移する
     * @param model 編集したい本のID
     * @param bookId
     * @return
     */
    @RequestMapping(value = "/editBook", method = RequestMethod.POST) //value＝actionで指定したパラメータ
    //RequestParamでname属性を取得
    public String update(Locale locale, @RequestParam("bookId") int bookId,
            Model model) {
        //カテゴリを表示
        switch (booksService.getCategoryId(bookId)) {
        case 1:
            model.addAttribute("category1", "selected");
            break;
        case 2:
            model.addAttribute("category2", "selected");
            break;
        case 3:
            model.addAttribute("category3", "selected");
            break;
        case 4:
            model.addAttribute("category4", "selected");
            break;
        case 5:
            model.addAttribute("category5", "selected");
            break;
        case 6:
            model.addAttribute("category6", "selected");
            break;
        case 7:
            model.addAttribute("category7", "selected");
            break;
        case 8:
            model.addAttribute("category8", "selected");
            break;
        case 9:
            model.addAttribute("category9", "selected");
            break;
        }
        model.addAttribute("bookInfo", booksService.getBookInfo(bookId));
        return "editBook";
    }

    /**
     * 書籍情報を更新する
     * @param locale ロケール情報
     * @param title 書籍名
     * @param author 著者名
     * @param publisher 出版社
     * @param file サムネイルファイル
     * @param model モデル
     * @return 遷移先画面
     */

    @Transactional
    @RequestMapping(value = "/updateBook", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String updateBook(Locale locale,
            @RequestParam("bookId") Integer bookId,
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("publisher") String publisher,
            @RequestParam("thumbnail") MultipartFile file,
            @RequestParam("publishDate") String publishDate,
            @RequestParam("isbn") String isbn,
            @RequestParam("description") String description,
            @RequestParam("thumbnailUrl") String thumbnailURL,
            @RequestParam("category") int category,
            Model model) {
        logger.info("Welcome updateBooks.java! The client locale is {}.", locale);
        // パラメータで受け取った書籍情報をDtoに格納する。
        BookDetailsInfo bookInfo = new BookDetailsInfo();
        bookInfo.setBookId(bookId);
        bookInfo.setTitle(title);
        bookInfo.setAuthor(author);
        bookInfo.setPublisher(publisher);
        bookInfo.setPublishDate(publishDate);
        bookInfo.setIsbn(isbn);
        bookInfo.setDescription(description);
        bookInfo.setThumbnailUrl(thumbnailURL);
        bookInfo.setCategory(category);
        // クライアントのファイルシステムにある元のファイル名を設定する
        String thumbnail = file.getOriginalFilename();

        if (!file.isEmpty()) {
            try {
                // サムネイル画像をアップロード
                String fileName = thumbnailService.uploadThumbnail(thumbnail, file);
                // URLを取得
                String thumbnailUrl = thumbnailService.getURL(fileName);

                bookInfo.setThumbnailName(fileName);
                bookInfo.setThumbnailUrl(thumbnailUrl);

            } catch (Exception e) {

                // 異常終了時の処理
                logger.error("サムネイルアップロードでエラー発生", e);
                model.addAttribute("bookInfo", bookInfo);
                return "editBook";
            }
        }

        //  詳細画面に遷移する

        boolean isValidISBN = isbn.matches("[0-9]{0}|[0-9]{10}|[0-9]{13}");
        boolean flag = false;
        if (!(isValidISBN)) {
            model.addAttribute("isbnError", "ISBNは10桁または13桁で、半角数字で入力してください");
            flag = true;
        }
        bookInfo.setTitle(title);
        bookInfo.setAuthor(author);
        bookInfo.setPublisher(publisher);
        bookInfo.setPublishDate(publishDate);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        df.setLenient(false);
        try {
            df.parse(publishDate);

        } catch (ParseException e) {
            model.addAttribute("publishDateError", "日付はYYYYMMDD形式で入力してください");
            flag = true;
        }

        if (title.length() > 255) {
            model.addAttribute("titleLength", "タイトルは255文字以内で設定してください");
            flag = true;
        }

        if (author.length() > 255) {
            model.addAttribute("authorLength", "著者名は255文字以内で設定してください");
            flag = true;
        }
        if (publisher.length() > 255) {
            model.addAttribute("publisherLength", "出版社名は255文字以内で設定してください");
            flag = true;
        }
        if (publishDate.length() > 8) {
            model.addAttribute("publishDateLength", "日付はYYYYMMDD形式で入力してください");
            flag = true;
        }
        if (flag) {
            model.addAttribute("bookInfo", bookInfo);
            return "editBook";
        }
        // 書籍情報を編集

        booksService.editBook(bookInfo);
        booksService.insertCategory(bookId, bookInfo);
        model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));

        return "details3";

    }
}
