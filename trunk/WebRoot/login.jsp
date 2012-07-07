<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
<title>物流信息统计系统</title>
		
		<!--                       CSS                       -->
	  
		<!-- Reset Stylesheet -->
		<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />
	  
		<!-- Main Stylesheet -->
		<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />
		
		<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
		<link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />	
		
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
		<script type="text/javascript" src="resources/scripts/jquery-1.3.2.min.js"></script>
		
		<!-- jQuery Configuration -->
		<script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
		
		<!-- Facebox jQuery Plugin -->
		<script type="text/javascript" src="resources/scripts/facebox.js"></script>
		
		<!-- jQuery WYSIWYG Plugin -->
		<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>
		
		<!-- Internet Explorer .png-fix -->
		
		<!--[if IE 6]>
			<script type="text/javascript" src="resources/scripts/DD_belatedPNG_0.0.7a.js"></script>
			<script type="text/javascript">
				DD_belatedPNG.fix('.png_bg, img, li');
			</script>
		<![endif]-->
		
	</head>
  
	<body id="login">
		
  <div id="login-wrapper" class="png_bg">
			<div id="login-top">
			
				<h1>物流信息统计系统</h1>
				<!-- Logo (221px width) -->
				<img id="logo" src="resources/images/logo1.png" alt="Simpla Admin logo" />
			</div> <!-- End #logn-top -->
			
			<div id="login-content">
				
				<form action="login.action" method="post">
				
					<div class="notification information png_bg">
						<div>
							首次使用，用户名密码都是admin
						</div>
					</div>
					
					<p>
						<select name="level" class="" style="width: 300px">
								<option>请选择登录权限</option>
								<option value="1">查看人员</option>
								<option value="2">输入人员</option>
								<option value="3">管理员</option>
						</select>
					</p>
					
					<div><br></br></div>
					
					<p>
						<label>用户名</label>
						<input class="text-input" type="text" name="username"/>
					</p>
					<div class="clear"></div>
					<p>
						<label>密码</label>
						<input class="text-input" type="password" name="password"/>
					</p>
					<div class="clear"></div>
					<p id="remember-password">
						<input type="checkbox" />记住我
					</p>
					<div class="clear"></div>
					<p><input type="submit" class="button" value="登录"></p>
						
					
				</form>
				
			</div> <!-- End #login-content -->
			
		</div> <!-- End #login-wrapper -->
		
  </body>
  </html>
