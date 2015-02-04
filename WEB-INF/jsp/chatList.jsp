<%@page import="Entity.AllInfo"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>チャットルーム一覧</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript" ></script>
<link href="/ChatSystem/css/bootstrap.min.css" rel="stylesheet">
<link href="/ChatSystem/css/bootstrap-responsive.min.css" rel="stylesheet">
<script src="/ChatSystem/js/bootstrap.min.js"></script>
</head>
<body>

<!-- ナビ -->
   <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <a href="/ChatSystem/chat/chatList" class="navbar-brand">ChatSystem</a>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
          <ul class="nav navbar-nav">
            <li>
              <a href="/ChatSystem/chat/myPage">マイページ</a>
            </li>
            <li>
            	<a href="/ChatSystem/loginout/logout">ログアウト</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
<!--END ナビ -->


    <div class="container">
<!-- ステータス -->
      <div class="page-header" id="banner">
       
        <div class="row">
        </div>
      </div>
            
	<!-- お知らせ表示 -->
		<!-- エラーメッセージ -->
			<c:if test="${error.message != null}">
				<div class="bs-component">
					<div class="alert alert-dismissable alert-danger">
					 <button type="button" class="close" data-dismiss="alert">&times;</button>
					 	<h4>Warning!</h4>
						<p>${error.message}</p>
					</div>
				</div>
			</c:if>
		<!--END エラーメッセージ -->
		<!-- 実行結果 -->
			<c:if test="${result !=null}">
				<div class="bs-component">
					<div class="alert alert-dismissable alert-info">
					  <button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>Info</h4>
						<p>${result}</p>
					</div>
				</div>
			</c:if>
		<!--END 実行結果 -->
	<!--END お知らせ表示 -->
<!--END ステータス -->

    <div class="row">
     <div class="col-lg-3"><!-- 左カラム -->
   <div class="panel panel-default">
    <div class="panel-body">
<!-- 参加中のチャット -->
      <!-- Tables
      ================================================== -->
      <div class="bs-docs-section">

          <div class="col-lg-12">
            <div class="page-header">
              <h2 id="tables">参加中のチャット</h2>
            </div>

            <div class="bs-component">
              <table class="table table-striped table-hover ">
				<tr>
					<th>ルームNo</th>
					<th>ルーム名</th>
				</tr>
				<c:forEach var="joinChatRoomList" items="${joinChatRoomList}">
				<tr>
					<td>${joinChatRoomList.chatId}</td>
					<td><a href="/ChatSystem/chat/chatRoom?chatId=${joinChatRoomList.chatId}">${joinChatRoomList.roomName}</a></td>
				</tr>
				</c:forEach>
			</table>
		    </div>
	      </div>
      </div>
<!--END 参加中のチャット -->
  　　　</div>
  　　</div>

	</div>
	<div class="col-lg-9"><!-- 右カラム -->
<!--  チャットルーム作成 -->
    <!-- Forms
      ================================================== -->
      	<div class="bs-docs-section">
          <div class="col-lg-12">
            <div class="page-header">
              <h2 id="forms">チャットルームを作成する</h2>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <div class="well bs-component">
              <form class="form-horizontal" action="/ChatSystem/chat/chatRoom" method="post">
                <fieldset>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">チャットルーム名</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEmail" placeholder="チャットルーム名" name="roomName">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">ルームパスワード</label>
                    <div class="col-lg-10">
                      <input type="password" class="form-control" id="inputEmail" placeholder="ルームパスワード" name="roomPass">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-2 control-label">ルームメンバーを選択する</label>
                    <div class="col-lg-10">
                     <c:forEach var="friendslist" items="${friendslist}">
                      <div class="checkbox">
                        	<label><input type="checkbox" name="chatMemberList" value="${friendslist.accountId}">${friendslist.name}</label>
                      </div>
				     </c:forEach>
                   </div>  
                  </div>  
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                      <button class="btn btn-default" type="reset">リセット</button>
                      <input type="hidden" name="method" value="register">
                      <button type="submit" class="btn btn-primary">ルームを作成</button>
                    </div>
                  </div>
                </fieldset>
              </form>
            </div>
          </div>
		</div>
<!--END  チャットルーム作成 -->

<!-- 友人検索 -->
	<div class="row">
		<div class="col-lg-12">
            <div class="page-header">
              <h2 id="tables">友人検索</h2>
            </div>
            <div class="well bs-component">
              <form class="form-horizontal" action="/ChatSystem/search" method="post">
                <fieldset>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">友人を検索</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEmail" placeholder="友人の名前かまたは何も入力せずに検索ボタンを押してください" name="searchName">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                      <button type="reset" class="btn btn-default">リセット</button>
                      <input type="hidden" name="method" value="search">
                      <input type="hidden" name="action" value="searchFriends">
                      <button type="submit" class="btn btn-primary">検索</button>
                    </div>
                  </div>
				</fieldset>
     		   </form>
			</div>
			<c:if test="${searchResult != null}">
	            <div class="bs-component">
	           		<table class="table table-striped table-hover ">
	           		<thead>
						<tr>
							<th>名前</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="searchResult" items="${searchResult}">
						<tr>
							<td>
								<a href="/ChatSystem/read${searchResult.url}">${searchResult.name}</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
					</table>
				</div>
			</c:if>	
		</div>
	</div>
<!--END 友人検索 -->	
	</div>
</div>



<!--  チャットルーム一覧 -->
<!-- 
   <div class="bs-docs-section">

        <div class="row">
          <div class="col-lg-12">
            <div class="page-header">
              <h2 id="tables">チャットルーム一覧</h2>
            </div>
            <div class="bs-component">
           		<table class="table table-striped table-hover ">
           		<thead>
					<tr>
						<th>ルームNo</th>
						<th>ルーム名</th>
						<th>ルームメンバー</th>
						<th>パスワード</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="chatList" items="${chatList}">
					<tr>
						<td>${chatList.chatId}</td>
						<td>${chatList.roomName}へ入室</td>
						<td>${chatList.chatMember}</td>
						<form action="/ChatSystem/loginout/chatRoom" method="post">
						<td>
							<input type="password"	name="roomPass">
						</td>
						<td>
							<input type="hidden" name="chatId" value="${chatList.chatId}">
							<input type="hidden" name="method" value="login">
							<input type="submit" class="btn btn-primary" value="入室する">
						</td>
						</form>
					</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>	
		  </div>	
	    </div>	
	</div>
-->			
<!--END  チャットルーム一覧 -->
</div>
</body>
</html>