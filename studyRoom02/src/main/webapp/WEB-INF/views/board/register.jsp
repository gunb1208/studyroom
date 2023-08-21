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
           			<h1 class="text-warning mb-4"> 글쓰기 </h1>
           			<div class="panel-body">
           				<form role="form" action="/board/register" method="post">
           					<div class="form-group">
           						<input class="form-control" name='writer' required readonly value='${member.userName}'>
           					</div>
           					<div class="form-group">
           						<input class="form-control" id="title" name='title' placeholder="제목을 입력해주세요." value="${empty board ? '' : ''.concat(board.title).concat('의 답변입니다.')}">
           					</div>
           					<div class="form-group">
           						<textarea class="form-control" id="content" rows="10" name='content' placeholder="내용을 입력해주세요." style="resize: none;"></textarea>
           					</div>
           					<input type="hidden" name="parentNo" value="${board.bno}">
           					<input type="hidden" name="category" value="${category}">  
           					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
           					<a href="list${cri.listLink}" data-opper='list' class="btn btn-sm btn-outline-secondary float-right mt-1">목록</a>
           					<button type="submit" class="btn btn-sm btn-outline-warning float-right mr-1 mt-1" id="register">완료</button>
           					<button type="reset" class="btn btn-sm btn-outline-danger float-right mr-1 mt-1">초기화</button>
           				</form>
           			</div>
           		</div>
           	</div>
           </div>
			<!-- end -->
		</div>
	</div>
	<script>
	
		$("button[type='submit']").click(function(){
			event.preventDefault();
			if($("#title").val() == ""){
				alert("제목을 입력해주세요");
				return;
			}
			else if($("#content").val() == ""){
				alert("내용을 입력해주세요");
				return;
			}
			$(this).closest("form").submit();
		})
	</script>
	
<jsp:include page="../includes/footer.jsp" />