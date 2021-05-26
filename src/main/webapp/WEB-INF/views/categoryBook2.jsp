<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=utf8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>書籍の一覧｜シアトルライブラリ｜シアトルコンサルティング株式会社</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP" rel="stylesheet">
<link href="<c:url value="/resources/css/default.css" />" rel="stylesheet" type="text/css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet" type="text/css">
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
                <li><a href="<%=request.getContextPath()%>/"><img class="mark" src="resources/img/walk_girl_run.png" />Logout</a></li>
            </ul>
        </div>
    </header>
    <main>
        <h1>書籍一覧</h1>
        <div class="menu">
        <a href="<%=request.getContextPath()%>/addBook" class="btn_add_book">書籍の追加</a> <a href="<%=request.getContextPath()%>/bulkAddBooks" class="btn_bulk_book">一括登録</a>
         <div class="search">
        <form action="<%=request.getContextPath()%>/searchResult" method="post" enctype="multipart/form-data" id="data_upload_form">
            <c:if test="${!empty bookInfo}">
                <input required type="search" name="searchTitle" value=>
            </c:if>
            <c:if test="${empty bookInfo}">
                <input required type="search" name="searchTitle" placeholder="検索">
            </c:if></form>
        </div>
         <c:if test="${!empty searchError}">
            <div class="error_msg">${searchError}</div>
        </c:if></div>
        <c:if test="${!empty resultMessage}">
            <div class="error_msg">${resultMessage}</div>
        </c:if>
            <div>
                <div class="oneCategoryBooks">
                    <c:forEach var="bookInfo" items="${oneCategoryBooks}">
                        <input type="hidden" name="category" value="${bookDetailsInfo.category}">
                        <div class="aBook">
                            <form method="post" class="book_thumnail" action="<%=request.getContextPath()%>/details">
                                <a href="javascript:void(0)" onclick="this.parentNode.submit();"> <c:if test="${empty bookInfo.thumbnail}">
                                        <img class="book_noimg" src="resources/img/no image.png">
                                    </c:if> <c:if test="${!empty bookInfo.thumbnail}">
                                        <img class="book_noimg" src="${bookInfo.thumbnail}">
                                    </c:if> <input type="hidden" id="bookId" name="bookId" value="${bookInfo.bookId}">
                                </a>
                            </form>
                            <input type="hidden" name="category" value="${bookInfo.category}">
                            <ul>
                                <li class="book_title">${bookInfo.title}</li>
                                <li class="book_author">${bookInfo.author}（著）</li>
                                <li class="book_publisher">出版社:${bookInfo.publisher}</li>
                                <li class="book_publish_date">出版日:${bookInfo.publishDate}</li>
                            </ul>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
