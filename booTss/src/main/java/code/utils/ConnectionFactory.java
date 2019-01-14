package code.utils;

import java.sql.*;

/**
 * @author markie
 * @description 数据库连接
 * @table 
 * @date 2017年10月18日 下午9:36:24
 */
public class ConnectionFactory {
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    static {
        try {
            DRIVER = ConfigUtil.getTrimString("jdbc.driver");
            URL = ConfigUtil.getTrimString("jdbc.url");
            USERNAME = ConfigUtil.getTrimString("jdbc.username");
            PASSWORD = ConfigUtil.getTrimString("jdbc.password");
            Class.forName(DRIVER);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("JDBC找不到驱动！");
        }
    }

    /**
     * 获取数据库连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接失败！");
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param rs
     * @param state
     * @param conn
     */
    public static void close(ResultSet rs, Statement state, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (state != null) {
                state.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("关闭数据库连接失败！");
        }
    }
}
