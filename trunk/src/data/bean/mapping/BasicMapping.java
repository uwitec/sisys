package data.bean.mapping;

import java.sql.ResultSet;

import data.bean.User;

public abstract class BasicMapping<E> {

	public abstract E mapping(ResultSet rs);
}
