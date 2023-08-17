<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%> --%>

<jsp:include page="../includes/adminHeader.jsp" />

	<!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-7 align-self-center">
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">매출현황</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item"><a href="index.html" class="text-muted">Home</a></li>
                                    <li class="breadcrumb-item text-muted active" aria-current="page">Payment Chart</li>
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
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                        	<div class="card-header">
				            	<select class="custom-select custom-select-sm d-inline" id="year" name="year"></select>
							</div>
                            <div class="card-body">
                            
                             <h4 class="card-title">${year}년 월별 매출</h4>  
                            
							<div class="container"> 
							<canvas id="monthChart"></canvas>
							</div> 
                        </div>
                    </div>
                    <div class="card">
                    	<div class="card-header">
				            <input type="month" id="yearMonth" name="yearMonth" >
						</div>
                        <div class="card-body">
                            
                            <h4 class="card-title">월별 매출</h4>  
                            
							<div class="container"> 
							<canvas id="dayChart"></canvas>
							</div> 
                        </div>
                    </div>
							
							<script>
							var ctx = document.getElementById('monthChart')
							var ctx2 = document.getElementById('dayChart')
							
						    $(document).ready(function(){
						        init();
						        $("#year").change(function(){
									var pickYear = $("#year").val();
									showChart(pickYear);
								})
								$("#yearMonth").change(function(){
									showChart2($(this).val())
								})
						    });    
							
							// select box 연월 표시
							function setDateBox(){
								$.getJSON("payChartDataYearCount").done(function(result){
									console.log(result);
									$("#year").append("<option value=''>전체</option>");
									for(var i in result) {
										$("#year").append("<option value='" + result[i].YEAR + "'>" + result[i].YEAR + " 년" + "</option>");                 
									}
								}) // 1월 ~ 12월
							}
						 
						</script>
						
						<!-- 차트 -->
							<script> 
							
							monthChart = new Chart(ctx, {});
							dayChart = new Chart(ctx2, {});
							function init() {
								showChart();
								showChart2();
								setDateBox();
							}
							
							function showChart(year) {
								var yearData = {year:year || null}
								
								$.ajax("payMonthData", {
									data : yearData,
									dataType : 'json',
									success : function(result) {
										console.log(result);
									
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
											data.push(result[i].PAY);
										}
										console.log(labels, data);
										var config = {
												type: 'bar',
												data: {
													labels : labels,
													datasets: [{
														label : '# 매출액',
														data : data,
														backgroundColor: backgroundColor , 
														borderColor: borderColor, 
														borderWidth: 1
													}]
												},
												options: {
													scales: { yAxes: [{ ticks: { beginAtZero: true} }] },
												}
											}
										if (monthChart) {
											monthChart.config = config;
											monthChart.update();
										} else {
											monthChart = new Chart(ctx, config);
										}
										$(".yearCount").text(year ? year + "년" : '전체');
									}
								})
							}
							function showChart2(yearMonth) {
								var now = new Date();
								nowYearMonth = moment().format('YYYY-MM');
								var yearData = {yearMonth:yearMonth || nowYearMonth};
								
								$("#yearMonth").val(yearData.yearMonth);
								console.log(yearData);
								
								$.ajax("payDayData", {
									data : yearData,
									dataType : 'json',
									success : function(result) {
										console.log(result);
									
										var labels = [];
										var data = [];
										var backgroundColor = [ 
											'rgba(255, 99, 132, 0.2)', 
											'rgba(54, 162, 235, 0.2)', 
											'rgba(255, 206, 86, 0.2)', 
											'rgba(75, 192, 192, 0.2)', 
											'rgba(153, 102, 255, 0.2)', 
											'rgba(255, 159, 64, 0.2)',
										];
										var borderColor = [ 
											'rgba(255, 99, 132, 1)', 
											'rgba(54, 162, 235, 1)', 
											'rgba(255, 206, 86, 1)', 
											'rgba(75, 192, 192, 1)', 
											'rgba(153, 102, 255, 1)', 
											'rgba(255, 159, 64, 1)',
										]
										var backgroundColorResult = [];
										var borderColorResult = [];
										for(var i in result) {
											labels.push(result[i].DAY);
											data.push(result[i].PAY);
											backgroundColorResult.push(backgroundColor[i%6])
											borderColorResult.push(borderColor[i%6])
										}
										console.log(labels, data, backgroundColorResult, borderColorResult);
										var config = {
												type: 'bar',
												data: {
													labels : labels,
													datasets: [{
														label : '# 매출액',
														data : data,
														backgroundColor: backgroundColorResult , 
														borderColor: borderColorResult, 
														borderWidth: 1
													}]
												},
												options: {
													scales: { yAxes: [{ ticks: { beginAtZero: true} }] },
												}
											}
										if (dayChart) {
											dayChart.config = config;
											dayChart.update();
										} else {
											dayChart = new Chart(ctx2, config);
										}
										//$(".yearCount").text(year ? year + "년" : '전체');
									}
								})
							}
						
							</script> 
                    
                    <!-- ▲ 일 매출  -->
                       <%--  <div class="card">
                            <div class="card-body">
                              <h4 class="card-title">${month}월 일별 매출</h4>                        
                            
                        <!-- 차트 -->
							<div class="container"> 
							<canvas id="dayChart" style="display: block; width: 1110px; height: 555px;" height="555" class="chartjs-render-monitor" width="1110"></canvas>
							</div> 
							
						<!-- 차트 -->
							<script> 
							
							/* $getJSON("http://localhost:8080/admin/payChart", function(data){
								$each(data), function(inx, obj) {
									chartlabels1.push(obj.DAY);
									chartData1.push(obj.PAY);
								});
							createChart1();
							console.log("success OneDayChart")
							}); */
							
							var ctx = document.getElementById('dayChart'); 
							var day = ${dayChart[0].DAY};
							var dayChart = new Chart(
									ctx, { type: 'bar', data: { labels: ['1일','2일','3일','4일','5일','6일','7일','8일','9일','10일','11일','12일','13일','14일',
										'15일','16일','17일','18일','19일','20일','21일','22일','23일','24일','25일','26일','27일','28일','29일','30일','31일'], 
							
										datasets: [{ label: '# 매출액', 

									data: [${dayChart[0].PAY}, 
										${dayChart[1].PAY}, 
										${dayChart[2].PAY}, 
										${dayChart[3].PAY}, 
										${dayChart[4].PAY}, 
										${dayChart[5].PAY},
										${dayChart[6].PAY},
										${dayChart[7].PAY},
										${dayChart[8].PAY},
										${dayChart[9].PAY},
										${dayChart[10].PAY},
										${dayChart[11].PAY},
										${dayChart[12].PAY},
										${dayChart[13].PAY},
										${dayChart[14].PAY},
										${dayChart[15].PAY},
										${dayChart[16].PAY},
										${dayChart[17].PAY},
										${dayChart[18].PAY},
										${dayChart[19].PAY},
										${dayChart[20].PAY},
										${dayChart[21].PAY},
										${dayChart[22].PAY},
										${dayChart[23].PAY},
										${dayChart[24].PAY},
										${dayChart[25].PAY},
										${dayChart[26].PAY},
										${dayChart[27].PAY},
										${dayChart[28].PAY},
										${dayChart[29].PAY},
										${dayChart[30].PAY},
										] , 
									backgroundColor: [ 
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)', 
										'rgba(255, 206, 86, 0.2)',
										'rgba(255, 99, 132, 0.2)' ], 
									borderColor: [ 
										'rgba(255, 99, 132, 1)', 
										'rgba(255, 206, 86, 1)',
										'rgba(255, 99, 132, 1)', 
										'rgba(255, 206, 86, 1)',
										'rgba(255, 99, 132, 1)', 
										'rgba(255, 206, 86, 1)',
										'rgba(255, 99, 132, 1)', 
										'rgba(255, 206, 86, 1)',
										'rgba(255, 99, 132, 1)', 
										'rgba(255, 206, 86, 1)',
										'rgba(255, 99, 132, 1)', 
										'rgba(255, 206, 86, 1)',
										'rgba(255, 99, 132, 1)', 
										'rgba(255, 206, 86, 1)',
										'rgba(255, 99, 132, 1)', 
										'rgba(255, 206, 86, 1)',
										'rgba(255, 99, 132, 1)', 
										'rgba(255, 206, 86, 1)',
										'rgba(255, 99, 132, 1)', 
										'rgba(255, 206, 86, 1)',
										'rgba(255, 99, 132, 1)' ], 
									borderWidth: 1 }] }, 
									options: { scales: { yAxes: [{ ticks: { beginAtZero: true } }] } } 
									}); 
							</script> 
                            
                            
                            
                        </div>
                    </div>    --%>                 
                    
                    
                </div>
                <!-- ============================================================== -->
                <!-- End PAge Content -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Right sidebar -->
                <!-- ============================================================== -->
                <!-- .right-sidebar -->
                <!-- ============================================================== -->
                <!-- End Right sidebar -->
                <!-- ============================================================== -->
            </div>
            <!-- ============================================================== -->
            <!-- End Container fluid  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->

<jsp:include page="../includes/adminFooter.jsp" />