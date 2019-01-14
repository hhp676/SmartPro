package code.dao;


import code.entity.Book;
import code.utils.JavaJdbcTemplate;
import code.utils.JdbcTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HHP on 2019/1/6.
 */
public class BookDao implements Serializable {
    private static JdbcTemplate<Book> template = new JavaJdbcTemplate<>();

    public static void insert(){
        String insertSql = "INSERT into book(name, number) VALUE(?, ?)";
        List<Object[]> paramsList = new ArrayList<>();
        Object[] params = new Object[2];
        params[0] = "22";
        params[1] = "33";
        paramsList.add(params);
        template.insert(insertSql, paramsList);
    }
}
