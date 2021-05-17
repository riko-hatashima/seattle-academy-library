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
                <li><a href="<%=request.getContextPath()%>/"><img class="mark" src="resources/img/walk_girl_run.png" />ログアウト</a></li>
            </ul>
                
        </div>
          
    </header>
    <main>
        <form action="<%=request.getContextPath()%>/bulkInsertBooks" method="post" enctype="multipart/form-data" id="data_upload_form">
            <h1>一括登録</h1>
            <div class="bulk_form">
                <h2>CSVファイルをアップロードすることで書籍を一括で登録できます。</h2>
                <div class="caution">
                    <p>「書籍名,著者名,出版社,出版日,ISBN,説明文」の形式で記載してください。</p>
                    <p>※サムネイル画像は一括登録できません。編集画面で１冊単位で登録してください。</p>
                </div>
                <c:forEach var="errorMessage" items="${errorMessage}">
                    <div class="error">${errorMessage}</div>
                </c:forEach>
                
                <c:if test="${!empty emptyFile}">
                    <div class="error">${emptyFile}</div>
                </c:if>
                <c:if test="${!empty nullBook}">
                    <div class="error">${nullBook}</div>
                </c:if>
                <div>
                    <div>
                        <input type="file" accept=".csv" id="inputCSVFile" name="inputCSVFile">
                    </div>
                </div>
                <div class="content_right">
                    <div class="addBookBtn_box">
                        <button type="submit" value="" name="bookId" class="btn_bulkRegist">一括登録</button>
                        <c:if test="${!empty bulkAddComplete}">
                            <div class="error_msg">${bulkAddComplete}</div>
                        </c:if>
                    </div>
                </div>
            </div>
        </form>
    </main>
</body>
</html>
