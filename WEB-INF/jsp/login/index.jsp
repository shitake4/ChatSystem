<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript" ></script>
<link href="/ChatSystem/css/bootstrap.min.css" rel="stylesheet">
<link href="/ChatSystem/css/bootstrap-responsive.min.css" rel="stylesheet">
<script src="/ChatSystem/js/bootstrap.min.js"></script>
</head>
<body>

    <div class="container">

<!-- エラー表示 -->
      <div class="bs-docs-section">
		       <div class="row">
		         <div class="col-lg-12">
					<c:if test="${error.message != null}">
			            <div class="bs-component">
			              <div class="alert alert-dismissable alert-danger">
			                <button type="button" class="close" data-dismiss="alert">&times;</button>
								<h4>Warning!</h4>
								<p>${error.message}</p>
			              </div>
			            </div>
					</c:if>
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
		       　　　　　</div>
		      </div>
      </div>
<!--END エラー表示 -->

<!-- ログイン -->
      <!-- Forms
      ================================================== -->
      <div class="bs-docs-section">
        <div class="row">
          <div class="col-lg-12">
            <div class="page-header">
              <h2 id="forms">ログインする</h2>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-lg-6">
            <div class="well bs-component">
              <form action="/ChatSystem/loginout/login" method="post" class="form-horizontal">
                <fieldset>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">ユーザー名</label>
                    <div class="col-lg-10">
                      <input type="text" name="name" class="form-control" id="inputEmail" placeholder="ユーザー名を入力してください">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">パスワード</label>
                    <div class="col-lg-10">
                      <input type="password" name="pass" class="form-control" id="inputEmail" placeholder="パスワードを入力してください">
                    </div>
                  </div>
                  
                
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                      <button class="btn btn-default" type="reset">リセット</button>
                      <input type="hidden" name="method" value="login">
                      <button type="submit" class="btn btn-primary">ログイン</button>
                    </div>
                   
                  </div>
                </fieldset>
              </form>
            </div>
          </div>
         </div>
        </div>
	<p><a href="/ChatSystem/signUp/index.jsp">新規アカウント登録</a></p>
	</div>
</body>
</html>