<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>マイページ</title>
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
		<!-- お知らせその2　実行結果 -->
			<c:if test="${result !=null}">
				<div class="bs-component">
					<div class="alert alert-dismissable alert-info">
					  <button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>Info</h4>
						<p>${result}</p>
					</div>
				</div>
			</c:if>
		<!--END お知らせその2　実行結果 -->
	<!--END お知らせ表示 -->
<!--END ステータス -->

<div class="row">
  <div class="col-sm-3"><!-- 左カラム -->
   <div class="panel panel-default">
    <div class="panel-body">
<!-- フレンドリスト -->
	<!-- 友達一覧 -->
	      <!-- Tables
	      ================================================== -->
	      <div class="bs-docs-section">
	
	          <div class="col-lg-12">
	            <div class="page-header">
	              <h2 id="tables">友達一覧</h2>
	            </div>
	
	            <div class="bs-component">
	           	<form action="/ChatSystem/chat/friendsList" method="post">
	              <table class="table table-striped table-hover ">
						<tr>
							<th>友達名</th>
							<th>ステータス</th>
						</tr>	
					<c:forEach var="friendsList" items="${friendsList}">
						<tr>
							<td>${friendsList.name}さん</td>
								<td>
									<input type="hidden" name="method" value="remove">
									<button type="submit" name="friendsAccountId" value="${friendsList.accountId}" class="btn btn-primary">フォローを解除します</button>
								</td>
						</tr>	
					</c:forEach>
					</table>
				</form>
				</div>	
			   </div>	
		</div>
	<!--END 友達一覧 -->

	<!-- 友達リクエスト一覧 -->
		      <!-- Tables
		      ================================================== -->
		      <div class="bs-docs-section">
		
		          <div class="col-lg-12">
		            <div class="page-header">
		              <h2 id="tables">友達リクエスト一覧</h2>
		            </div>
		
		            <div class="bs-component">
					  <form action="/ChatSystem/chat/friendsRequestList" method="post">
		              <table class="table table-striped table-hover ">
						<tr>
							<th>友達名</th>
							<th>ステータス</th>
						</tr>	
					<c:forEach var="friendsRequestList" items="${friendsRequestList}">
						<tr>
							<td>${friendsRequestList.name}さん</td>
								<td>
									<input type="hidden" name="method" value="modify">
									<button type="submit" name="friendsAccountId" value="${friendsRequestList.accountId}" class="btn btn-primary">承認する</button>
								</td>
						</tr>	
					</c:forEach>
	 				  </table>
					  </form>
				　　　</div>	
			　　　　　</div>	
	　　　　　	　　</div>	
	<!--END 友達リクエスト一覧 -->
<!--END　フレンドリスト -->
    </div>
   </div>
  </div>
  <div class="col-sm-6"><!-- 中心カラム -->
   <div class="panel panel-default">
    <div class="panel-body">
<!-- 会員情報一覧 -->
      <!-- Forms
      ================================================== -->
      <div class="bs-docs-section">
          <div class="col-lg-12">
            <div class="page-header">
              <h2 id="forms">会員情報</h2>
            </div>
          </div>

          <div class="col-lg-12">
            <div class="well bs-component">
              <form class="form-horizontal" action="/ChatSystem/chat/accountManage" method="post">
                <fieldset>

                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">名前</label>
                    <div class="col-lg-10">
	                  <input type="text" class="form-control" id="inputEmail" value="${accountAllInfo.name}" disabled="disabled">
                    </div>
                  </div>
                
                <div class="form-group">
                  <label for="inputEmail" class="col-lg-2 control-label">新しいパスワード</label>
                   <div class="col-lg-10">
                     <input type="text" class="form-control" id="inputEmail" placeholder="パスワードを変更する場合は入力してください" name="newPass1">
                   </div>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEmail" placeholder="再度パスワードを入力" name="newPass2">
                    </div>
                 </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">メールアドレス</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEmail" value="${accountAllInfo.mail}" name="mail">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="select" class="col-lg-2 control-label">都道府県</label>
                    <div class="col-lg-10">
					<select name="prefecture"class="form-control" id="select">
						<option selected value="${accountAllInfo.prefecture}">${accountAllInfo.prefecture}</option>
						<optgroup label="北海道・東北">
							<option value="北海道">北海道</option>
							<option value="青森県">青森県</option>
							<option value="岩手県">岩手県</option>
							<option value="宮城県">宮城県</option>
							<option value="秋田県">秋田県</option>
							<option value="山形県">山形県</option>
							<option value="福島県">福島県</option>
						</optgroup>
						<optgroup label="関東">
							<option value="茨城県">茨城県</option>
							<option value="栃木県">栃木県</option>
							<option value="群馬県">群馬県</option>
							<option value="埼玉県">埼玉県</option>
							<option value="千葉県">千葉県</option>
							<option value="東京都">東京都</option>
							<option value="神奈川県">神奈川県</option>
						</optgroup>
						<optgroup label="甲信越・北陸">
							<option value="新潟県">新潟県</option>
							<option value="富山県">富山県</option>
							<option value="石川県">石川県</option>
							<option value="福井県">福井県</option>
							<option value="山梨県">山梨県</option>
							<option value="長野県">長野県</option>
						</optgroup>
						<optgroup label="東海">
							<option value="岐阜県">岐阜県</option>
							<option value="静岡県">静岡県</option>
							<option value="愛知県">愛知県</option>
							<option value="三重県">三重県</option>
						</optgroup>
						<optgroup label="関西">
							<option value="滋賀県">滋賀県</option>
							<option value="京都府">京都府</option>
							<option value="大阪府">大阪府</option>
							<option value="兵庫県">兵庫県</option>
							<option value="奈良県">奈良県</option>
							<option value="和歌山県">和歌山県</option>
						</optgroup>
							<optgroup label="中国">
							<option value="鳥取県">鳥取県</option>
							<option value="島根県">島根県</option>
							<option value="岡山県">岡山県</option>
							<option value="広島県">広島県</option>
							<option value="山口県">山口県</option>
						</optgroup>
						<optgroup label="四国">
							<option value="徳島県">徳島県</option>
							<option value="香川県">香川県</option>
							<option value="愛媛県">愛媛県</option>
							<option value="高知県">高知県</option>
						</optgroup>
						<optgroup label="九州・沖縄">
							<option value="福岡県">福岡県</option>
							<option value="佐賀県">佐賀県</option>
							<option value="長崎県">長崎県</option>
							<option value="熊本県">熊本県</option>
							<option value="大分県">大分県</option>
							<option value="宮崎県">宮崎県</option>
							<option value="鹿児島県">鹿児島県</option>
							<option value="沖縄県">沖縄県</option>
						</optgroup>
					</select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">電話番号</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEmail" value="${accountAllInfo.tel}" name="tel">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">年齢</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEmail" value="${accountAllInfo.age}" name="age">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">閲覧用URL</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEmail" value="${accountAllInfo.url}" name="accountUrl">
                    <span class="help-block">
                    	<a href="http://testserver.weserve.co.jp/ChatSystem/read/${accountAllInfo.url}" >http://testserver.weserve.co.jp/ChatSystem/read/${accountAllInfo.url}</a><br />
                    </span>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
					<input type="hidden" name="accountId" value="${accountAllInfo.accountId}">
					<input type="hidden" name="method" value="modify">
                    <button type="submit" class="btn btn-primary">変更する</button>
                    </div>
                  </div>
                </fieldset>
              </form>
            </div>
          </div>
      </div>
<!--END 会員情報一覧 -->
	 </div>
	</div>
  </div>
  <div class="col-sm-3"><!-- 右カラム -->
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
</div>


</div>
</body>
</html>