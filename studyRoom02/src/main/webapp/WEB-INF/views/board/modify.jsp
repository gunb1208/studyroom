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
           <div class="row">
	           	<div style="width: 800px; margin:0 auto">
	           		<div class="panel panel-default">
	           			<h1 class="text-warning mb-4">게시글 수정</h1>
	           			<div class="panel-body">
							<form method="post">
								<input type="hidden" name="pageNum" value="${cri.pageNum}">
		                        <input type="hidden" name="amount" value="${cri.amount}">
		                        <input type="hidden" name="category" value="${board.category}">
		                        <input type="hidden" id="bno" name="bno" required value="${board.bno}">
	           					<div class="form-group">
	           						<input class="form-control" name='writer' value='<c:out value="${board.writer}"/>' readonly>
	           					</div>
	           					<div class="form-group">
	           						<input class="form-control" name='title'value='<c:out value="${board.title}"/>'>
	           					</div>
	           					<div class="form-group">
	           						<textarea class="form-control" rows="10" name='content' style="resize: none;"><c:out value="${board.content }"/></textarea>
	           					</div>				
								<!-- 버튼 --> 
								<div>
									<a href="list${cri.listLink}" data-opper='list' class="btn btn-sm btn-outline-secondary float-right">목록</a>
									<button data-oper='remove' class="btn btn-sm btn-outline-danger float-right mr-2" id="removeButton">삭제</button>         				
		          					<button data-oper='modify' class="btn btn-sm btn-outline-warning float-right mr-2">완료</button>
		          				</div> 
							</form>
	           			</div>
	           		</div>
                </div>
	    	</div>
    	</div> 
			<!-- end -->
	</div>
<script>
	
	$(function() {
	    $('#removeButton').click(function() {
	    	if (confirm("게시글을 삭제하시겠습니까?") == true){    
	    		$('form').prop('action', 'remove').submit();
			 } else {   
			    return false;
			 }
	    });
	});
	
</script>
	
<jsp:include page="../includes/footer.jsp" />