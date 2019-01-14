package domains.secutity.repository;


import domains.secutity.entity.Account;

import java.util.List;
import java.util.Map;

/**
 * Created by hhp on 2018/3/5.
 */
public interface SecurityDao {
    /**
     * 注册帐号，插入用户
     *
     * @param account 帐号信息
     * @return 是否成功
     */
    int insertAccount(Account account);

    /**
     * 根据指定的map中的参数去查询 符合条件的记录的数量
     *
     * @param statement 指定的statement
     * @param map       封装着条件的map
     * @return 是否存在，如果查到的数量大于1，那么就返回true,否则返回false
     */
    Integer getCountByCondition(String statement, Map<String, String> map);

    /**
     * 根据指定的登录名获取帐号
     *
     * @param loginName 登录名
     * @return 指定登录名的实体, 如果没有返回空参构造函数
     */
    Account getAccountByLoginName(String loginName);

    /**
     * 通过登录名字，获取加密后的密码，用于比较输入旧密码是否正确
     *
     * @param loginName 登录密码
     * @return 加密的密码
     */
    String getPasswordByLoginName(String loginName);

    /**
     * 更新密码
     *
     * @param account 帐号
     * @return 是否成功, 1表示成功，0表示失败
     */
    int resetPassword(Account account);

    /**
     * 使得指定的账户不可用
     *
     * @param loginName 账户登录的名称
     * @return 0表示操作失败，1表示操作成功
     */
    int makeAccountUnEnable(String loginName);

    /**
     * 根据别名查找对应的整个账户信息
     * 本方法是为了获取到指定的账户的全部信息，包括核心信息loginName
     *
     * @param nickname 别名
     * @return 此帐号的所有信息，如果没有查询到，返回空参对象
     */
    Account getAccountByNickname(String nickname);


    /**
     * 获取所有账户信息
     *
     * @param map 封装着是否获取可用的信息
     * @return 封装着所有账户信息的List集合
     */
    List<Account> obtainAllAccount(Map<String, Object> map);

    /**
     * 通过账户的ID获取账户
     *
     * @param accountId 账户的ID
     * @return 指定ID的账户相关信息
     */
    Account getAccountById(Integer accountId);

    /**
     * 更新帐号
     *
     * @param account 帐号实体
     * @return 是否更新成功, 0表示失败，1表示成功
     */
    int updateAccount(Account account);

    /**
     * 根据情况查询数据
     *
     * @param params 主要有Account的属性，比如身份证，登录名，昵称等去查询
     * @return 查询到的指定条件的账户
     */
    List<Account> getAccountByCondition(Map<String, Object> params);

}
