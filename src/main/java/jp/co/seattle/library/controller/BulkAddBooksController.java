package jp.co.seattle.library.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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

@Controller
public class BulkAddBooksController {
    final static Logger logger = LoggerFactory.getLogger(BulkAddBooksController.class);
   
    @Autowired
    private BooksService booksService;

    //一括登録画面に遷移するため
    @RequestMapping(value = "/bulkAddBooks", method = RequestMethod.GET)
    public String bulk(Model model) {
        return "bulkAddBooks";
    }

    //jsp作成する
    //一括登録のボタン→登録完了画面へ　ファイルを本格的に受け取る
    @Transactional
    @RequestMapping(value = "/bulkInsertBooks", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String bulkInsertBooks(Locale locale,
            @RequestParam("inputCSVFile") MultipartFile inputCSVFile, Model model) {
        List<String[]> bookData = new ArrayList<String[]>();

        try (InputStream stream = inputCSVFile.getInputStream();
            Reader reader = new InputStreamReader(stream);
                BufferedReader br = new BufferedReader(reader);) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] bulk = line.split(",", -1);
                bookData.add(bulk);
            }
        } catch (FileNotFoundException e) {
            model.addAttribute("notExistFile", "ファイルが存在しません");
        } catch (IOException e) {
            model.addAttribute("notReadFile", "ファイル読み込みに失敗しました");
        }
        boolean flag = false;
        List<String> errorMessage = new ArrayList<String>();
        for (int i = 0; i < bookData.size(); i++) {
            boolean isValidISBN = bookData.get(i)[4].matches("[0-9]{0}|[0-9]{10}|[0-9]{13}");


            if (bookData.get(i)[0].isEmpty() || bookData.get(i)[1].isEmpty() || bookData.get(i)[2].isEmpty()
                    || bookData.get(i)[3].isEmpty()) {
                errorMessage.add((i + 1) + "冊目に空欄の項目があります");
                flag = true;
            }
            else if (!(isValidISBN)) {
                errorMessage.add((i + 1) + "冊目のISBNは10桁または13桁で、半角数字で入力してください");
                flag = true;
            }
            if (!(bookData.get(i)[3].isEmpty())) {

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            df.setLenient(false);
            try {
                df.parse(bookData.get(i)[3]);

            } catch (ParseException e) {
                errorMessage.add((i + 1) + "冊目の日付はYYYYMMDD形式で入力してください");
                flag = true;
            }
        }

        }

        if (flag) {
            model.addAttribute("errorMessage", errorMessage);
            return "bulkAddBooks";
        }
        BookDetailsInfo bookInfo = new BookDetailsInfo();

        for (int i = 0; i < bookData.size(); i++) {
            bookInfo.setTitle(bookData.get(i)[0]);
            bookInfo.setAuthor(bookData.get(i)[1]);
            bookInfo.setPublisher(bookData.get(i)[2]);
            bookInfo.setPublishDate(bookData.get(i)[3]);
            bookInfo.setIsbn(bookData.get(i)[4]);
            bookInfo.setDescription(bookData.get(i)[5]);

            booksService.registBook(bookInfo);

        }

        model.addAttribute("bulkAddComplete", "登録完了");
        return "bulkAddBooks";

    }

}
