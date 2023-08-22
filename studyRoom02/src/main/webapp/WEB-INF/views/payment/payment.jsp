<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../includes/header_.jsp" />
	<!-- Home -->

	<div class="home">
		<div class="home_background_container prlx_parent">
			<div class="home_background prlx" style="background-image:url(${pageContext.request.contextPath}/resources/images/news_background.jpg)"></div>
		</div>
	</div>

	<!-- News -->

	<div class="container d-flex justify-content-center my-5">
		<div class="card">
			<div class="card-header ">
				<h2 class="card-title">결제 정보</h2>
			</div>
			<div class="card-body row">
				<img class="col-3" src="/resources/images/desk.jpg" >
				<div class="col-9">
					<h2 class="font-weight-bold">${feeDomain.name} ${feeDomain2.name}</h2>
					<table class="table">
						<tr>
							<td>
							회원이름 :
							</td>
							<td>
							${regInfoDomain.userName}
							</td>
						</tr>
						<tr>
							<td>
							좌석번호 :
							</td>
							<td>
							${regInfoDomain.seatNo}
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="card-footer">
					<h3>총 결제금액 : ${feeDomain.price + feeDomain2.price} 원 </h3>
	    			<form method="post" action="payComplete" class="CompleteForm">
		    			<input type="hidden" name="itemName" value="${feeDomain.name} ${feeDomain2.name}" readonly>
						<input type="hidden" name="payment" value="${feeDomain.price + feeDomain2.price}" readonly>
		    			<input type="hidden" name="userName" value="${regInfoDomain.userName}" readonly>
						<input type="hidden" name="seatNo" value="${regInfoDomain.seatNo}" readonly>
		    			<input type="hidden" name="period" value="${feeDomain.period}">
		    			<input type="hidden" name="fno" value="${feeDomain.fno}">
		    			<input type="hidden" name="feeName" value="${feeDomain.name}">
		    			<input type="hidden" name="userNo" value="${regInfoDomain.userNo}">
	    				<button class="btn btn-success float-right px-5 py-2" id="payButton" type="button">결제</button>
	   				</form>
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
	
	
	var idleTime = 0;
	$(function() {
		var idleInterval = setInterval(timeIncrement, 60000); // 1분
		/* 마우스의 움직임이나 키보드 입력이 있으면 시간을 초기화 한다. */
		$(this).mousemove(function (e) {idleTime = 0;});
		$(this).keypress(function (e) {idleTime = 0;});
	})
	/* 특정 시간이 지나면 로그아웃 처리 */
	function timeIncrement() {
		idleTime = idleTime + 1;
		if(idleTime > 9) { // 10분
			location.href='/member/logout';
			alert("결제 시간이 만료되어 로그아웃 되었습니다.");
		}
	}
	</script>
	<script>
	var fname = '${feeDomain.name} ${feeDomain2.name}';
	var amount = ${feeDomain.price + feeDomain2.price};
	var buyerName = '${regInfoDomain.userName}';
	//console.log(fname);
	//console.log(amount);
	//console.log(buyerName);
	$("#payButton").click(function(){
		var IMP = window.IMP; // 생략가능
		IMP.init('imp66686777'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
			
		IMP.request_pay({
		    pg : 'inicis', // version 1.1.0부터 지원.
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name :  fname ,
		    amount : amount ,
		    buyer_name : buyerName ,
		    m_redirect_url : 'https://www.yourdomain.com/payments/complete'
		}, function(rsp) {
		    if ( rsp.success ) {
		        var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		        
		    } else {
		        var msg = '결제에 실패하였습니다.';
		        msg += '에러내용 : ' + rsp.error_msg;
		        
		        $(".CompleteForm").submit();
		    }
		    alert(msg);
		});
	});
	
	 
</script>
<jsp:include page="../includes/footer.jsp" />