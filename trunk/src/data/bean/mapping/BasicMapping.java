package data.bean.mapping;

import java.sql.ResultSet;

public abstract class BasicMapping<E> {

	public abstract E mapping(ResultSet rs);
}
