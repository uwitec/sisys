<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

					<li><a href="Index_operator.jsp"
						class="nav-top-item current no-submenu"> <!-- Add the class "current" to current menu item -->
							首页
					</a></li>

					<li><a href="#" class="nav-top-item"> 工单管理 </a>
						<ul>
							<li><a href="formAdd.action">添加工单</a></li>
							<li><a href="FormSearch_operator.jsp">工单列表</a></li>
						</ul></li>

					<li><a href="#" class="nav-top-item"> 统计单管理 </a>
						<ul>
							<li><a href="SearchJD_operator.jsp">生产进度统计单</a></li>
							<li><a href="SearchHour_operator.jsp">工时统计单</a></li>
							<li><a href="SearchProduct_operator.jsp">不合格品统计单</a></li>
							<li><a href="SearchPeople_operator.jsp">员工工作统计单</a></li>
						</ul></li>

					<li><a href="AdminInfo_operator.jsp"
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
			<h2>Welcome!</h2>
			<p id="page-intro">What would you like to do?</p>

			<ul class="shortcut-buttons-set">

				<li><a class="shortcut-button" href="FormAdd.jsp"><span>
							<img src="resources/images/icons/pencil_48.png" alt="icon" /><br />
							添加工单
					</span></a></li>

				<li><a class="shortcut-button" href="FormSearch_operator.jsp"><span>
							<img src="resources/images/icons/paper_content_pencil_48.png"
							alt="icon" /><br /> 工单列表
					</span></a></li>

			</ul>
			<!-- End .shortcut-buttons-set -->

			<div class="clear"></div>
			<!-- End .clear -->

			<div class="content-box">
				<!-- Start Content Box -->

				<div class="content-box-header">

					<h3>超期未完成产品</h3>


					<div class="clear"></div>

				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div class="tab-content default-tab" id="tab1">
						<!-- This is the target div. id must match the href of this div's tab -->

						<table>
							<tfoot>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td><a href="#"> more</a></td>
								</tr>
							</tfoot>

							<tbody>
								<tr>
									<td><h3>生产一部</h3></td>
									<td><h3>生产二部</h3></td>
									<td><h3>生产三部</h3></td>
									<td><h3>生产四部</h3></td>
									<td><h3>生产五部</h3></td>
									<td><h3>生产六部</h3></td>
								</tr>

								<tr>
									<td><h6>product6:</h6>
										<a href="SheetJD1.jsp" title="title">20120624</a></td>
									<td><h6>product7:</h6>
										<a href="#" title="title">20120602</a></td>
									<td><h6>product8:</h6>
										<a href="#" title="title">20120603</a></td>
									<td><h6>product9:</h6>
										<a href="#" title="title">20120604</a></td>
									<td><h6>product0:</h6>
										<a href="#" title="title">20120605</a></td>
									<td><h6>product1:</h6>
										<a href="#" title="title">20120606</a></td>
								</tr>

								<tr>
									<td><h6>product7:</h6>
										<a href="#" title="title">20120601</a></td>
									<td><h6>product8:</h6>
										<a href="#" title="title">20120602</a></td>
									<td><h6>product9:</h6>
										<a href="#" title="title">20120603</a></td>
									<td><h6>product0:</h6>
										<a href="#" title="title">20120604</a></td>
									<td><h6>product1:</h6>
										<a href="#" title="title">20120605</a></td>
									<td><h6>product2:</h6>
										<a href="#" title="title">20120606</a></td>
								</tr>
							</tbody>

						</table>

					</div>
					<!-- End #tab1 -->



				</div>
				<!-- End .content-box-content -->

			</div>
			<!-- End .content-box -->


			<div class="content-box">
				<!-- Start Content Box -->

				<div class="content-box-header">

					<h3>正在生产产品</h3>


					<div class="clear"></div>

				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div class="tab-content default-tab" id="tab1">
						<!-- This is the target div. id must match the href of this div's tab -->

						<table>
							<tfoot>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td><a href="#"> more</a></td>
								</tr>
							</tfoot>

							<tbody>
								<tr>
									<td><h3>生产一部</h3></td>
									<td><h3>生产二部</h3></td>
									<td><h3>生产三部</h3></td>
									<td><h3>生产四部</h3></td>
									<td><h3>生产五部</h3></td>
									<td><h3>生产六部</h3></td>
								</tr>

								<tr>
									<td><h6>product6:</h6>
										<a href="SheetJD1.jsp" title="title">20120501</a></td>
									<td><h6>product7:</h6>
										<a href="#" title="title">20120502</a></td>
									<td><h6>product8:</h6>
										<a href="#" title="title">20120503</a></td>
									<td><h6>product9:</h6>
										<a href="#" title="title">20120504</a></td>
									<td><h6>product0:</h6>
										<a href="#" title="title">20120505</a></td>
									<td><h6>product1:</h6>
										<a href="#" title="title">20120506</a></td>
								</tr>

								<tr>
									<td><h6>product7:</h6>
										<a href="#" title="title">20120501</a></td>
									<td><h6>product8:</h6>
										<a href="#" title="title">20120502</a></td>
									<td><h6>product9:</h6>
										<a href="#" title="title">20120503</a></td>
									<td><h6>product0:</h6>
										<a href="#" title="title">20120504</a></td>
									<td><h6>product1:</h6>
										<a href="#" title="title">20120505</a></td>
									<td><h6>product2:</h6>
										<a href="#" title="title">20120506</a></td>
								</tr>
							</tbody>

						</table>

					</div>
					<!-- End #tab1 -->



				</div>
				<!-- End .content-box-content -->

			</div>
			<!-- End .content-box -->


			<div class="clear"></div>

			<div id="footer">
				<small> <!-- Remove this notice or replace it with whatever you want -->
					&#169; Copyright 2012 Your Company | Powered by 顺江实验室 | <a href="#">Top</a>
				</small>
			</div>
			<!-- End #footer -->

		</div>
		<!-- End #main-content -->

	</div>
</body>

<!-- Download From www.exet.tk-->
</html>
