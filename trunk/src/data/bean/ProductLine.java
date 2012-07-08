package data.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 生产线信息
 */
public class ProductLine {

	private int id;
	private String lineDesc;
	private int isDelete;
	private Date deleteTime;

	// get 和set 方法
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLineDesc() {
		return lineDesc;
	}

	public void setLineDesc(String lineDesc) {
		this.lineDesc = lineDesc;
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
		result = prime * result + id;
		result = prime * result + isDelete;
		result = prime * result
				+ ((lineDesc == null) ? 0 : lineDesc.hashCode());
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
		ProductLine other = (ProductLine) obj;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (id != other.id)
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (lineDesc == null) {
			if (other.lineDesc != null)
				return false;
		} else if (!lineDesc.equals(other.lineDesc))
			return false;
		return true;
	}

	// 构造函数
	public ProductLine(int id, String lineDesc, int isDelete, Date deleteTime) {
		super();
		this.id = id;
		this.lineDesc = lineDesc;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}

	public ProductLine() {
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "ProductLine [id=" + id + ", lineDesc=" + lineDesc
				+ ", isDelete=" + isDelete + ", deleteTime=" + deleteTime + "]";
	}

}
