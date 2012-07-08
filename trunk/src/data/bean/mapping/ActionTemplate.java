package data.bean.mapping;

import java.sql.ResultSet;

public interface ActionTemplate<T> {
	public T getClass(ResultSet resultSet);
}
