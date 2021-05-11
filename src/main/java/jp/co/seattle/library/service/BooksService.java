package jp.co.seattle.library.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jp.co.seattle.library.dto.BookDetailsInfo;
import jp.co.seattle.library.dto.BookInfo;
import jp.co.seattle.library.rowMapper.BookDetailsInfoRowMapper;
import jp.co.seattle.library.rowMapper.BookInfoRowMapper;

/**
 * 書籍サービス
 * 
 *  booksテーブルに関する処理を実装する
 */
/**
 * @author user
 *
 */
@Service
public class BooksService {
    final static Logger logger = LoggerFactory.getLogger(BooksService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 書籍リストを取得する
     *
     * @return 書籍リスト
     */
    public List<BookInfo> getBookList() {

        // TODO 取得したい情報を取得するようにSQLを修正
        List<BookInfo> getedBookList = jdbcTemplate.query(
                "SELECT id,title,author,publisher,publish_date,thumbnail_url FROM books ORDER BY TITLE ASC",
                new BookInfoRowMapper());

        return getedBookList;
    }

    /**
     * 書籍IDに紐づく書籍詳細情報を取得する
     *
     * @param bookId 書籍ID
     * @return 書籍情報
     */
    public BookDetailsInfo getBookInfo(int bookId) {

        // JSPに渡すデータを設定する
        String sql = "SELECT * FROM books where id ="
                + bookId;

        BookDetailsInfo bookDetailsInfo = jdbcTemplate.queryForObject(sql, new BookDetailsInfoRowMapper());

        return bookDetailsInfo;
    }

    public int getBookId() {
        String sql = "SELECT MAX(id) FROM books";

        jdbcTemplate.queryForObject(sql, Integer.class);

        return jdbcTemplate.queryForObject(sql, Integer.class);

    }

    /**
     * 書籍を登録する
     *
     * @param bookInfo 書籍情報
     */
    public void registBook(BookDetailsInfo bookInfo) {
        String sql = "INSERT INTO books (title, author,publisher,thumbnail_name,thumbnail_url,publish_date,isbn,description,reg_date,upd_date) VALUES ('"
                + bookInfo.getTitle() + "','" + bookInfo.getAuthor() + "','" + bookInfo.getPublisher() + "','"
                + bookInfo.getThumbnailName() + "','"
                + bookInfo.getThumbnailUrl() + "','"
                + bookInfo.getPublishDate() + "','"
                + bookInfo.getIsbn() + "','"
                + bookInfo.getDescription() + "',"
                + "sysdate(),"
                + "sysdate())";

        jdbcTemplate.update(sql);
    }

    public void deleteBook(int bookId) {

        String sql = "DELETE FROM books WHERE id =" + bookId + ";";

        jdbcTemplate.update(sql);
    }

    /**
     * 書籍を編集する
     */

    public void editBook(BookDetailsInfo bookInfo) {
        String sql = "UPDATE books SET "
                + "title='" + bookInfo.getTitle() + "',"
                + "author='" + bookInfo.getAuthor() + "',"
                + "publisher='" + bookInfo.getPublisher() + "',"
                + "publish_date='" + bookInfo.getPublishDate() + "',"
                + "isbn='" + bookInfo.getIsbn() + "',"
                + "description='" + bookInfo.getDescription() + "',"
                + "upd_date=" + "sysdate()"
                + "WHERE id="
                + bookInfo.getBookId() + ";";

        jdbcTemplate.update(sql);

    }

    /**
     * 書籍を借りる
     */
    public void rentBook(int bookId) {
        String sql = "INSERT INTO rent (bookId) VALUES (" + bookId + ");";
        jdbcTemplate.update(sql);

    }


    /**
     * 貸し出し中の書籍情報を取得する
     * @param bookId　書籍ID
     * @return
     */
    public int getRentBookInfo(int bookId) {

        // JSPに渡すデータを設定する
        String sql = "SELECT id FROM rent WHERE bookId ="
                + bookId;

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }


    /**
     * 書籍を返却する
     * @param bookId　書籍ID
     */
    public void returnBook(int bookId) {
        String sql = "DELETE FROM rent WHERE bookId=" + bookId + ";";

        jdbcTemplate.update(sql);
    }

}
