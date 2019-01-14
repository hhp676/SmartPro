package domains.article.entity;

import com.google.common.base.MoreObjects;

/**
 * Created by hhp on 2018/3/21.
 * 本来用来传递数据
 * 页面上需要的所有数据，均可使用本类传输
 */
public final class Model {
    private Long articleId;//文章的ID

    private String articleTitle;//文章标题

    private String commentContent;//评论内容

    public Model() {
    }

    /**
     * 用于个人中心的，文章评论关联文章标题
     *
     * @param commentContent 评论内容
     * @param articleTitle   文章标题
     */
    public Model(String commentContent, String articleTitle) {
        this.commentContent = commentContent;
        this.articleTitle = articleTitle;
    }

    public Model(String commentContent, String articleTitle, Long articleId) {
        this.commentContent = commentContent;
        this.articleTitle = articleTitle;
        this.articleId = articleId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("articleId", articleId)
                .add("articleTitle", articleTitle)
                .add("commentContent", commentContent)
                .toString();
    }
}
