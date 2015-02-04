<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>チャットルーム</title>
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
              <a href="/ChatSystem/fileManager/export?action=export&chatId=${chatRoomInfo.chatId}">エクスポート</a>
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
		<!-- チャットルーム参加メッセージ -->
			<c:if test="${joinMessage !=null}">
				<div class="bs-component">
					<div class="alert alert-dismissable alert-success">
					  <button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>Success</h4>
							<p>${loginUserInfo.name}さんは${joinMessage}</p>
					</div>
				</div>
			</c:if>
		<!--END チャットルーム参加メッセージ -->
	<!--END お知らせ表示 -->
	
<!--END ステータス -->
<div class="row">
  <div class="col-sm-6"><!-- 左カラム -->
  	<div class="panel panel-default">
     <div class="panel-body">
      <div class="bs-docs-section">

<!-- ルーム名 -->
	  <div class="col-lg-12">
	      <h2 id="tables">ルーム名</h2>
          <h4>${chatRoomInfo.roomName}</h4>
	  </div>
<!--END ルーム名 -->     
     
    <!-- ルームメンバー -->
      <!-- Tables
      ================================================== -->

          <div class="col-lg-12">
            <div class="page-header">
              <h2 id="tables">ルームメンバー</h2>
            </div>

            <div class="bs-component">
              <table class="table table-striped table-hover ">
				<c:forEach var="chatMemberList" items="${chatMemberList}">
				<tr>
					<td>
						<a href="">${chatMemberList.name}</a>
					</td>
					<td>
						<form action="/ChatSystem/chat/friendsRequestList" method="post">
							<input type="hidden" name="chatId" value="${chatRoomInfo.chatId}">
							<input type="hidden" name="friendsAccountId" value="${chatMemberList.accountId}">
								<c:if test="${chatMemberList.accountId == loginUserInfo.accountId}" var="ifResult" />
									<c:if test="${ifResult}">					
										<p>自分のアカウントです</p>
									</c:if>
									<c:if test="${!ifResult}">
										<input type="hidden" name="method" value="register">					
										<input type="submit" value="友達申請を送る" class="btn btn-primary">
									</c:if>
						</form>
					</td>
					<td>
						<form action ="/ChatSystem/chat/roomMember" method="post">
							<input type="hidden" name="chatId" value="${chatRoomInfo.chatId}">
							<input type="hidden" name="memberAccountId" value="${chatMemberList.accountId}">
							<c:if test="${chatMemberList.accountId == loginUserInfo.accountId}" var="ifResult" />
								<c:if test ="${ifResult}">
								</c:if>
								<c:if test ="${!ifResult}">
									<c:if test="${authorityInfo.adminFlg ==1}">
										<input type="hidden" name="method" value="modify">
										<input type="submit" value="ルームメンバーから外す" class="btn btn-primary">
									</c:if>
								</c:if>
						</form>
					</td>
					<td>
					<c:if test="${chatMemberList.adminFlg == 1}" var="adminResult" />
					<c:if test="${adminResult}">
						ルームの管理者
					</c:if>
					<c:if test="${!adminResult}">
						<c:if test="${authorityInfo.adminFlg ==1}">
							<form action="/ChatSystem/permit/roomMember" method="post">
								<input type="hidden" name="chatId" value="${chatRoomInfo.chatId}">
								<input type="hidden" name="accountId" value="${chatMemberList.accountId}">
								<button class="btn btn-primary" type="submit" name="method" value="permit">管理者にする</button>
							</form>
						</c:if>
					</c:if>
					</td>
				</tr>
			</c:forEach>
			</table>
		   </div>	
		  </div>	
		</div>
		<form action="/ChatSystem/chat/roomMember" method="post">
			<input type="hidden" name="chatId" value="${chatRoomInfo.chatId}">
			<input type="hidden" name="accountId" value="${loginUserInfo.accountId}">
			<input type="hidden" name="method" value="remove">
			<input type="submit" value="メンバーから抜ける" class="btn btn-primary btn-lg btn-block">
	   </form>
<!--END ルームメンバー -->

<!-- メンバーの追加 -->
    <!-- Forms
      ================================================== -->
      	<div class="bs-docs-section">
          <div class="col-lg-12">
            <div class="page-header">
              <h2 id="forms">ルームメンバーの追加</h2>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <div class="well bs-component">
              <form class="form-horizontal" action="/ChatSystem/chat/roomMember" method="post">
                <fieldset>
                  <div class="form-group">
               	  <c:if test="${friendsListIsNull == null}" var="nullTest" />
                  	<c:if test="${!nullTest}">
		             <label class="col-lg-3 control-label">友達を選択する</label>
		             <div class="col-lg-9">
		              <c:forEach var="friendsList" items="${friendsList}">
				       <div class="checkbox">
				        <label><input type="checkbox" name="addChatMemberList" value="${friendsList.accountId}">${friendsList.name}</label>
				       </div>
					  </c:forEach>
		             </div>
                  </div>  
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                      <input type="hidden" name="method" value="register">
                      <input type="hidden" name="chatId" value="${chatRoomInfo.chatId}">
                      <button type="submit" class="btn btn-primary">メンバーを追加</button>
                    </div>
                  </div>
                  </c:if>
                  <c:if test="${nullTest}">
		           <div class="col-lg-9 col-lg-offset-3">
		            <p>ルームに追加できる友達はおりません</p>
		           </div>
                  </c:if>
                </fieldset>
              </form>
            </div>
          </div>
		</div>
<!--END メンバーの追加 -->


<!-- リロード -->
      <div class="bs-docs-section">

            <p class="bs-component">
              <a href="/ChatSystem/chat/chatRoom?chatId=${chatRoomInfo.chatId}" class="btn btn-primary btn-lg btn-block">リロード</a>
            </p>
      </div>      
<!--END リロード -->

  　　　</div>
  　　</div>
　　</div>
  <div class="col-sm-6"><!-- 中心 -->
  
  
  <!-- 新規投稿 -->
      <!-- Forms
      ================================================== -->
     <div class="panel panel-default">
       <div class="panel-body">
      <div class="bs-docs-section">
          <div class="col-lg-12">
            <div class="well bs-component">
              <form class="form-horizontal" action="/ChatSystem/chat/chatContent" method="post">
                <fieldset>
                  <div class="form-group">
                    <label for="textArea" class="col-lg-2 control-label">内容</label>
                    <div class="col-lg-10">
                      <textarea name="content" class="form-control" rows="3" id="textArea" placeholder="つぶやいてみてください"></textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                      <button class="btn btn-default" type="reset">リセット</button>
					　　<input type="hidden" name="chatId" value="${chatRoomInfo.chatId}">
					　　<input type="hidden" name="accountId" value="${loginUserInfo.accountId}">
					　　<input type="hidden" name="method" value="register">
                      <button type="submit" class="btn btn-primary">送信する</button>
                    </div>
                  </div>
                </fieldset>
              </form>
              <form class="form-horizontal" enctype="multipart/form-data" action="/ChatSystem/fileManager/upload" method="post">
                <fieldset>
                  <div class="form-group">
                    <label for="textArea" class="col-lg-2 control-label"></label>
                    <div class="col-lg-10">
                      <input type="file" name="filename" size="30">
                    </div>
                  </div>
                 <div class="form-group">
                    <label for="textArea" class="col-lg-2 control-label"></label>
                    <div class="col-lg-10">
                      <button type="submit" class="btn btn-primary">アップロード</button>
					　　<input type="hidden" name="chatId" value="${chatRoomInfo.chatId}">
					　　<input type="hidden" name="accountId" value="${loginUserInfo.accountId}">
                    </div>
                  </div>
                </fieldset>
              </form>
            </div>
          </div>
        </div>
          
<!--END 新規投稿 -->
  
  
<!-- チャット内容の表示 -->
      <!-- Containers
      ================================================== -->
      <div class="bs-docs-section">

          <div class="col-lg-12">
	            <div class="bs-component">
					<c:forEach var="chatContentsList" items="${chatContentsList}">
		              <div class="panel panel-primary">
		                <div class="panel-heading">
		                  <h3 class="panel-title">名前:<a href="">${chatContentsList.name}</a></h3>
		                  <p>日時:${chatContentsList.time}</p>
		                </div>
		                <div class="panel-body">
		                	<c:if test="${chatContentsList.filePath != null}" var="ifResult" />
			                	<c:if test="${ifResult}">
			                		<form action="/ChatSystem/fileManager/download" method="post">
			                			<input type="hidden" name="filePath" value="${chatContentsList.filePath}">
			                			<input type="hidden" name="fileName" value="${chatContentsList.returnContent}">
					  					<input type="hidden" name="action" value="download">
										<button value="submit">${chatContentsList.returnContent}</button>			                		
			                		</form>
				                </c:if>
				                <c:if test="${!ifResult}">
				                  <p>${chatContentsList.returnContent}</p>
				                </c:if>
		                </div>
		              </div>
					</c:forEach>
	            </div>
	      </div>
	 </div>
<!--END チャット内容の表示 -->
  　　　</div>
  　　</div>
  </div>
</div>

</div>
</body>
</html>
	