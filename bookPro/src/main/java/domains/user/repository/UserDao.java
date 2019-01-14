package domains.user.repository;

import java.util.Map;

/**
 * Created by hhp on 2018/3/5.
 * 本接口编写的目的是操作Shiro的user表
 */
public interface UserDao {

    /**
     * 使用loginName与users表的username进行关联
     * 在插入t_account表之前，先在Shiro的users表里插入用户的相关信息，包括帐号密码密盐
     * Blog Space
     *
     * @param map 封装的loginName,password,password_salt的集合
     * @return 是否操作成功, 0失败, 1成功
     */
    int insertLoginInfo(Map<String, Object> map);

    /**
     * 通过登录名找到密盐
     * Blog Space
     *
     * @param loginName
     * @return 密盐
     */
    String findSaltByLoginName(String loginName);

    /**
     * 更新密码操作，与t_acccount保持一致
     * Blog Space
     * <p>
     * map中有 username，也就是登录名，和新的加密后的密码
     *
     * @return 0表示失败，1表示成功
     */
    int updatePassword(Map<String, String> map);

    /**
     * 为新用户插入角色信息
     *
     * @param id 新用户的id
     * @return
     */
    int insertRoleForNewAccount(Long id);

}