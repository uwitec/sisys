package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.DisqKind;

public class DisqKindMapping extends BasicMapping<DisqKind> {

	@Override
	public DisqKind mapping(ResultSet rs) {

		DisqKind disqKind = new DisqKind();
		try {
			System.out.println(rs);
			System.out.println(rs.getDate("deleteTime"));
			disqKind.setDeleteTime(rs.getDate("deleteTime"));
			disqKind.setDisDesc(rs.getString("disDesc"));
			disqKind.setId(rs.getInt("Id"));
			disqKind.setIsDelete(rs.getInt("isDelete"));
			disqKind.setKind(rs.getInt("kind"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return disqKind;
	}

}
