package data.build;

import java.io.*; 

import jxl.*; 

public class DataRead { 

	String command = "cmd.exe /c mysqldump -uroot -proot sisys>";
	
	public void chooseFilePath(String filePath) {
		command +=filePath;
	}
	
	public void read() {
		try {

			Process process = Runtime.getRuntime().exec(command);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String args[]) { 
		/*try { 
			Workbook book= 	Workbook.getWorkbook(new File("f://user.xls")); 
			Sheet sheet=book.getSheet(0); 
			ReadUser ru = new ReadUser();
			Sheet sheet=book.getSheet(2); 
			ReadUser ru = new ReadUser();
			ru.save(sheet);
	 		book.close(); 
		} catch(Exception e) { 
			System.out.println(e); 
		} */
		DataRead dr = new DataRead();
		dr.chooseFilePath("f:/s.sql");
		dr.read();
	} 
	
}

