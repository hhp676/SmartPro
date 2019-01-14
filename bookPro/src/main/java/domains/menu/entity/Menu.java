package domains.menu.entity;

import java.util.List;

/**
 * Created by hhp on 2018/2/6.
 */
public final class Menu {
    private Long id;//主键

    private String name;//菜单名字

    private String uri;//路径

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("Menu");
        sb.append("{uri='").append(uri).append('\'');
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append('}');

        return sb.toString();
    }

}
