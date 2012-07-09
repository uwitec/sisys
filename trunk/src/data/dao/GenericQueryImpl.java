package data.dao;

/**
 * 查询类
 * @author huangxin
 *
 * @param <PK>
 * @param <E>
 */
public class GenericQueryImpl<PK, E> {

	protected Class<? extends E> entityClass;
	
	//构造函数
	public GenericQueryImpl(Class<? extends E> entityClass) {
		
		this.entityClass = entityClass;
	}
	
	public GenericQueryImpl(String entityClassName) {
		try {
			this.entityClass = (Class<? extends E>) Class.forName(entityClassName);
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
