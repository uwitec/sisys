<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="data.bean.Batch"%>
<%@ page import="data.bean.Flowpath"%>
<%@ page import="data.bean.Product"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

function $(id) {    
        return document.getElementById(id);    
}    
//getAjax请求    
//创建XMLHttpRequest对象    
function getXhr() {    
        var xhr;    
        try {    
                //IE浏览器    
                xhr = new ActiveXObject("Microsoft.XMLHTTP");    
        } catch (err) {    
                try {    
                        //firefox opera 等其他浏览器    
                        xhr = new XMLHttpRequest();    
                } catch (er) {    
                        alert("您的浏览器不支持ajax技术的操作,请您升级.....");    
                }    
    
        }    
    
        return xhr;    
}      
  
function checkuser() {

  var user = document.getElementById("cname").value;
  //获取xhr对象    
                     var xhr = getXhr();    
                                xhr.open("get","/test.action?entity.cname="+user,true);    
                                xhr.send();    
                                document.getElementById("spanUser").innerHTML = '<img src=images/wait.gif />';
                                xhr.onreadystatechange = function (){    
          if(xhr.readyState==4 && xhr.status==200){
            if(xhr.responseText){
              document.getElementById("spanUser").innerHTML = '<img src=images/cry.png />';
            }else{
              document.getElementById("spanUser").innerHTML = '<img src=images/smile.png />';
            }
          }
        }
}
<html>
<body>
<table>
<tr>

  <td class="td01">
    登录名<span class=" text_o">*</span>：
  </td>

  <td class="td02" style="width: 400px;">

    <div class="left">
      <input name="entity.cname" id="cname" type="text" size="30" onblur="checkuser()">
        <span id="spanUser"></span>
    </div>
  </td>

</tr>
</table>
</body>
</html>