package domains.article.repository.mybatis;

import domains.article.entity.Article;
import domains.article.entity.Comment;
import domains.article.repository.CommentDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by hhp on 2018/3/7.
 */
@Repository
public final class CommentDaoImpl extends SqlSessionDaoSupport implements CommentDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentDaoImpl.class);

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insertComment(Comment comment) {
        LOGGER.info("Repository:insertComment,comment={}", comment);
        return this.getSqlSession().insert("insertComment", comment);
    }

    @Override
    public int deleteComment(Comment comment) {
        LOGGER.info("Repository:deleteComment,comment={}", comment);
        return this.getSqlSession().delete("deleteComment", comment);
    }

    @Override
    public int deleteCommentByArticleId(Long articleId) {
        LOGGER.info("Repository:deleteCommentByArticleId,articleId={}", articleId);
        return this.getSqlSession().delete("deleteCommentByArticleId", articleId);
    }

    @Override
    public Comment getCommentById(Long id) {
        LOGGER.info("Repository:getCommentById,id={}", id);
        Comment comment = this.getSqlSession().selectOne("getCommentById", id);
        return comment == null ? new Comment() : comment;
    }

    @Override
    public List getAllComment(Map<String, Object> map) {
        LOGGER.info("Repository:getAllComment,map={}", map);
        return this.getSqlSession().selectList("getAllComment", map);
    }

    @Override
    public List<Comment> getCommentsByArticle(Article article) {
        LOGGER.info("Repository:getCommentsByArticle,articleTitle={}", article.getTitle());
        return this.getSqlSession().selectList("getCommentsByArticle", article);
    }
}
