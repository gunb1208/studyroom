<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../includes/header_.jsp" />
	<!-- Home -->
	<div class="home">
		<div class="home_background_container prlx_parent">
			<div class="home_background prlx" style="background-image:url(${pageContext.request.contextPath}/resources/images/news_background.jpg)"></div>
		</div>
	</div>

	<!-- News -->

	<div class="news">
		<form method="post">
		<div class="container">
		
		<div class="d-flex justify-content-center text-warning mb-5">
			<h1>좌석 이용/변경</h1>
		</div>
		
			<div class="d-flex justify-content-center mt-5"><h2>고정석</h2></div>
			
            <div class="mb-3">
            	<c:forEach items="${regList}" var="reg">
            		<c:if test="${reg.userNo == member.userNo}">
            			<fmt:formatDate var="formatEndDate" value="${reg.endDate}" pattern="yyyy.MM.dd"/>
						<span>현재 이용중인 좌석: ${reg.seatNo}번 / 만료일: ${formatEndDate}</span>
						<a href="${pageContext.request.contextPath}/payment/exPayment" class="btn btn-sm btn-outline-warning mx-2">연장하기</a>
						<input type="hidden" name="sno" value="${reg.seatNo}"> <!-- 기존 좌석 번호 -->
            		</c:if>
            	</c:forEach>
			</div>
			
				<div class="row">
					<div id="room1" class="border px-5 py-5">
						<div class="card-deck">
							<c:forEach items="${seatList}" var="seat" begin="0" end="3">
								<c:if test="${seat.status == true}">
									<c:choose>
										<c:when test="${seat.userNo == member.userNo}">
											<div class="card bg-primary container" style="width: 90px">
											  <div class="card-body text-center px-0">
											    <p class="card-text text-white">${seat.sno}번 좌석</p>
											    <p class="card-text text-white">${seat.name}</p>
											    <p class="card-text text-white">이용중</p>
											  </div>
											</div>
										</c:when>
										<c:otherwise>	
											<div class="card bg-warning container" style="width: 90px">
											  <div class="card-body text-center px-0">
											    <p class="card-text text-white">${seat.sno}번 좌석</p>
											    <p class="card-text text-white">${seat.name}</p>
											    <p class="card-text text-white">이용중</p>
											  </div>
											</div>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${seat.status == false}">
									<div class="card bg-muted container" style="width: 90px"">
									  <div class="card-body text-center px-0">
									    <p class="card-text">${seat.sno}번 좌석</p>
									    <p class="card-text">${seat.name}</p>
									    <button type="button" class="sRegBtn btn btn-sm btn-light container px-0" data-sno="${seat.sno}" value="${seat.sno}">변경</button>
									  </div>
									</div>
								</c:if>
							</c:forEach>
						</div>
						<div class="card-deck mt-4">
							<c:forEach items="${seatList}" var="seat" begin="4" end="7">
								<c:if test="${seat.status == true}">
									<c:choose>
										<c:when test="${seat.userNo == member.userNo}">
											<div class="card bg-primary container" style="width: 90px">
											  <div class="card-body text-center px-0">
											    <p class="card-text text-white">${seat.sno}번 좌석</p>
											    <p class="card-text text-white">${seat.name}</p>
											    <p class="card-text text-white">이용중</p>
											  </div>
											</div>
										</c:when>
										<c:otherwise>	
											<div class="card bg-warning container" style="width: 90px">
											  <div class="card-body text-center px-0">
											    <p class="card-text text-white">${seat.sno}번 좌석</p>
											    <p class="card-text text-white">${seat.name}</p>
											    <p class="card-text text-white">이용중</p>
											  </div>
											</div>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${seat.status == false}">
									<div class="card bg-muted container" style="width: 90px"">
									  <div class="card-body text-center px-0">
									    <p class="card-text">${seat.sno}번 좌석</p>
									    <p class="card-text">${seat.name}</p>
									    <button type="button" class="sRegBtn btn btn-sm btn-light container px-0" data-sno="${seat.sno}" value="${seat.sno}">변경</button>
									  </div>
									</div>
								</c:if>
							</c:forEach>
						</div>
						<div class="card-deck mt-4">
							<c:forEach items="${seatList}" var="seat" begin="8" end="11">
								<c:if test="${seat.status == true}">
									<c:choose>
										<c:when test="${seat.userNo == member.userNo}">
											<div class="card bg-primary container" style="width: 90px">
											  <div class="card-body text-center px-0">
											    <p class="card-text text-white">${seat.sno}번 좌석</p>
											    <p class="card-text text-white">${seat.name}</p>
											    <p class="card-text text-white">이용중</p>
											  </div>
											</div>
										</c:when>
										<c:otherwise>	
											<div class="card bg-warning container" style="width: 90px">
											  <div class="card-body text-center px-0">
											    <p class="card-text text-white">${seat.sno}번 좌석</p>
											    <p class="card-text text-white">${seat.name}</p>
											    <p class="card-text text-white">이용중</p>
											  </div>
											</div>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${seat.status == false}">
									<div class="card bg-muted container" style="width: 90px"">
									  <div class="card-body text-center px-0">
									    <p class="card-text">${seat.sno}번 좌석</p>
									    <p class="card-text">${seat.name}</p>
									    <button type="button" class="sRegBtn btn btn-sm btn-light container px-0" data-sno="${seat.sno}" value="${seat.sno}">변경</button>
									  </div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>				
					<div id="room2" class="border px-5 py-5 ml-3">
						<div class="card-deck">
							<c:forEach items="${seatList}" var="seat" begin="12" end="15">
								<c:if test="${seat.status == true}">
									<c:choose>
										<c:when test="${seat.userNo == member.userNo}">
											<div class="card bg-primary container" style="width: 90px">
											  <div class="card-body text-center px-0">
											    <p class="card-text text-white">${seat.sno}번 좌석</p>
											    <p class="card-text text-white">${seat.name}</p>
											    <p class="card-text text-white">이용중</p>
											  </div>
											</div>
										</c:when>
										<c:otherwise>	
											<div class="card bg-warning container" style="width: 90px">
											  <div class="card-body text-center px-0">
											    <p class="card-text text-white">${seat.sno}번 좌석</p>
											    <p class="card-text text-white">${seat.name}</p>
											    <p class="card-text text-white">이용중</p>
											  </div>
											</div>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${seat.status == false}">
									<div class="card bg-muted container" style="width: 90px"">
									  <div class="card-body text-center px-0">
									    <p class="card-text">${seat.sno}번 좌석</p>
									    <p class="card-text">${seat.name}</p>
									    <button type="button" class="sRegBtn btn btn-sm btn-light container px-0" data-target="#myModal" data-sno="${seat.sno}" value="${seat.sno}">변경</button>
									  </div>
									</div>
								</c:if>
							</c:forEach>
						</div>
						<div class="card-deck mt-4">
							<c:forEach items="${seatList}" var="seat" begin="16" end="19">
								<c:if test="${seat.status == true}">
									<c:choose>
										<c:when test="${seat.userNo == member.userNo}">
											<div class="card bg-primary container" style="width: 90px">
											  <div class="card-body text-center px-0">
											    <p class="card-text text-white">${seat.sno}번 좌석</p>
											    <p class="card-text text-white">${seat.name}</p>
											    <p class="card-text text-white">이용중</p>
											  </div>
											</div>
										</c:when>
										<c:otherwise>	
											<div class="card bg-warning container" style="width: 90px">
											  <div class="card-body text-center px-0">
											    <p class="card-text text-white">${seat.sno}번 좌석</p>
											    <p class="card-text text-white">${seat.name}</p>
											    <p class="card-text text-white">이용중</p>
											  </div>
											</div>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${seat.status == false}">
									<div class="card bg-muted container" style="width: 90px"">
									  <div class="card-body text-center px-0">
									    <p class="card-text">${seat.sno}번 좌석</p>
									    <p class="card-text">${seat.name}</p>
									    <button type="button" class="sRegBtn btn btn-sm btn-light container px-0" data-sno="${seat.sno}" value="${seat.sno}">변경</button>
									  </div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div> <!-- end room2 -->
				</div> <!-- end row -->
	
				<!-- 자유석 -->
				<div class="d-flex justify-content-center mt-5"><h2>자유석</h2></div>
				<div id="room3" class="border px-5 py-5 mr-3 mt-3">
					<div class="card-deck">
						<c:forEach items="${seatList}" var="seat" begin="20" end="27">
							<c:if test="${seat.status == true}">
								<c:choose>
									<c:when test="${seat.userNo == member.userNo}">
										<div class="card bg-primary container" style="width: 90px">
										  <div class="card-body text-center px-0">
										    <p class="card-text text-white">${seat.sno}번 좌석</p>
										    <p class="card-text text-white">${seat.name}</p>
										    <p class="card-text text-white">이용중</p>
										  </div>
										</div>
									</c:when>
									<c:otherwise>	
										<div class="card bg-warning container" style="width: 90px">
										  <div class="card-body text-center px-0">
										    <p class="card-text text-white">${seat.sno}번 좌석</p>
										    <p class="card-text text-white">${seat.name}</p>
										    <p class="card-text text-white">이용중</p>
										  </div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${seat.status == false}">
								<div class="card bg-muted container" style="width: 90px"">
								  <div class="card-body text-center px-0">
								    <p class="card-text">${seat.sno}번 좌석</p>
								    <p class="card-text">${seat.name}</p>
								    <button type="button" class="sRegBtn btn btn-sm btn-light container px-0" data-target="#myModal" data-sno="${seat.sno}" value="${seat.sno}">변경</button>
								  </div>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<!--  --> 
					<div class="card-deck mt-4">
						<c:forEach items="${seatList}" var="seat" begin="28" end="35">
							<c:if test="${seat.status == true}">
								<c:choose>
									<c:when test="${seat.userNo == member.userNo}">
										<div class="card bg-primary container" style="width: 90px">
										  <div class="card-body text-center px-0">
										    <p class="card-text text-white">${seat.sno}번 좌석</p>
										    <p class="card-text text-white">${seat.name}</p>
										    <p class="card-text text-white">이용중</p>
										  </div>
										</div>
									</c:when>
									<c:otherwise>	
										<div class="card bg-warning container" style="width: 90px">
										  <div class="card-body text-center px-0">
										    <p class="card-text text-white">${seat.sno}번 좌석</p>
										    <p class="card-text text-white">${seat.name}</p>
										    <p class="card-text text-white">이용중</p>
										  </div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${seat.status == false}">
								<div class="card bg-muted container" style="width: 90px"">
								  <div class="card-body text-center px-0">
								    <p class="card-text">${seat.sno}번 좌석</p>
								    <p class="card-text">${seat.name}</p>
								    <button type="button" class="sRegBtn btn btn-sm btn-light container px-0" data-target="#myModal" data-sno="${seat.sno}" value="${seat.sno}">변경</button>
								  </div>
								</div>
							</c:if>
						</c:forEach>
					</div> 
				</div> <!-- end room3 -->
					<input type="hidden" name="userNo" value="${member.userNo}"> <!-- 유저번호 -->
			</div>
		</form>
	</div> <!-- news 끝 -->
	
	
	<script>

    
	$(function() { //ready
		/* 각 좌석의 변경 버튼을 눌렀을 때 */
		$(".sRegBtn").click(function() {

			var result = confirm($(this).attr('value') + "번 좌석으로 변경하시겠습니까?");
			var sno = $(this).data("sno"); /* 등록 버튼에 해당하는 좌석 번호 */
			var user = '${member.userNo}';
	 		var str = '<input type="hidden" name="seatNo" value="'+ sno +'">'; 	// 변경할 좌석
			if(user =='') {
				alert("로그인 후 이용하실 수 있습니다.");
				return false;
			} else { // 로그인 된 상태
				if(result) {
					$(this).closest("form").append(str).submit();
					
				}
			}
	 		
		});
		var msg = '${msg}';
		if(msg === 'changeOK') {
			alert("좌석 변경이 완료되었습니다.");
		}
	})

	</script>
<jsp:include page="../includes/footer.jsp" />