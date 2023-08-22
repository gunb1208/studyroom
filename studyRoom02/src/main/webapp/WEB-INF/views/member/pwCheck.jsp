<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header_.jsp" />
	<!-- Home -->

	<div class="home">
		<div class="home_background_container prlx_parent">
			<div class="home_background prlx" style="background-image:url(${pageContext.request.contextPath}/resources/images/news_background.jpg)"></div>
		</div>
	</div>
	<div class="container">
	
		<div class="d-flex justify-content-center text-warning my-5">
			<h1>회원 탈퇴</h1>
		</div>
		
		<div class="row d-flex justify-content-center mb-5" >
			<div class="col-4 pt-4 d-flex align-self-center">
	<!-- 		<div class="col-lg-4 d-none d-lg-block"> -->
					<img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" width="600px">
			</div>
	        <div class="col-5 pt-5 mb-5">
	<!--         <div class="col-lg-4 mt-5"> -->
				
				<h3 class="mediuen text-danger text-center">회원탈퇴를 위해 비밀번호를 한번 더 입력해야합니다</h3>
			        <table class="table table-boardered mt-5">
			            <tr>
			            <form action="${pageContext.request.contextPath}/member/withdraw" method="post" id="frm">
			                <th>패스워드</th>
			                <td><input class="form-control" name="userPw" type="password"></td>
			    		</form>      
			            </tr>
			            <tr>
			                <td colspan="2">
			                <div class="text-center"><button class="btn btn-sm btn-warning py-2 px-5 exitBtn" onclick="chk_form()">회원 탈퇴</button></div>
			                </td>
			            </tr>
			        </table>
		    </div>
		</div>
	</div>
<jsp:include page="../includes/footer.jsp" />


<script>

	function chk_form() {
		if (confirm("정말로 탈퇴하시겠습니까?") == true){    
			document.getElementById('frm').submit();
		 } else {   
		    alert('취소되었습니다.');
		 }
	}
	
</script>