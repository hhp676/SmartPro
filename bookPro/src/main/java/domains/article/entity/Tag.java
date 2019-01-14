package domains.article.entity;

import com.google.common.base.MoreObjects;

/**
 * Created by hhp on 2018/3/23.
 * 文章标签，对应数据库t_tag表
 */
public final class Tag {
    private Long id;//标签主键

    private String name;//标签名称

    private Long articleId;//文章ID。设计为外键，主表为t_article表，此字段表明当前标签对象是哪篇文章的标签

    public Tag() {
    }

    public Tag(String name, Long articleId) {
        this.name = name;
        this.articleId = articleId;
    }

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

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("articleId", articleId)
                .toString();
    }
}
