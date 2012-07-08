package sisys.data.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 产品信息
 * 
 */
public class Product {

	private int id;
	private int deptId;
	private int prolineId; // 生产线ID号
	private String proNo;
	private String proName;
	private Date time;
	private int disqNum; // 不合格数量
	private double disqPerc; // 不合格品百分比
	private int totalNum; // 目标数量
	private int isDelete;
	private Date deleteTime;

	// get和set方法
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getProlineId() {
		return prolineId;
	}

	public void setProlineId(int prolineId) {
		this.prolineId = prolineId;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getDisqNum() {
		return disqNum;
	}

	public void setDisqNum(int disqNum) {
		this.disqNum = disqNum;
	}

	public double getDisqPerc() {
		return disqPerc;
	}

	public void setDisqPerc(double disqPerc) {
		this.disqPerc = disqPerc;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	// 得到hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result + deptId;
		result = prime * result + disqNum;
		long temp;
		temp = Double.doubleToLongBits(disqPerc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + isDelete;
		result = prime * result + ((proName == null) ? 0 : proName.hashCode());
		result = prime * result + ((proNo == null) ? 0 : proNo.hashCode());
		result = prime * result + prolineId;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + totalNum;
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
		Product other = (Product) obj;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (deptId != other.deptId)
			return false;
		if (disqNum != other.disqNum)
			return false;
		if (Double.doubleToLongBits(disqPerc) != Double
				.doubleToLongBits(other.disqPerc))
			return false;
		if (id != other.id)
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (proName == null) {
			if (other.proName != null)
				return false;
		} else if (!proName.equals(other.proName))
			return false;
		if (proNo == null) {
			if (other.proNo != null)
				return false;
		} else if (!proNo.equals(other.proNo))
			return false;
		if (prolineId != other.prolineId)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (totalNum != other.totalNum)
			return false;
		return true;
	}

	// 构造函数
	public Product(int id, int deptId, int prolineId, String proNo,
			String proName, Date time, int disqNum, double disqPerc,
			int totalNum, int isDelete, Date deleteTime) {
		super();
		this.id = id;
		this.deptId = deptId;
		this.prolineId = prolineId;
		this.proNo = proNo;
		this.proName = proName;
		this.time = time;
		this.disqNum = disqNum;
		this.disqPerc = disqPerc;
		this.totalNum = totalNum;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "Product [id=" + id + ", deptId=" + deptId + ", prolineId="
				+ prolineId + ", proNo=" + proNo + ", proName=" + proName
				+ ", time=" + time + ", disqNum=" + disqNum + ", disqPerc="
				+ disqPerc + ", totalNum=" + totalNum + ", isDelete="
				+ isDelete + ", deleteTime=" + deleteTime + "]";
	}

}
