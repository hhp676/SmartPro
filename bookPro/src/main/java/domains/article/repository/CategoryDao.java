package domains.article.repository;

import domains.article.entity.Category;

import java.util.List;

/**
 * Created by hhp on 2018/3/7.
 */
public interface CategoryDao {

    /**
     * 插入一个新的目录
     *
     * @param category 目录对象
     * @return         是否操作成功，1表示成功，0表示失败
     */
    int insertCategory(Category category);

    /**
     * 删除目录
     *
     * @param category 目录对象，操作此目录对象之前先要查询到此目录对象
     * @return         是否操作成功，1表示成功，0表示失败
     */
    int deleteCategory(Category category);

    /**
     * 更新目录
     *
     * @param category 目录实体对象，需要先查询到此对象
     * @return   是否操作成功，1表示成功，0表示失败
     */
    int updateCategory(Category category);

    /**
     * 通过id获取到此目录，方便对其进行CRUD操作
     *
     * @param id 目录主键id
     * @return   查询到的Category实体对象，如果没有，返回空参对象
     */
    Category getCategoryById(Long id);


    /**
     * 获取所有目录
     *
     * @return    所有目录的List集合
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






























