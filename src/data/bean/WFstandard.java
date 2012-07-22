package data.bean;

public class WFstandard {
	private int wfId;
	private String BatchNo;
	private String proName;
	private String proNo;
	private String procName;
	private String procNo;
	private String disqDetail;
	private int quaNum;
	private String staName;
	private String staNo;
	private String status;
	private int disqNum;
	private String deletetime;

	public WFstandard(int wfId, String batchNo, String proName, String proNo,
			String procName, String procNo, String disqDetail, int quaNum,
			String staName, String staNo, String status, int disqNum,
			String deletetime) {
		super();
		this.wfId = wfId;
		BatchNo = batchNo;
		this.proName = proName;
		this.proNo = proNo;
		this.procName = procName;
		this.procNo = procNo;
		this.disqDetail = disqDetail;
		this.quaNum = quaNum;
		this.staName = staName;
		this.staNo = staNo;
		this.status = status;
		this.disqNum = disqNum;
		this.deletetime = deletetime;
	}
	
	//get,set方法
	public String getDeletetime() {
		return deletetime;
	}
	public void setDeletetime(String deletetime) {
		this.deletetime = deletetime;
	}
	public int getDisqNum() {
		return disqNum;
	}
	public void setDisqNum(int disqNum) {
		this.disqNum = disqNum;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getWfId() {
		return wfId;
	}
	public void setWfId(int wfId) {
		this.wfId = wfId;
	}
	public String getBatchNo() {
		return BatchNo;
	}
	public void setBatchNo(String batchNo) {
		BatchNo = batchNo;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
	}
	public String getProcNo() {
		return procNo;
	}
	public void setProcNo(String procNo) {
		this.procNo = procNo;
	}
	public String getDisqDetail() {
		return disqDetail;
	}
	public void setDisqDetail(String disqDetail) {
		this.disqDetail = disqDetail;
	}
	public int getQuaNum() {
		return quaNum;
	}
	public void setQuaNum(int quaNum) {
		this.quaNum = quaNum;
	}
	public String getStaName() {
		return staName;
	}
	public void setStaName(String staName) {
		this.staName = staName;
	}
	public String getStaNo() {
		return staNo;
	}
	public void setStaNo(String staNo) {
		this.staNo = staNo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BatchNo == null) ? 0 : BatchNo.hashCode());
		result = prime * result
				+ ((deletetime == null) ? 0 : deletetime.hashCode());
		result = prime * result
				+ ((disqDetail == null) ? 0 : disqDetail.hashCode());
		result = prime * result + disqNum;
		result = prime * result + ((proName == null) ? 0 : proName.hashCode());
		result = prime * result + ((proNo == null) ? 0 : proNo.hashCode());
		result = prime * result
				+ ((procName == null) ? 0 : procName.hashCode());
		result = prime * result + ((procNo == null) ? 0 : procNo.hashCode());
		result = prime * result + quaNum;
		result = prime * result + ((staName == null) ? 0 : staName.hashCode());
		result = prime * result + ((staNo == null) ? 0 : staNo.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + wfId;
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
		WFstandard other = (WFstandard) obj;
		if (BatchNo == null) {
			if (other.BatchNo != null)
				return false;
		} else if (!BatchNo.equals(other.BatchNo))
			return false;
		if (deletetime == null) {
			if (other.deletetime != null)
				return false;
		} else if (!deletetime.equals(other.deletetime))
			return false;
		if (disqDetail == null) {
			if (other.disqDetail != null)
				return false;
		} else if (!disqDetail.equals(other.disqDetail))
			return false;
		if (disqNum != other.disqNum)
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
		if (procName == null) {
			if (other.procName != null)
				return false;
		} else if (!procName.equals(other.procName))
			return false;
		if (procNo == null) {
			if (other.procNo != null)
				return false;
		} else if (!procNo.equals(other.procNo))
			return false;
		if (quaNum != other.quaNum)
			return false;
		if (staName == null) {
			if (other.staName != null)
				return false;
		} else if (!staName.equals(other.staName))
			return false;
		if (staNo == null) {
			if (other.staNo != null)
				return false;
		} else if (!staNo.equals(other.staNo))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (wfId != other.wfId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "WFstandard [wfId=" + wfId + ", BatchNo=" + BatchNo
				+ ", proName=" + proName + ", proNo=" + proNo + ", procName="
				+ procName + ", procNo=" + procNo + ", disqDetail="
				+ disqDetail + ", quaNum=" + quaNum + ", staName=" + staName
				+ ", staNo=" + staNo + ", status=" + status + ", disqNum="
				+ disqNum + ", deletetime=" + deletetime + "]";
	}

	public WFstandard() {
		super();
		// TODO Auto-generated constructor stub
	}

}
