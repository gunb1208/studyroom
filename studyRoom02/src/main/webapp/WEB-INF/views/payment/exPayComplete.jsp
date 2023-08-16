<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header_.jsp" />
	<!-- Home -->

	<div class="home">
		<div class="home_background_container prlx_parent">
			<div class="home_background prlx" style="background-image:url(${pageContext.request.contextPath}/resources/images/news_background.jpg)"></div>
		</div>
		<div class="home_content">
			<h1>연장 완료</h1>
		</div>
	</div>

	<!-- News -->

		<div class="container my-5">
			<div class="card">
				<div class="card-header bg-light text-dark">
				<p>좌석 연장이 완료되었습니다.</p>
				</div>
				<%-- <p>${paymentDomain}<p><br>
				<p>${regInfoDomain}<p> --%>
				<div class="card-body row mx-1">
					<img class="col-4" src="/resources/images/study4.jpg" >
					<div class="col-8">
						<table class="table">
							<tr>
								<td>등록번호 : </td>
								<td>${regInfoDomain.regNo}</td>
							</tr>
							<tr>
								<td>상품이름 : </td>
								<td>${paymentDomain.itemName}<td>
							</tr>
							<tr>
								<td>결제금액 : </td>
								<td>${paymentDomain.payment}원</td>
							</tr>
							<tr>
								<td>좌석번호 : </td>
								<td>${regInfoDomain.seatNo}번 좌석</td>
							</tr>
							<tr>
								<td>추가기간 : </td>
								<td>${regInfoDomain.period}일</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	
	<script>
	
		function doNotReload(){
		    if( (event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116) ) {
		        event.keyCode = 0;
		        event.cancelBubble = true;
		        event.returnValue = false;
		    } 
		}
		document.onkeydown = doNotReload;
		
	</script>
	
<jsp:include page="../includes/footer.jsp" />