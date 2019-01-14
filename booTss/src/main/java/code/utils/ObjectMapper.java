package code.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ObjectMapper<T> {

    T map(ResultSet rs) throws SQLException; 
}
