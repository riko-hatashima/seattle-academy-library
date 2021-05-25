<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=utf8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>ホーム｜シアトルライブラリ｜シアトルコンサルティング株式会社</title>
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
                <li><a href="<%=request.getContextPath()%>/"><img class="mark" src="resources/img/walk_girl_run.png" />ログアウト</a></li>
                 </ul>
       
        
           
        </div>
    </header>
 
    <main>
        <h1>Home</h1>
        <a href="<%=request.getContextPath()%>/addBook" class="btn_add_book">書籍の追加</a> <a href="<%=request.getContextPath()%>/bulkAddBooks" class="btn_bulk_book">一括登録</a>
         <div class="search"><img class="mark" src="resources/img/magnifier_mushimegane_blank.png" />
        <form action="<%=request.getContextPath()%>/searchResult" method="post" enctype="multipart/form-data" id="data_upload_form">
            <c:if test="${!empty bookInfo}">
                <input required type="text" name="searchTitle" value=>
            </c:if>
            <c:if test="${empty bookInfo}">
                <input required type="text" name="searchTitle" placeholder="検索">
            </c:if></form>
        </div>
        
        
        <c:if test="${!empty resultMessage}">
            <div class="error_msg">${resultMessage}</div>
        </c:if>
        <div class="categoryList1">
            <div class="categoryName">
                <form method="get" class="category_thumbnail" action="<%=request.getContextPath()%>/bookCategory">
                    <input type="hidden" id="category" name="category" value="2"> <a href="javascript:void(0)" onclick="this.parentNode.submit();"> <img class="categoryImg" src="resources/img/1.png">
                    </a>
                </form>
            </div>
            <div class="categoryName">
                <form method="get" class="category_thumbnail" action="<%=request.getContextPath()%>/bookCategory">
                    <input type="hidden" id="category" name="category" value="3"> <a href="javascript:void(0)" onclick="this.parentNode.submit();"> <img class="categoryImg" src="resources/img/2.png">
                    </a>
                </form>
            </div>
            <div class="categoryName">
                <form method="get" class="category_thumbnail" action="<%=request.getContextPath()%>/bookCategory">
                    <input type="hidden" id="category" name="category" value="4"> <a href="javascript:void(0)" onclick="this.parentNode.submit();"> <img class="categoryImg" src="resources/img/3.png">
                    </a>
                </form>
            </div>
            <div class="categoryName">
                <form method="get" class="category_thumbnail" action="<%=request.getContextPath()%>/bookCategory">
                    <input type="hidden" id="category" name="category" value="5"> <a href="javascript:void(0)" onclick="this.parentNode.submit();"> <img class="categoryImg" src="resources/img/4.png">
                    </a>
                </form>
            </div>
        </div>
        <div class="categoryList2">
            <div class="categoryName">
                <form method="get" class="category_thumbnail2" action="<%=request.getContextPath()%>/bookCategory">
                    <input type="hidden" id="category" name="category" value="6"> <a href="javascript:void(0)" onclick="this.parentNode.submit();"> <img class="categoryImg2" src="resources/img/5.png">
                    </a>
                </form>
            </div>
            <div class="categoryName">
                <form method="get" class="category_thumbnail2" action="<%=request.getContextPath()%>/bookCategory">
                    <input type="hidden" id="category" name="category" value="7"> <a href="javascript:void(0)" onclick="this.parentNode.submit();"> <img class="categoryImg2" src="resources/img/6.png">
                    </a>
                </form>
            </div>
            <div class="categoryName">
                <form method="get" class="category_thumbnail2" action="<%=request.getContextPath()%>/bookCategory">
                    <input type="hidden" id="category" name="category" value="8"> <a href="javascript:void(0)" onclick="this.parentNode.submit();"> <img class="categoryImg2" src="resources/img/7.png">
                    </a>
                </form>
            </div>
            <div class="categoryName">
                <form method="get" class="category_thumbnail2" action="<%=request.getContextPath()%>/bookCategory">
                    <input type="hidden" id="category" name="category" value="9"> <a href="javascript:void(0)" onclick="this.parentNode.submit();"> <img class="categoryimg2" src="resources/img/8.png">
                    </a>
                </form>
            </div>
            <div class="categoryName">
                <form method="get" class="category_thumbnail2" action="<%=request.getContextPath()%>/bookCategory">
                    <input type="hidden" id="category" name="category" value="1"> <a href="javascript:void(0)" onclick="this.parentNode.submit();"> <img class="categoryimg2" src="resources/img/9.png">
                    </a>
                </form>
               
            </div>
        </div>
    </main>
</body>
</html>
