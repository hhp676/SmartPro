package domains.article.entity;

import com.google.common.base.MoreObjects;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by hhp on 2018/3/7.
 * 文章评论，对应的是数据库中t_comment表
 */
public final class Comment {
    private Long id;//主键id

    private String content;//文章内容

    private String name;//评论者，对应Account里面的nickname

    private Date date;//评论日期

    private Long articleId;//评论的文章的主键id号,设计为外键。主表为t_acticle表，表明是那篇文章的评论

    public Comment() {
    }

    public Comment(String content, String name, Date date, Long articleId) {
        this.content = content;
        this.name = name;
        this.date = date;
        this.articleId = articleId;
    }

    /**
     * 修改了原始的JavaBean，
     * 在页面上请求属性的时候，
     * 调用的是这个get方法返回通用的时间字符串
     * jay 18-10-21 评价:
     * DateFormat是线程不安全的，
     * 所以每次需要获取一个新的格式化对象
     * 用空间换时间
     *
     * @return 格式化后的时间对象
     */
    public String getDate() {
        return DateFormat.getDateTimeInstance().format(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
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
                .add("content", content)
                .add("name", name)
                .add("date", date)
                .add("articleId", articleId)
                .toString();
    }
}
