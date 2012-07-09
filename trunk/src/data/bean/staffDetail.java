package data.bean;

/**
 * 
 * @author huangxin 员工生产详细 将员工对一道工序的完成情况汇总。用于导出员工完成详细表
 */
public class staffDetail {

	private int id;
	private int staffId;
	private String proName;
	private String proNo;
	private String procName;
	private int quaNum;
	private int gWaste; // 工废数量
	private int lWaste; // 料废数量
	private double workHours; // 工时

	// get和set方法
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
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

	public int getQuaNum() {
		return quaNum;
	}

	public void setQuaNum(int quaNum) {
		this.quaNum = quaNum;
	}

	public int getgWaste() {
		return gWaste;
	}

	public void setgWaste(int gWaste) {
		this.gWaste = gWaste;
	}

	public int getfWaste() {
		return lWaste;
	}

	public void setfWaste(int fWaste) {
		this.lWaste = fWaste;
	}

	public double getWorkHours() {
		return workHours;
	}

	public void setWorkHours(double workHours) {
		this.workHours = workHours;
	}

	// 得到hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lWaste;
		result = prime * result + gWaste;
		result = prime * result + id;
		result = prime * result + ((proName == null) ? 0 : proName.hashCode());
		result = prime * result + ((proNo == null) ? 0 : proNo.hashCode());
		result = prime * result
				+ ((procName == null) ? 0 : procName.hashCode());
		result = prime * result + quaNum;
		result = prime * result + staffId;
		long temp;
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
		staffDetail other = (staffDetail) obj;
		if (lWaste != other.lWaste)
			return false;
		if (gWaste != other.gWaste)
			return false;
		if (id != other.id)
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
		if (quaNum != other.quaNum)
			return false;
		if (staffId != other.staffId)
			return false;
		if (Double.doubleToLongBits(workHours) != Double
				.doubleToLongBits(other.workHours))
			return false;
		return true;
	}

	// 构造函数
	public staffDetail(int id, int staffId, String proName, String proNo,
			String procName, int quaNum, int gWaste, int fWaste,
			double workHours) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.proName = proName;
		this.proNo = proNo;
		this.procName = procName;
		this.quaNum = quaNum;
		this.gWaste = gWaste;
		this.lWaste = fWaste;
		this.workHours = workHours;
	}

	public staffDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "StaffDetail [id=" + id + ", staffId=" + staffId + ", proName="
				+ proName + ", proNo=" + proNo + ", procName=" + procName
				+ ", quaNum=" + quaNum + ", gWaste=" + gWaste + ", fWaste="
				+ lWaste + ", workHours=" + workHours + "]";
	}

}
