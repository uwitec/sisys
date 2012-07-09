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

		<div id="sidebar"><div id="sidebar-wrapper"> <!-- Sidebar with logo and menu -->
			
			<h1 id="sidebar-title"><a href="#">生产物流信息统计系统</a></h1>
		  
			<!-- Logo (221px wide) -->
			<a href="#"><img id="logo" src="resources/images/log0-l.png" alt="logo" /></a>
		  
			<!-- Sidebar Profile links -->

			<ul id="main-nav">  <!-- Accordion Menu -->
				
				<li>
					<a href="Index_admin.jsp" class="nav-top-item no-submenu"> <!-- Add the class "current" to current menu item -->
						首页
					</a>       
				</li>
				
				<li>
					<a href="BatchAdd.jsp" class="nav-top-item no-submenu">
						新建批次
					</a>       
				</li>
				
				<li> 
					<a href="#" class="nav-top-item">
					工单管理
					</a>
					<ul>
						<li><a href="FormSearch_admin.jsp">工单列表</a></li>
					</ul>
				</li>
				
				<li>
					<a href="#" class="nav-top-item">
						统计单管理
					</a>
					<ul>
						<li><a href="SearchJD_admin.jsp">生产进度统计单</a></li>
						<li><a href="SearchHour_admin.jsp">工时统计单</a></li>
						<li><a href="SearchProduct_admin.jsp">不合格品统计单</a></li>
						<li><a href="SearchPeople_admin.jsp">员工工作统计单</a></li>
					</ul>
				</li>
				
				<li>
					<a href="OutOfDue.jsp" class="nav-top-item no-submenu"> <!-- Add the class "no-submenu" to menu items with no sub menu -->
						超期批次管理
					</a>
				</li>
				
				<li>
					<a href="#" class="nav-top-item">
						数据管理
					</a>
					<ul>
						<li><a href="DataSave.jsp">数据备份</a></li>
						<li><a href="DataLogin.jsp">数据导入</a></li>
					</ul>
				</li>
				
				<li>
					<a href="UserAdd.jsp" class="nav-top-item current no-submenu"> <!-- Add the class "no-submenu" to menu items with no sub menu -->
						添加用户
					</a>
				</li>
				
				<li>
					<a href="AdminInfo_admin.jsp" class="nav-top-item no-submenu"> <!-- Add the class "no-submenu" to menu items with no sub menu -->
						个人信息管理
					</a>
				</li>
				     
				
			</ul> <!-- End #main-nav -->
			
			
		</div></div> <!-- End #sidebar -->

		<div id="main-content">
			<!-- Main Content Section with everything -->

			<!-- Page Head -->
			<h2>添加用户</h2>
			<p id="page-intro">Add User</p>



			<div class="clear"></div>
			<!-- End .clear -->

			<div class="content-box">
				<!-- Start Content Box -->

				<div class="content-box-header">

					<h3>Search Box</h3>


					<div class="clear"></div>

				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div id="login-content">

						<form action="#">
							<p>
								<label>用户类型</label> <select>
									<option value="">----请选择--</option>
									<option>普通查看人员</option>
									<option>输入人员</option>
									<option>管理员</option>
								</select>
							</p>

							<p>
								<label>初始用户名</label> <input class="text-input" type="text" />
							</p>
							<div class="clear"></div>
							<p>
								<label>初始用户密码</label> <input class="text-input" type="text" />
							</p>
							<div class="clear"></div>

							<p>
								<input class="button" type="button" value="确定" />
							</p>
							<div class="clear"></div>
						</form>
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
