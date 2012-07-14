<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Simpla Admin</title>

<!--                       CSS                       -->

<!-- Reset Stylesheet -->
<link rel="stylesheet" href="resources/css/reset.css" type="text/css"
	media="screen" />

<!-- Main Stylesheet -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css"
	media="screen" />

<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css"
	media="screen" />

<!-- Colour Schemes
	  
		Default colour scheme is green. Uncomment prefered stylesheet to use it.
		
		<link rel="stylesheet" href="resources/css/blue.css" type="text/css" media="screen" />
		
		<link rel="stylesheet" href="resources/css/red.css" type="text/css" media="screen" />  
	 
		-->

<!-- Internet Explorer Fixes Stylesheet -->

<!--[if lte IE 7]>
			<link rel="stylesheet" href="resources/css/ie.css" type="text/css" media="screen" />
		<![endif]-->

<!--                       Javascripts                       -->

<!-- jQuery -->
<script type="text/javascript"
	src="resources/scripts/jquery-1.3.2.min.js"></script>

<!-- jQuery Configuration -->
<script type="text/javascript"
	src="resources/scripts/simpla.jquery.configuration.js"></script>

<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="resources/scripts/facebox.js"></script>

<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>

<!-- jQuery Datepicker Plugin -->
<script type="text/javascript"
	src="resources/scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.date.js"></script>
<script type="text/javascript" src="resources/scripts/My97DatePicker/WdatePicker.js"></script>
<script>
	function loac_go() {
		//获得下拉对象
		var url_zd = document.getElementById('time');
		//循环对象中的option
		for ( var i = 0; i < url_zd.options.length; i++) {
			//判断被选中
			if (url_zd.options[i].selected && url_zd.options[i].value != "") {
				//跳转
				window.location.href = url_zd.options[i].value;
			}
		}
	}
</script>
<!--[if IE]><script type="text/javascript" src="resources/scripts/jquery.bgiframe.js"></script><![endif]-->


<!-- Internet Explorer .png-fix -->

<!--[if IE 6]>
			<script type="text/javascript" src="resources/scripts/DD_belatedPNG_0.0.7a.js"></script>
			<script type="text/javascript">
				DD_belatedPNG.fix('.png_bg, img, li');
			</script>
		<![endif]-->

</head>

<body>
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->

		<div id="sidebar">
			<div id="sidebar-wrapper">
				<!-- Sidebar with logo and menu -->

				<h1 id="sidebar-title">
					<a href="#">生产物流信息统计系统</a>
				</h1>

				<!-- Logo (221px wide) -->
				<a href="#"><img id="logo" src="resources/images/log0-l.png"
					alt="logo" /></a>

				<!-- Sidebar Profile links -->

				<ul id="main-nav">
					<!-- Accordion Menu -->

					<li><a href="Index_viewer.jsp"
						class="nav-top-item no-submenu"> <!-- Add the class "current" to current menu item -->
							首页
					</a></li>

					<li><a href="#" class="nav-top-item"> 工单管理 </a>
						<ul>
							<li><a href="FormSearch_viewer.jsp">工单列表</a></li>
						</ul></li>

					<li><a href="#" class="nav-top-item current"> 统计单管理 </a>
						<ul>
							<li><a href="SearchJD_viewer.jsp">生产进度统计单</a></li>
							<li><a href="SearchHour_viewer.jsp">工时统计单</a></li>
							<li><a href="SearchProduct_viewer.jsp">不合格品统计单</a></li>
							<li><a href="SearchPeople_viewer.jsp">员工工作统计单</a></li>
						</ul></li>

					<li><a href="AdminInfo_viewer.jsp"
						class="nav-top-item no-submenu"> <!-- Add the class "no-submenu" to menu items with no sub menu -->
							个人信息管理
					</a></li>


				</ul>
				<!-- End #main-nav -->


			</div>
		</div>
		<!-- End #sidebar -->

		<div id="main-content">
			<!-- Main Content Section with everything -->

			<!-- Page Head -->
			<h2>不合格产品统计查询</h2>
			<p id="page-intro">Staff working hours inquiries</p>



			<div class="clear"></div>
			<!-- End .clear -->

			<div class="content-box">
				<!-- Start Content Box -->

				<div class="content-box-header">

					<h3>部门废品统计表</h3>


					<div class="clear"></div>

				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

							<p>
								<label>表格类型</label> <select name="time" id="time"
									onchange="loac_go();">
									<option value="">----请选择--</option>
									<option value="SearchProduct_viewer.jsp">产品废品统计表</option>
									<option value="SearchProduct2_viewer.jsp">批次某产品废品统计表</option>
									<option value="SearchProduct3_viewer.jsp">员工废品统计表</option>
									<option value="SearchProduct4_viewer.jsp">部门废品统计表</option>
									<option value="SearchProduct5_viewer.jsp">生产线废品统计表</option>
								</select>
							</p>

						
							<div id="login-content">

						<s:form action="SearchPd5.action">

							<p>
								 <s:textfield name="lineNo" label="生产线编号"></s:textfield>
								<s:textfield name="startTime" label="起始时间(YYYY-MM-DD)"
									onClick="WdatePicker()"></s:textfield>
								<s:textfield name="endTime" label="截止时间(YYYY-MM-DD)"
									onClick="WdatePicker()"></s:textfield>
							</p>

							<div class="clear"></div>									
						 <s:submit value="submit"/>
						</s:form>
					</div>
					<!-- End #login-content -->
				</div>





			</div>
			<!-- End #main-content -->
			<div id="footer">
				<small> <!-- Remove this notice or replace it with whatever you want -->
					&#169; Copyright 2012 Your Company | Powered by 顺江实验室 | <a href="#">Top</a>
				</small>
			</div>
			<!-- End #footer -->
		</div>
	</div>
</body>


<!-- Download From www.exet.tk-->
</html>
