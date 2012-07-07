package data.bean;

public class UserBean {
	private Integer id;
	private String username;
	private String password;
	private Integer level;
	
	public UserBean(){
		
	}
	
	public UserBean(Integer id,String username,String password,Integer level){
		this.id = id;
		this.username = username;
		this.password = password;
		this.level = level;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return id;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	public void setLevel(Integer level){
		this.level = level;
	}
	public Integer getLevel(){
		return level;
	}
}
