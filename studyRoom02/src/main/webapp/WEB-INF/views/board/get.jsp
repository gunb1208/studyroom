<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%> --%>	
	<jsp:include page="../includes/header_.jsp" />

	<!-- Home -->
	<div class="home">
		<div class="home_background_container prlx_parent">
			<div class="home_background prlx" style="background-image:url(${pageContext.request.contextPath}/resources/images/news_background.jpg)"></div>
		</div>
	</div>
	
	<!-- News -->

	<div class="news">
		<div class="container">
			<!-- 게시판  -->
			<form id="frm" action="remove${cri.listLink}&bno=${board.bno}" method="post">
           <div class="row">
	           	<div style="width: 800px; margin:0 auto">
	           		<div class="panel panel-default">
	          			<div class="panel-body col-12 p-0">
	          				<div class="row mb-2">
	          					<div>
		          					<c:if test="${board.category == 1}">
		          						<h2 class="text-warning m-3 pt-1">공지사항 ></h2>
		          					</c:if>
		          					<c:if test="${board.category == 2}">
		          						<c:if test="${board.parentNo == 0}">
		          							<h2 class="text-warning m-3 pt-1"> 질문 > </h2>
		          						</c:if>
			          					<c:if test="${board.parentNo != 0}">
			          						<h2 class="text-warning m-3 pt-1"> 답변 > </h2>
			          					</c:if>
		          					</c:if>
	          					</div>
	       						<div><h2 class="text-break m-3 pt-1"><c:out value="${board.title}"/></h2></div>
	       					</div>
	       					<div class="card shadow mb-4">
			                    <div class="uploadResult">
									<ul class="list-group list-group-horizontal"></ul>
								</div>
								<div class="modal fade" id="imageModal">
							        <div class="modal-dialog modal-xl">
							            <div class="modal-content">
							            	<div class="modal-body text-center">
												<img class="mw-100">
											</div>
								        </div>
									</div>
								</div>
		                  	</div>
	       					<div style="min-height: 200px;">
	       						<h3 class="text-break mb-3"><c:out value="${board.content}"/></h3>
	       					</div>
	       				</div>
	       				<div class="card shadow mb-4">
			                    <div class="uploadResult">
									<ul class="list-group list-group-horizontal"></ul>
								</div>
								<div class="modal fade" id="imageModal">
							        <div class="modal-dialog modal-xl">
							            <div class="modal-content">
							            	<div class="modal-body text-center">
												<img class="mw-100">
											</div>
								        </div>
									</div>
								</div>
		                  	</div>
       					<div class="row mb-3 mt-2">
	       					<div class="row col-8">
	       						<i class="fas fa-user ml-4 mr-1 mt-1"></i><c:out value="${board.writer}"/>
	       						<div class="ml-2"><fmt:formatDate value="${board.regDate}" /></div> 
	       						<div class="ml-2">조회수 : <c:out value="${board.hitCount}"/></div>
	       					</div>
       					 	<div class="col-4 p-0">
       					 		<sec:authentication property="principal" var="pinfo"/>
       					 		<sec:authorize access="hasRole('ROLE_ADMIN')">
									<c:set var="adm" value="1" />
								</sec:authorize>
								<h6 class="float-right ml-2"><a href="list${cri.listLink}">목록<i class="fas fa-tasks"></i></a></h6>
	       					 	<c:if test="${(board.category != 1) && (adm == 1) && (board.parentNo == 0)}">
		       					 	<h6 class="float-right"><a href="register?category=2&parentNo=${board.bno}">답변하기</a></h6>
	       					 	</c:if>
	       					 	<sec:authorize access="isAuthenticated()"> 
       					 		<c:if test="${(pinfo.vo.userId == board.writer) || (adm == 1)}">
		                    		<h6 class="float-right ml-2"><a href="modify${cri.listLink}&bno=${board.bno}">수정<i class="far fa-edit mr-2 ml-1"></i></a></h6>
		                    		<h6 class="float-right ml-2"><a href="#" onclick="chk_form()">삭제<i class="far fa-trash-alt mr-2 ml-1"></i></a></h6>
		                    	</c:if>
		                    	</sec:authorize>
							</div>
           				</div>
				</div>
			</div>
		</div>
		</form>
	</div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/reply.js"></script>
<script>

	function chk_form() {
		if (confirm("게시글을 삭제하시겠습니까?") == true){    
			document.getElementById('frm').submit();
		 } else {   
		    return false;
		 }
	}
					
</script>
<jsp:include page="../includes/footer.jsp" />