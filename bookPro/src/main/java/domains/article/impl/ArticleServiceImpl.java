package domains.article.impl;


import com.google.common.collect.Lists;
import domains.article.ArticleService;
import domains.article.entity.Article;
import domains.article.entity.Tag;
import domains.article.repository.ArticleDao;
import domains.article.repository.CommentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by hhp on 2018/3/7.
 */
@Service("ArticleService")
@Transactional
public final class ArticleServiceImpl implements ArticleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private CommentDao commentDao;

    @Override
    public int insertArticle(Article article) {
        LOGGER.info("Service:insertArticle,articleTitle={}", article.getTitle());
        return articleDao.insertArticle(article);
    }

    @Override
    public int insertArticle(Article article, HttpServletRequest request) {
        LOGGER.info("Service:insertArticle,articleTitle={},article.getTitle()");
        //插入文章
        try {
            int i = insertArticle(article);
            if (i == 0) {
                LOGGER.info("插入文章失败");
                return i;
            }
            List<Tag> tags = handleTag(request, article.getId());
            int j = insertArticleTag(tags);
            if (j == 0) {
                LOGGER.info("插入标签失败");
            }
            return 1;
        }catch (Exception e){
            LOGGER.error("录入失败" +e);
        }
        return 0;
    }

    @Override
    public int deleteArticle(Article article) {
        LOGGER.info("Service:deleteArticle,articleTitle={}", article.getTitle());
        Long articleId = article.getId();
        //删除文章标签
        deleteTagByArticleId(articleId);
        //删除文章评论
        commentDao.deleteCommentByArticleId(articleId);
        return articleDao.deleteArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        LOGGER.info("Service:updateArticle,articleTitle={}", article.getTitle());
        return articleDao.updateArticle(article);
    }

    @Override
    public Article getArticleById(Long id) {
        LOGGER.info("Service:getArticleById,id={}", id);
        return articleDao.getArticleById(id);
    }

    @Override
    public List<Article> getAllArticle(Map<String, Object> map) {
        LOGGER.info("Service:getAllArticle,map={}", map);
        return articleDao.getAllArticle(map);
    }

    @Override
    public List<Article> getArticleByClick(Integer limit) {
        LOGGER.info("Service:getArticleByClick,limit={}", limit);
        return articleDao.getArticleByClick(limit);
    }

    @Override
    public List<Article> getArticleByCondition(Map<String, Object> map) {
        LOGGER.info("Service:getArticleByCondition,map={}", map);
        return articleDao.getArticleByCondition(map);
    }

    @Override
    public Article getPreviousArticle(Long currentArticleId) {
        LOGGER.info("Service:getPreviousArticle,currentArticleId={}", currentArticleId);
        return articleDao.getPreviousArticle(currentArticleId);
    }

    @Override
    public Article getNextArticle(Long currentArticleId) {
        LOGGER.info("Service:getNextArticle,currentArticleId={}", currentArticleId);
        return articleDao.getNextArticle(currentArticleId);
    }

    @Override
    public int insertArticleTag(List<Tag> tags) {
        LOGGER.info("Service:insertArticleTag,tags size={}", tags.size());
        return articleDao.insertArticleTag(tags);
    }

    @Override
    public List<Tag> getTagsByArticleId(Long articleId) {
        LOGGER.info("Service:getTagsByArticleId,articleId={}", articleId);
        return articleDao.getTagsByArticleId(articleId);
    }

    @Override
    public List<Tag> getSimilarTag(Map<String, Object> tagNames) {
        LOGGER.info("Service:getSimilarTag,tag size={}", tagNames.size());
        return articleDao.getSimilarTag(tagNames);
    }

    @Override
    public List<Long> getRecommandArticleId() {
        LOGGER.info("Service:getRecommandArticleId");
        return articleDao.getRecommandArticleId();
    }

    @Override
    public List<Article> getRecommandArtice(List<Long> articleIds) {
        LOGGER.info("Service:getRecommandArtice,articleIds size = {}", articleIds.size());
        return articleDao.getRecommandArtice(articleIds);
    }

    @Override
    public int deleteTagByArticleId(Long articleId) {
        LOGGER.info("Service:deleteTagByArticleId, articleId = {}", articleId);
        return articleDao.deleteTagByArticleId(articleId);
    }

    /**
     * 为新文章插入标签
     * 感觉这段代码写的真的烂，但是我又不知道怎么接收多个参数的集合，只能手动创建对象了
     *
     * @param request
     * @param currentArticleId 当前文章的ID
     * @return 处理的多个标签的List集合
     */
    private List<Tag> handleTag(HttpServletRequest request, Long currentArticleId) {
        //下面的代码是我写过的最烂的代码
        List<Tag> tags = Lists.newArrayList();
        for (int i = 1; i <= 4; i++) {
            //感觉要乱码，但是好像被SpringMVC的配置文件给怼了，不然必然乱码啊
            String name = request.getParameter("tag" + i);
            tags.add(new Tag(name, currentArticleId));
        }
        return tags;
    }
}