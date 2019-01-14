package code.service;


import code.dao.BookDao;

/**
 * Created by HHP on 2019/1/6.
 */
public class BookService {

    private static BookDao bookDao;

    public static void insert(){
        BookDao.insert();
        System.out.println("<<<<<");
    }

}
