package domains.article;

import domains.article.entity.Category;

import java.util.List;

/**
 * Created by hhp on 2018/3/7.
 */
public interface CategoryService {
    /**
     * 插入一个新的文章类别,不允许重复
     *
     * @param category 类别对象
     * @return         是否操作成功，1表示成功，0表示失败
     */
    int insertCategory(Category category);

    /**
     * 删除文章类别
     *
     * @param category 类别对象，操作此类别对象之前先要查询到此类别对象
     * @return         是否操作成功，1表示成功，0表示失败
     */
    int deleteCategory(Category category);

    /**
     * 更新文章类别,不允许更新的文章类别已经存在于数据库中
     *
     * @param category 类别实体对象，需要先查询到此对象
     * @return   是否操作成功，1表示成功，0表示失败
     */
    int updateCategory(Category category);

    /**
     * 通过id获取到此文章类别，方便对其进行CRUD操作
     *
     * @param id 目录主键id
     * @return   查询到的Category实体对象，如果没有，返回空参对象
     */
    Category getCategoryById(Long id);


    /**
     * 获取所有文章类别
     *
     * @return    所有类别的List集合
     */
    List<Category> getAllCategory();

    /**
     * 根据类别名获取类别对象，在插入分类的时候和更新分类的时候，不允许更新成同名的类别
     *
     * @param typeName 类别名
     * @return         指定类别名的类别实体
     */
    Category getCategoryByName(String typeName);
}
