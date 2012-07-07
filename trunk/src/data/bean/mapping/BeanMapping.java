package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BeanMapping {

	public Object mapping(ResultSet rs) throws SQLException;
}
