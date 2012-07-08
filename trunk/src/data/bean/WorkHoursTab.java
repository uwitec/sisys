package data.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 工时表 用于记录员工每天的工时和工钱
 */
public class WorkHoursTab {

	private int id;
	private int staId;
	private Date time;
	private double workHours; // 工时，根据工序得到
	private double salary; // 工钱，根据工序得到

	// get和set方法
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStaId() {
		return staId;
	}

	public void setStaId(int staId) {
		this.staId = staId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getWorkHours() {
		return workHours;
	}

	public void setWorkHours(double workHours) {
		this.workHours = workHours;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	// 得到hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + staId;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		temp = Double.doubleToLongBits(workHours);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	// 判断两个类是否相同
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkHoursTab other = (WorkHoursTab) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(salary) != Double
				.doubleToLongBits(other.salary))
			return false;
		if (staId != other.staId)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (Double.doubleToLongBits(workHours) != Double
				.doubleToLongBits(other.workHours))
			return false;
		return true;
	}

	// 构造函数
	public WorkHoursTab(int id, int staId, Date time, double workHours,
			double salary) {
		super();
		this.id = id;
		this.staId = staId;
		this.time = time;
		this.workHours = workHours;
		this.salary = salary;
	}

	public WorkHoursTab() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "WorkHoursTab [id=" + id + ", staId=" + staId + ", time=" + time
				+ ", workHours=" + workHours + ", salary=" + salary + "]";
	}

}
