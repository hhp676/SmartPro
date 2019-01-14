package domains.article.entity;

import com.google.common.base.MoreObjects;

/**
 * Created by hhp on 2018/3/7.
 * 文章分类，技术类的还是教育类或者是文学类的
 */

public final class Category {
    private Long id;//主键id

    private String typeName;//类别名称

    private String description;//描述

    public Category() {
    }

    public Category(String typeName, String description) {
        this.typeName = typeName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("typeName", typeName)
                .add("description", description)
                .toString();
    }


}
