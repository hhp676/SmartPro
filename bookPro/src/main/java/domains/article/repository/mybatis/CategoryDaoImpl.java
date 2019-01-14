package domains.article.repository.mybatis;


import domains.article.entity.Category;
import domains.article.repository.CategoryDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hhp on 2018/3/7.
 */
@Repository("CategoryDao")
public final class CategoryDaoImpl extends SqlSessionDaoSupport implements CategoryDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDaoImpl.class);

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }


    @Override
    public int insertCategory(Category category) {
        LOGGER.info("Repository:insertCategory,category={}", category);
        return this.getSqlSession().insert("insertCategory", category);
    }

    @Override
    public int deleteCategory(Category category) {
        LOGGER.info("Repository:deleteCategory,category={}", category);
        return this.getSqlSession().delete("deleteCategory", category);
    }

    @Override
    public int updateCategory(Category category) {
        LOGGER.info("Repository:updateCategory,category={}", category);
        return this.getSqlSession().update("updateCategory", category);
    }

    @Override
    public Category getCategoryById(Long id) {
        LOGGER.info("Repository:getCategoryById,category={}", id);
        Category category = this.getSqlSession().selectOne("getCategoryById", id);
        return category == null ? new Category() : category;
    }

    @Override
    public List<Category> getAllCategory() {
        LOGGER.info("Repository:getAllCategory");
        return this.getSqlSession().selectList("getAllCategory");
    }

    @Override
    public Category getCategoryByName(String typeName) {
        LOGGER.info("Repository:getCategoryByName,typeName={}",typeName);
        Category category = this.getSqlSession().selectOne("getCategoryByName",typeName);
        return category == null ? new Category() : category;
    }
}
