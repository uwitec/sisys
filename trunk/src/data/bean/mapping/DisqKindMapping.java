package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.DisqKind;

public class DisqKindMapping implements BeanMapping {

	public Object mapping(ResultSet rs) throws SQLException {

		DisqKind disqKind = new DisqKind();
		if(rs.next()) {
			disqKind.setDeleteTime(rs.getDate("deleteTime"));
			disqKind.setDisDesc(rs.getString("disDesc"));
			disqKind.setId(rs.getInt("Id"));
			disqKind.setIsDelete(rs.getInt("isDelete"));
			disqKind.setKind(rs.getInt("kind"));
		}
		return disqKind;
	}

}
