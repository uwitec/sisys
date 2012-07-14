<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
		
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
	<title>Simpla Admin</title>
	<style type="text/css">
	td{
		height:20px;
	}
	.b20120502 .color22{
		background-color:blue;
	}
	.b20120502 .color26{
		background-color:red;
	}
	.b20120502 .color30{
		background-color:green;
	}
	.b20120502 .color31{
		background-color:yellow;
	}
	.b20120624 .color25{
		background-color:blue;
	}
	.b20120624 .color29{
		background-color:red;
	}
	.b20120624 .color4{
		background-color:green;
	}
	.b20120624 .color8{
		background-color:yellow;
	}
	</style>
		
	</head>
  
	<body><div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
		
		
		
		<div id="main-content"> <!-- Main Content Section with everything -->
			
			
			
			<div class="content-box"><!-- Start Content Box -->
				
				
				
				<div class="content-box-content">
					
					<div class="tab-content default-tab" id="tab1"> <!-- This is the target div. id must match the href of this div's tab -->
						
						<h2 style="text-align:center">部门产品生产进度汇总表</h2>
						<div align="center"><a class="button" href="SheetProduct1.html">查看不合格品统计</a><a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a><a class="button" href="#">导出</a></div>
						<br></br>
						<table border=1 cellpadding=0 cellspacing=0 class = "" style="font-size:15px;margin:0px auto;">
							<thead>
								<tr>
									<th colspan=3>日期</th>
									<s:iterator value="Jdsheet">
									<th><s:property value="Time" /></th>
									</s:iterator>
								</tr>
								<tr>
									<th>产品名称</th>
									<th>产品编号</th>
									<th>批次</th>
								</tr>																					
							</thead>
							<tbody style="font-size:14px">
								<tr class="b20120502">
								<td rowspan=2><s:property value="proName" /></td>
								<td rowspan=2><s:property value="proNo" /></td>
								<td rowspan=2><s:property value="batchNo" /></td>
								<s:iterator value="Jdsheet">				
								<td><s:property value="num" /></td>	
								</s:iterator>
									<td class="color22">3000</td>
									<td class="color23"></td>
									<td class="color24"></td>
									<td class="color22">1000</td>
									<td class="color26">3000</td>
									<td class="color27"></td>
									<td class="color28"></td>
									<td class="color29"></td>
									<td class="color30">1500</td>
									<td class="color31">1500</td>
									<td class="color1"></td>
									<td class="color2"></td>
									<td class="color3"></td>
									<td class="color4"></td>
									<td class="color5"></td>
									<td class="color6"></td>
									<td class="color7"></td>
									<td class="color8"></td>
									<td class="color9"></td>
									<td class="color10"></td>
									<td class="color11"></td>
									<td class="color12"></td>
									<td class="color13"></td>
									<td class="color14"></td>
									<td class="color15"></td>
									<td class="color16"></td>
									<td class="color17"></td>
									<td class="color18"></td>
									<td class="color19"></td>
									<td class="color20"></td>
								</tr>
								<tr class="b20120502">
									<td class="color21"></td>
									<td class="color26">1000</td>
									<td class="color23"></td>
									<td class="color24"></td>
									<td class="color2"></td>
									<td class="color2"></td>
									<td class="color27"></td>
									<td class="color28"></td>
									<td class="color29"></td>
									<td class="color3"></td>
									<td class="color3"></td>
									<td class="color1"></td>
									<td class="color2"></td>
									<td class="color3"></td>
									<td class="color4"></td>
									<td class="color5"></td>
									<td class="color6"></td>
									<td class="color7"></td>
									<td class="color8"></td>
									<td class="color9"></td>
									<td class="color10"></td>
									<td class="color11"></td>
									<td class="color12"></td>
									<td class="color13"></td>
									<td class="color14"></td>
									<td class="color15"></td>
									<td class="color16"></td>
									<td class="color17"></td>
									<td class="color18"></td>
									<td class="color19"></td>
									<td class="color20"></td>
								</tr>
								<tr class="b20120624">
									<td rowspan=2>B21曲轴链轮</td>
									<td rowspan=2>1111702</td>
									<td rowspan=2><font color="red">20120624</font></td>
									<td class="color21"></td>
									<td class="color22"></td>
									<td class="color23"></td>
									<td class="color24"></td>
									<td class="color25">3000</td>
									<td class="color26"></td>
									<td class="color27"></td>
									<td class="color28"></td>
									<td class="color29">3000</td>
									<td class="color30"></td>
									<td class="color31"></td>
									<td class="color1"></td>
									<td class="color2"></td>
									<td class="color3"></td>
									<td class="color4">1500</td>
									<td class="color5"></td>
									<td class="color6"></td>
									<td class="color7"></td>
									<td class="color4">1500</td>
									<td class="color9"></td>
									<td class="color10"></td>
									<td class="color11"></td>
									<td class="color12"></td>
									<td class="color13"></td>
									<td class="color14"></td>
									<td class="color15"></td>
									<td class="color16"></td>
									<td class="color17"></td>
									<td class="color18"></td>
									<td class="color19"></td>
									<td class="color20"></td>
								</tr>
								<tr class="b20120624">
									<td class="color21"></td>
									<td class="color22"></td>
									<td class="color23"></td>
									<td class="color24"></td>
									<td class="color2"></td>
									<td class="color26"></td>
									<td class="color27"></td>
									<td class="color28"></td>
									<td class="color2"></td>
									<td class="color30"></td>
									<td class="color31"></td>
									<td class="color1"></td>
									<td class="color2"></td>
									<td class="color3"></td>
									<td class="color20"></td>
									<td class="color5"></td>
									<td class="color6"></td>
									<td class="color7"></td>
									<td class="color8">1500</td>
									<td class="color9"></td>
									<td class="color10"></td>
									<td class="color11"></td>
									<td class="color12"></td>
									<td class="color13"></td>
									<td class="color14"></td>
									<td class="color15"></td>
									<td class="color16"></td>
									<td class="color17"></td>
									<td class="color18"></td>
									<td class="color19"></td>
									<td class="color20"></td>
								</tr>
							</tbody>
						</table>
						
						
						
						
					</div> <!-- End #tab1 -->
					
  
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->

			
			
		</div> <!-- End #main-content -->
		
	</div></body>
  

<!-- Download From www.exet.tk-->
</html>
