package data.build;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import data.bean.User;
import data.dao.UserDAO;

//读XLS文件，得到User的集合并将其存入数据库中
class ReadUser {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
	List<User> userList = new ArrayList<User>();
	User user;
	public List<User> getUserList(Sheet sheet) throws ParseException {
		for(int rowNum=1; rowNum<sheet.getColumns(); rowNum ++) {
			Cell[] c = sheet.getRow(rowNum);
			user = new User();
			user.setId(Integer.parseInt(c[0].getContents()));
			user.setUsername(c[1].getContents());
			user.setPassword(c[2].getContents());
			user.setLevel(Integer.parseInt(c[3].getContents()));
			user.setIsDelete(Integer.parseInt(c[4].getContents()));
			//System.out.println(c[5].getContents().equals("null"));
			user.setDeleteTime(c[5].getContents().equals("null") ? null : sdf.parse(c[5].getContents()));
			System.out.println(user);
			userList.add(user);
		}
		return userList;
	} 
	public void saveUserList() {
		for(int i=0; i<userList.size(); i++) {

			UserDAO userDao = new UserDAO();
			userDao.create(userList.get(i));
		}
	}
	public void save(Sheet sheet) {
		try {
			getUserList(sheet);
			saveUserList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}