package domains.user.entity;

import com.google.common.base.MoreObjects;

/**
 * Created by hhp on 2017/12/12.
 */
public class User {
    private int id;//用户id
    private String name;//用户名
    private int age;//年龄
    private String password;//密码

    public int getId() {
        return id;
    }

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, int age, String password) {
        this.age = age;
        this.name = name;
        this.password = password;
    }

    public User(int id, String name, int age, String password) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("age", age)
                .add("password", password)
                .toString();
    }
}