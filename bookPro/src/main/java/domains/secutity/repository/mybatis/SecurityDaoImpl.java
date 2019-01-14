package domains.secutity.repository.mybatis;

import domains.secutity.entity.Account;
import domains.secutity.repository.SecurityDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by hhp on 2018/3/5.
 */
@Repository("securityDao")
public final class SecurityDaoImpl extends SqlSessionDaoSupport implements SecurityDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityDaoImpl.class);

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insertAccount(Account account) {
        LOGGER.info("Repository:insertAccount,account={}", account);
        return getSqlSession().insert("insertAccount", account);
    }

    @Override
    public Integer getCountByCondition(String statement, Map<String, String> map) {
        LOGGER.info("Repository:getCountByCondition,map={}", map);
        return (Integer) this.getSqlSession().selectOne(statement, map);
    }

    @Override
    public Account getAccountByLoginName(String loginName) {
        LOGGER.info("Repository:getAccountByLoginName,name={}", loginName);
        return this.getSqlSession().selectOne("getAccountByLoginName", loginName);
    }

    @Override
    public String getPasswordByLoginName(String loginName) {
        LOGGER.info("Repository:getPasswordByLoginName,name={}", loginName);
        return this.getSqlSession().selectOne("getPasswordByLoginName", loginName);
    }

    @Override
    public int resetPassword(Account account) {
        LOGGER.info("Repository:resetPassword,account={}", account);
        return this.getSqlSession().update("resetPassword", account);
    }

    @Override
    public int makeAccountUnEnable(String loginName) {
        LOGGER.info("Repository:makeAccountUnEnable,loginName={}", loginName);
        return this.getSqlSession().update("makeAccountUnEnable", loginName);
    }

    @Override
    public Account getAccountByNickname(String nickname) {
        LOGGER.info("Repository:getAccountByNickname,nickname={}", nickname);
        Account account = this.getSqlSession().selectOne("getAccountByNickname", nickname);
        return account == null ? new Account() : account;
    }

    @Override
    public List<Account> obtainAllAccount(Map<String, Object> map) {
        LOGGER.info("Repository:obtainAllAccount,map={}", map);
        return this.getSqlSession().selectList("obtainAllAccount", map);
    }

    @Override
    public Account getAccountById(Integer accountId) {
        LOGGER.info("Repository:getAccountById,accountId={}", accountId);
        return this.getSqlSession().selectOne("getAccountById", accountId);
    }

    @Override
    public int updateAccount(Account account) {
        LOGGER.info("Repository:updateAccount,account={}", account);
        return this.getSqlSession().update("updateAccount", account);
    }

    @Override
    public List<Account> getAccountByCondition(Map<String, Object> params) {
        LOGGER.info("Repository:getAccountByCondition");
        return this.getSqlSession().selectList("getAccountByCondition", params);
    }
}
