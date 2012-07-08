package data.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 工单信息
 */
public class WorkForm {

	private int id;
	private int staId;
	private int procId;
	private int batchId;
	private int proId;
	private int quaNum;
	private String disDetail; // 记录不合格详情，具体格式为(不合格种类ID)-(不合格品数量)；(不合格种类ID):(不合格品数量)...
	private Date time; // 记录工单存入系统的时间
	private int isDelete; // 标识是否删除，0未删除，1删除
	private Date deleteTime; // 若删除，则记录删除时间

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

	public int getProcId() {
		return procId;
	}

	public void setProcId(int procId) {
		this.procId = procId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getQuaNum() {
		return quaNum;
	}

	public void setQuaNum(int quaNum) {
		this.quaNum = quaNum;
	}

	public String getDisDetail() {
		return disDetail;
	}

	public void setDisDetail(String disDetail) {
		this.disDetail = disDetail;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
		result = prime * result + batchId;
		result = prime * result
				+ ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result
				+ ((disDetail == null) ? 0 : disDetail.hashCode());
		result = prime * result + id;
		result = prime * result + isDelete;
		result = prime * result + proId;
		result = prime * result + procId;
		result = prime * result + quaNum;
		result = prime * result + staId;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		WorkForm other = (WorkForm) obj;
		if (batchId != other.batchId)
			return false;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (disDetail == null) {
			if (other.disDetail != null)
				return false;
		} else if (!disDetail.equals(other.disDetail))
			return false;
		if (id != other.id)
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (proId != other.proId)
			return false;
		if (procId != other.procId)
			return false;
		if (quaNum != other.quaNum)
			return false;
		if (staId != other.staId)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	// 构造函数
	public WorkForm(int id, int staId, int procId, int batchId, int proId,
			int quaNum, String disDetail, Date time, int isDelete,
			Date deleteTime) {
		super();
		this.id = id;
		this.staId = staId;
		this.procId = procId;
		this.batchId = batchId;
		this.proId = proId;
		this.quaNum = quaNum;
		this.disDetail = disDetail;
		this.time = time;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}

	public WorkForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "WorkForm [id=" + id + ", staId=" + staId + ", procId=" + procId
				+ ", batchId=" + batchId + ", proId=" + proId + ", quaNum="
				+ quaNum + ", disDetail=" + disDetail + ", time=" + time
				+ ", isDelete=" + isDelete + ", deleteTime=" + deleteTime + "]";
	}

}
