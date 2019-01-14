package domains.secutity.impl;


import com.google.common.collect.Maps;
import domains.PasswordHelper;
import domains.secutity.SecurityService;
import domains.secutity.entity.Account;
import domains.secutity.repository.SecurityDao;
import domains.user.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("SecurityService")
@Transactional
public final class SecurityServiceImpl implements SecurityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private SecurityDao securityDao;
    @Autowired
    private UserDao userDao;

    @Override
    public int insertAccount(final Account account) {
        LOGGER.info("Service:insertAccount,account={}", account);
        //使用Shiro安全框架加密，避免在java类里面出现耦合
        //使用Shiro携带的加密类加密，并且返回 username,加密后密码，和密盐
        final Map<String, Object> map = PasswordHelper.getInstance().EncPassword(account);
        //在t_account表中检查是否已经存在相同的名称或者登录名的账户
        if (checkAccountExistByNickname(account.getNickname())) {
            //如果有相同的昵称
            return 0;
        }
        if (checkAccountExistByLoginName(account.getLoginName())) {
            //如果有相同的登录名
            return 0;
        }
        //插入t_account表之前先关联Shiro的users表
        userDao.insertLoginInfo(map);
        int i = securityDao.insertAccount(account);
        if(i > 0){
            //如果插入的Account成功了，那么返回它的主键
            Long id = account.getId();
            int j = userDao.insertRoleForNewAccount(id);
            return j;
        }
        return 0;
    }

    @Override
    public Boolean checkAccountExistByNickname(String nickname) {
        LOGGER.info("Service:checkAccountExist,nickname={}", nickname);
        final Map<String, String> map = Maps.newHashMap();
        map.put("nickname", nickname);
        final Integer count = securityDao.getCountByCondition("checkAccountExistByNickname", map);
        return count > 0;
    }

    @Override
    public Boolean checkAccountExistByLoginName(String loginName) {
        LOGGER.info("Service:checkAccountExist,loginName={}", loginName);
        final Map<String, String> map = Maps.newHashMap();
        map.put("loginName", loginName);
        final Integer count = securityDao.getCountByCondition("checkAccountExistByloginName", map);
        return count > 0;
    }

    @Override
    public Account accountAuthenticate(String loginName, String password) {
        LOGGER.info("Service:accountAuthenticate,loginName={},password={}", loginName, password);
        final boolean isAuthencated = PasswordHelper.getInstance().isAuthencated(loginName, password);
        if (!isAuthencated) {
            return new Account();
        }
        Account account = securityDao.getAccountByLoginName(loginName);
        if(!account.isEnabled()){
            //防止冻结的账户被认为是已经认证过的
            PasswordHelper.getInstance().logout();
        }
        return account;
    }

    @Override
    public int updatePassword(String loginName, String oldPassword, String newPassword) {
        LOGGER.info("Service:accountPassword reset");
        //找到私盐
        final String salt = userDao.findSaltByLoginName(loginName);
        //密码管理者
        final PasswordHelper helper = PasswordHelper.getInstance();
        //旧的密码
        final String oldEncPassword = helper.ObtainEncPassword(loginName, oldPassword, salt);
        //数据库中存放的旧的密码
        final String oldEncPasswordFromDB = securityDao.getPasswordByLoginName(loginName);
        if (!oldEncPassword.equals(oldEncPasswordFromDB)) {
            LOGGER.info("旧密码输入错误");
            //如果旧密码与数据库中的不一致，那么更新密码失败
            return 0;
        }
        //指定的账户
        final Account account = securityDao.getAccountByLoginName(loginName);
        //这是新的加密后的密码
        final String newEncPassword = helper.ObtainEncPassword(loginName, newPassword, salt);
        final Map<String, String> map = Maps.newHashMap();
        map.put("username", loginName);
        map.put("password", newEncPassword);
        //操作Shiro的users表
        int i = userDao.updatePassword(map);
        if (i == 0) {
            LOGGER.info("Shiro的users表密码更新失败");
            return 0;
        }
        account.setPassword(newEncPassword);
        LOGGER.info("根据登录名找到的account是={}，准备进行重置密码", account);
        return securityDao.resetPassword(account);
    }

    @Override
    public int makeAccountUnEnable(String loginName) {
        LOGGER.info("Service:makeAccountUnEnable,loginName={}", loginName);
        return securityDao.makeAccountUnEnable(loginName);
    }

    @Override
    public Account getAccountByNickname(String nickname) {
        LOGGER.info("Service:getAccountByNickname,nickname={}", nickname);
        return securityDao.getAccountByNickname(nickname);
    }

    @Override
    public List<Account> obtainAllAccount(Map<String, Object> map) {
        LOGGER.info("Service:obtainAllAccount,map={}", map);
        return securityDao.obtainAllAccount(map);
    }

    @Override
    public Account getAccountById(Integer accountId) {
        LOGGER.info("Service:getAccountById,Integer={}", accountId);
        return securityDao.getAccountById(accountId);
    }

    /**
     * 更新账户操作，主要更新身份证，手机号，是否可用
     *
     * @param account 用户的实体类
     * @return        是否操作成果能够，0表示失败，1表示成功
     */
    @Override
    public int updateAccount(Account account) {
        LOGGER.info("Service:updateAccount,account={}", account);
        return securityDao.updateAccount(account);
    }

    /**
     * 重置密码操作，主要是在后台进行操作，不需要用户提供旧的密码
     *
     * @param accountId 用户ID
     * @return          是否操作成果能够，0表示失败，1表示成功
     */
    @Override
    public int resetPassword(Integer accountId,String newPassword) {
        LOGGER.info("Service:resetPassword,accountId={},newPassword={}", accountId,newPassword);
        Account account = securityDao.getAccountById(accountId);
        if(account.getId() == null){
            LOGGER.info("未查询到指定ID的用户");
            return 0;
        }
        //密码管理者
        final PasswordHelper helper = PasswordHelper.getInstance();
        //用户名
        String loginName = account.getLoginName();
        //根据用户名获取私盐
        final String salt = userDao.findSaltByLoginName(loginName);
        final String newEncPassword = helper.ObtainEncPassword(loginName,newPassword,salt);
        account.setPassword(newEncPassword);
        final Map<String,String> map = Maps.newHashMap();
        map.put("username",loginName);
        map.put("password",newEncPassword);
        int i = userDao.updatePassword(map);
        if(i == 0){
            LOGGER.info("Shiro的users表操作失败");
            return i;
        }
        int j = securityDao.resetPassword(account);
        if(j == 0){
            LOGGER.info("t_account表操作失败");
            return j ;
        }
        return j;
    }

    @Override
    public List<Account> getAccountByCondition(Map<String, Object> params) {
        LOGGER.info("Service:getAccountByCondition");
        return securityDao.getAccountByCondition(params);
    }
}
