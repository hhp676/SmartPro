package domains.article.impl;

import domains.article.CategoryService;
import domains.article.entity.Category;
import domains.article.repository.CategoryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hhp on 2018/3/7.
 */
@Service("CategoryService")
@Transactional
public final class CategoryServiceImpl implements CategoryService{
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public int insertCategory(Category category) {
        LOGGER.info("Service:insertCategory,category={}",category);
        String typeName = category.getTypeName();
        //如果数据库中已经有此文章类别了，那么就插入失败
        Category category1 = getCategoryByName(typeName);
        if(category1.getId() == null){
            LOGGER.info("没有查询到指定的文章类别");
            return 0;
        }
        if(category1.getTypeName()!=null){
            LOGGER.info("数据库中已经有相同的文章类别了");
            return 0;
        }
        return categoryDao.insertCategory(category);
    }

    @Override
    public int deleteCategory(Category category) {
        LOGGER.info("Service:deleteCategory,category={}",category);
        return categoryDao.deleteCategory(category);
    }

    @Override
    public int updateCategory(Category category) {
        LOGGER.info("Service:updateCategory,category={}",category);
        String typeName = category.getTypeName();
        //如果数据库中已经有此文章类别了，那么就更新失败
        Category category1 = getCategoryByName(typeName);
        if(category1.getId() == null){
            LOGGER.info("没有查询到指定的文章类别");
            return 0;
        }
        if(category1.getTypeName()!=null){
            LOGGER.info("数据库中已经有相同的文章类别了");
            return 0;
        }
        return categoryDao.updateCategory(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        LOGGER.info("Service:getCategoryById,id={}",id);
        return categoryDao.getCategoryById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        LOGGER.info("Service:getAllCategory");
        return categoryDao.getAllCategory();
    }

    @Override
    public Category getCategoryByName(String typeName) {
        LOGGER.info("Service:getCategoryByName,typeName={}",typeName);
        return categoryDao.getCategoryByName(typeName);
    }
}