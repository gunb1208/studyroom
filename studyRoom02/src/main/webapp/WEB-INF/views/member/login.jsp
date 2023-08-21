<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header_.jsp" />
	<!-- Home -->

	<div class="home">
		<div class="home_background_container prlx_parent">
			<div class="home_background prlx" style="background-image:url(${pageContext.request.contextPath}/resources/images/news_background.jpg)"></div>
		</div>
	</div>
	<div class="container mt-3">
		<div class="row d-flex justify-content-center mb-5">
			<div class="col-4 align-self-center">
	<!-- 		<div class="col-lg-4 d-none d-lg-block"> -->
					<img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" width="600px">
			</div>
	        <div class="col-5">
	<!--         <div class="col-lg-4 mt-5"> -->
				<form action="/member/login" method="post">
			        <table class="table table-boardered mt-5">
			            <tr>
			                <th>아이디</th>
			                <td><input class="form-control" name="userId" id="userId" type="text" placeholder="id"></td>     
			            </tr>
			            <tr>
			                <th>패스워드</th>
			                <td><input class="form-control" name="userPw" id="userPw" type="password" placeholder="password"></td>      
			            </tr>
			            <tr>
			                <td colspan="2">
			                <div class="text-center"><button class="btn btn-sm btn-warning text-white py-2 px-5 col-12" type="submit">로그인</button></div>
			                </td>
			            </tr>
			        </table>
			        <div id="naver_id_login" style="text-align:center"><a href="${url}">
					<img src="${pageContext.request.contextPath}/resources/images/btnG_perfect.png"/></a></div>
					<br>
			        <%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> --%>
			    </form>
			    <p class="mediuen">YS독서실에 가입하고 여러 혜택을 누리시겠습니까? <a href="${pageContext.request.contextPath}/member/terms">회원가입 하기</a></p>
		    </div>
		</div>
	</div>
<jsp:include page="../includes/footer.jsp" />

<script>

		  
		  
</script>
