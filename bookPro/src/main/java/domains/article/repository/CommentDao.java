package domains.article.repository;

import domains.article.entity.Article;
import domains.article.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * Created by hhp on 2018/3/7.
 */
public interface CommentDao {
    /**
     * 增加评论
     *
     * @param comment 评论
     * @return 0代表评论失败，1代表评论成功
     */
    int insertComment(Comment comment);

    /**
     * 删除评论
     *
     * @param comment 评论
     * @return 0代表删除评论失败，1代表删除评论成功
     */
    int deleteComment(Comment comment);

    /**
     * 根据文章的ID删除所有评论
     * 在删除文章的时候，进行关联删除
     *
     * @param articleId 文章ID
     * @return  0代表删除评论失败，1代表删除评论成功
     */
    int deleteCommentByArticleId(Long articleId);

    /**
     * 根据指定的评论id号获取评论，因为评论的唯一性只能有id来维护
     *
     * @param id 评论的主键id号
     * @return   指定id的评论
     */
    Comment getCommentById(Long id);

    /**
     * 获取所有评论，要么为管理员获取所有评论，要么获取作者的所有评论
     *
     * @param map map中要么什么都没有，如果key为指定的作者，返回这个作者的所有评论
     * @return    所有评论
     */
    List<Comment> getAllComment(Map<String,Object> map);

    /**
     * 根据文章获取到这篇文章的所有评论
     *
     * @param article 文章对象
     * @return        这篇文章的所有评论
     */
    List<Comment> getCommentsByArticle(Article article);

}
