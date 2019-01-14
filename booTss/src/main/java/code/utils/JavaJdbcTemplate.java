package code.utils;

import code.exception.EteException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JavaJdbcTemplate<T> implements JdbcTemplate<T>, Serializable {

    private static final long serialVersionUID = -5044965331293651817L;

    public void execute(String sql) {
        executeSql(sql, null);
    }
    
    /**
     * 获取某条数据
     * @param sql
     * @param params
     * @param mapper
     * @return
     */
    public T find(String sql, Object[] params, ObjectMapper<T> mapper) {
        T t = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);
            setParams(ps, params);
            rs = ps.executeQuery();
            if(rs.next()) {
                t = mapper.map(rs);
            }
        } catch (Exception e) {
           throw new EteException(e);
        } finally {
            ConnectionFactory.close(rs, ps, conn);
        }
        return t;
    }

    /**
     * 获取list集合
     * @param sql
     * @param params
     * @param mapper
     * @return
     */
    public List<T> query(String sql, Object[] params, ObjectMapper<T> mapper) {
        List<T> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);
            setParams(ps, params);
            rs = ps.executeQuery();
            while(rs.next()) {
                list.add(mapper.map(rs));
            }
        } catch (Exception e) {
           throw new EteException(e);
        } finally {
            ConnectionFactory.close(rs, ps, conn);
        }
        return list;
    }

    /**
     * 插入数据
     * @param sql
     * @param params
     */
    public void insert(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            setParams(ps, params);
            int count = ps.executeUpdate();
            if(count > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            throw new EteException(e);
        }finally {
            ConnectionFactory.close(null, ps, conn);
        }
    }

    /**
     * 批量插入数据
     * @param sql
     * @param paramsList
     */
    public void insert(String sql, List<Object[]> paramsList) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            if(paramsList != null && paramsList.size() > 0) {
                conn = ConnectionFactory.getConnection();
                ps = conn.prepareStatement(sql);
                int count = 0;
                for (Object[] params : paramsList) {
                    setParams(ps, params);
                    ps.addBatch();
                    count++;
                    if(count % 100 == 0) {
                        ps.executeBatch();
                        ps.clearBatch();
                    }
                }
                ps.executeBatch();
                ps.clearBatch();
            }
        } catch (Exception e) {
            throw new EteException(e);
        } finally {
            ConnectionFactory.close(null, ps, conn);
        }
    }

    /**
     * 更新数据
     * @param sql
     * @param params
     */
    public void update(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            setParams(ps, params);
            int count = ps.executeUpdate();
            if(count > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            throw new EteException(e);
        }finally {
            ConnectionFactory.close(null, ps, conn);
        }
    }

    /**
     * 批量更新数据
     * @param sql
     * @param paramsList
     */
    public void update(String sql, List<Object[]> paramsList) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            if(paramsList != null && paramsList.size() > 0) {
                conn = ConnectionFactory.getConnection();
                ps = conn.prepareStatement(sql);
                int count = 0;
                for (Object[] params : paramsList) {
                    setParams(ps, params);
                    ps.addBatch();
                    count++;
                    if(count % 100 == 0) {
                        ps.executeBatch();
                        ps.clearBatch();
                    }
                }
                ps.executeBatch();
                ps.clearBatch();
            }
        } catch (Exception e) {
            throw new EteException(e);
        } finally {
            ConnectionFactory.close(null, ps, conn);
        }
    }

    public void delete(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            int i = ps.executeUpdate();
            if(i > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            throw new EteException(e);
        } finally {
            ConnectionFactory.close(null, ps, conn);
        }
    }

    public void delete(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            setParams(ps, params);
            int i = ps.executeUpdate();
            if(i > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            throw new EteException(e);
        } finally {
            ConnectionFactory.close(null, ps, conn);
        }
    }

    public void delete(String sql, List<Object[]> paramsList) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            if(paramsList != null && paramsList.size() > 0) {
                conn = ConnectionFactory.getConnection();
                ps = conn.prepareStatement(sql);
                int count = 0;
                for (Object[] params : paramsList) {
                    setParams(ps, params);
                    ps.addBatch();
                    count++;
                    if(count % 100 == 0) {
                        ps.executeBatch();
                        ps.clearBatch();
                    }
                }
                ps.executeBatch();
                ps.clearBatch();
            }
        } catch (Exception e) {
            throw new EteException(e);
        } finally {
            ConnectionFactory.close(null, ps, conn);
        }
    }
    
    public void setParams(PreparedStatement ps, Object[] params) {
        try {
            if(params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
        } catch (Exception e) {
            throw new EteException(e);
        }
    }

    private void executeSql(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            if(params != null && params.length != 0) {
                setParams(ps, params);
            }
            int i = ps.executeUpdate();
            if(i > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            throw new EteException(e);
        } finally {
            ConnectionFactory.close(null, ps, conn);
        }
    }
}
