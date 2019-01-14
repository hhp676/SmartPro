package code.utils;


import java.sql.PreparedStatement;
import java.util.List;

public interface JdbcTemplate<T>  {

    /**
     * 执行单一sql
     * @param sql
     */
    void execute(String sql);
    
    /**
     * 获取某条数据
     * 
     * @param sql
     * @param params
     * @param mapper
     * @return
     */
    T find(String sql, Object[] params, ObjectMapper<T> mapper);

    /**
     * 获取list集合
     * 
     * @param sql
     * @param params
     * @param mapper
     * @return
     */
    List<T> query(String sql, Object[] params, ObjectMapper<T> mapper);

    /**
     * 插入数据
     * 
     * @param sql
     * @param params
     */
    void insert(String sql, Object[] params);

    /**
     * 批量插入数据
     * 
     * @param sql
     */
    void insert(String sql, List<Object[]> paramsList);

    /**
     * 更新数据
     * 
     * @param sql
     * @param params
     */
    void update(String sql, Object[] params);

    /**
     * 批量更新数据
     * 
     * @param sql
     * @param paramsList
     */
    void update(String sql, List<Object[]> paramsList);

    /**
     * 逻辑删除
     * @param sql
     */
    void delete(String sql);

    /**
     * 逻辑删除
     * @param sql
     */
    void delete(String sql, Object[] params);
    
    /**
     * 逻辑删除
     * @param sql
     * @param paramsList
     */
    void delete(String sql, List<Object[]> paramsList);

    /**
     * 设置参数
     * @param ps
     * @param params
     */
    void setParams(PreparedStatement ps, Object[] params);
}
