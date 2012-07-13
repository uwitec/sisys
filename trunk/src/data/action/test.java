package data.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class test extends BaseAction {
	public String checkLogin() throws Exception {
	    
	    /** 获取response对象 */
	    HttpServletResponse response = ServletActionContext.getResponse();
	    /** 获取输出out对象 */
	    PrintWriter out = response.getWriter();
	   // Customers cus = customersServiceImpl.checkLogin(entity);
	    
	    if (true) {
	      out.print("1111");
	    }else{
	      
	    }
	    /** 这里返回的是null */
	    return null;
	  }
}
