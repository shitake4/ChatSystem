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
  <div class="col-sm-12"><!-- 中心カラム -->
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
              <form class="form-horizontal" action="/ChatSystem/chat/friendsRequestList" method="post">
                <fieldset>

                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">名前</label>
                    <div class="col-lg-10">
	                  <input type="text" class="form-control" id="inputEmail" value="${accountAllInfo.name}" disabled="disabled">
                    </div>
                  </div>
                
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">メールアドレス</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEmail" value="${accountAllInfo.mail}" name="mail" disabled="disabled">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="select" class="col-lg-2 control-label">都道府県</label>
                    <div class="col-lg-10">
					<select name="prefecture"class="form-control" id="select" disabled="disabled">
						<option selected value="${accountAllInfo.prefecture}">${accountAllInfo.prefecture}</option>
					</select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">電話番号</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEmail" value="${accountAllInfo.tel}" name="tel" disabled="disabled">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">年齢</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEmail" value="${accountAllInfo.age}" name="age" disabled="disabled">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                    	<c:if test="${accountAllInfo.accountId == loginUserInfo.accountId}" var="ifResult" />
                    		<c:if test="${!ifResult}">
								<input type="hidden" name="friendsAccountId" value="${accountAllInfo.accountId}">
								<input type="hidden" name="method" value="register">					
			                    <button type="submit" class="btn btn-primary">友達リクエストを送る</button>
	                    	</c:if>
                    		<c:if test="${ifResult}">
			                    <button type="submit" class="btn btn-primary" disabled="disabled">友達リクエストを送る</button>
	                    	</c:if>
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
</div>


</div>
</body>
</html>