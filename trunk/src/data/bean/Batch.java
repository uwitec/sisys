package data.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 批次
 */
public class Batch {

	private int id;
	private String batchNo;
	private int flowId; // 流程Id号
	private int proId;
	private int workTabId; // 工作表Id号，即某个流程下工序完成情况记录链表（WorkForm）的入口
	private int status; // 0代表正在生产，1代表已完成，2代表超期未完成，3代表已处理,4代表超期完成
	private Date startTime;
	private Date endTime;
	private int disqNum;
	private int completeNum;
	private double disqPercent;
	private int totalNum; // 目标生产数量
	private String note;//备注，对超期批次的修改
	private int isDelete;
	private Date deleteTime;

	// get和set方法
	public int getCompleteNum() {
		return completeNum;
	}

	public Batch(int id, String batchNo, int flowId, int proId, int workTabId,
			int status, Date startTime, Date endTime, int disqNum,
			int completeNum, double disqPercent, int totalNum, String note,
			int isDelete, Date deleteTime) {
		super();
		this.id = id;
		this.batchNo = batchNo;
		this.flowId = flowId;
		this.proId = proId;
		this.workTabId = workTabId;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.disqNum = disqNum;
		this.completeNum = completeNum;
		this.disqPercent = disqPercent;
		this.totalNum = totalNum;
		this.note = note;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}

	public void setCompleteNum(int completeNum) {
		this.completeNum = completeNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public int getFlowId() {
		return flowId;
	}

	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getWorkTabId() {
		return workTabId;
	}

	public void setWorkTabId(int workTabId) {
		this.workTabId = workTabId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getDisqNum() {
		return disqNum;
	}

	public void setDisqNum(int disqNum) {
		this.disqNum = disqNum;
	}

	public double getDisqPercent() {
		return disqPercent;
	}

	public void setDisqPercent(double disqPercent) {
		this.disqPercent = disqPercent;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchNo == null) ? 0 : batchNo.hashCode());
		result = prime * result + completeNum;
		result = prime * result
				+ ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result + disqNum;
		long temp;
		temp = Double.doubleToLongBits(disqPercent);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + flowId;
		result = prime * result + id;
		result = prime * result + isDelete;
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + proId;
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + status;
		result = prime * result + totalNum;
		result = prime * result + workTabId;
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
		Batch other = (Batch) obj;
		if (batchNo == null) {
			if (other.batchNo != null)
				return false;
		} else if (!batchNo.equals(other.batchNo))
			return false;
		if (completeNum != other.completeNum)
			return false;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (disqNum != other.disqNum)
			return false;
		if (Double.doubleToLongBits(disqPercent) != Double
				.doubleToLongBits(other.disqPercent))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (flowId != other.flowId)
			return false;
		if (id != other.id)
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (proId != other.proId)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status != other.status)
			return false;
		if (totalNum != other.totalNum)
			return false;
		if (workTabId != other.workTabId)
			return false;
		return true;
	}

	// 构造函数
	public Batch(int id, String batchNo, int flowId, int proId, int workTabId,
			int status, Date startTime, Date endTime, int disqNum,
			double disqPercent, int totalNum, String note, int isDelete, Date deleteTime) {
		super();
		this.id = id;
		this.batchNo = batchNo;
		this.flowId = flowId;
		this.proId = proId;
		this.workTabId = workTabId;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.disqNum = disqNum;
		this.disqPercent = disqPercent;
		this.totalNum = totalNum;
		this.note = note;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}

	public Batch() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Batch [id=" + id + ", batchNo=" + batchNo + ", flowId="
				+ flowId + ", proId=" + proId + ", workTabId=" + workTabId
				+ ", status=" + status + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", disqNum=" + disqNum
				+ ", completeNum=" + completeNum + ", disqPercent="
				+ disqPercent + ", totalNum=" + totalNum + ", note=" + note
				+ ", isDelete=" + isDelete + ", deleteTime=" + deleteTime + "]";
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

}
