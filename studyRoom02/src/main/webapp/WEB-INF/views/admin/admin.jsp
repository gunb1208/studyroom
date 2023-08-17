<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%> --%>
<jsp:include page="../includes/adminHeader.jsp" />

        <!-- ============================================================== -->
        <!-- End Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-7 align-self-center">
                        <h3 class="page-title text-truncate text-dark font-weight-medium mb-1">관리자 홈</h3>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item">Dashboard
                                    </li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
                <!-- *************************************************************** -->
                <!-- Start First Cards -->
                <!-- *************************************************************** -->
                <!-- *************************************************************** -->
                <!-- End First Cards -->
                <!-- *************************************************************** -->
                <!-- *************************************************************** -->
                <!-- Start Sales Charts Section -->
                <!-- *************************************************************** -->
                <div class="card-group">
                    <div class="card border-right">
                        <div class="card-body">
                            <div class="d-flex d-lg-flex d-md-block align-items-center">
                                <div>
                                    <div class="d-inline-flex align-items-center">
                                        <h2 class="text-dark mb-1 font-weight-medium">${memberCount}</h2>
                                    </div>
                                    <h6 class="text-muted font-weight-normal mb-0 w-100 text-truncate">회원의 수</h6>
                                </div>
                                <div class="ml-auto mt-md-3 mt-lg-0">
                                    <span class="opacity-7 text-muted"><i data-feather="user-plus"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card border-right">
                        <div class="card-body">
                            <div class="d-flex d-lg-flex d-md-block align-items-center">
                                <div>
                                    <h2 class="text-dark mb-1 w-100 text-truncate font-weight-medium">${usingCount} </h2>
                                    <h6 class="text-muted font-weight-normal mb-0 w-100 text-truncate">현재 이용자 수</h6>
                                </div>
                                <div class="ml-auto mt-md-3 mt-lg-0">
                                    <span class="opacity-7 text-muted"><i data-feather="user-plus"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card border-right">
                        <div class="card-body">
                            <div class="d-flex d-lg-flex d-md-block align-items-center">
                                <div>
                                    <div class="d-inline-flex align-items-center">
                                        <h2 class="text-dark mb-1 font-weight-medium">${totalIncome}</h2>
                                    </div>
                                    <h6 class="text-muted font-weight-normal mb-0 w-100 text-truncate">현재까지의 매출액</h6>
                                </div>
                                <div class="ml-auto mt-md-3 mt-lg-0">
                                    <span class="opacity-7 text-muted"><i data-feather="dollar-sign"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title font-weight-bold">월별 이용자 수 현황</h4>
                                <canvas id="memberChart"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title font-weight-bold">매출 현황</h4>
                                <canvas id="payChart"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- *************************************************************** -->
                <!-- End Sales Charts Section -->
                <!-- *************************************************************** -->
                <!-- *************************************************************** -->
                <!-- Start Location and Earnings Charts Section -->
                <!-- *************************************************************** -->
                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="card">
                        	<div class="card-header">
                        	<h4 class="text-dark font-weight-bold">최근공지</h4>
                        	</div>
                            <div class="card-body">
								<c:forEach items="${notiList}" var="board">
									<h6><a class="text-dark" href='/board/get${pageMaker.cri.listLink}&bno=${board.bno}&category=${board.category}'>
									<c:out value="${board.title}"/> [${board.replyCnt}]</a></h6>
									<p class="small"><c:out value="${board.writer}"/></p>
									<hr>
								</c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="card">
                        	<div class="card-header">
                        	<h4 class="text-dark font-weight-bold">최근 Q&A</h4>
                        	</div>
                            <div class="card-body">
								<c:forEach items="${QAList}" var="board">
									<div class="${board.parentNo == 0 ? '' : 'pl-4'}" >	
										<h6><a class="text-dark" href='/board/get${pageMaker.cri.listLink}&bno=${board.bno}&category=${board.category}'>
										<c:out value="${board.title}"/> [${board.replyCnt}]</a></h6>
										<p class="small"><c:out value="${board.writer}"/></p>
									</div>
									<hr>
								</c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- *************************************************************** -->
                <!-- End Location and Earnings Charts Section -->
                <!-- *************************************************************** -->
                <!-- *************************************************************** -->
                <!-- Start Top Leader Table -->
                <!-- *************************************************************** -->
                <!-- *************************************************************** -->
                <!-- End Top Leader Table -->
                <!-- *************************************************************** -->
            </div>
            <!-- ============================================================== -->
            <!-- End Container fluid  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->

    
	
	<!-- 차트 링크 --> 
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
							
	
	<script>
	var payCtx = document.getElementById('payChart'); 
	var payMonth = ${payChart[0].MONTH};
	var payChart = new Chart(
			payCtx, { type: 'bar', data: { labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], 
		datasets: [{ label: '# 이용 고객', 

			data: [${payChart[0].PAY}, 
				${payChart[1].PAY}, 
				${payChart[2].PAY}, 
				${payChart[3].PAY}, 
				${payChart[4].PAY}, 
				${payChart[5].PAY},
				${payChart[6].PAY},
				${payChart[7].PAY},
				${payChart[8].PAY},
				${payChart[9].PAY},
				${payChart[10].PAY},
				${payChart[11].PAY}
				], 
			backgroundColor: [ 
				'rgba(255, 99, 132, 0.2)', 
				'rgba(54, 162, 235, 0.2)', 
				'rgba(255, 206, 86, 0.2)', 
				'rgba(75, 192, 192, 0.2)', 
				'rgba(153, 102, 255, 0.2)', 
				'rgba(255, 159, 64, 0.2)',
				'rgba(255, 99, 132, 0.2)',
				'rgba(54, 162, 235, 0.2)',
				'rgba(255, 206, 86, 0.2)',
				'rgba(75, 192, 192, 0.2)',
				'rgba(153, 102, 255, 0.2)',
				'rgba(255, 159, 64, 0.2)'], 
				/* 색 추가 */
			borderColor: [ 
				'rgba(255, 99, 132, 1)', 
				'rgba(54, 162, 235, 1)', 
				'rgba(255, 206, 86, 1)', 
				'rgba(75, 192, 192, 1)', 
				'rgba(153, 102, 255, 1)', 
				'rgba(255, 159, 64, 1)',
				'rgba(255, 99, 132, 1)', 
				'rgba(54, 162, 235, 1)', 
				'rgba(255, 206, 86, 1)', 
				'rgba(75, 192, 192, 1)', 
				'rgba(153, 102, 255, 1)', 
				'rgba(255, 159, 64, 1)'], 
			borderWidth: 1 }] }, 
			options: { scales: { yAxes: [{ ticks: { beginAtZero: true } }] } } 
			}); 
	</script>
    <!-- 차트 -->
	<script>
		var ctx = document.getElementById('memberChart');
	    $(document).ready(function(){
	    	init();
	        $("#year").change(function(){
				console.log(location);
				var pickYear = $("#year option:selected").val();
				showChart(pickYear);
			})
	    });    
	 
	    // select box 연도 , 월 표시
	    function setDateBox(){
	        $.getJSON("memberChartDataYearCount").done(function(result) {
	        	console.log(result);
	        	$("#year").append("<option value=''>전체</option>");
	        	for(var i in result) {
	        		$("#year").append("<option value='"+ result[i].YEAR +"'>"+ result[i].YEAR + " 년" +"</option>");
	        	}
	        })
	        // 월 뿌려주기(1월부터 12월)
	        
	    }
	 
	</script>


		
	<!-- 차트 -->
	<script>
	myChart = new Chart(ctx, {});
	function init() {
		showChart();
		setDateBox();
	}
	
	function showChart(year) {
		
		var yearData = {year:year || null}
		
		$.ajax("memberChartData", {
			data : yearData,
			dataType : 'json',
			success: function(result) {
				console.log(result);
				//var month = ${chart[0].MONTH};
				var labels = [];
				var data = [];
				var backgroundColor = [ 
					'rgba(255, 99, 132, 0.2)', 
					'rgba(54, 162, 235, 0.2)', 
					'rgba(255, 206, 86, 0.2)', 
					'rgba(75, 192, 192, 0.2)', 
					'rgba(153, 102, 255, 0.2)', 
					'rgba(255, 159, 64, 0.2)',
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)',
					'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)'
				];
				var borderColor = [ 
					'rgba(255, 99, 132, 1)', 
					'rgba(54, 162, 235, 1)', 
					'rgba(255, 206, 86, 1)', 
					'rgba(75, 192, 192, 1)', 
					'rgba(153, 102, 255, 1)', 
					'rgba(255, 159, 64, 1)',
					'rgba(255, 99, 132, 1)', 
					'rgba(54, 162, 235, 1)', 
					'rgba(255, 206, 86, 1)', 
					'rgba(75, 192, 192, 1)', 
					'rgba(153, 102, 255, 1)', 
					'rgba(255, 159, 64, 1)'
				]
				for(var i in result) {
					labels.push(result[i].MONTH);
					data.push(result[i].CNT);
				}
				var config = { 
					type: 'bar', 
					data: { 
						labels: labels, 
						datasets:[{ 
							label: '# 이용 고객', 
							data: data, 
							backgroundColor: backgroundColor , 
							borderColor: borderColor, 
							borderWidth: 1 
						}] 
					}, 
					options: { 
						scales: { yAxes: [{ ticks: { beginAtZero: true } }] },
					} 
				}
				if (myChart) {
					myChart.config = config;
					myChart.update();
		        } else {
		        	myChart = new Chart(ctx,config);
		        }
				
				
				$(".yearCount").text(year ? year+"년" : '전체');
			}
		})
	}	
	 
	</script> 
<jsp:include page="../includes/adminFooter.jsp" />    