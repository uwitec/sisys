package data.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;


import data.bean.DisqKind;

import data.bean.Staff;
import data.bean.WorkForm;

import data.dao.DisqKindDAO;

import data.dao.StaffDAO;
import data.dao.WorkFormDAO;

public class SearchPd3Service {
	List<DisqStaff> disqStaff = new ArrayList<DisqStaff>();
	List<DisqKind> gWasteType = new ArrayList<DisqKind>();
	List<DisqKind> lWasteType = new ArrayList<DisqKind>();

	String sql;

	public Map<String, Object> SearchPd3(String starttime,String endtime) throws SQLException {
		Map<String, Object> map= new HashMap<String, Object>();
		// 查找出不合格的料废种类和工废种类
		DisqKindDAO disqKindDAO = new DisqKindDAO();
		Map<String, Object> equalsmap1 = new HashMap<String, Object>();
		equalsmap1.put("kind", 0);
		equalsmap1.put("isDelete", 0);
		gWasteType = disqKindDAO.findEntity(equalsmap1);
		System.out.println(gWasteType);

		Map<String, Object> equalsmap2 = new HashMap<String, Object>();
		equalsmap2.put("kind", 1);
		equalsmap2.put("isDelete", 0);
		DisqKindDAO disqKindDAO1 = new DisqKindDAO();
		lWasteType = disqKindDAO1.findEntity(equalsmap2);
		System.out.println(lWasteType);

		// 在WorkForm中查找时间符合的工单
		WorkFormDAO workFormDAO = new WorkFormDAO();
		List<WorkForm> workForm = new ArrayList<WorkForm>();
		sql = "select * from workform where time between '" + starttime
				+ "' and '" + endtime + "' and isDelete ='0'";
		workForm = workFormDAO.findEntityByList(sql);
		if(workForm.size() == 0){
			map.put("result", "error");
			map.put("message", "该时间段内无工单信息！");
			return map;
		}
		System.out.println(workForm);
		// 解析不合格品
		for (int i = 0; i < workForm.size(); i++) {
			DisqStaff tmp = new DisqStaff();
			String tempt = workForm.get(i).getDisDetail();
			String[] s = null;
			String[] disId = null;
			String[] disNum = null;
			if(tempt.equals(":")){
				continue;
			}else{		
			s = tempt.split(":");
			disId = s[0].split("-");
			disNum = s[1].split("-");
			for (int j = 0; j < disId.length; j++) {
				tmp.disqTypeId.add(Integer.parseInt(disId[j]));
				tmp.disqTypeNum.add(Integer.parseInt(disNum[j]));
			}

			StaffDAO staffDAO = new StaffDAO();
			List<Staff> staff = new ArrayList<Staff>();
			Map<String, Object> equalsmap5 = new HashMap<String, Object>();
			equalsmap5.put("Id", workForm.get(i).getStaId());
			equalsmap5.put("isDelete", 0);
			staff = staffDAO.findEntity(equalsmap5);

			tmp.staName = staff.get(0).getStaName();
			tmp.staNo = staff.get(0).getStaNo();
			disqStaff.add(tmp);
			}
		
		}
		// 合并工单中同一产品同一工序的数据
		Boolean Flag=false;
		for (int i = 0; i <disqStaff.size(); i++) {
			for (int j = i + 1; j < disqStaff.size(); j++) {
				if (disqStaff.get(j).staNo.equals(disqStaff.get(i).staNo)) {
					for(int k = 0; k <disqStaff.get(j).disqTypeId.size(); k++) {
						Flag = false;
						for(int l = 0; l <disqStaff.get(i).disqTypeId.size(); l++) {
							if(disqStaff.get(j).disqTypeId.get(k).equals(disqStaff.get(i).disqTypeId.get(l))){
								disqStaff.get(i).disqTypeNum.set(l, disqStaff.get(i).disqTypeNum.get(l)+disqStaff.get(j).disqTypeNum.get(k));
								Flag=true;
							}
						}
						if(Flag==false){
							disqStaff.get(i).disqTypeId.add(disqStaff.get(j).disqTypeId.get(k));
							disqStaff.get(i).disqTypeNum.add(disqStaff.get(j).disqTypeNum.get(k));
						}						
					}
				disqStaff.remove(j);
				j--;			
				}		
			}
		}
		List<Map<String,Object>> pd3List = new ArrayList<Map<String,Object>>();
		for (int m = 0; m<disqStaff.size(); m++) {
			int t=0;
			DisqStaff ds = disqStaff.get(m);
			Map<String,Object> pd3Map = new HashMap<String,Object>();
			Map<Integer,Object> dMap = new HashMap<Integer,Object>();
			
			System.out.println(ds.staName);
			for (int i = 0; i <ds.disqTypeNum.size(); i++) {
			t=t+ds.disqTypeNum.get(i);
			}
			ds.disqNum=t;
			
			pd3Map.put("staName", ds.staName);
			pd3Map.put("staNo", ds.staNo);
			pd3Map.put("disqNum", ds.disqNum);
			for(int i = 0;i < ds.disqTypeId.size();i++){
				dMap.put(ds.disqTypeId.get(i), ds.disqTypeNum.get(i));
			}
			pd3Map.put("dMap", dMap);
			
			pd3List.add(pd3Map);
		}
		
		map.put("result", "success");
		map.put("gWasteType", gWasteType);
		map.put("lWasteType", lWasteType);
		map.put("pd3List", pd3List);
		
		return map;
	}
	
	public static void main(String args[]) throws SQLException, ParseException{
		SearchPd3Service s=new SearchPd3Service();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchPd3("2012-07-08","2012-08-08");	
		System.out.println(map);
	}
	class DisqStaff {
		String staName;
		String staNo;
		int disqNum;
		List<Integer> disqTypeId = new ArrayList<Integer>();
		List<Integer> disqTypeNum = new ArrayList<Integer>();
	}
}