<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Simpla Admin</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

  </head>
  
  <body>
  <%int i=1; %>
    <h2 align="center">员工工作统计表(时间:<s:property value="sTime"/>到<s:property value="eTime" />)</h2>
	<div align="center"><a class="button" href="#">导出</a></div>
	<br></br>
	<div align="center">
		<table border="1" cellspacing="1" cellpadding="5">
				<tr align="center">
					<th width=10%>员工名称</th>
					<th width=10%><s:property value="staName"/></th>
					<th width=10%>工号</th>
					<th colspan=2 width=20%><s:property value="staNo"/></th>
					<th width=10%>所属部门</th>
					<th colspan=2 width=20%><s:property value="deptName"/></th>
				</tr>
				<tr align="center">
					<td width=10%>序号</td>
					<td width=10%>产品名称</td>
					<td width=10%>产品编号</td>
					<td width=10%>工序</td>
					<td width=10%>合格数量</td>
					<td width=10%>工费数量</td>
					<td width=10%>料废数量</td>
					<td width=10%>工时统计</td>
				</tr>
				<s:iterator value="Ppsheet">
				<tr align="center">
					<td width=10%><%=i++%></td>
					<td width=10%><s:property value="proName"/> </td>
					<td width=10%><s:property value="proNo"/></td>
					<td width=10%><s:property value="procName"/></td>
					<td width=10%><s:property value="quaNum" /></td>
					<td width=10%><s:property value="gWaste" /></td>
					<td width=10%><s:property value="lWaste" /></td>
					<td width=10%><s:property value="workHours" /></td>
				</tr>
				</s:iterator>
				

			</table>
	</div>
  </body>
</html>
