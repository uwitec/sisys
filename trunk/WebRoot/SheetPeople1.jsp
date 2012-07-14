<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Simpla Admin</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<SCRIPT type=text/javascript>
		function tableExportor(id){ 
			var forum = document.forms["tableExport"]; 
			forum.title.value = document.getElementById('tableTitle').innerHTML;
			forum.content.value=eval(id+".innerHTML"); 
			forum.submit(); 
		} 
	</SCRIPT>

  </head>
  
  <body>
    <h2 align="center" id="tableTitle">员工工作统计表</h2>
	<form name="tableExport" method="POST" action="tableExport.action">
		<input type="hidden" name="title">
		<input type="hidden" name="content">
		<div align="center"><input  onclick=tableExportor('MainTable') class="button" type="button" value="导出"></div>
	</form>
	<br></br>
	<div align="center">
		<table border="1" cellspacing="1" cellpadding="5" id="MainTable">
				<tr align="center">
					<td width=10%>员工名称</td>
					<td width=10%>张三</td>
					<td width=10%>工号</td>
					<td colspan = 2 width=20%>29001040</td>
					<td width=10%>所属部门</td>
					<td colspan=2 width=20%>机电</td>
				</tr>
				<tr align="center">
					<td width=10%>序号</td>
					<td width=10%>产品名称</td>
					<td width=10%>产品编号</td>
					<td width=10%>工序</td>
					<td width=10%>合格数量</td>
					<td width=10%>工费数量</td>
					<td width=10%>报废数量</td>
					<td width=10%>工时统计</td>
				</tr>
				<tr align="center">
					<td width=10%>001</td>
					<td width=10%>转轴</td>
					<td width=10%>01002</td>
					<td width=10%>3</td>
					<td width=10%>300</td>
					<td width=10%>1</td>
					<td width=10%>1</td>
					<td width=10%>3</td>
				</tr>
				<tr align="center">
					<td width=10%>002</td>
					<td width=10%>转轴</td>
					<td width=10%>01003</td>
					<td width=10%>2</td>
					<td width=10%>290</td>
					<td width=10%>2</td>
					<td width=10%>1</td>
					<td width=10%>2</td>
				</tr>

			</table>
	</div>
  </body>
</html>
