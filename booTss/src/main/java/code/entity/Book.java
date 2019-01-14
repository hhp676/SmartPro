package code.entity;

import java.io.Serializable;

/**
 * Created by HHP on 2019/1/6.
 */
public class Book implements Serializable {

    private String name;

    private String bookId;

    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
