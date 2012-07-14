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
    <h2 align="center">产品废品统计表(时间:<s:property value="sTime" /></th>-<th><s:property value="eTime" /></th>)</h2>
	<div align="center"><a class="button" href="#">导出</a></div>
	<br></br>
	<div align="center">
		<table border="1" cellspacing="1" cellpadding="5">
				<tr align="center">
					<th width=25%>批次号</th>
					<th width=25%>总数量</th>
					<th width=25%>废品数量</th>
					<th width=25%>废品率</th>
				</tr>
				
				<s:iterator value="Pdsheet">
				<tr align="center">				
					<td><s:property value="batchNo"/></td>	
					<td><s:property value="completeNum" /></td>	
					<td><s:property value="disqNum"/></td>	
					<td><s:property value="disqPercent"/></td>
				</tr>	
				</s:iterator>
				
				<tr align="center">
					<td>合计</td>
					<td><s:property value="TcompleteNum"/></td>
					<td><s:property value="TdisqNum"/></td>
					<td><s:property value="TdisqPercent"/></td>
				</tr>

			</table>
	</div>
  </body>
</html>
