<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<title>書籍の追加｜シアトルライブラリ｜シアトルコンサルティング株式会社</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP" rel="stylesheet">
<link href="<c:url value="/resources/css/default.css" />" rel="stylesheet" type="text/css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="resources/js/thumbnail.js"></script>
<script src="resources/js/addBtn.js"></script>
</head>
<body class="wrapper">
      
    <header>
            
        <div class="left">
                  <img class="mark" src="resources/img/logo.png" />       
            <div class="logo">Seattle Library</div>
                
        </div>
            
        <div class="right">
                  
            <ul>
                <li><a href="<%=request.getContextPath()%>/home" class="menu"><img class="mark" src="resources/img/ie_mark_ikkai.png" />Home</a></li>
                <li><a href="<%=request.getContextPath()%>/"> <img class="mark" src="resources/img/walk_girl_run.png" />Logout
                </a></li>
            </ul>
                
        </div>
          
    </header>
      
    <main>
        <form action="<%=request.getContextPath()%>/insertBook" method="post" enctype="multipart/form-data" id="data_upload_form">
            <h1>書籍の追加</h1>
            <div class="content_body add_book_content">
                <div class ="filein">
                    <span>書籍の画像</span> <span class="care care1">任意</span>
                    <div class="book_thumnail">
                        <img class="book_noimg" src="resources/img/no image.png">
                    </div>
                    <input type="file" accept="image/*" name="thumbnail" id="thumbnail">
                </div>
                <div class="content_right">
                    <div>
                        <c:if test="${!empty titleLength}">
                            <div class="error">${titleLength}</div>
                        </c:if>
                        <c:if test="${!empty authorLength}">
                            <div class="error">${authorLength}</div>
                        </c:if>
                        <c:if test="${!empty publisherLength}">
                            <div class="error">${publisherLength}</div>
                        </c:if>
                        <c:if test="${!empty publishDateLength}">
                            <div class="error">${publishDateLength}</div>
                        </c:if>
                        <c:if test="${!empty publishDateError}">
                            <div class="error">${publishDateError}</div>
                        </c:if>
                        <c:if test="${!empty isbnError}">
                            <div class="error">${isbnError}</div>
                        </c:if>
                        <span>書籍名</span><span class="care care2">必須</span> <select name="category">
                            <option value="other" selected>その他</option>
                            <option value="pictureBook">絵本</option>
                            <option value="novel">小説</option>
                            <option value="comic">漫画</option>
                            <option value="magazine">雑誌</option>
                            <option value="pratical">実用書</option>
                            <option value="business">ビジネス</option>
                            <option value="study">学習</option>
                            <option value="technical">専門書</option>
                        </select>
                        <c:if test="${!empty bookInfo}">
                            <input required type="text" name="title" value="${bookInfo.title}">
                        </c:if>
                        <c:if test="${empty bookInfo}">
                            <input required type="text" name="title" autocomplete="off">
                        </c:if>
                    </div>
                    <div>
                        <span>著者名</span><span class="care care2">必須</span>
                        <c:if test="${!empty bookInfo}">
                            <input required type="text" name="author" value="${bookInfo.author}">
                        </c:if>
                        <c:if test="${empty bookInfo}">
                            <input required type="text" name="author" autocomplete="off">
                        </c:if>
                    </div>
                    <div>
                        <span>出版社</span><span class="care care2">必須</span>
                        <c:if test="${!empty bookInfo}">
                            <input required type="text" name="publisher" value="${bookInfo.publisher}">
                        </c:if>
                        <c:if test="${empty bookInfo}">
                            <input required type="text" name="publisher">
                        </c:if>
                    </div>
                    <div>
                        <span>出版日</span><span class="care care2">必須</span>
                        <c:if test="${!empty bookInfo}">
                            <input required type="text" name="publishDate" value="${bookInfo.publishDate}">
                        </c:if>
                        <c:if test="${empty bookInfo}">
                            <input required type="text" name="publishDate" placeholder="YYYYMMDD">
                        </c:if>
                    </div>
                    <div>
                        <span>ISBN</span><span class="care care1">任意</span>
                        <c:if test="${!empty bookInfo}">
                            <input type="text" name="isbn" value="${bookInfo.isbn}">
                        </c:if>
                        <c:if test="${empty bookInfo}">
                            <input type="text" name="isbn">
                        </c:if>
                    </div>
                    <div>
                        <span>説明文</span><span class="care care1">任意</span>
                        <c:if test="${!empty bookInfo}">
                            <input type="text" name="description" value="${bookInfo.description}">
                        </c:if>
                        <c:if test="${empty bookInfo}">
                            <input type="text" name="description">
                        </c:if>
                    </div>
                    <input type="hidden" id="bookId" name="bookId" value="${bookInfo.bookId}">
                </div>
            </div>
            <div class="addBookBtn_box">
                <button type="submit" id="add-btn" class="btn_addBook">登録</button>
            </div>
        </form>
    </main>
</body>
</html>
