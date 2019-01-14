package domains.article.impl;

import domains.article.CommentService;
import domains.article.entity.Article;
import domains.article.entity.Comment;
import domains.article.repository.CommentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by hhp on 2018/3/7.
 */
@Service("CommentService")
@Transactional
public final class CommentServcieImpl implements CommentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServcieImpl.class);
    @Autowired
    private CommentDao commentDao;

    @Override
    public int insertComment(Comment comment) {
        LOGGER.info("Service:insertComment,comment={}", comment);
        return commentDao.insertComment(comment);
    }

    @Override
    public int deleteComment(Comment comment) {
        LOGGER.info("Service:deleteComment,comment={}", comment);
        Comment comment1 = getCommentById(comment.getId());
        if (comment1.getId() == null) {
            return 0;
        }
        return commentDao.deleteComment(comment);
    }

    @Override
    public Comment getCommentById(Long id) {
        LOGGER.info("Service:getCommentById,id={}", id);
        if(id==null){
            return new Comment();
        }
        return commentDao.getCommentById(id);
    }

    @Override
    public List<Comment> getAllComment(Map<String, Object> map) {
        LOGGER.info("Service:getAllComment,map={}", map);
        return commentDao.getAllComment(map);
    }

    @Override
    public List<Comment> getCommentsByArticle(Article article) {
        LOGGER.info("Service:getCommentsByArticle,articleTitle={}", article.getTitle());
        return commentDao.getCommentsByArticle(article);
    }
}
