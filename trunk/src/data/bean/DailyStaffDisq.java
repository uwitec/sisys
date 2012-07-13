package data.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 记录每天员工生产的不合格品总数及详情链接（disqdeId） 即员工_不合格详情（DisqDetail）提供链表入口
 */
public class DailyStaffDisq {

	private Integer id = null;
	private int disqdeId;
	private int staffId;
	private int totalNum;
	private Date time;

	// get和set方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getDisqdeId() {
		return disqdeId;
	}

	public void setDisqdeId(int disqdeId) {
		this.disqdeId = disqdeId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	// 得到hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + disqdeId;
		result = prime * result + id;
		result = prime * result + staffId;
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
		DailyStaffDisq other = (DailyStaffDisq) obj;
		if (disqdeId != other.disqdeId)
			return false;
		if (id != other.id)
			return false;
		if (staffId != other.staffId)
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
	public DailyStaffDisq(int id, int disqdeId, int staffId, int totalNum,
			Date time) {
		super();
		this.id = id;
		this.disqdeId = disqdeId;
		this.staffId = staffId;
		this.totalNum = totalNum;
		this.time = time;
	}

	public DailyStaffDisq() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "DailyStaffDisq [id=" + id + ", disqdeId=" + disqdeId
				+ ", staffId=" + staffId + ", totalNum=" + totalNum + ", time="
				+ time + "]";
	}

}
