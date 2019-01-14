package domains.secutity;


import domains.secutity.entity.Account;

import java.util.List;
import java.util.Map;


public interface SecurityService {

    /**
     * 注册帐号，插入用户
     * 这里拥有检查nickname和loginName的操作，需要根据返回值操作
     *
     * @param account 帐号信息
     * @return 是否成功, 0表示失败，1表示成功。
     */
    int insertAccount(Account account);

    /**
     * 插入的帐号不允许与其它帐号有相同的昵称
     *
     * @param nickname 用户昵称
     * @return 是否存在，0表示不存在相同的昵称，昵称可用，1表示存在,将不可用
     */
    Boolean checkAccountExistByNickname(String nickname);

    /**
     * 插入的帐号不允许与其它帐号有相同的登录名
     *
     * @param loginName 登录名
     * @return 是否存在，0表示不存在相同的登录名，登录名可用，1表示存在,将不可用
     */
    Boolean checkAccountExistByLoginName(String loginName);

    /**
     * 账户密码认证，登录操作
     *
     * @param loginName 登录帐号
     * @param password  登录系统密码
     * @return 存在的实体, 如果没有认证成功，返回一个 空参构造函数的Account对象
     */
    Account accountAuthenticate(String loginName, String password);

    /**
     * 重置密码操作
     *
     * @param loginName   登录名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 0表示更新密码失败，1表示更新密码成功
     */
    int updatePassword(String loginName, String oldPassword, String newPassword);


    /**
     * 使得指定的账户不可用
     *
     * @param loginName 账户登录的名称
     * @return 0表示操作失败，1表示操作成功
     */
    int makeAccountUnEnable(String loginName);

    /**
     * 根据别名查找整个账户信息
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
     * 通过账户ID获取账户
     *
     * @param accountId 账户的ID
     * @return 账户的信息
     */
    Account getAccountById(Integer accountId);

    /**
     * 更新账户，主要更新手机号，是否可用，身份证
     * 用户名称，登录名 不允许被修改
     *
     * @param account 用户的实体类
     * @return 是否操作成功, 0表示操作失败，1表示操作成功
     */
    int updateAccount(Account account);

    /**
     * 重置密码，用于后台操作，如果用户需要修改密码的话，需要提供旧密码
     *
     * @param accountId 用户ID
     * @return 是否重置成功，1表示成功，0表示失败
     */
    int resetPassword(Integer accountId, String newPassword);

    /**
     * 根据情况查询数据
     *
     * @param params 主要有Account的属性，比如身份证，登录名，昵称等去查询
     * @return 查询到的指定条件的账户
     */
    List<Account> getAccountByCondition(Map<String, Object> params);
}
