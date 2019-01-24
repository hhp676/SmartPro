package domains.article.repository.mybatis;

import domains.article.entity.Article;
import domains.article.entity.Banner;
import domains.article.entity.Tag;
import domains.article.repository.ArticleDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

/*import domains.article.entity.Article;
import domains.article.entity.Tag;
import domains.article.repository.ArticleDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;*/

/**
 * Created by hhp on 2018/3/7.
 */
@Repository("ArticleDao")
public final class ArticleDaoImpl extends SqlSessionDaoSupport implements ArticleDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleDaoImpl.class);

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insertArticle(Article article) {
        LOGGER.info("Repository:insertArticle,articleTitle={}", article.getTitle());
        return this.getSqlSession().insert("insertArticle", article);
    }

    @Override
    public int deleteArticle(Article article) {
        LOGGER.info("Repository:deleteArticle,articleTitle={}", article.getTitle());
        return this.getSqlSession().delete("deleteArticle", article);
    }

    @Override
    public int updateArticle(Article article) {
        LOGGER.info("Repository:updateArticle,articleTitle={}", article.getTitle());
        return this.getSqlSession().update("updateArticle", article);
    }

    @Override
    public Article getArticleById(Long id) {
        LOGGER.info("Repository:getArticleById,id={}", id);
        Article article = this.getSqlSession().selectOne("getArticleById", id);
        return article == null ? new Article() : article;
    }

    @Override
    public List<Article> getAllArticle(Map<String, Object> map) {
        LOGGER.info("Repository:getAllArticle,map={}", map);
        return this.getSqlSession().selectList("getAllArticle", map);
    }

    @Override
    public List<Article> getArticleByClick(Integer limit) {
        LOGGER.info("Repository:getArticleByClick,limit={}", limit);
        return this.getSqlSession().selectList("getArticleByClick", limit);
    }

    @Override
    public List<Article> getArticleByRecom(Integer limit) {
        LOGGER.info("Repository:getArticleByRecom,currentArticleId={}", limit);
        return this.getSqlSession().selectList("getArticleByRecom", limit);
    }

    @Override
    public List<Article> getArticleByDate(Integer limit) {
        LOGGER.info("Repository:getArticleByDate,currentArticleId={}", limit);
        return this.getSqlSession().selectList("getArticleByDate", limit);
    }

    @Override
    public List<Banner> getBannerList(Integer limit) {
        LOGGER.info("Repository:getBannerList,currentArticleId={}", limit);
        return this.getSqlSession().selectList("getBannerList", limit);
    }

    @Override
    public Article getPreviousArticle(Long currentArticleId) {
        LOGGER.info("Repository:getPreviousArticle,currentArticleId={}", currentArticleId);
        return this.getSqlSession().selectOne("getPreviousArticle", currentArticleId);
    }

    @Override
    public Article getNextArticle(Long currentArticleId) {
        LOGGER.info("Repository:getNextArticle,currentArticleId={}", currentArticleId);
        return this.getSqlSession().selectOne("getNextArticle", currentArticleId);
    }

    @Override
    public int insertArticleTag(List<Tag> tags) {
        LOGGER.info("Repository:insertArticleTag,tags size ={}", tags.size());
        return this.getSqlSession().insert("insertArticleTag", tags);
    }

    @Override
    public List<Tag> getTagsByArticleId(Long articleId) {
        LOGGER.info("Repository:getTagsByArticleId,articleId={}", articleId);
        return this.getSqlSession().selectList("getTagsByArticleId", articleId);
    }

    @Override
    public List<Tag> getSimilarTag(Map<String, Object> tagNames) {
        LOGGER.info("Repository:getSimilarTag,tagNames size={}", tagNames.size());
        return this.getSqlSession().selectList("getSimilarTag", tagNames);
    }

    @Override
    public List<Long> getRecommandArticleId() {
        LOGGER.info("Repository:getRecommandArticle");
        return this.getSqlSession().selectList("getRecommandArticleId");
    }

    @Override
    public List<Article> getRecommandArtice(List<Long> articleIds) {
        LOGGER.info("Repository:getRecommandArtice,articleIds size = {}", articleIds.size());
        return this.getSqlSession().selectList("getRecommandArtice", articleIds);
    }

    @Override
    public List<Article> getArticleByCondition(Map<String, Object> map) {
        LOGGER.info("Repository:getArticleByCondition,map size = {}", map.size());
        return this.getSqlSession().selectList("getArticleByCondition", map);
    }

    @Override
    public int deleteTagByArticleId(Long articleId) {
        LOGGER.info("Repository:deleteTagByArticleId,articleId= {}", articleId);
        return this.getSqlSession().delete("deleteTagByArticleId", articleId);
    }
}
