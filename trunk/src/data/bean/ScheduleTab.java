package data.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 进度表信息，汇总每天已完成工序及其数量。用于导出进度表
 */
public class ScheduleTab {

	private int id;
	private int batchId;
	private Date time;
	private String colorNo;
	private int num;
	private int wtId;

	// get和set方法
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getColorNo() {
		return colorNo;
	}

	public void setColorNo(String colorNo) {
		this.colorNo = colorNo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	// 得到hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batchId;
		result = prime * result + ((colorNo == null) ? 0 : colorNo.hashCode());
		result = prime * result + id;
		result = prime * result + num;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + wtId;
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
		ScheduleTab other = (ScheduleTab) obj;
		if (batchId != other.batchId)
			return false;
		if (colorNo == null) {
			if (other.colorNo != null)
				return false;
		} else if (!colorNo.equals(other.colorNo))
			return false;
		if (id != other.id)
			return false;
		if (num != other.num)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (num != other.num)
			return false;
		return true;
	}

	// 构造函数
	public ScheduleTab(int id, int batchId, Date time, String colorNo, int num, int wtId) {
		super();
		this.id = id;
		this.batchId = batchId;
		this.time = time;
		this.colorNo = colorNo;
		this.num = num;
		this.wtId = wtId;
	}

	public ScheduleTab() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "ScheduleTab [id=" + id + ", batchId=" + batchId + ", time="
				+ time + ", colorNo=" + colorNo + ", num=" + num + ", wtId=" + wtId + "]";
	}

	public void setWtId(int wtId) {
		this.wtId = wtId;
	}

	public int getWtId() {
		return wtId;
	}

}
