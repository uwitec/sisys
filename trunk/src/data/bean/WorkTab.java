package data.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 工作表 记录一个批次中按流程顺序排列的所有工序的完成情况， 即按链表形式组织工序，提供接口（id号）给对应批次
 */
public class WorkTab {

	private Integer id = null;
	private int procId;
	private int quNum;
	private int disqNum;
	private int isOver; // 判断该工序是否已完成
	private Date OverTime;
	private int isEnd; // 判断是否是该流程的最后一个工序

	// get和set方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getProcId() {
		return procId;
	}

	public void setProcId(int procId) {
		this.procId = procId;
	}

	public int getQuNum() {
		return quNum;
	}

	public void setQuNum(int quNum) {
		this.quNum = quNum;
	}

	public int getDisqNum() {
		return disqNum;
	}

	public void setDisqNum(int disqNum) {
		this.disqNum = disqNum;
	}

	public int getIsOver() {
		return isOver;
	}

	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}

	public Date getOverTime() {
		return OverTime;
	}

	public void setOverTime(Date overTime) {
		OverTime = overTime;
	}

	public int getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
	}

	// 得到hashCode
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((OverTime == null) ? 0 : OverTime.hashCode());
		result = prime * result + disqNum;
		result = prime * result + id;
		result = prime * result + isEnd;
		result = prime * result + isOver;
		result = prime * result + procId;
		result = prime * result + quNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkTab other = (WorkTab) obj;
		if (OverTime == null) {
			if (other.OverTime != null)
				return false;
		} else if (!OverTime.equals(other.OverTime))
			return false;
		if (disqNum != other.disqNum)
			return false;
		if (id != other.id)
			return false;
		if (isEnd != other.isEnd)
			return false;
		if (isOver != other.isOver)
			return false;
		if (procId != other.procId)
			return false;
		if (quNum != other.quNum)
			return false;
		return true;
	}

	// 构造函数
	public WorkTab(int id, int procId, int quNum, int disqNum, int isOver,
			Date overTime, int isEnd) {
		super();
		this.id = id;
		this.procId = procId;
		this.quNum = quNum;
		this.disqNum = disqNum;
		this.isOver = isOver;
		OverTime = overTime;
		this.isEnd = isEnd;
	}

	public WorkTab() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "WorkTab [id=" + id + ", procId=" + procId + ", quNum=" + quNum
				+ ", disqNum=" + disqNum + ", isOver=" + isOver + ", OverTime="
				+ OverTime + ", isEnd=" + isEnd + "]";
	}

}
