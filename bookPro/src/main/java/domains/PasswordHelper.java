package domains;

import com.google.common.collect.Maps;

import domains.secutity.entity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by hhp on 2018/3/5.
 */
public final class PasswordHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordHelper.class);
    private static final PasswordHelper passwordHelper = new PasswordHelper();

    private PasswordHelper() {
    }

    public static PasswordHelper getInstance() {
        return passwordHelper;
    }

    /**
     * 将指定的账户加密，并返回相关的加密信息
     *
     * @param account 指定的账户
     * @return 相关的加密信息
     */
    public Map<String, Object> EncPassword(final Account account) {
        String algorithmName = "md5";
        String username = account.getLoginName();//公钥
        String password = account.getPassword();
        //salt是随机生成的私钥。
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        //循环加密两次
        int hashIterations = 2;
        //加密规则就是“公钥+私钥” username + salt。我们的 password + 加密规则 = 密文，将其存放到数据库中
        SimpleHash hash = new SimpleHash(algorithmName, password, username + salt, hashIterations);
        //加密后密码
        String encodedPassword = hash.toHex();
        //为账户添加加密后密码
        account.setPassword(encodedPassword);
        LOGGER.info("PasswordHelper:加密后密码:{}", encodedPassword);
        LOGGER.info("PasswordHelper:加密的密盐:{}", salt);
        Map<String, Object> map = Maps.newHashMap();
        map.put("username", username);
        map.put("password", encodedPassword);
        map.put("password_salt", salt);
        return map;
    }

    /**
     * 根据明文密码及加密规则获取加密密码
     *
     * @param loginName 登录名
     * @param password  密码
     * @param salt      指定角色的密盐
     * @return 加密后密码
     */
    public String ObtainEncPassword(String loginName, String password, String salt) {
        String algorithmName = "md5";
        //循环加密两次
        int hashIterations = 2;
        //加密规则就是“公钥+私钥” username + salt。我们的 password + 加密规则 = 密文，将其存放到数据库中
        SimpleHash hash = new SimpleHash(algorithmName, password, loginName + salt, hashIterations);
        //返回加密后密码
        return hash.toHex();
    }

    /**
     * 判断用户是否被认证
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户认证成功
     */
    public boolean isAuthencated(String username, String password) {
        LOGGER.info("Authencated username&password by Shiro table users , username = {}", username);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //拜托，login是下面的这个private方法，不是subject.login(token);兄die
        return login(token);
    }

    /**
     * 用于验证用户的帐号密码信息是否合法
     *
     * @param token 封装着用户的帐号密码的UsernamePasswordToken
     * @return 用户输入的信息是否合法
     */
    private boolean login(UsernamePasswordToken token) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (Exception e) {
            return false;
        }
        return subject.isAuthenticated();
    }

    /**
     * 销毁当前的subject对象
     */
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
