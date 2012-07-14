<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="data.bean.DisqKind"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%
	List<DisqKind> dkList = (List)request.getAttribute("dkList");
	System.out.println(dkList);
	int height = (Integer)request.getAttribute("height");
%>

<%	
	String error = request.getParameter("result");
	if(error == null) {
		error = "";
	} else if(error.equals("success")) {
		error = "添加成功！";
	}  else if(error.equals("false")) {
		error = "添加失败！";
	}
%>

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

<script type="text/javascript"
	src="resources/scripts/addWorkForm.js"></script>

<script type="text/javascript"
	src="resources/scripts/addDisqKind.js"></script>

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
		
<base target="_self" />

</head>

<body>
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->

		<
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
						class="nav-top-item no-submenu"> <!-- Add the class "current" to current menu item -->
							首页
					</a></li>
				
				<li>
					<a href="BatchAdd.jsp" class="nav-top-item no-submenu">
						新建批次
					</a>       
				</li>

					<li><a href="#" class="nav-top-item current"> 工单管理 </a>
						<ul>
							<li><a href="FormAdd.jsp">添加工单</a></li>
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

			<div class="clear"></div>
			<!-- End .clear -->

			<div class="content-box">
				<!-- Start Content Box -->

				<div class="content-box-header">

					<h3>工单添加</h3>


					<div class="clear"></div>

				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div class="tab-content default-tab" id="tab1">
						<!-- This is the target div. id must match the href of this div's tab -->

						<form id="" action="addWorkForm.action" method="get">
							<table class="" id="table">
								<tr>
									<td><span>工号</span></td>
									<td><input type="text" width="50px"  id="staNo" name="staff.staNo" onblur="displayStaNo()"/></td>
				
									<td><span>姓名</span></td>
									<td><input type="text" width="50px"  id="staName" name="staff.staName"/></td>
										
								</tr>
								<tr>
									<td><span>产品编号</span></td>
									<td><input type="text" width="50px" id="proNo" name="product.proNo" onblur="displayProNo()"/></td>
								
									<td><span>产品名称</span></td>
									<td><input type="text" width="50px" id="proName" name="product.proName"/></td>
									</tr>
								<tr>
									<td><span>工序编号</span></td>
									<td><input type="text" width="50px" id="procNo" name="processes.procNo" onblur="displayProcNo()"/></td>
								
									<td><span>工序名称</span></td>
									<td><input type="text" width="50px" id="procName" name="processes.procName"/></td>									
									</tr>
								<tr>
									<td><span>批次号</span></td>
									<td><input type="text" width="50px" name="batchNo"/></td>
								</tr>
								<tr>
									
									<td><span>合格品数量</span></td>
									<td><input type="text" width="50px" name="workForm.quaNum"/></td>
								</tr>
								<tr>
									<td><span>不合格品种类</span></td>
									<td><select name="disqKind1" class="" style="width: 155px">
										<c:forEach items="${dkList }" var="entity">
											<option value="${entity.id }">${entity.disDesc}</option>
										</c:forEach>
											<option value="0" onclick="addDK()">添加种类</option>
									</select></td>
									<td><span>不合格品数量</span></td>
									<td><input type="text" width="50px" name="disqNum1"/></td>
								</tr>
								<tr>
									<td><span>不合格品种类</span></td>
									<td><select name="disqKind2" class="" style="width: 155px">
										<c:forEach items="${dkList }" var="entity">
											<option value="${entity.id }">${entity.disDesc}</option>
										</c:forEach>
											<option value="0" onclick="addDK()">添加种类</option>
									</select></td>
									<td><span>不合格品数量</span></td>
									<td><input type="text" width="50px" name="disqNum2"/></td>
								</tr>
								<tr>
									<td><span>不合格品种类</span></td>
									<td><select name="disqKind3" class="" style="width: 155px">
										<c:forEach items="${dkList }" var="entity">
											<option value="${entity.id }">${entity.disDesc}</option>
										</c:forEach>
											<option value="0" onclick="addDK()">添加种类</option>
									</select></td>
									<td><span>不合格品数量</span></td>
									<td><input type="text" width="50px" name="disqNum3"/></td>
								</tr>
								
								<%for(int i=4; i<height-1; i++) {
								%>
								<tr>
									<td><span>不合格品种类</span></td>
									<td><select name="disqKind"+${i} class="" style="width: 155px">
										<c:forEach items="${dkList}" var="entity">
											<option value="${entity.id }">${entity.disDesc}</option>
										</c:forEach>
											<option value="0" onclick="addDK()">添加种类</option>
									</select></td>
									<td><span>不合格品数量</span></td>
									<td><input type="text" width="50px" name="disqNum4"/></td>
								</tr>
								<%}%>
								
								<tr>
									<td><span>不合格品种类</span></td>
									<td><select name="disqKind3" class="" style="width: 155px">
										<c:forEach items="${dkList }" var="entity">
											<option value="${entity.id }">${entity.disDesc}</option>
										</c:forEach>
											<option value="0" onclick="addDK()">添加种类</option>
									</select></td>
									<td><span>不合格品数量</span></td>
									<td><input type="text" width="50px" name="disqNum3"/>
										<input class="button" type="button" value="+" onclick=addMore(<%=++height%>)>
									</td>
								</tr>
									
								<tr>
									<td><input class="button" type="submit" value="提交"/></td>
									<td><input class="button" type="reset" value="重置"/></td>
								</tr>
							</table>


						</form>

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
