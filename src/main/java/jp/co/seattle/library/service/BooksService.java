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
                "SELECT bookid,title,author,publisher,publish_date,thumbnail_url FROM books ORDER BY TITLE ASC",
                new BookInfoRowMapper());

        return getedBookList;
    }

    /**
     * 選択したカテゴリの書籍情報を取得
     * @param bookInfo
     * @return
     */

    public List<BookInfo> getBookCategory(String category) {
        String sql = "SELECT bookid,title,author,publisher,publish_date,thumbnail_url,categoryid FROM books WHERE categoryid="
                + category + " ORDER BY TITLE ASC;";
        List<BookInfo> categoryBookInfo = jdbcTemplate.query(sql, new BookInfoRowMapper());
        return categoryBookInfo;
    }

    /**
     * 選択した書籍のカテゴリを取得
     * @param bookId
     * @return
     */
    public int getCategoryId(int bookId) {
        String sql = "SELECT category FROM categories INNER JOIN books ON categories.id=books.categoryid WHERE bookid="+bookId+";";
        return jdbcTemplate.queryForObject(sql, Integer.class);

    }

    /**
     * 書籍IDに紐づく書籍詳細情報を取得する
     *
     * @param bookId 書籍ID
     * @return 書籍情報
     */

    public int getBookId() {
        String sql = "SELECT MAX(bookid) FROM books;";

        return jdbcTemplate.queryForObject(sql, Integer.class);

    }

    public BookDetailsInfo getBookInfo(int bookId) {

        // JSPに渡すデータを設定する
        String sql = "SELECT * FROM books WHERE bookid ="
                + bookId + ";";

        //categoryがnullになっている
        BookDetailsInfo bookDetailsInfo = jdbcTemplate.queryForObject(sql, new BookDetailsInfoRowMapper());

        return bookDetailsInfo;
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

    /**
     * 書籍を削除する
     * @param bookId
     */
    public void deleteBook(int bookId) {

        String sql = "DELETE FROM books WHERE bookid =" + bookId + ";";

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
                + "thumbnail_name='" + bookInfo.getThumbnailName() + "',"
                + "thumbnail_url='" + bookInfo.getThumbnailUrl() + "',"
                + "publish_date='" + bookInfo.getPublishDate() + "',"
                + "isbn='" + bookInfo.getIsbn() + "',"
                + "description='" + bookInfo.getDescription() + "',"
                + "upd_date=" + "sysdate()"
                + "WHERE bookid="
                + bookInfo.getBookId() + ";";

        jdbcTemplate.update(sql);

    }

    /**
     * 書籍を借りる
     */
    public void rentBook(int bookId) {
        String sql = "INSERT INTO rent (bookid) VALUES (" + bookId + ");";
        jdbcTemplate.update(sql);

    }

    /**
     * 貸し出し中の書籍情報を取得する
     * @param bookId　書籍ID
     * @return
     */
    public int getRentBookInfo(int bookId) {

        // JSPに渡すデータを設定する
        String sql = "SELECT id FROM rent WHERE bookid ="
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
        String sql = "DELETE FROM rent WHERE bookid=" + bookId + ";";

        jdbcTemplate.update(sql);
    }

    /**
     * booksTBLにcategoryidをセットする
     * @param bookId
     * @param bookInfo
     */
    public void insertCategory(int bookId, BookDetailsInfo bookInfo) {
        String sql = " UPDATE books SET categoryid=" + bookInfo.getCategory() + " WHERE bookid=" + bookId + ";";
        jdbcTemplate.update(sql);

    }


    public String searchTitle(String title) {
        String sql = "SELECT bookid,title,author,publisher,publish_date,thumbnail_url,categoryid FROM books WHERE title like '"
                + title + "%';";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

}
